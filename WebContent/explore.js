function searchUser(){
	var username = document.getElementById('searchInput').value
	fetch(url + '/searchUser?'+ new URLSearchParams({
		username: username
	}), {
		method: "GET"
	})
    .then(response => response.text())
    .then(response => {
		if(response.status == "Failed"){
			alert("No user found")
		}else{
			//display their profile?
			console.log(response)
			var data = JSON.parse(response)
			localStorage.setItem("searchedUserID", data.UserID)
			localStorage.setItem("searchedUsername", data.username)
			window.location.href = "profile.html"
		}
	})
}

function message(elem){
	//this is username of who you want to message
	console.log(elem.name)	
}

function createUserDiv(user){
	var parent = document.querySelector('.posts-area')
	var wrapper = document.createElement('div')
	wrapper.className = "post my-3"
	wrapper.innerHTML = "<div class = \"row\">" + "<div class=\"my-3 ml-5 align-self-center\">"
						+ "<img class=\"my-auto\" src=\"tommy_boi.jpeg\" alt=\"profile picture\" id=\"user-pic\">"
						+ "</div>" + "<div class=\" ml-4 mr-auto align-self-center\">" + "<h4>" + user.username + "</h4>"
						+ "</div>" 
						+ "<button name = \""+ user.username + "\"onclick=\"message(this)\" type=\"button\" class=\"btn login_btns mr-2\">" +
									"Profile" +
								"</button>"
						+ "</div>" + "<span class=\"form-control post-textarea mx-auto\">" + user.bio + "</span>" + "</div>"
	parent.appendChild(wrapper)
}

displayUsers()
function displayUsers(){
	var username = localStorage.getItem("username")
	fetch(url + '/getUsers?username=' + username, {
		method: "GET"
	})
    .then(response => response.text())
    .then(response => {
		if(response.replace(/\s/g, "") !== "NootherUsers"){
			var data = JSON.parse(response)
			data.forEach(user => {
				createUserDiv(user)
			})
		}
		
	})
}