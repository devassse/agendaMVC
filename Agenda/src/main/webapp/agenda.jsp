<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.JavaBeans" %>
    <%@ page import="java.util.ArrayList" %>
    <% ArrayList<JavaBeans> listContacts = (ArrayList<JavaBeans>) request.getAttribute("contacts"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Contactos</title>
<link rel="stylesheet" href="style.css ">
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
			<h1>Listagem de Contactos</h1>
			
			<table id="table">
			<thead>
				<tr>
					<th>#</th>
					<th>Nome</th>
					<th>Telefone</th>
					<th>E-mail</th>
					<th colspan="2">Ações</th>
				</tr>
			</thead>
			<tbody>
				<% for(int i = 0; i<listContacts.size();i++) { %>
					<tr>
						<td><%=listContacts.get(i).getId()%></td>
						<td><%=listContacts.get(i).getNome()%></td>
						<td><%=listContacts.get(i).getPhone()%></td>
						<td><%=listContacts.get(i).getEmail()%></td>
						<td><a href="updateContact?id=<%=listContacts.get(i).getId()%>">Actualizar</a></td>
						<td><a href="javascript:confirmDelete(<%=listContacts.get(i).getId()%>)">Remover</a></td>
					</tr>
				<% } %>
			    	
			</tbody>
			</table>
			
			<a href="new" class="botao">Novo contacto</a>
			<a href="report" target="_blank" class="botao">Relatório</a>
		</div>
	</main>
	<script type="text/javascript" src="scripts/confirm.js"></script>
</body>
</html>