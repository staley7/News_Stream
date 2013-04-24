using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;



namespace WebApplication1
{
    public partial class _Default : Page
    {

        private int storyIndex=0;

        protected void Page_Load(object sender, EventArgs e)
        {
           // load_stories();
        }

        public void load_stories()
        {
           SQLController controller = new SQLController();
           Story stor = controller.RetrieveStory(storyIndex);
           timeLiteral.Text = stor.getDate();
            //set atricle title 
            
        }

        public void load_articles()
        {
            SQLController controller = new SQLController();
            

        }
    }


   
}