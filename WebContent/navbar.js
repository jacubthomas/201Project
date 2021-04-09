window.onload = function() {
	console.log("test");
	let user = localStorage.getItem('username');
	console.log(user);
	if(user == null){
	document.getElementById("get_username").innerHTML = "guest";
	} else {
		document.getElementById("get_username").innerHTML = "~" + user;
	}
};

function settings(){
	let user = localStorage.getItem('username');
	if(user == null){
		window.location.href='register.html';
	} else {
		window.location.href='settings.html';
	}
}