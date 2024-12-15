<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="pEventos.aspx.cs" Inherits="LothelAplicacionWeb.pEventos" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
     <!--------------------search by id=.hidden------------------------>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyUser" runat="server">
    
    <div>
        <!--------------------Foto y encabezado de pagina------------------------>
        <div class="text-center container">
            <br />
            <p class="fontEvento">EVENTOS</p>
                    <hr />
            <img class="imgHead img-fluid" src="https://cache.marriott.com/content/dam/marriott-renditions/MIAES/miaes-pooldeck-weddingceremony-5020-hor-wide.jpg?output-quality=70&interpolation=progressive-bilinear&downsize=1920px:*" />
            <p class="fontElegante">Espacios mágicos</p>
            <hr />
            <p style="font-size:25px; margin-bottom:40px;">Nuestros espacios están diseñados para albergar todo tipo de eventos</p>
        </div>
        
        <!--------------------Lista de Eventos------------------------>
        <div class="listaEvento">
            <div class="evento">
                <img class="imgEvento" src="https://cdn.teleticket.com.pe/images/eventos/pgm015v2_calugalistado.jpg" />
                <div class="descripcionEvento">
                    <p>
                        <strong style="font-size: 12px;">SALA DE CONVENCIONES PISO 1<br />
                        </strong>
                        <strong>JUNTOS PARA TI 2024</strong><br />10 de mayo del 2024
                    </p>
                </div>
            </div>
            <div class="evento">
                <img class="imgEvento" src="https://cdn.teleticket.com.pe/images/eventos/ven038v2_calugalistado.jpg" />
                <div class="descripcionEvento">
                    <p>
                        <strong style="font-size:12px;">TEATRO B<br />
                        </strong>
                        <strong>DANNA LIVE TOUR</strong><br />10 de mayo del 2024
                    </p>
                </div>
            </div>
            <div class="evento">
                <img class="imgEvento" src="https://cdn.teleticket.com.pe/images/eventos/eli024v2_calugalistado.jpg" />
                <div class="descripcionEvento">
                    <p>
                        <strong style="font-size:12px;">PATIO DE COMIDAS PISO 2<br /></strong>
                        <strong>INTERPOL</strong><br />15 de mayo del 2024
                    </p>
                </div>
            </div>
            <div class="evento">
                <img class="imgEvento" src="https://cdn.teleticket.com.pe/images/eventos/fpp006_calugalistado.jpg" />
                <div class="descripcionEvento">
                   <p>
                       <strong style="font-size:12px;">SALON DE BODAS PISO 1<br /></strong>
                       <strong>JUANES TOUR LATAM 2024</strong><br />20 de mayo del 2024
                   </p> 
                </div>
            </div>
            <div class="evento">
                <img class="imgEvento" src="https://cdn.teleticket.com.pe/images/eventos/tro028v4_calugalistado.jpg" />
                <div class="descripcionEvento">
                    <p>
                        <strong style="font-size:12px;">TEATRO C<br /></strong>
                        <strong>GRUPO NICHE EN LOTHEL</strong><br />15 de julio del 2024
                    </p>
                </div>
            </div>
            <div class="evento">
                <button type="button" class="btn btn-outline-transparent" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    <img class="imgEvento" src="https://cdn.teleticket.com.pe/images/eventos/emp055_calugalistado.jpg"/>
                </button>
                <div class="descripcionEvento">
                    <p>
                        <strong style="font-size:12px;">SALON DE RECEPCIONES PISO 5<br /></strong>
                        <strong>37 ANIVERSARIO GRUPO 5</strong><br />10 de junio del 2024
                    </p>
                </div>
            </div>
        </div>

        <!--------------------Modales para evento (ventana emergente)------------------------>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
        </div>
</asp:Content>
