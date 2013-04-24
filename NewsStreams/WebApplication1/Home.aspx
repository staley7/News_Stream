<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="WebApplication1.Home" %>


  <head>
  <!--  bootstrap css links -->
  <LINK href="Content/bootstrap.min.css" REL="stylesheet" TYPE="text/css" media="screen">
  <LINK href="Content/bootstrap-responsive.min.css" REL="stylesheet" TYPE="text/css" media="screen">
  <LINK href="Content/bootstrap.css" REL="stylesheet" TYPE="text/css" media="screen">
  <LINK href="Content/bootstrap-responsive.css" REL="stylesheet" TYPE="text/css" media="screen">
  <link href="Content/home.css" rel="stylesheet" type="text/css" />
   
       
  
  <!-- my stylesheets links -->
  <!--<LINK REL=StyleSheet HREF="css/stylesheet.css" TYPE="text/css" MEDIA=screen>-->
   
   <title>News Stream</title>
  </head>
  
  <!--News Stream header -->
  <h1></h1> 
  
  <body>
    <script src="js/jquery-1.8.3.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/retrieveArticle.js"></script>
	
    

    
	
	<!--main news feed and box -->
	<div id = "main-feed" class="tts-form">
		<div id = "header-feed">
			News Stream
		</div>
		<div id = "time1"  class = "time-stamp" >
            
			<p> November 30 2012 <br> 12:45 pm
		</div>
        <!--<div>
            <asp:Literal ID="timeLiteral" runat="server"> </asp:Literal>
        </div>-->
		<div id = "time2"  class = "time-stamp" >
			<p> November 30 2012 <br> 10:35 am
		</div>
		<div id = "time3"  class = "time-stamp" >
			<p> November 30 2012 <br> 6:23 pm
		</div>
		<div id = "time4"  class = "time-stamp" >
			<p> November 29 2012 <br> 12:15 am
		</div>
		<div id = "time5"  class = "time-stamp" >
			<p> November 29 2012 <br> 9:03 pm
		</div>
		
		<!--  contains the  articles that are selected -->
		<nav id = "feed" onload ="retrieveArticle">
			<div id = "article-1"  class = "article">
				Article Title <br>
				<a href="#"> http://rss.cnn.com/rss/edition_world.rss </a>
			</div>
			<div id "article-2"  class = "article">
				Atricle Title 2<br>
				<a href="#"> test 2</a>
			</div>
			<div id  ="article-3" div class = "article">
				Atricle Title 3<br>
				<a href="#"> test 3</a>
			</div>
			<div id = "article-4" div class = "article">
				Atricle Title 4<br>
				<a href="#"> test 4</a>
			</div>
			<div id ="article-5" div class = "article">
				Atricle Title 5<br>
				<a href="#"> test 5</a>
			</div>

		</nav>
		
		<!-- next and previous buttons -->
		<button id = "previous" type="button">Previous</button>
		<button id = "next" type="button">Next</button>
		
	</div>
	
	<!--category table and box -->
	<div id = "category">
		<div id = "table-header"> Categories </div>
		<table id ="table1"  class="table-hover">
			<tr> <td>Category #1  </td></tr>
			<tr> <td>Category #2  </td></tr>
			<tr> <td>Category #3  </td></tr>
			<tr> <td>Category #4  </td></tr>
			<tr> <td>Category #5  </td></tr>
			<tr> <td>Category #6  </td></tr>
			<tr> <td>Category #7  </td></tr>
			<tr> <td>Category #8  </td></tr>
			<tr> <td>Category #9  </td></tr>
			<tr> <td>Category #10 </td></tr>
			<tr> <td>Category #11 </td></tr>
			<tr> <td>Category #12 </td></tr>
		</table>
	</div>
	
	<!--search bar and button -->
	<form id= "search"  class="form-search">
		<div id = "search-input"  class="input-append">
			<input type="text" class="span2 search-query">
			<button id = "search-button" type="submit" class="btn">Search</button>
		</div>
	</form>
	
	<!-- customize button -->
	<button type ="button"  id = "custom" > Customize Sources</button>
	<!-- login button -->
	<button type = "button"  id = "login"> Login </button>
	
	<!-- image used in the header -->
	<div id = "header-image">
		<img src="img/header blue-white background.jpg" img-polaroid">
	</div>
  
	
	



  </body>
