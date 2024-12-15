using LothelAplicacionWeb.LothelSoftWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Xml.Linq;


namespace LothelAplicacionWeb
{
    public partial class ReporteHabitaciones : System.Web.UI.Page
    {
        private ReservasWSClient daoHabitacion;
        private RRHHWSClient daoRRHH;
        private BindingList<habitacion> habitaciones;
        private BindingList<familiar> familiares;
        private BindingList<matrimonial> matrimoniales;
        private BindingList<simple> simples;
        
        protected void Page_Load(object sender, EventArgs e)
        {
            daoHabitacion = new ReservasWSClient();
            DateTime fechaDesde;
            DateTime.TryParse(detFechaDesdeDescarga.Value, out fechaDesde);
            DateTime fechaHasta;
            DateTime.TryParse(detFechaHastaDescarga.Value, out fechaHasta);



            if (fechaDesde > fechaHasta)
            {
                lblMensajeError.Text = "La fecha desde debe ser mayor que la fecha hasta";
            }
            else
            {
                var listaHuespedes = daoHabitacion.ListarHabitacionesInicio();
                lblMensajeError.Text = "";
                if (listaHuespedes == null)
                {
                    gvHabitacionesReporte.DataSource = null;
                    gvHabitacionesReporte.DataBind();
                }
                else
                {
                    habitaciones = new BindingList<habitacion>(listaHuespedes.ToList());
                    gvHabitacionesReporte.DataSource = habitaciones;
                    gvHabitacionesReporte.DataBind();
                }
            }
        }

        protected void Page_init(object sender, EventArgs e)
        {

            daoHabitacion = new ReservasWSClient();
            var listaHuespedes = daoHabitacion.ListarHabitacionesInicio();
            lblMensajeError.Text = "";
            if (listaHuespedes == null)
            {
                gvHabitacionesReporte.DataSource = null;
                gvHabitacionesReporte.DataBind();
            }
            else
            {
                habitaciones = new BindingList<habitacion>(listaHuespedes.ToList());
                gvHabitacionesReporte.DataSource = habitaciones;
                gvHabitacionesReporte.DataBind();
            }

        }

        protected void btnBuscarFecha_Click(object sender, EventArgs e)
        {
            string opcionSeleccionada = TipoDeHabitacionReporte.Value;
            DateTime fechaDesde;
            DateTime.TryParse(detFechaDesdeDescarga.Value, out fechaDesde);
            DateTime fechaHasta;
            DateTime.TryParse(detFechaHastaDescarga.Value, out fechaHasta);
            // Aquí puedes usar la variable opcionSeleccionada como necesites
            if (fechaDesde > fechaHasta)
            {
                lblMensajeError.Text = "La fecha desde debe ser mayor que la fecha hasta";
            }
            else
            {
                if (opcionSeleccionada == "all")
                {
                    //var listaHuespedes = daoHabitacion.();
                }
                else if (opcionSeleccionada == "familiar")
                {
                    familiares = new BindingList<familiar>(daoHabitacion.ListarFamiliarPorFechaYTipo(fechaDesde, fechaHasta));
                    gvHabitacionesReporte.DataSource = familiares;
                    gvHabitacionesReporte.DataBind();

                }
                else if (opcionSeleccionada == "matrimonial")
                {
                    matrimoniales = new BindingList<matrimonial>(daoHabitacion.ListarMatrimonialPorFechaYTipo(fechaDesde, fechaHasta));
                    gvHabitacionesReporte.DataSource = matrimoniales;
                    gvHabitacionesReporte.DataBind();
                }
                else if (opcionSeleccionada == "simple")
                {
                    simples = new BindingList<simple>(daoHabitacion.ListarSimplePorFechaYTipo(fechaDesde, fechaHasta));
                    gvHabitacionesReporte.DataSource = simples;
                    gvHabitacionesReporte.DataBind();
                }

            }

        }



        protected void btnPdf_Click(object sender, EventArgs e)
        {
            daoRRHH= new RRHHWSClient();
            DateTime fechaDesde;
            DateTime.TryParse(detFechaDesdeDescarga.Value, out fechaDesde);
            DateTime fechaHasta;
            DateTime.TryParse(detFechaHastaDescarga.Value, out fechaHasta);

            if (fechaDesde < fechaHasta)
            {
                byte [] pdf = daoRRHH.GenerarReporteHabitaciones(fechaDesde, fechaHasta);
            Response.Clear();
            Response.ContentType="application/pdf";
            Response.AddHeader("Content-Disposition", "inline; filename=Reporte.pdf");
            Response.BinaryWrite(pdf);
            Response.End();
            }
            else
            {
                lblMensajeError.Text = "La fecha desde debe ser mayor que la fecha hasta";
            }
        }



    }
}