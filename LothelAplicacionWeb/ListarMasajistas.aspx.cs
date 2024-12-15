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
    public partial class ListarMasajistas : System.Web.UI.Page
    {
        private RRHHWSClient daoMasajista;
        private administrador administrador;


        private BindingList<personalDeMasajes> masajistas;

        private personalDeMasajes masajista;

        protected void Page_Load(object sender, EventArgs e)
        {
            esconderBotonesNav();
            daoMasajista = new RRHHWSClient();
            masajistas = new BindingList<personalDeMasajes>(daoMasajista.listarMasajistas().ToList());
            gvMasajistas.DataSource = masajistas;
            gvMasajistas.DataBind();

            //Validaciones para el ingreso de datos a la BD (celular,correo, dni)

            lblMensajeErrorCelularMasajista.Text = "";
            lblMensajeErrorCorreoMasajista.Text = "";
            lblMensajeErrorDniMasajista.Text = "";
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
            Response.Redirect("ListarPersonalDeServicio.aspx");
        }

        protected void lbListarMasajistas_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarMasajistas.aspx");
        }
        protected void lbListarRecepcionistas_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarPersonalRecepcionista.aspx");
        }


        protected void lbRegistrarMasajista_Click(object sender, EventArgs e)
        {
            limpiarDatos();
            Session["modificar"] = 0;
            lbConfirmarRegistroMasajista.Visible = true;
            string script = "window.onload = function() { $('#form-modal-masajista').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
        }

        protected void btnConfirmarRegistroMasajista(object sender, EventArgs e)
        {
            int cantErrores = 0;
            //Verificar los datos de los textBoxes

            //Validaciones para el ingreso de datos a la BD (RUC,correo)

            lblMensajeErrorDniMasajista.Text = "";
            lblMensajeErrorCorreoMasajista.Text = "";
            lblMensajeErrorCelularMasajista.Text = "";
            lblMensajeErrorSueldo.Text = "";

            if (txtDniMasajista.Text.Length != 8 || !EsNumero(txtDniMasajista.Text))
            {
                lblMensajeErrorDniMasajista.Text = "El DNI ingresado no es valido (debe tener 8 digitos y no contener letras)";
                cantErrores++;
            }

            if (!IsValidEmail(txtCorreoMasajista.Text))
            {
                lblMensajeErrorCorreoMasajista.Text = "El correo ingresado no es valido\n";
                cantErrores++;
            }
            if (txtCelularMasajista.Text.Length != 9 || !EsNumero(txtCelularMasajista.Text))
            {
                lblMensajeErrorDniMasajista.Text = "El numero celular no es valido (debe contener 9 numeros)";
                cantErrores++;
            }
            if (txtSueldoMasajista.Text.Length == 0 || !IsNumberEnteroODouble(txtSueldoMasajista.Text) || !IsNumberWithSingleDot(txtSueldoMasajista.Text))
            {
                cantErrores++;
                lblMensajeErrorSueldo.Text += "El sueldo ingresado no es válido<br />";
                //usare sueldo para agregar otros posibles errores y no tener que crear labels para cada uno
            }

            //usare sueldo para agregar otros posibles errores y no tener que crear labels para cada uno
            if (txtNombreMasajista.Text.Length == 0 || txtNombreMasajista.Text.Length > 30 || 
                txtApePaternoMasajista.Text.Length == 0 || txtApePaternoMasajista.Text.Length > 30 ||
                txtApeMaternoMasajista.Text.Length == 0 || txtApeMaternoMasajista.Text.Length > 30)
            {
                cantErrores++;
                lblMensajeErrorSueldo.Text += "El nombre o apellido ingresado no es valido (max. 30 caracteres)<br />";
            }

            string fechaSeleccionada = dtpFechaContratacionMasajista.Value;

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
                masajista = new personalDeMasajes();
                masajista.nombre = txtNombreMasajista.Text;
                masajista.apellidoPaterno = txtApePaternoMasajista.Text;
                masajista.apellidoMaterno = txtApeMaternoMasajista.Text;
                masajista.dni = txtDniMasajista.Text;
                masajista.correo = txtCorreoMasajista.Text;
                masajista.sueldo = Double.Parse(txtSueldoMasajista.Text);
                masajista.especializacion = txtEspecializacionoMasajista.Text;

                masajista.celular = txtCelularMasajista.Text;
                if ((int)Session["modificar"] == 0)
                {
                    masajista.fechaRegistro = DateTime.Now; //este no cambia, pero cuando hago modificar si se actualiza
                    masajista.fechaRegistroSpecified = true;

                    masajista.fechaContratacion = DateTime.Parse(dtpFechaContratacionMasajista.Value);
                    masajista.fechaContratacionSpecified = true;

                }
                masajista.activo = true;
                masajista.estado = true;

                if (rbTurnoMañanaMasajista.Checked)
                    masajista.turno = tipoTurno.MAÑANA;
                else if (rbTurnoTardeMasajista.Checked)
                    masajista.turno = tipoTurno.TARDE;
                else
                    masajista.turno = tipoTurno.NOCHE;
                masajista.turnoSpecified = true;


                //Variables de sesion para el admin
                administrador adminCopia = (administrador)Session["administrador"];
                masajista.administrador = new administrador();
                masajista.administrador.idPersona = adminCopia.idPersona;

                if ((int)Session["modificar"] == 0)
                {
                    daoMasajista.registrarMasajista(masajista);
                }
                else if ((int)Session["modificar"] == 1)
                {
                    masajista.idPersona = Int32.Parse(txtIdMasajista.Text);
                    daoMasajista.modificarMasajista(masajista);
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
            txtNombreMasajista.Text = "";
            txtApePaternoMasajista.Text = "";
            txtApeMaternoMasajista.Text = "";
            txtDniMasajista.Text = "";
            txtSueldoMasajista.Text = "";
            txtCelularMasajista.Text = "";
            txtCorreoMasajista.Text = "";
            txtEspecializacionoMasajista.Text = "";
            dtpFechaContratacionMasajista.Value = "";
            // Resetear los RadioButtons
            rbTurnoMañanaMasajista.Checked = false;
            rbTurnoTardeMasajista.Checked = false;
            rbTurnoNocheMasajista.Checked = false;
        }

        protected void btnEliminarMasajista(object sender, EventArgs e)
        {

            int idLvandero = Int32.Parse(((LinkButton)sender).CommandArgument);
            daoMasajista.eliminarMasajista(idLvandero);
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);

        }

        protected void btnEditarMasajista(object sender, EventArgs e)
        {

            lblTituloModalMasajista.Text = "Modificar personal";
            int idMasajistaSelec = Int32.Parse(((LinkButton)sender).CommandArgument);
            personalDeMasajes masajistaSelec = masajistas.SingleOrDefault(x => x.idPersona == idMasajistaSelec);

            lbConfirmarRegistroMasajista.Visible = true;
            txtIdMasajista.Text = masajistaSelec.idPersona.ToString();
            txtApePaternoMasajista.Text = masajistaSelec.apellidoPaterno;
            txtApeMaternoMasajista.Text = masajistaSelec.apellidoMaterno;
            txtNombreMasajista.Text = masajistaSelec.nombre;
            txtDniMasajista.Text = masajistaSelec.dni;
            dtpFechaContratacionMasajista.Value= masajistaSelec.fechaContratacion.ToString("yyyy-MM-dd");
            //dtpFechaContratacionMasajista.Value = masajista.fechaContratacion;
            txtCorreoMasajista.Text = masajistaSelec.correo;
            txtCelularMasajista.Text = masajistaSelec.celular;
            txtSueldoMasajista.Text = masajistaSelec.sueldo.ToString();
            txtEspecializacionoMasajista.Text = masajistaSelec.especializacion;

            // Resetear los RadioButtons
            rbTurnoMañanaMasajista.Checked = false;
            rbTurnoTardeMasajista.Checked = false;
            rbTurnoNocheMasajista.Checked = false;


            if (masajistaSelec.turno == tipoTurno.MAÑANA)
                rbTurnoMañanaMasajista.Checked = true;
            else if (masajistaSelec.turno == tipoTurno.TARDE)
                rbTurnoTardeMasajista.Checked = true;
            else
                rbTurnoNocheMasajista.Checked = true;



            Session["modificar"] = 1;

            string script = "window.onload = function() { $('#form-modal-masajista').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);

        }

        protected void gvLMasajistas_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvMasajistas.PageIndex = e.NewPageIndex;
            gvMasajistas.DataBind();
        }
    }
}