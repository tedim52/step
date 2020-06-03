/* Fetches testimonials from server and add thems to DOM */
window.addEventListener('load', async(e)=> {
    //load testimonials in database
    var response = await fetch("/getTestimonials", {method="GET"});
    var testimonialList = await response.json();
    testimonialList.forEach((testimonial) => {
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
                "kind words":form.elements[2].value };
    console.log(preData);

    var response = await fetch("/testimonials", {
        method:"POST",
        body: formData
    });
    
    var data = await response.json();
    console.log(data);
    showTestimonial(data);
});

/* Displays most recently submitted testimonial onto screen. */
function showTestimonial(json) {
    var section = document.createElement("section");
    var div = document.createElement("div",{class:".testimonials"})
    section.appendChild(div);
    var text = document.createElement("p");
    text.innerHTML = "name: " +json.name + " who: "+json.relationship + " what they have to say: "+json.text;
    div.appendChild(text);
    document.querySelector(".container").appendChild(section);
}

function validateForm() {
    //TODO: validate for to make sure users enter something for all three parts
}
