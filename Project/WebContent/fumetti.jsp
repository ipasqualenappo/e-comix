<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 	
	Collection<?> comics = (Collection<?>) request.getAttribute("comics");
	ComicsBean comic = (ComicsBean) request.getAttribute("comic");
%>
<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>

<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title>Fumetti - e-comix</title>
<meta name="description"
	content="Tutti i nostri fumetti e le novità delle principali case editrici">
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
				<li class="active">Fumetti</li>
			</ul>

			<h1>Fumetti</h1>
			<hr>

			<div id="table">
				<div id="row">

					<div id="parametri">

						<h3>Genere:</h3>

						<form action="comic" method="post">
							<select name="genere" size="1">
								<option>Filtra per genere</option>
								<option value="shonen">Shonen</option>
								<option value="seinen">Seinen</option>
								<option value="avventura">Avventura</option>
								<option value="comico">Comico</option>
							</select> <br>
							<h3>Publisher:</h3>

							<select name="publisher" size="1">
								<option>Filtra per publisher</option>
								<option value="bao">Bao Publishing</option>
								<option value="planet">Planet Manga</option>
								<option value="star">Star Comics</option>
								<option value="flashbook">Flashbook</option>
								<option value="magicpress">Magicpress</option>
							</select> <br>
							<h3>Tipo:</h3>

							<select name="tipo" size="1">
								<option>Filtra per tipo</option>
								<option value="manga">Manga</option>
								<option value="comics">Comics</option>
								<option value="franco">Franco/Belga</option>
								<option value="novel">Graphic Novel</option>
							</select> <input type="reset"
								onclick="window.location.href='http://localhost:8080/ProgettoWeb/comic';"
								name="comic" name="comic" value="Reset"> <input
								type="submit" name="comic" value="Aggiorna">
						</form>
						<br>
					</div>


					<div id="prodotti">
						<h3>Risultati</h3>
						<hr>
						<div id="prodotti" class="bx-viewport"
							style="width: 100%; overflow: hidden; position: relative; height: 400px;">
							<div class="slider1"
								style="width: 155%; position: relative; z-index: 0; transition-duration: 0s; transform: translate3d(-630px, 0px, 0px);">

								<%
						if (comics != null && comics.size() != 0 ) {
						Iterator<?> it = comics.iterator();
						while (it.hasNext() ) {
						ComicsBean bean = (ComicsBean) it.next();
						
					%>
								<p class="slide" style="margin-left: 5px; margin-right: 5px;">
									<a href="object?action=read&id=<%=bean.getcode()%>"> <img
										src=<%=bean.getimage()%> width="140" height="205"> <br>
										<br> <%=bean.gettitle()%> <%=bean.getnumber()%>
									</a>
								</p>


								<%
						}
					 	%>
							</div>
						</div>
						<%} else { 
						%>

						<p id="else">Seleziona almeno un filtro prima di aggiornare la
							pagina.</p>

						<%
						}
						%>



					</div>

				</div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>