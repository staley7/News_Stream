<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.ArrayList"%>

<%
	//author Charles Litfin, modified by Lance Staley
	String connectionURL = "jdbc:mysql://streamsdatabase.ece.iastate.edu/mydb?user=root;password=";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	String input = "0";
	String newSource = "0";
	boolean contains = false;
	int position = 0;
	int i = 0;
%>


<%
	input = request.getParameter("User_Name");
	newSource = request.getParameter("Source_Exceptions");
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection(connectionURL, "root", "");
	statement = connection
			.prepareStatement("UPDATE user SET Source_Exceptions = ? WHERE User_Name = ?");
	statement.setString(1, newSource);
	statement.setString(2, input);
	int success = statement.executeUpdate();
	out.println(success);
	rs.close();
%>
