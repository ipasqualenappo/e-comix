<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> orders = (Collection<?>) request.getAttribute("orders");
	Collection<?> gadgets = (Collection<?>) request.getAttribute("gadgets");
	Collection<?> comics = (Collection<?>) request.getAttribute("comics");

	String email = (String) request.getAttribute("email");
%>

<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>


<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title>Compara prodotti - e-comix</title>
<meta name="description"
	content="Compara i nostri prodotti e scegli il più conveniente per te">
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
				<li class="active">Confronto</li>
			</ul>
			<h1>Confronto</h1>
			<hr>

			<div id="tabella" style="overflow-x: auto;">

				<%
					int i = 0;
					if (comics.size() > 0 || gadgets.size() > 0) {
				%>

				<h2>Primo prodotto</h2>
				<hr>
				<br>

				<table>
					<tr>
						<th>Immagine</th>
						<th>Riferimento</th>
						<th>Prezzo</th>
						<th>Categoria</th>
						<th>Visualizza Prodotto</th>
						<th>Elimina</th>

					</tr>
					<%
						if (comics != null && comics.size() != 0) {
								Iterator<?> it = comics.iterator();
								i++;
								while (it.hasNext() && i < 2) {

									ComicsBean bean = (ComicsBean) it.next();
									i++;
					%>
					<tr>
						<td><img src=<%=bean.getimage()%> width=100px height=150px></td>

						<td><%=bean.gettitle()%> n.<%=bean.getnumber()%></td>
						<td><%=bean.getprice()%> €</td>

						<td><%=bean.getgender()%></td>
						<td><a href="object?action=read&id=<%=bean.getcode()%>">Dettagli</a></td>
						<td><a href="confronto?action=delete&id=<%=bean.getcode()%>"
							onclick="removeconfront()">Elimina</a></td>
					</tr>
					<%
						}
							}
					%>
					<%
						if (gadgets != null && gadgets.size() != 0) {
								Iterator<?> it = gadgets.iterator();
								i++;
								while (it.hasNext() && i < 2) {

									GadgetBean bean = (GadgetBean) it.next();
									i++;
					%>
					<tr>
						<td><img src=<%=bean.getimage()%> width=150px height=150px></td>

						<td><%=bean.getname()%></td>
						<td><%=bean.getprice()%> €</td>


						<td><%=bean.gettype()%></td>
						<td><a href="oggetto?action=read&id=<%=bean.getcode()%>">Dettagli</a></td>

						<td><a href="confronto?action=delete&id=<%=bean.getcode()%>"
							onclick="removeconfront()">Elimina</a></td>

						<%
							}

								}
						%>
					
				</table>

				<%
					} else {
				%>
				<h3>Non sono stati aggiunti articoli al confronto</h3>



				<%
					}
					if (gadgets.size() + comics.size() == 2) {
				%>
				<br> <br>
				<h2>Secondo Prodottto</h2>
				<hr>
				<br>
				<table>
					<tr>
						<th>Immagine</th>
						<th>Riferimento</th>
						<th>Prezzo</th>
						<th>Categoria</th>
						<th>Visualizza Prodotto</th>
						<th>Elimina</th>

					</tr>

					<%
						if (i == 2)
								if (comics != null && comics.size() != 0) {
									Iterator<?> it = comics.iterator();

									while (it.hasNext() && i < 5) {
										ComicsBean bean = (ComicsBean) it.next();
										i++;
										if (i == 5 || i == 4) {
					%>
					<tr>
						<td><img src=<%=bean.getimage()%> width=100px height=150px></td>

						<td><%=bean.gettitle()%> n.<%=bean.getnumber()%></td>
						<td><%=bean.getprice()%> €</td>

						<td><%=bean.getgender()%></td>
						<td><a href="object?action=read&id=<%=bean.getcode()%>">Dettagli</a></td>
						<td><a href="confronto?action=delete&id=<%=bean.getcode()%>"
							onclick="removeconfront()">Elimina</a></td>

					</tr>
					<%
						}
									}
								}
					%>

					<%
						if (gadgets != null && gadgets.size() != 0) {
								Iterator<?> it = gadgets.iterator();
								while (it.hasNext() && i < 5) {

									GadgetBean bean = (GadgetBean) it.next();
									i++;
									if (i == 5 || i == 4) {
					%>

					<tr>
						<td><img src=<%=bean.getimage()%> width=150px height=150px></td>

						<td><%=bean.getname()%></td>
						<td><%=bean.getprice()%> €</td>


						<td><%=bean.gettype()%></td>
						<td><a href="oggetto?action=read&id=<%=bean.getcode()%>">Dettagli</a></td>
						<td><a href="confronto?action=delete&id=<%=bean.getcode()%>"
							onclick="removeconfront()">Elimina</a></td>

					</tr>

					<%
						}
								}
							}
					%>
				</table>
				<%
					}
				%>

			</div>

		</div>
		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>