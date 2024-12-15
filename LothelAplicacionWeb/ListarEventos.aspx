<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="ListarEventos.aspx.cs" Inherits="LothelAplicacionWeb.ListarEventos" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">


    <div class="container">
        
         <div class="row m-1">
             <div class="col">
                 <!-- Titulo del listado-->
                 <p class="fontEvento">Eventos</p>
             </div>
         </div>

        <div class="container mb-3">
            <div class="row">
                <!-- barra busqueda -->
                <div class="col">
                    <asp:TextBox CssClass="form-control" ID="txtBusquedaEvento" runat="server"></asp:TextBox>
                </div>

                <div class="col">
                    <asp:LinkButton ID="lbBuscarEvento" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="lbBuscarEventoPorNombre_Click" />
                </div>
                <div class="col">
                    <asp:Label ID="LblEventoNoEncontrado" Style="color: red;" runat="server" Text=""></asp:Label>
                </div>

                <div class="col text-end">
                    <asp:LinkButton ID="lbRegistrarEvento" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid fa-plus pe-2'></i> Registrar Evento" OnClick="lbRegistrarEvento_Click" />
                </div>
            </div>
        </div>

        <div class="container row">
            <asp:GridView ID="gvEventos" runat="server"
                AllowPaging="true" PageSize="5" OnPageIndexChanging="gvEventos_PageIndexChanging"
                AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnRowDataBound="gvEventos_RowDataBound">
                <Columns>
                    <asp:BoundField HeaderText="Id" DataField="idEvento" />
                    <asp:BoundField HeaderText="Nombre" DataField="nombre" />
                    <asp:BoundField HeaderText="Descripcion" DataField="descripcion" />
                    <asp:BoundField HeaderText="Asistentes" DataField="cantidadAsistentes" />
                    <asp:BoundField HeaderText="Fecha Inicio" />
                    <asp:BoundField HeaderText="Fecha Fin" />
                    <asp:BoundField HeaderText="Estado" DataField="estado" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-edit ps-2'></i>" OnClick="btnEditarEvento" CommandArgument='<%# Eval("idEvento") %>' />
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" OnClick="btnEliminarEvento" CommandArgument='<%# Eval("idEvento") %>' />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>


        <!-- MODAL PARA AGREGAR UN EVENTO --->

        <div class="modal" id="form-modal-evento">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">

                        <asp:Label runat="server" ID="lblTituloModalEvento" class="modal-title" Text="Registro de evento"></asp:Label>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <asp:UpdatePanel runat="server">
                            <ContentTemplate>
                                <div class="container row pb-3 pt-3">
                                    <div class="row align-items-center">
                                        <div class="col-sm-3">
                                            Id
                                        <asp:TextBox CssClass="form-control" ID="txtIdEvento" runat="server" Enabled="false"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Nombre
                                        <asp:TextBox CssClass="form-control" ID="txtNombreEvento" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Descripcion
                                        <asp:TextBox CssClass="form-control" ID="txtDescripcionEvento" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Cantidad De Asistentes
                                        <asp:TextBox CssClass="form-control" ID="txtCantAsistentesEvento" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-8">
                                            Fecha Inicio
                                            <input class="form-control" type="date" id="dtpFechaInicio" runat="server">
                                        </div>
                                        <div class="mb-3 row">
                                            <div class="col-sm-8">
                                                Fecha Fin
                                                <input class="form-control" type="date" id="dtpFechaFin" runat="server" enable="false">
                                            </div>
                                        </div>
                                        
                                        <div class="col mt-4 text-end">
                                            <asp:LinkButton ID="lbConfirmarRegistroEvento" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid  fa-check pe-2'></i> Confirmar" OnClick="btnConfirmarRegistroEvento" />
                                        </div>

                                        
                                        <div class="mb-3 row">
                                            Reserva Espacios
                                        </div>



                                        <!--mensaje de errores-->
                                        <div class="row text-end">
                                            <div class="col-md-12">
                                                <asp:Label ID="lblMensajeErrorDescripcionEvento" Style="color: red;" runat="server" Text=""></asp:Label>
                                            </div>
                                        </div>
                                        <div class="row text-end">
                                            <div class="col-md-12">
                                                <asp:Label ID="lblMensajeErrorFechaRealizacionEvento" Style="color: red;" runat="server" Text=""></asp:Label>
                                            </div>
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
