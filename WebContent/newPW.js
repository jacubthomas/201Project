if(localStorage.getItem("resetPassUserID") === null){
	window.location.href = "index.html"
}

function setNewPass(){
	var password = document.getElementById('pass').value
	var confirmPassword = document.getElementById('confirmPass').value
	var UserID = localStorage.getItem("resetPassUserID")	
	if(!password || !confirmPassword || password.length === 0 || confirmPassword.length === 0){
		return
	}
	if(password !== confirmPassword){
		alert("Passwords don't match")
		return
	}
    fetch(url + '/setPassword?' + new URLSearchParams({
		UserID,
		password
	}), {
		method: "GET"
	})
    .then(response => response.text())
    .then(response => {
		if(response.replace(/\s/g, "") === "Success"){
			alert("Successfully changed password")
			window.location.href = "index.html"
		}else{
			alert("Failed")
			window.location.href = "index.html"
		}
        localStorage.removeItem("resetPassUserID")	
	})
}