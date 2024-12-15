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
    public partial class ListarEventos : System.Web.UI.Page
    {
        private EventosWSClient daoEvento;

        private BindingList<evento> eventos;

        private evento even;
        private estadoEvento estEvento;


        protected void Page_Load(object sender, EventArgs e)
        {
            esconderBotonesNav();
            daoEvento = new EventosWSClient();
            eventos = new BindingList<evento>(daoEvento.listarEventos().ToList());
            Session["eventos"] = daoEvento.listarEventos().ToList();
            gvEventos.DataSource = eventos;
            gvEventos.DataBind();

            //Validaciones para el ingreso de datos a la BD (nombre, descripcion , cantidad, fechaInicio)

            lblMensajeErrorDescripcionEvento.Text = "";
            lblMensajeErrorFechaRealizacionEvento.Text = "";
            LblEventoNoEncontrado.Text = "";

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

        //para el registro los textBoxes estan vacios, pero cuando se llenen se debe validar los dats
        protected void lbRegistrarEvento_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarEvento.aspx");
            /*
            Session["modificar"] = 0;
            string script = "window.onload = function() { $('#form-modal-evento').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
            */
        }

        protected void btnConfirmarRegistroEvento(object sender, EventArgs e)
        {
            int cantErrores = 0;
            //Verificar los datos de los textBoxes

            //Validaciones para el ingreso de datos a la BD (RUC,correo)

            lblMensajeErrorDescripcionEvento.Text = "";
            lblMensajeErrorFechaRealizacionEvento.Text = "";

            if (txtDescripcionEvento.Text.Length > 120 || txtDescripcionEvento.Text.Length < 10)
            {
                lblMensajeErrorDescripcionEvento.Text = "La descripcion debe contener maximo 120 caracteres y como minimo 10 caracteres";
                cantErrores++;
            }
            DateTime fechaInicioPorValidar = DateTime.Parse(dtpFechaInicio.Value);
            if (!EsFechaValida(fechaInicioPorValidar))
            {
                lblMensajeErrorFechaRealizacionEvento.Text = "La fecha de realizacion no es valida\n";
                cantErrores++;
            }

            if (cantErrores > 0)
            {
                //si hay errores el flujo se corta (se queda aqui)
            }
            else
            {
                evento even = new evento();
                even.nombre = txtNombreEvento.Text;
                even.descripcion = txtDescripcionEvento.Text;
                even.cantidadAsistentes = Int32.Parse(txtCantAsistentesEvento.Text);
                //if ((int)Session["modificar"] == 0)
                //{
                even.fechaInicio = DateTime.Parse(dtpFechaInicio.Value);
                //}
                even.fechaInicioSpecified = true;

                even.estado = estadoEvento.PROGRAMADO; //cambiar a cancelado 
                even.estadoSpecified = true;

                even.activo = true;

                //Variables de sesion para el admin
                administrador adminCopia = (administrador)Session["administrador"];
                even.administrador = new administrador();
                even.administrador.idPersona = adminCopia.idPersona;




                if ((int)Session["modificar"] == 0)
                    daoEvento.registrarEvento(even);
                else if ((int)Session["modificar"] == 1)
                {
                    even.idEvento = Int32.Parse(txtIdEvento.Text);
                    daoEvento.modificarEvento(even);
                }
                Session["modificar"] = null;
                ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
            }
        }

        private static bool EsFechaValida(DateTime fechaInicioEvento)
        {
            DateTime fechaActual = DateTime.Now.Date;

            if (fechaInicioEvento.Date.CompareTo(fechaActual) > 0)
                return true;
            else
                return false;
        }

        protected void btnEliminarEvento(object sender, EventArgs e)
        {
            int idEvento = Int32.Parse(((LinkButton)sender).CommandArgument);
            daoEvento.eliminarEvento(idEvento);
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void btnEditarEvento(object sender, EventArgs e)
        {
            /*
            lblTituloModalEvento.Text = "Modificar evento";
            int idEventoSelec = Int32.Parse(((LinkButton)sender).CommandArgument);
            evento eventoSelec = eventos.SingleOrDefault(x => x.idEvento == idEventoSelec);


            txtIdEvento.Text = eventoSelec.idEvento.ToString();
            txtNombreEvento.Text = eventoSelec.nombre;
            txtDescripcionEvento.Text = eventoSelec.descripcion;
            txtCantAsistentesEvento.Text = eventoSelec.cantidadAsistentes.ToString();
            dtpFechaInicio.Value = eventoSelec.fechaInicio.ToString("dd/MM/yyyy"); 
            */

            int idEventoSelec = Int32.Parse(((LinkButton)sender).CommandArgument);
            evento eventoSelec = eventos.SingleOrDefault(x => x.idEvento == idEventoSelec);

            Session["reservaEspacioSeleccionado"] = null;
            Session["eventoSeleccionado"] = eventoSelec;
            Session["reservasEspacioDeEventoSel"] = eventoSelec.reservaEspacios;

            Response.Redirect("RegistrarEvento.aspx?accion=modificar");
        }


        protected void lbBuscarEventoPorNombre_Click(object sender, EventArgs e)
        {
            
            Session["eventos"] = daoEvento.listarEventosPorNombre(txtBusquedaEvento.Text);
            
            if (Session["eventos"] == null)
            {
                LblEventoNoEncontrado.Text = "No se ha encontrado el evento con el nombre especificado";
                eventos = new BindingList<evento>(daoEvento.listarEventosPorNombre("").ToList());
                gvEventos.DataSource = eventos;
                gvEventos.DataBind();
            }
            else
            {
                eventos = new BindingList<evento>(daoEvento.listarEventosPorNombre(txtBusquedaEvento.Text).ToList());
                gvEventos.DataSource = eventos;
                gvEventos.DataBind();
            }
            
        }
        protected void gvEventos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvEventos.PageIndex = e.NewPageIndex;
            gvEventos.DataBind();
        }

        protected void gvEventos_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[4].Text = ((DateTime)(DataBinder.Eval(e.Row.DataItem, "fechaInicio"))).ToString("yyyy-MM-dd");
                e.Row.Cells[5].Text = ((DateTime)(DataBinder.Eval(e.Row.DataItem, "fechaFin"))).ToString("yyyy-MM-dd");
            }
            

        }
    }
}