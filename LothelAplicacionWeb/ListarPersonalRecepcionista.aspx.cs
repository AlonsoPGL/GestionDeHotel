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
    public partial class ListarPersonalRecepcionista : System.Web.UI.Page
    {
        private RRHHWSClient daoRecepcionista;
        private administrador administrador;


        private BindingList<recepcionista> recepcionistas;

        private recepcionista recepcionista;

        protected void Page_Load(object sender, EventArgs e)
        {
            esconderBotonesNav();
            daoRecepcionista = new RRHHWSClient();
            recepcionistas = new BindingList<recepcionista>(daoRecepcionista.listarRecepcionistas().ToList());
            gvRecepcionistas.DataSource = recepcionistas;
            gvRecepcionistas.DataBind();

            //Validaciones para el ingreso de datos a la BD (celular,correo, dni)

            lblMensajeErrorCelularRecepcionista.Text = "";
            lblMensajeErrorCorreoRecepcionista.Text = "";
            lblMensajeErrorDniRecepcionista.Text = "";
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
            //Response.Redirect("ListarPersonalDeMasajes.aspx");
        }


        protected void lbRegistrarRecepcionista_Click(object sender, EventArgs e)
        {
            limpiarDatos();
            Session["modificar"] = 0;
            lblTituloModalRecepcionista.Text = "Registrar personal";
            lbConfirmarRegistroRecepcionista.Visible = true;
            txtCuentaRecepcionista.Enabled = true;
            txtUsuarioRecepcionista.Enabled = true;
            string script = "window.onload = function() { $('#form-modal-recepcionista').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
        }

        protected void btnConfirmarRegistroRecepcionista(object sender, EventArgs e)
        {
            int cantErrores = 0;
            //Verificar los datos de los textBoxes

            //Validaciones para el ingreso de datos a la BD (RUC,correo)

            lblMensajeErrorDniRecepcionista.Text = "";
            lblMensajeErrorCorreoRecepcionista.Text = "";
            lblMensajeErrorCelularRecepcionista.Text = "";
            lblMensajeErrorSueldo.Text = "";

            if (txtDniRecepcionista.Text.Length != 8 || !EsNumero(txtDniRecepcionista.Text))
            {
                lblMensajeErrorDniRecepcionista.Text = "El DNI ingresado no es valido (debe tener 8 digitos y no contener letras)";
                cantErrores++;
            }

            if (!IsValidEmail(txtCorreoRecepcionista.Text))
            {
                lblMensajeErrorCorreoRecepcionista.Text = "El correo ingresado no es valido\n";
                cantErrores++;
            }
            if (txtCelularRecepcionista.Text.Length != 9 || !EsNumero(txtCelularRecepcionista.Text))
            {
                lblMensajeErrorDniRecepcionista.Text = "El numero celular no es valido (debe contener 9 numeros)";
                cantErrores++;
            }
            if (txtSueldoRecepcionista.Text.Length == 0 || !IsNumberEnteroODouble(txtSueldoRecepcionista.Text) || !IsNumberWithSingleDot(txtSueldoRecepcionista.Text))
            {
                cantErrores++;
                lblMensajeErrorSueldo.Text += "El sueldo ingresado no es válido<br />";
            }

            //usare sueldo para agregar otros posibles errores y no tener que crear labels para cada uno
            if (txtNombreRecepcionista.Text.Length == 0 || txtNombreRecepcionista.Text.Length > 30 ||
                txtApePaternoRecepcionista.Text.Length == 0 || txtApePaternoRecepcionista.Text.Length > 30 ||
                txtApeMaternoRecepcionista.Text.Length == 0 || txtApeMaternoRecepcionista.Text.Length > 30)
            {
                cantErrores++;
                lblMensajeErrorSueldo.Text += "El nombre o apellido ingresado no es valido (max. 30 caracteres)<br />";
            }

            if (txtDeudaRecepcionista.Text.Length == 0 || !IsNumberEnteroODouble(txtDeudaRecepcionista.Text) || !IsNumberWithSingleDot(txtDeudaRecepcionista.Text))
            {
                cantErrores++;
                lblMensajeErrorSueldo.Text += "La deuda ingresada no es valida<br />";
            }

            string fechaSeleccionada = dtpFechaContratacionRecepcionista.Value;

            if (string.IsNullOrEmpty(fechaSeleccionada))
            {
                cantErrores++;
                lblMensajeErrorSueldo.Text += "Por favor, seleccione una fecha de contratación<br />";
            }

            if ((int)Session["modificar"] == 0)
            {
                if (txtUsuarioRecepcionista.Text.Length == 0 || txtCuentaRecepcionista.Text.Length == 0)
                {
                    cantErrores++;
                    lblMensajeErrorSueldo.Text += "El usuario o contraseña no son válidos<br />";
                }
            }
            

            if (cantErrores > 0)
            {
                //si hay errores el flujo se corta (se queda aqui)
            }
            else
            {
                recepcionista = new recepcionista();
                recepcionista.nombre = txtNombreRecepcionista.Text;
                recepcionista.apellidoPaterno = txtApePaternoRecepcionista.Text;
                recepcionista.apellidoMaterno = txtApeMaternoRecepcionista.Text;
                recepcionista.dni = txtDniRecepcionista.Text;
                recepcionista.correo = txtCorreoRecepcionista.Text;
                recepcionista.sueldo = Double.Parse(txtSueldoRecepcionista.Text);
                recepcionista.deuda = Double.Parse(txtDeudaRecepcionista.Text);
                recepcionista.cuenta = new cuenta();
                recepcionista.cuenta.tipocuenta = tipoCuenta.RECEPCIONISTA;
                recepcionista.cuenta.user = txtUsuarioRecepcionista.Text;
                recepcionista.cuenta.password = txtCuentaRecepcionista.Text;
                //recepcionista.usu

                recepcionista.celular = txtCelularRecepcionista.Text;
                if ((int)Session["modificar"] == 0)
                {
                    recepcionista.fechaRegistro = DateTime.Now; //este no cambia, pero cuando hago modificar si se actualiza
                    recepcionista.fechaRegistroSpecified = true;

                    recepcionista.fechaContratacion = DateTime.Parse(dtpFechaContratacionRecepcionista.Value);
                    recepcionista.fechaContratacionSpecified = true;

                }
                recepcionista.activo = true;
                recepcionista.estado = true;

                if (rbTurnoMañanaRecepcionista.Checked)
                    recepcionista.turno = tipoTurno.MAÑANA;
                else if (rbTurnoTardeRecepcionista.Checked)
                    recepcionista.turno = tipoTurno.TARDE;
                else
                    recepcionista.turno = tipoTurno.NOCHE;
                recepcionista.turnoSpecified = true;


                //Variables de sesion para el admin
                administrador adminCopia = (administrador)Session["administrador"];
                recepcionista.administrador = new administrador();
                recepcionista.administrador.idPersona = adminCopia.idPersona;

                if ((int)Session["modificar"] == 0)
                {
                    daoRecepcionista.registrarRecepcionista(recepcionista);
                    //limpiarDatos();
                }
                else if ((int)Session["modificar"] == 1)
                {
                    recepcionista.idPersona = Int32.Parse(txtIdRecepcionista.Text);
                    daoRecepcionista.modificarRecepcionista(recepcionista);
                    //limpiarDatos();
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
            txtNombreRecepcionista.Text = "";
            txtApePaternoRecepcionista.Text = "";
            txtApeMaternoRecepcionista.Text = "";
            txtDniRecepcionista.Text = "";
            txtSueldoRecepcionista.Text = "";
            txtCelularRecepcionista.Text = "";
            txtCorreoRecepcionista.Text = "";
            txtCuentaRecepcionista.Text = "";
            txtUsuarioRecepcionista.Text = "";
            txtDeudaRecepcionista.Text = "";
            // Resetear los RadioButtons
            rbTurnoMañanaRecepcionista.Checked = true;
            rbTurnoTardeRecepcionista.Checked = false;
            rbTurnoNocheRecepcionista.Checked = false;

            txtCuentaRecepcionista.Text = "";
            txtUsuarioRecepcionista.Text = "";
            txtIdRecepcionista.Text = "";
            dtpFechaContratacionRecepcionista.Value = "";
        }

        protected void btnEliminarRecepcionista(object sender, EventArgs e)
        {

            int idLvandero = Int32.Parse(((LinkButton)sender).CommandArgument);
            daoRecepcionista.eliminarRecepcionista(idLvandero);
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);

        }

        protected void btnEditarRecepcionista(object sender, EventArgs e)
        {
            limpiarDatos();
            lblTituloModalRecepcionista.Text = "Modificar personal";
            int idRecepcionistaSelec = Int32.Parse(((LinkButton)sender).CommandArgument);
            recepcionista recepcionistaSelec = recepcionistas.SingleOrDefault(x => x.idPersona == idRecepcionistaSelec);

            lbConfirmarRegistroRecepcionista.Visible = true;
            txtIdRecepcionista.Text = recepcionistaSelec.idPersona.ToString();
            txtApePaternoRecepcionista.Text = recepcionistaSelec.apellidoPaterno;
            txtApeMaternoRecepcionista.Text = recepcionistaSelec.apellidoMaterno;
            txtNombreRecepcionista.Text = recepcionistaSelec.nombre;
            txtDniRecepcionista.Text = recepcionistaSelec.dni;
            dtpFechaContratacionRecepcionista.Value = recepcionistaSelec.fechaContratacion.ToString("yyyy-MM-dd");
            txtCorreoRecepcionista.Text = recepcionistaSelec.correo;
            txtCelularRecepcionista.Text = recepcionistaSelec.celular;
            txtSueldoRecepcionista.Text = recepcionistaSelec.sueldo.ToString();
            txtDeudaRecepcionista.Text=recepcionistaSelec.deuda.ToString();

            // Resetear los RadioButtons
            rbTurnoMañanaRecepcionista.Checked = false;
            rbTurnoTardeRecepcionista.Checked = false;
            rbTurnoNocheRecepcionista.Checked = false;

            txtUsuarioRecepcionista.Enabled = false;
            txtCuentaRecepcionista.Enabled = false;

            if (recepcionistaSelec.turno == tipoTurno.MAÑANA)
                rbTurnoMañanaRecepcionista.Checked = true;
            else if (recepcionistaSelec.turno == tipoTurno.TARDE)
                rbTurnoTardeRecepcionista.Checked = true;
            else
                rbTurnoNocheRecepcionista.Checked = true;


            Session["modificar"] = 1;

            string script = "window.onload = function() { $('#form-modal-recepcionista').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);

        }

        protected void gvRecepcionistas_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvRecepcionistas.PageIndex = e.NewPageIndex;
            gvRecepcionistas.DataBind();
        }




    }
}