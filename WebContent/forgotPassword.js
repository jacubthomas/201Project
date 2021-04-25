function recover(){
	var username = document.getElementById('usr').value
	var e = document.getElementById('securityquestions')	
	var question = e.options[e.selectedIndex].text
	var answer = document.getElementById('ans').value
	if(!username || !question || !answer || username.length === 0 || question.length === 0 || answer.length === 0){
		return
	}
	
    fetch(url + '/forgotPassword?' + new URLSearchParams({
		username,
		question,
		answer
	}), {
		method: "GET"
	})
    .then(response => response.text())
    .then(response => {
        var data = JSON.parse(response)
		if(data.success){
			localStorage.setItem("resetPassUserID", data.UserID)	
			window.location.href = "newPW.html"
		}else{
			alert("Wrong Information")
		}
	})
}