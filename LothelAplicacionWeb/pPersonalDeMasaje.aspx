<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="pPersonalDeMasaje.aspx.cs" Inherits="LothelAplicacionWeb.pPersonalDeMasaje" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
    <script src="Scripts/LothelScripts/pReservaScript.js"></script>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="bodyUser" runat="server">

     <style>
        /* Estilos específicos para el contenedor principal */
#task-list-container {
    margin-top: 100px;
    /* Agrega el resto de estilos aquí */
}

/* Resto de los estilos aquí, precedidos por #task-list-container */
./* Resto de los estilos aquí, precedidos por #task-list-container */
#task-list-container .widget-subheading {
    color: #858a8e;
    font-size: 10px;
}

#task-list-container .btn-actions-pane-right {
    margin-left: auto;
    white-space: nowrap;
}

#task-list-container .scroll-area-sm {
    height: 288px;
    overflow-x: hidden;
}

#task-list-container .list-group-item {
    position: relative;
    display: block;
    padding: 0.75rem 1.25rem;
    margin-bottom: -1px;
    background-color: #fff;
    border: 1px solid rgba(0, 0, 0, 0.125);
}

#task-list-container .list-group {
    display: flex;
    flex-direction: column;
    padding-left: 0;
    margin-bottom: 0;
}

#task-list-container .todo-indicator {
    position: absolute;
    width: 4px;
    height: 60%;
    border-radius: 0.3rem;
    left: 0.625rem;
    top: 20%;
    opacity: .6;
    transition: opacity .2s;
}

#task-list-container .bg-warning {
    background-color: #f7b924 !important;
}

#task-list-container .widget-content {
    padding: 1rem;
    flex-direction: row;
    align-items: center;
}

#task-list-container .widget-content .widget-content-wrapper {
    display: flex;
    flex: 1;
    position: relative;
    align-items: center;
}

#task-list-container .widget-content .widget-content-right.widget-content-actions {
    visibility: hidden;
    opacity: 0;
    transition: opacity .2s;
}

#task-list-container .widget-content .widget-content-right {
    margin-left: auto;
}

#task-list-container .btn:not(:disabled):not(.disabled) {
    cursor: pointer;
}

#task-list-container .btn {
    position: relative;
    transition: color 0.15s, background-color 0.15s, border-color 0.15s, box-shadow 0.15s;
}

#task-list-container .btn-outline-success {
    color: #3ac47d;
    border-color: #3ac47d;
}

#task-list-container .btn-outline-success:hover {
    color: #fff;
    background-color: #3ac47d;
    border-color: #3ac47d;
}

#task-list-container .btn-primary {
    color: #fff;
    background-color: #3f6ad8;
    border-color: #3f6ad8;
}

#task-list-container .btn {
    position: relative;
    transition: color 0.15s, background-color 0.15s, border-color 0.15s, box-shadow 0.15s;
    outline: none !important;
}

#task-list-container .card-footer {
    background-color: #fff;
}
.specific-sidebar {
    width: 250px;
    background-color: #333;
    color: #fff;
    padding: 20px;
}

.specific-sidebar .profile-picture img {
    width: 100%;
    border-radius: 50%;
    margin-bottom: 20px;
}

.specific-sidebar .sidebar-menu {
    list-style-type: none;
    padding: 0;
}

.specific-sidebar .sidebar-menu li {
    margin-bottom: 10px;
}

.specific-sidebar .sidebar-menu li a {
    color: #fff;
    text-decoration: none;
}

