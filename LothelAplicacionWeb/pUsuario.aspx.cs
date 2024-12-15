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
    public partial class pUsuario : System.Web.UI.Page
    {
        private persona persona;
        private huesped huesped;
        private RRHHWSClient daoHuesped;
        private ReservasWSClient daoReservas;
        private BindingList<reservaHabitacion> reservasHuesped;
        private ReservasWSClient daoHabitaciones;
        private BindingList<habitacion> habitaciones;
        private VentasWSClient daoPedidos;
        private BindingList<pedido> pedidosHuesped;
        protected void Page_Init(object sender, EventArgs e)
        {
            esconderBarraLateral(); //Hacer en el init
            persona = (persona)Session["huesped"];
            txtCorreo.Text = persona.correo;
            txtCelular.Text = persona.celular;
            txtUserProfile.Text = persona.cuenta.user;

            txtNombreApellido.Text = persona.nombre + " " + persona.apellidoPaterno + " " + persona.apellidoMaterno;
            txtDNI.Text = persona.dni;

            //falta añadir una diferenciacion en caso sea usuario vip

            //logica para esconder contraseña:
            //txtPasswordProfile.Text = persona.cuenta.password;

            int cantAsteriscos = persona.cuenta.password.Length;
            txtPasswordProfile.Text = new string('*', cantAsteriscos);

            string fechaCompleta = persona.fechaRegistro.ToString();
            string fechaSinHora = fechaCompleta.Substring(0, fechaCompleta.Length - 8);
            txtFechaReg.Text = fechaSinHora;
            lblCancelarEdicion.Visible = false;
            lblConfirmarEdicion.Visible = false;

            //Validaciones
            lblMensajeErrorCelular.Text = "";
            lblMensajeErrorCorreo.Text = "";
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            daoReservas = new ReservasWSClient();
            daoPedidos = new VentasWSClient();
            huesped = (huesped)Session["huesped"];

            //Uso de variable para verificar si existen habitaciones
            Session["vefReserva"] = null;
            Session["vefReserva"] = daoReservas.ListarReservaXIDHuesped(huesped.idPersona);

            noReservas.Visible = false;
            if (Session["vefReserva"] != null)
            {
                noReservas.Visible = false;
                reservasHuesped = new BindingList<reservaHabitacion>(daoReservas.ListarReservaXIDHuesped(huesped.idPersona).ToList());
                gvReservaHabitacion.DataSource = reservasHuesped;
                gvReservaHabitacion.DataBind();
            }
            else //si es nulo escribir que no tiene reservas aun habilitar un div extra que muestre un texto bonito
            {
                noReservas.Visible = true;
            }

            //Marcelo cuando redirigas a la pagina completas estos campos:

            Session["vefPedidos"] = null;
            Session["vefPedidos"] = daoPedidos.listarPedidosPorHuesped(huesped.idPersona);

            noPedidos.Visible = false;
            if (Session["vefPedidos"] != null)
            {
                noReservas.Visible = false;
                pedidosHuesped = new BindingList<pedido>(daoPedidos.listarPedidosPorHuesped(huesped.idPersona).ToList());
                gvPedidosCliente.DataSource = pedidosHuesped;
                gvPedidosCliente.DataBind();
            }
            else
            {
                noPedidos.Visible = true;
            }
        }

        protected void btnEditarPerfilUsuario(object sender, EventArgs e)
        {
            txtCelular.Enabled = true;
            txtCorreo.Enabled = true;
            //txtUserProfile.Enabled = true;
            txtPasswordProfile.Enabled = true;
            lblModificarPerfil.Visible = false;
            lblConfirmarEdicion.Visible = true;
            lblCancelarEdicion.Visible = true;
            txtPasswordProfile.Text = persona.cuenta.password;
        }


        protected void btnConfirmarEdicion(object sender, EventArgs e)
        {
            //Si no se realizaron cambios al perfil
            //txtUserProfile.Text == persona.cuenta.user &&

            //Validaciones para el ingreso de datos a la BD (celular,correo,contraseña)

            lblMensajeErrorCelular.Text = "";
            lblMensajeErrorCorreo.Text = "";

            int cantError = 0;
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

            if (cantError > 0)
            {
                //si hay errores el flujo se corta 
            }
            else //si no hay errores el flujo avanza
            {
                if (txtCorreo.Text == persona.correo &&
            txtCelular.Text == persona.celular &&
            txtPasswordProfile.Text == persona.cuenta.password)
                {
                    Response.Redirect("pUsuario.aspx");
                }
                else
                {
                    //copiando el cascaron igual
                    persona = (persona)Session["huesped"];
                    huesped = new huesped();
                    huesped.cuenta = new cuenta();
                    huesped.idPersona = persona.idPersona;
                    huesped.cuenta.tipocuenta = persona.cuenta.tipocuenta;
                    huesped.cuenta.user = persona.cuenta.user;
                    huesped.cuenta.password = persona.cuenta.password;
                    huesped.correo = persona.correo;
                    huesped.dni = persona.dni;
                    huesped.celular = persona.celular;
                    huesped.nombre = persona.nombre;
                    huesped.apellidoMaterno = persona.apellidoMaterno;
                    huesped.apellidoPaterno = persona.apellidoPaterno;
                    huesped.estado = persona.estado;
                    // falta agregar esVip

                    //colocando los datos que pueden ser modificados
                    huesped.correo = txtCorreo.Text;
                    huesped.cuenta.password = txtPasswordProfile.Text;
                    huesped.celular = txtCelular.Text;

                    daoHuesped = new RRHHWSClient();
                    daoHuesped.modificarHuesped(huesped);

                    //mostrar mensaje que los cambios se han realizado con exito

                    //redireccionando a la pagina de inicio con todo null,
                    //ya que la cuenta se ha refrescado, debe loguearse de nuevo
                    Session["huesped"] = null;
                    Session["administrador"] = null;
                    Response.Redirect("pLothel.aspx");
                }

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

        protected void btnCancelarEdicion(object sender, EventArgs e)
        {
            //Reseteando los textBoxes
            persona = (persona)Session["huesped"];
            txtCorreo.Text = persona.correo;
            txtCelular.Text = persona.celular;

            int cantAsteriscos = persona.cuenta.password.Length;
            txtPasswordProfile.Text = new string('*', cantAsteriscos);

            //Volviendo al estado anterior
            txtCelular.Enabled = false;
            txtCorreo.Enabled = false;
            //txtUserProfile.Enabled = false;
            txtPasswordProfile.Enabled = false;
            lblModificarPerfil.Visible = true;
            lblConfirmarEdicion.Visible = false;
            lblCancelarEdicion.Visible = false;
        }
        protected void esconderBarraLateral()
        {
            var menuLateral = this.Master.FindControl("menuLateral") as HtmlGenericControl;
            menuLateral.Visible = false;
        }

        protected void gvReservaHabitacion_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            daoReservas = new ReservasWSClient();
            huesped = (huesped)Session["huesped"];
            reservasHuesped = new BindingList<reservaHabitacion>(daoReservas.ListarReservaXIDHuesped(huesped.idPersona).ToList());
            gvReservaHabitacion.PageIndex = e.NewPageIndex;
            gvReservaHabitacion.DataSource = reservasHuesped;
            gvReservaHabitacion.DataBind();
        }

        protected void gvPedidosCliente_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            daoPedidos = new VentasWSClient();
            huesped = (huesped)Session["huesped"];
            pedidosHuesped = new BindingList<pedido>(daoPedidos.listarPedidosPorHuesped(huesped.idPersona).ToList());
            gvPedidosCliente.PageIndex = e.NewPageIndex;
            gvPedidosCliente.DataSource = pedidosHuesped;
            gvPedidosCliente.DataBind();
        }

        protected void gvReservaHabitacion_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "idReserva").ToString();
                DateTime fechaDeReserva = Convert.ToDateTime(DataBinder.Eval(e.Row.DataItem, "fechaDeReserva"));
                e.Row.Cells[1].Text = fechaDeReserva.ToString("dd/MM/yyyy");

                DateTime fechaInicio = Convert.ToDateTime(DataBinder.Eval(e.Row.DataItem, "fechaInicio"));
                e.Row.Cells[2].Text = fechaInicio.ToString("dd/MM/yyyy");

                DateTime fechaFin = Convert.ToDateTime(DataBinder.Eval(e.Row.DataItem, "fechaFin"));
                e.Row.Cells[3].Text = fechaFin.ToString("dd/MM/yyyy");

                e.Row.Cells[4].Text = DataBinder.Eval(e.Row.DataItem, "estado").ToString();
            }
        }

        protected void gvPedidosCliente_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "idPedido").ToString();

                DateTime FechaSolicitud = Convert.ToDateTime(DataBinder.Eval(e.Row.DataItem, "fechaSolicitud"));
                e.Row.Cells[1].Text = FechaSolicitud.ToString("dd/MM/yyyy");

                e.Row.Cells[2].Text = DataBinder.Eval(e.Row.DataItem, "estado").ToString();

                e.Row.Cells[3].Text = "S/." + DataBinder.Eval(e.Row.DataItem, "montoAcumulado").ToString();

                reservaHabitacion res = (reservaHabitacion)DataBinder.Eval(e.Row.DataItem, "reserva");
                e.Row.Cells[4].Text = res.idReserva.ToString();
            }
        }

        protected void lbVisualizarReserva_Click(object sender, EventArgs e)
        {
            LinkButton btnDetalles = (LinkButton)sender;
            int idReserva = Int32.Parse(((LinkButton)sender).CommandArgument);

            // Obtén los datos de la reserva y del huésped usando el idReserva
            reservaHabitacion reserva = reservasHuesped.SingleOrDefault(x => x.idReserva == idReserva); // Implementa este método
            huesped huesped = (huesped)Session["huesped"]; // Implementa este método
            habitacion habitacionSelec = daoReservas.listarHabitacionXidHuesped(idReserva, reserva.habitacion.idHabitacion,
                huesped.idPersona);
            // Asigna los valores a las etiquetas del modal
            lblNumeroReserva.Text = reserva.idReserva.ToString();
            lblFechaReserva.Text = reserva.fechaDeReserva.ToString("dd/MM/yyyy");
            lblFechaInicio.Text = reserva.fechaInicio.ToString("dd/MM/yyyy");
            lblFechaFin.Text = reserva.fechaFin.ToString("dd/MM/yyyy");
            lblEstado.Text = reserva.estado.ToString();
            lblNombreHuesped.Text = huesped.nombre + " " + huesped.apellidoPaterno + " " + huesped.apellidoMaterno;
            lblNumeroHabitacion.Text = habitacionSelec.numeroDeCamas.ToString();
            lblPiso.Text = habitacionSelec.piso.ToString();
            lblNumeroCamas.Text = habitacionSelec.numeroDeCamas.ToString();
            lblPrecio.Text = habitacionSelec.precio.ToString();

            /*lblNumeroHabitacion.Text = reserva.habitacion.numeroDeCamas.ToString();
            lblPiso.Text = reserva.habitacion.piso.ToString();
            lblNumeroCamas.Text = reserva.habitacion.numeroDeCamas.ToString();
            lblPrecio.Text = reserva.habitacion.precio.ToString();*/

            // Abre el modal (opcional si data-toggle="modal" data-target="#detalleModal" no funciona)
            ScriptManager.RegisterStartupScript(this, this.GetType(), "LaunchServerSide", "$(function() { $('#detalleModal').modal('show'); });", true);
        }
        protected void lblEliminarReserva_Click(object sender, EventArgs e)
        {
            int idReserva = Int32.Parse(((LinkButton)sender).CommandArgument);
            huesped = (huesped)Session["huesped"];
            reservaHabitacion reservaEliminar = reservasHuesped.SingleOrDefault(x => x.idReserva == idReserva);
            int resultado = daoReservas.eliminarReservaHabitacion(idReserva);
            reservasHuesped.Remove(reservaEliminar);
            gvReservaHabitacion.DataBind();
        }
    }
}