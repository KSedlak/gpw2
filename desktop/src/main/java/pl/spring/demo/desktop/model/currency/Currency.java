package pl.spring.demo.desktop.model.currency;



public enum Currency {
	PLN("PLN"),
	EURO("Euro"),
	USD("Dolar");


	private final String name;
    private double lower;
    private double upper;



	private Currency(String name, double lower, double upper) {
		this.name = name;
		this.lower = lower;
		this.upper = upper;
	}
	private Currency(String name) {///podstawowa waluta
		this.name = name;
		this.lower = 1.0;
		this.upper = 1.0;
	}
	public String getName() {
		return name;
	}
	public double getLower() {
		return lower;
	}
	public void setLower(double lower) {
		this.lower = lower;
	}
	public void setUpper(double upper) {
		this.upper = upper;
	}
	public double getUpper() {
		return upper;
	}





}
