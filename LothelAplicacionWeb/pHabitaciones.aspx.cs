using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace LothelAplicacionWeb
{
    public partial class pHabitaciones : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            esconderBarraLateral();
        }

        protected void esconderBarraLateral()
        {
            var menuLateral = this.Master.FindControl("menuLateral") as HtmlGenericControl;
            menuLateral.Visible = false;
        }

        protected void btnReservar1_Click(object sender, EventArgs e)
        {
            Response.Redirect("pReservas.aspx");
        }

        protected void btnReservar2_Click(object sender, EventArgs e)
        {
            Response.Redirect("pReservas.aspx");
        }

        protected void btnReservar3_Click(object sender, EventArgs e)
        {
            Response.Redirect("pReservas.aspx");
        }
    }
}