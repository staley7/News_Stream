<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"  %>
<%@ page import="java.io.*"  %> 
<%@ page import="java.util.ArrayList"  %> 

<%
String connectionURL = "jdbc:mysql://streamsdatabase.ece.iastate.edu/mydb?user=root;password=";
Connection connection = null;
PreparedStatement statement = null;
ResultSet rs = null;
String input = "0";
String newSource = "0";
String oldpass = "0";
int success = 0;
%>




<%
	input = request.getParameter("User_Name");
	newSource = request.getParameter("Source_Exceptions");
	oldpass = request.getParameter("userpassword");
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection(connectionURL, "root", "");
	statement = connection.prepareStatement("DELETE FROM user WHERE User_Name = ?;");
	statement.setString(1, input);
	statement.clearParameters();
	statement = connection.prepareStatement("INSERT INTO user(User_Name, Source_Exceptions, userpassword) VALUES(?, ?, ?);");
	statement.setString(1, input);
	statement.setString(2, newSource);
	statement.setString(3, oldpass);
	success = statement.executeUpdate();
      out.println(success);
	statement.close();
%>
