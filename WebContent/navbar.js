var url = "http://localhost:8080/Group_Project"

window.onload = function() {
	setUserName()
}

function setUserName(){
	let user = localStorage.getItem('username');
	if(user == null){
	document.getElementById("get_username").innerHTML = "guest";
	} else {
		document.getElementById("get_username").innerHTML = "~" + user;
	}
}

function settings(){
	let user = localStorage.getItem('username');
	if(user == null){
		window.location.href='register.html';
	} else {
		window.location.href='settings.html';
	}
}