package it.polito.tdp.flight.model;

public class AirportDistance implements Comparable<AirportDistance>{
	
	private Airport end;
	private double distanza;
	public AirportDistance(Airport end, double distanza) {
		super();
		this.end = end;
		this.distanza = distanza;
	}
	public Airport getEnd() {
		return end;
	}
	public void setEnd(Airport end) {
		this.end = end;
	}
	public double getDistanza() {
		return distanza;
	}
	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}
	@Override
	public int compareTo(AirportDistance other) {
		return Double.compare(other.getDistanza(), this.getDistanza());
	}

}
