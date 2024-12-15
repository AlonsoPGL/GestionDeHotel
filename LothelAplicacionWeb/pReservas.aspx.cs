using LothelAplicacionWeb.LothelSoftWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics.Eventing.Reader;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LothelAplicacionWeb
{
    public partial class pReservas : System.Web.UI.Page
    {
        private ReservasWSClient daoSimple;
        private ReservasWSClient daoMatrimonial;
        private ReservasWSClient daoFamiliar;
        private BindingList<simple> simples;
        private BindingList<matrimonial> matrimoniales;
        private BindingList<familiar> familiares;
        private ReservasWSClient daoReserva;
        private reservaHabitacion reserva;
        private huesped huesped;
        protected void Page_Load(object sender, EventArgs e)
        {
            daoSimple = new ReservasWSClient();
            daoMatrimonial = new ReservasWSClient();
            daoFamiliar = new ReservasWSClient();

            
            lblMensajeError.Text = "";
           


            esconderBarraLateral();
            String accion = Request.QueryString["selectHabitacion"];
            if (accion == "habitacionSimple")
            {
                simples = new BindingList<simple>(daoSimple.ListarSimples().ToList());
                parentRepeater.DataSource = simples;
                parentRepeater.DataBind();
            }
            else if (accion == "habitacionMatri")
            {
                matrimoniales = new BindingList<matrimonial>(daoMatrimonial.ListarMatrimoniales().ToList());
                parentRepeater.DataSource = matrimoniales;
                parentRepeater.DataBind();
            }
            else if (accion == "habitacionFamiliar")
            {
                familiares = new BindingList<familiar>(daoFamiliar.ListarFamiliar().ToList());
                parentRepeater.DataSource = familiares;
                parentRepeater.DataBind();
            }
            if (accion == null)
            {
                simples = new BindingList<simple>(daoSimple.ListarSimples().ToList());
                parentRepeater.DataSource = simples;
                parentRepeater.DataBind();
            }

            if (numHuesped.Text == "")
            {
                String cantHuesped = (string)Session["cantHuespedesEscoger"];
                if (cantHuesped == null)
                {
                    numHuesped.Text = numHuespedEscoger.Text + " 1  adultos";
                }
                else
                {
                    DateTime fechaInicio = (DateTime)Session["fechaInicioReserva"];
                    DateTime fechaSalida = (DateTime)Session["fechaSalidaReserva"];
                    numHuesped.Text = cantHuesped;
                    dtpFechaInicioReserva.Value = fechaInicio.ToString("yyyy-MM-dd");
                    dtpFechaSalidaReserva.Value = fechaSalida.ToString("yyyy-MM-dd");
                }
            }
            else
            {
                if (numHuespedEscoger.Text != "")
                {
                    numHuesped.Text = numHuespedEscoger.Text + "   adultos";
                    Session["cantHuespedesEscoger"] = numHuesped.Text;
                }
                string fechaEntrada = dtpFechaInicioReserva.Value;
                DateTime.TryParse(fechaEntrada, out DateTime fechaEntr);
                Session["fechaInicioReserva"] = fechaEntr;

                string fechaSalida = dtpFechaSalidaReserva.Value;
                DateTime.TryParse(fechaSalida, out DateTime fechaSal);
                Session["fechaSalidaReserva"] = fechaSal;
            }


        }

        protected void esconderBarraLateral()
        {
            var menuLateral = this.Master.FindControl("menuLateral") as HtmlGenericControl;
            menuLateral.Visible = false;
        }

        protected void btnReservar_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(dtpFechaInicioReserva.Value) || string.IsNullOrWhiteSpace(dtpFechaSalidaReserva.Value) || string.IsNullOrWhiteSpace(numHuesped.Text))
            {
                string script = "alert('Primero debe llenar los campos de fechas y número de huéspedes.');";
                ClientScript.RegisterStartupScript(this.GetType(), "Pop", script, true);
                return;
            }
            DateTime fechaInicio = DateTime.Parse(dtpFechaInicioReserva.Value);
            DateTime fechaSalida = DateTime.Parse(dtpFechaSalidaReserva.Value);

            if (fechaSalida <= fechaInicio)
            {
                lblMensajeError.Text = "La fecha de ingreso debe ser menor a la fecha de salida";
                return;
            }

            if(fechaInicio <= DateTime.Now)
            {
                lblMensajeError.Text = "La fecha de ingreso debe ser mayor o igual a la fecha actual";
                return;
            }
            lblMensajeError.Text = "";
            int idHabitacion = Int32.Parse(((Button)sender).CommandArgument);
            String accion = Request.QueryString["selectHabitacion"];

            if (accion == "habitacionSimple" || accion==null)
            {
                Session["habitacionReserva"] = simples.SingleOrDefault(x => x.idHabitacion == idHabitacion);
            }
            else if (accion == "habitacionMatri")
            {
                Session["habitacionReserva"] = matrimoniales.SingleOrDefault(x => x.idHabitacion == idHabitacion);
            }
            else if (accion == "habitacionFamiliar")
            {
                Session["habitacionReserva"] = familiares.SingleOrDefault(x => x.idHabitacion == idHabitacion);
            }
           
            string pagoScript = "window.onload= function () { PagoModal() };";
            ClientScript.RegisterStartupScript(GetType(), "", pagoScript, true);
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
        protected void btnPagar_Click(object sender, EventArgs e)
        {

            habitacion habitacion = (habitacion)Session["habitacionReserva"];
            reserva = new reservaHabitacion();

            DateTime fechaEntrada;
            DateTime.TryParse(dtpFechaInicioReserva.Value, out fechaEntrada);
            reserva.fechaInicio = fechaEntrada;
            reserva.fechaInicioSpecified = true;
            DateTime fechaSalida;
            DateTime.TryParse(dtpFechaSalidaReserva.Value, out fechaSalida);
            reserva.fechaFin = fechaSalida;
            reserva.fechaFinSpecified = true;
            reserva.fechaDeReserva = DateTime.Now;
            reserva.fechaDeReservaSpecified = true;

            reserva.estado = estadoReserva.EN_CURSO;
            reserva.estadoSpecified = true;
            huesped = (huesped)Session["huesped"];
            if (huesped == null)
            {
                errorPagarNoLogueado.Text = "No se ha detectado una sesión activa. Por favor, inicie sesión.";
                return;
            }
            reserva.habitacion = new habitacion();
            reserva.habitacion.idHabitacion = habitacion.idHabitacion;
            reserva.huesped = new huesped();
            reserva.huesped.idPersona = huesped.idPersona;
            daoReserva = new ReservasWSClient();
            int resultado = daoReserva.insertarReservaHabitacion(reserva);
            dtpFechaInicioReserva.Value = string.Empty;
            dtpFechaSalidaReserva.Value=string.Empty;
            numHuesped.Text = numHuesped.Text = numHuespedEscoger.Text + " 1  adultos";
            Session["cantHuespedesEscoger"] = null;
            Session["fechaInicioReserva"]=null;
            Session["fechaSalidaReserva"] = null;
            Response.Redirect("pUsuario.aspx");

        }

        protected void seleccionarSimpleClick(object sender, EventArgs e)
        {
            Response.Redirect("pReservas.aspx?selectHabitacion=habitacionSimple");
        }
        protected void seleccionarMatrimonClick(object sender, EventArgs e)
        {
            Response.Redirect("pReservas.aspx?selectHabitacion=habitacionMatri");
        }
        protected void seleccionarFamilarClick(object sender, EventArgs e)
        {
            Response.Redirect("pReservas.aspx?selectHabitacion=habitacionFamiliar");
        }

    }


}