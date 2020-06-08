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
    var section = document.createElement("section");
    
    var div = document.createElement("div",{class:"testimonials"});
    section.appendChild(div);
    
    var text = document.createElement("p", {class: "text"});
    text.innerHTML = json.name + "   "+json.relationship + "   "+json.text;
    div.appendChild(text);
    
    //add up and downvote links
    var upvoteLink = document.createElement("a",{class:"up"});
    upvoteLink.innerHTML = '<i class="fas fa-arrow-up"></i>';
    div.appendChild(upvoteLink);
    
    var upvoteCount = document.createElement("p",{class:"up-count"});
    upvoteCount.innerHTML = json.upvote;
    div.appendChild(upvoteCount);
    
    var downvoteLink = document.createElement("a");
    downvoteLink.innerHTML = '<i class="fas fa-arrow-down"></i>';
    div.appendChild(downvoteLink);
    
    var downvoteCount = document.createElement("p");
    downvoteCount.innerHTML = "0";
    div.appendChild(downvoteCount);

    document.querySelector(".container").appendChild(section);
}

//TODO: Add onclick listener for upvote, downvote links

