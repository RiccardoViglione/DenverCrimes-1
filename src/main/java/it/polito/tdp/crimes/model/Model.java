package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	private EventsDao dao;
	private Graph<String,DefaultWeightedEdge>grafo;
	
	public Model() {
		dao=new EventsDao();
	}
	public List<String> listAllReati(){
		return dao.listAllReati();
	}
	public List<Integer> listAllMesi(){
		return dao.listAllMesi();
		
	}
	public void creaGrafo(String reato,int mese) {
		this.grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, this.dao.getVertici(reato, mese));
		for(Adiacenze a:this.dao.getArchi(reato, mese)) {
			Graphs.addEdgeWithVertices(this.grafo, a.getR1(), a.getR2(),a.getPeso());
			
		}
		
	}
	public List<String> getVertici(){
		return new ArrayList<>(this.grafo.vertexSet());
	}
	
	public boolean grafoCreato() {
		if(this.grafo == null)
			return false;
		else 
			return true;
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public double pesoMedio(){
		double peso=0.0;
		
		for(DefaultWeightedEdge e:this.grafo.edgeSet()) {
			peso+=this.grafo.getEdgeWeight(e);
		}
		peso=peso/this.grafo.edgeSet().size();
	return peso;
	}
	public List<Adiacenze> peso(){
		double pesoMedio=this.pesoMedio();
		List<Adiacenze>result=new ArrayList<>();
		for(DefaultWeightedEdge e:this.grafo.edgeSet()) {
			int peso=(int)this.grafo.getEdgeWeight(e);
			if(peso>pesoMedio) {
				result.add(new Adiacenze(this.grafo.getEdgeSource(e),this.grafo.getEdgeTarget(e),peso));
			}
			
		}
		
	return result;
	}
	
	
	
}
