
		//returns the newest stories id and sets the count value to it
		function getNewStoryID(){	
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					//count = xmlhttp.responseText;
					count = parseInt(xmlhttp.responseText);
				}
			}
			xmlhttp.open("GET", "retrieveLastStoryDate.jsp", true);
			xmlhttp.send();
			
		}
	
	
		//populates the first article url on page load
		function retrieveArticleURL() {
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					document.getElementById("url").innerHTML = xmlhttp.responseText;
				}
			}
			xmlhttp.open("GET", "retrieveArticleUrl.jsp", true);
			xmlhttp.send();

		}

		//populates first story title on page load
		function retrieveStory() {
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					document.getElementById("title-1").innerHTML = xmlhttp.responseText;
				}
			}
			xmlhttp.open("GET", "retrieveStory.jsp", true);
			xmlhttp.send();
		}

		//populates the story titels on page load
		function retrieveStoryTitlebyID(id) {
			var xmlhttp;
			var Story_ID = id;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					document.getElementById("title-"+id).innerHTML = xmlhttp.responseText;
				}
			}
			xmlhttp.open("GET", "retrieveStoryTitlebyID.jsp?Story_ID= "
					+ Story_ID, true);
			xmlhttp.send();
		}
		
		//populates teh story url on page load
		function retrieveArticleURLbyID(id) {
			var xmlhttp;
			var Story_ID = id;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					document.getElementById("url" + id).innerHTML = xmlhttp.responseText;
				}
			}
			xmlhttp.open("GET", "retrieveArticleUrlbyID.jsp?Story_ID= "
					+ Story_ID, true);
			xmlhttp.send();

		}
		
		//popolutes the story url on next click
		function retArticleURLbyID(counter, id ) {
			var xmlhttp;
			var Story_ID = counter;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					document.getElementById("url" + id).innerHTML = xmlhttp.responseText;
				}
			}
			xmlhttp.open("GET", "retrieveArticleUrlbyID.jsp?Story_ID= "
					+ Story_ID, true);
			xmlhttp.send();

		}
		
		// retrieves story titles on next button click
		function retStoryTitlebyID(counter, id) {
			var xmlhttp;
			var Story_ID = counter;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					document.getElementById("title-"+id).innerHTML = xmlhttp.responseText;
				}
			}
			xmlhttp.open("GET", "retrieveStoryTitlebyID.jsp?Story_ID= "
					+ Story_ID, true);
			xmlhttp.send();
		}
		
		
		
		
		//calls the function to populate main feed on next button
		function next(){
			count=count+5;
			retStoryTitlebyID(count, 5);
 			retArticleURLbyID(count, 5);
 			retStoryTitlebyID(count-1, 4);
 			retArticleURLbyID(count-1, 4)
 			retStoryTitlebyID(count-2, 3);
 			retArticleURLbyID(count-2, 3);
 			retStoryTitlebyID(count-3, 2);
			retArticleURLbyID(count-3, 2);
			retStoryTitlebyID(count-4, 1);
			retArticleURLbyID(count-4, 1);
 			
  			
  			;
 		
		}
		
		// function which loads new stories on previous button call
		function previous(){
			count=count-5;
			retStoryTitlebyID(count+4, 1);
			retArticleURLbyID(count+4, 1);
 			retStoryTitlebyID(count+3, 2);
			retArticleURLbyID(count+3, 2);
  			retStoryTitlebyID(count+2, 2);
 			retArticleURLbyID(count+2, 3);
  			retStoryTitlebyID(count+1, 4);
 			retArticleURLbyID(count+1, 4);
 			retStoryTitlebyID(count, 5);
 			retArticleURLbyID(count, 5);
			
			
		}
		
		//onload function which makes javascript calls to populate the main feed
		function startOnLoad() {
			getNewStoryID();
			makeTableRowClickable();
			retrieveStoryTitlebyID(count);
			retrieveArticleURLbyID(count);
 			retrieveStoryTitlebyID(count-1);
			retrieveArticleURLbyID(count-1);
 			retrieveStoryTitlebyID(count-2);
			retrieveArticleURLbyID(count-2);
 			retrieveStoryTitlebyID(count-3);
			retrieveArticleURLbyID(count-3);
			retrieveStoryTitlebyID(count-4);
			retrieveArticleURLbyID(count-4);

		}
		
		//makes articles be based on recent news
		function RecentNews(){
			alert("clicked");
		}
		
		//makes articles based on world news
		function WorldNews(){
			alert("world");
		}
		
		//makes the articles based on tech news
		function techNews(){
			alert("tech");
		}
		
		//makes the rows clickable in the tables usign JQuery
		function makeTableRowClickable(){
			$("#recent").click(function(){
			   RecentNews();
			 });
			$("#tech").click(function(){
				   techNews();
			});
			$("#world").click(function(){
				   WorldNews();
				 });
		}
		
		//tell page to run function on window load
		window.onload = startOnLoad;