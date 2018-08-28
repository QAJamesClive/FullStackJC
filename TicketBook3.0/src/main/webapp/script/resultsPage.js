
getdata();
function navigateShell(i){
	return function() {
	  var queryString = "?para1=" + i;
	  window.location.href = "event.html" + queryString;  
	}
}

	function getdata(){
	    var queryString = decodeURIComponent(window.location.search);
	    queryString = queryString.substring(1);
	    var queries = queryString.split("&");
	    var searchList = queries[0].split("=");
	    var search = searchList[1];
	    var searchTypeList = queries[1].split("=");
	    var searchType = searchTypeList[1];
	    
	    var requestURL = 'http://localhost:8080/TicketBook3.0/example/web/find/'+search+'/'+searchType;
	    var request = new XMLHttpRequest();
	    
	    request.open('GET', requestURL);
	    request.responseType = 'json';
	    request.send();
	    request.onload = function () {
		var reply = request.response;
		if(searchType == "All"){
			idcount = 0;
			for (i in reply){
				replyI = reply[i];
				for(j in replyI){
					idcount ++;
					replyJ = replyI[j];
					var result = document.createElement('div');
					result.className ="searchResult";
					var headOne = document.createElement("H1");
					headOne.id = "headOne"+idcount;
					var headTwo = document.createElement("H2");
					headTwo.id = "headTwo"+idcount;			
					
					var eventSuggestions = document.createElement("div");
					eventSuggestions.id ="eventSuggestions"+idcount;
					eventSuggestions.className ="eventSuggestions";
										
					
					document.getElementById('searchResults').appendChild(result);
					if(replyJ.bandName !== undefined){
						headOne.textContent += replyJ.bandName;
						headTwo.textContent += replyJ.bandDescription;
						result.onclick = expandResult(result);

						for(k =0; k < replyJ.Event.length;k++){
							replyEvent = replyJ.Event;
							event = document.createElement("div");
							event.className ="event";
							event.onclick = navigateShell(replyEvent[k].eventIDPK);
							
							var eventHeadOne = document.createElement("H3");
							eventHeadOne.id = "eventHeadTwo"+idcount;
							var eventHeadTwo = document.createElement("H4");
							eventHeadTwo.id = "eventHeadTwo"+idcount;	
							
							eventHeadOne.textContent += replyEvent[k].eventName;
							eventHeadTwo.textContent += replyEvent[k].eventDescription;
							
							eventSuggestions.appendChild(event);
							event.appendChild(eventHeadOne);
							event.appendChild(eventHeadTwo);
						}
						result.onclick = expandResult(result);
												
						
					}
					if(replyJ.venueName !== undefined){
						headOne.textContent += replyJ.venueName;
												
						for(k =0; k < replyJ.Event.length;k++){
							event = document.createElement("div");
							event.className ="event";
							replyEvent = replyJ.Event;
							event.onclick = navigateShell(replyEvent[k].eventIDPK);
							
							var eventHeadOne = document.createElement("H3");
							eventHeadOne.id = "eventHeadTwo"+idcount;
							var eventHeadTwo = document.createElement("H4");
							eventHeadTwo.id = "eventHeadTwo"+idcount;	
							
							eventHeadOne.textContent += replyEvent[k].eventName;
							eventHeadTwo.textContent += replyEvent[k].eventDescription;
							
							eventSuggestions.appendChild(event);
							event.appendChild(eventHeadOne);
							event.appendChild(eventHeadTwo);
						}
						result.onclick = expandResult(result);
					}
					if(replyJ.eventName !== undefined){
						headOne.textContent += replyJ.eventName;
						headTwo.textContent += replyJ.eventDescription;
						result.onclick = expandResult(result);
					}
					result.appendChild(headOne);
					result.appendChild(headTwo);
					result.appendChild(eventSuggestions)

					
				}
			}
		}else{
			idcount = 0;
			for (i in reply){
				replyI = reply[i];
				console.log(replyI)
				idcount ++;
				var result = document.createElement('div');
				result.className ="searchResult";
				var headOne = document.createElement("H1")
				headOne.id = "headOne"+idcount;
				var headTwo = document.createElement("H2")
				headTwo.id = "headTwo"+idcount;	
				
				
				var eventSuggestions = document.createElement("div");
				eventSuggestions.id ="eventSuggestions"+idcount;
				eventSuggestions.className ="eventSuggestions";
				
				document.getElementById('searchResults').appendChild(result);
				if(replyI.bandName !== undefined){
					headOne.textContent += replyI.bandName;
					headTwo.textContent += replyI.bandDescription;
						
					for(k =0; k < replyI.Event.length;k++){
						event = document.createElement("div");
						event.className ="event";
						replyEvent = replyI.Event;
						event.onclick = navigateShell(replyEvent[k].eventIDPK);
						
						var eventHeadOne = document.createElement("H3");
						eventHeadOne.id = "eventHeadTwo"+idcount;
						var eventHeadTwo = document.createElement("H4");
						eventHeadTwo.id = "eventHeadTwo"+idcount;	
						
						eventHeadOne.textContent += replyEvent[k].eventName;
						eventHeadTwo.textContent += replyEvent[k].eventDescription;
						
						eventSuggestions.appendChild(event);
						event.appendChild(eventHeadOne);
						event.appendChild(eventHeadTwo);
					}
					result.onclick = expandResult(result);
					
				}
				if(replyI.venueName !== undefined){
					headOne.textContent += replyI.venueName;
					for(k =0; k < replyI.Event.length;k++){
						event = document.createElement("div");
						event.className ="event";
						replyEvent = replyI.Event;
						event.onclick = navigateShell(replyEvent[k].eventIDPK);
						
						var eventHeadOne = document.createElement("H3");
						eventHeadOne.id = "eventHeadTwo"+idcount;
						var eventHeadTwo = document.createElement("H4");
						eventHeadTwo.id = "eventHeadTwo"+idcount;	
						
						eventHeadOne.textContent += replyEvent[k].eventName;
						eventHeadTwo.textContent += replyEvent[k].eventDescription;
						
						eventSuggestions.appendChild(event);
						event.appendChild(eventHeadOne);
						event.appendChild(eventHeadTwo);
					}
					result.onclick = expandResult(result);
				}
				if(replyI.eventName !== undefined){
					headOne.textContent += replyI.eventName;
					headTwo.textContent += replyI.eventDescription;
					result.onclick = navigateShell(replyI.eventIDPK);
				}
				result.appendChild(headOne);
				result.appendChild(headTwo);
				result.appendChild(eventSuggestions)
			}
		}

	}

}
	

function expandResult(result){
	return function(){
		result.classList.toggle("selectedResult");
		for(i=0; i< result.childNodes.length;i++){
			div = result.childNodes[i].id
			if(div.indexOf("headOne") > -1||div.indexOf("headTwo") > -1){
				
			}else{
				console.log(div);
				element = document.getElementById(div);
				element.style.display = element.style.display == "block" ? "none" : "block";
			}
		}
	}
}