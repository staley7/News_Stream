/* javascript file for the custom.html page*/



function setToggle(){
		alert('hello');
		var list = document.getElementsByClassName('btn btn-primary');
		for(var i = 0; i < list.length; i++)
		$(list[i].button('toggle');
}

/*$(document).ready(function(){
	document.getElementById("source1").click(function(){
		$(this).toggleClass("down");
	});
});
//javascript calls to jquery call to create toogle button
$(document).ready(function(){
    $('div.source1').click(function(){
        $(this).toggleClass("down");
    });
});

function toggle(button)
{
	
 }