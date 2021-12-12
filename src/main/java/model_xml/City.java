package model_xml;

public class City {
	public Coord getcoord() {
		 return this.coord; } 
	public void setcoord(Coord coord) {
		 this.coord = coord; } 
	Coord coord;
	public String getcountry() { 
		 return this.country; } 
	public void setcountry(String country) { 
		 this.country = country; } 
	String country;
	public int gettimezone() { 
		 return this.timezone; } 
	public void settimezone(int timezone) { 
		 this.timezone = timezone; } 
	int timezone;
	public Sun getsun() {
		 return this.sun; } 
	public void setsun(Sun sun) {
		 this.sun = sun; } 
	Sun sun;
	public int getid() { 
		 return this.id; } 
	public void setid(int id) { 
		 this.id = id; } 
	int id;
	public String getname() { 
		 return this.name; } 
	public void setname(String name) { 
		 this.name = name; } 
	String name;
	public String gettext() { 
		 return this.text; } 
	public void settext(String text) { 
		 this.text = text; } 
	String text;
}
