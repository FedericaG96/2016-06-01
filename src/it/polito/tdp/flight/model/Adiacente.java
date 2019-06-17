package it.polito.tdp.flight.model;



public class Adiacente {
	
	private Airline airline;
	private Airport source;
	private Airport destination;
	private double distanza;
	
	public Adiacente(Airline airline, Airport source, Airport destination) {
		super();
		this.airline = airline;
		this.source = source;
		this.destination = destination;
		
	}
	public Airline getAirline() {
		return airline;
	}
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	public Airport getSource() {
		return source;
	}
	public void setSource(Airport source) {
		this.source = source;
	}
	public Airport getDestination() {
		return destination;
	}
	public void setDestination(Airport destination) {
		this.destination = destination;
	}
	public double getDistanza() {
		return distanza;
	}
	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}
	@Override
	public String toString() {
		return String.format("%s - destination=%s (%s) \n", source,
				destination, distanza);
	}
	
	

}
