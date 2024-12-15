<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="RegistrarEvento.aspx.cs" Inherits="LothelAplicacionWeb.RegistrarEvento" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">

    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>Registrar Evento</h2>
            </div>
            <div class="card-body">
                <div class="card border">
                    <div class="card-header bg-light">
                        <h5 class="card-title mb-0">Información del Evento</h5>
                    </div>
                    <div class="card-body">
                        <div class="mb-3 row">
                            <asp:Label ID="lblIdEvento" runat="server" Text="ID del Evento:" CssClass="col-sm-3 col-form-label" />
                            <div class="col-sm-3">
                                <asp:TextBox ID="txtIdEvento" runat="server" Enabled="false" CssClass="form-control" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="lblNombreEvento" runat="server" Text="Nombre del Evento:" CssClass="col-sm-3 col-form-label" />
                            <div class="col-sm-4">
                                <asp:TextBox ID="txtNombreEvento" runat="server" CssClass="form-control" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="lblDescripcionEvento" runat="server" Text="Descripcion del Evento:" CssClass="col-sm-3 col-form-label" />
                            <div class="col-sm-4">
                                <asp:TextBox ID="txtDescripcionEvento" runat="server" CssClass="form-control" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="lbl" runat="server" Text="Cantidad Asistentes:" CssClass="col-sm-3 col-form-label" />
                            <div class="col-sm-4">
                                <asp:TextBox ID="txtCantidadAsisEvento" runat="server" CssClass="form-control" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="lblFechaInicioEvento" runat="server" Text="Fecha de inicio:" CssClass="col-sm-3 col-form-label" />
                            <div class="col-sm-4">
                                <input class="form-control" type="date" id="dtpFechaInicioEvento" runat="server">
                            </div>
                        </div>

                    </div>
                </div>


                <div class="card border">
                    <div class="card-header bg-light">
                        <h5 class="card-title mb-0">Detalle de Reserva</h5>
                    </div>
                    <div class="card-body">

                        <div class="mb-3 row">
                            <asp:Label ID="lblFechaReservaEspacio" runat="server" Text="Fecha:" CssClass="col-sm-3 col-form-label" />
                            <div class="col-sm-4">
                                <input class="form-control" type="date" id="dtpFechaReservaEspacio" runat="server">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="lblIDEspacio" runat="server" Text="ID del Espacio:" CssClass="col-sm-3 col-form-label" />
                            <div class="col-sm-3">
                                <asp:TextBox ID="txtIDEspacio" runat="server" Enabled="false" CssClass="form-control" />
                            </div>
                            <asp:Button ID="btnBuscarEspacio" CssClass="btn btn-primary col-sm-2" runat="server" Text="Buscar Espacio" OnClick="btnBuscarEspacio_Click"  />
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="lblNombreEspacio" runat="server" Text="Nombre:" CssClass="col-sm-3 col-form-label" />
                            <div class="col-sm-5">
                                <asp:TextBox ID="txtNombreEspacio" runat="server" Enabled="false" CssClass="form-control" />
                            </div>
                            <asp:Button ID="btnVerDisponibilidadEspacio" CssClass="btn btn-primary col-sm-2" runat="server" Text="Ver disponibilidad"  OnClick="btnVerDisponibilidadEspacio_Click" />
                        </div>

                        <!--ocultar para modificar-->


                        <div class="mb-3 row">
                            <asp:Label ID="lblHoraInicio" runat="server" Text="Hora Inicio:" CssClass="col-sm-3 col-form-label" />
                            <div class="col-sm-4">
                                <input class="form-control" type="time" id="hrIniReservaEspacio" runat="server" onchange="setMinutesToZero(this)">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="lblHoraFin" runat="server" Text="Hora Fin:" CssClass="col-sm-3 col-form-label" />
                            <div class="col-sm-4">
                                <input class="form-control" type="time" id="hrFinReservaEspacio" runat="server" onchange="setMinutesToZero(this)">
                            </div>
                            <div class="col-sm-3">
                                <asp:LinkButton ID="lbAgregarReservaEspacio" CssClass="btn btn-success" runat="server" Text=" Agregar"   OnClick="lbAgregarReservaEspacio_Click"  />
                            </div>
                        </div>

                        <!--fin-->
                        
                        <div class="row">
                            <asp:GridView ID="gvreservaEspacios" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnRowDataBound="gvreservaEspacios_RowDataBound">
                                <Columns>
                                    <asp:BoundField HeaderText="Id" DataField="idReservaEspacio" />
                                    <asp:BoundField HeaderText="Fecha de reserva"  />
                                    <asp:BoundField HeaderText="Hora inicio" DataField="horaInicio" />
                                    <asp:BoundField HeaderText="Hora fin" DataField="horaFin" />
                                    <asp:BoundField HeaderText="Nombre espacio"  />
                                    <asp:TemplateField>
                                        <ItemTemplate>
                                            
                                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" CommandArgument='<%# Eval("idReservaEspacio") %>'  OnClick="btnEliminarReservaEspacio_Click" />
                                        </ItemTemplate>
                                    </asp:TemplateField>
                                </Columns>
                            </asp:GridView>
                        </div>
                        

                    </div>
                </div>
            </div>
            <div class="card-footer clearfix">
                <asp:Button ID="btnGuardar" runat="server" Text="Guardar" CssClass="float-end btn btn-primary" OnClick="btnGuardar_Click" />
                <asp:Button ID="btnRegresar" runat="server" Text="Regresar" CssClass="float-start btn btn-secondary" OnClick="lbRegresarListarEventos_Click" />
                
            </div>
            <!--validaciones-->
            <div class="row text-end">
                <div class="col-md-12">
                    <asp:Label ID="lblErrorEventoReservaEspacio" Style="color: red;" runat="server" Text=""></asp:Label>
                </div>
            </div>
            



        </div>
    </div>

    

    

    
    <div class="modal" id="form-modal-espacio">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Búsqueda de Espacios</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <div class="container row pb-3 pt-3">
                                <div class="row align-items-center">
                                    <div class="col-auto">
                                        <asp:Label CssClass="form-label" runat="server" Text="Ingresar nombre del espacio:"></asp:Label>
                                    </div>
                                    <div class="col-sm-3">
                                        <asp:TextBox CssClass="form-control" ID="txtNombreEspacioModal" runat="server"></asp:TextBox>
                                    </div>
                                    <div class="col-sm-2">
                                        <asp:LinkButton ID="lbBusquedaEspacioModal" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="lbBusquedaEspacioModal_Click" />
                                    </div>
                                </div>
                            </div>
                            <div class="container row">
                                <asp:GridView ID="gvEspacios" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="gvEspacios_PageIndexChanging" >
                                    <Columns>
                                        <asp:BoundField HeaderText="Id del Espacio" DataField="idEspacio" />
                                        <asp:BoundField HeaderText="Nombre del Espacio" DataField="seccion" />
                                        <asp:BoundField HeaderText="Aforo" DataField="aforo" />
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <asp:LinkButton class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="btnSeleccionarEspacioModal_Click" CommandArgument='<%# Eval("idEspacio") %>' />
                                            </ItemTemplate>
                                        </asp:TemplateField>
                                    </Columns>
                                </asp:GridView>
                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>



    <div class="modal" id="form-modal-horasDisponibles">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Ver disponibilidad</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <div class="container row pb-3 pt-3">
                                <div class="row align-items-center">
                                    <div class="col-auto">
                                        <asp:Label CssClass="form-label" runat="server" Text="Nombre del espacio:"></asp:Label>
                                    </div>
                                    <div class="col-sm-5">
                                        <asp:TextBox CssClass="form-control" ID="txtNombreEspacioElegido" runat="server" Enabled="false"></asp:TextBox>
                                    </div>
                                </div>
                            </div>
                            <div class="container row">


                                
                                <asp:Repeater ID="rptAvailableHours" runat="server">
                                    <ItemTemplate>
                                        <button type="button" class='<%# Container.DataItem.ToString().Contains(" NO DISPONIBLE") ? "btn btn-secondary" : "btn btn-primary"%>'>
                                            <%# Container.DataItem.ToString() %>
                                        </button>
                                    </ItemTemplate>
                                </asp:Repeater>




                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>


    <!--setear minutos a 00-->
    <script>
    function setMinutesToZero(inputElement) {
        // Obtiene la hora seleccionada por el usuario
        var selectedTime = inputElement.value;

        // Establece los minutos en "00"
        var adjustedTime = selectedTime.split(":")[0] + ":00";

        // Actualiza el valor del campo de entrada
        inputElement.value = adjustedTime;
    }
    </script>

</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="bodyUser" runat="server">
</asp:Content>
