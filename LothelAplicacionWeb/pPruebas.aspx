<%@ Page Title="" Language="C#" MasterPageFile="~/pMaestra.Master" AutoEventWireup="true" CodeBehind="pPruebas.aspx.cs" Inherits="LothelAplicacionWeb.pPruebas" %>
<asp:Content ID="Content1" ContentPlaceHolderID="title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="bodyAdmin" runat="server">
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="bodyUser" runat="server">
<p>
  <a class="btn btn-primary" data-bs-toggle="collapse" href="#explam1" role="button" aria-bs-expanded="false" aria-bs-controls="collapseExample">
    Link with href
  </a>
  <button runat="server" OnClick="" class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#explam1" aria-bs-expanded="false" aria-bs-controls="collapseExample">
    Button with data-target
  </button>
</p>
<div class="collapse" id="explam1">
  <div class="card card-body">
    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
  </div>
</div>
</asp:Content>
