function ValidateEmail(email)
{
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(email.value.match(mailformat))
	{
		return true;
	}
	else
	{
		alert("Hai inserito un indirizzo e-mail non valido!");
		email.focus();
		return false;
	}
}

function ValidatePassword(name)
{
	var letters1=/[a-zA-Z0-9]{5,10}/;
	if(name.value.match(letters1))
	{
		return true;
	}
	else
	{
		alert("Hai inserito una password che non rispecchia i parametri richiesti");
		name.focus();
		return false;
	}
}


function ValidateName(name)
{
	var letters1= /^([a-zA-Z]{2,100}\s[a-zA-z]{1,100}'?-?[a-zA-Z]{2,100}\s?([a-zA-Z]{1,100})?)/;
	var letters2 = /^[A-Za-z]{2,100}/;
	if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('Il nome non rispetta il formato richiesto');
		name.focus();
		return false;
	}
}

function ValidateCity(name)
{
	var letters1= /^([a-zA-Z]{3,100}\s[a-zA-z]{1,100}'?-?[a-zA-Z]{1,100}\s?([a-zA-Z]{1,100})?)/;
	var letters2 = /^[A-Za-z]{5,100}/;
	if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('La citta inserita non rispetta i parametri richiesti');
		name.focus();
		return false;
	}
}

function ValidateSearch(name)
{
	var letters1= /^([a-zA-Z]{3,100}\s[a-zA-z0-9]{1,100}'?-?[a-zA-Z0-9]{1,100}\s?([a-zA-Z0-9]{1,100})?)/;
	var letters2 = /^[A-Za-z]{5,100}/;
	if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('Hai inserito troppi pochi caratteri per effettuare la ricerca');
		name.focus();
		return false;
	}
}


function ValidateCap(name)
{
	var letters2 = /\d{5,10}/;
	if(name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('Il cap inserito non rispetta i parametri richiesti');
		name.focus();
		return false;
	}
}

function ValidateProvincia(name)
{
	var letters1= /^([a-zA-Z]{2,50}\s[a-zA-z]{1,}'?-?[a-zA-Z]{1,}\s?([a-zA-Z]{1,})?)/;
var letters2 = /^[A-Za-z]{5,50}/;
if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('La provincia inserita non rispetta i parametri richiesti');
		name.focus();
		return false;
	}
}

