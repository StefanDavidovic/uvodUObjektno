package model;

public class Deo {
	
	public int id;
	public String marka;
	public String model;
	public String naziv;
	public float cena;
	
	public Deo(int id, String marka, String model, String naziv, float cena) {
		this.id = id;
		this.marka = marka;
		this.model = model;
		this.naziv = naziv;
		this.cena = cena;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}
	
	public String toFile() {
		return id + "|" + marka + "|" + model + "|" + naziv + "|" + cena + "|";
	}
	
	
}

class test5 {
	public static void main(String[] args){
		Deo d = new Deo(111, "BMW", "320d", "Amortizer", 2500);
		System.out.println(d.toFile());
	}
}
