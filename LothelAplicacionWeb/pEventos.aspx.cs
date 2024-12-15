using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LothelAplicacionWeb
{
    public partial class pEventos : System.Web.UI.Page
    {
        private BindingList<int> productos;
        protected void Page_Load(object sender, EventArgs e)
        {
            esconderBarraLateral();
            /*Testeando si la variable de session sigue viva*/
            //SI VIVE, VINO DESD PVENTAS.ASPX CON EL VALOR DE 1 Y AHORA SERA 1 Y 7
            /*productos = (BindingList<int>)Session["productos"];
            productos.Add(7);*/
            /*foreach(int i in productos)
            {
                System.Console.WriteLine(i);
            }*/
            
        }

        protected void esconderBarraLateral()
        {
            var menuLateral = this.Master.FindControl("menuLateral") as HtmlGenericControl;
            menuLateral.Visible = false;
        }
    }
}