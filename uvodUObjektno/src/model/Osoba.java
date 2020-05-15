package model;

public class Osoba {
	
	public int id;
	public String ime;
	public String  prezime;
	public int jmbg;
	public String pol;
	public String broj;
	public String korisnickoIme;
	public String lozinka;
	
	public Osoba(int id, String ime, String prezime, int jmbg, String pol, String broj, String korisnickoIme,
			String lozinka) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.broj = broj;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public int getJmbg() {
		return jmbg;
	}

	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	
	}

	public String toString() {
		return "Osoba [id=" + id + "| ime=" + ime + "| prezime=" + prezime + "| jmbg=" + jmbg + "| pol=" + pol
				+ "| broj=" + broj + "| korisnickoIme=" + korisnickoIme + "| lozinka=" + lozinka + "]";
	}
	
	

}
