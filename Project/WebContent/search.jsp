<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> comics = (Collection<?>) request.getAttribute("comics");
	ComicsBean comic = (ComicsBean) request.getAttribute("comic");

	Collection<?> gadgets = (Collection<?>) request.getAttribute("gadgets");
	GadgetBean gadget = (GadgetBean) request.getAttribute("gadget");
%>
<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>
<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title>Risultati Ricerca - e-comix</title>
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
				<li class="active">Risultati Ricerca</li>
			</ul>
			<h1>Risultati Ricerca</h1>
			<hr>
			<div id="table">
				<div id="row">

					<div id="categoria">
						<h3>Categorie</h3>
						<hr>
						<br> <br> <a href="comic"> Vai a Fumetti</a>
						<hr>
						<br> <a href="gadget"> Vai a Gadget</a>
						<hr>
					</div>

					<div id="prodotti">
						<h3>Risultati Ricerca </h3>
						<hr>
						<%
							boolean si = false;

							if (comics != null && comics.size() != 0) {
								Iterator<?> it = comics.iterator();
								si = true;
								while (it.hasNext()) {
									ComicsBean bean = (ComicsBean) it.next();
						%>
						<p>
							<a href="object?action=read&id=<%=bean.getcode()%>"> <img
								src=<%=bean.getimage()%> width="140" height="205"> <br>
								<br> <%=bean.gettitle()%> <%=bean.getnumber()%>
							</a>
						</p>
						<%
							}
							}
						%>
						<%
							if (gadgets != null && gadgets.size() != 0) {
								Iterator<?> it = gadgets.iterator();
								si = true;
								while (it.hasNext()) {
									GadgetBean bean = (GadgetBean) it.next();
						%>
						<p>
							<a href="oggetto?action=read&id=<%=bean.getcode()%>"> <img
								src=<%=bean.getimage()%> width="140" height="140"> <br>
								<%=bean.gettype()%> <br> <%=bean.getname()%>
							</a>
						</p>
						<%
							}
							}
						%>
						<%
							if (si == false) {
						%>
						<p id="else">La ricerca non ha prodotto alcun risultato.</p>
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