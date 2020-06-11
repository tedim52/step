var map;
function initMap() {
  map = new google.maps.Map(document.querySelector(".map"), {
    center: { lat: -34.397, lng: 150.644 },
    zoom: 8
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
                    'hAxis': {
                        'textStyle':{color: '#f2f2f2'}
                    },
                    'vAxis': {
                        'textStyle':{color: '#f2f2f2'}
                    }
                };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.querySelector('.chart-div'));
        chart.draw(data, options);
      }

