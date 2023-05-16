<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style>
      .flag {
        max-width: 90px;
      }
    </style>
    <title>Aéroports par pays</title>
  </head>
  <body>
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
    <div class="container">
      <h1 class="text-center mb-19">Aéroports par pays </h1>
      <table class="table table-hover">
        <thead class="thead-light">
        </thead>
        <tbody>
          <!-- Rows with airport information go here -->
          <c:forEach var="a" items="${aeroports}">
          <tr>
            <td><a href="T?pays=${a.pays}"> <img class="flag" src="${a.photoPays }" alt="Drapeau du pays"> ${a.pays} </a></td>
            <td>${a.nbrPiste} airoports</td>
            
          </tr>			   
		</c:forEach>

        </tbody>
      </table>
    </div>
  </body>
</html>