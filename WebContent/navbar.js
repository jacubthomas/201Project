

window.onload = function() {
	setUserName()
}

function setUserName(){
	let user = localStorage.getItem('username');
	if(user == null){
	document.getElementById("get_username").innerHTML = "guest";
	document.getElementById("nav_pro").classList.add("hidden");
	document.getElementById("nav_mes").classList.add("hidden");
	document.getElementById("nav_set").classList.add("hidden");
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