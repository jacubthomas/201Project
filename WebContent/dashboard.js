//logging to console all local storage items
for (var i = 0; i < localStorage.length; i++){
    console.log(localStorage.key(i) + ': ' +localStorage.getItem(localStorage.key(i)))
}

getAllPosts()
function getAllPosts(){
    fetch('http://localhost:8080/Group_Project/getPosts?', {
		method: "GET"
	})
	.then(response => response.text())
	.then(response => {
		postsArr = JSON.parse(response)
		console.log(postsArr)
	})

}

function post(){
	postInput = document.getElementById('postInput').value
	UserID = localStorage.getItem("UserID")
    fetch('http://localhost:8080/Group_Project/post?' + new URLSearchParams({
		postInput: postInput,
		UserID: UserID
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
	var probst = document.getElementById("login");
	var friend = document.getElementById("pickafriend");
	var label = document.getElementById("picklabel");
	toggle.classList.toggle("nightmode");
	masterdiv.classList.toggle("nightmode");
	txtarea.classList.toggle("nightmode2");
	probst.classList.toggle("nightmode2");
	friend.classList.toggle("hidden");
	label.classList.toggle("hidden");
}