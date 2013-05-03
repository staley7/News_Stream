<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>

<%
	String connectionURL = "jdbc:mysql://streamsdatabase.ece.iastate.edu/mydb?user=root;password=";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	int i = 0;
	String inputID = "0";
%>


<html>
<body>


	<%
		inputID = request.getParameter("ID");
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(connectionURL, "root", "");
		statement = connection
				.prepareStatement("SELECT Title FROM story WHERE Story_ID =?");
		statement.setString(1, inputID);
		rs = statement.executeQuery();

		while (rs.next()) {
			out.println(rs.getString("Title") + "<br>");
			i++;
		}

		rs.close();
	%>