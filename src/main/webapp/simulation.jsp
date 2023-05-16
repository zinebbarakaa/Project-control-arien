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
        //faire le carte
        const map = L.map('map').setView([0, 0], 1);
        var CartoDB_Positron = L.tileLayer('https://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors &copy; <a href="https://carto.com/attributions">CARTO</a>',
        subdomains: 'abcd',
        maxZoom: 19
        }).addTo(map);
        // tiles.addTo(map);
        
        //faire el marker et l'icon
        const myIcon = L.icon({
            iconUrl: 'avion1.png',
            iconSize: [50, 32],
            iconAnchor: [25, 16],
            
        });
        const marker=L.marker([0, 0], {icon: myIcon}).addTo(map);
        const marker2=L.marker([0, 0], {icon: myIcon}).addTo(map);
        var codeOci=["GMMN","LFPG","GMAD","LFPO","LIRF","KEF"]
        const marker1=L.marker([0, 0], {icon: myIcon}).addTo(map);
        const marker3=L.marker([0, 0], {icon: myIcon}).addTo(map);
        const marker4=L.marker([0, 0], {icon: myIcon}).addTo(map);
        const marker5=L.marker([0, 0], {icon: myIcon}).addTo(map);
        const marker6=L.marker([0, 0], {icon: myIcon}).addTo(map);
        const marker7=L.marker([0, 0], {icon: myIcon}).addTo(map);
        const marker8=L.marker([0, 0], {icon: myIcon}).addTo(map);
        //aeroport

        const arp1 = L.icon({
            iconUrl: 'airport1.png',
            iconSize: [30,30],
            //iconAnchor: [35, 26],
            
        });
        const markerarp1=L.marker([31.7917, 7.0926], {icon: arp1}).addTo(map);
        var info1 = "code OACI: <a href=\"http://\"/>" + codeOci[0] + "</a>  <br> latitude: " + 31.7917 + "  <br> longtitude: " + 7.0926 ;
        markerarp1.bindPopup(info1).addTo(map);
        
        const markerarp3=L.marker([ 26.8206,30.8025], {icon: arp1}).addTo(map);
        var info3 = "code OACI: <a href=\"http://\"/>" + codeOci[2] + "</a>  <br> latitude: " + 26.8206 + "  <br> longtitude: " + 30.8025 ;
        markerarp3.bindPopup(info3).addTo(map);
       
        const markerarp4=L.marker([39.8283,98.5795], {icon: arp1}).addTo(map);
        var info4 = "code OACI: <a href=\"http://\"/>" + codeOci[3] + "</a>  <br> latitude: " +  39.8283 + "  <br> longtitude: " + 98.5795 ;
        markerarp4.bindPopup(info4).addTo(map);

        const markerarp5=L.marker([56.1304,106.3468], {icon: arp1}).addTo(map);
        var info5 = "code OACI: <a href=\"http://\"/>" + codeOci[4] + "</a>  <br> latitude: " + 56.1304 + "  <br> longtitude: " +106.3468 ;
        markerarp5.bindPopup(info5).addTo(map);

        const markerarp6=L.marker([50.5039, 4.4699], {icon: arp1}).addTo(map);
        var info6 = "code OACI: <a href=\"http://\"/>" + codeOci[5] + "</a>  <br> latitude: " +50.5039+ "  <br> longtitude: " + 4.4699 ;
        markerarp6.bindPopup(info6).addTo(map);

        const markerarp7=L.marker([-14.2350,-51.9253], {icon: arp1}).addTo(map);
        var info7 = "code OACI: <a href=\"http://\"/>" + codeOci[5] + "</a>  <br> latitude: " + -14.2350+ "  <br> longtitude: " + -51.9253 ;
        markerarp7.bindPopup(info7).addTo(map);

        const markerarp8=L.marker([25.3548,  51.1839], {icon: arp1}).addTo(map);
        var info8 = "code OACI: <a href=\"http://\"/>" + codeOci[5] + "</a>  <br> latitude: " + 25.3548+ "  <br> longtitude: " +   51.1839 ;
        markerarp8.bindPopup(info8).addTo(map);

        const markerarp9=L.marker([40.730610, -73.935242], {icon: arp1}).addTo(map);
        var info9 = "code OACI: <a href=\"http://\"/>" + codeOci[5] + "</a>  <br> latitude: " + 40.730610+ "  <br> longtitude: " + -73.935242 ;
        markerarp9.bindPopup(info9).addTo(map);

        const markerarp10=L.marker([49.193901, -123.180517], {icon: arp1}).addTo(map);
        var info10 = "code OACI: <a href=\"http://\"/>" + codeOci[5] + "</a>  <br> latitude: " + 49.193901+ "  <br> longtitude: " + -123.180517 ;
        markerarp10.bindPopup(info10).addTo(map);
        

        const data = [
{"latitude":33.3675,"longitude":-7.58997,"altitude":415.2903586629},
{"latitude":49.009691,"longitude":2.547925,"altitude":415.24682784435},
  {"latitude":30.3299,"longitude":-9.4102,"altitude":415.22968226978},
 {"latitude":48.7451193,"longitude":2.401373,"altitude":415.22968226978},
 {"latitude":41.799887,"longitude":12.246238,"altitude":415.22968226978}
]

       
        
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

