<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="ListarPersonalRecepcionista.aspx.cs" Inherits="LothelAplicacionWeb.ListarPersonalRecepcionista" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">
    <div class="container">
    <!--aqui para elegir el personal de servicio-->
    <div class="container row">
        <div class="text-start p-3">
            <asp:LinkButton ID="btnRecepcionista" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid fa-concierge-bell pe-2'></i> Recepcionistas" OnClick="lbListarRecepcionistas_Click" />
            <asp:LinkButton ID="btnMasajista" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid fa-spa pe-2'></i> Personal De Masajes" OnClick="lbListarMasajistas_Click" />
            <asp:LinkButton ID="btnLavandero" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid fa-tshirt pe-2'></i> Personal De Lavanderia" OnClick="lbListarLavanderos_Click" />
            <hr>
        </div>
    </div>

    <!-- Titulo mas boton de registrar -->
    <div class="container">
        <div class="row">
            <div class="col">
                <!-- Titulo del listado-->
                <p class="fontEvento">Recepcionistas</p>
            </div>
            <div class="col text-end">
        <asp:LinkButton ID="lbRegistrarRecepcionista" runat="server" CssClass="btn btn-success me-4" Text="<i class='fa-solid fa-plus pe-2'></i> Registrar Personal" OnClick="lbRegistrarRecepcionista_Click" />
            </div>
        </div>
    </div>

    <div class="container row">
        <asp:GridView ID="gvRecepcionistas" runat="server"
            AllowPaging="true" PageSize="5" OnPageIndexChanging="gvRecepcionistas_PageIndexChanging"
            AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped">
            <Columns>
                <asp:BoundField HeaderText="Id" DataField="idPersona" />
                <asp:BoundField HeaderText="DNI" DataField="dni" />
                <asp:BoundField HeaderText="Nombre" DataField="nombre" />
                <asp:BoundField HeaderText="Apellido Paterno" DataField="apellidoPaterno" />
                <asp:BoundField HeaderText="Apellido Materno" DataField="apellidoMaterno" />
                <asp:BoundField HeaderText="Correo" DataField="correo" />
                <asp:BoundField HeaderText="Turno" DataField="turno" />
                <asp:TemplateField>
                    <ItemTemplate>
                        <asp:LinkButton runat="server" Text="<i class='fa-solid fa-edit ps-2'></i>" OnClick="btnEditarRecepcionista" CommandArgument='<%# Eval("idPersona") %>' />
                        <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" OnClick="btnEliminarRecepcionista" CommandArgument='<%# Eval("idPersona") %>' />
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </div>


    <!-- MODAL PARA AGREGAR A LAVANDERO --->

    <div class="modal" id="form-modal-recepcionista">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">

                    <asp:Label runat="server" ID="lblTituloModalRecepcionista" class="modal-title" Text="Registro de personal"></asp:Label>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <div class="container row pb-3 pt-3">
                                <div class="row align-items-center">
                                    <div class="col-sm-3">
                                        Id
                                <asp:TextBox CssClass="form-control" ID="txtIdRecepcionista" runat="server" Enabled="false"></asp:TextBox>
                                    </div>
                                    <div class="col-sm-3">
                                        Nombre
                                <asp:TextBox CssClass="form-control" ID="txtNombreRecepcionista" runat="server"></asp:TextBox>
                                    </div>
                                    <div class="col-sm-3">
                                        Apellido Paterno
                                <asp:TextBox CssClass="form-control" ID="txtApePaternoRecepcionista" runat="server"></asp:TextBox>
                                    </div>
                                    <div class="col-sm-3">
                                        Apellido Materno
                                    <asp:TextBox CssClass="form-control" ID="txtApeMaternoRecepcionista" runat="server"></asp:TextBox>
                                    </div>
                                    <div class="mb-3 row">
                                        <div class="col-sm-3">
                                            Correo
                                        <asp:TextBox CssClass="form-control" ID="txtCorreoRecepcionista" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Celular
                                        <asp:TextBox CssClass="form-control" ID="txtCelularRecepcionista" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            DNI
                                            <asp:TextBox CssClass="form-control" ID="txtDniRecepcionista" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Sueldo
                                            <asp:TextBox CssClass="form-control" ID="txtSueldoRecepcionista" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Deuda
                                            <asp:TextBox CssClass="form-control" ID="txtDeudaRecepcionista" runat="server"></asp:TextBox>
                                        </div>

                                    </div>
                                    <div class="mb-3 row">
                                        <div class="mb-3 row">
                                            <asp:Label ID="lblTurnoRecepcionista" runat="server" Text="Turno:" CssClass="col-sm-2 col-form-label" />
                                            <div class="col-sm-8">
                                                <div class="form-check form-check-inline">
                                                    <asp:RadioButton ID="rbTurnoMañanaRecepcionista" runat="server" Text="" GroupName="TurnoRecepcionista" Checked="true" CssClass="form-check-input" />
                                                    <label class="form-check-label" for="rbTurnoMañanaRecepcionista" runat="server">Mañana</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <asp:RadioButton ID="rbTurnoTardeRecepcionista" runat="server" Text="" GroupName="TurnoRecepcionista" CssClass="form-check-input" />
                                                    <label class="form-check-label" for="rbTurnoTardeRecepcionista" runat="server">Tarde</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <asp:RadioButton ID="rbTurnoNocheRecepcionista" runat="server" Text="" GroupName="TurnoRecepcionista" CssClass="form-check-input" />
                                                    <label class="form-check-label" for="rbTurnoNocheRecepcionista" runat="server">Noche</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            Usuario
                                            <asp:TextBox CssClass="form-control" ID="txtUsuarioRecepcionista" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Contraseña
                                            <asp:TextBox CssClass="form-control" ID="txtCuentaRecepcionista" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            TipoCuenta
                                            <asp:TextBox CssClass="form-control" ID="txtTipoCuentaRecepcionista" runat="server" Text="RECEPCIONISTA" Enabled="false"></asp:TextBox>
                                        </div>

                                        <div class="col-sm-8 mt-3">
                                            Fecha Contratacion
                                        <input type="date" id="dtpFechaContratacionRecepcionista" runat="server">
                                        </div>
                                    </div>

                                    <div class="col mt-4 text-end">
                                        <asp:LinkButton ID="lbConfirmarRegistroRecepcionista" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid  fa-check pe-2'></i> Confirmar" OnClick="btnConfirmarRegistroRecepcionista" />
                                    </div>
                                    <div class="row text-end">
                                        <div class="col-md-12">
                                            <asp:Label ID="lblMensajeErrorDniRecepcionista" Style="color: red;" runat="server" Text=""></asp:Label>
                                        </div>
                                    </div>
                                    <div class="row text-end">
                                        <div class="col-md-12">
                                            <asp:Label ID="lblMensajeErrorCorreoRecepcionista" Style="color: red;" runat="server" Text=""></asp:Label>
                                        </div>
                                    </div>
                                    <div class="row text-end">
                                        <div class="col-md-12">
                                            <asp:Label ID="lblMensajeErrorCelularRecepcionista" Style="color: red;" runat="server" Text=""></asp:Label>
                                        </div>
                                    </div>
                                    <div class="row text-end">
                                        <div class="col-md-12">
                                            <asp:Label ID="lblMensajeErrorSueldo" Style="color: red;" runat="server" Text=""></asp:Label>
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


    <!-- fin del modal  de registrar -->

    <!-- MODAL PARA MODIFICAR A LAVANDERO --->

    <!--fin del modal de modificar a lavanero-->

</div>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="bodyUser" runat="server">
</asp:Content>
