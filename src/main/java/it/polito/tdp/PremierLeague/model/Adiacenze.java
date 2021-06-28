package it.polito.tdp.PremierLeague.model;

public class Adiacenze {
	
	private Integer p1;
	private Integer p2;
	private Double peso;
	
	public Adiacenze(Integer p1, Integer p2, Double peso) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.peso = peso;
	}
	
	public Integer getP1() {
		return p1;
	}
	
	public void setP1(Integer p1) {
		this.p1 = p1;
	}
	
	public Integer getP2() {
		return p2;
	}
	
	public void setP2(Integer p2) {
		this.p2 = p2;
	}
	
	public Double getPeso() {
		return peso;
	}
	
	public void setPeso(Double peso) {
		this.peso = peso;
	}

}
