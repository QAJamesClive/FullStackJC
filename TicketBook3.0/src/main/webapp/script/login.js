checkLogin();
function login(){
    var username = document.getElementById('loginUsername').value;
    var password = document.getElementById('loginPassword').value;
    var requestURL = 'http://localhost:8080/TicketBook3.0/example/web/login';
    var request = new XMLHttpRequest();
    
    
    request.open('POST', requestURL,true);
    request.setRequestHeader("Content-Type","application/json");
    request.responseType = 'text';
    let user = new Object();
    user.username = username;
    user.password = password;
    let jsonString = JSON.stringify(user);
    request.send(jsonString);
    request.onload = function () {
        var reply = request.response;
        if(reply){
        	document.cookie = "username="+username;
        	checkLogin();
        }
    }
}

function checkLogin(){
	if(getCookie("username") !=""){
		createLoggedin();
	}
}

function createLoggedin(){
	document.getElementById("profile").style.display="none";
	var logout = document.getElementById("logout");
	var usernameText = document.getElementById("usernameText");
	var headOne = document.createElement("H1")
		
	
	headOne.textContent = "Signed in as "+getCookie("username");
	usernameText.appendChild(headOne);;
	logout.style.display="inline";	
	document.getElementById('Section').appendChild(logout);
}

function logout(){	document.cookie = "username=";
	checkLogin();
	document.getElementById("usernameText").innerHTML = "";
	document.getElementById("logout").style.display="none";
	document.getElementById("profile").style.display="inline";	
}

function getCookie(cname) {
    var decodedCookie = decodeURIComponent(document.cookie);
    var cookies = decodedCookie.split(';');
    for(var i = 0; i <cookies.length; i++) {
        var cookie = cookies[i].split('=');
        if(cookie[0].includes(cname)){
        	return cookie[1];
        }
    }
    return "";
}

function displayRegister(){
	document.getElementById("login").style.display="none";
	document.getElementById("register").style.display="inline";	
}

function cancelAddUser(){
	document.getElementById("login").style.display="inline";
	document.getElementById("register").style.display="none";	
}

function checkNameCredentials(nameInput){
	var inputValue = document.getElementById(nameInput).value;
	var errorrMessage = document.getElementById("errorMessage");
	if(inputValue == null || inputValue.includes(" ")||inputValue==""|| inputValue.length >= 16){
		errorMessage.innerHTML += "Not a valid name";
		return false;
	}
	return true;
}

function checkUsernameCredentials(usernameInput){
	var inputValue = document.getElementById(usernameInput).value;
	var errorrMessage = document.getElementById("errorMessage");
	if(inputValue == null || inputValue.includes(" ")||inputValue==""|| inputValue.length >= 16||checkUsernameUnique(inputValue)){
		errorMessage.innerHTML += "Not a valid username";
		console.log("Its taken");
		return false;
	}
	return true;
}

function checkPasswordCredentials(passwordInput){
	var inputValue = document.getElementById(passwordInput).value;
	var errorrMessage = document.getElementById("errorMessage");
	if(inputValue == null || inputValue.includes(" ")||inputValue==""|| inputValue.length >= 16){
		errorMessage.innerHTML += "Not a valid password";
		return false;
	}
	return true;
}

function checkUsernameUnique(username){
	var requestURL = 'http://localhost:8080/TicketBook3.0/example/web/usernameCheck';
    var request = new XMLHttpRequest();
    
    
    request.open('POST', requestURL,true);
    request.setRequestHeader("Content-Type","application/json");
    request.responseType = 'text';
    let user = new Object();
    user.username = username;
    let jsonString = JSON.stringify(user);
    request.send(jsonString);
    request.onload = function () {
        var reply = request.response;
        return reply;
    }
}

function addUser(){
	errorMessage.innerHTML = "";
	if(checkNameCredentials("firstNameInput")&&checkNameCredentials("lastNameInput")&&checkUsernameCredentials("usernameInput")&&checkPasswordCredentials("passwordInput")){
		console.log("Acceptable name")
		
		
		
		var requestURL = 'http://localhost:8080/TicketBook3.0/example/web/addUser';
		var request = new XMLHttpRequest();
    
    
	    request.open('POST', requestURL,true);
	    request.setRequestHeader("Content-Type","application/json");
	    request.responseType = 'text';
	    let user = new Object();
	    
	    user.firstName = document.getElementById("firstNameInput").value;
	    user.lastName = document.getElementById("lastNameInput").value;
	    user.username = document.getElementById("usernameInput").value;
	    user.password = document.getElementById("passwordInput").value;
	    
	    let jsonString = JSON.stringify(user);
	    request.send(jsonString);
	    request.onload = function () {
	        var reply = request.response;
	        if(reply){
	        	cancelAddUser();
	        }else{
	        	errorMessage.innerHTML = "Failed user creation";
	        }        
	    }
	}
}
	