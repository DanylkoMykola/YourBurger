
var mymap = L.map('mapid').setView([49.326284, 23.520156], 11);
var markerStebnyk = L.marker([49.300592, 23.555163]).addTo(mymap);
var markerDrogobych = L.marker([49.347491, 23.512689]).addTo(mymap);
var polygon = L.polygon([
    [49.280307, 23.406380],
    [49.385450, 23.447703],
    [49.391503, 23.559496],
    [49.337983, 23.619677],
    [49.214927, 23.660548],
    [49.268823, 23.399823]
]).addTo(mymap);
markerStebnyk.bindPopup("<b>Стебник Грушевського 6/1</b><br>+380680668277").openPopup();
markerDrogobych.bindPopup("<b>Дрогобич Війтівська 64<br>Гора </b><br>+380680668278").openPopup();
polygon.bindPopup("<b>Доставка здійснюється<br>в цих межах");

L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
    maxZoom: 18,
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
        '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
        'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    id: 'mapbox/streets-v11',
    tileSize: 512,
    zoomOffset: -1
}).addTo(mymap);

