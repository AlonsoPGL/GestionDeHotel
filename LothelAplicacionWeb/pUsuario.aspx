<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="pUsuario.aspx.cs" Inherits="LothelAplicacionWeb.pUsuario" %>

<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="bodyUser" runat="server">

    <style>
        .profile-container {
            display: inline-block;
            padding: 20px;
            border-radius: 10px;
            background-color: #d9a13c;
        }

            .profile-container .d-flex {
                color: floralwhite; /* Color de texto blanco */
            }
    </style>

    <div>

        <div id="destinoPerfil" class="text-center container">
            <br />
            <div class="align-content-between">
                <a href="#destinoPerfil" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-user"></i></a>
                <a href="#destinoReservas" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-bed"></i></a>
                <a href="#destinoPedidos" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-file-alt"></i></a>
            </div>
            <p class="fontEvento">Gestionar Perfil</p>
            <hr />
        </div>

        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-5">
                    <div class="profile-container d-flex justify-content-center align-items-center">
                        <div class="d-flex align-items-center">
                            <div>
                                <img src="https://c1.klipartz.com/pngpicture/823/765/sticker-png-login-icon-system-administrator-user-user-profile-icon-design-avatar-face-head.png" alt="Descripción de la imagen" style="max-width: 100px; border-radius: 50%; border: 2px solid floralwhite;" />
                            </div>
                            <div class="ms-3">
                                <div>
                                    <asp:Label ID="txtNombreApellido" runat="server" Text="text" />
                                </div>
                                <div>
                                    <asp:Label ID="Label3" runat="server" Text="Miembro desde: " CssClass="me-2" />
                                    <asp:Label ID="txtFechaReg" runat="server" Text="text" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <asp:UpdatePanel ID="UpdatePanel1" runat="server">
                <ContentTemplate>

                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-md-5">

                                <div class="row text-center">
                                    <div class="col-md-6">

                                        <asp:Label ID="Label1" runat="server" Text="DNI: " CssClass="me-2" />
                                        <asp:TextBox ID="txtDNI" runat="server" CssClass="form-control" Enabled="false"></asp:TextBox>
                                    </div>
                                    <div class="col-md-6">
                                        <asp:Label ID="Label2" runat="server" Text="Correo: " CssClass="me-2" />
                                        <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control" Enabled="false"></asp:TextBox>
                                    </div>
                                </div>

                                <div class="row text-center mt-2">
                                    <div class="col-md-12">
                                        <asp:Label ID="Label4" runat="server" Text="Celular: " CssClass="me-2" />
                                        <asp:TextBox ID="txtCelular" runat="server" CssClass="form-control text-center" Enabled="false"></asp:TextBox>
                                    </div>
                                </div>

                                <div class="row text-center mt-2">
                                    <div class="col-md-12">
                                        <asp:Label ID="Label5" runat="server" Text="Usuario: " CssClass="me-2" />
                                        <asp:TextBox ID="txtUserProfile" runat="server" CssClass="form-control text-center" Enabled="false"></asp:TextBox>
                                    </div>
                                </div>

                                <div class="row text-center mt-2">
                                    <div class="col-md-12">
                                        <asp:Label ID="Label6" runat="server" Text="Contraseña: " CssClass="me-2" />
                                        <asp:TextBox ID="txtPasswordProfile" runat="server" CssClass="form-control text-center" Enabled="false"></asp:TextBox>
                                    </div>
                                </div>

                                <div class="row text-end mt-4">
                                    <div class="col-md-12">
                                        <asp:LinkButton ID="lblModificarPerfil" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid fa-edit pe-2'></i> Editar Perfil" OnClick="btnEditarPerfilUsuario" />
                                    </div>
                                </div>

                                <div class="row text-end">
                                    <div class="col-md-12">
                                        <asp:LinkButton ID="lblConfirmarEdicion" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid fa-check-circle pe-2'></i> Guardar Cambios" OnClick="btnConfirmarEdicion" />
                                        <asp:LinkButton ID="lblCancelarEdicion" runat="server" CssClass="btn btn-danger ms-2" Text="<i class='fas fa-times-circle'></i> Cancelar" OnClick="btnCancelarEdicion" />
                                    </div>
                                </div>

                                <div class="row text-end">
                                    <div class="col-md-12">
                                        <asp:Label ID="lblMensajeErrorCelular" style="color:red;" runat="server" Text=""></asp:Label>
                                    </div>
                                </div>
                                <div class="row text-end">
                                    <div class="col-md-12">
                                        <asp:Label ID="lblMensajeErrorCorreo" style="color:red;" runat="server" Text=""></asp:Label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </ContentTemplate>
            </asp:UpdatePanel>



            <div id="destinoReservas" class="text-center container">
                <br />
                <hr />
                <div class="align-content-between">
                    <a href="#destinoPerfil" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-user"></i></a>
                    <a href="#destinoReservas" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-bed"></i></a>
                    <a href="#destinoPedidos" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-file-alt"></i></a>
                </div>
                <p class="fontEvento">Habitaciones reservadas</p>
                <hr />

                <!-- div en caso no existan reservas (inicio) -->
                <div id="noReservas" runat="server" class="text-center container">
                    <asp:Label ID="lblnoTieneReservas" CssClass="info-heading" runat="server" Text="No tiene reservaciones aún"></asp:Label>
                </div>
                <!-- div en caso no existan reservas (fin) -->

                <div class="container row">
                    <asp:GridView ID="gvReservaHabitacion" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnRowDataBound="gvReservaHabitacion_RowDataBound" OnPageIndexChanging="gvReservaHabitacion_PageIndexChanging">
                        <Columns>
                            <asp:BoundField HeaderText="Numero de Reserva" />
                            <asp:BoundField HeaderText="Fecha de Reserva" />
                            <asp:BoundField HeaderText="Fecha Inicio" />
                            <asp:BoundField HeaderText="Fecha Fin" />
                            <asp:BoundField HeaderText="Estado" />
                            <asp:TemplateField>
                                <ItemTemplate>
                                    <asp:LinkButton ID="btnVerDetalle" runat="server" Text="<i class='fa-solid fa-eye'></i>" OnClick="lbVisualizarReserva_Click" CommandArgument='<%# Eval("idReserva") %>' data-toggle="modal" data-target="#detalleModal" />
                                </ItemTemplate>
                            </asp:TemplateField>
                            <asp:TemplateField>
                                <ItemTemplate>
                                    <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash'></i>" OnClick="lblEliminarReserva_Click" CommandArgument='<%# Eval("idReserva") %>' />
                                </ItemTemplate>
                            </asp:TemplateField>
                        </Columns>
                    </asp:GridView>
                </div>
            </div>

            <div id="destinoPedidos" class="text-center container">
                <br />
                <hr />
                <div class="align-content-between">
                    <a href="#destinoPerfil" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-user"></i></a>
                    <a href="#destinoReservas" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-bed"></i></a>
                    <a href="#destinoPedidos" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-file-alt"></i></a>
                </div>
                <p class="fontEvento">Pedidos realizados</p>
                <hr />
                <!-- div en caso no existan reservas (inicio) -->
                <div id="noPedidos" runat="server" class="text-center container">
                    <asp:Label ID="lblNoPedidos" CssClass="info-heading" runat="server" Text="No tiene pedidos aún"></asp:Label>
                </div>
                <!-- div en caso no existan reservas (fin) -->

                <!--Marcelo aca colocas el gridview de pedidos-->
                <div class="container row">
                    <asp:GridView ID="gvPedidosCliente" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnRowDataBound="gvPedidosCliente_RowDataBound" OnPageIndexChanging="gvPedidosCliente_PageIndexChanging">
                        <Columns>
                            <asp:BoundField HeaderText ="No."/>
                            <asp:BoundField HeaderText ="Fecha de solicitud"/>
                            <asp:BoundField HeaderText ="Estado"/>
                            <asp:BoundField HeaderText ="Monto"/>
                            <asp:BoundField HeaderText ="Reserva Asignada" />
                        </Columns>
                    </asp:GridView>
                </div>
                <!--fin del gridview de pedidos-->

            </div>

        </div>
    </div>
    <div class="modal fade" id="detalleModal" tabindex="-1" role="dialog" aria-labelledby="detalleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="detalleModalLabel">Detalles de la Reserva</h5>
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <h2><strong>Detalle Reserva</strong></h2>
                            <h6><strong>Número de Reserva:</strong> <asp:Label ID="lblNumeroReserva" runat="server" /></h6>
                            <h6><strong>Fecha de Reserva:</strong> <asp:Label ID="lblFechaReserva" runat="server" /></h6>
                            <h6><strong>Fecha de Inicio:</strong> <asp:Label ID="lblFechaInicio" runat="server" /></h6>
                            <h6><strong>Fecha de Fin:</strong> <asp:Label ID="lblFechaFin" runat="server" /></h6>
                            <h6><strong>Estado:</strong> <asp:Label ID="lblEstado" runat="server" /></h6>
                            <h6><strong>Nombre del Huésped:</strong> <asp:Label ID="lblNombreHuesped" runat="server" /></h6>
                        </div>
                        <div class="col-12 mt-3">
                            <h2><strong>Detalle Reserva</strong></h2>
                            <p><strong>Número de Habitación:</strong> <asp:Label ID="lblNumeroHabitacion" runat="server" /></p>
                            <p><strong>Número de Piso:</strong> <asp:Label ID="lblPiso" runat="server" /></p>
                            <p><strong>Número de Camas:</strong> <asp:Label ID="lblNumeroCamas" runat="server" /></p>
                            <p><strong>Precio de la Habitación:</strong> <asp:Label ID="lblPrecio" runat="server" /></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>
</asp:Content>
