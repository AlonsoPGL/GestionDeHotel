<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="pHabitaciones.aspx.cs" Inherits="LothelAplicacionWeb.pHabitaciones" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyUser" runat="server">
     <meta charset="UTF-8">

    <link rel="stylesheet" href="Content/sites.css">

    <div class="container">
        <div class="text-center fontEvento">
            <br />
            <p>HABITACIONES</p>
        </div>
        <hr />
        <div id="habitacion1" class="row mt-5 align-items-center habitacion1">
            <div class="col-md-4">
                <img src="images/familiar.jpg" class="img-fluid imagenHabitacion" alt="Imagen 2">
            </div>
            <div class="col-md-8">
                <h1 class="text-center tipoHabitacion">Familiar</h1>
                <ul class="deta">
                    <li class="tituloHabitacion">45 - 60 metros cuadrados</li>
                    <li class="tituloHabitacion">(484 - 646 pies cuadrados)</li>
                  </ul>
                <p class="tituloHabitacion">Las habitaciones familiares son espaciosas y cómodas, ideales para familias grandes o grupos. Cuentan con una variedad de comodidades, como una cama king-size, una cama queen-size y dos camas individuales, lo que garantiza un alojamiento cómodo para todos los miembros de la familia. Además, estas habitaciones suelen incluir áreas de estar separadas y balcones con hermosas vistas panorámicas.</p>
                <ul class="serv">
                    <li>
                        <div class="text-center">
                            <img width="83" height="64" src="https://hotelb.pe/wp-content/uploads/2020/08/wifi-icon.svg" class="attachment-full size-full" alt="" decoding="async">
                            <p>WI-FI</p>
                        </div>
                    </li>
                    <li>
                        <div class="text-center">
                            <img width="43" height="42" src="https://hotelb.pe/wp-content/uploads/2020/08/phone-icon.svg" class="attachment-full size-full" alt="" decoding="async" loading="lazy">
                            <p>LLAMADAS TELEFÓNICAS LOCALES</p>
                        </div>
                    </li>
                    <li>
                        <div class="text-center">
                            <img width="35" height="49" src="https://hotelb.pe/wp-content/uploads/2020/08/mini-bar-icon.svg" class="attachment-full size-full" alt="" decoding="async" loading="lazy">
                            <p>MINIBAR</p>
                        </div>
                    </li>
                    <li>
                        <div class="text-center">
                            <img width="70" height="46" src="https://hotelb.pe/wp-content/uploads/2020/08/tv-icon.svg" class="attachment-full size-full" alt="" decoding="async" loading="lazy">
                            <p>TV PLASMA HD</p>
                        </div>
                    </li>
                    <li>
                        <div class="text-center">
                            <img width="152" height="90" src="https://hotelb.pe/wp-content/uploads/2020/08/ipod-icon.svg" class="attachment-full size-full" alt="" decoding="async" loading="lazy">
                            <p>ESTACIÓN DE MONTAJE PARA IPOD</p>
                        </div>
                    </li>
                </ul>
                  <div class="ct-btndeta text-center">
                    <asp:Button ID="Button3" runat="server" Text="MÁS DETALLES&gt;" class="btn btn-outline btn-enviar" style="background-color: saddlebrown; color:floralwhite;" />
                    <asp:Button ID="btnReservar1" runat="server" Text="RESERVAR AHORA" class="btn btn-outline btn-enviar" style="background-color: saddlebrown; color:floralwhite;" OnClick="btnReservar1_Click" />
                   </div>
            </div>
        </div>
        <hr />
        <div id="habitacion2" class="row align-items-center">
            <div class="col-md-4">
                <img src="images/Matrimonial.jpg" class="img-fluid imagenHabitacion" alt="Imagen 2">
            </div>
            <div class="col-md-8">
                <h1 class="text-center tipoHabitacion">Matrimonial</h1>
                <ul class="deta">
                    <li class="tituloHabitacion">35 - 45 metros cuadrados</li>
                    <li class="tituloHabitacion">(377 - 484 pies cuadrados)</li>
                  </ul>
                <p class="tituloHabitacion">Las habitaciones matrimoniales ofrecen un ambiente acogedor y romántico, perfecto para parejas que desean disfrutar de una escapada juntos. Estas habitaciones suelen contar con una cama king-size o queen-size, ropa de cama de lujo y una decoración elegante. Además, algunas habitaciones matrimoniales pueden incluir balcones privados con vistas panorámicas y baños de lujo con bañeras de hidromasaje.</p>
                <ul class="serv">
                    <li>
                        <div class="text-center">
                            <img width="83" height="64" src="https://hotelb.pe/wp-content/uploads/2020/08/wifi-icon.svg" class="attachment-full size-full" alt="" decoding="async">
                            <p>WI-FI</p>
                        </div>
                    </li>
                    <li>
                        <div class="text-center">
                            <img width="43" height="42" src="https://hotelb.pe/wp-content/uploads/2020/08/phone-icon.svg" class="attachment-full size-full" alt="" decoding="async" loading="lazy">
                            <p>LLAMADAS TELEFÓNICAS LOCALES</p>
                        </div>
                    </li>
                    <li>
                        <div class="text-center">
                            <img width="35" height="49" src="https://hotelb.pe/wp-content/uploads/2020/08/mini-bar-icon.svg" class="attachment-full size-full" alt="" decoding="async" loading="lazy">
                            <p>MINIBAR</p>
                        </div>
                    </li>
                    <li>
                        <div class="text-center">
                            <img width="70" height="46" src="https://hotelb.pe/wp-content/uploads/2020/08/tv-icon.svg" class="attachment-full size-full" alt="" decoding="async" loading="lazy">
                            <p>TV LCD HD</p>
                        </div>
                    </li>
                    <li>
                        <div class="text-center">
                            <img width="152" height="90" src="https://hotelb.pe/wp-content/uploads/2020/08/ipod-icon.svg" class="attachment-full size-full" alt="" decoding="async" loading="lazy">
                            <p>ESTACIÓN DE MONTAJE PARA IPOD</p>
                        </div>
                    </li>
                </ul>
                  <div class="ct-btndeta text-center">
                    <asp:Button ID="Button5" runat="server" Text="MÁS DETALLES&gt;" class="btn btn-outline btn-enviar" style="background-color: saddlebrown; color:floralwhite;" />
                    <asp:Button ID="btnReservar2" runat="server" Text="RESERVAR AHORA" class="btn btn-outline btn-enviar" style="background-color: saddlebrown; color:floralwhite;" OnClick="btnReservar2_Click"/>
                   </div>
            </div>
           
        </div>
        <hr />
        <div id="habitacion3" class="row align-items-center">
            <div class="col-md-4">
                <img src="images/simple.jpg" class="img-fluid imagenHabitacion" alt="Imagen 2">
            </div>
            <div class="col-md-8">
                <h1 class="text-center tipoHabitacion">Simple</h1>
                <ul class="deta">
                    <li class="tituloHabitacion">20 - 25 metros cuadrados</li>
                    <li class="tituloHabitacion">(215 - 270 pies cuadrados)</li>
                  </ul>
                <p class="tituloHabitacion">Las habitaciones simples son perfectas para viajeros individuales o parejas que buscan una estancia cómoda y asequible. Estas habitaciones suelen contar con una cama individual o doble, un espacio de estar compacto y un baño privado. Aunque son más pequeñas en tamaño, ofrecen todas las comodidades necesarias para una estancia agradable, incluyendo conexión Wi-Fi gratuita y televisión por cable..</p>
                <ul class="serv">
                    <li>
                        <div class="text-center">
                            <img width="83" height="64" src="https://hotelb.pe/wp-content/uploads/2020/08/wifi-icon.svg" class="attachment-full size-full" alt="" decoding="async">
                            <p>WI-FI</p>
                        </div>
                    </li>
                    <li>
                        <div class="text-center">
                            <img width="43" height="42" src="https://hotelb.pe/wp-content/uploads/2020/08/phone-icon.svg" class="attachment-full size-full" alt="" decoding="async" loading="lazy">
                            <p>LLAMADAS TELEFÓNICAS LOCALES</p>
                        </div>
                    </li>
                    <li>
                        <div class="text-center">
                            <img width="35" height="49" src="https://hotelb.pe/wp-content/uploads/2020/08/mini-bar-icon.svg" class="attachment-full size-full" alt="" decoding="async" loading="lazy">
                            <p>MINIBAR</p>
                        </div>
                    </li>
                    <li>
                        <div class="text-center">
                            <img width="70" height="46" src="https://hotelb.pe/wp-content/uploads/2020/08/tv-icon.svg" class="attachment-full size-full" alt="" decoding="async" loading="lazy">
                            <p>TV LCD HD</p>
                        </div>
                    </li>
                    <li>
                        <div class="text-center">
                            <img width="152" height="90" src="https://hotelb.pe/wp-content/uploads/2020/08/ipod-icon.svg" class="attachment-full size-full" alt="" decoding="async" loading="lazy">
                            <p>ESTACIÓN DE MONTAJE PARA IPOD</p>
                        </div>
                    </li>
                </ul>
                  <div class="ct-btndeta text-center">
                    <asp:Button ID="Button1" runat="server" Text="MÁS DETALLES&gt;" class="btn btn-outline btn-enviar" style="background-color: saddlebrown; color:floralwhite;" />
                    <asp:Button ID="btnReservar3" runat="server" Text="RESERVAR AHORA" class="btn btn-outline btn-enviar" style="background-color: saddlebrown; color:floralwhite;" OnClick="btnReservar3_Click"/>
                   </div>
                </div>
            </div>
        </div>
        <div class="politicas">
            <h1 class="tituloPolitica">Políticas del Hotel:</h1>
            <ol class="list-policies">
                <li class="itemPolitica"><b>Garantía de reservas:</b>Las reservas deben estar garantizadas con tarjeta de crédito al realizar la reserva.</li>
                <li><b>Política de cancelación:</b>Las fechas restringidas están sujetas a términos y condiciones especiales.</li>
                <li>Check-in a partir de las 15:00h. Salida antes de las 12:00h.</li>
                <li>Somos un hotel 100% libre de humo.</li>
                <li><b>Restricciones de edad</b>Debe ser mayor de 18 años para reservar una habitación.</li>
            </ol>
                <a href="#" class="social-icon"><i class="fab fa-facebook-f" style="color:darkgoldenrod"></i></a>
                <a href="#" class="social-icon"><i class="fab fa-twitter" style="color: darkgoldenrod"></i></a>
                <a href="#" class="social-icon"><i class="fab fa-instagram" style="color: darkgoldenrod"></i></a>
                <p class="copyright">COPYRIGHT © HOTEL B. ALL RIGHTS RESERVED.</p>
        </div>

</asp:Content>