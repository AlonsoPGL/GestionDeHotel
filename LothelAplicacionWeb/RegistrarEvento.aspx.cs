using LothelAplicacionWeb.LothelSoftWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing.Drawing2D;
using System.EnterpriseServices;
using System.Globalization;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;




namespace LothelAplicacionWeb
{
    public partial class RegistrarEvento : System.Web.UI.Page
    {

        private EventosWSClient daoEvento;
        private BindingList<evento> eventos;
        private estadoEvento estEvento;
        private evento even;

        private EventosWSClient daoReservaEspacio;
        private reservaEspacio reservaEsp;
        private BindingList<reservaEspacio> reservasListaEspacio;

        private EventosWSClient daoEspacio;
        private BindingList<espacio> espacios; 

        private BindingList<Int32> horas;
       
        protected void Page_Load(object sender, EventArgs e)
        {
            esconderBotonesNav();

            daoEvento = new EventosWSClient();
            daoEspacio = new EventosWSClient();
            daoReservaEspacio = new EventosWSClient();
            horas = new BindingList<Int32>();


            espacios = new BindingList<espacio>(daoEspacio.listarEspaciosPorNombre(" ").ToList());

            gvEspacios.DataSource = espacios;
            gvEspacios.DataBind();

            String accion = Request.QueryString["accion"];
            if (accion != null && accion == "modificar" && Session["eventoSeleccionado"] != null)
            {
                even = (evento)(Session["eventoSeleccionado"]);
                if (!IsPostBack)
                {
                    even.reservaEspacios = daoReservaEspacio.listarReservasXIdEvento(even.idEvento);

                    // Convierte el arreglo a un BindingList
                    reservasListaEspacio = new BindingList<reservaEspacio>(even.reservaEspacios.ToList());

                    // Almacena el BindingList en la sesión
                    Session["bindingReservaEspacio"] = reservasListaEspacio;
                    mostrarDatos();
                }
                
            }
            else
            {
                even = new evento();
                reservasListaEspacio = new BindingList<reservaEspacio>();
                if (!IsPostBack)
                {
                    Session["idOrdenVenta"] = null;
                    Session["bindingReservaEspacio"] = null;
                    Session["espacio"] = null;
                    Session["estadoEvento"] = null;
                }
            }
            if (Session["bindingReservaEspacio"] == null)
            {
                reservasListaEspacio = new BindingList<reservaEspacio>();
                even.reservaEspacios = (new BindingList<reservaEspacio>()).ToArray();
            }
            else
            {
                reservasListaEspacio = new BindingList<reservaEspacio>();
                reservasListaEspacio = (BindingList<reservaEspacio>)Session["bindingReservaEspacio"];
                even.reservaEspacios = reservasListaEspacio.ToArray();
            }
            gvreservaEspacios.DataSource = even.reservaEspacios;
            gvreservaEspacios.DataBind();

            lblErrorEventoReservaEspacio.Text= "";

            btnVerDisponibilidadEspacio.Visible = true;

            if(dtpFechaReservaEspacio.Value=="" || txtIDEspacio.Text == "")
            {
                btnVerDisponibilidadEspacio.Visible = false;
            }
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

        protected void lbRegresarListarEventos_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarEventos.aspx");
        }

        public void mostrarDatos()
        {
            if (even != null)
            {
                txtNombreEvento.Text = even.nombre;
                txtIdEvento.Text = even.idEvento.ToString();
                txtDescripcionEvento.Text = even.descripcion;
                txtCantidadAsisEvento.Text = even.cantidadAsistentes.ToString();
                dtpFechaInicioEvento.Value = even.fechaInicio.ToString("yyyy-MM-dd");
            }

        }


        protected void btnBuscarEspacio_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { $('#form-modal-espacio').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
        }

        protected void lbBusquedaEspacioModal_Click(object sender, EventArgs e)
        {
            espacios = new BindingList<espacio>(daoEspacio.listarEspaciosPorNombre(txtNombreEspacioModal.Text).ToList());
            gvEspacios.DataSource = espacios;
            gvEspacios.DataBind();
        }
        protected void btnSeleccionarEspacioModal_Click(object sender, EventArgs e)
        {
            int IDEspacio = Int32.Parse(((LinkButton)sender).CommandArgument);
            espacio espacioSeleccionado = espacios.SingleOrDefault(x => x.idEspacio == IDEspacio);
            Session["espacio"] = espacioSeleccionado;
            txtNombreEspacio.Text = espacioSeleccionado.seccion;
            txtIDEspacio.Text = espacioSeleccionado.idEspacio.ToString();
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);

