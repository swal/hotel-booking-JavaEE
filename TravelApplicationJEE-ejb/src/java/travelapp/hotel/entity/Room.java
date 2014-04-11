/**
 *     Copyright 2010 KU Leuven Research and Development - iMinds-DistriNet
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
package travelapp.hotel.entity;

import travelapp.hotel.BookingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity class representing a room
 * 
 * @author Stefan Walraven <stefan.walraven@cs.kuleuven.be>
 *
 * Date: 03-Dec-2010
 */
@Entity
public class Room {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Embedded
    private RoomDetails details;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Booking> bookings;
    private String hotelName;
    
    /**
     * Default constructor for <code>Room</code>
     */
    protected Room() {
        bookings = new ArrayList<Booking>();
    }
    
    /**
     * Constructor for <code>RoomDetails</code>
     * with given <code>RoomDetails</code> and <code>hotelName</code>
     * @param hotelName 
     * @param details
     */
    public Room(String hotelName, RoomDetails details) {
        this();
        this.hotelName = hotelName;
        this.details = details;
    }

    /**
     * @return  the id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the details of a room
     * @return  the room details
     */
    public RoomDetails getDetails() {
        return details;
    }

    public String getHotelName() {
        return hotelName;
    }
    
    /**
     * Returns a list of bookings
     * @return  the list of bookings
     */
    public List<Booking> getBookingDetails() {
        return Collections.unmodifiableList(getBookings());
    }

    protected List<Booking> getBookings() {
        return bookings;
    }
    
    /**
     * Returns whether the room is free in a specific period
     * @param start  the start date of the period
     * @param end    the end date of the period
     * @return  True if the room is free; False otherwise
     */
    public boolean isFree(Date start, Date end){
        for(Booking booking: getBookings()){
            if(booking.getEndDate().before(start))
                continue;
            if(booking.getStartDate().after(end))
                continue;
            return false;
        }
        return true;
    }
    
    /**
     * Add a booking to this room
     * @param booking   the booking to add
     * @throws hotel.BookingException   Exception thrown if the booking is illegal
     *
     * NOTE: It is possible that multiple session beans try to add a booking
     *  for the same period concurrently. So actually this method should be
     *  synchronized (with the <code>synchronized</code> keyword).
     *  For this lab session it isn't required to take it into consideration.
     */
    public void addBooking(Booking booking) throws BookingException {
        if(isFree(booking.getStartDate(), booking.getEndDate())){
            getBookings().add(booking);
        } else {
            throw new BookingException("Room "+getDetails().getRoomNb()+" not available.");
        }
    }
    
    /**
     * Cancel an existing booking for this room
     * @param booking
     */
    public void cancelBooking(Booking booking) {
        getBookings().remove(booking);
    }
}