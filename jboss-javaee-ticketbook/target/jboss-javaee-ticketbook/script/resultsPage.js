
getdata();
	function getdata(){
	    var queryString = decodeURIComponent(window.location.search);
	    queryString = queryString.substring(1);
	    var queries = queryString.split("&");
	    var searchList = queries[0].split("=");
	    var search = searchList[1];
	    var searchTypeList = queries[1].split("=");
	    var searchType = searchTypeList[1];
	    
	    var requestURL = 'http://localhost:8080/jboss-javaee-ticketbook/example/web/find/'+search+'/'+searchType;
	    var request = new XMLHttpRequest();
	    
	    request.open('GET', requestURL);
	    request.responseType = 'json';
	    request.send();
	    request.onload = function () {
	    var reply = request.response;
		if(searchType.equals("All")){
			for (i in reply){
				replyI = reply[i];
				for(j in replyI){
					replyJ = replyI[j];
					for(k in replyJ){
						var result = document.createElement('div');
						result.id = "searchResult";
						result.textContent = replyJ[k];
						document.getElementById('searchResults').appendChild(result);
					}
				}
			}
		}else{
			for (i in reply){
				replyI = reply[i];
				for(j in replyI){
					var result = document.createElement('div');
					result.id = "searchResult";
					result.textContent = replyI[j];
					document.getElementById('searchResults').appendChild(result);
				}
			}
		}

	}

}