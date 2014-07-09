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

import java.util.List;
import travelapp.hotel.ManagementException;
import javax.ejb.Remote;
import travelapp.hotel.entity.Booking;
import travelapp.hotel.entity.RoomDetails;
import util.Address;

/**
 * Remote interface for ManagerSessionBean
 * 
 * @author Stefan Walraven <stefan.walraven@cs.kuleuven.be>
 *
 * Date: 01-Jun-2012
 */
@Remote
public interface ManagerSession {
    
    /**********************
     * ADD HOTELS & ROOMS *
     **********************/
    
    /**
     * Add a new hotel to the online booking application
     * with given name <code>hotelName</code> and address <code>address</code>.
     *
     * @param   hotelName
     *          The name of the new hotel.
     * @param   address
     *          The address of the new hotel.
     * @post    A new hotel is added to the online booking application
     *          with given name and address.
     *          | new.getHotels().contains(hotelName)
     * @throws  ManagementException
     *          Adding a new hotel failed.
     */
    public void addNewHotel(String hotelName, Address address)
                                                throws ManagementException;

    /**
     * Add a new room with given room details to the hotel with the given name.
     *
     * @param   hotelName
     *          Name of the hotel to add the new room to.
     * @param   details
     *          Details of the new room.
     * @post    A new room with given details is added to the hotel
     *          with the given name.
     *          | new.getRooms(hotelName).contains(details)
     * @throws  ManagementException
     *          Adding a new room to the hotel failed.
     */
    public void addNewRoomToHotel(String hotelName, RoomDetails details)
                                                throws ManagementException;

    /************************************
     * LOOK UP HOTELS, ROOMS & BOOKINGS *
     ************************************/
    
    /**
     * Returns a list containing the names of all hotels.
     *
     * @return  a list containing all hotels
     */
    public List<String> getHotels();
    
    /**
     * Returns the address of the hotel with name <code>hotelName</code>.
     * 
     * @param   hotelName
     *          The hotel name
     * @return  the address of the hotel with the given name
     */
    public Address getHotelAddress(String hotelName);

    /**
     * Returns a list containing <code>RoomDetails</code> of
     * the hotel with name <code>hotel</code>
     *
     * @param   hotelName
     *          The hotel name
     * @return  a list containing <code>RoomDetails</code>
     */
    public List<RoomDetails> getRooms(String hotelName);

    /**
     * Returns a list containing the bookings of all hotels
     * 
     * @return  a list containing all <code>Booking</code>
     */
    public List<Booking> getBookings();
    
    /**
     * Returns a list containing the bookings of the hotel
     * with name <code>hotelName</code>
     *
     * @param   hotelName
     *          the hotel name
     * @return  a list containing <code>Booking</code>
     */
    public List<Booking> getBookings(String hotelName);
    
    /************
     * CLEAN UP *
     ************/
    
    /**
     * Removes all bookings of all hotels
     * 
     * @post    All bookings of all hotels are removed.
     * @throws  ManagementException
     *          Removing bookings failed.
     */
    public void removeAllBookings() throws ManagementException;
}