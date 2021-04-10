function register(){
	var username = document.getElementById('usr').value
	var password = document.getElementById('pwd').value
	var confirmPassword = document.getElementById('pwd_confirm').value
	var firstname = document.getElementById('first_name').value
	var lastname = document.getElementById('last_name').value
	var securityQuestion = document.getElementById("securityQuestions").value
	var securityAnswer = document.getElementById("security_answer").value
	
	if(!(username && password && confirmPassword && firstname && lastname && securityQuestion && securityAnswer)){
		alert("Please fill out all fields.")
		return
	}
	if(password != confirmPassword){
		alert("Passwords do not match.")
		return
	}
	//figure out how the hash the passwords later
	fetch('http://localhost:8080/Group_Project/register?' + new URLSearchParams({
		username: username,
		password: password,
		confirmPassword: confirmPassword,
		firstname: firstname,
		lastname: lastname,
		securityQuestion: securityQuestion,
		securityAnswer: securityAnswer
	}), {
		method: "GET"
	})
    .then(response => response.text())
    .then(response => {
		if(response === "Username Exists"){
			alert("Username Exists")
			return
		}
		userData = JSON.parse(response) 
		console.log(userData)
		localStorage.setItem("UserID", userData.UserID)
		localStorage.setItem("username", userData.username);
		localStorage.setItem("firstname", userData.firstname);
		localStorage.setItem("lastname", userData.lastname);
		window.location.href = "dashboard.html"
	})
}

getSecurityQuestions()
//loads the security questions into register dropdown
function getSecurityQuestions(){
	var questions = [
		"What is your mother's maiden name?",
		"What is the name of your first pet?",
		"What was your first car?",
		"What elementary school did you attend?",
		"What is the name of the city where you were born?",
		"What high school did you attend?",
		"What is the name of the road you grew up on?",
		"What is the name of your favorite childhood friend?",
		"In what city did you meet your spouse/significant other?",
		"In what city or town was your first job?"
		
	]
	var dropDown = document.getElementById("securityQuestions")
	questions.forEach(question => {
		var option = document.createElement("option")
		option.text = question;
		dropDown.add(option);
	})

}