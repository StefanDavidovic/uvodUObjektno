package model;

public class Musterija extends Osoba {
	
	public int brojBodova;

	public Musterija(int id, String ime, String prezime, int jmbg, String pol, String broj, String korisnickoIme,
			String lozinka, int brojBodova) {
		super(id, ime, prezime, jmbg, pol, broj, korisnickoIme, lozinka);
		this.brojBodova = brojBodova;
	}

	public int getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(int brojBodova) {
		this.brojBodova = brojBodova;
	}


	public String toString() {
		return "Musterija [id=" + id + "|ime=" + ime + "|prezime=" + prezime + "|jmbg=" + jmbg + "|pol=" + pol 
								+ "|broj=" + broj + "|korisnickoIme=" + korisnickoIme + "|lozinka=" + lozinka + "|brojBodova=" + brojBodova + "]";
		
	}
	
	public String toFile() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme + "|" + lozinka + "|" + brojBodova + "|";
	}
	
}


class test1 {
	
	public static void main(String[] args){ 
		Musterija mu = new Musterija(123, "Marko", "Pre", 1234343, "muski", "0342324", "MP", "marko123", 3);
		System.out.println(mu);
		System.out.println(mu.toFile());
				
		
		}
}
		
