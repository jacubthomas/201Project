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
		for(var i=0; i <postsArr.length; i++){
			var username = postsArr[i].username;
			var text = postsArr[i].postText;
			var timestamp = postsArr[i].date;
			var likes = postsArr[i].likes;
			var shares = postsArr[i].shares;
			var security = postsArr[i].security;
			var div =  document.createElement("div");
			div.classList.add("post");
			div.classList.add("p-2");
			div.innerHTML = timestamp + " " + username + "\n"+ text
							+ "\nLikes: " + likes + " , Shares: " + shares;
			if(security != 1)
				div.classList.add("private");
			document.getElementById("dashposts").appendChild(div);
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