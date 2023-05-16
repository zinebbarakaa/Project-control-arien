<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.google.gson.Gson" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="doa.Aeroport" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>map</title>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/p5.js/0.7.3/p5.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/p5.js/0.7.3/addons/p5.dom.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/mappa-mundi@0.0.4"></script>
	
</head>
<body>
<%

String mesObjetsJson = new Gson().toJson(request.getAttribute("chemin"));
%>

<script type="text/javascript">
//const data = [
	//{"latitude":33.3675,"longitude":-7.58997,"altitude":415.2903586629},
	//{"latitude":49.009691,"longitude":2.547925,"altitude":415.24682784435},
	  //{"latitude":30.3299,"longitude":-9.4102,"altitude":415.22968226978},
	// {"latitude":48.7451193,"longitude":2.401373,"altitude":415.22968226978},
	// {"latitude":41.799887,"longitude":12.246238,"altitude":415.22968226978}
//	]
const data =[];
var mesObjets = JSON.parse('<%= mesObjetsJson %>');
for (let i = 0; i < mesObjets.length ; i++) {
	  data.push({
	    latitude: mesObjets[i].latitude,
	    longitude: mesObjets[i].longtitude
	  });
	}
	console.log(data);
	let index=0;
	/*function increneter(obj_init,obj_fin){
	  
	  let arraylist=[];
	  let clonedObject = Object.assign({}, obj_init);
	 arraylist.unshift(obj_init);
	  let obf=obj_fin;
	  let list;
	  let arr1;
	  for (let index = 0; index < 10; index++) {
	    list=arraylist.map(function (){
	      return objj={
	        latitude:clonedObject.latitude +=0.05,
	        longitude:clonedObject.longitude +=0.05    }
	    });
	    arr1=list.filter(function(el){
	      return el.latitude <= obf.latitude && el.longitude <= obf.longitude ?  el:""
	    })
	    arraylist=arraylist.concat(arr1);
	    console.log(arraylist);
	  }
	  
	  arr1.unshift(obj_init);
	  arraylist.push(obj_fin);
	  return arraylist;
	}*/
	
	function pointInter(obj_init,obj_fin)
	{
		
		const EARTH_RADIUS = 6371; // rayon de la Terre en kilomètres
		const lat1 = obj_init.latitude;
		const lon1 = obj_init.longitude;
		const lat2 = obj_fin.latitude;
		const lon2 = obj_fin.longitude;

		const dLat = (lat2 - lat1) * Math.PI / 180;
		const dLon = (lon2 - lon1) * Math.PI / 180;
		const lat1Rad = lat1 * Math.PI / 180;
		const lat2Rad = lat2 * Math.PI / 180;

		const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
		          Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1Rad) * Math.cos(lat2Rad);
		const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		const distance = EARTH_RADIUS * c; // distance en kilomètres

		const n = 10 ; // nombre de points à trouver
		let data=[];
		for (let i = 0; i < n; i++) {
		  const fraction = i / (n - 1);
		  const A = Math.sin((1 - fraction) * c) / Math.sin(c);
		  const B = Math.sin(fraction * c) / Math.sin(c);
		  const x = A * Math.cos(lat1Rad) * Math.cos(lon1) + B * Math.cos(lat2Rad) * Math.cos(lon2);
		  const y = A * Math.cos(lat1Rad) * Math.sin(lon1) + B * Math.cos(lat2Rad) * Math.sin(lon2);
		  const z = A * Math.sin(lat1Rad) + B * Math.sin(lat2Rad);
		  const lat = Math.atan2(z, Math.sqrt(x * x + y * y)) * 180 / Math.PI;
		  const lon = Math.atan2(y, x) * 180 / Math.PI;
		 
		 data.push({
			    latitude: lat,
			    longitude: lon
			  });
		  console.log(lat, lon); // coordonnées du point intermédiaire
		}
		 data.unshift({
			    latitude: lat1,
			    longitude: lon1
			  });
		 
		 data.push({
			    latitude: lat2,
			    longitude: lon2
			  });
		 
		 return data;
		
	}
	 //var data1=pointInter(obj_init,obj_fin);
	//console.log(data1);
	let obj_init;
	let obj_fin;
	let obj_init1;
	let obj_fin1;
	let array,array1;
	if(data.length === 2){
		 obj_init=data[0];
		 obj_fin=data[1];
		array=pointInter(obj_init,obj_fin);
	}
	else{
			 obj_init=data[0];
			 obj_fin=data[1];
			 obj_init1=data[1];
			 obj_fin1=data[2];
			 array=pointInter(obj_init,obj_fin);
			 array1=pointInter(obj_init1,obj_fin1);
	}

	
	console.log(array);

	
	var mappa = new Mappa("Leaflet");
	let mymap;
	let issImg;
	let firstTime = true;
	let history = [];
	let history1 = [];
	let counter;

	function preload() {
	issImg = loadImage("avion.png");
	issImg1 = loadImage("avion.png");
	marker = loadImage("airport1.png");

	}
	let leng=array.length;

	function setup() {
	    canvas = createCanvas(1500, 700);
	    let obj1 = array[index];
	    if (firstTime) {
	      const options = {
	      lat: obj1.latitude,
	      lng: obj1.longitude,
	      zoom: 3,
	      style: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
	      };
	      mymap = mappa.tileMap(options);
	      firstTime = false;
	    }
	   
	      
	     
	      mymap.overlay(canvas);

	      getData();
	     
	      counter=setInterval(getData, 1000);
	      if(data.length > 2)
	      getData1();
	      
	}

	let index1=0;
	async function getData() {
	   
	        mymap.onChange(render);
	        history.push(array[index]);
	        render();
	        index++;
	        //index1++;
	        // leng=array.length;
	        if((index === leng)){
	            clearInterval(counter);
	            if(data.length > 2)
	            counter1=setInterval(getData1, 1000);
	        }
	        
	}
	async function getData1() {
	   
	        mymap.onChange(render);
	        //history.push(array[index]);
	        history1.push(array1[index1]);
	        render();
	        //index++;
	        index1++;
	        //let leng=array.length;
	        let leng1=array1.length;
	        if((index1 === leng1)){
	            clearInterval(counter1);
	        }
	        
	}

	let arp=data;
	function render() {
	    clear();

	  // Draw a path
	    strokeWeight(mymap.zoom());
	    stroke(100);
	    noFill();
	  
	    beginShape();
	    for (let data of history) {
	      
	        const pix = mymap.latLngToPixel(data.latitude, data.longitude);
	        vertex(pix.x, pix.y);
	        
	    }
	   
	    
	    for (let data of  arp) {
	      
	      const pos = mymap.latLngToPixel(data.latitude, data.longitude);
	      point(pos.x, pos.y);
	       image(marker, pos.x, pos.y, 30, 30)
	      } noFill();
	      
	      endShape();
	      if(data.length > 2){
	    	  beginShape();
	   	   for (let data of history1) {
	   	      
	   	    const pix1 = mymap.latLngToPixel(data.latitude, data.longitude);
	   	   vertex(pix1.x, pix1.y);
	   	  }
	   	  endShape();
	   	  const current1 = history1[history1.length - 1];
	   	   const pix1 = mymap.latLngToPixel(current1.latitude, current1.longitude);
	   	    image(issImg, pix1.x, pix1.y, 20, 20);
	    	  
	      }
	     

	  // Get the last spot is current
	    const current = history[history.length - 1];
	    const pix = mymap.latLngToPixel(current.latitude, current.longitude);
	    imageMode(CENTER);
	    image(issImg, pix.x, pix.y, 20, 20);
	    
	}

</script>
</body>
</html>