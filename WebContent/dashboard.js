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