            //aqui?
            txtNombreEspacioElegido.Text = espacioSeleccionado.seccion;
        }
        /*
        protected void btnVerDisponibilidadEspacio_Click(object sender, EventArgs e)
        {
            //de la fecha de Reserva y el id de espacio, mostrar la disponibilidad
            //procedure a reservaespacio y busca el idEspacio y la fecha-> horarios disponibles en un modal como lista de cuadrados de 1 am, 2 am. 3 am,..

            DateTime fechaReserva = DateTime.ParseExact(dtpFechaReservaEspacio.Value, "yyyy-MM-dd", CultureInfo.InvariantCulture);
            List<int> todasLasHoras = Enumerable.Range(0, 24).ToList();
            List<int> horasDisponibles = daoReservaEspacio.listarHorasDisponibles(Int32.Parse(txtIDEspacio.Text), fechaReserva).ToList();

            // Convertir las horas a formato 12 horas 
            List<string> horasParaMostrar = todasLasHoras.Select(h =>
                horasDisponibles.Contains(h) ?
                new DateTime(1, 1, 1, h, 0, 0).ToString("hh:mm tt") + " DISPONIBLE" :
                new DateTime(1, 1, 1, h, 0, 0).ToString("hh:mm tt") + " NO DISPONIBLE"
            ).ToList();

            rptAvailableHours.DataSource = horasParaMostrar;
            rptAvailableHours.DataBind();

            string script = "window.onload = function() { $('#form-modal-horasDisponibles').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
        }
        */

        protected void btnVerDisponibilidadEspacio_Click(object sender, EventArgs e)
        {
            //de la fecha de Reserva y el id de espacio, mostrar la disponibilidad
            //procedure a reservaespacio y busca el idEspacio y la fecha-> horarios disponibles en un modal como lista de cuadrados de 1 am, 2 am. 3 am,..

            DateTime fechaReserva = DateTime.ParseExact(dtpFechaReservaEspacio.Value, "yyyy-MM-dd", CultureInfo.InvariantCulture);
            List<int> todasLasHoras = Enumerable.Range(0, 24).ToList();
            List<int> horasDisponibles = daoReservaEspacio.listarHorasDisponibles(Int32.Parse(txtIDEspacio.Text), fechaReserva).ToList();

            // Convertir las horas a formato 12 horas 
            List<string> horasParaMostrar = todasLasHoras.Select(h =>
                horasDisponibles.Contains(h) ?
                new DateTime(1, 1, 1, h, 0, 0).ToString("hh:mm tt") + " DISPONIBLE" :
                new DateTime(1, 1, 1, h, 0, 0).ToString("hh:mm tt") + " NO DISPONIBLE"
            ).ToList();

            rptAvailableHours.DataSource = horasParaMostrar;
            rptAvailableHours.DataBind();

            string script = "window.onload = function() { $('#form-modal-horasDisponibles').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
        }


