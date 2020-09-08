package model;

public class Osoba {
	
	public String id;
	public String ime;
	public String  prezime;
	public int jmbg;
	public Pol pol;
	public String broj;
	public String korisnickoIme;
	public String lozinka;
	public boolean obrisan;
	
	public Osoba() {
		this.obrisan = false;
	}
	
	
	public Osoba(String id, String ime, String prezime, int jmbg, Pol pol, String broj, String korisnickoIme,
			String lozinka, boolean obrisan) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.broj = broj;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.obrisan = obrisan;
		
		
		
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
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
	
	public boolean isObrisan() {
		return obrisan;
	}
	
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public String toString() {
		return "Osoba [id=" + id + "| ime=" + ime + "| prezime=" + prezime + "| jmbg=" + jmbg + "| pol=" + pol
				+ "| broj=" + broj + "| korisnickoIme=" + korisnickoIme + "| lozinka=" + lozinka + "]";
	}
	
	

}
