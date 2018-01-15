<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>
<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title>Contattaci - e-comix</title>
<meta name="description"
	content="Hai dei problemi con un ordine o col nostro sito? Contattaci e ti risponderemo il prima possibile">
<meta name="robots" content="index,follow,noodp" />
<%@ include file="head.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp"%>
		<div id="sopra"></div>
		<div id="main">
			<ul class="breadcrumb">
				<li><a href="home">Home</a></li>
				<li class="active">Contatti</li>
			</ul>
			<h1>Invia messaggio</h1>
			<hr>
			<form action="mail" method="get">
				<input type="hidden" name="action" value="mailus">
				<div id="table">
					<div id="row">

						<div id="mittente">

							Indirizzo e-mail: <br> <br> <input type="text"
								id="email" name="email2" required="required"> <br>
							<br> Oggetto del messaggio: <br> <br> <input
								type="text" id="objectmessage" name="object" required="required">
						</div>
						<div id="messaggio">
							Messaggio: <br> <br>
							<textarea id="message" name="message" rows="10" cols="90"></textarea>
						</div>
					</div>
				</div>
				<div  id="send">
						<input type="submit" value="Invia">
				</div>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>