<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="ListarBebidas.aspx.cs" Inherits="LothelAplicacionWeb.ListarBebidas" %>

<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">

    <div class="container">
        <!--aqui para elegir el tipo de item a listar-->
        <div class="container row">
            <div class="text-start p-3">
                <asp:LinkButton ID="btnAlimentos" runat="server" CssClass="btn btn-success" OnClick="btnAlimentos_click">
                        <i class="fas fa-apple-alt"></i> Alimentos
                    </asp:LinkButton>
                <asp:LinkButton ID="btnBebidas" runat="server" CssClass="btn btn-success" OnClick="btnBebidas_click">
                        <i class="fas fa-coffee"></i> Bebidas
                    </asp:LinkButton>
                <asp:LinkButton ID="btnCuidadoPersonal" runat="server" CssClass="btn btn-success" OnClick="btnCuidado_click">
                        <i class="fas fa-spa"></i> Cuidado Personal
                    </asp:LinkButton>
                <hr>
            </div>
        </div>

                <!-- parte superior (arriba del gridView) -->
         <div class="row m-1">

             <div class="col">
                 <!-- Titulo del listado-->
                 <p class="fontEvento">Bebidas</p>
             </div>
         </div>

        <div class="container mb-3">
            <div class="row">
                 <!-- barra busqueda -->
                 <div class ="col">
                         <asp:TextBox CssClass="form-control" ID="txtBusquedaProducto" runat="server"></asp:TextBox>
                 </div>

                <div class ="col">
                        <asp:LinkButton ID="LinkButton1" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="lbBuscarProductoPorNombre_Click" />
                </div>
                <!-- fin de barra busqueda -->

                <div class="col text-end">
            <asp:LinkButton ID="lbRegistrarBebida" runat="server" CssClass="btn btn-success me-4" Text="<i class='fa-solid fa-plus pe-2'></i> Registrar Bebida" OnClick="lbRegistrarBebida_Click" />
                </div>

            </div>
        </div>

        <div class="row text-center">
            <div class="col-md-12">
                <asp:Label ID="LblProductoNoEncontrado" style="color:red;" runat="server" Text=""></asp:Label>
            </div>
        </div>

    <!-- fin -->

        <div class="container row">
            <asp:GridView ID="gvBebidas" runat="server"
                AllowPaging="true" PageSize="5" OnPageIndexChanging="gvAlimentos_PageIndexChanging"
                AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnRowDataBound="gvBebidas_RowDataBound">
                <Columns>
                    <asp:BoundField HeaderText="Id" DataField="idIteam" />
                    <asp:BoundField HeaderText="Nombre" DataField="nombre" />
                    <asp:BoundField HeaderText="Descripcion" DataField="descripcion" />
                    <asp:BoundField HeaderText="Precio" DataField="precio" />
                    <asp:BoundField HeaderText="Stock" DataField="stock" />
                    <asp:BoundField HeaderText="Categoria" DataField="categoria" />
                    <asp:BoundField HeaderText="Proveedor"  />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-edit ps-2'></i>" OnClick="btnEditarBebida" CommandArgument='<%# Eval("idIteam") %>' />
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" OnClick="btnEliminarBebida" CommandArgument='<%# Eval("idIteam") %>' />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>


        <!-- MODAL PARA AGREGAR UNA Bebida --->

        <div class="modal" id="form-modal-alimento">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">

                        <asp:Label runat="server" ID="lblTituloModalAlimento" class="modal-title" Text="Registro de bebida"></asp:Label>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <asp:UpdatePanel runat="server">
                            <ContentTemplate>
                                <div class="container row pb-3 pt-3">
                                    <div class="row align-items-center">
                                        <div class="col-sm-2">
                                            Id
                                            <asp:TextBox CssClass="form-control" ID="txtIdAlimento" runat="server" Enabled="false"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Nombre
                                            <asp:TextBox CssClass="form-control" ID="txtNombreAlimento" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-7">
                                            Descripcion
                                            <asp:TextBox CssClass="form-control" ID="txtDescripcionAlimento" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Stock
                                            <asp:TextBox CssClass="form-control" ID="txtStockAlimento" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-2">
                                            Precio
                                            <asp:TextBox CssClass="form-control" ID="txtPrecioAlimento" runat="server"></asp:TextBox>
                                        </div>

                                        <div class="col-sm-3">
                                            Categoria
                                        <asp:DropDownList ID="ddlCategoria" runat="server">
                                            <asp:ListItem Text="-- Selecciona una categoria --" Value="NOSELECT" />
                                            <asp:ListItem Text="INFUSIONES" Value="INFUSIONES" />
                                            <asp:ListItem Text="GASEOSAS" Value="GASEOSAS" />
                                            <asp:ListItem Text="LICORES" Value="LICORES" />
                                        </asp:DropDownList>
                                        </div>

                                        <!-- empresa proveedora -->
                                            <div class="col-sm-3 m-4">
                                                Empresa Proveedora
                                                <asp:DropDownList runat="server" ID="ddlEmpresasProv">
                                                </asp:DropDownList>
                                            </div>
                                         <!-- empresa proveedora -->

                                        <!-- imagen -->
                                        <div class="col-sm-7">
                                            URL de la imagen
                                            <asp:LinkButton style="color: #d9a13c; text-decoration: none;" Visible="false" OnClick="btnOcultarImagen_OnClick" ID="btnOjo" runat="server" Text="<i class='fa-solid fa-eye m-2'></i>" />
                                            <asp:LinkButton style="color: #d9a13c; text-decoration: none;" Visible="false" ID="btnOjoCerrado" OnClick="btnMostrarImagen_OnClick" runat="server" Text="<i class='fa-solid fa-eye-slash m-2'></i>" />
                                                <asp:TextBox CssClass="form-control" ID="txtUrlImagen" runat="server"></asp:TextBox>
                                        </div>

                                        <div class="col-sm-3 mt-4">
                                            <asp:LinkButton runat="server" CssClass="btn btn-secondary" OnClick="btnSubirImagen_OnClick" Text="<i class='fa-solid fa-upload'></i> Subir Imagen" />
                                        </div>

                                        <div class="col mt-4 text-end">
                                            <asp:LinkButton ID="btnConfirmarRegistro" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid  fa-check pe-2'></i> Confirmar" OnClick="btnConfirmarRegistroBebida" />
                                        </div>

                                        <div class="col-sm-4">

                                        </div>

                                        <div class="col-xl-3 mt-4">
                                            <div ID="divImage" class="image-container" runat="server" visible="false" style="width: 300px; height: 200px; overflow: hidden; position: relative;">
                                                <asp:Image ID="imgProducto" ImageUrl="https://cdn-icons-png.flaticon.com/512/5798/5798294.png" runat="server" style="height: 100%; width: auto; position: absolute; top: 0; left: 50%; transform: translateX(-50%);"/>
                                            </div>
                                        </div>

                                        <!-- imagen -->

                                        
                                    </div>

                                    <!-- mensajes de validacion -->
                                    <div class="row text-end">
                                        <div class="col-md-12">
                                            <asp:Label ID="lblErrorSubidaImagen" style="color:red;" runat="server" Text=""></asp:Label>
                                        </div>
                                    </div>

                                    <div class="row text-end">
                                        <div class="col-md-12">
                                            <asp:Label ID="lblMensajeErrorAlimentos" Style="color: red;" runat="server" Text=""></asp:Label>
                                        </div>
                                    </div>

                                </div>

                            </ContentTemplate>
                        </asp:UpdatePanel>
                    </div>
                </div>
            </div>
        </div>


        <!-- fin del modal -->

    </div>

</asp:Content>
