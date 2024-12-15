<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="pContactenos.aspx.cs" Inherits="LothelAplicacionWeb.pContactenos" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
    
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="bodyUser" runat="server" >
    <meta charset="UTF-8">
    <link rel="stylesheet" href="Content/sites.css">
    
    <!--Icon-Font-->
   <script src="https://kit.fontawesome.com/eb496ab1a0.js" crossorigin="anonymous"></script>
   <div style="margin-top:20px;" class="px-5 form-container" >
    <div class="row">
        <div class="col-md-6">
            <div class="info-form text-md-rigth ">
                <h2 class="info-heading">Y tú</h2>
                <h2 class="info-heading">¿por qué aún no</h2>
                <h2 class="info-heading"> nos has contactado?</h2>
                <p class="info-subheading">Más información:</p>
                <p class="item_contacto"><i class="fa fa-phone"></i> Teléfono: 123-456-789</p>
                <p class="item_contacto"><i class="fa fa-hotel"></i> Hotel: +51 903544864</p>
                <p class="item_contacto"><i class="fa fa-envelope"></i> a20203869@pucp.edu.pe</p>
                <p class="item_contacto"><i class="fa fa-map-marked"></i> Dirección: Av. General Marcelo Peter Sarmiento Ricaldi</p>
                <p class="item_contacto"><i class="fa fa-map-marker"></i> Lima, Perú</p>
            </div>
        </div>
        <div class="col-md-6  text-md-right ">
            <h1 class="contactenos">Contáctenos</h1>
            <p class="solicito">Por favor, complete el siguiente formulario para ponerse en contacto con nosotros:</p>
            <asp:Label ID="lblMessage" runat="server" Text="" CssClass="text-success"></asp:Label>
            <asp:Label ID="lblError" runat="server" Text="" CssClass="text-danger"></asp:Label>

                <asp:TextBox ID="txtName" runat="server" CssClass="form-control" placeholder="Nombre..." required></asp:TextBox>
                <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control mt-3" placeholder="Correo electrónico..." type="email" required></asp:TextBox>
                 <asp:TextBox ID="txtxPhone" runat="server" CssClass="form-control mt-3" placeholder="telefono..." type="phone" required></asp:TextBox>
                <asp:TextBox ID="txtMessage" runat="server" CssClass="form-control mt-3" placeholder="Mensaje..." type="text"  TextMode="MultiLine" Rows="5" required></asp:TextBox>
                <asp:Button ID="btnSubmit" runat="server" Text="Enviar" CssClass="btn btn-outline mt-3 btn-lg btn-enviar" style="background-color: saddlebrown; color:floralwhite;"  />

        </div>
    </div>
</div>
<hr />
<div class="mapa">
    <h1 class="explore">A un paso de ti</h1>
    <div class="map-container">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2839.6908102036987!2d-77.07682062549907!3d-12.070796083855736!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9105c912d40840a5%3A0xd7a0bfb797e5862e!2sPontificia%20Universidad%20Cat%C3%B3lica%20del%20Per%C3%BA!5e0!3m2!1ses!2spe!4v1715061743565!5m2!1ses!2spe" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        
    </div>
    <div class="footer">
        <div class="social-icons">
            <!-- Agrega aquí los iconos de tus redes sociales, por ejemplo: -->
            <a href="#"><i class="fab fa-facebook-f"></i></a>
            <a href="#"><i class="fab fa-twitter"></i></a>
            <a href="#"><i class="fab fa-instagram"></i></a>
        </div>
        <div class="footer-content">
            <div class="footer-links">
                <a href="#">Contáctanos</a>
                <a href="#">Libro de Reclamaciones</a>
                <a href="#">Política de Privacidad</a>
                <a href="#">Corporativo</a>
            </div>
            <div class="copyright">
                © 2024 lothel | Todos los derechos reservados. Diseñado por:Mi
            </div>
        </div>
    </div>
</div>


</asp:Content>