var intermediatePoints = getIntermediatePoints(31.7917, 7.0926, 26.8206,30.8025, 10);
console.log(intermediatePoints);
var intermediatePointsline = getIntermediatePoints(49.193901,-123.180517,50.5039, 4.4699, 20);
console.log(intermediatePointsline);
var intermediatePoints1 = getIntermediatePoints(49.193901,-123.180517, 40.73061,-73.935242,20);
console.log(intermediatePoints1);
var intermediatePoints2 = getIntermediatePoints(-14.235, -51.9253, 40.73061,-73.935242,20);
console.log(intermediatePoints2);
var intermediatePoints3 = getIntermediatePoints(40.73061,-73.935242, -14.235, -51.9253,20);
console.log(intermediatePoints2);
var intermediatePoints4 = getIntermediatePoints(-14.235, -51.9253, 31.7917, 7.0926,20);
console.log(intermediatePoints4);
var intermediatePoints5 = getIntermediatePoints(25.3548,  51.1839,56.1304,106.3468,20);
console.log(intermediatePoints5);
var intermediatePoints6 = getIntermediatePoints(25.3548,51.1839, 39.8283,98.5795,10);
console.log(intermediatePoints6);
var intermediatePoints7 = getIntermediatePoints(50.5039, 4.4699,26.8206,30.8025,20);
console.log(intermediatePoints7);
var intermediatePoints8 = getIntermediatePoints(39.8283,98.5795,50.5039, 4.4699,20);
console.log(intermediatePoints8);
var polylineline = L.polyline([intermediatePointsline[0], intermediatePointsline[21]], {color: 'red'}).addTo(map);


let index = 0;
let firstTime= true;
var speed = 0; // en km/h

var fuel = 3000; // en litres

var fuel1 = 2000; // en litres
var altitude = 0; // en mètres
var altitude1 = 0; // en mètres

var currentSpeed=100;
var fuelConsumption = 10;
var fuelConsumptionfuite = 90;
var i = 0;
var startTime = Date.now();
let  nv=0;
let  inx=0;

