// Initialize and add the map
var map;
async function initMap() {

    // Initialize map on world
    map = new google.maps.Map(
      document.getElementById('map'), {
          zoom: 1, 
          minZoom:1,
          center: new google.maps.LatLng(0, 0)
    });
    
    //Fetch wine locations and data from backend
    var data = await fetch("/wines");
    var wineData = await data.json();
    console.log(wineData);

    var infoWindow = new google.maps.InfoWindow;
    wineData.forEach(async function(wine) {
        //Create wine info window with information
        var infowincontent = document.createElement('div');
        var text = document.createElement('strong');
        text.textContent = wine.province +": "+ wine.description +" "+ wine.designation;
        infowincontent.appendChild(text);
        
        //Retrieve coordinates using Geocode API
        var location = await fetch('https://maps.googleapis.com/maps/api/geocode/json?address='
                                    +wine.province+'&key=AIzaSyAKkWb1JRbNIja-AdTJ2upmSy9bFLK6b5A');                            
        var locationJSON = await location.json();
        var lat = locationJSON.results[0].geometry.location.lat;
        var lng = locationJSON.results[0].geometry.location.lng;
        var latlng = { lat: lat, lng: lng};
        console.log(latlng);

        //Add marker to specified coordinate with info window
        var marker = new google.maps.Marker({
            map:map, 
            position: latlng
        });
        
        marker.addListener('click', function() {
            infoWindow.setContent(infowincontent);
            infoWindow.open(map, marker);
        });
    });
}


// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Wine');
    data.addColumn('number', 'Rating');
    data.addRows([
        ['SAUVIGNON BLANC', 3],
        ['CHARDONNAY', 1],
        ['CHAMPAGNE', 1],
        ['MERLOT', 1]
    ]);

    // Set chart options
    var options = {
                    'width':900,
                    'height':450,
                    'colors': ['#fc85ae', '#9e579d', '#574b90', '#303a52'],
                    'backgroundColor': '#0B0C10',
                    'opacity':100,
                    'pieHole': .4,
    };

    // Instantiate and draw chart with specified options
    var chart = new google.visualization.PieChart(document.querySelector('.chart-div'));
    chart.draw(data, options);
}

