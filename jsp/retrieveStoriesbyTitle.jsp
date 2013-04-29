<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"  %>
<%@ page import="java.io.*"  %> 

<%
String connectionURL = "jdbc:mysql://streamsdatabase.ece.iastate.edu/mydb?user=root;password=";
Connection connection = null;
PreparedStatement statement = null;
ResultSet rs = null;
int i = 0;
String inputTitle = 0;
%>


<html><body>


<%
inputTitle = request.getParameter("Title");
Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection(connectionURL, "root", "");
statement = connection.prepareStatement("SELECT Story_ID, Category, Date, Title FROM story WHERE Title =?");
statement.setString(1, inputTitle);
rs = statement.executeQuery();

while (rs.next() && i < 5) {
out.println(rs.getInt("Story_ID") + " " +rs.getString ("Category") + " " + rs.getString("Date") + " " + rs.getString("Title") +"<br>");
i++
}

rs.close();
%>
