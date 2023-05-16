<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <h1 class="text-center">Informations sur l'aéroport</h1>
                <div class="row">
                    <div class="col-md-15">
                        <div class="card">
                            <img src="${aeroport.photo}" class="card-img-top" alt="Image de l'aéroport">
                            <div class="card-body">
                                <h5 class="card-title">${aeroport.nomAeroport}</h5>
                                <p class="card-text">OACI: ${aeroport.codeOACI}</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-15">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Informations générales</h5>
                                <ul class="list-group list-group-flush">
                                <li class="list-group-item">Ville: New York</li>
                                <li class="list-group-item">Pays: ${aeroport.pays}</li>
                                <!-- <li class="list-group-item">
                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#weatherModal">
                                Météo actuelle
                                </button>
                                </li> -->
                                </ul>
                                </div>
                                </div>
                                <!-- Modal  -->
                                <div class="modal fade" id="weatherModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Météo actuelle</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                                </button>
                                </div>
                                <div class="modal-body">
                                ...
                                </div>
                                <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                                </div>
                                </div>
                                </div>
                                </div>
                                </div>
                                </div>
                                <h2 class="text-center">Informations sur les pistes</h2>
                                <div class="row">
                                <div class="col-md-12">
                                <div id="accordion">
                                <div class="card">
                                <div class="card-header" id="headingOne">
                                <h5 class="mb-0">
                                <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    Piste 1
                                    </button>
                                    </h5>
                                    </div>
                                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                                    <div class="card-body">
                                    <table class="table">
                                    <tbody>
                                    <tr>
                                    <td>Longueur:</td>
                                    <td>3200 m</td>
                                    </tr>
                                    <tr>
                                    <td>Largeur:</td>
                                    <td>45 m</td>
                                    </tr>
                                    <tr>
                                    <td>Surface:</td>
                                    <td>Asphalte</td>
                                    </tr>
                                    </tbody>
                                    </table>
                                    </div>
                                    </div>
                                    </div>
                                    <div class="card">
                                    <div class="card-header" id="headingTwo">
                                    <h5 class="mb-0">
                                    <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Piste 2
                                    </button>
                                    </h5>
                                    </div>
                                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                                    <div class="card-body">
                                    <table class="table">
                                    <tbody>
                                    <tr>
                                    <td>Longueur:</td>
                                    <td>2800 m</td>
                                    </tr>
                                    <tr>
                                        <td>Largeur:</td>
                                        <td>45 m</td>
                                    </tr>
                                    <tr>
                                        <td>Surface:</td>
                                        <td>Béton</td>
                                    </tr>
                            </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
            </div>
        </div>
        <!-- </div> -->
        </div>
        <!-- Footer-->
        <!-- <footer class="py-5 bg-dark"> -->
        <footer class="py-3 bg-dark">
            
            <div class="container px-4 px-lg-5"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2022</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script> -->
        <!-- Core theme JS-->
        <!-- <script src="js/scripts.js"></script> -->
    </body>
</html>