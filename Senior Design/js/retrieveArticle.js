function creatingAjaxObject(){

if(typeof XMLHttpRequest != "undefined"){
		return new XMLHttpRequest();
	}else if(window.ActiveXObject()){
		var aVersions = ["MSXML2.XMLHttp.6.0", "MSXML.XMLHttp.3.0"];
		
		for (var i= 0; i < aVersions.length; i++) {
			try{
				var oXHR = new ActiveXboject(aVersions[i]);
				return oXHR;
			}catch (oError) {
				//Do nothing
			}
		}
	}throw new Error("XMLHttp object could not be created.");

}

function retrieveArticle(){
	var obj;
	alert("in function");
	if(typeof XMLHttpRequest != "undefined"){
		obj = new XMLHttpRequest();
	}else if(window.ActiveXObject()){
		var aVersions = ["MSXML2.XMLHttp.6.0", "MSXML.XMLHttp.3.0"];
		
		for (var i= 0; i < aVersions.length; i++) {
			try{
				obj = new ActiveXboject(aVersions[i]);
			}catch (oError) {
				//Do nothing
				alert("error");
			}
		}
	}throw new Error("XMLHttp object could not be created.");
	//get response
	mlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
	alert("got response"):
    document.getElementById("article-1").innerHTML=xmlhttp.responseText;
    }
  }
	alert("send request");
	obj.open("GET", "retriveArticle.jsp", true);
	obj.send();
}
	
}