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
boolean contains = false;
int position = 0;
int i = 0;
%>


<html><body>


<%
input = request.getParameter("User_Name");
newSource = request.getParameter("Source_Exceptions");
Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection(connectionURL, "root", "");
statement = connection.prepareStatement("SELECT Source_Exceptions FROM user WHERE User_Name = ?");
statement.setString(1, input);
rs = statement.executeQuery();
String[] excepts = new String[3];
Array z = rs.getArray("Source_Exceptions");
String[] original = (String[])z.getArray();
while(i< original.length){
	if(original[i] == newSource){
		contains = true;
	}else{
		excepts[i] = original[i];
	}
	i++;
}
if(!contains){
	excepts[i] = newSource;
}
statement = connection.prepareStatement("UPDATE user SET Source_Exceptions = '"+ excepts +"' WHERE User_Name = ?");
//statement.setArray(1, excepts);
statement.setString(1, input);
if(contains){
	out.println("The source was already present in the list" + "<br>");
}else{
	out.println("The source was added to the exceptions list" + "<br>");
}
statement.executeUpdate();
rs.close();
%>
