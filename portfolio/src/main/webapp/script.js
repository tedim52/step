// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.




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
