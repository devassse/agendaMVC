<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Contactos</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<header>
		<nav>
			<div>
				<h2>LOGO</h2>
			</div>
			<div>
				<ul>
				  <li><a href="#">Contactos</a></li>
				  <li><a href="#">Eventos</a></li>
				  <li><a href="#">Calendário</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<main>
		<div class="wraper">
			<h1>Editar Contacto</h1>
			<fieldset>
			<legend>Dados do contacto</legend>
			<div>
				<form name="frmContacts" action="doUpdateContact">
					<table>
					<tr>
				     	<td>
				     		<input type="number" name="id" class="inputid" readonly="readonly" value="<% out.print(request.getAttribute("id")); %>">
				     	</td>
				     </tr>
				     <tr>
				     	<td>
				     		<input type="text" name="name" class="inputs" value="<% out.print(request.getAttribute("name")); %>">
				     	</td>
				     </tr>
				     <tr>
				     	<td>
				     		<input type="text" name="phone" class="inputs" value="<% out.print(request.getAttribute("phone")); %>">
				     	</td>
				     </tr>
				     <tr>
				     	<td>
				     		<input type="email" name="email" class="inputs" value="<% out.print(request.getAttribute("email")); %>">
				     	</td>
				     </tr>
				     <tr>
				     	<td>
				     		<a href="main">Voltar</a>
				     	</td>
				     	<td>
				     		<input type="submit" onclick="fieldValidation()" value="Actualizar" class="botao">
				     	</td>
				     </tr>
				    </table>
				</form>
			</div>
			</fieldset>
		</div>
	</main>
	<script type="text/javascript" src="scripts/validator.js"></script>
</body>
</html>