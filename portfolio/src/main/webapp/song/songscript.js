/*Plays random song from array of random song
  Going to attempt to use spotify web api to get songs form my playlists */

// Retrieves the song icon and adds random song functionality on click
const headphone = document.querySelector('.suggest');
headphone.addEventListener('click', randomSong());

/* Produces album cover and song name from given arrays and 
    outputs them to be shown on random song generator page */
async function randomSong() {
  /*
  var songs = ["Too Young", "Stargazing", "Cash Out"];
  var covers = ["post.jpg", "astro.jpg", "funk.jpg"];

  random = Math.floor(Math.random() * Math.floor(3)); 
  var song = songs[random];
  var art = covers[random]; */
  console.log("Random Song initiated");
  card = document.querySelector(".cards");
  //card.setAttribute("src", "/images/"+ art); just set art to be constant to playlist cover

  //Retrieve playlist song and image from backend
  response = await fetch("/randomsong");
  song = await response.text()
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
