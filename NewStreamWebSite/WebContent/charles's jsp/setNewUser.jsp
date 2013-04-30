<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"  %>
<%@ page import="java.io.*"  %> 

<%
String connectionURL = "jdbc:mysql://streamsdatabase.ece.iastate.edu/mydb?user=root;password=";
Connection connection = null;
PreparedStatement statement = null;
ResultSet rs = null;
String input = "0";
String password = "0";
%>


<html><body>


<%
input = request.getParameter("User_Name");
password = request.getParameter("userpassword");
Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection(connectionURL, "root", "");
statement = connection.prepareStatement("INSERT INTO user(User_Name, Source_Exceptions, userpassword) VALUES(?, null, ?)");
statement.setString(1, input);
statement.setString(2, password);
rs = statement.executeQuery();


rs.close();
%>
