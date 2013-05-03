<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"  %>
<%@ page import="java.io.*"  %> 

<%
//@author Lance Staley
String connectionURL = "jdbc:mysql://streamsdatabase.ece.iastate.edu/mydb?user=root;password=";
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
%>



<%
Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection(connectionURL, "root", "");
statement = connection.createStatement();
String query="SELECT url FROM article where Story_ID ="+ request.getParameter("Story_ID");
rs = statement.executeQuery(query);

while (rs.next()) {
//out.println("<a href ="+rs.getString("url")+"><br></br>"+rs.getString("url")+"</a>");
out.println("<a href ="+rs.getString("url")+">"+rs.getString("url")+"</a>");

}

rs.close();
%>