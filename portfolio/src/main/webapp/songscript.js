/*Plays random song from array of random song
  Going to attempt to use spotify web api to get songs form my playlists */




/*
var retriever = new XMLHttpRequest();
retriever.open('POST', 'https://accounts.spotify.com/api/token')
var authToken;
retriever.onreadystatechange = function() {
  if (retriever.readyState == 4 && retriever.status == 200) {//Request was successful
    authToken = retriever.response;
    console.log(authToken);
  }
};
retriever.setRequestHeader('Authorization', 'Basic MjBiMDRjNDE4Mjc5NGQ1NmEzMTgxZTI5ZDZlNThhZTM6ZWJmNzRjMmYyN2YzNGQxMWJhMmFhMzhiMDU0ZDg0MjU=');
retriever.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
retriever.send("grant_type=client_credentials");

*/


// Retrieves the song icon and adds random song functionality on click
const headphone = document.querySelector('.suggest');
headphone.addEventListener('click', randomSong());

/* Produces album cover and song name from given arrays and 
    outputs them to be shown on random song generator page */
function randomSong() {
  var songs = ["Too Young", "Stargazing", "Cash Out"];
  var covers = ["post.jpg", "astro.jpg", "funk.jpg"];

  random = Math.floor(Math.random() * Math.floor(3)); 
  var song = songs[random];
  var art = covers[random];

  card = document.querySelector(".cards");
  card.setAttribute("src", "/images/"+ art);
  card.addEventListener('mouseenter', showText(song));
  card.addEventListener('mouseleave', hideText());
}

/* Changes card text displayed */
function showText(song) {
  return function(){
    text = document.querySelector('.card-text');
    text.innerHTML = song;
    text.style.display = "block";
    text.style.transform = "translate(-250px,150px)";
  }
}

/* Changes card text to be hidden */
function hideText() {
  return function(){
    text = document.querySelector('.card-text');
    text.style.display = "none";
  }
}
