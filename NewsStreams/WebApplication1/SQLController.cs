using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;


namespace WebApplication1
{
    class SQLController
    {
        /// <summary>
        /// The connection string used for connecting to the database. 
        /// Do NOT modify these values unless the directory of the database changes.
        /// </summary>
        private static string _CONNECTION_STRING = @"Data Source=jdbc:mysql://streamsdatabase.ece.iastate.edu/;Integrated Security=False;AttachDbFilename=mydb; User ID=root; Password=;";
        public static string CONNECTION_STRING
        {
            get { return _CONNECTION_STRING; }
            set { _CONNECTION_STRING = value; }
        }

        public Story RetrieveStory(int StoryID)
        {


            using (SqlConnection conn = new SqlConnection(CONNECTION_STRING))
            using (SqlCommand query = conn.CreateCommand())
            {
                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.Append("SELECT Title, Date ");
                queryBuilder.Append("FROM Story ");
                queryBuilder.Append("WHERE StoryID = @id");

                query.CommandText = queryBuilder.ToString();
                query.Parameters.AddWithValue("@id", StoryID);

                conn.Open();
                SqlDataReader reader = query.ExecuteReader();

                // If there are no records returned from the select statement, the DataReader will be empty
                Story store = null;
                if (reader.Read())
                {
                    String title = (string)reader["Title"];
                    String data = (string)reader["Date"];
                    store = new Story(title, data);
                }
                return store;

            }
        }

        public Article RetrieveArticle(int StoryID)
        {


            using (SqlConnection conn = new SqlConnection(CONNECTION_STRING))
            using (SqlCommand query = conn.CreateCommand())
            {
                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.Append("SELECT StoryID, ArticleID, url");
                queryBuilder.Append("FROM Article ");
                queryBuilder.Append("WHERE StoryID = @id");

                query.CommandText = queryBuilder.ToString();
                query.Parameters.AddWithValue("@id", StoryID);

                conn.Open();
                SqlDataReader reader = query.ExecuteReader();

                // If there are no records returned from the select statement, the DataReader will be empty
                Article art = null;
                if (reader.Read())
                {
                    String title = (string)reader["url"];
                    String data = (string)reader["Date"];
                   // art = new Article(title, data);
                }
                return art;

            }
        }

    }
}
