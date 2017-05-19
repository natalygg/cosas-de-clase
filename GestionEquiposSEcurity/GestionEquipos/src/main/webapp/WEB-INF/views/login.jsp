<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${param.error ne null}">
	DAtos de acceso erroneos,
</c:if>
<c:if test="${param.logout ne null}">
	Ha termninado su sesion.
</c:if>
<form action="hacerlogin" method="POST">
	<label for="username" >Nombre:</label>
	<input type="text" name="username"/>
	<br/>
	<label for="password">Contraseña: </label>
	<input type="password" name="password">
	<br/>
	<button type="submit">Entrar</button>
</form>
</body>
</html>