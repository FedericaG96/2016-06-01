package it.polito.tdp.flight.model;

public class Airline {
	
	private int airlineId ;
	private String name ;
	private String alias ;
	private String iata ;
	private String icao ;
	private String callsign ;
	private String country ;
	private String active ;
	
	public Airline(int airlineId, String name, String alias, String iata, String icao, String callsign, String country,
			String active) {
		super();
		this.airlineId = airlineId;
		this.name = name;
		this.alias = alias;
		this.iata = iata;
		this.icao = icao;
		this.callsign = callsign;
		this.country = country;
		this.active = active;
	}

	public int getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return String.format("%s: %s", airlineId, name);
	}
	
	

}
