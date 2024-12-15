using LothelAplicacionWeb.LothelSoftWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.EnterpriseServices;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LothelAplicacionWeb
{
    public partial class ListarEmpresasProveedoras : System.Web.UI.Page
    {
        private EventosWSClient daoEmpresa;
        
        private BindingList<empresaProveedora> empresas;

        private empresaProveedora empresa;
        
        protected void Page_Load(object sender, EventArgs e)
        {
            esconderBotonesNav();
            daoEmpresa = new EventosWSClient();
            empresas = new BindingList<empresaProveedora>(daoEmpresa.listarEmpresasProveedoras().ToList()) ;
            gvEmpresas.DataSource = empresas;
            gvEmpresas.DataBind();

            //Validaciones para el ingreso de datos a la BD (RUC,correo)

            lblMensajeErrorRUC.Text = "";
            lblMensajeErrorCorreo.Text = "";
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

        //para el registro los textBoxes estan vacios, pero cuando se llenen se debe validar los dats
        protected void lbRegistrarEmpresa_Click(object sender, EventArgs e)
        {
            //reseteando textBoxes
            lblTituloModalEmpresa.Text = "Registrar Empresa";
            TxtIdEmpresaProv.Text = "";
            txtRazonSocial.Text = "";
            txtRUC.Text = "";

            Session["modificar"] = 0;
            string script = "window.onload = function() { $('#form-modal-empresa').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
        }

        //para editar los datos de la empresa, colocamos los datos en los textBoxes y luego los recuperamos
        protected void btnEditarEmpresa(object sender, EventArgs e)
        {

            lblTituloModalEmpresa.Text = "Modificar empresa";
            int idEmpSelec = Int32.Parse(((LinkButton)sender).CommandArgument);
            empresaProveedora empresaSelec = empresas.SingleOrDefault(x => x.idEmpresa == idEmpSelec);

            TxtIdEmpresaProv.Text = empresaSelec.idEmpresa.ToString();
            txtRazonSocial.Text = empresaSelec.razonSocial;
            txtRUC.Text = empresaSelec.ruc;
            txtCorreo.Text = empresaSelec.correo;

            Session["modificar"] = 1;

            string script = "window.onload = function() { $('#form-modal-empresa').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);

        }

        //Este boton sirve para registrar y modificar
        protected void btnConfirmarRegistro(object sender, EventArgs e)
        {
            int cantErrores = 0;
            //Verificar los datos de los textBoxes

            //Validaciones para el ingreso de datos a la BD (RUC,correo)

            lblMensajeErrorRUC.Text = "";
            lblMensajeErrorCorreo.Text = "";

            if (txtRUC.Text.Length!=11 || !EsNumero(txtRUC.Text))
            {
                lblMensajeErrorRUC.Text = "El RUC ingresado no es valido (debe tener 11 digitos y no contener letras)";
                cantErrores++;
            }

            if (!IsValidEmail(txtCorreo.Text))
            {
                lblMensajeErrorCorreo.Text = "El correo ingresado no es valido\n";
                cantErrores++;
            }

            if (cantErrores > 0)
            {
                //si hay errores el flujo se corta (se queda aqui)
            }
            else
            {
                empresa = new empresaProveedora();
                empresa.razonSocial = txtRazonSocial.Text;
                empresa.ruc = txtRUC.Text;
                empresa.correo = txtCorreo.Text;
                empresa.activo = true;

                if ((int)Session["modificar"] == 0)
                    daoEmpresa.registrarEmpresaProveedora(empresa);
                else if ((int)Session["modificar"] == 1)
                    empresa.idEmpresa = Int32.Parse(TxtIdEmpresaProv.Text);
                daoEmpresa.modificarEmpresaProveedora(empresa);
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
        private static bool IsValidEmail(string email)
        {
            // Expresión regular para validar el formato del correo electrónico
            string pattern = @"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$";

            // Validar el correo electrónico usando la expresión regular
            Regex regex = new Regex(pattern);
            return regex.IsMatch(email);
        }
        protected void btnEliminarEmpresa(object sender, EventArgs e)
        {
            int idEmpresa = Int32.Parse(((LinkButton)sender).CommandArgument);
            daoEmpresa.eliminarEmpresaProveedora(idEmpresa);
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void gvEmpresas_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvEmpresas.PageIndex = e.NewPageIndex;
            gvEmpresas.DataBind();
        }
    }


}