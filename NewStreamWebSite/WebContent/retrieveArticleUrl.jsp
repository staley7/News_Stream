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


<html><body>


<%
Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection(connectionURL, "root", "");
statement = connection.createStatement();
rs = statement.executeQuery("SELECT url FROM article where Story_ID = 1 ");

while (rs.next()) {
//out.println(rs.getString("url")+"<br>");
out.println("<a href ="+rs.getString("url")+"><br></br>"+rs.getString("url")+"</a>");
//response.write("<tr><td><b>" & x.name & "</b></td>")
 //   response.write("<td>" & x.value & "</td></tr>")
}

rs.close();
%>