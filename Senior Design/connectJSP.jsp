<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 

<%
String connectionURL = "jdbc:mysql://streamsdatabase.ece.iastate.edu/?user=root;password=";
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
%>


<html><body>


<%
Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection(connectionURL, "root", "");
statement = connection.createStatement();
rs = statement.executeQuery("SELECT url FROM Article");

while (rs.next()) {
out.println(rs.getString("name")+"<br>");
}

rs.close();
%>