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
    public partial class pPersonalDeLavanderia : System.Web.UI.Page
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
            daoEmpresa = new EventosWSClient();
            daoPedido = new RRHHWSClient();



            //if (daoPedido.ListarServiciosLavanderoPorEntregar().ToList() != null)


            persona per = (persona)Session["personal_lavanderia"];
            lblNombreBarraLateral.Text = per.nombre + " " + per.apellidoPaterno;

            GridTareasLavandero.DataSource = pedidos;
            GridTareasLavandero.DataBind();



            string view = Request.QueryString["view"];
            if (view == "entregar")
            {
                var serviciosPorEntregar = daoPedido.ListarServiciosLavanderoPorEntregar();
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
                var serviciosLavandero = daoPedido.ListarServiciosLavandero();
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
        protected void Page_init()
        {
            TareasPendientes.Visible = true;
            TareasPorEntregar.Visible = false;
        }


        private void BindGridView()
        {
            pedidos = new BindingList<pedido>(daoPedido.ListarServiciosLavandero());
            pedidosPorEntregar = new BindingList<pedido>(daoPedido.ListarServiciosLavanderoPorEntregar().ToList());

            GridTareasLavandero.DataSource = pedidos;
            GridTareasLavandero.DataBind();

            GridTareasPorEntregarLavandero.DataSource = pedidosPorEntregar;
            GridTareasPorEntregarLavandero.DataBind();

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
            lblErrorCadena.Text = mensaje;
            GridTareasLavandero.DataSource = null;
            GridTareasPorEntregarLavandero.DataSource = null;
            GridTareasLavandero.DataBind();
            GridTareasPorEntregarLavandero.DataBind();

            TareasPendientes.Visible = false;
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

            daoPedido.modificarPedidoPendiente(pedido,"EN_PROCESO");
            Response.Redirect("pPersonalDeLavanderia.aspx");

        }



        protected void btnComentarIncidencia_Click(object sender, EventArgs e)
        {
            int idPed = Int32.Parse(((LinkButton)sender).CommandArgument);
            pedido ped = pedidosPorEntregar.SingleOrDefault(x => x.idPedido == idPed);
            Session["pedido"] = ped;
            if (ped.incidenciaDeHabitacion != null)
            {
                txtDescription.Text = ped.incidenciaDeHabitacion;
            }

            string script = "window.onload= function () { IncidenciaModal() };";
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }

        protected void btnGuardarModalIncidencia_Click(object sender, EventArgs e)
        {
            pedido ped = (pedido)Session["pedido"];

            string cadena = txtDescription.Text;
            if (cadena.Length > 100)
            {
                lblErrorCadena.Text = "La incidencia debe tener menos de 100 caracteres";
            }
            else
            {
                daoPedido.Agregardescripcion(ped, cadena);
                Response.Redirect("pPersonalDeLavanderia.aspx?view=entregar");
            }

        }

        protected void btnTerminar_Click(object sender, EventArgs e)
        {
            int idPed = Int32.Parse(((LinkButton)sender).CommandArgument);
            pedido ped = pedidosPorEntregar.SingleOrDefault(x => x.idPedido == idPed);

            daoPedido.modificarPedidoPendiente(ped,"COMPLETADO");
            Response.Redirect("pPersonalDeLavanderia.aspx");
        }

       

        protected void btnPendientes_Click(object sender, EventArgs e)
        {
            Response.Redirect("pPersonalDeLavanderia.aspx?view=pendientes");
        }

        protected void btnPorAtender_Click(object sender, EventArgs e)
        {
            Response.Redirect("pPersonalDeLavanderia.aspx?view=entregar");
        }

        protected void GridTareasLavandero_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            GridTareasLavandero.PageIndex = e.NewPageIndex;
            GridTareasLavandero.DataBind();
        }

        protected void GridTareasPorEntregarLavandero_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            
                GridTareasPorEntregarLavandero.PageIndex = e.NewPageIndex;
            GridTareasPorEntregarLavandero.DataBind();
        }

        protected void btnVerDetalleLavandero_Click(object sender, EventArgs e)
        {
            int idPedido = Int32.Parse(((LinkButton)sender).CommandArgument);
            pedidos = new BindingList<pedido>(daoPedido.ListarServiciosLavandero().ToList());
            // Obtén los datos de la reserva y del huésped usando el idReserva
            pedido ped = pedidos.SingleOrDefault(x => x.idPedido == idPedido); // Implementa este método


            lblDescripcionLavanderia.Text = ped.anotacionesServicio;
            lblNombreHUesped.Text = ped.nombreHuesped;
            lblNumeroHabitacion.Text = ped.numHabitacion.ToString();
            lblFechaRealizacion.Text = ped.fechaSolicitud.ToString();

            string script = "window.onload= function () { Detalle() };";
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }

    }

}