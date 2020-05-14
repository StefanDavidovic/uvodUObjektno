package model;


public class Automobil {
	
	public int id;
	public String vlasnik;
	public String Marka;
	public String Model;
	public int Godiste;
	public int KubikazaMotora;
	public int SnagaMotora;
	public String Gorivo;
	public Automobil(int id, String vlasnik, String marka, String model, int godiste, int kubikazaMotora, int snagaMotora,
			String gorivo) {
		this.id = id;
		this.vlasnik = vlasnik;
		this.Marka = marka;
		this.Model = model;
		this.Godiste = godiste;
		this.KubikazaMotora = kubikazaMotora;
		this.SnagaMotora = snagaMotora;
		this.Gorivo = gorivo;
		
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getvlasnik() {
		return vlasnik;
	}
	public void setvlasnik(String vlasnik) {
		this.vlasnik = vlasnik;
	}
	public String getMarka() {
		return Marka;
	}
	public void setMarka(String marka) {
		Marka = marka;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public int getGodiste() {
		return Godiste;
	}
	public void setGodiste(int godiste) {
		Godiste = godiste;
	}
	public int getKubikazaMotora() {
		return KubikazaMotora;
	}
	public void setKubikazaMotora(int kubikazaMotora) {
		KubikazaMotora = kubikazaMotora;
	}
	public int getSnagaMotora() {
		return SnagaMotora;
	}
	public void setSnagaMotora(int snagaMotora) {
		SnagaMotora = snagaMotora;
	}
	public String getGorivo() {
		return Gorivo;
	}
	public void setGorivo(String gorivo) {
		this.Gorivo = gorivo;
	}
	
	public String toFile() {
		return id + "|" + vlasnik + "|" + Marka + "|" + Model + "|" + Godiste + "|" + KubikazaMotora + "|" + SnagaMotora + "|" + Gorivo + "|";
	}	
	
}

class test4 {
	public static void main(String[] args){
		Automobil auto = new Automobil(134,"Marko", "BMW", "320d", 2003, 1400, 99, "Benzin" );
		System.out.println(auto.toFile());
		
	}
}



