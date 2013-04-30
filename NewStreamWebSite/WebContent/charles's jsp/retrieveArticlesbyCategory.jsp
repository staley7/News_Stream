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
	String input = "0";
%>


<html>
<body>


	<%
		input = request.getParameter("Category");
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(connectionURL, "root", "");
		statement = connection
				.prepareStatement("SELECT Article_ID, Story_ID, URL, Source, Date, Title, Article_Keywords, Category, Quotations FROM article WHERE Category = ?");
		statement.setString(1, input);
		rs = statement.executeQuery();

		while (rs.next() && i < 5) {
			out.println(rs.getInt("Article_ID") + " "
					+ rs.getInt("Story_ID") + " " + rs.getString("Source")
					+ " " + rs.getLong("Date") + " " + rs.getString("URL")
					+ " " + rs.getArray("Article_KeyWords").toString()
					+ " " + rs.getString("Category") + " "
					+ rs.getArray("Quotations").toString() + "<br>");
			i++;
		}

		rs.close();
	%>