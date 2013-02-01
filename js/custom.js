/* javascript file for the custom.html page*/


//javascript calls to jquery call to create toogle button
$(document).ready(function(){
    $('a#button').click(function(){
        $(this).toggleClass("down");
    });
});