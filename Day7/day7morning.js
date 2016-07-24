/*Function to get all the names in a HTML form*/
function getFormValues() {
	var formNames = [];
	for(var i = 0; i < document.getElementById("form1").elements.length; i++) 
	{
    	formNames.push(document.getElementById("form1").elements[i].value);
    }
    return formNames;
};

/*Change the color of the text of the div to red*/
var isRed = false;
var btnClicked = document.getElementById('color-btn');
var div = document.querySelectorAll('.change-color')[0];
btnClicked.onclick = function(){
	if(!isRed)
	{
		div.classList.add('myDiv');
		isRed = true;
	}
	else{
		div.classList.remove('myDiv');
		isRed = false;
	}
};

function toggleImage() {
	
};

/*function called when "name-btn" is called*/
document.getElementById("name-btn").onclick = function() {
	var names = getFormValues();
	console.log(names.join(" "));
};

var imgBtn = document.getElementById('image-btn');
var ltBox = document.querySelectorAll('.lightbox')[0];
var isShowing = false;
var showImage = function()
{
	ltBox.classList.add('isVisible');

	isShowing = true;
};

var hideImage = function()
{		
	ltBox.classList.remove('isVisible');
	isShowing = false;
};

imgBtn.onclick = function() {
	if(!isShowing)
	{
		showImage();
	}
	else{
		hideImage();
	}
};

var ltboxBg = document.querySelectorAll('.lightbox-bg')[0];
ltboxBg.onclick = function(){
	ltBox.classList.remove('isVisible');
	isShowing = false;
}
