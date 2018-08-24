function dropdown() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
function setFilter(item){
    filter = document.getElementById("dropbtn").textContent;
    document.getElementById("dropbtn").innerHTML = item + ' <i class="fa fa-angle-down"></i>';
    listOfItems = document.getElementById("myDropdown");
    console.log(filter);
}
function search(page){
  input = document.getElementById("searchTerm").value;
  filter = document.getElementById("dropbtn").textContent;
  console.log(input);
  console.log(filter);
  var queryString = "?para1=" + input + "&para2=" + filter;
  window.location.href = page+".html" + queryString;  
}