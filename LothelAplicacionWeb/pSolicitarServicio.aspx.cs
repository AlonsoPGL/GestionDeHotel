using LothelAplicacionWeb.LothelSoftWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.ServiceModel.Channels;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LothelAplicacionWeb
{
    public partial class pSolicitarServicio : System.Web.UI.Page
    {
        private ReservasWSClient daoReservas;
        private BindingList<reservaHabitacion> reservas;
        private VentasWSClient daoPedido;
        private int tipoServicio; // 1 -> lavanderia, 2-> masajes;
        protected void Page_Load(object sender, EventArgs e)
        {
            if(!IsPostBack)
            {
                LlenarHorasMasaje();
            }

        }

        protected void Page_Init(object sender, EventArgs e)
        {
            daoReservas = new ReservasWSClient();
            if (Session["huesped"] != null)
            {
                huesped huesped = (huesped)Session["huesped"];
                reservas = new BindingList<reservaHabitacion>(daoReservas.ListarReservaXIDHuesped(huesped.idPersona).ToList());
                ddlReservasEnCurso.DataSource = reservas;
                ddlReservasEnCurso.DataTextField = "idReserva";
                ddlReservasEnCurso.DataValueField = "idReserva";
                ddlReservasEnCurso.DataBind();
            }
        }

        protected void Page_init(object sender, EventArgs e)
        {
            esconderBarraLateral();
        }

        protected void esconderBarraLateral()
        {
            var menuLateral = this.Master.FindControl("menuLateral") as HtmlGenericControl;
            menuLateral.Visible = false;
        }

        protected void SolicitarLavanderia_Click(object sender, EventArgs e)
        {
            if (Session["Huesped"] == null)
            {
                lblErrorMensaje.Text = "Debe Logearse antes de solicitar un servicio";
            } else
            {
                Session["tipoServicio"] = 1;
                daoReservas = new ReservasWSClient();

                huesped huesped = (huesped)Session["huesped"];
                reservas = new BindingList<reservaHabitacion>(daoReservas.ListarReservaXIDHuesped(huesped.idPersona).ToList());
                ddlReservasEnCurso.DataSource = reservas;
                ddlReservasEnCurso.DataTextField = "idReserva";
                ddlReservasEnCurso.DataValueField = "idReserva";
                ddlReservasEnCurso.DataBind();
                string script = "window.onload = function() { $('#form-modal-carrito').modal('show'); };";
                ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
            }
            
        }

        protected void SolicitarMasajes_Click(object sender, EventArgs e)
        {
            if (Session["Huesped"] == null)
            {
                lblErrorMensaje.Text = "Debe Logearse antes de solicitar un servicio";
            } else
            {
                daoReservas = new ReservasWSClient();

                huesped huesped = (huesped)Session["huesped"];
                reservas = new BindingList<reservaHabitacion>(daoReservas.ListarReservaXIDHuesped(huesped.idPersona).ToList());
                ddlReservasEnCurso.DataSource = reservas;
                ddlReservasEnCurso.DataTextField = "idReserva";
                ddlReservasEnCurso.DataValueField = "idReserva";
                ddlReservasEnCurso.DataBind();
                Session["tipoServicio"] = 2;
                string script = "window.onload = function() { $('#form-modal-carrito').modal('show'); };";
                ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
            }
        }

        protected void lbRegistrarSolicitud_Click(object sender, EventArgs e)
        {
            daoReservas = new ReservasWSClient();

            huesped huesped = (huesped)Session["huesped"];
            reservas = new BindingList<reservaHabitacion>(daoReservas.ListarReservaXIDHuesped(huesped.idPersona).ToList());
            VentasWSClient daoItem = new VentasWSClient();
            VentasWSClient daoPedido = new VentasWSClient();
            pedido ped = new pedido();
            persona quiensolicita = (persona)Session["Huesped"];
            int idReservaSeleccionada = Int32.Parse(ddlReservasEnCurso.SelectedValue);
            reservaHabitacion reservaSeleccionada = new reservaHabitacion();
            foreach (reservaHabitacion res in reservas)
            {
                if (res.idReserva == idReservaSeleccionada)
                {
                    reservaSeleccionada = res;
                    break;
                }
            }
            //reservaHabitacion reservaSeleccionada = reservas.SingleOrDefault(x => x.idReserva == idReservaSeleccionada);
            ped.reserva = reservaSeleccionada;
            
            ped.fechaSolicitud = DateTime.Now;
            ped.fechaSolicitudSpecified = true;
            ped.estado = estadoPedido.Pendiente;
            ped.estadoSpecified = true;

            List<item> servicioAgregar = new List<item>();
            item aux;
            int idaux = 0;
            if((int)Session["tipoServicio"] == 1)
            {
                servicioDeLavanderia servLavanderia= new servicioDeLavanderia();
                servLavanderia.nombre = "LAVADO";
                servLavanderia.descripcion = "Lavado de ropa";
                servLavanderia.precio = 25;
                servLavanderia.calificacion = 5;
                servLavanderia.estado = estadoServicio.POR_CONFIRMAR;
                servLavanderia.estadoSpecified = true;
                servLavanderia.incidencia = "sin incidencias";
                servLavanderia.anotaciones = txtDescripcionServicio.Text;
                
                idaux = daoItem.insertarServicioDeLavanderia(servLavanderia);
                servLavanderia.idIteam = idaux;
                aux = (item)servLavanderia;
                servicioAgregar.Add(aux);

            } else if((int)Session["tipoServicio"] == 2)
            {
                servicioDeMasaje servmasaje = new servicioDeMasaje();
                servmasaje.nombre = "MASAJE";
                servmasaje.descripcion = "masaje completo";
                servmasaje.precio = 25;
                servmasaje.calificacion = 5;
                servmasaje.estado = estadoServicio.POR_CONFIRMAR;
                servmasaje.estadoSpecified = true;
                servmasaje.incidencia = "sin incidencias";
                

                DateTime fechaHoy = DateTime.Now.Date;
                string horaSeleccionada = ddlHoraMasaje.SelectedValue;
                DateTime horaInicio = DateTime.ParseExact($"{fechaHoy:yyyy-MM-dd} {horaSeleccionada}", "yyyy-MM-dd HH:mm", null);
                DateTime horaFin = horaInicio.AddHours(2);

                servmasaje.horaInicio = horaInicio;
                servmasaje.horaInicioSpecified = true;
                servmasaje.horaFin = horaFin;
                servmasaje.horaFinSpecified = true;
                idaux = daoItem.insertarServicioDeMasaje(servmasaje);

                servmasaje.idIteam = idaux;
                aux = (item)servmasaje;
                servicioAgregar.Add(aux);
                
            } else
            {
                System.Diagnostics.Debug.WriteLine("hubo un error al registrar el producto");
            }

            ped.items = servicioAgregar.ToArray();
            ped.montoAcumulado = 25;
            int pedidoRegistrado = daoPedido.registarServicio(ped);

            if (pedidoRegistrado > 0)
            {
                // Limpiar ProductosAgregar si se registró correctamente
                servicioAgregar.Clear();

                // Redirigir a pUsuario.aspx
                Response.Redirect("pUsuario.aspx");
            }
            else
            {
                // Manejar el caso de error en el registro del pedido si es necesario
                System.Diagnostics.Debug.WriteLine("hubo un error al registrar el producto");
            }

        }

        private void LlenarHorasMasaje()
        {
            DateTime now = DateTime.Now;
            DateTime today = now.Date;

            for (int hour = 0; hour < 24; hour++)
            {
                DateTime timeSlot1 = today.AddHours(hour);
                DateTime timeSlot2 = timeSlot1.AddMinutes(30);

                if (timeSlot1 >= now)
                {
                    ddlHoraMasaje.Items.Add(new ListItem($"{hour:D2}:00", $"{hour:D2}:00"));
                }

                if (timeSlot2 >= now )
                {
                    ddlHoraMasaje.Items.Add(new ListItem($"{hour:D2}:30", $"{hour:D2}:30"));
                }
            }
        }
    }
}