<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>

<% 
	 Boolean RegisteredUserRoles = (Boolean) session.getAttribute("RegisteredUserRoles");
	if ((RegisteredUserRoles == null) || (!RegisteredUserRoles.booleanValue()))
	{	
	    response.sendRedirect("./login");
	    return;
	}
	
%>

<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>
<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title>Il mio account - e-comix</title>
<meta name="description" content="Entra nel mondo di ComicShop!">
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
				<li class="active">Il mio account</li>
			</ul>
			<h1>Il mio Account</h1>
			<hr>
			<br>
			<div class="area">


				<h2>
					<a href="order">I Miei Acquisti</a>
				</h2>
				<hr>

				Qui troverai tutti gli ordini da te effettuati nei dettagli.<br>
				<br>
				<h2>
					<a href="wishes">La mia lista desideri</a>
				</h2>
				<hr>
				Qui vi sono tutti i prodotti da te desiderati. <br> <br>
				<h2>
					<a href="client">I miei dati</a>
				</h2>
				<hr>
				In quest'area vi è la possibiità di consultare e modificare i dati
				da te inseriti.<br> <br>

				<h2>
					<a href="logout">Logout</a>
				</h2>
				<hr>

				Qui puoi uscire dalla tua area e abbandonare la sessione.
			</div>
		</div>
		<%@ include file="footer.jsp"%>

	</div>
</body>
</html>