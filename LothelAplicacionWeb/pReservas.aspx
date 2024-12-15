<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="pReservas.aspx.cs" Inherits="LothelAplicacionWeb.pReservas" EnableEventValidation="false"%>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
    
    <script src="Scripts/LothelScripts/pReservaScript.js"></script>

</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyUser" runat="server">
    
        <div>
            <div>
                <div class="d-flex flex-row flex-shrink-0 p-3 text-white offcanvas-md offcanvas-start">
                    <div class="col-md-2 p-2">
                        <div class="card">
                            <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#modalHuesped">
                                <div class="card-body">
                                    <i class="fa-solid fa-users"></i>
                                    <h5 class="card-title"><strong>Huéspedes</strong></h5>
                                    <asp:TextBox ID="numHuesped" CssClass="border-0" style="margin-right:-5%; padding: 1px; width: 100px" Enabled="false" runat="server"></asp:TextBox>
                                </div>
                            </button>
                            <!--MODALHUESPED-->
                           <div class="modal fade" id="modalHuesped" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                               <div class="modal-dialog" role="document">
                                   <div class="modal-content align-content-center">
                                       <div class="col modal-header" style="padding: 15px 20px;">
                                           <h5 class="modal-title" id="exampleModalLabel">Ingrese numero de huespedes</h5>
                                           <button type="button" class="close btn btn-outline" data-bs-dismiss="modal" aria-label="Close">
                                               <span aria-hidden="true">&times;</span>
                                           </button>
                                       </div>
                                       <div class="modal-body" style="padding: 40px 50px;">
                                           <div class="form-group" style="padding: 15px 20px;">
                                               <label for="usrname"><span class=""></span>Numero de huespedes</label>
                                               <asp:TextBox ID="numHuespedEscoger" class="form-control" runat="server"></asp:TextBox>
                                               <asp:Label ID="errorMensaje" runat="server" Text="" ForeColor="Red"></asp:Label>
                                           </div>
                                           <div style="padding: 15px 20px;">
                                               <asp:Button ID="Button1" class="btn btn-success btn-block" runat="server" Text="Ingresar" OnClientClick="return validarCantHuespedes();" />
                                           </div>
                                       </div>
                                       <div class="modal-footer">
                                           <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                       </div>
                                   </div>
                               </div>
                           </div>
                                <script>
                                    function validarCantHuespedes() {
                                        var numHuespedEscoger = document.getElementById('<%= numHuespedEscoger.ClientID %>').value;
                                        if (numHuespedEscoger.trim() === "") {
                                            document.getElementById('<%= errorMensaje.ClientID %>').innerText = "Debe ingresar un número de huéspedes";
                                            return false;
                                        } else if (!/^\d+$/.test(numHuespedEscoger)) {
                                            document.getElementById('<%= errorMensaje.ClientID %>').innerText = "Ingrese solo números";
                                                return false;
                                            } else if (parseInt(numHuespedEscoger) <= 0) {
                                                document.getElementById('<%= errorMensaje.ClientID %>').innerText = "El número de huéspedes debe ser mayor a 0";
                                                return false;
                                            } else {
                                                document.getElementById('<%= errorMensaje.ClientID %>').innerText = "";
                                            return true;
                                        }
                                    }
                                </script>
                        </div>
                    </div>
                    <div class="col-md-3 p-2">
                        <div class="card">
                            <div class="card-body">
                                <i class="fa-solid fa-calendar-days"></i>
                                <h5 class="card-title"><strong>Fecha de entrada</strong></h5>
                                <input class="form-control " type="date" id="dtpFechaInicioReserva" runat="server">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2 p-2">
                        <div class="card">
                            <div class="card-body">
                                <i class="fa-solid fa-calendar-days"></i>
                                <h5 class="card-title"><strong>Fecha de salida</strong></h5>
                                <input class="form-control" type="date" id="dtpFechaSalidaReserva" runat="server">
                            </div>
                        </div>
                    </div> 
                    

                    


                </div>
            </div>

            <div class="text-center mb-2">
                <asp:Label ID="lblMensajeError" Style="color: red;" runat="server" Text=""></asp:Label>
            </div>
            <h3><strong class="" style="color: darkgoldenrod">Seleccione una habitacion:</strong></h3>
            <div class="d-flex justify-content-sm-between flex-wrap w-auto nav-item">
                <div class="container-fluid">
                    <h1 class="nav-link dropdown-toggle" href="#" id="habitacionesDropdown" data-bs-toggle="dropdown" aria-expanded="false">Ver resultados por habitaciones</h1>
                    <nav id="BarraDeNavegacionPrincipal" class="navbar navbar-expand-lg navbar-fixed-top colorNavBar" runat="server">
                        <div class="offcanvas-body">
                            <ul class="navbar-nav justify-content-center flex-grow-1 pe-3">
                                <li id="lothelNavBar" runat="server" class="nav-item">
                                    <asp:Button ID="btnReservaSimple" class="nav-link mx-lg-4" runat="server" Text="Simple" onclick="seleccionarSimpleClick" /></li>
                                <li id="eventosNavBar" runat="server" class="nav-item">
                                    <asp:Button ID="btnReservaMatrimonial" class="nav-link mx-lg-4" runat="server" Text="Matrimonial" onclick="seleccionarMatrimonClick" /></li>
                                <li id="contactenosNavBar" runat="server" class="nav-item">
                                    <asp:Button ID="btnReservaFamiliar" class="nav-link mx-lg-4" runat="server" Text="Familiar" onclick="seleccionarFamilarClick" />
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
            <div>
                <asp:Repeater ID="parentRepeater" runat="server">
                    <ItemTemplate>
                        <div>
                            <div class="flex-lg-wrap d-flex flex-row offcanvas-md ">
                                <div class="card col-md-3 col-md-2 ">
                                    <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#imagenModal">
                                        <img src="https://controlcenter-p1.synxis.com/hotel/60550/images/room/atelierkingtwinbeds1.jpg" width="500" height="350" />
                                    </button>
                                </div>
                                <div class="modal fade" id="imagenModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered modal-xl">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <img src="https://controlcenter-p1.synxis.com/hotel/60550/images/room/atelierkingtwinbeds1.jpg" class="img-fluid" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card p-4 col-md-3  col-md-9 ">
                                    <div>
                                        <h2 style="font-family: Comic"><strong><%# DataBinder.Eval(Container.DataItem, "titulo") %></strong></h2>
                                        <div>
                                            <span style="color: red">Solo quedan <%# DataBinder.Eval(Container.DataItem, "stock") %> habitaciones</span>
                                        </div>
                                        <div>
                                            <span>Huespedes <%# DataBinder.Eval(Container.DataItem, "cantHuespedes") %></span>
                                        </div>
                                        <div>
                                            <spam><%# DataBinder.Eval(Container.DataItem, "descripcion") %> </spam>
                                        </div>
                                        <div>
                                            <p></p>
                                            <a href="#" style="color: goldenrod"><strong>Detalles de la habitacion</strong></a>
                                        </div>
                                        <hr />
                                        <div>
                                            <hr />
                                            <h4 style="font-size: 40px"><strong><%# DataBinder.Eval(Container.DataItem, "precio") %> US$</strong></h4>
                                            <h6>Por noche</h6>
                                            <h6>Impuestos y tasas excluidas</h6>
                                            <asp:Button ID="idBotonReservar" Style="background-color: darkgoldenrod; color: white; font-size: 20px; border: 2px solid darkgoldenrod; padding: 10px 20px;" runat="server" Text="RESERVAR AHORA" OnClick="btnReservar_Click" CommandArgument='<%# Eval("idHabitacion") %>' OnClientClick="return validarFechaReserva();" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </ItemTemplate>
                </asp:Repeater>
            </div>
            <script type="text/javascript">
                function validarFechaReserva() {
                    var fechaInicio = document.getElementById('<%= dtpFechaInicioReserva.ClientID %>').value;
                           var fechaSalida = document.getElementById('<%= dtpFechaSalidaReserva.ClientID %>').value;
                           var numHuesped = document.getElementById('<%= numHuesped.ClientID %>').value;

                    if (fechaInicio === "" || fechaSalida === "" || numHuesped === "") {
                        alert("Primero debe llenar los campos de fechas y número de huéspedes.");
                        return false;
                    }

                    return true;
                }
            </script>
        </div>
    <div class="modal" id="modal-PagoReserva">
        <div class="modal-dialog modal-xl d-flex justify-content-center align-items-center" style="width: 900px">
            <div class="modal-content">
                <div class="modal-header d-flex justify-content-between px-3 py-1">
                    <h5 class="modal-title">Realización del Pago</h5>
                    <button type="button" class="btn-close float-end" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <!-- Font Awesome CDN -->
                            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
                            <style>
                                #modal-PagoReserva * {
                                    margin: 0;
                                    padding: 0;
                                    box-sizing: border-box;
                                }

                                #modal-PagoReserva .container {
                                    width: 100%;
                                    max-width: 600px;
                                    border-radius: 8px;
                                    padding: 40px;
                                    box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.1), 0 5px 12px -2px rgba(0, 0, 0, 0.1), 0 18px 36px -6px rgba(0, 0, 0, 0.1);
                                    margin: auto;
                                }

                                #modal-PagoReserva .title {
                                    font-size: 20px;
                                    font-family: Arial, Helvetica, sans-serif;
                                    text-align: center;
                                }

                                #modal-PagoReserva form input {
                                    display: none;
                                }

                                #modal-PagoReserva form .category {
                                    margin-top: 10px;
                                    padding-top: 20px;
                                    display: grid;
                                    grid-template-columns: repeat(2, 1fr);
                                    grid-gap: 15px;
                                }

                                #modal-PagoReserva .category label {
                                    height: 145px;
                                    padding: 20px;
                                    box-shadow: 0px 0px 0px 1px rgba(0, 0, 0, 0.2);
                                    display: flex;
                                    justify-content: center;
                                    align-items: center;
                                    cursor: pointer;
                                    border-radius: 5px;
                                    position: relative;
                                    transition: box-shadow 0.3s ease;
                                }

                                #modal-PagoReserva #visa:checked ~ .category .visaMethod,
                                #modal-PagoReserva #mastercard:checked ~ .category .mastercardMethod,
                                #modal-PagoReserva #paypal:checked ~ .category .paypalMethod,
                                #modal-PagoReserva #AMEX:checked ~ .category .amexMethod {
                                    box-shadow: 0px 0px 0px 1px #6064b6;
                                }

                                    #modal-PagoReserva #visa:checked ~ .category .visaMethod .check,
                                    #modal-PagoReserva #mastercard:checked ~ .category .mastercardMethod .check,
                                    #modal-PagoReserva #paypal:checked ~ .category .paypalMethod .check,
                                    #modal-PagoReserva #AMEX:checked ~ .category .amexMethod .check {
                                        display: block;
                                    }

                                #modal-PagoReserva label .imgName {
                                    display: flex;
                                    justify-content: center;
                                    align-items: center;
                                    flex-wrap: wrap;
                                    flex-direction: column;
                                    gap: 10px;
                                }

                                #modal-PagoReserva .imgName span {
                                    font-family: Arial, Helvetica, sans-serif;
                                    position: absolute;
                                    top: 72%;
                                    transform: translateY(-72%);
                                }

                                #modal-PagoReserva .imgName .imgContainer {
                                    width: 50px;
                                    display: flex;
                                    justify-content: center;
                                    align-items: center;
                                    position: absolute;
                                    top: 35%;
                                    transform: translateY(-35%);
                                }

                                #modal-PagoReserva img {
                                    width: 50px;
                                    height: auto;
                                }

                                #modal-PagoReserva .visa img {
                                    width: 80px;
                                }

                                #modal-PagoReserva .mastercard img {
                                    width: 65px;
                                }

                                #modal-PagoReserva .paypal img {
                                    width: 80px;
                                }

                                #modal-PagoReserva .AMEX img {
                                    width: 50px;
                                }

                                #modal-PagoReserva .check {
                                    display: none;
                                    position: absolute;
                                    top: -4px;
                                    right: -4px;
                                }

                                    #modal-PagoReserva .check i {
                                        font-size: 18px;
                                    }
                            </style>
                            <div class="container">
                                <div class="title">
                                    <h4>Select a <span style="color: #6064b6">Payment</span> method</h4>
                                </div>
                                <form action="#">
                                    <input type="radio" name="payment" id="visa">
                                    <input type="radio" name="payment" id="mastercard">
                                    <input type="radio" name="payment" id="paypal">
                                    <input type="radio" name="payment" id="AMEX">
                                    <div class="category" style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
                                        <label for="visa" class="visaMethod">
                                            <div class="imgName">
                                                <div class="imgContainer visa">
                                                    <img src="https://i.ibb.co/vjQCN4y/Visa-Card.png" alt="Visa">
                                                </div>
                                                <span class="name">VISA</span>
                                            </div>
                                            <span class="check"><i class="fa-solid fa-circle-check"></i></span>
                                        </label>

                                        <label for="mastercard" class="mastercardMethod">
                                            <div class="imgName">
                                                <div class="imgContainer mastercard">
                                                    <img src="https://i.ibb.co/vdbBkgT/mastercard.jpg" alt="Mastercard">
                                                </div>
                                                <span class="name">Mastercard</span>
                                            </div>
                                            <span class="check"><i class="fa-solid fa-circle-check"></i></span>
                                        </label>

                                        <label for="paypal" class="paypalMethod">
                                            <div class="imgName">
                                                <div class="imgContainer paypal">
                                                    <img src="https://i.ibb.co/KVF3mr1/paypal.png" alt="Paypal">
                                                </div>
                                                <span class="name">Paypal</span>
                                            </div>
                                            <span class="check"><i class="fa-solid fa-circle-check"></i></span>
                                        </label>

                                        <label for="AMEX" class="amexMethod" style="display: flex">
                                            <div class="imgName">
                                                <div class="imgContainer AMEX">
                                                    <img src="https://i.ibb.co/wQnrX86/American-Express.jpg" alt="AMEX">
                                                </div>
                                                <span class="name">AMEX</span>
                                            </div>
                                            <span class="check"><i class="fa-solid fa-circle-check"></i></span>
                                        </label>
                                    </div>
                                    <div class="guest-payment_container modal-container">
                                        <hr />
                                        <fieldset>
                                            <legend>
                                                <h2 class="app_heading1"><span>Información de pago</span></h2>
                                            </legend>

                                            <div class="payment-select-fop_creditCardsContainer">
                                                <div class="guest-payment-create_paymentSelection">

                                                    <div class="guest-payment-create_cardNumberField input-field_container input-field_withIconLeft" data-error="false" data-warning="false">
                                                        <asp:Label runat="server" AssociatedControlID="cardnumber" CssClass="input-field_label">Número de tarjeta</asp:Label>
                                                        <asp:TextBox runat="server" ID="cardnumber" TextMode="SingleLine" MaxLength="16" placeholder="" aria-label="Número de tarjeta" CssClass="form-control"></asp:TextBox>
                                                    </div>
                                                    <div class="guest-payment-create_expDateField input-field_container" data-error="false" data-warning="false">
                                                        <asp:Label runat="server" AssociatedControlID="expDate" CssClass="input-field_label">Fecha de vencimiento (MM/AA)</asp:Label>
                                                        <asp:TextBox runat="server" ID="expDate" TextMode="SingleLine" MaxLength="5" placeholder="" aria-label="Fecha de vencimiento (MM/AA)" CssClass="form-control"></asp:TextBox>
                                                    </div>
                                                    <div class="payment-cvv-field_cvvField input-field_container input-field_withIconRight" data-error="false" data-warning="false">
                                                        <asp:Label runat="server" AssociatedControlID="cvv" CssClass="input-field_label">CVV</asp:Label>
                                                        <asp:TextBox runat="server" ID="cvv" TextMode="SingleLine" MaxLength="3" placeholder="" aria-label="CVV" CssClass="form-control"></asp:TextBox>
                                                    </div>
                                                    <div class="guest-payment-create_nameField input-field_container" data-error="false" data-warning="false">
                                                        <asp:Label runat="server" AssociatedControlID="ccname" CssClass="input-field_label">Nombre del titular de la tarjeta</asp:Label>
                                                        <asp:TextBox runat="server" ID="ccname" TextMode="SingleLine" MaxLength="60" placeholder="" aria-label="Nombre del titular de la tarjeta" CssClass="form-control"></asp:TextBox>
                                                    </div>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </div>

                                    <div class="guest-info_additionalDetails modal-container">
                                        <hr />
                                        <h2 class="app_heading1"><span>Comentarios </span></h2>
                                        <div class="guest-profile-preferences_container">
                                            <div class="guest-profile-preferences_commentArea input-field_container" data-error="false" data-warning="false">
                                                <asp:Label runat="server" AssociatedControlID="commentArea" CssClass="input-field_label input-field_textAreaLabel">(edad de niños, reserva de cena, requisitos alimenticios, mascotas…)</asp:Label>
                                                <asp:TextBox runat="server" ID="commentArea" MaxLength="200" TextMode="MultiLine" placeholder="" Rows="4" aria-label="Tenga en cuenta sus solicitudes o necesidades especiales (máximo 200 caracteres)" CssClass="form-control" Style="padding-top: 10px;"></asp:TextBox>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer clearfix mt-2">
                                        <asp:Label ID="errorPagarNoLogueado" runat="server" Text="" ForeColor="Red"></asp:Label>
                                        <asp:Button ID="btnCancelar" runat="server" Text="CANCELAR" CssClass="float-start btn btn-secondary" />
                                        <asp:Button ID="btnPagar" runat="server" Text="PAGAR"
                                            CssClass="float-end btn btn-primary" OnClick="btnPagar_Click" />
                                    </div>
                                   
                                </form>
                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>
</asp:Content>