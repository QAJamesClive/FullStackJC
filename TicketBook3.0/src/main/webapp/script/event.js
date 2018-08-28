
getData()
initMap()
function getData(){
	var queryString = decodeURIComponent(window.location.search);
    queryString = queryString.substring(1);
    var queries = queryString.split("&");
    var searchList = queries[0].split("=");
    var search = searchList[1];
    console.log(search);
}
var map;
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -34.397, lng: 150.644},
    zoom: 8
  });
}