package it.polito.tdp.PremierLeague.model;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Simulator {

	//input
	private PremierLeagueDAO dao;
	private SimpleDirectedWeightedGraph<Player, DefaultWeightedEdge> grafo;
	private Match partita;
	private Best migliore;
	private Integer azioniSalienti;
	private Integer giocatoriCasa;
	private Integer giocatoriFuori;

	//output
	private Integer goalCasa;
	private Integer goalFuori;
	private Integer espulsi;

	public void init(Match m, Integer n, Best migliore, SimpleDirectedWeightedGraph<Player, DefaultWeightedEdge> grafo) {

		this.dao = new PremierLeagueDAO();
		this.grafo = grafo;
		this.partita = m;
		this.migliore = migliore;
		this.azioniSalienti = n;
		this.giocatoriCasa = 11;
		this.giocatoriFuori = 11;

		this.goalCasa = 0;
		this.goalFuori = 0;
		this.espulsi = 0;
	}

	public void run() {

		Integer flag = dao.getTeamHomeOrAway(partita, migliore.getBestPlayer());

		for(int index = 0; index < azioniSalienti; index ++) {
			Double probabilita = Math.random();

			//goal
			if(probabilita>=0.5) {
				
				if(giocatoriCasa>giocatoriFuori) {
					goalCasa++;
					
				}else if(giocatoriFuori>giocatoriCasa) {
					goalFuori++;
				
				}else if(giocatoriCasa==giocatoriFuori) {
					
					if(this.grafo.containsVertex(migliore.getBestPlayer())) {
						//migliore appartiene alla squadra di casa
						if(flag==1) {
							goalCasa++;
						//migliore appartiene alla squadra fuori
						}else {
							goalFuori++;
						}
					}
					
				}

				//espulsione	
			}else if(probabilita>=0.2 && probabilita<0.5) {

				if(this.grafo.containsVertex(migliore.getBestPlayer())) {
					Double x = Math.random();

						//migliore appartiene alla squadra in casa
					if(flag == 1) {
						if(x<=0.6) {
							giocatoriCasa--;
							espulsi++;
						}else {
							giocatoriFuori--;
							espulsi++;
						}
						//migliore appartiene alla squadra fuori casa
					}else {
						if(x<=0.6) {
							giocatoriFuori--;
							espulsi++;
						}else {
							giocatoriCasa--;
							espulsi++;
						}
					}
				}

				//infortunio	
			}else if(probabilita<0.2) {
				Double allungo = Math.random();
				if(allungo>0.5) {
					azioniSalienti = azioniSalienti+2;
				}else {
					azioniSalienti = azioniSalienti+3;
				}

			}
		}

	}

	public Integer getEspulsi() {
		return this.espulsi;
	}
	
	public Integer getGoalCasa(){
		return this.goalCasa;
	}
	
	public Integer getGoalFuori() {
		return this.goalFuori;
	}

}
