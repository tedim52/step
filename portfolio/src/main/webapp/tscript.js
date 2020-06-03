
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

    //display testimonial onto screen
});

//function to display testimonial
//function to validate form
