<%@ page import="sun.security.provider.VerificationProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%	
	Cookie attempt = new Cookie("attempt", "");
	Cookie[] cookies = request.getCookies();
	for (Cookie cookie: cookies) {
		if ((cookie.getName()).equals("attempt")) {
			if(cookie.getValue().equals("true")) {
				attempt.setValue("true");
			}
			else if (cookie.getValue().equals("false")) {
			}
		}

			}

	String ver= (String) request.getAttribute("verifica");
	
%>

<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>
<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title>Accedi/Registrati - e-comix</title>
<meta name="description" content="Entra nel mondo di ComicShop!">
<meta name="robots" content="noodp" />
<%@ include file="head.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp"%>

		<div id="sopra"></div>

		<div id="main">
			<ul class="breadcrumb">
				<li><a href="home">Home</a></li>
				<li><a href="login">Autenticazione</a></li>
				<li class="active">Recupero Password</li>

			</ul>
			<h1>Recupero Password</h1>
			<hr>
			<p>Inserisci l'indirizzo email usato per registrarti. Riceverai
				una email con la tua nuova password.</p>



			<div id="recovery2">
				<form action="Send" method="get">
					<input type="hidden" name="action" value="recovery"> <input
						type="email" name="email2" onchange="ValidateEmail(email2)"> <input type="submit"
						name="check" value="Invia"> <br>

				</form>
				<% 	if (ver.equals("false")) { %>
				<h4>E-mail non esisente nel database, ricontrollare.</h4>
				<%}%>

			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>