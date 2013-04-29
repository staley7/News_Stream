<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"  %>
<%@ page import="java.io.*"  %> 

<%
String connectionURL = "jdbc:mysql://streamsdatabase.ece.iastate.edu/mydb?user=root;password=";
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
int i = 0;
%>


<html><body>


<%
Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection(connectionURL, "root", "");
statement = connection.createStatement();
rs = statement.executeQuery("SELECT Article_ID, Story_ID, URL, Source, Date, Title, Article_Keywords, Category, Quotations FROM article ORDER BY Article_ID");

while (rs.next()) {
out.println(rs.getInt("Article_ID") + " " +rs.getInt("Story_ID") + " " +rs.getString ("Source") + " " +rs.getLong("Date") + " " + rs.getString("URL")+ " " + rs.getArray("Article_KeyWords").toString() + " " + rs.getString("Category") + " " + rs.getArray("Quotations").toString() +"<br>");
}

rs.close();
%>