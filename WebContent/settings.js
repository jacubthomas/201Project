function saveChanges(){
	var bio = document.getElementById("bio").value
	var username = document.getElementById("username").value
	var fname = document.getElementById("fname").value
	var lname = document.getElementById("lname").value
	var UserID = localStorage.getItem("UserID")
	var status = document.getElementsByName('privacy')
	status = status[0].checked ? status[0].value : status[1].value
	//not sure how to upload images
/*	var picture = document.getElementById('picture').files[0]
	if(picture){
	    var FR= new FileReader();
	    FR.addEventListener("load", function(e) {
	      picture = e.target.result
		  console.log(picture)
    	})
    	FR.readAsDataURL( picture );
	}*/
	if((!bio) && (!username || username.length === 0) && 
	   (!fname && fname.length === 0) && (!lname && lname.length === 0)){
		alert("Please fill at least one section")
		return
	}
    fetch(url + '/settings?' + new URLSearchParams({
		UserID: UserID,
		bio: bio,
		username: username,
		fname: fname,
		lname: lname,
		status: status,
	}), {
		method: "GET"
	})
    .then(response => response.text())
    .then(response => {
		console.log(response)
        var data = JSON.parse(response)
		//updating settings in localStorage
		for(var key in data){
			if(data[key] !== "" && data[key] !== 0){
				localStorage.setItem(key, data[key])
			}
		}
		//updating username in the navbar
		setUserName()
	})
}