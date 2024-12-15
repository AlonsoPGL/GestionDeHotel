using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LothelAplicacionWeb
{
    public partial class pLothel : System.Web.UI.Page
    {
        /*CuentaDAO daoCuenta;
        Cuenta cuenta;*/

        protected void Page_Init(object sender, EventArgs e)
        {
            esconderBarraLateral(); //Hacer en el init
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            string view = Request.QueryString["view"];
            if (view == "logeado") //si la accion viene luego del boton de login, entonces se hara redirect a ella misma
            {
                //ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true); //Esto esta mal genera postBack infinito
                Response.Redirect("pLothel.aspx");
            }
        }
        

        protected void esconderBarraLateral()
        {
            var menuLateral = this.Master.FindControl("menuLateral") as HtmlGenericControl;
            menuLateral.Visible = false;
        }
    }
}