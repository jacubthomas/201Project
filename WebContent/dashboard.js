//logging to console all local storage items
for (var i = 0; i < localStorage.length; i++){
    console.log(localStorage.key(i) + ': ' +localStorage.getItem(localStorage.key(i)))
}

fetch('http://localhost:8080/Group_Project/getPosts?', {
	method: "GET"
	})
	.then(response => response.text())
	.then(response => {
		postsArr = JSON.parse(response)
		console.log(postsArr)
		console.log(postsArr[0].UserID);
		var user = localStorage.getItem("username");
		
		for(var i=0; i <postsArr.length; i++){
			var username = postsArr[i].username;
			var text = postsArr[i].postText;
			var timestamp = postsArr[i].date;
			var likes = postsArr[i].likes;
			var shares = postsArr[i].shares;
			var security = postsArr[i].security;
			var PID = postsArr[i].PostID;
			if(user != null){
				var div =  document.createElement("div");
				div.classList.add("post");
				div.classList.add("p-2");
				if(security == 1){
				div.innerHTML = "<strong>" + timestamp + "<br>" + username + ":</strong><hr>" + text
								+ "<hr><div style=\"text-align:right; margin:2%;\"><button id=\"like" + PID +"\" onclick=\"like(" + PID + ")\" class=\"btn login_btns p-2\">Likes: " + likes 
								+ "</button><button class=\"btn login_btns p-2\">Shares: " + shares +"</button></div>";
				} else {
				div.innerHTML = "<strong>" + timestamp + "<br>" + username + ":</strong><hr>" + text
								+ "<hr><div style=\"text-align:right; margin:2%;\"><button id=\"like" + PID +"\" onclick=\"like(" + PID + ")\" style=\"background-color:#9DA2AB;\" class=\"btn login_btns p-2\">Likes: " + likes 
								+ "</button><button style=\"background-color:#9DA2AB;\" class=\"btn login_btns p-2\">Shares: " + shares +"</button></div>";
				}
				if(security != 1)
					div.classList.add("private");
				document.getElementById("dashposts").appendChild(div);
			} else {
				if(security == 1){
				var div =  document.createElement("div");
				div.classList.add("post");
				div.classList.add("p-2");
				div.innerHTML =  "<strong>" + timestamp + "<br>" + username + ":</strong><hr>" + text
								+ "<hr><div style=\"text-align:right; margin:2%;\"><button id=\"like" + PID +"\" onclick=\"like(" + PID + ")\" style=\"background-color:#9DA2AB;\" class=\"btn login_btns p-2\">Likes: " + likes
								 + "</button><button style=\"background-color:#9DA2AB;\" class=\"btn login_btns p-2\">Shares: " + shares +"</button></div>";
				document.getElementById("dashposts").appendChild(div);}
			}
		} 
	})
	

function post(){
	postInput = document.getElementById('say_more_txt').value;
	postSecurity = document.getElementById('switch1').checked;
	var postRecipient = document.getElementById('pickafriend').value;
	UserID = localStorage.getItem("UserID")
    fetch('http://localhost:8080/Group_Project/post?' + new URLSearchParams({
		Input: postInput,
		UserID: UserID,
		Security: postSecurity,
		Recipient: postRecipient
	}), {
		method: "GET"
	})
	.then(response => response.text())
	.then(response => console.log(response))
}

function privacyToggle(){
	var toggle = document.getElementById("switch1");
	var masterdiv = document.getElementById("say_more");
	var txtarea = document.getElementById("say_more_txt");
	var probst = document.getElementById("post_btn");
	var friend = document.getElementById("pickafriend");
	var label = document.getElementById("picklabel");
	toggle.classList.toggle("nightmode");
	masterdiv.classList.toggle("nightmode");
	txtarea.classList.toggle("nightmode2");
	probst.classList.toggle("nightmode2");
	friend.classList.toggle("hidden");
	label.classList.toggle("hidden");
}

function like(PID){
	fetch('http://localhost:8080/Group_Project/like?' + new URLSearchParams({
		PostID: PID 
	}), {
		method: "GET"
	})
	.then(response => response.text())
	.then(response => {
	resp = JSON.parse(response)
	console.log(resp.Likes);
	var id = "like" + PID;
	document.getElementById(id).innerHTML="Likes: " + resp.Likes;
	})
	
}