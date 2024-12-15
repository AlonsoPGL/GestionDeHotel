<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="ListarEmpresasProveedoras.aspx.cs" Inherits="LothelAplicacionWeb.ListarEmpresasProveedoras" %>

<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">



    <div class="container">

        <div class="container mt-3">
            <div class="row">
                <div class="col">
                    <!-- Titulo del listado-->
                    <p class="fontEvento">Empresas Proveedoras</p>
                </div>
                <div class="col text-end">
                    <asp:LinkButton ID="lbRegistrarEmpresa" runat="server" CssClass="btn btn-success me-4" Text="<i class='fa-solid fa-plus pe-2'></i> Registrar Empresa" OnClick="lbRegistrarEmpresa_Click" />
                </div>
            </div>
        </div>

        <div class="container row">
            <asp:GridView ID="gvEmpresas" runat="server"
                AllowPaging="true" PageSize="5"
                AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="gvEmpresas_PageIndexChanging">
                <Columns>
                    <asp:BoundField HeaderText="Id" DataField="idEmpresa" />
                    <asp:BoundField HeaderText="RUC" DataField="ruc" />
                    <asp:BoundField HeaderText="Razon social" DataField="razonSocial" />
                    <asp:BoundField HeaderText="Correo electrónico" DataField="correo" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-edit ps-2'></i>" OnClick="btnEditarEmpresa" CommandArgument='<%# Eval("idEmpresa") %>' />
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" OnClick="btnEliminarEmpresa" CommandArgument='<%# Eval("idEmpresa") %>' />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>


        <!-- MODAL PARA AGREGAR UNA EMPRESA PROVEEDORA --->

        <div class="modal" id="form-modal-empresa">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">

                        <asp:Label runat="server" ID="lblTituloModalEmpresa" class="modal-title" Text="Registro de empresa"></asp:Label>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <asp:UpdatePanel runat="server">
                            <ContentTemplate>
                                <div class="container row pb-3 pt-3">
                                    <div class="row align-items-center">
                                        <div class="col-sm-3">
                                            Id
                                    <asp:TextBox CssClass="form-control" ID="TxtIdEmpresaProv" runat="server" Enabled="false"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            RUC
                                    <asp:TextBox CssClass="form-control" ID="txtRUC" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Razon Social
                                    <asp:TextBox CssClass="form-control" ID="txtRazonSocial" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Correo
                                    <asp:TextBox CssClass="form-control" ID="txtCorreo" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col mt-4 text-end">
                                            <asp:LinkButton ID="lbConfirmarRegistro" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid  fa-check pe-2'></i> Confirmar" OnClick="btnConfirmarRegistro" />
                                        </div>
                                    </div>

                                    <div class="row text-end">
                                        <div class="col-md-12">
                                            <asp:Label ID="lblMensajeErrorRUC" Style="color: red;" runat="server" Text=""></asp:Label>
                                        </div>
                                    </div>
                                    <div class="row text-end">
                                        <div class="col-md-12">
                                            <asp:Label ID="lblMensajeErrorCorreo" Style="color: red;" runat="server" Text=""></asp:Label>
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
