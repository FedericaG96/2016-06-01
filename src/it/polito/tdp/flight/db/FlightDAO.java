package it.polito.tdp.flight.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.flight.model.Adiacente;
import it.polito.tdp.flight.model.Airline;
import it.polito.tdp.flight.model.Airport;

public class FlightDAO {

	public List<Airport> getAllAirports() {
		
		String sql = "SELECT * FROM airport" ;
		
		List<Airport> list = new ArrayList<>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Airport a = new Airport(
						res.getInt("Airport_ID"),
						res.getString("name"),
						res.getString("city"),
						res.getString("country"),
						res.getString("IATA_FAA"),
						res.getString("ICAO"),
						res.getDouble("Latitude"),
						res.getDouble("Longitude"),
						res.getFloat("timezone"),
						res.getString("dst"),
						res.getString("tz"));
				list.add( a) ;
			}
			
			conn.close();
			
			return list ;
		} catch (SQLException e) {

			e.printStackTrace();
			return null ;
		}
	}
	
	public static void main(String args[]) {
		FlightDAO dao = new FlightDAO() ;
		
		List<Airport> arps = dao.getAllAirports() ;
		System.out.println(arps);
	}

	public List<Airline> getAllAirlines() {
		String sql = "SELECT * FROM airline" ;
		
		List<Airline> list = new ArrayList<>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				list.add( new Airline(
						res.getInt("Airline_ID"),
						res.getString("Name"),
						res.getString("Alias"),
						res.getString("IATA"),
						res.getString("ICAO"),
						res.getString("Callsign"),
						res.getString("Country"),
						res.getString("Active")));
			}
			
			conn.close();
			
			return list ;
		} catch (SQLException e) {

			e.printStackTrace();
			return null ;
		}
	}

	public List<Adiacente> getAdiacenti(Map<Integer, Airport> idMapAirport, Airline compagniaScelta) {
		
		String sql = "SELECT   r.Source_airport_ID, r.Destination_airport_ID " + 
				"				FROM route AS r, airport a1, airport a2  " + 
				"				WHERE r.Source_airport_ID = a1.Airport_ID " + 
				"				AND r.Destination_airport_ID = a2.Airport_ID " + 
				"				AND a1.Airport_ID <> a2.Airport_ID " + 
				"				AND r.Airline_ID = ?";
		List<Adiacente> adiacenti = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, compagniaScelta.getAirlineId());
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Airport source =idMapAirport.get(res.getInt("Source_airport_ID"));
				Airport destination = idMapAirport.get(res.getInt("Destination_airport_ID"));
				adiacenti.add( new Adiacente(compagniaScelta, source, destination));
					
			}
			
			conn.close();
			
			return adiacenti ;
		} catch (SQLException e) {

			e.printStackTrace();
			return null ;
	}
	}
	
}
