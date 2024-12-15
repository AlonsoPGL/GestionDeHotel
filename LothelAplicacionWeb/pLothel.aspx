<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="pLothel.aspx.cs" Inherits="LothelAplicacionWeb.pLothel" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyUser" runat="server">


    <!--------------------Frase + Imagenes referenciales (Carousel) https://picsum.photos/1600/800---------------------->

<div id="slider-lothel" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
        <button class="active" data-bs-target="#slider-lothel" data-bs-slide-to="0"></button>
        <button data-bs-target="#slider-lothel" data-bs-slide-to="1"></button>
        <button data-bs-target="#slider-lothel" data-bs-slide-to="2"></button>
    </div>

    <div class="carousel-inner carousel-fade">
        <div class="carousel-item active" data-bs-interval="3000">
            <img class="d-block w-100" src="https://fastly.picsum.photos/id/813/1600/800.jpg?hmac=a4317L6jR0fp8QCo1nFShoDCXFbh8e8AWLSsRJzMqDA" alt="" />

            <div class="carousel-caption d-none d-md-block divSlide">
                <h1 class="text-align:center">El hotel</h1>
                <p>Que no solo es un hotel...</p>
            </div>
        </div>

        <div class="carousel-item active" data-bs-interval="3000">
            <img class="d-block w-100" src="https://fastly.picsum.photos/id/721/1600/800.jpg?hmac=lJ8bemgsMrj6GqQJzSk2imZ0__9cd0nhTqoYdt8SUFU" alt="" />

            <div class="carousel-caption d-none d-md-block divSlide">
                <h1 class="text-align:center">El hotel</h1>
                <p>Que no solo es un hotel...</p>
            </div>
        </div>
        <div class="carousel-item active" data-bs-interval="3000">
            <img class="d-block w-100" src="https://fastly.picsum.photos/id/194/1600/800.jpg?hmac=cHwrfc_tR3DxPeNru1omizfe_ZDNSjSffBFBmQb7u9Y" alt="" />

            <div class="carousel-caption d-none d-md-block divSlide">
                <h1 class="text-align:center">El hotel</h1>
                <p>Que no solo es un hotel...</p>
            </div>
        </div>
    </div>

    <button class="carousel-control-prev" type="button" data-bs-target="#slider-lothel" data-bs-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#slider-lothel" data-bs-slide="next">
        <span class="carousel-control-next-icon"></span>
    </button>
</div>
</asp:Content>