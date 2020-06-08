/* Adds animation to cards on me page.
  Cards rise and show background text. */

const cards = document.querySelectorAll('#card');
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
