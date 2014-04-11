/**
 *     Copyright 2012 KU Leuven Research and Development - iMinds-DistriNet
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
package travelapp.session;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import travelapp.hotel.BookingConstraints;
import travelapp.hotel.BookingException;
import travelapp.hotel.HotelInfo;
import travelapp.hotel.entity.Booking;

/**
 * Remote interface for BookingSessionBean
 * 
 * @author Stefan Walraven <stefan.walraven@cs.kuleuven.be>
 *
 * Date: 01-Jun-2012
 */
@Remote
public interface BookingSession {

    /***********
     * LOOK UP *
     ***********/

    /**
     * Look up all hotels in the given city.
     *
     * @param   city
     * @return  List of hotels in the given city.
     */
    public List<HotelInfo> findAllHotelsInCity(String city);

    /**
     * Look up all hotels with rooms available in the given period.
     *
     * @param   start
     *          Start date of period
     * @param   end
     *          End date of period
     * @return  List of hotels with free rooms in the given period.
     */
    public List<HotelInfo> findAllHotelsWithFreeRoomsInPeriod(Date start, Date end);

    /************
     * BOOKINGS *
     ************/
    
    /**
     * Tentatively book a room.
     * 
     * @param hotelName the name of the hotel
     * @param bookingConstraints the booking constraints
     * @param client    the client name
     * @return  a <code>BookingDetails</code> instance
     * @throws hotel.BookingException   exception thrown when the booking 
     *  constraints could not be fulfilled.
     */
    public Booking createBooking(String hotelName, BookingConstraints bookingConstraints,
            String client) throws BookingException;
    
    /**
     * Finalizes given tentative bookings. 
     * 
     * This is an atomic operation, a single booking finalization failure 
     * causes all other bookings to fail.
     * 
     * @param  tentativeBookings    the list of tentative <code>Booking</code>
     * @return  the list of finalized <code>Booking</code>
     * @throws hotel.BookingException   exception thrown when one or more
     *      bookings failed to finalize.
     */
    public List<Booking> finalizeBookings(List<Booking> tentativeBookings) throws BookingException;
}