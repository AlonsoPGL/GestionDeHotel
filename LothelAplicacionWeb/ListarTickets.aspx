<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="ListarTickets.aspx.cs" Inherits="LothelAplicacionWeb.ListarTickets" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">

        <div class="container-fluid"> 

        <div class="row">
          <div class="col-lg-3 order-lg-first"  style="background-color: #d9a13c"><!--order-lg-firs-->
            <!--Menu Lateral-->
            <div id="bdSidebar" class="d-flex flex-column flex-shrink-0 p-3 text-white ">
                <!--aqui cambio-->

                
    
               <ul class="mynav nav nav-pills flex-column mb-auto">


           
                    <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#userMenu" aria-expanded="false" aria-controls="userMenu" style="color: white;">
                        <i class="fa-solid fa-right-to-bracket pe-2">Pedro Villanueva</i>
        
                    </a>

                    <div class="collapse" id="userMenu">
                        <ul class="sidebar-dropdown list-unstyled" style="color: white;">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link" style="color: white;"">
                                    <span class="topic text-white">Editar Perfil </span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link" style="color: white;"">
                                    <span class="topic text-white">Configuracion</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link" style="color: white;">
                                    <span class="topic text-white">Desconectar</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </ul>
















                <hr>
                <ul class="mynav nav nav-pills flex-column mb-auto">
                    <li class="nav-item mb-1">
                        <a href="ListarEmpresasProveedoras.aspx"><i class="fa-solid fa-building pe-2"></i>Empresas Externas</a>
                    </li>
                    <li class="nav-item mb-1">
                        <a href="ListarEventos.aspx"><i class="fa-solid fa-calendar pe-2"></i>Eventos</a>
                    </li>
                    <li class="nav-item mb-1">
                        <a href="ListarEspacios.aspx"><i class="fa-solid fa-building pe-2"></i>Espacios</a>
                    </li>
                    <li class="nav-item mb-1">
                        <a href="ListarPersonalDeServicio.aspx"><i class="fa-solid fa-people-group  pe-2"></i>Personal de servicio</a>
                    </li>


                    <li class="sidebar-item  nav-item mb-1">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#settings" aria-expanded="false" aria-controls="settings">
                            <span class="topic">Items</span>
                        </a>

                        <ul id="settings" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link"><i class="fa-solid fa-list pe-2"></i>
                                    <span class="topic">Servicios</span>
                                </a>
                                <!---->
                            </li>
                            <li class="sidebar-item">
                                <a href="ListarProductos.aspx" class="sidebar-link">
                                    <i class="fa-solid fa-list pe-2"></i>
                                    <span class="topic">Productos</span>
                                </a>
                            </li>
                        </ul>
                    </li>

                    

                </ul>
                <ul class="mynav nav nav-pills flex-column mb-auto">
                    <li class="sidebar-item  nav-item mb-1">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#settings2" aria-expanded="false" aria-controls="settings">
                            <span class="topic">Reportes</span>
                        </a>

                        <ul id="settings2" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar2">
                            <li class="sidebar-item">
                                <a href="ReporteHuespedes.aspx" class="sidebar-link"><i class="fa-solid fa-list pe-2"></i>
                                    <span class="topic">Huespes</span>
                                </a>
                                <!---->
                            </li>
                            <li class="sidebar-item">
                                <a href="ReporteHabitaciones.aspx" class="sidebar-link">
                                    <i class="fa-solid fa-list pe-2"></i>
                                    <span class="topic">Habitaciones</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <hr>
                <div class="d-flex">
                    <i class="fa-solid fa-book me-2"></i>
                    <h6 class="mt-1 mb-0">Lothel-Administrador</h6>
                </div>
          </div>
          
        </div>  
       <div class="col-lg-9">
          <!-- Contenido del cuerpo principal aquí -->
            
    <h2 class="h2Nuevo" style="margin-top: 2em;" >Listado de Tickets</h2><!--style="margin-top: 2em;"-->

    <a href="ListarProductos.aspx" class="h2NuevoHorizontalButton">
        <button type="button">Alimentos</button>
    </a>
    <a href="ListarBebidas.aspx" class="h2NuevoHorizontalButton">
        <button type="button">Bebidas</button>
    </a>
    <a href="ListarCuidadoPersonal.aspx" class="h2NuevoHorizontalButton">
        <button type="button">Cuidado Personal</button>
    </a>

    <a href="ListarTickets.aspx" class="h2NuevoHorizontalButton">
        <button type="button">Ticket</button>
    </a>

    <div class="container row">
        <div class="text-end p-3">
    
            <button type="submit" class="h2NuevoHorizontal"><i class="fa-solid fa-ticket pe-2"></i>Registrar Tickets</button>
        </div>
    </div>
    <div class="container row">
    </div>
    <table>
       <tr>
         <th>Numero de serie</th>
         <th>Evento</th>
         <th>Cantidad de tickets</th>
         <th>Cantidad Vendida>
         <th>Stock</th>
         <th>    </th>
         <th>    </th>
       </tr>
       <tr>
         <td>0001</td>
         <td>Concierto 1</td>
         <td>220</td>
         <td>150</td>
         <td>70</td>
         <td><i class="fa-solid fa-edit ps-2"></i></td>
       </tr>
       
        <tr>
          <td>0002</td>
          <td>Obra de teatro</td>
          <td>180</td>
          <td>120</td>
          <td>60</td>
          <td><i class="fa-solid fa-edit ps-2"></i></td>
        </tr>
        <tr>
          <td>0003</td>
          <td>Festival de cine</td>
          <td>150</td>
          <td>100</td>
          <td>50</td>
          <td><i class="fa-solid fa-edit ps-2"></i></td>
        </tr>
        <tr>
          <td>0004</td>
          <td>Conferencia</td>
          <td>200</td>
          <td>130</td>
          <td>70</td>
          <td><i class="fa-solid fa-edit ps-2"></i></td>
        </tr>
        <tr>
          <td>0005</td>
          <td>Feria de arte</td>
          <td>250</td>
          <td>160</td>
          <td>90</td>
          <td><i class="fa-solid fa-edit ps-2"></i></td>
        </tr>

     

       <!-- Puedes agregar más filas aquí -->
     </table>

        
        </div>   
                

      </div>
    </div>

</asp:Content>
