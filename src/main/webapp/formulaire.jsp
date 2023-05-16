<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
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
            </div></nav>
        
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-6">
      <form  action="Map" method="post">
        <div class="form-group">
          <label for="exampleInputEmail1">Aeroport de depart</label>
			<select class="form-control" id="exampleFormControlSelect1" name="arpDep">
			<c:forEach var="a" items="${aeroports}">
			    <option value="${a.idAeroport}">${a.nomAeroport}</option>
			   
			    </c:forEach>
			  </select>
			 
        </div>
        <div class="form-group">
          <label for="exampleInputPassword1">Aeroport d'arrivee</label>
			<select class="form-control" id="exampleFormControlSelect1" name="arpArv">
			    <c:forEach var="a" items="${aeroports}">
			    <option value="${a.idAeroport}">${a.nomAeroport}</option>
			   
			     </c:forEach>
			  </select>
		</div>
        <button type="submit" class="btn btn-primary">Envoyer</button>
      </form>
    </div>
  </div>
</div>
     
</body>
</html>