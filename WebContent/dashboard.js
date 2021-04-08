//logging to console all local storage items
for (var i = 0; i < localStorage.length; i++){
    console.log(localStorage.key(i) + ': ' +localStorage.getItem(localStorage.key(i)))
}