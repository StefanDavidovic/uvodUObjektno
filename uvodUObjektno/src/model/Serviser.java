package model;

public class Serviser extends Osoba {
	
	public int plata;
	public String specijalizacija;
	
	public Serviser(int id, String ime, String prezime, int jmbg, String pol, String broj, String korisnickoIme,
			String lozinka, int plata, String specijalizacija) {
		super(id, ime, prezime, jmbg, pol, broj, korisnickoIme, lozinka);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}
	
	public String toFile() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme + "|" + lozinka + "|" + plata + "|" + specijalizacija + "|";
	}
	
	

}

class test3 {
	public static void main(String[] args){
		Serviser s = new Serviser(123, "Mark", "Bla", 12345, "Musko", "78534", "mar123", "mar123", 12000, "automehanicar");
		System.out.println(s.toFile());
	}
}
	


