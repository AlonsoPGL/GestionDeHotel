<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="ListarPersonalDeServicio.aspx.cs" Inherits="LothelAplicacionWeb.ListarPersonalDeServicio" %>
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
                    <p class="fontEvento">Personal de lavanderia</p>
                </div>
                <div class="col text-end">
                <asp:LinkButton ID="lbRegistrarLavandero" runat="server" CssClass="btn btn-success me-4" Text="<i class='fa-solid fa-plus pe-2'></i> Registrar Personal" OnClick="lbRegistrarLavandero_Click" />
                </div>
            </div>
        </div>


        <div class="container row">
            <asp:GridView ID="gvLavanderos" runat="server"
                AllowPaging="true" PageSize="5" OnPageIndexChanging="gvLavanderos_PageIndexChanging"
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
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-edit ps-2'></i>" OnClick="btnEditarLavandero" CommandArgument='<%# Eval("idPersona") %>' />
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" OnClick="btnEliminarLavandero" CommandArgument='<%# Eval("idPersona") %>' />
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-eye ps-2'></i>" OnClick="btnVerDetalleLavandero" CommandArgument='<%# Eval("idPersona") %>' />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>


        <!-- MODAL PARA AGREGAR A LAVANDERO --->

        <div class="modal" id="form-modal-lavandero">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">

                        <asp:Label runat="server" ID="lblTituloModalLavandero" class="modal-title" Text="Registro de personal"></asp:Label>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <asp:UpdatePanel runat="server">
                            <ContentTemplate>
                                <div class="container row pb-3 pt-3">
                                    <div class="row align-items-center">
                                        <div class="col-sm-3">
                                            Id
                                    <asp:TextBox CssClass="form-control" ID="txtIdLavandero" runat="server" Enabled="false"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Nombre
                                    <asp:TextBox CssClass="form-control" ID="txtNombreLavandero" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Apellido Paterno
                                    <asp:TextBox CssClass="form-control" ID="txtApePaternoLavandero" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="col-sm-3">
                                            Apellido Materno
                                        <asp:TextBox CssClass="form-control" ID="txtApeMaternoLavandero" runat="server"></asp:TextBox>
                                        </div>
                                        <div class="mb-3 row">
                                            <div class="col-sm-3">
                                                Correo
                                            <asp:TextBox CssClass="form-control" ID="txtCorreoLavandero" runat="server"></asp:TextBox>
                                            </div>
                                            <div class="col-sm-3">
                                                Celular
                                            <asp:TextBox CssClass="form-control" ID="txtCelularLavandero" runat="server"></asp:TextBox>
                                            </div>
                                            <div class="col-sm-3">
                                                DNI
                                            <asp:TextBox CssClass="form-control" ID="txtDniLavandero" runat="server"></asp:TextBox>
                                            </div>
                                            <div class="col-sm-3">
                                                Sueldo
                                            <asp:TextBox CssClass="form-control" ID="txtSueldoLavandero" runat="server"></asp:TextBox>
                                            </div>

                                        </div>
                                        <div class="mb-3 row">
                                            <div class="mb-3 row">
                                                <asp:Label ID="lblTurnoLavandero" runat="server" Text="Turno:" CssClass="col-sm-2 col-form-label" />
                                                <div class="col-sm-8">
                                                    <div class="form-check form-check-inline">
                                                        <asp:RadioButton ID="rbTurnoMañanaLavandero" runat="server" Text="" GroupName="TurnoLavandero" Checked="true" CssClass="form-check-input" />
                                                        <label class="form-check-label" for="rbTurnoMañanaLavandero" runat="server">Mañana</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <asp:RadioButton ID="rbTurnoTardeLavandero" runat="server" Text="" GroupName="TurnoLavandero" CssClass="form-check-input" />
                                                        <label class="form-check-label" for="rbTurnoTardeLavandero" runat="server">Tarde</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <asp:RadioButton ID="rbTurnoNocheLavandero" runat="server" Text="" GroupName="TurnoLavandero" CssClass="form-check-input" />
                                                        <label class="form-check-label" for="rbTurnoNocheLavandero" runat="server">Noche</label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="mb-3 row">
                                                <asp:Label ID="lblRiesgoLavandero" runat="server" Text="Riesgo:" CssClass="col-sm-2 col-form-label" />
                                                <div class="col-sm-8">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" id="rbSiRiesgoLavandero" runat="server" value="V">
                                                        <label class="form-check-label" for="rbMasculino" runat="server">Sí</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" id="rbNoRiesgoLavandero" runat="server" value="F">
                                                        <label class="form-check-label" for="rbFemenino" runat="server">No</label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-sm-8">
                                                Fecha Contratacion
                                            <input type="date" id="dtpFechaContratacionLavandero" runat="server">
                                            </div>
                                        </div>

                                        <div class="col mt-4 text-end">
                                            <asp:LinkButton ID="lbConfirmarRegistroLavandero" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid  fa-check pe-2'></i> Confirmar" OnClick="btnConfirmarRegistroLavandero" />
                                        </div>
                                        <div class="row text-end">
                                            <div class="col-md-12">
                                                <asp:Label ID="lblMensajeErrorDniLavandero" Style="color: red;" runat="server" Text=""></asp:Label>
                                            </div>
                                        </div>
                                        <div class="row text-end">
                                            <div class="col-md-12">
                                                <asp:Label ID="lblMensajeErrorCorreoLavandero" Style="color: red;" runat="server" Text=""></asp:Label>
                                            </div>
                                        </div>
                                        <div class="row text-end">
                                            <div class="col-md-12">
                                                <asp:Label ID="lblMensajeErrorCelularLavandero" Style="color: red;" runat="server" Text=""></asp:Label>
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
