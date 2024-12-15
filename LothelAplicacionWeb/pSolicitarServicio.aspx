<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="pSolicitarServicio.aspx.cs" Inherits="LothelAplicacionWeb.pSolicitarServicio" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="bodyUser" runat="server">
    <asp:Label ID="lblTituloSolicitarServicio" runat="server">
        <h2>Solicitar un Servicio</h2>
    </asp:Label>
    <div class ="mt-3">
    <div class="col mb-3 d-flex justify-content-center">
        <asp:Label ID="lblErrorMensaje" runat="server" Text=" " ForeColor="red"></asp:Label>
    </div>
</div>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <img class="card-img-top" src="https://tecnohotelnews.com/wp-content/uploads/2018/12/shutterstock_422824102.jpg" width="576px" height="374px" alt="Lavandería"> <!--llenar imagen-->
                    <div class="card-body">
                        <h5 class="card-title">Servicio de Lavandería</h5>
                        <p class="card-text">Solicita nuestro servicio de lavandería para mantener tu ropa limpia.</p>
                        <asp:Panel runat="server">
                            <asp:Label runat="server" ID="lblTextSolRopa" Text="Descripción de servicio a solicitar:" />
                            <asp:TextBox ID="txtDescripcionServicio" runat="server" CssClass="form-control"/>
                            <br />
                            <asp:Button ID="btnSolicitarLavanderia" runat="server" CssClass="btn btn-primary" Text="Solicitar" OnClick="SolicitarLavanderia_Click" />
                        </asp:Panel>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <img class="card-img-top" src="https://images-ext-1.discordapp.net/external/a2N5obYsEBdTi0TcclOz5RAwfM7fVNbHuID3Lkd-Qz0/https/elcomercio.pe/resizer/LgcBrlIsfZXC4bJN3b6oPu4Bcq4%3D/980x0/smart/filters%3Aformat%28jpeg%29%3Aquality%2875%29/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/S4DBSGCQVNDKXMCZ5NCWUFQCHA.jpg?format=webp&width=1082&height=635" alt="Masajes">
                    <div class="card-body">
                        <h5 class="card-title">Servicio de Masajes</h5>
                        <p class="card-text">Relájate y disfruta de nuestros servicios de masajes profesionales.</p>
                        <asp:Panel runat="server">
                            <asp:Label runat="server" ID="lblTxtSolMasaje" Text="Ingresar hora de masaje" />
                             <asp:DropDownList ID="ddlHoraMasaje" runat="server" CssClass="form-control"></asp:DropDownList>
                            <br />
                            <asp:Button ID="btnSolicitarMasajes" runat="server" CssClass="btn btn-primary" Text="Solicitar" OnClick="SolicitarMasajes_Click" />
                        </asp:Panel>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal" id="form-modal-carrito">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <asp:Label runat="server" ID="lblTituloSolicitud" class="modal-title" Text="Solicitar Servicio"></asp:Label>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <div class="container row pb-3 pt-3">
                                <div class="row">
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <asp:Label ID="lblElejirReservaPedido" runat="server" Text="Elegir Reserva asociada al pedido:" CssClass="col-form-label" />
                                    </div>
                                    <div class="col">
                                        <asp:DropDownList ID="ddlReservasEnCurso" CssClass="form-select" runat="server"></asp:DropDownList>
                                    </div>
                                </div>
                                <div class="row text-end">
                                    <asp:LinkButton ID="lbRegistrarSolicitud" runat="server"
                                        CssClass="btn btn-success" Text="<i class='fa-solid fa-plus pe-2'></i> Pedir" OnClick="lbRegistrarSolicitud_Click" />
                                </div>
                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
