using LothelAplicacionWeb.LothelSoftWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LothelAplicacionWeb
{
    public partial class pVentas : System.Web.UI.Page
    {
        private BindingList<reservaHabitacion> reservas;
        private ReservasWSClient daoReservas;
        private pedido ped;

        // Propiedad para manejar productos usando ViewState
        private BindingList<producto> Productos
        {
            get
            {
                if (ViewState["productos"] == null)
                {
                    return new BindingList<producto>();
                }
                return ViewState["productos"] as BindingList<producto>;
            }
            set
            {
                ViewState["productos"] = value;
            }
        }

        // Propiedad para manejar productosAgregar usando ViewState
        private BindingList<producto> ProductosAgregar
        {
            get
            {
                if (ViewState["productosAgregar"] == null)
                {
                    return new BindingList<producto>();
                }
                return ViewState["productosAgregar"] as BindingList<producto>;
            }
            set
            {
                ViewState["productosAgregar"] = value;
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            errorCarritoLogeo.Text = " ";
            

            if (!IsPostBack)
            {
                ProductosAgregar = new BindingList<producto>(); // Inicializar la lista aquí
                Productos = new BindingList<producto>();
                BindRepeater();

                VentasWSClient daoProducto = new VentasWSClient();
                Productos = new BindingList<producto>(daoProducto.listarPorNombre(" ").ToList());
                BindRepeater();

                esconderBarraLateral();
            }

        }

        protected void Page_init(object sender, EventArgs e)
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

        protected void esconderBarraLateral()
        {
            var menuLateral = this.Master.FindControl("menuLateral") as HtmlGenericControl;
            menuLateral.Visible = false;
        }

        private void BindRepeater()
        {
            rptProductosLista.DataSource = Productos;
            rptProductosLista.DataBind();
        }

        protected void rptProductosLista_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "Agregar")
            {
                if (Session["huesped"] == null)
                {
                    errorCarritoLogeo.Text="Debe logearse como Huesped antes de realizar un pedido";
                }
                else
                {
                    // Obtener el CommandArgument
                    int idProductoSeleccionado = Int32.Parse(e.CommandArgument.ToString());

                    // Verificar que productos no es nulo
                    if (Productos == null)
                    {
                        System.Diagnostics.Debug.WriteLine("productos es nulo");
                        return;
                    }
                    else
                    {
                        System.Diagnostics.Debug.WriteLine("productos si tiene elementos");
                    }

                    // Buscar el producto en la lista de productos
                    producto prodSeleccionado = Productos.SingleOrDefault(x => x.idIteam == idProductoSeleccionado);
                    if (prodSeleccionado != null)
                    {
                        // Obtener el TextBox dentro del ItemTemplate
                        TextBox txtSelectorQty = e.Item.FindControl("txtSelectorQty") as TextBox;
                        if (txtSelectorQty != null)
                        {
                            int cantidad;

                            if (EsNumero(txtSelectorQty.Text) && txtSelectorQty.Text.Length > 0 && Int32.Parse(txtSelectorQty.Text) <= prodSeleccionado.stock)
                            {
                                if (Int32.TryParse(txtSelectorQty.Text, out cantidad))
                                {
                                    prodSeleccionado.cantPedido = cantidad;

                                    // Agregar el producto a la lista de productos a agregar

                                    //verificar si ya existe el producto

                                    bool existeProducto=false;
                                    foreach(producto p in ProductosAgregar)
                                    {
                                        if (p.idIteam == prodSeleccionado.idIteam)
                                        {
                                            p.cantPedido += prodSeleccionado.cantPedido;
                                            existeProducto = true;
                                            break;
                                        }
                                    }
                                    //if(!existeProducto)ProductosAgregar.Add(prodSeleccionado);
                                    if (existeProducto == false)
                                    {
                                        ProductosAgregar.Add(prodSeleccionado);
                                    }
                                    //prodSeleccionado.stock -= cantidad; //Descontando el stock del arreglo de ProductosSeleccionados
                                }
                                else
                                {
                                    errorCarritoLogeo.Text = "Tiene que ingresar una cantidad valida en " + prodSeleccionado.nombre;
                                    txtSelectorQty.Text = "1";
                                }
                            }
                            else
                            {
                                errorCarritoLogeo.Text = "Tiene que ingresar una cantidad valida en " + prodSeleccionado.nombre;
                                txtSelectorQty.Text = "1";
                            }
                        }
                    }
                }
                
            }
        }

        private bool EsNumero(string texto)
        {
            foreach (char c in texto)
            {
                if (!char.IsDigit(c) || c=='e')
                {
                    return false;
                }
            }
            return true;
        }

        protected void lbBUscarProductoPorNombre_Click(object sender, EventArgs e)
        {
            // Inicializar daoProducto aquí para asegurarse de que esté disponible
            VentasWSClient daoProducto = new VentasWSClient();
            Session["TextoBusqueda"] = txtBusquedaProducto.Text;
            //Productos = new BindingList<producto>(daoProducto.listarPorNombre(txtBusquedaProducto.Text).ToList());
            //BindRepeater();

            Session["vefBuscar"] = daoProducto.listarPorNombre((string)Session["TextoBusqueda"]);

            if (Session["vefBuscar"] == null || ((string)Session["TextoBusqueda"]).Length>=45)
            {
                errorCarritoLogeo.Text += "No se ha encontrado un producto con el nombre especificado<br />";
            }
            else
            {
                Productos = new BindingList<producto>(daoProducto.listarPorNombre(txtBusquedaProducto.Text).ToList());
                BindRepeater();
            }
        }

        protected void lbVerCarrito_Click(object sender, EventArgs e)
        {
            lblMensajeErrorCantidad.Text = "";
            if (Session["Huesped"] == null)
            {
                errorCarritoLogeo.Text = "Debe logearse como Huesped antes de realizar un pedido";
            } else
            {
                gvCarrito.DataSource = ProductosAgregar;
                gvCarrito.DataBind();
                string script = "window.onload = function() { $('#form-modal-carrito').modal('show'); };";
                ClientScript.RegisterStartupScript(GetType(), "ShowModal", script, true);
            } 
        }

        protected void lbRegistrarPedido_Click(object sender, EventArgs e)
        {
            //validacion inicial de cantidad pedida

            bool esCantMayorStock = false;

            foreach (producto p in ProductosAgregar)
            {
                foreach(producto pAux in Productos)
                {
                    if (p.idIteam == pAux.idIteam) //si encuentro el producto
                    {
                        if (p.cantPedido > pAux.stock) //si la cantidad pedida es mayor al stock
                        {
                            esCantMayorStock = true;
                        }
                    }
                }
            }

            if (esCantMayorStock == true || ProductosAgregar.Count==0)
            {
                lblMensajeErrorCantidad.Text = "La cantidad pedida debe ser menor al stock";

                if (ProductosAgregar.Count == 0)
                {
                    lblMensajeErrorCantidad.Text = "Debe seleccionar al menos un producto";
                }
            }
            else
            {
                VentasWSClient daoPedido = new VentasWSClient();
                pedido ped = new pedido();
                persona quiensolicita = (persona)Session["Huesped"];
                int idReservaSeleccionada = Int32.Parse(ddlReservasEnCurso.SelectedValue);
                reservaHabitacion reservaSeleccionada = reservas.SingleOrDefault(x => x.idReserva == idReservaSeleccionada);
                ped.reserva = reservaSeleccionada;
                ped.fechaSolicitud = DateTime.Now;
                ped.fechaSolicitudSpecified = true;
                ped.estado = estadoPedido.Pendiente;
                ped.estadoSpecified = true;

                List<item> itemsAgregar = new List<item>();
                double acumulado = 0;
                foreach (producto pAgreg in ProductosAgregar)
                {
                    item it = (item)pAgreg;
                    itemsAgregar.Add(it);
                    acumulado += pAgreg.cantPedido * ((item)pAgreg).precio;
                }
                ped.items = itemsAgregar.ToArray();
                ped.montoAcumulado = acumulado;
                int pedidoRegistrado = daoPedido.registarPedido(ped);

                if (pedidoRegistrado > 0)
                {
                    // Limpiar ProductosAgregar si se registró correctamente
                    ProductosAgregar.Clear();

                    // Redirigir a pUsuario.aspx
                    Response.Redirect("pUsuario.aspx");
                }
                else
                {
                    // Manejar el caso de error en el registro del pedido si es necesario
                    System.Diagnostics.Debug.WriteLine("hubo un error al registrar el producto");
                }
            }
        }

        protected void rblRating_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (Session["Huesped"] == null)
            {
                errorCarritoLogeo.Text = "Debe logearse como Huesped antes de calificar un producto";
            }
            else
            {

                RadioButtonList rblRating = (RadioButtonList)sender;


                RepeaterItem item = (RepeaterItem)rblRating.NamingContainer;


                HiddenField hfProductId = (HiddenField)item.FindControl("hfProductId");
                int productId = Convert.ToInt32(hfProductId.Value);
                int rating = Convert.ToInt32(rblRating.SelectedValue);


                GuardarCalificacionProducto(productId, rating);

                VentasWSClient daoProducto = new VentasWSClient();
                if (txtBusquedaProducto.Text.Length == 0) {
                    Productos = new BindingList<producto>(daoProducto.listarPorNombre(" ").ToList());
                } else
                    Productos = new BindingList<producto>(daoProducto.listarPorNombre((string)Session["TextoBusqueda"]).ToList());
                BindRepeater();

            }
        }

        private void GuardarCalificacionProducto(int productId, int rating)
        {
            VentasWSClient daoItem = new VentasWSClient();
            item itemCalificar = (item)(Productos.SingleOrDefault(x => x.idIteam == productId));
            daoItem.ingresarCalificacion(itemCalificar, rating);

        }

        protected void btnEliminarAlimento(object sender, EventArgs e)
        {
            int idAux = Int32.Parse(((LinkButton)sender).CommandArgument);

            foreach(producto p in ProductosAgregar.ToList())
            {
                if (idAux == p.idIteam)
                {
                    ProductosAgregar.Remove(p);
                }
            }

            lbVerCarrito_Click(sender,e);
        }
    }


}
