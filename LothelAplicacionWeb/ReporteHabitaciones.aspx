<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="ReporteHabitaciones.aspx.cs" Inherits="LothelAplicacionWeb.ReporteHabitaciones" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">

        
       <div class="col-lg-12">
          <!-- Contenido del cuerpo principal aquí -->
            <h2 class="h2Nuevo" style="margin-top: 2em;" >Reporte de Habitaciones</h2><!--style="margin-top: 2em;"-->

        <div class="container row">




            <div class="row mt-4">
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body">
                            <i class="fa-solid fa-calendar-days"></i>
                            <h5 class="card-title align-middle"><strong>Fecha desde</strong></h5>
                            <input class="form-control align-middle" type="date" id="detFechaDesdeDescarga" runat="server">
                            <asp:Label ID="lblMensajeError" Style="color: red;" runat="server" Text=""></asp:Label>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body">
                            <i class="fa-solid fa-calendar-days"></i>
                            <h5 class="card-title align-middle"><strong>Fecha hasta</strong></h5>
                            <input class="form-control align-middle" type="date" id="detFechaHastaDescarga" runat="server">
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                            <i class="fa-solid fa-bed"></i>
                            <h5 class="card-title align-middle"><strong>Tipo de Habitaciones:</strong></h5>
                            <select class="form-control align-middle" id="TipoDeHabitacionReporte" runat="server">
                                <option value="all">Todas</option>
                                <option value="familiar">Familiar</option>
                                <option value="matrimonial">Matrimonial</option>
                                <option value="simple">Simple</option>
                            </select>
                        </div>
                    </div>
                </div>


                <div class="col-md-1 align-self-center">
                    <asp:LinkButton ID="btnBuscarFecha" class="btn btn-success btn-lg-custom align-middle" runat="server" Onclick="btnBuscarFecha_Click">
                        <i class="fas fa-search"></i>
                        Buscar
                    </asp:LinkButton>
                </div>
                <div class="col-md-1 align-self-center">
                    <asp:LinkButton ID="btnPdf" class="btn btn-success btn-lg-custom align-middle" runat="server" Onclick="btnPdf_Click" >
                        <i class="fas fa-file"></i>
                        GenerarPDF
                    </asp:LinkButton>
                </div>
            </div>  
                
            <div class="row mt-4">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <asp:GridView ID="gvHabitacionesReporte" runat="server" AutoGenerateColumns="false"   CssClass="table table-hover table-responsive table-striped" >
                                <Columns>
                                    <asp:BoundField HeaderText="Id Huesped" DataField="idHabitacion" />
                                    <asp:BoundField HeaderText="piso" DataField="piso" />
                                    <asp:BoundField HeaderText="Numero de camas " DataField="numeroDeCamas" />
                                    <asp:BoundField HeaderText="Descripcion " DataField="descripcion" />
                                    <asp:BoundField HeaderText="Precio " DataField="precio" />
                                    
                                </Columns>
                            </asp:GridView>
                        </div>
                    </div>
                </div>
            </div>
 

    


</asp:Content>
