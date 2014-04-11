package travelapp;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import travelapp.hotel.entity.Booking;

/**
 * Class representing a list of tentative bookings
 * 
 * @author Stefan Walraven <stefan.walraven@cs.kuleuven.be>
 *
 * Date: 03-Dec-2010
 */
@SuppressWarnings("serial")
public class PlannedTrip implements Serializable {
	
	private List<Booking> plannedBookings;
	
	public PlannedTrip() {
		plannedBookings = new CopyOnWriteArrayList<Booking>();
	}
	
	public void add(Booking bd) {
		plannedBookings.add(bd);
	}
	
	public void remove(Booking bd) {
		plannedBookings.remove(bd);
	}
	
	public int getNbOfBookings() {
		return plannedBookings.size();
	}
	
	public List<Booking> getBookings() {
		return plannedBookings;
	}
	
	public void clear() {
		plannedBookings.clear();
	}
}
