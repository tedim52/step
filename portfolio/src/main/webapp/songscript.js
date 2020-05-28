// Plays random song from array of random song
// Going to attempt to use spotify web api to get songs form my playlists


const headphone = document.querySelector('.suggest')

headphone.addEventListener('click', randomSong(headphone))

function randomSong() {
  var songs = ["Pursuit of Happniess", "Redstripe Rhapsody", "Be a Googler"];
  var covers = ["post.jpeg", "astro.jpg", "funk.jpg"];
  var song = songs[Math.random()*3];
  var art =covers[Math.random()*3];

  document.querySelector(".card").setAttribute("src", "/images/"+art);
  document.querySelector(".card-text").innerHTML = song;
}

//Add hover effect to card
const cards = document.querySelectorAll(".card");
for(var i = 0; i < cards.length; i++){
  cards[i].addEventListener('mouseenter', showText(cards[i]));
  cards[i].addEventListener('mouseleave', hideText(cards[i]));
}

function showText(elem) {
  return function(){
    text = elem.querySelector('.card-text');
    text.style.display = "block";
    text.style.transform = "translateY(-100px)";
    text.style.transition = "transform .25s ease";
  }
}

function hideText(elem) {
  return function(){
    text = elem.querySelector('.card-text');
    text.style.display = "none";
  }
}
