using LothelAplicacionWeb.LothelSoftWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LothelAplicacionWeb
{
    public partial class pRecepcionista : System.Web.UI.Page
    {
        private huesped huesped;
        private huesped huespedTextBoxes;
        private RRHHWSClient daoHuesped;

        //Para productos:
        private VentasWSClient daoAlimento;

        private BindingList<alimento> alimentos;

        private alimento alimen;
        private categoriaAlimento cat;

        protected void Page_Init(object sender, EventArgs e)
        {
            esconderBotonesNav();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            lblMensajeErrorCelular.Text = "";
            lblMensajeErrorCorreo.Text = "";
            lblMensajeErrorApellidos.Text = "";
            lblMensajeErrorUsuarioContrasena.Text = "";
            lblMensajeErrorDNI.Text = "";


            Session["huespedTxt"] = null;
        }

        protected void btnConfirmarRegistro_OnClick(object sender, EventArgs e)
        {
            int cantError = 0;

            //una variable de session para evitar se eliminen los datos
            huespedTextBoxes = new huesped();
            huespedTextBoxes.esVIP = true; //por defecto es no vip? o podra registrarlo con subscripcion vip ?
            huespedTextBoxes.dni = txtDNI.Text;
            huespedTextBoxes.nombre = txtNombre.Text;

            huespedTextBoxes.fechaRegistro = new DateTime();
            huespedTextBoxes.fechaRegistroSpecified = true;
            huespedTextBoxes.correo = txtCorreo.Text;
            huespedTextBoxes.celular = txtCelular.Text;
            huespedTextBoxes.apellidoPaterno = txtApellidoPaterno.Text;
            huespedTextBoxes.apellidoMaterno = txtApellidoMaterno.Text;
            huespedTextBoxes.estado = true;

            huespedTextBoxes.cuenta = new cuenta();
            //el id se autoincrementa al insertar
            huespedTextBoxes.cuenta.user = txtUserProfile.Text;
            huespedTextBoxes.cuenta.password = txtPasswordProfile.Text;
            huespedTextBoxes.cuenta.tipocuenta = tipoCuenta.HUESPED;
            huespedTextBoxes.cuenta.tipocuentaSpecified = true;
            Session["huespedTxt"] = huespedTextBoxes;

            
            //validando dni
            if(txtDNI.Text.Length != 8 || txtDNI.Text.Length == 0) { 
                lblMensajeErrorDNI.Text = "El numero de DNI no es valido\n";
                cantError++;
            }

            //validando celular y correo
            if (!EsNumero(txtCelular.Text) || txtCelular.Text.Length != 9 || txtCelular.Text.Length == 0)
            {
                lblMensajeErrorCelular.Text = "Numero de celular ingresado no valido\n";
                cantError++;
            }

            if (!IsValidEmail(txtCorreo.Text) || txtCorreo.Text.Length == 0)
            {
                lblMensajeErrorCorreo.Text = "El correo ingresado no es valido\n";
                cantError++;
            }

            //validacion de los apellidos
            //string[] apellidos = txtApellidos.Text.Trim().Split(' ');

            // Verificar si hay exactamente dos apellidos
            if (txtApellidoPaterno.Text.Length ==0 || txtApellidoPaterno.Text.Length> 30  ||
                txtApellidoMaterno.Text.Length == 0 || txtApellidoMaterno.Text.Length > 30)
            {
                lblMensajeErrorApellidos.Text = "El apellido paterno o materno es invalido";
                cantError++; 
            }

            //usuario y contraseña no validos
            if(txtUserProfile.Text.Length==0 || txtPasswordProfile.Text.Length == 0)
            {
                lblMensajeErrorUsuarioContrasena.Text = "Usuario y/o contraseña no validos";
                cantError++;
            }
        
            if (cantError > 0)
            {
                //si hay errores el flujo se corta (reestablecemos los textBoxes)
                huespedTextBoxes=(huesped)Session["HuespedTxt"];
                txtDNI.Text = huespedTextBoxes.dni;
                txtApellidoPaterno.Text = huespedTextBoxes.apellidoPaterno;
                txtApellidoMaterno.Text = huespedTextBoxes.apellidoMaterno;
                txtCelular.Text = huespedTextBoxes.celular;
                txtCorreo.Text = huespedTextBoxes.correo;
                txtNombre.Text = huespedTextBoxes.nombre;
                txtPasswordProfile.Text = huespedTextBoxes.cuenta.password;
                txtUserProfile.Text = huespedTextBoxes.cuenta.user;

            }
            else //si no hay errores el flujo avanza
            {
                huesped = new huesped();
                // falta agregar esVip
                huesped.esVIP = true; //por defecto es no vip? o podra registrarlo con subscripcion vip ?
                huesped.dni = txtDNI.Text;
                huesped.nombre = txtNombre.Text;
                huesped.apellidoPaterno = txtApellidoPaterno.Text;
                huesped.apellidoMaterno = txtApellidoMaterno.Text;
                huesped.fechaRegistro = DateTime.Now;
                huesped.fechaRegistroSpecified = true;
                huesped.correo = txtCorreo.Text;
                huesped.celular = txtCelular.Text;
                huesped.estado = true;

                huesped.cuenta = new cuenta();
                //el id se autoincrementa al insertar
                huesped.cuenta.user = txtUserProfile.Text;
                huesped.cuenta.password = txtPasswordProfile.Text;
                huesped.cuenta.tipocuenta = tipoCuenta.HUESPED;
                huesped.cuenta.tipocuentaSpecified = true;

                daoHuesped = new RRHHWSClient();
                daoHuesped.registrarHuesped(huesped);

                //mostrar mensaje que los cambios se han realizado con exito y realizar postBack
                //ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
                //Response.Redirect("pRecepcionista.aspx");

                
                string script = "window.onload = function() { $('#form-modal-registroExitoso').modal('show'); };";
                ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);

                txtApellidoMaterno.Text = "";
                txtApellidoPaterno.Text = "";
                txtCelular.Text = "";
                txtCorreo.Text = "";
                txtDNI.Text = "";
                txtNombre.Text = "";
                txtPasswordProfile.Text = "";
                txtUserProfile.Text = "";
            }

        }
        
        protected void btnModalRegExitoso_Click(object sender, EventArgs e)
        {
            txtApellidoMaterno.Text = "";
            txtApellidoPaterno.Text = "";
            txtCelular.Text = "";
            txtCorreo.Text = "";
            txtDNI.Text = "";
            txtNombre.Text = "";
            txtPasswordProfile.Text = "";
            txtUserProfile.Text = "";
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }
            private bool EsNumero(string texto)
        {
            foreach (char c in texto)
            {
                if (!char.IsDigit(c))
                {
                    return false;
                }
            }
            return true;
        }
        private static bool IsValidEmail(string email)
        {
            // Expresión regular para validar el formato del correo electrónico
            string pattern = @"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$";

            // Validar el correo electrónico usando la expresión regular
            Regex regex = new Regex(pattern);
            return regex.IsMatch(email);
        }

        protected void esconderBotonesNav()
        {
            var lothelNavBar = this.Master.FindControl("lothelNavBar") as HtmlGenericControl;
            //barraDeNavegacion.Visible = false;
            lothelNavBar.Visible = false;

            var habitacionesNavBar = this.Master.FindControl("habitacionesNavBar") as HtmlGenericControl;
            //barraDeNavegacion.Visible = false;
            habitacionesNavBar.Visible = false;

            var contactenosNavBar = this.Master.FindControl("contactenosNavBar") as HtmlGenericControl;
            //barraDeNavegacion.Visible = false;
            contactenosNavBar.Visible = false;

            var reservacionNavBar = this.Master.FindControl("reservacionNavBar") as HtmlGenericControl;
            //barraDeNavegacion.Visible = false;
            reservacionNavBar.Visible = false;

            var serviciosNavBar = this.Master.FindControl("serviciosNavBar") as HtmlGenericControl;
            //barraDeNavegacion.Visible = false;
            serviciosNavBar.Visible = false;

            var eventosNavBar = this.Master.FindControl("eventosNavBar") as HtmlGenericControl;
            //barraDeNavegacion.Visible = false;
            eventosNavBar.Visible = false;
        }

        //Gestion de productos:

        //para el registro los textBoxes estan vacios, pero cuando se llenen se debe validar los dats
        protected void lbRegistrarAlimento_Click(object sender, EventArgs e)
        {
            Session["modificar"] = 0;
            string script = "window.onload = function() { $('#form-modal-alimento').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
        }

        protected void btnAlimentos_Click(object sender, EventArgs e)
        {
            Session["accionRecep"] = 1;
            Response.Redirect("ListarAlimentos.aspx");
        }

        protected void btnBebidas_Click(object sender, EventArgs e)
        {
            Session["accionRecep"] = 1;
            Response.Redirect("ListarBebidas.aspx");
        }

        protected void btnCuidadoPersonal_Click(object sender, EventArgs e)
        {
            Session["accionRecep"] = 1;
            Response.Redirect("ListarCuidadoPersonal.aspx");
        }
    }
}