function ValidateAddress(name)
{
	var letters1= /^([a-zA-Z]{2,}\s[a-zA-Z0-9]{2,}'?-?[a-zA-Z0-9]{1,}\s?([a-zA-Z0-9]{1,})?)/;
if(name.value.match(letters1))
	{
		return true;
	}
	else
	{
		alert('L indirizzo inserito non rispetta i parametri richiesti');
		name.focus();
		return false;
	}
}

function allLetters(surname)
{
	var letters1= /^([a-zA-Z]{5,50}\s[a-zA-z]{1,}'?-?[a-zA-Z]{2,100}\s?([a-zA-Z]{1,})?)/;
	var letters2 = /^[A-Za-z]{5,50}/;
	if(surname.value.match(letters1) || surname.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('Il cognome non rispetta il formato richiesto');
		surname.focus();
		return false;
	}
}

function ValidatePhone(phone)
{
	var numbers= /\d{9,10}/;
 
	if(phone.value.match(numbers))
	{
		return true;
	}
	else
	{
		alert('Il cellulare inserito non rispetta il formato richiesto');
		phone.focus();
		return false;
	}
}


function ValidateCode(name)
{
	var letters1= /^([G,F]{1,1}[0-9]{9,15})/;
if(name.value.match(letters1))
	{
		return true;
	}
	else
	{
		alert('Il codice inserito non rispetta i parametri richiesti');
		name.focus();
		return false;
	}
}

function ValidatePrice(price)
{
	var numbers= /^[\d\.]{3,}/;
 
	if(price.value.match(numbers))
	{
		return true;
	}
	else
	{
		alert('Il prezzo inserito non rispetta il formato richiesto');
		price.focus();
		return false;
	}
}

function ValidatePublisher(name)
{
	var letters1= /^([a-zA-Z0-9]{3,100}\s[a-zA-z0-9]{1,100}'?-?[a-zA-Z0-9]{2,100}\s?([a-zA-Z0-9]{1,100})?)/;
	var letters2 = /^[A-Za-z0-9]{5,100}/;
	if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('Il publisher inserito non rispetta il formato richiesto');
		name.focus();
		return false;
	}
}

function ValidateNumber(number)
{
	var numbers= /\d{1,10}/;
 
	if(number.value.match(numbers))
	{
		return true;
	}
	else
	{
		alert('Il numero inserito non rispetta il formato richiesto');
		number.focus();
		return false;
	}
}

function ValidateWriter(name)
{
	var letters1= /^([a-zA-Z]{3,100}\s[a-zA-z]{1,100}'?-?[a-zA-Z]{2,100}\s?([a-zA-Z]{1,100})?)/;
	var letters2 = /^[A-Za-z]{5,100}/;
	if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('Lo scrittore inserito non rispetta il formato richiesto');
		name.focus();
		return false;
	}
}

function ValidateTitle(name)
{
	var letters1= /^([a-zA-Z]{3,100}\s[a-zA-z]{1,100}'?-?[a-zA-Z]{2,100}\s?([a-zA-Z]{1,100})?)/;
	var letters2 = /^[A-Za-z]{5,100}/;
	if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('Il titolo inserito non rispetta il formato richiesto');
		name.focus();
		return false;
	}
}

function ValidatePlot(name)
{
/*	var letters1= /^([a-zA-Z0-9/s]{9,1000})/;
	var letters2 = /^[A-Za-z0-9]{5,100}/;
	if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('La trama non inserita non rispetta il formato richiesto');
		name.focus();
		return false;
	}*/
}

function ValidateGender(name)
{
	var letters1= /^([a-zA-Z]{3,100}\s[a-zA-z]{2,100}'?-?[a-zA-Z]{2,100}\s?([a-zA-Z]{1,100})?)/;
	var letters2 = /^[a-zA-Z]{5,100}/;
	if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('Il genere inserito non rispetta il formato richiesto');
		name.focus();
		return false;
	}
}

function ValidateDrawer(name)
{
	var letters1= /^([a-zA-Z]{3,100}\s[a-zA-z]{1,100}'?-?[a-zA-Z]{2,100}\s?([a-zA-Z]{1,100})?)/;
	var letters2 = /^[A-Za-z]{5,100}/;
	if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('Il disegnatore inserito non rispetta il formato richiesto');
		name.focus();
		return false;
	}
}

function ValidateAvailability(price)
{
	var numbers= /\d{1,10}/;
 
	if(price.value.match(numbers))
	{
		return true;
	}
	else
	{
		alert('La disponibilità inserito non rispetta il formato richiesto');
		price.focus();
		return false;
	}
}

function ValidateType(name)
{
	var letters1= /^([a-zA-Z]{3,100}\s[a-zA-z]{1,100}'?-?[a-zA-Z]{2,100}\s?([a-zA-Z]{1,100})?)/;
	var letters2 = /^[A-Za-z]{5,100}/;
	if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('Il tipo inserito non rispetta il formato richiesto');
		name.focus();
		return false;
	}
}

function ValidateDimension(name)
{
	var letters1= /^([a-zA-Z0-9]{3,100}\s[a-zA-z0-9]{1,100}'?-?[a-zA-Z0-9]{2,100}\s?([a-zA-Z0-9]{1,100})?)/;
	var letters2 = /^[A-Za-z0-9]{5,100}/;
	if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('La dimensione inserita non rispetta il formato richiesto');
		name.focus();
		return false;
	}
}

function ValidateWeight(name)
{
	var letters1= /^([a-zA-Z0-9]{3,100}\s[a-zA-z0-9]{1,100}'?-?[a-zA-Z0-9]{2,100}\s?([a-zA-Z0-9]{1,100})?)/;
	var letters2 = /^[A-Za-z0-9]{5,100}/;
	if(name.value.match(letters1) || name.value.match(letters2))
	{
		return true;
	}
	else
	{
		alert('Il peso inserito non rispetta il formato richiesto');
		name.focus();
		return false;
	}
}


