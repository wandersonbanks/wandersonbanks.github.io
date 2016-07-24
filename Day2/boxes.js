function ContentDown(d) 
{
	var obj = document.getElementById(d);
	var currentPosition = int(obj.style.top)
	var amountToMove = 30
	obj.style.top = currentPosition+amountToMove+"pt";
}

