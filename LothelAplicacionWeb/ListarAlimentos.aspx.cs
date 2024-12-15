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
    public partial class ListarAlimentos : System.Web.UI.Page
    {
        private VentasWSClient daoAlimento;

        private BindingList<alimento> alimentos;

        private alimento alimen;
        private categoriaAlimento cat;

        //redireccion a otras paginas

        //Para realizar las busquedas
        private BindingList<producto> Productos;

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
        protected void Page_Load(object sender, EventArgs e)
        {
            daoAlimento = new VentasWSClient();
            /*alimentos = new BindingList<alimento>(daoAlimento.listarAlimentos().ToList());
            gvAlimentos.DataSource = alimentos;
            gvAlimentos.DataBind();*/

            //busqueda
            LblProductoNoEncontrado.Text = "";
            alimentos = new BindingList<alimento>(daoAlimento.listarAlimentosPorNombre(" ").ToList());
            gvAlimentos.DataSource = alimentos;
            gvAlimentos.DataBind();
        }

        //Para subir imagenes (inicio)
        protected void btnSubirImagen_OnClick(object sender, EventArgs e)
        {
            lblErrorSubidaImagen.Text = "";
            if (!esImagenValida(txtUrlImagen.Text)|| txtUrlImagen.Text.Length==0)
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
            btnOjo.Visible=true;
            btnOjoCerrado.Visible = false;
        }
        protected void btnOcultarImagen_OnClick(object sender, EventArgs e)
        {
            divImage.Visible= false;
            btnOjo.Visible = false;
            btnOjoCerrado.Visible = true;
        }
        //Para subir imagenes (fin)

        //Para la barra de busqueda
        protected void lbBuscarProductoPorNombre_Click(object sender, EventArgs e)
        {
            // Inicializar daoProducto aquí para asegurarse de que esté disponible
            //VentasWSClient daoProducto = new VentasWSClient();
            Session["vefBuscar"] = daoAlimento.listarAlimentosPorNombre(txtBusquedaProducto.Text);
            
            if (Session["vefBuscar"] == null)
            {
                LblProductoNoEncontrado.Text = "No se ha encontrado un producto con el nombre especificado";
            }
            else
            {
                alimentos = new BindingList<alimento>(daoAlimento.listarAlimentosPorNombre(txtBusquedaProducto.Text).ToList());
                gvAlimentos.DataSource = alimentos;
                gvAlimentos.DataBind();
            }
        }


        //para el registro los textBoxes estan vacios, pero cuando se llenen se debe validar los dats
        protected void lbRegistrarAlimento_Click(object sender, EventArgs e)
        {

            lblTituloModalAlimento.Text = "Registrar alimento";

            txtIdAlimento.Text = "";
            txtNombreAlimento.Text = "";
            txtDescripcionAlimento.Text = "";
            txtStockAlimento.Text = "";
            txtPrecioAlimento.Text = "";
            
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

        protected void esconderBarraLateral()
        {
            var menuLateral = this.Master.FindControl("menuLateral") as HtmlGenericControl;
            menuLateral.Visible = false;
        }

        protected void btnConfirmarRegistroAlimento(object sender, EventArgs e)
        {
            lblMensajeErrorAlimentos.Text = "";
            int cantErrores = 0;

            if (!esImagenValida(txtUrlImagen.Text))
            {
                cantErrores++;
                lblMensajeErrorAlimentos.Text += "Debe subir una imagen valida<br />";
            }

            if (txtNombreAlimento.Text.Length == 0 || txtNombreAlimento.Text.Length>45)
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

            if (ddlCategoria.SelectedValue.Equals("NOSELECT"))
            {
                cantErrores++;
                lblMensajeErrorAlimentos.Text += "Debe seleccionar una categoria<br />";
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
                alimento alimen = new alimento();
                alimen.nombre = txtNombreAlimento.Text;
                alimen.descripcion = txtDescripcionAlimento.Text;
                alimen.precio = Double.Parse(txtPrecioAlimento.Text);
                alimen.stock = Int32.Parse(txtStockAlimento.Text);
                alimen.urlImagen = imgProducto.ImageUrl;

                if (ddlCategoria.SelectedValue.Equals("SNACKS"))
                    alimen.categoria = categoriaAlimento.SNACKS;
                if (ddlCategoria.SelectedValue.Equals("PLATOS"))
                    alimen.categoria = categoriaAlimento.PLATOS;
                if (ddlCategoria.SelectedValue.Equals("POSTRES"))
                    alimen.categoria = categoriaAlimento.POSTRES;
                alimen.categoriaSpecified = true;

                alimen.disponibilidad = true;

                alimen.empresa = new empresaProveedora();
                alimen.empresa.idEmpresa = Int32.Parse(ddlEmpresasProv.SelectedValue);


                if ((int)Session["modificar"] == 0)
                    daoAlimento.registrarAlimento(alimen);
                else if ((int)Session["modificar"] == 1)
                {
                    alimen.idIteam = Int32.Parse(txtIdAlimento.Text);
                    daoAlimento.modificarAlimento(alimen);
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

            if (input[0] == '.' || input[input.Length-1]=='.')
            {
                return false;
            }

            // Si no es ni entero ni double, devolvemos false
            return false;
        }

        protected void btnEliminarAlimento(object sender, EventArgs e)
        {
            int idAlimento = Int32.Parse(((LinkButton)sender).CommandArgument);
            daoAlimento.eliminarAlimento(idAlimento);
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void btnEditarAlimento(object sender, EventArgs e)
        {
            
            lblTituloModalAlimento.Text = "Modificar alimento";
            int idAlimentoSelec = Int32.Parse(((LinkButton)sender).CommandArgument);
            alimento alimenSelec = alimentos.SingleOrDefault(x => x.idIteam == idAlimentoSelec);



            txtIdAlimento.Text = alimenSelec.idIteam.ToString();
            txtNombreAlimento.Text = alimenSelec.nombre;
            txtDescripcionAlimento.Text = alimenSelec.descripcion;
            txtStockAlimento.Text = alimenSelec.stock.ToString();
            txtPrecioAlimento.Text = alimenSelec.precio.ToString();
            ddlCategoria.SelectedValue= alimenSelec.categoria.ToString();

            txtUrlImagen.Text = alimenSelec.urlImagen;
            imgProducto.ImageUrl = alimenSelec.urlImagen;
            btnSubirImagen_OnClick(sender, e);

            ddlEmpresasProv.ClearSelection();
            ListItem item = ddlEmpresasProv.Items.FindByValue(alimenSelec.empresa.idEmpresa.ToString());
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
            gvAlimentos.PageIndex = e.NewPageIndex;
            gvAlimentos.DataBind();
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
            //Como esta en la misma página solo es necesario hacer postback o tal vez no hacer nada
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void btnBebidas_click(object sender, EventArgs e)
        {
            Response.Redirect("ListarBebidas.aspx");
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
                alimento alimento = (alimento)e.Row.DataItem;

                // Asignar el valor de IdEmpresa directamente a la celda correspondiente
                e.Row.Cells[6].Text = alimento.empresa.idEmpresa.ToString();
            }
        }
    }
}