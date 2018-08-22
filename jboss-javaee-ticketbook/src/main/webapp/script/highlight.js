function hideSection(section){
    document.getElementById(section+"Content").style.display ="inline";
    document.getElementById(section+"Show").style.display="inline"
    document.getElementById(section+"Hide").style.display="none"
    document.getElementById(section+"Show").style.textAlign="center";
}
function showSection(section){
    document.getElementById(section+"Content").style.display="inline";
    document.getElementById(section+"Hide").style.display="inline"
    document.getElementById(section+"Show").style.display="none"
    document.getElementById(section+"Hide").style.textAlign="center";
}

var highlights = [
    { title: 'Item 1', image: "img/gig.jpeg", description: "A description" },
    { title: 'Item 2', image: "img/gig.jpeg", description: "Another decription" },
];

for(i=0;i<highlights.length;i++){
    highlightContent = document.getElementById('highlightContent');
    highlightSection = document.createElement('div');
    highlightSection.innerHTML = "<h2>" + highlights[i].title + "</h2><img src='" + highlights[i].image + "'><p>" + highlights[i].description + "</p>";
    highlightSection.id = "Item";
    highlightContent.appendChild(highlightSection);
}