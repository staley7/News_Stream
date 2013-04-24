using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication1
{
    /// <summary>
    /// Summary description for Story
    /// </summary>
    public class Story
    {
        private String title;
        private String date;
        private String category;

        public Story()
        {
            title = "";
            date = "";
            category = "";

        }

        public Story(String title, String date)
        {
            this.title = title;
            this.date = date;
            this.category = "";

        }

        public Story(String title, String date, String category)
        {
            this.title = title;
            this.date = date;
            this.category = category;

        }


        public String getTitle()
        {
            return this.title;
        }

        public String getDate()
        {
            return this.date;
        }

    }
}
