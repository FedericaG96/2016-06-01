package it.polito.tdp.flight.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.flight.db.FlightDAO;

public class Model {
	
	FlightDAO dao;
	Graph<Airport, DefaultWeightedEdge> grafo;
	List<Airline> airlines;
	List<Airport> airports;
	Map<Integer, Airport> idMapAirport;
	Map<Integer, Airline> idMapAirlines;
	List<Adiacente> adiacenti;
	List<Airport> serviti;
	
	public Model() {
		dao = new FlightDAO();
		airlines = dao.getAllAirlines();
		airports = dao.getAllAirports();
		idMapAirport = new HashMap<>();
		idMapAirlines = new HashMap<>();
	}
	
	public void creaGrafo(Airline compagniaScelta) {
		
		for(Airport a : airports) {
			idMapAirport.put(a.getAirportId(), a);
		}
		grafo = new SimpleDirectedWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, airports);
		
		for(Airline a : airlines) {
			idMapAirlines.put(a.getAirlineId(), a);
		}
		
		double peso = 0.0;
		adiacenti = dao.getAdiacenti(idMapAirport, compagniaScelta);
		for(Adiacente a : adiacenti) {
			LatLng c1 = new LatLng(a.getSource().getLatitude(), a.getSource().getLongitude());
			LatLng c2 = new LatLng(a.getDestination().getLatitude(), a.getDestination().getLongitude());
			 peso = LatLngTool.distance(c1, c2, LengthUnit.KILOMETER);
			 a.setDistanza(peso);
			 Graphs.addEdge(grafo, a.getSource(), a.getDestination(), peso);
		}
		
	}
	
	public List<Airline> getAllAirlines() {
		return this.airlines;
	}

	
	public List<Airport> getServiti(Airline compagnia) {
		serviti = new ArrayList<>();
		for(Adiacente a : adiacenti) {
			if(!serviti.contains(a.getSource())){
				serviti.add(a.getSource());
			}
			if(!serviti.contains(a.getDestination())) {
				serviti.add(a.getDestination());
			}
		}
		return serviti;
	}

	public List<Adiacente> getAdiacenti(){
		return adiacenti;
	}

	public List<AirportDistance> getRaggiungibili(Airport airpScelto) {
		List<AirportDistance> list = new ArrayList<>();
		
		for(Airport a : serviti) {
			if(!a.equals(airpScelto)) {
			DijkstraShortestPath<Airport, DefaultWeightedEdge> dij = new DijkstraShortestPath<>(this.grafo, airpScelto, a);
			GraphPath<Airport, DefaultWeightedEdge> path = dij.getPath();
			if(path!=null) {
				list.add(new AirportDistance(a, path.getWeight()));
			}
			}
		}
		
		Collections.sort(list);
		return list;
	}
}
