/*Plays random song from array of random song
  Going to attempt to use spotify web api to get songs form my playlists */



//Retrieves the song icon and adds random song functionality on click
const headphone = document.querySelector('.suggest')
headphone.addEventListener('click', randomSong())


function randomSong() {
  var songs = ["Pursuit of Happiness", "Redstripe Rhapsody", "Be a Googler"];
  var covers = ["post.jpg", "astro.jpg", "funk.jpg"];

  random = Math.floor(Math.random() * Math.floor(3));//pulled from MDN Math.random() page
  var song = songs[random];
  var art = covers[random];

  card = document.querySelector(".cards");
  card.setAttribute("src", "/images/"+art);
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
