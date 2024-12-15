<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="pRecepcionista.aspx.cs" Inherits="LothelAplicacionWeb.pRecepcionista" %>

<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="bodyUser" runat="server">
    <style>
        .profile-container {
            display: inline-block;
            padding: 20px;
            border-radius: 10px;
            background-color: #d9a13c;
        }

            .profile-container .d-flex {
                color: floralwhite; /* Color de texto blanco */
            }
    </style>

    <div>

        <div id="destinoRegistro" class="text-center container">
            <br />
            <div class="align-content-between">
                <a href="#destinoRegistro" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-user"></i></a>
                <a href="#destinoServicios" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-file-alt"></i></a>
            </div>
            <p class="fontEvento">Registro de nuevo Huesped</p>
            <hr />
        </div>




        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-5">


                    <!-- TextBoxes para llenar los datos del huesped -->

                    <div class="row text-center">
                        <div class="col-md-6">
                            <asp:Label ID="Label3" runat="server" Text="Nombre: " CssClass="me-2" />
                            <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" Enabled="true"></asp:TextBox>
                        </div>

                        <div class="col-md-6">
                            <asp:Label ID="Label1" runat="server" Text="DNI: " CssClass="me-2" />
                            <asp:TextBox ID="txtDNI" runat="server" CssClass="form-control" Enabled="true"></asp:TextBox>
                        </div>
                    </div>

                    <div class="row text-center">
                        <div class="col-md-6">
                            <asp:Label ID="Label9" runat="server" Text="Apellido Paterno: " CssClass="me-2" />
                            <asp:TextBox ID="txtApellidoPaterno" runat="server" CssClass="form-control" Enabled="true"></asp:TextBox>
                        </div>
                        <div class="col-md-6">
                            <asp:Label ID="Label8" runat="server" Text="Apellido Materno: " CssClass="me-2" />
                            <asp:TextBox ID="txtApellidoMaterno" runat="server" CssClass="form-control" Enabled="true"></asp:TextBox>
                        </div>
                    </div>

                    <div class="row text-center">

                        <div class="col-md-6">
                            <asp:Label ID="Label7" runat="server" Text="Celular: " CssClass="me-2" />
                            <asp:TextBox ID="txtCelular" runat="server" CssClass="form-control text-center" Enabled="true"></asp:TextBox>
                        </div>

                        <div class="col-md-6">
                            <asp:Label ID="Label2" runat="server" Text="Correo: " CssClass="me-2" />
                            <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control" Enabled="true"></asp:TextBox>
                        </div>
                    </div>

                    <div class="row text-center mt-2">
                        <div class="col-md-12">
                            <asp:Label ID="Label5" runat="server" Text="Usuario: " CssClass="me-2" />
                            <asp:TextBox ID="txtUserProfile" runat="server" CssClass="form-control text-center" Enabled="true"></asp:TextBox>
                        </div>
                    </div>

                    <div class="row text-center mt-2">
                        <div class="col-md-12">
                            <asp:Label ID="Label6" runat="server" Text="Contraseña: " CssClass="me-2" />
                            <asp:TextBox ID="txtPasswordProfile" runat="server" CssClass="form-control text-center" Enabled="true"></asp:TextBox>
                        </div>
                    </div>

                    <!-- Boton de confirmacion, debe de abrir un modal en caso logre insertar -->
                    <div class="row text-end mt-4">
                        <div class="col-md-12">
                            <asp:LinkButton ID="lblConfirmarRegistro" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid fa-check-circle pe-2'></i> Registrar Huesped" OnClick="btnConfirmarRegistro_OnClick" />
                        </div>
                    </div>

                    <!-- Mensajes de error (Validaciones) -->
                    <div class="row text-end">
                        <div class="col-md-12">
                            <asp:Label ID="lblMensajeErrorApellidos" Style="color: red;" runat="server" Text=""></asp:Label>
                        </div>
                    </div>

                    <div class="row text-end">
                        <div class="col-md-12">
                            <asp:Label ID="lblMensajeErrorDNI" Style="color: red;" runat="server" Text=""></asp:Label>
                        </div>
                    </div>

                    <div class="row text-end">
                        <div class="col-md-12">
                            <asp:Label ID="lblMensajeErrorCelular" Style="color: red;" runat="server" Text=""></asp:Label>
                        </div>
                    </div>
                    <div class="row text-end">
                        <div class="col-md-12">
                            <asp:Label ID="lblMensajeErrorCorreo" Style="color: red;" runat="server" Text=""></asp:Label>
                        </div>
                    </div>


                    <div class="row text-end">
                        <div class="col-md-12">
                            <asp:Label ID="lblMensajeErrorUsuarioContrasena" Style="color: red;" runat="server" Text=""></asp:Label>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- aqui va el cierre del update panel -->


        <!-- Modal que confirma el registro -->
        <div class="modal" id="form-modal-registroExitoso">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">

                        <asp:Label runat="server" ID="lblTituloModalEmpresa" class="modal-title" Text="Registro exitoso"></asp:Label>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <asp:UpdatePanel runat="server">
                            <ContentTemplate>
                                <div class="container">
                                    <div class="row justify-content-center align-items-center">
                                        <div class="col-md-8">
                                            <!-- Ajusta el ancho del modal aquí -->
                                            <div class="row text-center">
                                                <div class="col-md-12">
                                                    <asp:Label ID="lblRegistroExitoso" Style="color: black;" runat="server" Text="El registro se ha realizado con éxito"></asp:Label>
                                                </div>
                                            </div>
                                            <div class="row mt-4 justify-content-center">
                                                <!-- Centra el botón -->
                                                <div class="col-md-4">
                                                    <!-- Ajusta el ancho del botón aquí -->
                                                    <asp:LinkButton ID="lbConfirmarRegistro" runat="server" CssClass="btn btn-success w-100" Text="<i class='fa-solid  fa-check pe-2'></i> Aceptar" OnClick="btnModalRegExitoso_Click" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </ContentTemplate>
                        </asp:UpdatePanel>
                    </div>

                </div>
            </div>
        </div>
        <!-- fin modal-->

        <!-- gestion de productos -->
        <div id="destinoServicios" class="text-center container">
            <br />
            <hr />
            <div class="align-content-between">
                <a href="#destinoRegistro" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-user"></i></a>
                <a href="#destinoServicios" class="icon-link m-3" style="color: #d9a13c; text-decoration: none;"><i class="fas fa-file-alt"></i></a>
            </div>
            <p class="fontEvento">Gestionar Productos</p>
            <hr />
        </div>


        <!--grid view de productos-->
        <div class="container">
            <!--aqui para elegir el tipo de producto a listar-->
            <div class="container row">
                <div class="text-center p-3">
                    <asp:LinkButton ID="btnAlimentos" runat="server" CssClass="btn btn-success" OnClick="btnAlimentos_Click">
                <i class="fas fa-bars"></i> Alimentos
            </asp:LinkButton>
                    <asp:LinkButton ID="btnBebidas" runat="server" CssClass="btn btn-success" OnClick="btnBebidas_Click">
                <i class="fas fa-wine-glass-alt"></i> Bebidas
            </asp:LinkButton>
                    <asp:LinkButton ID="btnCuidadoPersonal" runat="server" CssClass="btn btn-success" OnClick="btnCuidadoPersonal_Click">
                <i class="fas fa-spa"></i> Cuidado Personal
            </asp:LinkButton>
                </div>
            </div>





        </div>
        <!-- fin de grid view de productos -->
    </div>
</asp:Content>
