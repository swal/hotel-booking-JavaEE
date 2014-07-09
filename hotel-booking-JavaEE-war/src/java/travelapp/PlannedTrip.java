/**
 *     Copyright (c) KU Leuven Research and Development - iMinds-DistriNet
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *     Administrative Contact: dnet-project-office@cs.kuleuven.be
 *     Technical Contact: stefan.walraven@cs.kuleuven.be
 */
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
