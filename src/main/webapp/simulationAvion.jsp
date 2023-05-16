<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="doa.Aeroport" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.3/dist/leaflet.css"
    integrity="sha256-kLaT2GOSpHechhsozzB+flnD+zUyjE2LlfWPgU04xyI="
    crossorigin=""/>
     <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <!-- Core theme CSS (includes Bootstrap)-->
    <!-- Make sure you put this AFTER Leaflet's CSS -->
    <script src="https://unpkg.com/leaflet@1.9.3/dist/leaflet.js"
    integrity="sha256-WBkoXOwTeyKclOHuWtc+i2uENFpDZ9YPdf5Hf+D7ewM="
    crossorigin=""></script>
    <link href="style1.css" rel="stylesheet" />
    <style>
        #map { height: 1260px; }
    </style>
    <title>Document</title>
</head>
<body>

<%

String mesObjetsJson = new Gson().toJson(request.getAttribute("chemin"));
%>

<!-- Responsive navbar-->
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container px-10">
            
                <!-- <a class="navbar-brand" href="#!">Start Bootstrap</a> -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-9 mb-lg-1">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="Simulation">Simulateur</a></li>
                        <li class="nav-item"><a class="nav-link" href="formulaire">Vol</a></li>
                        <li class="nav-item"><a class="nav-link" href="Presentation">Aiport</a></li>
                        <li class="nav-item"><a class="nav-link" href="Avions">Avion</a></li>
                    </ul>
                </div>
            </div>
        </nav>
<!-- Page Content-->


   <!--  <label >altitude</label>
    <span id="altitude"></span>
    <label >vitesse</label>
        <span id="vitesse"></span>
        <label >carburant</label>
        <span id="carburant"></span>
        <label >temps</label>
        <span id="temps"></span> --> 
    <div id="map">
    </div>