async function getData(){
var currentAltitude = 50 ;
var currentAltitudecol = 100 ;
var currentSpeed = 100 ;

if (i < (intermediatePoints1.length / 2)  ) {
    altitude += currentAltitude;
    speed += currentSpeed; 
    fuelConsumption = speed*0.05;
    fuel -= fuelConsumption;
    altitude1 += currentAltitudecol;
    fuel1 -= fuelConsumptionfuite;
}
else{
    altitude -= currentAltitude; 
    speed -= currentSpeed; 
    fuelConsumption = speed*0.05;
    fuel -= fuelConsumption;
    altitude1 -= currentAltitudecol; 
    fuel1 -= fuelConsumptionfuite;
}

i++;

console.log(i);



const aspect = 1.5625;
const w = (altitude * aspect) / 10;
const h = altitude / 10;
myIcon.options.iconSize = [w, h];
myIcon.options.iconAnchor = [w / 2, h / 2];

var d = new Date();
var time1 =   d.getMinutes() + ":" + d.getSeconds();

marker1.setLatLng([intermediatePoints1[index].lat,intermediatePoints1[index].lon]);
marker2.setLatLng([intermediatePoints2[index].lat,intermediatePoints2[index].lon]);
marker3.setLatLng([intermediatePoints3[index].lat,intermediatePoints3[index].lon]);
marker4.setLatLng([intermediatePoints4[index].lat,intermediatePoints4[index].lon]);
marker5.setLatLng([intermediatePoints5[index].lat,intermediatePoints5[index].lon]);
marker7.setLatLng([intermediatePoints7[index].lat,intermediatePoints7[index].lon]);
marker8.setLatLng([intermediatePoints8[index].lat,intermediatePoints8[index].lon]);



var info1 = "Vitesse: " + speed + " km/h <br> Consommation de carburant: " + fuel1 + " litres <br> latitude : " + intermediatePoints1[index].lat +" mètres  <br> longtitude: " +intermediatePoints1[index].lon+ "mètres  <br> altitude:"+altitude+" mètres <br> temps:"+time1+"  UTC ";
marker1.bindPopup(info1).addTo(map);

var info2 = "Vitesse: " + speed + " km/h <br> Consommation de carburant: " + fuel + " litres <br> latitude : " + intermediatePoints2[index].lat +" mètres  <br> longtitude: " +intermediatePoints2[index].lon+ "mètres  <br> altitude:"+altitude+" mètres <br> temps:"+time1+"  UTC ";
marker2.bindPopup(info2).addTo(map);

var info3 = "Vitesse: " + speed + " km/h <br> Consommation de carburant: " + fuel + " litres <br> latitude : " + intermediatePoints3[index].lat +" mètres  <br> longtitude: " +intermediatePoints3[index].lon+ "mètres  <br> altitude:"+altitude1+" mètres <br> temps:"+time1+"  UTC ";
marker3.bindPopup(info3).addTo(map);

var info4 = "Vitesse: " + speed + " km/h <br> Consommation de carburant: " + fuel + " litres <br> latitude : " + intermediatePoints4[index].lat +" mètres  <br> longtitude: " +intermediatePoints4[index].lon+ "mètres  <br> altitude:"+altitude+" mètres <br> temps:"+time1+"  UTC ";
marker4.bindPopup(info4).addTo(map);

var info5 = "Vitesse: " + speed + " km/h <br> Consommation de carburant: " + fuel + " litres <br> latitude : " + intermediatePoints5[index].lat +" mètres  <br> longtitude: " +intermediatePoints5[index].lon+ "mètres  <br> altitude:"+altitude+" mètres <br> temps:"+time1+"  UTC ";
marker5.bindPopup(info5).addTo(map);


var info7 = "Vitesse: " + speed + " km/h <br> Consommation de carburant: " + fuel + " litres <br> latitude : " + intermediatePoints7[index].lat +" mètres  <br> longtitude: " +intermediatePoints7[index].lon+ "mètres  <br> altitude:"+altitude+" mètres <br> temps:"+time1+"  UTC ";
marker7.bindPopup(info7).addTo(map);

var info8 = "Vitesse: " + speed + " km/h <br> Consommation de carburant: " + fuel + " litres <br> latitude : " + intermediatePoints7[index].lat +" mètres  <br> longtitude: " +intermediatePoints7[index].lon+ "mètres  <br> altitude:"+altitude+" mètres <br> temps:"+time1+"  UTC ";
marker8.bindPopup(info8).addTo(map);



if(nv< intermediatePoints1.length-1){
    var fuelLeak = L.circle([intermediatePoints1[nv].lat,intermediatePoints1[nv].lon], {
    color: 'red',
    fillColor: '#f03',
    fillOpacity: 0.5,
    radius: 500
}).addTo(map);



var polyline1 = L.polyline([intermediatePoints1[nv], intermediatePoints1[nv+1]], {color: 'blue'}).addTo(map);


var polyline2 = L.polyline([intermediatePoints2[nv], intermediatePoints2[nv+1]], {color: 'blue'}).addTo(map);


var polyline3 = L.polyline([intermediatePoints3[nv], intermediatePoints3[nv+1]], {color: 'blue'}).addTo(map);


var polyline4 = L.polyline([intermediatePoints4[nv], intermediatePoints4[nv+1]], {color: 'blue'}).addTo(map);

var polyline5 = L.polyline([intermediatePoints5[nv], intermediatePoints5[nv+1]], {color: 'blue'}).addTo(map);


var polyline7 = L.polyline([intermediatePoints7[nv], intermediatePoints7[nv+1]], {color: 'blue'}).addTo(map);

var polyline8 = L.polyline([intermediatePoints8[nv], intermediatePoints8[nv+1]], {color: 'blue'}).addTo(map);

nv++;
}
        if(firstTime){
            map.setView([30.3299,-9.4102],3);
            firstTime=false;
        }
            // console.log(index);
            index++;
            
            if(index === intermediatePoints1.length){
                clearInterval(counter);
            }
            
}
getData();
let counter=setInterval(getData,3000);
var nc=0;
var altitude4 = 0; // en mètres
var indx=0;
var speed4 = 0; // en km/h
var fuel4 = 3000; // en litres
var currentAltitude4 = 50 ;
var currentSpeed4 = 100 ;
async function getData1(){

if (nc < (intermediatePoints.length / 2)  ) {
    altitude4 += currentAltitude4;
    speed4 += currentSpeed4; 
    fuelConsumption4 = speed4*0.05;
    fuel4 -= fuelConsumption4;
}
else{
    altitude4 -= currentAltitude4; 
    speed4 -= currentSpeed4; 
    fuelConsumption4 = speed4*0.05;
    fuel4 -= fuelConsumption4;
}
nc++;

// console.log(i);



const aspect = 1.5625;
const w = (altitude * aspect) / 10;
const h = altitude / 10;
myIcon.options.iconSize = [w, h];
myIcon.options.iconAnchor = [w / 2, h / 2];

var d = new Date();
var time1 =   d.getMinutes() + ":" + d.getSeconds();


marker.setLatLng([intermediatePoints[indx].lat,intermediatePoints[indx].lon]);
marker6.setLatLng([intermediatePoints6[indx].lat,intermediatePoints6[indx].lon]);



var info = "Vitesse: " + speed4 + " km/h <br> Consommation de carburant: " + fuel4 + " litres <br> latitude : " + intermediatePoints[index].lat +" mètres  <br> longtitude: " +intermediatePoints[index].lon+ "mètres  <br> altitude:"+altitude4+" mètres <br> temps:"+time1+"  UTC ";
marker.bindPopup(info).addTo(map);

var info6 = "Vitesse: " + speed4 + " km/h <br> Consommation de carburant: " + fuel4 + " litres <br> latitude : " + intermediatePoints6[index].lat +" mètres  <br> longtitude: " +intermediatePoints6[index].lon+ "mètres  <br> altitude:"+altitude4+" mètres <br> temps:"+time1+"  UTC ";
marker6.bindPopup(info6).addTo(map);


if(inx < intermediatePoints.length -1){
    var polyline = L.polyline([intermediatePoints[inx], intermediatePoints[inx+1]], {color: 'blue'}).addTo(map);
    var polyline6 = L.polyline([intermediatePoints6[inx], intermediatePoints6[inx+1]], {color: 'blue'}).addTo(map);
inx++;
}
        
    indx++;
    // console.log(indx);
    if(indx === intermediatePoints.length){
        clearInterval(counter1);
    }
            
}
        
        getData1();
        let counter1=setInterval(getData1,1000);
    </script>
    <!-- Footer-->
    <footer class="footer navbar-fixed-bottom" style="background-color: burlywood;">
    <div class="container">
      <p class="text-muted">Copyright © Mon Site 2021</p>
    </div>
  </footer>
  
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script></body>
</html>
