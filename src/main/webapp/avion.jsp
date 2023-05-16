<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="doa.Avion" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="style1.css" rel="stylesheet" />
    </head>
    <body>
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
        <div class="container px-6 px-lg-5">
            <!-- <div class="container-fluid"> -->
                <h1 class="text-center">Informations sur l'avion</h1>
                <div class="row">
                    <div class="col-md-15">
                        <div class="card">
                           <img src="${avion.photo}" class="card-img-top" alt="Image de l'aéroport">
                            <div class="card-body">
                            
                                <h5 class="card-title">${avion.nom}</h5>
                                <p class="card-text">type: ${avion.type}</p>
                                
                                 <p class="card-text">Capacite de carburant : ${avion.capaciteCarburant} litres </p>
                                 <p class="card-text">Altitude de croisière : ${avion.croisiereAltitude} m</p>
                                 <p class="card-text">Vitesse de croisière : ${avion.cruisingSpeed} km/h</p> 
                                 <p class="card-text">Autonomie: Environ  ${avion.distanceVolMax} km</p> 
                                 
                         
                            </div>
                        </div>
                    </div>
                    
                    </div>

                               
        <!-- </div> -->
        </div>
        <!-- Footer-->
        <!-- <footer class="py-5 bg-dark"> -->
        

        </footer>
        <!-- Bootstrap core JS-->
        <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script> -->
        <!-- Core theme JS-->
        <!-- <script src="js/scripts.js"></script> -->
    </body>
</html>