<script>
const data =[];
var mesObjets = JSON.parse('<%= mesObjetsJson %>');
for (let i = 0; i < mesObjets.length ; i++) {
	  data.push({
	    lat: mesObjets[i].latitude,
	    lon: mesObjets[i].longtitude
	  });
	}
	console.log(data);
        //faire le carte
        const map = L.map('map').setView([0, 0], 1);
        var codeOci=["GMMN","LFPG","GMAD","LFPO","LIRF","KEF"]
        var CartoDB_Positron = L.tileLayer('https://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors &copy; <a href="https://carto.com/attributions">CARTO</a>',
            subdomains: 'abcd',
            maxZoom: 19
            }).addTo(map);
        //faire el marker et l'icon
        const myIcon = L.icon({
            iconUrl: 'avion1.png',
            iconSize: [50, 32],
            iconAnchor: [25, 16],
            
        });
        const marker=L.marker([0, 0], {icon: myIcon}).addTo(map);

        //aeroport

        const arp1 = L.icon({
            iconUrl: 'airport1.png',
            iconSize: [30,30],
            //iconAnchor: [35, 26],
            
        });
        const markerarp1=L.marker([data[0].lat, data[0].lon], {icon: arp1}).addTo(map);
        var info1 = "code OACI: <a href=\"http://\"/>" + codeOci[0] + "</a>  <br> latitude: " + data[0].lat + "  <br> longtitude: " + data[0].lon ;
        markerarp1.bindPopup(info1).addTo(map);

        const markerarp4=L.marker([data[1].lat,data[1].lon], {icon: arp1}).addTo(map);
        var info4 = "code OACI: <a href=\"http://\"/>" + codeOci[3] + "</a>  <br> latitude: " + data[1].lat + "  <br> longtitude: " + data[1].lon;
        markerarp4.bindPopup(info4).addTo(map);
        
        
        
        function getIntermediatePoints(lat1, lon1, lat2, lon2, nbPoints) {
            let  obj_init={
                lat:lat1,
                lon:lon1 
            }
            let  obj_fin={
                lat:lat2,
                lon:lon2 
            }
            let clonedObject = Object.assign({}, obj_init);
            let clonedObject1 = Object.assign({}, obj_fin);
        // convertir les degrés en radians
        lat1 = (lat1 * Math.PI) / 180;
        lon1 = (lon1 * Math.PI) / 180;
        lat2 = (lat2 * Math.PI) / 180;
        lon2 = (lon2 * Math.PI) / 180;

        // formule de Haversine pour le calcul de la distance
        var dlon = lon2 - lon1;
        var dlat = lat2 - lat1;
        var a =
            Math.pow(Math.sin(dlat / 2), 2) +
            Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var R = 6371; // rayon de la terre
        var distance = R * c;

        // calculer les intermédiaires points
        var points = [];
        for (var i = 1; i <= nbPoints; i++) {
            var f = i / (nbPoints + 1);
            var A = Math.sin((1 - f) * c) / Math.sin(c);
            var B = Math.sin(f * c) / Math.sin(c);
            var x = A * Math.cos(lat1) * Math.cos(lon1) + B * Math.cos(lat2) * Math.cos(lon2);
            var y = A * Math.cos(lat1) * Math.sin(lon1) + B * Math.cos(lat2) * Math.sin(lon2);
            var z = A * Math.sin(lat1) + B * Math.sin(lat2);
            var lat = Math.atan2(z, Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
            var lon = Math.atan2(y, x);
            points.push({
            lat: (lat * 180) / Math.PI,
            lon: (lon * 180) / Math.PI
            });
        }
        points.unshift({
            lat: clonedObject.lat,
            lon: clonedObject.lon
            });
        points.push({
            lat: clonedObject1.lat,
            lon: clonedObject1.lon
            });
        return points;
        }

    var intermediatePoints = getIntermediatePoints(data[0].lat, data[0].lon, data[1].lat, data[1].lon, 20);
    console.log(intermediatePoints);
    

    


let index = 0;
let firstTime= true;
var speed = 0; // en km/h
var fuel = 1000; // en litres
var altitude = 0; // en mètres
var currentSpeed=100;
var fuelConsumption = 10;
    // Variables pour le contrôle de l'animation
    var i = 0;
    //var prevCoord = coords[i];
    var startTime = Date.now();
   let  nv=0;
async function getData(){

var currentAltitude = 50 ;
var currentAltitudecol = 100 ;
var currentSpeed = 100 ;
if (i<  (intermediatePoints.length / 2) ) {
    altitude += currentAltitude;
    speed += currentSpeed; 
    fuelConsumption = speed*0.05;
    fuel -= fuelConsumption;
}
else{
    altitude -= currentAltitude; 
    speed -= currentSpeed; 
    fuelConsumption = speed*0.05;
    fuel -= fuelConsumption;
}

i++;
const aspect = 1.5625;
const w = (altitude * aspect) / 10;
const h = altitude / 10;
myIcon.options.iconSize = [w, h];
myIcon.options.iconAnchor = [w / 2, h / 2];

var d = new Date();
var time1 =   d.getMinutes() + ":" + d.getSeconds();

marker.setLatLng([intermediatePoints[index].lat,intermediatePoints[index].lon]);
var info = "Vitesse: " + speed + " km/h <br> Consommation de carburant: " + fuel + " litres <br> latitide:" +intermediatePoints[index].lat+ " mètres <br> longtitude:"+intermediatePoints[index].lon+" mètres <br> Altitude: " + altitude + "  mètres <br> temps:"+time1+" UTC";
marker.bindPopup(info).addTo(map);
if(nv< intermediatePoints.length-1){
    
var polyline = L.polyline([intermediatePoints[nv], intermediatePoints[nv+1]], {color: 'blue'}).addTo(map);

nv++;
}

        if(firstTime){
                map.setView([30.3299,-9.4102],3);
                firstTime=false;
            }
            index++;
            console.log(index);
            if(index === intermediatePoints.length){
                clearInterval(counter);
            }
    }
        getData();
        let counter=setInterval(getData,1000);
    </script>
    <!-- Footer-->
    <footer class="py-5 bg-dark">
        <div class="container px-4 px-lg-5"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2022</p></div>
    </footer>
    <script src="index2.js"></script>
</body>
</html>
