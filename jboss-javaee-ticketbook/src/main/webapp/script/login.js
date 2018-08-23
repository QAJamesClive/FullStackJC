function login(){
    var username = document.getElementById('loginUsername').value;
    var password = document.getElementById('loginPassword').value;
    var requestURL = 'http://localhost:8080/jboss-javaee-ticketbook/example/web/login/'+username+'/'+password;
    var request = new XMLHttpRequest();
    
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();
    request.onload = function () {
        var reply = request.response;
        console.log(reply);
    }
}