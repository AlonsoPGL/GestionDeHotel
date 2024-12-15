using LothelAplicacionWeb.LothelSoftWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LothelAplicacionWeb
{
    public partial class ListarEspacios : System.Web.UI.Page
    {
        private EventosWSClient daoEspacio;
        private BindingList<espacio> espacios;

        private espacio esp;

        protected void Page_Load(object sender, EventArgs e)
        {
            esconderBotonesNav();
            daoEspacio = new EventosWSClient();
            espacios = new BindingList<espacio>(daoEspacio.listarEspacios().ToList());
            gvEspacios.DataSource = espacios;
            gvEspacios.DataBind();

            //Validaciones
            lblMensajeErrorPiso.Text = "";
            lblMensajeErrorSeccion.Text = "";
            lblMensajeErrorAforo.Text = "";
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

        protected void lbRegistrarEspacio_Click(object sender, EventArgs e)
        {
            //reseteando textBoxes
            lblTituloModalEspacio.Text = "Registrar Espacio";
            txtIdEspacio.Text = "";
            txtPiso.Text = ""; //del 1 al 20
            txtSeccion.Text = ""; //del A a la F
            txtAforo.Text = ""; //del 0 al 1000

            Session["modificar"] = 0;
            string script = "window.onload = function() { $('#form-modal-espacio').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
        }

        protected void btnConfirmarRegistroEspacio(object sender, EventArgs e)
        {
            int cantErrores = 0;
            //Verificar los datos de los textBoxes

            //Validaciones para el ingreso de datos a la BD (RUC,correo)

            lblMensajeErrorPiso.Text = "";
            lblMensajeErrorSeccion.Text = "";
            lblMensajeErrorAforo.Text = "";

            if (txtPiso.Text.Length > 0 && EsNumero(txtPiso.Text))
            {
                if (Int64.Parse(txtPiso.Text) < 1 || Int64.Parse(txtPiso.Text) > 20)
                {
                    lblMensajeErrorPiso.Text = "Numero de piso incorrecto: Debe ser un numero del 1-20";
                    cantErrores++;
                }
            }
            else
            {
                lblMensajeErrorPiso.Text = "Numero de piso incorrecto: Debe ser un numero del 1-20";
                cantErrores++;
            }
            

            if (txtSeccion.Text.Length >30 || txtSeccion.Text.Length==0)
            {
                lblMensajeErrorSeccion.Text = "La seccion debe tener como máximo 30 caracteres\n";
                cantErrores++;
            }

            if(txtAforo.Text.Length > 0 && EsNumero(txtAforo.Text))
            {
                if (Int64.Parse(txtAforo.Text) < 0 || Int64.Parse(txtAforo.Text) > 2000)
                {
                    lblMensajeErrorSeccion.Text = "El aforo máximo es 2000\n";
                    cantErrores++;
                }
            }
            else 
            {
                lblMensajeErrorSeccion.Text = "Debe ingresar un numero válido en el aforo";
                cantErrores++;
            }
            

            if (cantErrores > 0)
            {
                //si hay errores el flujo se corta (se queda aqui)
            }
            else
            {
                espacio esp = new espacio();
                esp.numeroPiso = Int32.Parse(txtPiso.Text);
                esp.seccion = txtSeccion.Text;
                esp.aforo = Int32.Parse(txtAforo.Text);
                esp.disponibilidad = true;


                if ((int)Session["modificar"] == 0)
                    daoEspacio.registrarEspacio(esp);
                else if ((int)Session["modificar"] == 1)
                    esp.idEspacio = Int32.Parse(txtIdEspacio.Text);
                daoEspacio.modificarEspacio(esp);
                Session["modificar"] = null;
                ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
            }
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
        protected void btnEliminarEspacio(object sender, EventArgs e)
        {
            int idEspacio = Int32.Parse(((LinkButton)sender).CommandArgument);
            daoEspacio.eliminarEspacio(idEspacio);
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void btnEditarEspacio(object sender, EventArgs e)
        {

            lblTituloModalEspacio.Text = "Modificar espacio";
            int idEspacioSelec = Int32.Parse(((LinkButton)sender).CommandArgument);
            espacio espacioSelec = espacios.SingleOrDefault(x => x.idEspacio == idEspacioSelec);


            txtIdEspacio.Text = espacioSelec.idEspacio.ToString();
            txtPiso.Text = espacioSelec.numeroPiso.ToString();
            txtSeccion.Text = espacioSelec.seccion;
            txtAforo.Text = espacioSelec.aforo.ToString();

            Session["modificar"] = 1;

            string script = "window.onload = function() { $('#form-modal-espacio').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
        }

        protected void gvEspacios_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvEspacios.PageIndex = e.NewPageIndex;
            gvEspacios.DataBind();
        }
    }
}