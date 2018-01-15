package GestioneProdotti;

import java.io.Serializable;

public class ComicsBean implements Serializable {
	private static final long serialVersionUID = 1L;

	String code;
	String gender;
	float price;
	String writer;
	String drawer;
	String publisher;
	String type;
	String plot;
	String title;
	String number;
	String image;
	int quantity;
	int availability;


	public ComicsBean() {
		code = "";
		plot = "";
		gender = "";
		publisher = "";
		type = "";
		drawer = "";
		price = 0;
		writer = "";
		title = "";
		number = "";
		image="";
		quantity=1;
		availability=1;
	}

	public int getavailability() {
		return	availability;
	}

	public void setavailability(int availability) {
		this.availability = availability;
	}

	public String getcode() {
		return code;
	}

	public void setcode(String code) {
		this.code = code;
	}


	public int getquantity() {
		return quantity;
	}

	public void setquantity(int quantity) {
		this.quantity = quantity;
	}

	public String getgender() {
		return gender;
	}

	public void setgender(String gender) {
		this.gender = gender;
	}

	public float getprice() {
		return price;
	}

	public void setprice(float price) {
		this.price = price;
	}

	public String getwriter() {
		return writer;
	}

	public void setwriter(String writer) {
		this.writer = writer;
	}

	public String getdrawer() {
		return drawer;
	}

	public void setdrawer(String drawer) {
		this.drawer = drawer;
	}

	public String getpublisher() {
		return publisher;
	}

	public void setpublisher(String publisher) {
		this.publisher = publisher;
	}

	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

	public String getplot() {
		return plot;
	}

	public void setplot(String plot) {
		this.plot = plot;
	}

	public String gettitle() {
		return title;
	}

	public void settitle(String title) {
		this.title = title;
	}

	public String getnumber() {
		return number;
	}

	public void setnumber(String number) {
		this.number = number;
	}

	public String getimage() {
		return image;
	}

	public void setimage(String image) {
		this.image = image;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}