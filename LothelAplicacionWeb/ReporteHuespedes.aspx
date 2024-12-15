<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="ReporteHuespedes.aspx.cs" Inherits="LothelAplicacionWeb.ReporteHuespedes" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">

    <style>
        /* Custom CSS */
        .h2Nuevo {
            margin-top: 2em;
        }

        .card {
            margin-bottom: 1em;
        }

        .ms-3 {
            margin-left: 1em !important;
        }

        .btn-lg-custom {
            padding: .6rem 1.25rem;
            font-size: 1.2rem;
            border-radius: .3rem;
        }

        .align-middle {
            vertical-align: middle !important;
        }
    </style>
    <div class="container mt-4">
        <div class="row align-items-center">
            <div class="col-lg-6">
                <h2 class="h2Nuevo"><strong>Reporte de Huespedes</strong></h2>
            </div>
            <div class="col-lg-6 text-end">
                <asp:Button ID="btnDescargaHuesped" class="btn btn-success" runat="server" Text="Descargar" OnClick="btnDescargaHuesped_Click"/>
            </div>
        </div>
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
            <div class="col-md-2 align-self-center">
                <asp:Button ID="btnBuscarFecha" class="btn btn-success btn-lg-custom align-middle" runat="server" Text="Buscar" OnClick="btnBuscarFecha_Click"/>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <asp:GridView ID="gvHuespedesReporte" runat="server" AllowPaging="true" PageSize="5" OnPageIndexChanging="gvReporteHuespedes_PageIndexChanging" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnRowDataBound="gvHuespedesReporte_RowDataBound">
                            <Columns>
                                <asp:BoundField HeaderText="Id Huesped" />
                                <asp:BoundField HeaderText="Nombre Completo" />
                                <asp:BoundField HeaderText="DNI" />
                                <asp:BoundField HeaderText="Fecha De Registro" />
                                <asp:BoundField HeaderText="Celular" />
                                <asp:BoundField HeaderText="Correo" />
                                <asp:BoundField HeaderText="Es Vip" />
                            </Columns>
                        </asp:GridView>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
