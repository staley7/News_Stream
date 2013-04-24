<%
response.expires=-1
sql="SELECT TITLE FROM STORY WHERE count(*) < 6"
sql=sql & "'" & request.querystring("q") & "'"

set conn=Server.CreateObject("ADODB.Connection")
conn.Provider="Microsoft.Jet.OLEDB.4.0"
conn.Open(Server.Mappath("/db/northwind.mdb"))
set rs=Server.CreateObject("ADODB.recordset")
rs.Open sql,conn

response.write("<table>")
do until rs.EOF
  for each x in rs.Fields
    response.write("<tr><td><b>" & x.name & "</b></td>")
    response.write("<td>" & x.value & "</td></tr>")
  next
  rs.MoveNext
loop
response.write("</table>")
%>



Tables in Database
Article Table
	Article ID (int)- The id that is associated with the article, a unique id will be given to 			every article. This will be the primary key of the Article Table.
	Story ID (int)- The id that is associated with the story the article is talking about, if 			several articles are about the same story they will have the same id. This will be 			the primary key in the Story table.
	URL (String)- The url of the article.
	Source (String)- The name of the news source that the article is from.
	Date (long)- The date of publication for the article.
	Title (String)- The title that was given to the article by the news source.
	Article Keywords (String[])- The key words that were found in the article.
	Category (String)- The category that the news source placed this article in on its website.
	Quotations (String[]) - Quotations that were found in the article.
	
Story Table
	Story ID (int)- See description in Article Table.
	Category (String)- The category that the story falls under on our website.
	Date (long)- The date that the particular story occurred on, it will be an average of all the 			articles that match to this story, and will be the date we use on our website.
	Title (String)- Will be the title of the story that we display on our website.

Users Table
	User Name (String) -
	Source Exceptions (String[]) -
