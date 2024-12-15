using LothelAplicacionWeb.LothelSoftWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LothelAplicacionWeb
{
    public partial class ReporteHuespedes : System.Web.UI.Page
    {
        private RRHHWSClient daoHuesped;
        private BindingList<huesped> huespedes;
        private RRHHWSClient daoRRHH;
        protected void Page_Load(object sender, EventArgs e)
        {
            daoHuesped = new RRHHWSClient();
            DateTime fechaDesde;
            DateTime.TryParse(detFechaDesdeDescarga.Value, out fechaDesde);
            DateTime fechaHasta;
            DateTime.TryParse(detFechaHastaDescarga.Value, out fechaHasta);
            if (fechaDesde > fechaHasta) {
                lblMensajeError.Text = "La fecha desde debe ser mayor que la fecha hasta";
            }
            else
            {
                var listaHuespedes = daoHuesped.listarHuespedesXPeriodo(fechaDesde, fechaHasta);
                lblMensajeError.Text = "";
                if (listaHuespedes == null)
                {
                    gvHuespedesReporte.DataSource = null; 
                    gvHuespedesReporte.DataBind(); 
                }
                else
                {
                    huespedes = new BindingList<huesped>(listaHuespedes.ToList());
                    gvHuespedesReporte.DataSource = huespedes;
                    gvHuespedesReporte.DataBind();
                }
            }
        }

        protected void gvReporteHuespedes_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvHuespedesReporte.PageIndex = e.NewPageIndex;
            gvHuespedesReporte.DataBind();
        }
        
        protected void gvHuespedesReporte_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "idPersona").ToString();
                e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "nombre").ToString() + " " + DataBinder.Eval(e.Row.DataItem, "apellidoPaterno").ToString() + " " + DataBinder.Eval(e.Row.DataItem, "apellidoMaterno").ToString();
                e.Row.Cells[2].Text = DataBinder.Eval(e.Row.DataItem, "dni").ToString();
                e.Row.Cells[3].Text = Convert.ToDateTime(DataBinder.Eval(e.Row.DataItem, "fechaRegistro")).ToString("dd/MM/yyyy");
                e.Row.Cells[4].Text = DataBinder.Eval(e.Row.DataItem, "celular").ToString();
                e.Row.Cells[5].Text = DataBinder.Eval(e.Row.DataItem, "correo").ToString();
                e.Row.Cells[6].Text = DataBinder.Eval(e.Row.DataItem, "esVIP").ToString();

            }
        }

        protected void btnDescargaHuesped_Click(object sender, EventArgs e)
        {
            daoRRHH = new RRHHWSClient();
            DateTime fechaDesde;
            DateTime.TryParse(detFechaDesdeDescarga.Value, out fechaDesde);
            DateTime fechaHasta;
            DateTime.TryParse(detFechaHastaDescarga.Value, out fechaHasta);

            if (fechaDesde < fechaHasta)
            {
                byte[] pdf = daoRRHH.GenerarReporteHuespedes(fechaDesde, fechaHasta);
                Response.Clear();
                Response.ContentType = "application/pdf";
                Response.AddHeader("Content-Disposition", "inline; filename=Reporte.pdf");
                Response.BinaryWrite(pdf);
                Response.End();
            }
            else
            {
                lblMensajeError.Text = "La fecha desde debe ser mayor que la fecha hasta";
            }
        }
           
        protected void btnBuscarFecha_Click(object sender, EventArgs e)
        {
            gvHuespedesReporte.DataSource = huespedes;
            gvHuespedesReporte.DataBind();
        }
            
    }
}