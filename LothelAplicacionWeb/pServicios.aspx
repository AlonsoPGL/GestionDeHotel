<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="pServicios.aspx.cs" Inherits="LothelAplicacionWeb.pServicios" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyUser" runat="server">
    <div class="contenedor">
        <img src ="https://escuelaelbs.com/wp-content/uploads/Servicios-de-un-hotel.png"
            class ="MNImagenTitulo"/>
        <h2 class ="MNTitulSuperpuesto">Servicios del Hotel</h2>
        <p class ="MNTituloTextoSuperpuesto">Siempre atentos para servirte en cualquier momento</p>
    </div>
    <br />

    <!-- CARTAS DE SERVICIOS -->
    <div class="MNListaServicio">
        <div class="card MNmargen" style="width: 25rem; height: 31rem; background-color: bisque">
            <img class="card-img-top" src="https://blog.hubspot.es/hubfs/media/ventaminorista.jpeg" alt="imgServicioVentaDeArticulos">
            <div class="card-body">
                <h5 class="card-title">Servicio de Venta de Artículos</h5>
                <p class="card-text">Para lo que sea que necesites, en todo momento estamos equipados para brindarte aquello que requieres</p>
                <a href="pVentas.aspx" class="btn" style="background-color: #d9a13c; color: floralwhite">Enterate más</a>
            </div>
        </div>
        <div class="card MNmargen" style="width: 25rem; height: 31rem; background-color: bisque">
            <img class="card-img-top" src="https://www.beautymarketamerica.com/fotos/17365_notbmae1grande.jpg" alt="imgServicioMasajes">
            <div class="card-body">
                <h5 class="card-title">Servicio de Masajes</h5>
                <p class="card-text">El formar parte de tu relajo y placer es parte de nuestra misión. Tómate el tiempo que requieras y disfruta de un momento tranquilo con nosotros</p>
                <a href="pSolicitarServicio.aspx" class="btn" style="background-color: #d9a13c; color: floralwhite">Enterate más</a>
            </div>
        </div>
        <div class="card MNmargen" style="width: 25rem; height: 31rem; background-color: bisque">
            <img class="card-img-top" src="https://www.tintoreriaylavanderia.com/images/nueva_TL/tintoreria/tintodehotel.jpg" alt="imgServicioDeLavanderia">
            <div class="card-body">
                <h5 class="card-title">Servicio de Lavandería</h5>
                <p class="card-text">Buscamos que no haya motivos para irte del lugar. No te preocupes por tus prendas y enfocate en disfrutar todo lo que te ofrecemos, sin miedo a nada</p>
                <a href="pSolicitarServicio.aspx" class="btn" style="background-color: #d9a13c; color: floralwhite">Enterate más</a>
            </div>
        </div>
        <div class="card MNmargen" style="width: 25rem; height: 31rem; background-color: bisque">
            <img class="card-img-top" src="https://i.blogs.es/410bab/danielle-cerullo-cqfnt66ttzm-unsplash/1366_2000.jpeg" alt="imgServicioDeGimnasio">
            <div class="card-body">
                <h5 class="card-title">Gimnasio del hotel</h5>
                <p class="card-text">Te acompañamos hacia todas tus metas. Ejercítate en nuestras modernas y amplias instalaciones y que nada sea una excusa en tus esfuerzos por conseguir aquello que anhelas</p>
                <a href="#" class="btn" style="background-color: #d9a13c; color: floralwhite">Enterate más</a>
            </div>
        </div>
    </div>
    <br />
    <h2 style="text-align: center;">Nuestro Hotel esta capacitado las 24 horas para brindarte una reserva con todas las facilidades que requieras</h2>
    
    
</asp:Content>