<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
	if ((adminRoles == null) || (!adminRoles.booleanValue())) {
		response.sendRedirect("./myaccount");
		return;
	}
%>

<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>
<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title>Area Gestore - e-comix</title>
<meta name="description" content="Questa è l'area gestore.">
<meta name="robots" content="noodp" />
<%@ include file="head.jsp"%>
</head>
<body>

	<%@ include file="navbar.jsp"%>
	<div class="container">

		<div id="sopra"></div>

		<div id="main">
			<ul class="breadcrumb">
				<li><a href="home">Home</a></li>
				<li class="active">Manager area</li>
			</ul>
			<h1>Manager Area</h1>
			<hr>

			<div class="area">
				<br> <br>
				<h2>
					<a href="aggiungiprodotto">Inserisci Prodotto</a>
				</h2>
				<hr>
				Qui puoi inserire nuovi prodotti da vendere <br> <br> <br>

				<h2>
					<a href="deleteproduct">Controllo Prodotti</a>
				</h2>
				<hr>
				In quest'area è possibile eliminare o modificare i prodotti in
				vendita <br> <br> <br>
				<h2>
					<a href="banned">Elimina Utente</a>
				</h2>
				<hr>
				In quest'area è possibile eliminare gli utenti<br> <br> <br>
				<h2>
					<a href="order">Visualizza Ordini</a>
				</h2>
				<hr>
				Qui troverai tutti gli ordini effettuati dagli utenti <br> <br>
				<br>
				<h2>
					<a href="logout">Logout</a>
				</h2>
				<hr>
				Qui puoi uscire dalla tua area e abbandonare la sessione.
			</div>
			<br> <br>
		</div>
		<%@ include file="footer.jsp"%>

	</div>
</body>
</html>