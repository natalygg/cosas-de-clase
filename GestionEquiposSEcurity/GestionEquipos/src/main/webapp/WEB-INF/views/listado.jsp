<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		Nuevo equipo:
		<form action="altaequipo" method="POST">
			<label for="tipo">Tipo:</label> 
			<select name="tipo">
				<c:forEach items="${tipos}" var="tipo">
					<option value="${tipo.name()}">${tipo.toString()}</option>
				</c:forEach>
			</select> 
			<br /> 
			<label for="ubicacion">Ubicacion:</label> 
			<input type="text"
				name="ubicacion" /> 
				<br />
			<label for="modelo">Modelo:</label> 
			<input type="text" name="modelo" /> 
			<br /> 
			<label for="fechaInstalacion">Fecha	de instalacion:</label> 
			<input type="date" name="fechaInstalacion" /> 
			<br />
			<button type="submit">Alta</button>
		</form>
	</div>
	<div>
		Listado de equipos existentes.

		<table>
			<tr>
				<td>Tipo</td>
				<td>Ubicacion</td>
				<td>Modelo</td>
				<td>Fecha de instalación</td>
				<td></td>
			</tr>
			<c:forEach items="${lista}" var="e">
				<tr>
					<td><a href="editar?id=${e.id}">${e.stringTipo}</a></td>
					<td><a href="editar?id=${e.id}">${e.ubicacion}</a></td>
					<td><a href="editar?id=${e.id}">${e.modelo}</a></td>
					<td><a href="editar?id=${e.id}">${e.fechaInstalacion}</a></td>
					<td><a href="borrar?id=${e.id}" style="color: red;">Borrar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>