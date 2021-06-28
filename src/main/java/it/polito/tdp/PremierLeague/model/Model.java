package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {

	private PremierLeagueDAO dao;
	private SimpleDirectedWeightedGraph<Player, DefaultWeightedEdge> grafo;
	private Map<Integer, Player> idMap;

	public Model() {
		this.dao = new PremierLeagueDAO();
		this.idMap = new HashMap<Integer, Player>();
	}

	public List<Match> getMatches(){
		List<Match> result = new ArrayList<Match>(dao.listAllMatches());
		Collections.sort(result);
		return result;
	}

	public void creaGrafo(Match partita) {
		
		this.grafo = new SimpleDirectedWeightedGraph<Player, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		//aggiungo vertici
		dao.getPlayersFromMatch(partita, idMap);
		Graphs.addAllVertices(this.grafo, this.idMap.values());

		//aggiungo archi
		for(Adiacenze a: dao.getEdges(partita)) {
			if(idMap.containsKey(a.getP1()) && idMap.containsKey(a.getP2())) {
				if(a.getPeso()>0)
					Graphs.addEdge(this.grafo, idMap.get(a.getP1()), idMap.get(a.getP2()), a.getPeso());
				else
					Graphs.addEdge(this.grafo, idMap.get(a.getP2()), idMap.get(a.getP1()), (-1)*a.getPeso());	
			}
		}

		System.out.println(this.grafo.edgeSet().toString());
	}

	public int getNumVertex() {
		return this.grafo.vertexSet().size();
	}

	public int getNumEdges() {
		return this.grafo.edgeSet().size();
	}
	
	public boolean esistenzaGrafo() {
		if(this.grafo==null)
			return false;
		else
			return true;
	}

	public Best getBest() {

		Best b = new Best(null, 0.00);

		for(Player p: this.grafo.vertexSet()) {

			Double sommaPesiEntranti = 0.00;
			Double sommaPesiUscenti = 0.00;

			for(DefaultWeightedEdge e: this.grafo.incomingEdgesOf(p)) {
				sommaPesiEntranti += this.grafo.getEdgeWeight(e);
			}
			for(DefaultWeightedEdge e: this.grafo.outgoingEdgesOf(p)) {
				sommaPesiUscenti += this.grafo.getEdgeWeight(e);
			}

			Double efficenza = sommaPesiUscenti-sommaPesiEntranti;

			if(efficenza>b.getEfficenza()) {
				b.setBestPlayer(p);
				b.setEfficenza(efficenza);
			}
		}
		
		return b;
	}

}
