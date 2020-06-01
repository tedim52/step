/*Plays random song from array of random song
  Going to attempt to use spotify web api to get songs form my playlists */


//Retrieving access token

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
//Instantiating Spotify Wrapper JM-Perez

//Retrieves the song icon and adds random song functionality on click
const headphone = document.querySelector('.suggest');
headphone.addEventListener('click', randomSong());


function randomSong() {
  var songs = ["Too Young", "Stargazing", "Cash Out"];
  var covers = ["post.jpg", "astro.jpg", "funk.jpg"];

  random = Math.floor(Math.random() * Math.floor(3));//pulled from MDN Math.random() page
  var song = songs[random];
  var art = covers[random];

  card = document.querySelector(".cards");
  card.setAttribute("src", "/images/"+ art);
  card.addEventListener('mouseenter', showText(song));
  card.addEventListener('mouseleave', hideText());
}

function showText(song) {
  return function(){
    text = document.querySelector('.card-text');
    text.innerHTML = song;
    text.style.display = "block";
    text.style.transform = "translate(-250px,150px)";
  }
}

function hideText() {
  return function(){
    text = document.querySelector('.card-text');
    text.style.display = "none";
  }
}
