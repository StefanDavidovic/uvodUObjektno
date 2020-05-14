package model;

public class Administrator extends Osoba{
	
	public int plata;

	public Administrator(int id, String ime, String prezime, int jmbg, String pol, String broj, String korisnickoIme,
			String lozinka, int plata) {
		super(id, ime, prezime, jmbg, pol, broj, korisnickoIme, lozinka);
		this.plata = plata;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}
	

	public String toFile() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme + "|" + lozinka + "|" + plata + "|";
	}
	
	
}

class test2 {
	
	public static void main(String[] args){ 
		Administrator ma = new Administrator(124, "Marko", "Pre", 1234343, "muski", "0342324", "MP", "marko123", 2600);
		System.out.println(ma.toFile());
		
	}
}