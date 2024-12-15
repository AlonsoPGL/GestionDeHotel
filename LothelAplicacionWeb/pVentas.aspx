<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="pVentas.aspx.cs" Inherits="LothelAplicacionWeb.pVentas" EnableEventValidation="false" %>

<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyUser" runat="server">
    <div class="col contenedor d-flex align-content-center MNpothos">
        <img src="https://www.wanzl.com/TMP%20forever/News%20und%20Presse/2023/Mini-Markets%20im%20Hotel/image-thumb__104966__lightbox/Mini-Markt_2.webp"
            class="MNImagenTitulo" />
        <p class="MNTitulSuperpuesto" style="color: floralwhite">Nuestra tienda<br />
        </p>
        <p class="MNTituloTextoSuperpuesto" style="color: floralwhite">Para todo lo que necesites</p>
    </div>
    <div>
        <br />
        <h3 style="text-align: center">¿Qué estas buscando?</h3>
        <div class="container">
            <!--<form action="/search" method="get">-->
            <div class="row" style="margin-left: 550px">
                <div class="col-sm-3">
                    <asp:TextBox CssClass="form-control" ID="txtBusquedaProducto" runat="server"></asp:TextBox>
                </div>
                <div class="col-sm-4">
                    <asp:LinkButton ID="lbBuscarProductoPorNombre" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="lbBUscarProductoPorNombre_Click" />
                </div>
            </div>
            <!---- boton de ver carrito ---->
            <div class="row mt-3">
                <div class="col mb-3 d-flex justify-content-center">
                    <asp:LinkButton ID="lbVerCarrito" runat="server" CssClass="btn lbAalCarrito"
                        Text="<i class='fa-solid fa-shopping-cart pe-2'></i> Ver Carrito" OnClick="lbVerCarrito_Click" />
                </div>

            </div>
            <div class ="row mt-3">
                <div class="col mb-3 d-flex justify-content-center">
                    <asp:Label ID="errorCarritoLogeo" runat="server" Text=" " ForeColor="red"></asp:Label>
                </div>
            </div>

            <!----- repeater -------------->
            <div class="container row">
                <asp:Repeater ID="rptProductosLista" runat="server" OnItemCommand="rptProductosLista_ItemCommand">


                    <ItemTemplate>
                        <!-- Aquí defines cómo se verá cada elemento en tu repetidor -->
                        <div class="card MNmargen mb-3" style="background-color: bisque;">
                            <div class="row no-gutters">
                                <div class="col mt-3" >
                                    <img class="MNImagenItem"  src='<%# Eval("urlImagen") %>' alt="imageholder">
                                </div>
                                <div class="col-6">
                                    <div class="card-body MN-card-body mt-2">
                                        <div class="row">
                                            <div class="card-title MN-card-title col-6">
                                                <asp:Label ID="lblCartaNombreProducto" runat="server" Text='<%# Eval("nombre") %>'></asp:Label>
                                            </div>
                                            <div class="col-4 ">
                                                <asp:Label ID="lblprecalificacion" runat="server" Text="Calificacion:"></asp:Label>
                                                <asp:Label ID="lblCalificacion" runat="server" Text='<%# String.Format("{0:F2}", Eval("calificacion")) %>'></asp:Label>
                                                <asp:Label ID="lblEstrellita" runat="server" Text="★"></asp:Label>
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="card-text MN-card-description">
                                            <asp:Label ID="lblCartaDescripcionProducto" runat="server" Text='<%# Eval("descripcion") %>'></asp:Label>
                                        </div>
                                        <div class="card-text MN-card-cost-label">
                                            <asp:Label runat="server" Text='Costo Unitario:&nbsp'></asp:Label>
                                            <asp:Label ID="lblCartaCostoProducto" class="MN-card-cost" runat="server" Text='<%# Eval("precio") %>'></asp:Label>
                                            
                                        </div>
                                        <div class="card-text MN-card-cost-label">
                                            <asp:Label runat="server" Text='Stock:&nbsp'></asp:Label>
                                            <asp:Label ID="lblCartaStockProducto" class="MN-card-cost" runat="server" Text='<%# Eval("stock") %>'></asp:Label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="mt-4">
                                        <asp:Label ID="lblEtiquetaProductoCadd" runat="server" Text="Cantidad a añadir:"></asp:Label>
                                    </div>
                                    <div class="input-group quantity-selector">
                                        <asp:TextBox ID="txtSelectorQty" runat="server"
                                            CssClass="form-control numeric-only"
                                            TextMode="Number"
                                            aria-label="Quantity selector"
                                            Text="1"
                                            min="1"
                                            max="10"
                                            step="1"
                                            title="Quantity"
                                            Style="margin-right: 70px;">
                                    </asp:TextBox>

                                    </div>
                                    <br />
                                    <div class="card-body">
                                        <asp:LinkButton runat="server" CssClass="lbAalCarrito" CommandName="Agregar" CommandArgument='<%# Eval("idIteam") %>' Text="Añadir al Carrito:"></asp:LinkButton>
                                    </div>
                                    <asp:Label ID="Label1" runat="server" Text="Danos tu opinión!:"></asp:Label>
                                    <!-- Rating control -->
                                    <asp:HiddenField ID="hfProductId" runat="server" Value='<%# Eval("idIteam") %>' />
                                    <asp:RadioButtonList ID="rblRating" runat="server" CssClass="MN-rating-stars" RepeatDirection="Horizontal" AutoPostBack="true" OnSelectedIndexChanged="rblRating_SelectedIndexChanged">
                                        <asp:ListItem Value="5">★</asp:ListItem>
                                        <asp:ListItem Value="4">★</asp:ListItem>
                                        <asp:ListItem Value="3">★</asp:ListItem>
                                        <asp:ListItem Value="2">★</asp:ListItem>
                                        <asp:ListItem Value="1">★</asp:ListItem>
                                    </asp:RadioButtonList>
                                </div>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>
            </div>

            <!--</form>-->
        </div>
        <br />
        <br />
    </div>

    <!-- Modal -->
    <div class="modal" id="form-modal-carrito">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <asp:Label runat="server" ID="lblTituloModalCarrito" class="modal-title" Text="Carrito de Compras"></asp:Label>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <div class="container row pb-3 pt-3">
                                <div class="row">
                                    <asp:GridView ID="gvCarrito" runat="server" CssClass="table table-striped" AutoGenerateColumns="false">
                                        <Columns>
                                            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
                                            <asp:BoundField DataField="descripcion" HeaderText="Descripción" />
                                            <asp:BoundField DataField="precio" HeaderText="Precio" />
                                            <asp:BoundField DataField="cantPedido" HeaderText="Cantidad" />
                                            <asp:TemplateField>
                                                <ItemTemplate>
                                                    <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" OnClick="btnEliminarAlimento" CommandArgument='<%# Eval("idIteam") %>' />
                                                </ItemTemplate>
                                            </asp:TemplateField>
                                        </Columns>
                                    </asp:GridView>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <asp:Label ID="lblElejirReservaPedido" runat="server" Text="Elegir Reserva asociada al pedido:" CssClass="col-form-label" />
                                    </div>
                                    <div class="col mb-3">
                                        <asp:DropDownList ID="ddlReservasEnCurso" CssClass="form-select" runat="server"></asp:DropDownList>
                                    </div>
                                </div>
                                <div class="row text-end">
                                    <asp:LinkButton ID="lbRegistrarPedido" runat="server"
                                        CssClass="btn btn-success" Text="<i class='fa-solid fa-plus pe-2'></i> Pedir" OnClick="lbRegistrarPedido_Click" />
                                </div>

                                <div class="row text-end">
                                    <div class="col-md-12">
                                        <asp:Label ID="lblMensajeErrorCantidad" style="color:red;" runat="server" Text=""></asp:Label>
                                    </div>
                                </div>

                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>



    <br />
    <br />
    <br />
    <br />
    <br />
</asp:Content>


