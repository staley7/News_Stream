<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"  %>
<%@ page import="java.io.*"  %> 

<%
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
rs = statement.executeQuery("SELECT title FROM story WHERE Story_ID < 2");

while (rs.next()) {
out.println(rs.getString("title")+"<br>");
}

rs.close();
%>