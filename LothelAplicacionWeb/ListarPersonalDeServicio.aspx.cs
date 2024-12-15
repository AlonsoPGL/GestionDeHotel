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
    public partial class ListarPersonalDeServicio : System.Web.UI.Page
    {
        private RRHHWSClient daoLavandero;
        private administrador administrador;


        private BindingList<personalDeLavanderia> lavanderos;

        private personalDeLavanderia lavandero;

        protected void Page_Load(object sender, EventArgs e)
        {
            esconderBotonesNav();
            daoLavandero = new RRHHWSClient();
            lavanderos = new BindingList<personalDeLavanderia>(daoLavandero.listarLavanderos().ToList());
            gvLavanderos.DataSource = lavanderos;
            gvLavanderos.DataBind();

            //Validaciones para el ingreso de datos a la BD (celular,correo, dni)

            lblMensajeErrorCelularLavandero.Text = "";
            lblMensajeErrorCorreoLavandero.Text = "";
            lblMensajeErrorDniLavandero.Text = "";
            lblMensajeErrorSueldo.Text = "";
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
        protected void lbListarLavanderos_Click(object sender, EventArgs e)
        {

        }

        protected void lbListarMasajistas_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarMasajistas.aspx");
        }
        protected void lbListarRecepcionistas_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarPersonalRecepcionista.aspx");
        }


        protected void lbRegistrarLavandero_Click(object sender, EventArgs e)
        {
            Session["modificar"] = 0;
            lbConfirmarRegistroLavandero.Visible = true;
            string script = "window.onload = function() { $('#form-modal-lavandero').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
        }

        protected void btnConfirmarRegistroLavandero(object sender, EventArgs e)
        {
            int cantErrores = 0;
            //Verificar los datos de los textBoxes

            //Validaciones para el ingreso de datos a la BD (RUC,correo)

            lblMensajeErrorDniLavandero.Text = "";
            lblMensajeErrorCorreoLavandero.Text = "";
            lblMensajeErrorCelularLavandero.Text = "";
            lblMensajeErrorSueldo.Text = "";

            if (txtDniLavandero.Text.Length != 8 || !EsNumero(txtDniLavandero.Text))
            {
                lblMensajeErrorDniLavandero.Text = "El DNI ingresado no es valido (debe tener 8 digitos y no contener letras)";
                cantErrores++;
            }

            if (!IsValidEmail(txtCorreoLavandero.Text))
            {
                lblMensajeErrorCorreoLavandero.Text = "El correo ingresado no es valido\n";
                cantErrores++;
            }
            if (txtCelularLavandero.Text.Length != 9 || !EsNumero(txtCelularLavandero.Text))
            {
                lblMensajeErrorDniLavandero.Text = "El numero celular no es valido (debe contener 9 numeros)";
                cantErrores++;
            }
            if (txtSueldoLavandero.Text.Length == 0 || !IsNumberEnteroODouble(txtSueldoLavandero.Text) || !IsNumberWithSingleDot(txtSueldoLavandero.Text))
            {
                cantErrores++;
                lblMensajeErrorSueldo.Text += "El sueldo ingresado no es válido<br />";
            }

            //usare sueldo para agregar otros posibles errores y no tener que crear labels para cada uno
            if (txtNombreLavandero.Text.Length == 0 || txtNombreLavandero.Text.Length > 30 ||
                txtApePaternoLavandero.Text.Length == 0 || txtApePaternoLavandero.Text.Length > 30 ||
                txtApeMaternoLavandero.Text.Length == 0 || txtApeMaternoLavandero.Text.Length > 30)
            {
                cantErrores++;
                lblMensajeErrorSueldo.Text += "El nombre o apellido ingresado no es valido (max. 30 caracteres)<br />";
            }

            string fechaSeleccionada = dtpFechaContratacionLavandero.Value;

            if (string.IsNullOrEmpty(fechaSeleccionada))
            {
                cantErrores++;
                lblMensajeErrorSueldo.Text += "Por favor, seleccione una fecha de contratación<br />";
            }

            if (cantErrores > 0)
            {
                //si hay errores el flujo se corta (se queda aqui)
            }
            else
            {
                lavandero = new personalDeLavanderia();
                lavandero.nombre = txtNombreLavandero.Text;
                lavandero.apellidoPaterno = txtApePaternoLavandero.Text;
                lavandero.apellidoMaterno = txtApeMaternoLavandero.Text;
                lavandero.dni = txtDniLavandero.Text;
                lavandero.correo = txtCorreoLavandero.Text;
                lavandero.sueldo = Double.Parse(txtSueldoLavandero.Text);

                lavandero.celular = txtCelularLavandero.Text;
                if ((int)Session["modificar"] == 0)
                {
                    lavandero.fechaRegistro = DateTime.Now; //este no cambia, pero cuando hago modificar si se actualiza
                    lavandero.fechaRegistroSpecified = true;

                    lavandero.fechaContratacion = DateTime.Parse(dtpFechaContratacionLavandero.Value);
                    lavandero.fechaContratacionSpecified = true;

                }
                lavandero.activo = true;
                lavandero.estado = true;

                if (rbTurnoMañanaLavandero.Checked)
                    lavandero.turno = tipoTurno.MAÑANA;
                else if (rbTurnoTardeLavandero.Checked)
                    lavandero.turno = tipoTurno.TARDE;
                else
                    lavandero.turno = tipoTurno.NOCHE;
                lavandero.turnoSpecified = true;


                if (rbSiRiesgoLavandero.Checked)
                    lavandero.autorizacionDeRiesgoBiologico = true;
                else
                    lavandero.autorizacionDeRiesgoBiologico = false;

                //Variables de sesion para el admin
                administrador adminCopia = (administrador)Session["administrador"];
                lavandero.administrador = new administrador();
                lavandero.administrador.idPersona = adminCopia.idPersona;

                if ((int)Session["modificar"] == 0)
                {
                    daoLavandero.registrarLavandero(lavandero);
                    limpiarDatos();
                }
                else if ((int)Session["modificar"] == 1)
                {
                    lavandero.idPersona = Int32.Parse(txtIdLavandero.Text);
                    daoLavandero.modificarLavandero(lavandero);
                    limpiarDatos();
                }
                limpiarDatos();
                Session["modificar"] = null;
                ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
            }
        }

        public bool IsNumberEnteroODouble(string input)
        {
            // Primero intentamos convertir la cadena a un entero
            int intResult;
            if (int.TryParse(input, out intResult))
            {
                return true;
            }

            // Si no es un entero, intentamos convertirla a un double
            double doubleResult;
            if (double.TryParse(input, out doubleResult))
            {
                return true;
            }

            // Si se encuentra el punto al inicio o final

            if (input[0] == '.' || input[input.Length - 1] == '.')
            {
                return false;
            }

            // Si no es ni entero ni double, devolvemos false
            return false;
        }

        public bool IsNumberWithSingleDot(string input)
        {
            if (string.IsNullOrEmpty(input))
            {
                return false;
            }

            // Definir la expresión regular
            string pattern = @"^\d+(\.\d+)?$";

            // Comprobar si la cadena coincide con el patrón
            return Regex.IsMatch(input, pattern);
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
        public void limpiarDatos()
        {
            txtNombreLavandero.Text = "";
            txtApePaternoLavandero.Text = "";
            txtApeMaternoLavandero.Text = "";
            txtDniLavandero.Text = "";
            txtSueldoLavandero.Text = "";
            txtCelularLavandero.Text = "";
            txtCorreoLavandero.Text = "";
            // Resetear los RadioButtons
            rbTurnoMañanaLavandero.Checked = false;
            rbTurnoTardeLavandero.Checked = false;
            rbTurnoNocheLavandero.Checked = false;

            rbSiRiesgoLavandero.Checked = false;
            rbNoRiesgoLavandero.Checked = false;
        }

        protected void btnEliminarLavandero(object sender, EventArgs e)
        {

            int idLvandero = Int32.Parse(((LinkButton)sender).CommandArgument);
            daoLavandero.eliminarLavandero(idLvandero);
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);

        }
        protected void btnVerDetalleLavandero(object sender, EventArgs e)
        {

            lblTituloModalLavandero.Text = "Ver Detalle";
            int idLavanderoSelec = Int32.Parse(((LinkButton)sender).CommandArgument);
            personalDeLavanderia lavanderoSelec = lavanderos.SingleOrDefault(x => x.idPersona == idLavanderoSelec);

            //TxtIdEmpresaProv.Text = empresaSelec.idEmpresa.ToString();
            txtIdLavandero.Text = lavanderoSelec.idPersona.ToString();
            txtNombreLavandero.Text = lavanderoSelec.nombre;
            txtApePaternoLavandero.Text = lavanderoSelec.apellidoPaterno;
            txtApeMaternoLavandero.Text = lavanderoSelec.apellidoMaterno;
            txtDniLavandero.Text = lavanderoSelec.dni;

            txtCorreoLavandero.Text = lavanderoSelec.correo;
            txtCelularLavandero.Text = lavanderoSelec.celular;
            txtSueldoLavandero.Text = lavanderoSelec.sueldo.ToString();
            dtpFechaContratacionLavandero.Value = lavanderoSelec.fechaContratacion.ToString("yyyy-MM-dd");

            //lbConfirmarRegistroLavandero.Visible = false;
            // Resetear los RadioButtons
            rbTurnoMañanaLavandero.Checked = false;
            rbTurnoTardeLavandero.Checked = false;
            rbTurnoNocheLavandero.Checked = false;

            rbSiRiesgoLavandero.Checked = false;
            rbNoRiesgoLavandero.Checked = false;

            if (lavanderoSelec.turno == tipoTurno.MAÑANA)
                rbTurnoMañanaLavandero.Checked = true;
            else if (lavanderoSelec.turno == tipoTurno.TARDE)
                rbTurnoTardeLavandero.Checked = true;
            else
                rbTurnoNocheLavandero.Checked = true;

            if (lavanderoSelec.autorizacionDeRiesgoBiologico == true)
                rbSiRiesgoLavandero.Checked = true;
            else
                rbNoRiesgoLavandero.Checked = true;


            Session["modificar"] = 2;

            string script = "window.onload = function() { $('#form-modal-lavandero').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
            
        }

        protected void btnEditarLavandero(object sender, EventArgs e)
        {

            lblTituloModalLavandero.Text = "Modificar personal";
            int idLavanderoSelec = Int32.Parse(((LinkButton)sender).CommandArgument);
            personalDeLavanderia lavanderoSelec = lavanderos.SingleOrDefault(x => x.idPersona == idLavanderoSelec);

            lbConfirmarRegistroLavandero.Visible = true;
            txtIdLavandero.Text = lavanderoSelec.idPersona.ToString();
            txtApePaternoLavandero.Text = lavanderoSelec.apellidoPaterno;
            txtApeMaternoLavandero.Text = lavanderoSelec.apellidoMaterno;
            txtNombreLavandero.Text = lavanderoSelec.nombre;
            txtDniLavandero.Text = lavanderoSelec.dni;
            dtpFechaContratacionLavandero.Value = lavanderoSelec.fechaContratacion.ToString("yyyy-MM-dd");
            txtCorreoLavandero.Text = lavanderoSelec.correo;
            txtCelularLavandero.Text = lavanderoSelec.celular;
            txtSueldoLavandero.Text = lavanderoSelec.sueldo.ToString();

            // Resetear los RadioButtons
            rbTurnoMañanaLavandero.Checked = false;
            rbTurnoTardeLavandero.Checked = false;
            rbTurnoNocheLavandero.Checked = false;

            rbSiRiesgoLavandero.Checked = false;
            rbNoRiesgoLavandero.Checked = false;

            if (lavanderoSelec.turno == tipoTurno.MAÑANA)
                rbTurnoMañanaLavandero.Checked = true;
            else if (lavanderoSelec.turno == tipoTurno.TARDE)
                rbTurnoTardeLavandero.Checked = true;
            else
                rbTurnoNocheLavandero.Checked = true;

            if (lavanderoSelec.autorizacionDeRiesgoBiologico == true)
                rbSiRiesgoLavandero.Checked = true;
            else
                rbNoRiesgoLavandero.Checked = true;

            Session["modificar"] = 1;

            string script = "window.onload = function() { $('#form-modal-lavandero').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);

        }

        protected void gvLavanderos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvLavanderos.PageIndex = e.NewPageIndex;
            gvLavanderos.DataBind();
        }
    }
}