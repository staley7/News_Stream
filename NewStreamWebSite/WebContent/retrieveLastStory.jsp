<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"  %>
<%@ page import="java.io.*"  %> 

<%
//author Charles Litfin, modified by Lance Staley
String connectionURL = "jdbc:mysql://streamsdatabase.ece.iastate.edu/mydb?user=root;password=";
Connection connection = null;
PreparedStatement statement = null;
ResultSet rs = null;
%>




<%
Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection(connectionURL, "root", "");
statement = connection.prepareStatement("SELECT Story_ID FROM story ORDER BY Story_ID DESC LIMIT 1");
rs = statement.executeQuery();

while (rs.next()) {
out.println(rs.getInt("Story_ID") +"<br>");
}

rs.close();
%>
