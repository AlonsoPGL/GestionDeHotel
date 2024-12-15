using LothelAplicacionWeb.LothelSoftWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.EnterpriseServices;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LothelAplicacionWeb
{
    public partial class pPersonalDeMasaje : System.Web.UI.Page
    {
        private EventosWSClient daoEmpresa;
        private RRHHWSClient daoServicioLavanderia;
        private RRHHWSClient daoPedido;
        private BindingList<empresaProveedora> empresas;
        private BindingList<pedido> pedidos;
        private BindingList<pedido> pedidosPorEntregar;
        private empresaProveedora empresa;

        protected void Page_Load(object sender, EventArgs e)
        {
            esconderBotonesNav();
            esconderBarraLateral(); //Hacer en el init
            //daoEmpresa = new EventosWSClient();
            daoPedido = new RRHHWSClient();
            //empresas = new BindingList<empresaProveedora>(daoEmpresa.listarEmpresasProveedoras().ToList());
            //solo los pedidos con item servicio lavanderia


            //if (daoPedido.ListarServiciosLavanderoPorEntregar().ToList() != null)

            persona per = (persona)Session["personal_masajes"];
            lblNombreBarraLateral.Text = per.nombre + " " + per.apellidoPaterno;


            GridTareasLavandero.DataSource = pedidos;
            GridTareasLavandero.DataBind();



            string view = Request.QueryString["view"];
            if (view == "entregar")
            {
                var serviciosPorEntregar = daoPedido.ListarServiciosMasajistaPorEntregar();
                if (serviciosPorEntregar != null)
                {
                    pedidosPorEntregar = new BindingList<pedido>(serviciosPorEntregar.ToList());
                    GridTareasPorEntregarLavandero.DataSource = pedidosPorEntregar;
                    GridTareasPorEntregarLavandero.DataBind();
                    TareasPendientes.Visible = false;
                    TareasPorEntregar.Visible = true;
                }
                else
                {
                    mostrarMensajeError2("No se encontraron pedidos por entregar.");
                }
            }
            else
            {
                var serviciosLavandero = daoPedido.ListarServiciosMasajista();
                if (serviciosLavandero != null)
                {
                    pedidos = new BindingList<pedido>(serviciosLavandero.ToList());
                    GridTareasLavandero.DataSource = pedidos;
                    GridTareasLavandero.DataBind();
                    TareasPendientes.Visible = true;
                    TareasPorEntregar.Visible = false;
                }
                else
                {
                    mostrarMensajeError("No se encontraron tareas pendientes.");
                }
            }

        }
        private void mostrarMensajeError(string mensaje)
        {
            ErrorPendientes.Text = mensaje;
            GridTareasLavandero.DataSource = null;
            GridTareasPorEntregarLavandero.DataSource = null;
            GridTareasLavandero.DataBind();
            GridTareasPorEntregarLavandero.DataBind();

            TareasPorEntregar.Visible = false;
        }

        private void mostrarMensajeError2(string mensaje)
        {
            ErrorPorEntregar.Text = mensaje;
            GridTareasLavandero.DataSource = null;
            GridTareasPorEntregarLavandero.DataSource = null;
            GridTareasLavandero.DataBind();
            GridTareasPorEntregarLavandero.DataBind();

            TareasPendientes.Visible = false;
        }
        protected void Page_init()
        {
            TareasPendientes.Visible = true;
            TareasPorEntregar.Visible = false;
        }


        private void BindGridView()
        {
            pedidos = new BindingList<pedido>(daoPedido.ListarServiciosMasajista());
            pedidosPorEntregar = new BindingList<pedido>(daoPedido.ListarServiciosMasajistaPorEntregar().ToList());

            GridTareasLavandero.DataSource = pedidos;
            GridTareasLavandero.DataBind();

            GridTareasPorEntregarLavandero.DataSource = pedidosPorEntregar;
            GridTareasPorEntregarLavandero.DataBind();

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



        protected void esconderBarraLateral()
        {
            var menuLateral = this.Master.FindControl("menuLateral") as HtmlGenericControl;
            menuLateral.Visible = false;
        }

        protected void btnConfirmarPedidoLavandero_Click(object sender, EventArgs e)
        {
            int idPed = Int32.Parse(((LinkButton)sender).CommandArgument);
            pedido pedido = pedidos.SingleOrDefault(x => x.idPedido == idPed);

            daoPedido.modificarPedidoPendienteMasajista(pedido, "EN_PROCESO");
            Response.Redirect("pPersonalDeMasaje.aspx");

        }



        protected void btnComentarMasajista_Click(object sender, EventArgs e)
        {
            int idPed = Int32.Parse(((LinkButton)sender).CommandArgument);
            pedido ped = pedidosPorEntregar.SingleOrDefault(x => x.idPedido == idPed);
            Session["pedido"] = ped;
            if (ped.incidenciaDeHabitacion != null)
            {
                txtDescription.Text = ped.incidenciaDeHabitacion;
            }

            string script = "window.onload= function () { ModalMasaje() };";
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
            
        }

        protected void btnGuardarModalIncidencia_Click(object sender, EventArgs e)
        {
            pedido ped = (pedido)Session["pedidoM"];

            string cadena = txtDescription.Text;
            if (cadena.Length > 100)
            {
                lblErrorCadena.Text = "La incidencia debe tener menos de 100 caracteres";

            }
            else
            {
                daoPedido.AgregardescripcionMasaje(ped, cadena);
                Response.Redirect("pPersonalDeMasaje.aspx?view=pendientes");
            }

        }

        protected void btnTerminar_Click(object sender, EventArgs e)
        {
            int idPed = Int32.Parse(((LinkButton)sender).CommandArgument);
            pedido ped = pedidosPorEntregar.SingleOrDefault(x => x.idPedido == idPed);

            daoPedido.modificarPedidoPendienteMasajista(ped, "COMPLETADO");
            Response.Redirect("pPersonalDeMasaje.aspx?view=pendientes");
        }



        protected void btnPendientes_Click(object sender, EventArgs e)
        {
            Response.Redirect("pPersonalDeMasaje.aspx?view=pendientes");
        }

        protected void btnPorAtender_Click(object sender, EventArgs e)
        {
            Response.Redirect("pPersonalDeMasaje.aspx?view=entregar");
        }

        protected void GridTareasPorEntregarLavandero_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            GridTareasPorEntregarLavandero.PageIndex = e.NewPageIndex;
            GridTareasPorEntregarLavandero.DataBind();
        }

        protected void GridTareasLavandero_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            
            GridTareasLavandero.PageIndex = e.NewPageIndex;
            GridTareasLavandero.DataBind();
        }

        protected void btnVerDetalleMasajista_Click(object sender, EventArgs e)
        {
            int idPedido = Int32.Parse(((LinkButton)sender).CommandArgument);
            pedidos = new BindingList<pedido>(daoPedido.ListarServiciosMasajista().ToList());
            // Obtén los datos de la reserva y del huésped usando el idReserva
            pedido ped = pedidos.SingleOrDefault(x => x.idPedido == idPedido); // Implementa este método


            lblDescripcionLavanderia.Text = ped.horaFinServicio.ToString("dd/MM/yyyy");
            lblNombreHUesped.Text = ped.nombreHuesped;
            lblNumeroHabitacion.Text = ped.numHabitacion.ToString();
            lblFechaRealizacion.Text = ped.fechaSolicitud.ToString("dd/MM/yyyy");

            string script = "window.onload= function () { DetalleMasaje() };";
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }

    }

}