.specific-sidebar .sidebar-menu li a:hover {
    text-decoration: underline;
}
.link_aux {

    color: #FFFFFF8C;
    text-decoration: none !important;
        font-family: 'Montserrat', sans-serif;
    font-size: 20px;
    font-weight: bold;

}

    </style>       


        <div class="d-flex" style="height:750px">
         <div class="container-fluid d-flex p-0 h-100">
     <div id="menuLateral" runat="server" class="order-lg-first" style="background-color: #d9a13c">

         <!--Menu Lateral-->
         <div id="bdSidebar" class="d-flex flex-column flex-shrink-0 p-3 text-white ">
             <!--aqui cambio-->
             <div class="mynav nav nav-pills flex-column mb-auto">
                 <div href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#userMenu" aria-expanded="false" aria-controls="userMenu" style="color: white;">

                     <div class="sidebar-link" style="color: white; text-decoration: none">
                         <i class="fa-solid fa-user m-1 pe-2"></i>
                         <asp:Label ID="lblNombreBarraLateral" runat="server" Text="Label"></asp:Label>
                         <!--<button runat="server" OnClick="ocultarMostrarPerfil">-->

                         <!--</button>-->

                     </div>
                 </div>
             </div>
             <div  style="display: flex; flex-direction: column; align-items: center; text-align: center; margin-top:40px;">
    <img src="https://c1.klipartz.com/pngpicture/823/765/sticker-png-login-icon-system-administrator-user-user-profile-icon-design-avatar-face-head.png" alt="Descripción de la imagen" style="max-width: 100px; border-radius: 50%; border: 2px solid floralwhite;" />
   <h5>Personal De Masaje</h5>
</div>
             <hr>
             <ul class="mynav nav nav-pills flex-column mb-auto">
                 
                 <li class="nav-item mb-1">
                     <asp:LinkButton ID="btnPendientes" runat="server" class="sidebar-link link_aux" OnClick="btnPendientes_Click" Style="color: white;">
     Pedido a Confirmar:
</asp:LinkButton>
                 </li>
                 <li class="nav-item mb-1">
                     <asp:LinkButton ID="btnPorAtender" runat="server" class="sidebar-link link_aux" OnClick="btnPorAtender_Click" Style="color: white;">
    Pedido a Entregar:
                     </asp:LinkButton>
                 </li>

             </ul>
        </div> 
    </div>
 </div>

<div class="col-md-10 " ID="TareasPendientes" runat="server" style="padding-left:20px">
            <h2>Listado de Solicitudes Pendientes:</h2>
            <div class="col-md-12">
                <asp:Label ID="ErrorPendientes" Style="color: #d9a13c;font-family: 'Parisienne', cursive;font-size: 50px;" runat="server" Text=""></asp:Label>
            </div>
            <asp:GridView ID="GridTareasLavandero" runat="server" AutoGenerateColumns="False" AllowPaging="true" PageSize="7" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="GridTareasLavandero_PageIndexChanging" >
                <Columns>
                    <asp:BoundField DataField="idPedido" HeaderText="ID" />
                    <asp:BoundField DataField="numHabitacion" HeaderText="HABITACION" />
                    <asp:BoundField DataField="pisoHabitacion" HeaderText="PISO" />
                    <asp:BoundField DataField="nombreHuesped" HeaderText="NOMBRE" />
                    <asp:TemplateField HeaderText="Detalle:">
                        <ItemTemplate>
                            <asp:LinkButton ID="btnVerDetalleMasajista" runat="server" Text="<i class='fa-solid fa-eye'></i>" OnClick="btnVerDetalleMasajista_Click" CommandArgument='<%# Eval("idPedido") %>' />

                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="CONFIMRAR:">
                        <ItemTemplate>
                            <asp:LinkButton ID="btnConfirmarPedidoLavandero" runat="server" Text=" CONFIRMAR" style="background-color:#d9a13c;border:none"  Class="btn btn-primary fa-solid fa-square-check" CommandArgument='<%# Eval("idPedido") %>' OnClick="btnConfirmarPedidoLavandero_Click"/>
                            
                        </ItemTemplate>
                    </asp:TemplateField>

                </Columns>
            </asp:GridView>
            <hr />


            <div class="modal fade" id="modalDetalleMasaje" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="dtl">Detalle pedido Lavanderia</h5>
                            <button type="button" class="close btn btn-outline" data-bs-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <h6><strong>Nombre del Huesped:</strong>
                                <asp:Label ID="lblNombreHUesped" runat="server" /></h6>
                            <h6><strong>Numero de Habitacion:</strong>
                                <asp:Label ID="lblNumeroHabitacion" runat="server" /></h6>
                            <h6><strong>Fecha Realizacion:</strong>
                                <asp:Label ID="lblFechaRealizacion" runat="server" /></h6>
                            <h6><strong>Fecha del Inicio:</strong>
                                <asp:Label ID="lblDescripcionLavanderia" runat="server" /></h6>
                        </div>

                        <div class="modal-footer">
                            <asp:LinkButton Text="<i class='fa-solid fa-square-xmark' style='color:#A52A2A;'></i> Cerrar" runat="server" />
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <script>
            function DetalleMasaje() {
                var myModal = new bootstrap.Modal(document.getElementById('modalDetalleMasaje'), {
                    keyboard: false
                });
                myModal.show();
            }
        </script>
        
    <hr />

    <div class="col-md-10 ml-0" ID="TareasPorEntregar" runat="server" style="padding-left:20px">
        <hr />
    <h2>Listado de Solicitudes Por Entregar:</h2>
        <div class="col-md-12">
    <asp:Label ID="ErrorPorEntregar" Style="color: red;" runat="server" Text=""></asp:Label>
