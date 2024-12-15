<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="ListarEspacios.aspx.cs" Inherits="LothelAplicacionWeb.ListarEspacios" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">

 <div class="container">

     <div class="container mt-3">
        <div class="row">
            <div class="col">
                <!-- Titulo del listado-->
                <p class="fontEvento">Espacios Disponibles</p>
            </div>
            <div class="col text-end">
                <asp:LinkButton ID="lbRegistrarEspacio" runat="server" CssClass="btn btn-success me-4" Text="<i class='fa-solid fa-plus pe-2'></i> Registrar Espacio" OnClick="lbRegistrarEspacio_Click"  />
            </div>
        </div>
    </div>


    <div class="container row">
        <asp:GridView ID="gvEspacios" runat="server"
            AllowPaging="true" PageSize="5" OnPageIndexChanging="gvEspacios_PageIndexChanging"
            AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" >
            <Columns>
                <asp:BoundField HeaderText="Id" DataField="idEspacio" />
                <asp:BoundField HeaderText="Piso" DataField="numeroPiso" />
                <asp:BoundField HeaderText="Seccion" DataField="seccion" />
                <asp:BoundField HeaderText="Aforo" DataField="aforo" />
                <asp:TemplateField>
                    <ItemTemplate>
                        <asp:LinkButton runat="server" Text="<i class='fa-solid fa-edit ps-2'></i>" OnClick="btnEditarEspacio" CommandArgument='<%# Eval("idEspacio") %>' />
                        <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" OnClick="btnEliminarEspacio" CommandArgument='<%# Eval("idEspacio") %>' />
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </div>


    <!-- MODAL PARA AGREGAR UN ESPACIO --->

     <div class="modal" id="form-modal-espacio">
         <div class="modal-dialog modal-xl">
             <div class="modal-content">
                 <div class="modal-header">
                     <asp:Label runat="server" ID="lblTituloModalEspacio" class="modal-title" Text="Registro de espacio"></asp:Label>
                     <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                 </div>
                 <div class="modal-body">
                     <asp:UpdatePanel runat="server">
                         <ContentTemplate>
                             <div class="container row pb-3 pt-3">
                                 <div class="row align-items-center">
                                     <div class="col-sm-3">
                                         Id
                                <asp:TextBox CssClass="form-control" ID="txtIdEspacio" runat="server" Enabled="false"></asp:TextBox>
                                     </div>
                                     <div class="col-sm-3">
                                         Piso
                                <asp:TextBox CssClass="form-control" ID="txtPiso" runat="server"></asp:TextBox>
                                     </div>
                                     <div class="col-sm-3">
                                         Seccion
                                <asp:TextBox CssClass="form-control" ID="txtSeccion" runat="server"></asp:TextBox>
                                     </div>
                                     <div class="col-sm-3">
                                         Aforo
                                <asp:TextBox CssClass="form-control" ID="txtAforo" runat="server"></asp:TextBox>
                                     </div>
                                     <div class="col mt-4 text-end">
                                         <asp:LinkButton ID="lbConfirmarRegistroEspacio" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid  fa-check pe-2'></i> Confirmar" OnClick="btnConfirmarRegistroEspacio" />
                                     </div>
                                 </div>

                                 <div class="row text-end">
                                     <div class="col-md-12">
                                         <asp:Label ID="lblMensajeErrorPiso" Style="color: red;" runat="server" Text=""></asp:Label>
                                     </div>
                                 </div>
                                 <div class="row text-end">
                                     <div class="col-md-12">
                                         <asp:Label ID="lblMensajeErrorSeccion" Style="color: red;" runat="server" Text=""></asp:Label>
                                     </div>
                                 </div>
                                 <div class="row text-end">
                                     <div class="col-md-12">
                                         <asp:Label ID="lblMensajeErrorAforo" Style="color: red;" runat="server" Text=""></asp:Label>
                                     </div>
                                 </div>

                             </div>

                         </ContentTemplate>
                     </asp:UpdatePanel>
                 </div>
             </div>
         </div>
     </div>


     <!-- fin del modal -->

</div>

   
</asp:Content>
