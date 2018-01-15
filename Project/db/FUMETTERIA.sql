/* DATABASE - CREAZIONE */
DROP DATABASE IF EXISTS fumetteria;
CREATE DATABASE fumetteria;
USE fumetteria;

/* TABELLE */
DROP TABLE IF EXISTS Utente;
CREATE TABLE Utente
(
NOME		VARCHAR(15)		NOT NULL,
COGNOME		VARCHAR(15)		NOT NULL,
E_MAIL  	VARCHAR(30)		NOT NULL,
NUM_ORDINI	SMALLINT 		UNSIGNED,
PASS		VARCHAR(20)		NOT NULL,
CAP 		VARCHAR(20)		NOT NULL,
CELLULARE 	VARCHAR(20)		NOT NULL,
CITTA		VARCHAR(20)		NOT NULL,
INDIRIZZO	VARCHAR(20)		NOT NULL,
PROVINCIA	VARCHAR(20)		NOT NULL,		

PRIMARY KEY (E_MAIL)
);

/* ORDINE */
DROP TABLE IF EXISTS Ordine;
CREATE TABLE Ordine
(
DATA_ORDINE			DATE	NOT NULL,
COD_ORDINE			VARCHAR(40) 		NOT NULL,
COSTO_TOTALE   		vArchar(40),
STATO				VARCHAR(20),
PAGAMENTO 			VARCHAR(20),
E_MAIL				VARCHAR(30)		NOT NULL, 	

PRIMARY KEY (COD_ORDINE),
FOREIGN KEY (E_MAIL) REFERENCES Utente(E_MAIL) ON DELETE CASCADE ON UPDATE CASCADE
);

/* GADGET */
DROP TABLE IF EXISTS Gadget;
CREATE TABLE Gadget
(	
COD_GADGET		VARCHAR(40) 	NOT NULL, 			
TIPO			VARCHAR(20) 	NOT NULL, 			
PESO			VARCHAR(20)		NOT NULL,				
COSTO			DOUBLE 			NOT NULL,					
DIMENSIONI 		VARCHAR(10),	
NOME			VARCHAR(30),
PUBLISHER		VARCHAR(24),    				
DESCRIZIONE		VARCHAR(100),
IMMAGINI 		VARCHAR(50),
CONFRONTO		boolean,	
DISPONIBILITA	smallint,
                
PRIMARY KEY	(COD_GADGET)
);

/* FUMETTO */
DROP TABLE IF EXISTS Fumetto;
CREATE TABLE Fumetto
(
COD_FUMETTO 	VARCHAR(40) 	NOT NULL, 
GENERE 			VARCHAR(15),
COSTO			DOUBLE 			NOT NULL,
SCRITTORE		VARCHAR(24)		NOT NULL,
DISEGNATORE 	VARCHAR(24),						
PUBLISHER		VARCHAR(24),  
TIPO			VARCHAR(15),						
TRAMA			VARCHAR(1000) 	NOT NULL, 					
TITOLO			VARCHAR(30),				
NUMERO			VARCHAR(30) NOT NULL,
IMMAGINI 		VARCHAR(50),
CONFRONTO		boolean,
DISPONIBILITA	smallint,

PRIMARY KEY (COD_FUMETTO)
);

/* FUMETTO FORMATO DA */
DROP TABLE IF EXISTS Formato_da;
CREATE TABLE Formato_da
(
COD_FUMETTO 		VARCHAR(40)	NOT NULL,
QUANTITA 			smallint,
COD_ORDINE			VARCHAR(40)	NOT NULL,

PRIMARY KEY (COD_FUMETTO,COD_ORDINE),
FOREIGN KEY (COD_FUMETTO)	REFERENCES	Fumetto(COD_FUMETTO) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (COD_ORDINE) 	REFERENCES 	Ordine(COD_ORDINE) ON DELETE CASCADE ON UPDATE CASCADE
);

/* GADGET ARTICOLATO DA*/
DROP TABLE IF EXISTS Articolato_da;
CREATE TABLE Articolato_da
(
COD_GADGET		VARCHAR(40)	NOT NULL,
QUANTITA 			smallint,
COD_ORDINE		VARCHAR(40)	NOT NULL,

PRIMARY KEY (COD_GADGET,COD_ORDINE),
FOREIGN KEY (COD_GADGET) REFERENCES Gadget(COD_GADGET) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (COD_ORDINE) REFERENCES Ordine(COD_ORDINE) ON DELETE CASCADE ON UPDATE CASCADE
);

/* WISHLIST GADGET */
DROP TABLE IF EXISTS Wishlist_Gadget;
CREATE TABLE Wishlist_Gadget
(
COD_GADGET		VARCHAR(40)		NOT NULL,
E_MAIL			VARCHAR(30)		NOT NULL, 

PRIMARY KEY (COD_GADGET,E_MAIL),
FOREIGN KEY (COD_GADGET) REFERENCES Gadget(COD_GADGET) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (E_MAIL) REFERENCES Utente(E_MAIL) ON DELETE CASCADE ON UPDATE CASCADE
);

/* WISHLIST FUMETTI */
DROP TABLE IF EXISTS Wishlist_Fumetto;
CREATE TABLE Wishlist_Fumetto
(
COD_FUMETTO 	VARCHAR(40) 	NOT NULL,
E_MAIL			VARCHAR(30)		NOT NULL,

PRIMARY KEY (COD_FUMETTO,E_MAIL),
FOREIGN KEY (COD_FUMETTO) REFERENCES Fumetto(COD_FUMETTO) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (E_MAIL) REFERENCES Utente(E_MAIL) ON DELETE CASCADE ON UPDATE CASCADE
);