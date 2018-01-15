<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
	Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
	if ((adminRoles == null) || (!adminRoles.booleanValue()))
	{	
	    response.sendRedirect("./myaccount");
	    return;
	}


	String prova = (String) request.getAttribute("verifica");
	ComicsBean object = (ComicsBean) request.getAttribute("object");
	GadgetBean oggetto = (GadgetBean) request.getAttribute("oggetto");
	
%>



<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>


<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title>Modifica prodotto - e-comix</title>
<meta name="description"
	content="Questo è il tuo profilo utente su e-comix">
<meta name="robots" content="index,follow,noodp" />
<%@ include file="head.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp"%>

		<script>
						function registrazione(){
						alert("Modifca prodotto riuscita");
						}
						</script>

		<div id="sopra"></div>

		<div id="main">
			<ul class="breadcrumb">
				<li><a href="home">Home</a></li>
				<li><a href="managerccount">Manager area</a></li>
				<li class="active">Inserimento Prodotto</li>
			</ul>
			<h1>Modifica Prodotto</h1>
			<hr>
			<br> <br>

			<% if (oggetto.getname().equals("")) {	
	
	%>


			<h2>Modifica Fumetto</h2>
			<hr>
			<form action="modify" method="post" enctype="multipart/form-data">
				<input type="hidden" name="action" value="modifyc">

				<div id="table">
					<div id="row">

						<div id="dati1">
							<p>Codice:</p>
							<input name="code" type="text" maxlength="30"
								value="<%=object.getcode()%>" readonly="readonly" >


							<p>Titolo:</p>
							<input name="titolo" type="text" maxlength="30" required
								value="<%=object.gettitle()%>" onchange="ValidateTitle(titolo)">


							<p>Genere:</p>
							<input name="gender" type="text" maxlength="30" required
								value="<%=object.getgender()%>" onchange="ValidateGender(gender)">

							<p>Disponibilita:</p>
							<input name="availability" type="text" maxlength="30" required
								value="<%=object.getavailability()%>" onchange="ValidateAvailability(availability)">


						</div>

						<div id="dati2">
							<p>Costo:</p>
							<input name="price" type="text" maxlength="30" required
								value="<%=object.getprice()%>" onchange="ValidatePrice(price)">

							<p>Scrittore:</p>
							<input name="writer" type="text" maxlength="30" required
								value="<%=object.getwriter()%>" onchange="ValidateWriter(writer)">

							<p>Disegantore:</p>
							<input name="drawer" type="text" maxlength="30" required
								value="<%=object.getdrawer()%>" onchange="ValidateDrawer(drawer)">

						</div>

						<div id="dati3">

							<p>Publisher:</p>
							<input name="publisher" type="text" maxlength="30" required
								value="<%=object.getpublisher()%>" onchange="ValidatePublisher(publisher)"> <br>

							<p>Trama:</p>
							<textarea name="plot" rows="10" cols="18" required onchange="ValidatePlot(plot)"><%=object.getplot()%>  </textarea>


						</div>

						<div id="dati4">


							<p>Tipo:</p>
							<input name="tipo" type="text" maxlength="30" required
								value="<%=object.gettype()%>" onchange="ValidateType(tipo)"> <br>
							<p>Numero:</p>
							<input name="number" type="text" maxlength="30" required
								value="<%=object.getnumber()%>" onchange="ValidateNumber(number)">

							<p>Immagine:</p>
							<label class="fileContainer"> <span id="scritta">Clicca
									qui per caricare l'immagine</span> <input type="file" name="filename"
								accept="image/jpeg, image/png">
							</label><br> <br>
						</div>
					</div>
				</div>

				<div id="privacy2">
					<p id="send2">
						<input type="submit" name="submit" value="Conferma modifica"
							onclick="registrazione()">
					</p>
				</div>
			</form>


			<%	}	else if (object.gettitle().equals("")){	
	
	%>

			<h2>Inserimento Gadget</h2>
			<hr>
			<form action="modify" method="post" enctype="multipart/form-data">
				<input type="hidden" name="action" value="modifyg">


				<div id="table">
					<div id="row">

						<div id="dati1">
							<p>Codice:</p>
							<input name="codeg" type="text" maxlength="30"
								value="<%=oggetto.getcode()%>" readonly="readonly">


							<p>Nome:</p>
							<input name="nome" type="text" maxlength="30" required
								value="<%=oggetto.getname()%>" onchange="ValidateTitle(nome)">


							<p>Tipo:</p>
							<input name="typeg" type="text" maxlength="30" required
								value="<%=oggetto.gettype()%>" onchange="ValidateType(typeg)">

						</div>



						<div id="dati2">
							<p>Peso:</p>
							<input name="weight" type="text" maxlength="30" required
								value="<%=oggetto.getweight()%>" onchange=" ValidateWeight(weight)">

							<p>Dimensioni:</p>
							<input name="dimension" type="text" maxlength="30" required
								value="<%=oggetto.getdimension()%>" onchange="ValidateDimension(dimension)">

							<p>Costo:</p>
							<input name="priceg" type="text" maxlength="30" required
								value="<%=oggetto.getprice()%>" onchange="ValidatePrice(priceg)">


						</div>

						<div id="dati3">

							<p>Publisher:</p>
							<input name="publisherg" type="text" maxlength="30" required
								value="<%=oggetto.getpublisher()%>" onchange="ValidatePublisher(publisherg)"> <br>


							<p>Immagine:</p>
							<label class="fileContainer"> <span id="scritta">Clicca
									qui per caricare l'immagine</span> <input type="file" name="filename"
								accept="image/jpeg, image/png">
							</label><br> <br>

						</div>
						<div id="dati4">
							<p>Disponibilita:</p>
							<input name="availabilityg" type="text" maxlength="30" required
								value="<%=oggetto.getavailability()%>" onchange="ValidateAvailability(availabilityg)">

							<p>Descrizione:</p>
							<textarea name="description" rows="10" cols="18" required onchange="ValidateDimension(description)"> <%=oggetto.getdescription()%></textarea>
						</div>
					</div>
				</div>

				<div id="privacy2">
					<p id="send2">
						<input type="submit" name="submit" value="Conferma modifica"
							onclick="registrazione()">
					</p>
				</div>
			</form>
			<%
			}		
	
	%>
		</div>
		<%@ include file="footer.jsp"%>

	</div>


</body>
</html>