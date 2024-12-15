using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Configuration;
using System.Web.UI;
using System.Web.UI.WebControls;
using LothelAplicacionWeb.LothelSoftWS;

namespace LothelAplicacionWeb
{
    public partial class pMaestra : System.Web.UI.MasterPage
    {
        private RRHHWSClient daoCuenta;
        private persona persona;
        protected void Page_Load(object sender, EventArgs e)
        {
            
            //Validacion
            lblMensajeErrorCuenta.Text = "";
            botonServicio.Visible = false;
            botonRecepcionista.Visible = false;

            if (Session["huesped"] == null && Session["administrador"] == null && Session["recepcionista"] == null)
            {
                botonPerfil.Visible = false;
                botonLogin.Visible = true;
                botonCerrarSesion.Visible = false;
            }

            if (Session["huesped"] != null || Session["administrador"] != null)
            {
                botonLogin.Visible = false;
                botonPerfil.Visible = true;
                botonCerrarSesion.Visible = true;

                //Mostrando los datos en el modal de Perfil
                if (Session["huesped"] != null)
                {
                    persona = (persona)Session["huesped"];
                }

                if (Session["administrador"] != null)
                {
                    persona = (persona)Session["administrador"];
                    lblNombreBarraLateral.Text = persona.nombre + " " + persona.apellidoPaterno;
                }

            }

            //Si estamos en la sesion del administrador debemos esconder los botones de perfil
            if (Session["administrador"] != null)
            {
                botonPerfil.Visible = false;
                botonLogin.Visible = false;
            }

            //Si estamos en la sesion del recepcionista debemos esconder algunos botones
            if (Session["recepcionista"] != null)
            {
                botonPerfil.Visible = false;
                botonLogin.Visible = false;
                botonCerrarSesion.Visible = true;
                menuLateral.Visible = false;
                botonServicio.Visible = true;
            }

            if (Session["recepcionista"] != null && Session["accionRecep"] !=null)
            {
                botonRecepcionista.Visible = true;
                botonCerrarSesion.Visible=true;
                botonServicio.Visible = false;
            }

            if (Session["personal_lavanderia"] != null)
            {
                botonPerfil.Visible = false;
                botonLogin.Visible = false;
                botonCerrarSesion.Visible = true;
                menuLateral.Visible = false;
                botonServicio.Visible = true;
            }
            if (Session["personal_masajes"] != null)
            {
                botonPerfil.Visible = false;
                botonLogin.Visible = false;
                botonCerrarSesion.Visible = true;
                menuLateral.Visible = false;
                botonServicio.Visible = true;
            }

            //Mostrar Ocultar contraseña
            //valores iniciales por defecto
            /*if(Session["mostrar"] == null)
            {
                cuentaPassword.TextMode= TextBoxMode.Password;
            }
            if (Session["mostrar"] == null) Session["mostrar"] = 0;

            if (Session["click"] == null) Session["click"]= 0;
            //logica de mostrado ocultado

            if ((int)Session["click"] == 1)
            {
                if ((int)Session["mostrar"] == 1)
                {
                    cuentaPassword.TextMode = TextBoxMode.SingleLine;
                }
                else
                {
                    if ((int)Session["mostrar"] == 0)
                    {
                        cuentaPassword.TextMode = TextBoxMode.Password;
                    }
                }
            } */

            //por defecto
            btnMostrarPassword.Visible = true;
            btnOcultarPassword.Visible = false;

            //if(Session["password"] != null) cuentaPassword.Text = (string)Session["password"];
            Session["password"] = cuentaPassword.Text;
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Session["accionRecep"] = null;
            Response.Redirect("pRecepcionista.aspx");
        }


        protected void ClickMostrarPassword(object sender, EventArgs e)
        {
            cuentaPassword.TextMode=TextBoxMode.SingleLine;
            btnMostrarPassword.Visible = false; //se oculta
            btnOcultarPassword.Visible = true;
        }

        protected void ClickOcultarPassword(object sender, EventArgs e)
        {
            cuentaPassword.TextMode = TextBoxMode.Password;
            btnOcultarPassword.Visible = false; //se oculta
            btnMostrarPassword.Visible=true;
            cuentaPassword.Attributes.Add("value", (string)Session["password"]); //Esto hace que se agregue el valor luego del postback
        }

        protected void redirectPerfil(object sender, EventArgs e)
        {
            Response.Redirect("pUsuario.aspx");
        }
        protected void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            Session["administrador"] = null;
            Session["huesped"] = null;
            Session["recepcionista"] = null;
            Session["personal_lavanderia"] = null;
            Session["personal_masajes"] = null;
            Session["accionRecep"] = null;
            Response.Redirect("pLothel.aspx");
            //ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }
        protected void btnDiscAdmin(object sender, EventArgs e)
        {
            Session["administrador"] = null; //admin cerró sesión
            Response.Redirect("pLothel.aspx");
            //ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void clickLogearse(object sender, EventArgs e)
        {
            daoCuenta = new RRHHWSClient();

            persona = daoCuenta.obtenerCuentaUserPass(cuentaUsuario.Text, cuentaPassword.Text);
            if(persona == null)
            {
                lblMensajeErrorCuenta.Text = "El usuario o contraseña son incorrectos";
            }
            else
            {
                lblMensajeErrorCuenta.Text = "";
                if (persona.cuenta.tipocuenta.ToString().Equals("ADMINISTRADOR"))
                {
                    Session["administrador"] = persona; //Crear una variable para el administrador
                    Response.Redirect("ListarEmpresasProveedoras.aspx");

                    //Ocultar barra de navegacion

                }
                else
                {
                    if (persona.cuenta.tipocuenta.ToString().Equals("HUESPED"))
                    {
                        //huesped
                        Session["huesped"] = persona; //Crear una variable para el huesped
                        Response.Redirect("pLothel.aspx?view=logeado");
                    }
                    else
                    {
                        if (persona.cuenta.tipocuenta.ToString().Equals("PERSONAL_DE_LAVANDERIA"))
                        {
                            //lavandero
                            Session["personal_lavanderia"] = persona; //Crear una variable para el lavandero
                            Response.Redirect("pPersonalDeLavanderia.aspx?view=pendientes");
                            //Response.Redirect("pLavandero.aspx"); 
                        }
                        else
                        {
                            if (persona.cuenta.tipocuenta.ToString().Equals("PERSONAL_DE_MASAJES"))
                            {
                                //masajista
                                Session["personal_masajes"] = persona; //Crear una variable para el masajista
                                Response.Redirect("pPersonalDeMasaje.aspx?view=pendientes");
                            }
                            else
                            {
                                if (persona.cuenta.tipocuenta.ToString().Equals("RECEPCIONISTA"))
                                {
                                    //recepcionista
                                    Session["recepcionista"] = persona; //Crear una variable para el recepcionista
                                    Response.Redirect("pRecepcionista.aspx");
                                }
                            }
                        }
                    }
                }

            }
        }

    }
}