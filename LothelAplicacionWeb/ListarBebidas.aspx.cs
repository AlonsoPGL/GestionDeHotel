using LothelAplicacionWeb.LothelSoftWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Security.Cryptography;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LothelAplicacionWeb
{
    public partial class ListarBebidas : System.Web.UI.Page
    {
        private VentasWSClient daoBebida;

        private BindingList<bebida> bebidas;

        private bebida bebi;
        private categoriaAlimento cat;

        //Para dll
        private EventosWSClient daoEmpresa;
        private BindingList<empresaProveedora> empresas;

        protected void Page_Init(object sender, EventArgs e)
        {
            esconderBotonesNav();
            if (Session["Recepcionista"] != null)
            {
                esconderBarraLateral();
            }

            //Cargando ddl de Empresas proveedoras, de esta forma se hará la carga de datos una sola vez, y no cada que se inicialice la página

            if (Session["empresas"] == null)
            {
                daoEmpresa = new EventosWSClient();
                empresas = new BindingList<empresaProveedora>(daoEmpresa.listarEmpresasProveedoras().ToList());
                Session["empresas"] = empresas;
            }
            else
            {
                ddlEmpresasProv.DataTextField = "razonSocial";
                ddlEmpresasProv.DataValueField = "idEmpresa";
                ddlEmpresasProv.DataSource = (BindingList<empresaProveedora>)Session["empresas"];
                ddlEmpresasProv.DataBind();
                ddlEmpresasProv.Items.Insert(0, new ListItem("-- Selecciona una empresa --", "NOSELECT"));
                ddlEmpresasProv.SelectedValue = "NOSELECT";
            }
            
        }

        protected void esconderBarraLateral()
        {
            var menuLateral = this.Master.FindControl("menuLateral") as HtmlGenericControl;
            menuLateral.Visible = false;
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            //limpiando mensaje de error de bebidas
            lblMensajeErrorAlimentos.Text = "";

            daoBebida = new VentasWSClient();

            //busqueda
            LblProductoNoEncontrado.Text = "";
            bebidas = new BindingList<bebida>(daoBebida.listarBebidasPorNombre(" ").ToList());
            gvBebidas.DataSource = bebidas;
            gvBebidas.DataBind();

        }

        //Para subir imagenes (inicio)
        protected void btnSubirImagen_OnClick(object sender, EventArgs e)
        {
            lblErrorSubidaImagen.Text = "";
            if (!esImagenValida(txtUrlImagen.Text) || txtUrlImagen.Text.Length == 0)
            {
                imgProducto.ImageUrl = "";
                divImage.Visible = false;
                btnOjo.Visible = false;
                lblErrorSubidaImagen.Text = "La URL ingresada no es válida <br />";
            }
            else
            {
                imgProducto.ImageUrl = txtUrlImagen.Text;
                btnOjo.Visible = true;
                divImage.Visible = true;
            }
        }
        protected static bool esImagenValida(string url)
        {
            string pattern = @"^(http(s?):)([/|.|\w|\s|-])*\.(?:jpg|jpeg|gif|png|webp|bmp|svg)$";
            return Regex.IsMatch(url, pattern, RegexOptions.IgnoreCase);
        }
        protected void btnMostrarImagen_OnClick(object sender, EventArgs e)
        {
            divImage.Visible = true;
            btnOjo.Visible = true;
            btnOjoCerrado.Visible = false;
        }
        protected void btnOcultarImagen_OnClick(object sender, EventArgs e)
        {
            divImage.Visible = false;
            btnOjo.Visible = false;
            btnOjoCerrado.Visible = true;
        }
        //Para subir imagenes (fin)


        //Para la barra de busqueda
        protected void lbBuscarProductoPorNombre_Click(object sender, EventArgs e)
        {
            // Inicializar daoProducto aquí para asegurarse de que esté disponible
            //VentasWSClient daoProducto = new VentasWSClient();
            Session["vefBuscar"] = daoBebida.listarBebidasPorNombre(txtBusquedaProducto.Text);

            if (Session["vefBuscar"] == null)
            {
                LblProductoNoEncontrado.Text = "No se ha encontrado un producto con el nombre especificado";
            }
            else
            {
                bebidas = new BindingList<bebida>(daoBebida.listarBebidasPorNombre(txtBusquedaProducto.Text).ToList());
                gvBebidas.DataSource = bebidas;
                gvBebidas.DataBind();
            }
        }

        //para el registro los textBoxes estan vacios, pero cuando se llenen se debe validar los dats
        protected void lbRegistrarBebida_Click(object sender, EventArgs e)
        {

            lblTituloModalAlimento.Text = "Registrar bebida";

            txtIdAlimento.Text = "";
            txtNombreAlimento.Text = "";
            txtDescripcionAlimento.Text = "";
            txtStockAlimento.Text = "";
            txtPrecioAlimento.Text = "";
            ddlCategoria.SelectedValue = "";

            txtUrlImagen.Text = "";
            imgProducto.ImageUrl = "";
            btnOjo.Visible = false;
            divImage.Visible = false;
            ddlEmpresasProv.SelectedValue = "NOSELECT";
            ddlCategoria.SelectedValue = "NOSELECT";

            Session["modificar"] = 0;
            string script = "window.onload = function() { $('#form-modal-alimento').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
        }

        protected void btnConfirmarRegistroBebida(object sender, EventArgs e)
        {
            lblMensajeErrorAlimentos.Text = "";
            int cantErrores = 0;

            if (!esImagenValida(txtUrlImagen.Text))
            {
                cantErrores++;
                lblMensajeErrorAlimentos.Text += "Debe subir una imagen valida<br />";
            }

            if (txtNombreAlimento.Text.Length == 0 || txtNombreAlimento.Text.Length > 45)
            {
                cantErrores++;
                lblMensajeErrorAlimentos.Text += "El nombre del producto no es válido (Debe tener de 1 a 45 caracteres)<br />";
            }

            if (txtDescripcionAlimento.Text.Length == 0 || txtDescripcionAlimento.Text.Length > 45)
            {
                cantErrores++;
                lblMensajeErrorAlimentos.Text += "La descripcion no es válida (Debe tener de 1 a 45 caracteres)<br />";
            }

            if (txtPrecioAlimento.Text.Length == 0 || !IsNumberEnteroODouble(txtPrecioAlimento.Text) || !IsNumberWithSingleDot(txtPrecioAlimento.Text))
            {
                cantErrores++;
                lblMensajeErrorAlimentos.Text += "El precio ingresado no es válido<br />";
            }

            if (txtStockAlimento.Text.Length == 0 || !EsNumero(txtStockAlimento.Text))
            {
                cantErrores++;
                lblMensajeErrorAlimentos.Text += "El stock ingresado no es válido<br />";
            }
            /*
            if (!cumpleCategorias(txtCategoriaAlimento.Text))
            {
                cantErrores++;
                lblMensajeErrorAlimentos.Text += "La categoría ingresada no es válida<br />";
            }
            */

            if (ddlCategoria.SelectedValue.Equals("NOSELECT"))
            {
                cantErrores++;
                lblMensajeErrorAlimentos.Text += "Debe seleccionar una categoría<br />";
            }

            if (ddlEmpresasProv.SelectedValue.Equals("NOSELECT"))
            {
                cantErrores++;
                lblMensajeErrorAlimentos.Text += "Debe seleccionar una empresa<br />";
            }

            if (cantErrores > 0)
            {
                //Si no cumple el flujo termina aqui
            }
            else //Si la cantidad de errores es cero prosigue a insertar o modificar segun sea el caso
            {

                bebida bebi = new bebida();
                bebi.nombre = txtNombreAlimento.Text;
                bebi.descripcion = txtDescripcionAlimento.Text;
                bebi.precio = Double.Parse(txtPrecioAlimento.Text);
                bebi.stock = Int32.Parse(txtStockAlimento.Text);
                bebi.urlImagen = imgProducto.ImageUrl;

                //diferencia (estaHelada)
                bebi.estaHelada = true;

                if (ddlCategoria.SelectedValue.Equals("GASEOSAS"))
                    bebi.categoria = categoriaBebida.GASEOSAS;
                if (ddlCategoria.SelectedValue.Equals("INFUSIONES"))
                    bebi.categoria = categoriaBebida.INFUSIONES;
                if (ddlCategoria.SelectedValue.Equals("LICORES"))
                    bebi.categoria = categoriaBebida.LICORES;
                bebi.categoriaSpecified = true;

                bebi.disponibilidad = true;

                bebi.empresa = new empresaProveedora();
                bebi.empresa.idEmpresa = Int32.Parse(ddlEmpresasProv.SelectedValue);


                if ((int)Session["modificar"] == 0)
                    daoBebida.registrarBebida(bebi);
                else if ((int)Session["modificar"] == 1)
                {
                    bebi.idIteam = Int32.Parse(txtIdAlimento.Text);
                    daoBebida.modificarBebida(bebi);
                }
                Session["modificar"] = null;
                ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
            }
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


        protected void btnEliminarBebida(object sender, EventArgs e)
        {
            int idBebida = Int32.Parse(((LinkButton)sender).CommandArgument);
            daoBebida.eliminarBebida(idBebida);
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void btnEditarBebida(object sender, EventArgs e)
        {

            lblTituloModalAlimento.Text = "Modificar bebida";
            int idBebidaSelec = Int32.Parse(((LinkButton)sender).CommandArgument);
            bebida bebidaSelec = bebidas.SingleOrDefault(x => x.idIteam == idBebidaSelec);



            txtIdAlimento.Text = bebidaSelec.idIteam.ToString();
            txtNombreAlimento.Text = bebidaSelec.nombre;
            txtDescripcionAlimento.Text = bebidaSelec.descripcion;
            txtStockAlimento.Text = bebidaSelec.stock.ToString();
            txtPrecioAlimento.Text = bebidaSelec.precio.ToString();
            ddlCategoria.SelectedValue= bebidaSelec.categoria.ToString();
            
            txtUrlImagen.Text = bebidaSelec.urlImagen;
            imgProducto.ImageUrl = bebidaSelec.urlImagen;
            btnSubirImagen_OnClick(sender, e);

            ddlEmpresasProv.ClearSelection();
            ListItem item = ddlEmpresasProv.Items.FindByValue(bebidaSelec.empresa.idEmpresa.ToString());
            if (item != null)
            {
                item.Selected = true;
            }

            Session["modificar"] = 1;

            string script = "window.onload = function() { $('#form-modal-alimento').modal('show'); };";
            ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);

        }

        protected void gvAlimentos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvBebidas.PageIndex = e.NewPageIndex;
            gvBebidas.DataBind();
        }

        //Para esconder la barra de navegacion
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

        //botones para redireccionar
        protected void btnAlimentos_click(object sender, EventArgs e)
        {
            Response.Redirect("ListarAlimentos.aspx");
        }

        protected void btnBebidas_click(object sender, EventArgs e)
        {
            //Como esta en la misma página solo es necesario hacer postback o tal vez no hacer nada
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void btnCuidado_click(object sender, EventArgs e)
        {
            Response.Redirect("ListarCuidadoPersonal.aspx");
        }

        protected void gvBebidas_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                // Obtener la bebida actual
                bebida bebida = (bebida)e.Row.DataItem;

                // Asignar el valor de IdEmpresa directamente a la celda correspondiente
                e.Row.Cells[6].Text = bebida.empresa.idEmpresa.ToString();
            }
        }
    }
}