</div>
                    <div class="row text-end">
                <div class="col-md-12">
    <asp:Label ID="lblErrorCadena" Style="color: red;" runat="server" Text=""></asp:Label>
</div>
            </div>
    <asp:GridView ID="GridTareasPorEntregarLavandero" runat="server" AutoGenerateColumns="False" AllowPaging="true" PageSize="7" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="GridTareasPorEntregarLavandero_PageIndexChanging" >
        <Columns>
            <asp:BoundField DataField="idPedido" HeaderText="ID" />
            <asp:BoundField DataField="fechaSolicitud" HeaderText="Fecha Inicio:" DataFormatString="{0:dd/MM/yyyy}" HtmlEncode="false" />
            <asp:BoundField DataField="horaFinServicio" HeaderText="Fecha Fin:"  DataFormatString="{0:dd/MM/yyyy}" HtmlEncode="false" />
            <asp:BoundField DataField="incidenciaDeHabitacion" HeaderText="Incidencias:" />
            <asp:TemplateField HeaderText="Incidencias:">
                <ItemTemplate>
                   
                    <asp:LinkButton ID ="btnComentarMasajista" runat="server"  Text=" Incidencia" Class="btn btn-primary fa-solid  fa-eye" style="background-color:#d9a13c;border:none" Onclick="btnComentarMasajista_Click" CommandArgument='<%# Eval("idPedido") %>'/>
                </ItemTemplate>
            </asp:TemplateField>
            <asp:TemplateField HeaderText="Confirmar Pago:">
                <ItemTemplate>
                    <asp:LinkButton ID="btnTerminar" runat="server" Text=" Terminado" style="background-color:#d9a13c;border:none" Class="btn btn-primary fa-solid fa-dollar-sign" OnClick="btnTerminar_Click" CommandArgument='<%# Eval("idPedido") %>'/>
                </ItemTemplate>
            </asp:TemplateField>

        </Columns>
    </asp:GridView>

        <div class="modal fade" ID="Modal-Incidencia" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Incidencia</h5>
                <button type="button" class="close btn btn-outline" data-bs-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <asp:TextBox ID="txtDescription" runat="server" TextMode="MultiLine" CssClass="form-control" Rows="4" Placeholder="Escribe una descripción..."></asp:TextBox>
            </div>
            
            <div class="modal-footer">
                <asp:LinkButton Text="<i class='fa-solid fa-check'></i> Cerrar" runat="server"  />
                <asp:LinkButton Text="<i class='fa-solid fa-check'></i> Guardar" runat="server"  OnClick="btnGuardarModalIncidencia_Click" CommandArgument='<%# Eval("idPedido") %>'/>
            </div>
        </div>
    </div>
</div>


</div>





</div> 


</asp:Content>
