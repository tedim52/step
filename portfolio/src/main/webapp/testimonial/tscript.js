/* Fetches testimonials from server and add thems to DOM */
window.addEventListener('load', async(e)=> {
    e.preventDefault();
    console.log("Page has loaded");
    var response = await fetch("/getTestimonials");
    var testimonialList = await response.json();
    console.log(testimonialList);
    testimonialList.forEach((testimonial) => {
        console.log(testimonial);
        showTestimonial(testimonial);
    });

});

/* Retrieves submissions from form on submit
    and puts them on the screen for the user to see */
const form = document.getElementsByTagName("form")[0];
form.addEventListener('submit', async(e) => {
    e.preventDefault();
    
    var formData = new FormData(form);
    
    preData = { "name": form.elements[0].value, 
                "relationship":form.elements[1].value, 
                "text":form.elements[2].value };
    
    if(validateForm(preData)){
        var response = await fetch("/testimonials", {
            method:"POST",
            body: formData
        });
        
        data = await response.json();
        console.log(data);
        if(data.sentiment == "Positive") {
            showTestimonial(data);      
        }  
    } else {
        alert("Please fill out form.");
    }
});

function validateForm(json) {
    if(json.name=="" ||json.relationship=="" || json.text=="") {
        return false;
    } else {
        return true;
    }
}

/* Displays most recently submitted testimonial onto screen. */
function showTestimonial(json) {
    var section = document.createElement("section", {class:"testimonials"});
    
    var div = document.createElement("div");
    section.appendChild(div);
    
    var text = document.createElement("p", {class: "text"});
    text.innerHTML = json.name + "   "+json.relationship + "   "+json.text;
    div.appendChild(text);
    
    var countDiv = document.createElement("div", {class:"vote"});
    section.appendChild(countDiv);
    
    //Add up and downvote links
    var upvoteLink = document.createElement("a",{class:"up"});
    upvoteLink.innerHTML = '<i class="fas fa-arrow-up"></i>';
    countDiv.appendChild(upvoteLink);
    
    var upvoteCount = document.createElement("p",{class:"up-count"});
    upvoteCount.innerHTML = json.upvote;
    countDiv.appendChild(upvoteCount);
    
    var downvoteLink = document.createElement("a");
    downvoteLink.innerHTML = '<i class="fas fa-arrow-down"></i>';
    countDiv.appendChild(downvoteLink);
    
    var downvoteCount = document.createElement("p");
    downvoteCount.innerHTML = "0";
    countDiv.appendChild(downvoteCount);

    document.querySelector(".container").appendChild(section);
    //<section class ="testimonial">
    //  <div class="text">
    //      <p>testimonial info</p>
    //  <div>
    //  <div class="vote">
    //      <a class="up"><i class = "fas fa-arrow-up"></i></a>
    //      <p class ="up-count">upvote count</p>
    //      <a><i class = "fas fa-arrow-down"></i></a>
    //      <p>downvote count</p>
    //  </div>
    //</section>
}

/* UPVOTE/DOWNVOTE Feature Deprecated
//TODO: Add onclick listener for upvote, downvote links
document.querySelectorAll(".up").forEach((up) => {
    up.addEventListener("click", (e)=> {
        var text = up.parentElement.parentElement.getElementdByClassName(".text")[0].innerText;
        data = {testimonial: text};
        //post request to increment vote count sending the text in json to retrieve proper entity
        var upVoteCount = await fetch("/vote", {
            method:"POST",
            headers: {
                'Content-Type':'application/json';
            },
            body:JSON.stringify(data)
        })
        up.parentElement.getElementdByClassName("up-count").innerText = upVoteCount.count;
    });
});
*/