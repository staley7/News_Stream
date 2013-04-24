using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication1
{
    public class Article
    {

        String url;
        int ArticleID;
        int StoryID;
        

        public Article()
        {
            url = "";
            ArticleID = -1;
            StoryID = -1;
        }

        public Article(int ArticleID, int StoryID, String url)
        {
            this.ArticleID = ArticleID;
            this.StoryID = StoryID;
            this.url = url;
        }

        public int getArticleID()
        {
            return this.ArticleID;
        }


        public int getStory()
        {
            return StoryID;
        }

        public String getUrl()
        {
            return this.url;
        }


    }
}