        protected void gvEspacios_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvEspacios.PageIndex = e.NewPageIndex;
            gvEspacios.DataSource = espacios;
            gvEspacios.DataBind();
        }

        protected void lbAgregarReservaEspacio_Click(object sender, EventArgs e)
        {
            int cantError = 0;
            if (dtpFechaReservaEspacio.Value.Length == 0)
            {
                lblErrorEventoReservaEspacio.Text += "Se debe de seleccionar una fecha de reserva espacio<br/>";
                cantError++;
            }
            if (Session["espacio"] == null)
            {
                lblErrorEventoReservaEspacio.Text += "Se debe de seleccionar un espacio<br/>";
                cantError++;
            }
            if (hrIniReservaEspacio.Value.Length == 0)
            {
                lblErrorEventoReservaEspacio.Text += "Debe ingresar una hora de inicio...<br/>";
                cantError++;
            }
            if (hrFinReservaEspacio.Value.Length == 0)
            {
                lblErrorEventoReservaEspacio.Text += "Debe ingresar una hora de fin...<br/>";
                cantError++;
            }
            //verificar horas /*****************************************************************/
            string strHrIni = hrIniReservaEspacio.Value; // Captura el valor de la hora inicial
            string strHrFin = hrFinReservaEspacio.Value;
            TimeSpan hrIniResEspacio;
            TimeSpan hrFinResEspacio;

            if (TimeSpan.TryParse(strHrIni, out hrIniResEspacio) && TimeSpan.TryParse(strHrFin, out hrFinResEspacio))
            {
                // Compara las horas
                if (hrIniResEspacio >= hrFinResEspacio)
                {
                    lblErrorEventoReservaEspacio.Text += "La hora de inicio debe ser menor a la hora de fin<br/>";
                    cantError++;
                }
            }





            if (cantError > 0)
            {
                //si hay errores no se hace nada
            }
            else
            {




                /********************************************************************/
                reservaEspacio reserEsp = new reservaEspacio();
                reserEsp.fechaDeReserva = DateTime.Parse(dtpFechaReservaEspacio.Value);
                reserEsp.fechaDeReservaSpecified = true;
                reserEsp.evento = new evento();
                reserEsp.espacio = new espacio();
                reserEsp.espacio = (espacio)Session["espacio"];
                reserEsp.horaInicio = hrIniReservaEspacio.Value;
                reserEsp.horaFin = hrFinReservaEspacio.Value;


                /*************************************************/
                // Obtén las horas disponibles
                List<int> horasDisponibles = daoReservaEspacio.listarHorasDisponibles(Int32.Parse(txtIDEspacio.Text), reserEsp.fechaDeReserva).ToList();

                // Convierte las horas de inicio y fin a enteros
                int horaInicioInt = DateTime.Parse(hrIniReservaEspacio.Value).Hour;

                int horaFinInt = DateTime.Parse(hrFinReservaEspacio.Value).Hour;
                /*
                // Verifica si ambas horas están dentro del rango de horas disponibles
                if (horasDisponibles.Contains(horaInicioInt) && horasDisponibles.Contains(horaFinInt))
                {
                }
                else
                {
                    //Response.Write("Las horas de inicio y fin deben estar dentro del rango de horas disponibles. Ver disponibilidad");
                    lblErrorEventoReservaEspacio.Text += "Las horas de inicio y fin deben estar dentro del rango de horas disponibles. Ver disponibilidad<br/>";
                    cantError++;
                }
                */
                if (!horasDisponibles.Contains(horaInicioInt))
                {
                    lblErrorEventoReservaEspacio.Text += "Las horas de inicio y fin deben estar dentro del rango de horas disponibles. Ver disponibilidad<br/>";
                    cantError++;
                }
                else
                {
                    // Verifica si la diferencia entre la hora de inicio y fin es mayor a 1
                    if (Math.Abs(horaFinInt - horaInicioInt) > 1)
                    {
                        // Recorre todas las horas en el rango y verifica si todas están en horasDisponibles
                        bool todasHorasDisponibles = true;
                        for (int i = Math.Min(horaInicioInt, horaFinInt) + 1; i < Math.Max(horaInicioInt, horaFinInt); i++)
                        {
                            if (!horasDisponibles.Contains(i))
                            {
                                todasHorasDisponibles = false;
                                break;
                            }
                        }

                        if (!todasHorasDisponibles)
                        {
                            lblErrorEventoReservaEspacio.Text += "Las horas de inicio y fin deben estar dentro del rango de horas disponibles. Ver disponibilidad<br/>";
                            cantError++;
                        }
                    }
                }


                if (cantError > 0)
                {
                    //si hay errores no se hace nada
                }
                else
                {


                    /*************************************************/
                    if (Session["bindingReservaEspacio"] != null)
                        reservasListaEspacio = (BindingList<reservaEspacio>)Session["bindingReservaEspacio"];
                    reservasListaEspacio.Add(reserEsp);
                    Session["bindingReservaEspacio"] = reservasListaEspacio;
                    even.reservaEspacios = reservasListaEspacio.ToArray();



                    gvreservaEspacios.DataSource = even.reservaEspacios;
                    gvreservaEspacios.DataBind();

                    //limpiar datos
                    txtIdEvento.Text = "";
                    txtIDEspacio.Text = "";
                    txtNombreEspacio.Text = "";
                    hrFinReservaEspacio.Value = "";
                    hrIniReservaEspacio.Value = "";
                    dtpFechaReservaEspacio.Value = "";
                }
            }
        }


        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            int cantError = 0;

            if (txtNombreEvento.Text.Length == 0 || txtNombreEvento.Text.Length > 45)
            {
                lblErrorEventoReservaEspacio.Text += "El nombre de evento no es válido (0 a 45 caracteres)<br/>";
                cantError++;
            }
            if (txtDescripcionEvento.Text.Length == 0 || txtDescripcionEvento.Text.Length > 120)
            {
                lblErrorEventoReservaEspacio.Text += "La descripción del evento no es válida (0 a 120 caracteres)<br/>";
                cantError++;
            }
            if (!EsNumero(txtCantidadAsisEvento.Text) || txtCantidadAsisEvento.Text.Length == 0)
            {
                lblErrorEventoReservaEspacio.Text += "El numero de asistentes no es válido<br/>";
                cantError++;
            }
            if (dtpFechaInicioEvento.Value.Length == 0) {
                lblErrorEventoReservaEspacio.Text += "Debe de ingresar una fecha de inicio para el evento<br/>";
                cantError++;
            }
            else
            {
                DateTime fechaInicio = DateTime.Parse(dtpFechaInicioEvento.Value);


                if (fechaInicio <= DateTime.Now)
                {
                    lblErrorEventoReservaEspacio.Text += "La fecha de inicio debe ser mayor a la fecha actual<br/>";
                    cantError++;
                }
            }
            if (Session["bindingReservaEspacio"] == null)
            {
                lblErrorEventoReservaEspacio.Text += "Debe de tener al menos una reserva<br/>";
                cantError++;
            }

            if (cantError > 0)
            {
                //si hay errores no se hace nada
            }
            else
            {

                //datos generales de evento
                even.nombre = txtNombreEvento.Text;
                even.descripcion = txtDescripcionEvento.Text;
                even.cantidadAsistentes = Int32.Parse(txtCantidadAsisEvento.Text);
                even.fechaInicio = DateTime.Parse(dtpFechaInicioEvento.Value);
                even.fechaInicioSpecified = true;
                even.activo = true;
                //Variables de sesion para el admin
                administrador adminCopia = (administrador)Session["administrador"];
                even.administrador = new administrador();
                even.administrador.idPersona = adminCopia.idPersona;

                reservasListaEspacio = (BindingList<reservaEspacio>)Session["bindingReservaEspacio"];
                even.reservaEspacios = reservasListaEspacio.ToArray();

                //
                DateTime ultimaFecha = DateTime.MinValue; // Inicializa con una fecha muy temprana
                bool alMenosUnaFechaIgual = false;
                foreach (reservaEspacio resEspacio in reservasListaEspacio)
                {
                    if (resEspacio.fechaDeReserva > ultimaFecha)
                    {
                        ultimaFecha = resEspacio.fechaDeReserva;
                    }
                    //por lo menos una fecha de reservaEspacio debe ser igual a la fecha de Inicio del Evento
                    if (resEspacio.fechaDeReserva == even.fechaInicio)
                    {
                        alMenosUnaFechaIgual = true;

                    }
                }

                if (alMenosUnaFechaIgual == false)
                {
                    //Response.Write("Ninguna fecha de reserva coincide con la fecha de inicio del evento."); // no lo veo
                    lblErrorEventoReservaEspacio.Text += "Ninguna fecha de reserva coincide con la fecha de inicio del evento<br/>";
                    cantError++;
                }
                else
                {
                    even.fechaFin = ultimaFecha;
                    even.fechaFinSpecified = true;

                    //para el ultimo agregado
                    String accion = Request.QueryString["accion"];
                    if (accion != "modificar")
                    {
                        int resultado = daoEvento.registrarEvento(even);
                    }
                    else
                    {
                        int sale = daoEvento.modificarEvento(even);
                    }
                    Response.Redirect("ListarEventos.aspx");
                }
            }
            
        }
       

        protected void btnEliminarReservaEspacio_Click(object sender, EventArgs e)
        {
            String accion = Request.QueryString["accion"];
            if (accion == "modificar")
            {
                int idReservaEspacioSel = Int32.Parse(((LinkButton)sender).CommandArgument);
                reservasListaEspacio = (BindingList<reservaEspacio>)Session["bindingReservaEspacio"];
                reservaEspacio reservaEsp = reservasListaEspacio.SingleOrDefault(x => x.idReservaEspacio == idReservaEspacioSel);
                reservasListaEspacio.Remove(reservaEsp);
                Session["bindingReservaEspacio"] = reservasListaEspacio;
                even.reservaEspacios = reservasListaEspacio.ToArray();
                
                //int sale = daoEvento.modificarEvento(even);

                gvreservaEspacios.DataSource = even.reservaEspacios;
                gvreservaEspacios.DataBind();
            }
        }



        protected void gvreservaEspacios_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            /*
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = ((DateTime)(DataBinder.Eval(e.Row.DataItem, "fechaDeReserva"))).ToString("dd-MM-yyyy");
                e.Row.Cells[4].Text = ((DataBinder.Eval(e.Row.DataItem, "fechaDeReserva"))).ToString("dd-MM-yyyy");
            }
            */
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                reservaEspacio resEspacio = (reservaEspacio)e.Row.DataItem;
                e.Row.Cells[1].Text = resEspacio.fechaDeReserva.ToString("dd-MM-yyyy");
                // Asumiendo que "nombre" es la propiedad que contiene el nombre del espacio
                espacio espacioVer = new espacio();
                espacioVer = espacios.SingleOrDefault(x => x.idEspacio == resEspacio.espacio.idEspacio);
                resEspacio.espacio = espacioVer;
                e.Row.Cells[4].Text = resEspacio.espacio.seccion;
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

    }
}