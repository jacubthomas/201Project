

if(localStorage.getItem("searchedUserID") !== null){
	console.log("HUH")
	var UserID = localStorage.getItem("searchedUserID")
	var username = localStorage.getItem("searchedUsername")
	getSpecificUserPosts(UserID, username)
	localStorage.removeItem("searchedUserID")
	localStorage.removeItem("searchedUsername")
}else{
	var UserID = localStorage.getItem("UserID")
	var username = localStorage.getItem("username")
	getSpecificUserPosts(UserID, username)
}

function getSpecificUserPosts(UserID, username){
	document.getElementById("profileName").innerHTML = username
	fetch(url + '/getSpecificUserPosts?UserID=' + UserID,{
		method: "GET"
	})	
	.then(response => response.text())
	.then(response => {
		if(response.replace(/\s/g, "") === "Noposts"){
			alert("User has no posts")
		}else{
			var data = JSON.parse(response)
			data.forEach(post => {
				createPost(post, username)
			})
		}
	})
}

function createPost(data, user){
	var timestamp = data.date
	var text = data.postText
	var PID = data.PostID
	var shares = data.shares
	var likes = data.likes
	var security = data.security
	
	if(user != null){
		var div =  document.createElement("div");
		div.classList.add("post");
		div.classList.add("p-2");
		if(security == 1){
		div.innerHTML = "<strong>" + timestamp + "<br>" + user + ":</strong><hr>" + text
						+ "<hr><div style=\"text-align:right; margin:2%;\"><button id=\"like" + PID +"\" onclick=\"like(" + PID + ")\" class=\"btn login_btns p-2\">Likes: " + likes 
						+ "</button><button class=\"btn login_btns p-2\">Shares: " + shares +"</button></div>";
		} else {
		div.innerHTML = "<strong>" + timestamp + "<br>" + user + ":</strong><hr>" + text
						+ "<hr><div style=\"text-align:right; margin:2%;\"><button id=\"like" + PID +"\" onclick=\"like(" + PID + ")\" style=\"background-color:#9DA2AB;\" class=\"btn login_btns p-2\">Likes: " + likes 
						+ "</button><button style=\"background-color:#9DA2AB;\" class=\"btn login_btns p-2\">Shares: " + shares +"</button></div>";
		}
		if(security != 1)
			div.classList.add("private");
		document.getElementById("profile_feed").appendChild(div);
	} 
}