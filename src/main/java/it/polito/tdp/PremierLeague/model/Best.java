package it.polito.tdp.PremierLeague.model;

public class Best {
	
	private Player bestPlayer;
	private Double efficenza;
	
	public Best(Player bestPlayer, Double efficenza) {
		super();
		this.bestPlayer = bestPlayer;
		this.efficenza = efficenza;
	}
	
	public Player getBestPlayer() {
		return bestPlayer;
	}
	
	public void setBestPlayer(Player bestPlayer) {
		this.bestPlayer = bestPlayer;
	}
	
	public Double getEfficenza() {
		return efficenza;
	}
	
	public void setEfficenza(Double efficenza) {
		this.efficenza = efficenza;
	}

	@Override
	public String toString() {
		return "Il miglior giocatore è: " + bestPlayer + ", efficenza = " + efficenza + "\n";
	}
	
	
}
