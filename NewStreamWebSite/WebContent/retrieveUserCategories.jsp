<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>

<%
	String connectionURL = "jdbc:mysql://streamsdatabase.ece.iastate.edu/mydb?user=root;password=";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	String input = "0";
%>

	<%
		input = request.getParameter("User_Name");
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(connectionURL, "root", "");
		statement = connection
				.prepareStatement("SELECT Source_Exceptions FROM user WHERE User_Name = ?");
		statement.setString(1, input);
		rs = statement.executeQuery();
		if (rs.next()) {
			out.println(rs.getString("Source_Exceptions"));
		} else {
			out.println("");
		}

		rs.close();
	%>