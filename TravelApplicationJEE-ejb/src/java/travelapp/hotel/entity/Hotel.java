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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import travelapp.feature.price.PriceCalculation;
import travelapp.hotel.BookingConstraints;
import travelapp.hotel.BookingException;
import util.Address;

/**
 * Named queries
 */
@NamedQueries({
    @NamedQuery(
        name="findAllHotelNames",
        query="SELECT h.name FROM Hotel h"
    ),
    @NamedQuery(
        name="findAllHotels",
        query="SELECT h FROM Hotel h"
    ),
    @NamedQuery(
    	name="findAllBookings",
    	query="SELECT b FROM Booking b"
    ),
    @NamedQuery(
    	name="removeAllBookings",
    	query="DELETE FROM Booking"
    ),
    @NamedQuery(
        name="findAllRoomsInHotel",
        query="SELECT r FROM Room r WHERE r.hotelName = :hotelName"
    ),
    @NamedQuery(
        name="findAllBookingsInHotel",
        query="SELECT r.bookings FROM Room r WHERE r.hotelName = :hotelName"
    ),
    @NamedQuery(
        name="findAllHotelsInCity",
        query="SELECT h.name FROM Hotel h WHERE h.address.city = :city"
    ),
    @NamedQuery(
        name="findAllHotelsWithFreeRoomsBetweenGivenPeriod",
        query="SELECT DISTINCT r.hotelName FROM Room r WHERE (r.bookings IS EMPTY) " +
        "OR NOT EXISTS (" +
        "SELECT b FROM r.bookings b WHERE (b.startDate BETWEEN :start AND :end) OR " +
        "(b.endDate BETWEEN :start AND :end))"
    )
})
/**
 * Entity class representing a hotel
 * 
 * @author Stefan Walraven <stefan.walraven@cs.kuleuven.be>
 *
 * Date: 03-Dec-2010
 */
@Entity
public class Hotel {
    
    @Id
    private String name;
    @OneToOne(cascade=CascadeType.ALL)
    private Address address;
    
    /**
     * Holds the list of rooms
     */
    @OneToMany(cascade=CascadeType.ALL)
    private List<Room> rooms;
    
    /**
     * Default constructor
     */
    protected Hotel() {
        rooms = new ArrayList<Room>();
    }
    
    /**
     * Constructor with parameter <code>name</code and <code>address</code>.
     * @param name  the name of the hotel
     * @param address  the address of the hotel
     */
    public Hotel(String name, Address address) {
        this();
        this.name = name;
        this.address = address;
    }

    /**
     * Returns the name of the hotel
     * @return  the name of the hotel
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the address of this hotel
     * @return the address of this hotel
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns a list containing the <code>Room</code>s in this <code>Hotel</code>
     * @return  a list of rooms
     */
    protected List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * Returns the <code>Room</code> with number <code>roomnb</code>.
     * @param roomnb    the room number
     * @return  the room
     */
    private Room getRoom(int roomnb){
        for(Room room: rooms){
            if(room.getDetails().getRoomNb()==roomnb){
                return room;
            }
        }
        return null;
    }

    public void addRoom(Room room) {
        if(getRoom(room.getDetails().getRoomNb()) == null)
            rooms.add(room);
        else
            throw new IllegalArgumentException("There is already a room with" +
                    " the same room number.");
    }

    /**
     * Return a list containing <code>RoomDetails</code> for all <code>Room</code>s
     * @return a list of <code>RoomDetails</code> for all <code>Room</code>s
     */
    public List<RoomDetails> getAllRoomDetails() {

        List<RoomDetails> roomDetailList = new ArrayList<RoomDetails>();

        for (Room r : getRooms()) {
            roomDetailList.add(r.getDetails());
        }

        return roomDetailList;
    }

    public boolean hasFreeRooms(Date from, Date to) {
        for(Room room: rooms)
            if(room.isFree(from, to))
                return true;
        return false;
    }

    /**
     * Returns a list of free rooms in a given period.
     * @param from  the start date of the period
     * @param to    the end date of the period
     * @return  a list of free rooms
     */
    private List<RoomDetails> getFreeRooms(Date from, Date to){
        List<RoomDetails> freeRooms = new ArrayList<RoomDetails>();
        for(Room room: rooms){
            if(room.isFree(from, to)) {
                freeRooms.add(room.getDetails());
            }
        }
        return freeRooms;
    }

    /**
     * Create a valid booking for Hotel <code>hotel</code> that satisfies
     * booking constraint <code>constraint</code>.
     * @param constraint    the constraint to satisfy
     * @param clientName  the name of the client
     * @return  the booking details
     * @throws hotel.BookingException
     * 
     * NOTE: creating a booking does not register the booking with the hotel. 
     *  It suggests a valid booking for the provided constraint, when possible.
     *
     * @see finalizeBooking()
     */
    public Booking createBooking(BookingConstraints constraint,
            String guestName, PriceCalculation calc) throws BookingException {
        for(RoomDetails room:
            getFreeRooms(constraint.getStartDate(), constraint.getEndDate())) {

                if(room.isSmokingAllowed() == constraint.isSmokingAllowed()
                        && constraint.getNbOfBeds() <= room.getNbOfBeds()
                        && constraint.getMaxPricePerNight() >= room.getPricePerNight()) {

                    Booking booking =
                            new Booking(guestName, constraint.getStartDate(),
                            constraint.getEndDate(), room.getRoomNb(), getName(),
                            calc.calculatePrice(room.getPricePerNight(),
                                constraint.getStartDate(), constraint.getEndDate())
                            );

                    return booking;
            }
        }
        throw new BookingException("No room to satisfy the constraint.");
    } 
    
    /**
     * Finalize a booking by registering it with the hotel
     * @param booking   the booking details
     */
    public void finalizeBooking(Booking booking) throws BookingException {
        int roomnb = booking.getRoomNb();
        Room room = getRoom(roomnb);
        
        room.addBooking(booking);
    }
    
    /**
     * Returns a list containing <code>BookingDetail</code>s for room with number <code>roomnb</code>
     * @param roomnb    the room number
     * @return  the <code>BookingDetail</code>s
     */
    public List<Booking> getBookings(int roomnb) {
        return getRoom(roomnb).getBookingDetails();
    }
    
    /**
     * Returns a list containing the bookings of all rooms.
     * @return
     */
    public List<Booking> getBookings() {
        
        List<Booking> bookings = new ArrayList<Booking>();
        for (Room r : getRooms()) {
            bookings.addAll(r.getBookingDetails());
        }
        return bookings;
    }
    
    /**
     * Cancels an already registered* booking
     * 
     * [*] through finalizeBooking()
     * @param booking   the booking details
     */
    public void cancelBooking(Booking bd) {
        int roomnb = bd.getRoomNb();
        Room room = getRoom(roomnb);
        
        room.cancelBooking(bd);
    }
}