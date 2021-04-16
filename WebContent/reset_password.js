function changePassword(){
	var oldPass = document.getElementById('old-password').value
	var newPass = document.getElementById('new-password').value
	var confirmPass = document.getElementById('confirm-password').value
	var UserID = localStorage.getItem("UserID")
	if(newPass !== confirmPass){
		alert('New passwords do not match!')
		return
	}
	
    fetch(url + '/changePassword?' + new URLSearchParams({
		UserID: UserID,
		oldPassword: oldPass,
		newPassword: newPass,
	}), {
		method: "GET"
	})
    .then(response => response.text())
    .then(response => {
		console.log(response)
		if(response === "Wrong Password"){
			alert(response)
		}else if(response === "Successfully changed Password"){
			alert(response)
		}
	})
}