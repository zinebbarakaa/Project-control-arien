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