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
        console.log(reply);
    }
}