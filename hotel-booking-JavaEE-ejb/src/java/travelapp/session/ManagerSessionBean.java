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

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import travelapp.hotel.ManagementException;
import travelapp.hotel.entity.Booking;
import travelapp.hotel.entity.Hotel;
import travelapp.hotel.entity.Room;
import travelapp.hotel.entity.RoomDetails;
import util.Address;

/**
 *
 * 
 * @author Stefan Walraven <stefan.walraven@cs.kuleuven.be>
 * 
 * Date: 01-Jun-2012
 */
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Stateless
public class ManagerSessionBean implements ManagerSession {

    @PersistenceContext
    private EntityManager em;
    
    @Resource
    private SessionContext ctx;
    
    /**********************
     * ADD HOTELS & ROOMS *
     **********************/

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void addNewHotel(String hotelName, Address address)
                                            throws ManagementException {
        try {
            em.persist(new Hotel(hotelName, address));
        } catch(Exception e) {
            ctx.setRollbackOnly();
            throw new ManagementException("Adding a new hotel failed.");
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void addNewRoomToHotel(String hotelName, RoomDetails details)
                                            throws ManagementException {
        try {
            Hotel hotel = em.find(Hotel.class, hotelName);
            hotel.addRoom(new Room(hotelName, details));
        } catch(Exception e) {
            ctx.setRollbackOnly();
            throw new ManagementException("Adding a new room failed.");
        }
    }

    /************************************
     * LOOK UP HOTELS, ROOMS & BOOKINGS *
     ************************************/

    @Override
    public List<String> getHotels() {
        return em.createNamedQuery("findAllHotelNames").getResultList();
    }
    
    @Override
    public Address getHotelAddress(String hotelName) {
        return em.find(Hotel.class, hotelName).getAddress();
    }

    @Override
    public List<RoomDetails> getRooms(String hotel) {
        List<Room> rooms = em.createNamedQuery("findAllRoomsInHotel")
                .setParameter("hotelName", hotel)
                .getResultList();
        List<RoomDetails> details = new ArrayList<RoomDetails>();
        for(Room r : rooms)
            details.add(r.getDetails());
        return details;
    }
    
    @Override
    public List<Booking> getBookings() {
        return em.createNamedQuery("findAllBookings").getResultList();
    }

    @Override
    public List<Booking> getBookings(String hotel) {
        return em.createNamedQuery("findAllBookingsInHotel")
                .setParameter("hotelName", hotel)
                .getResultList();
    }
    
    /************
     * CLEAN UP *
     ************/

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void removeAllBookings() throws ManagementException {
        try {
            em.createNamedQuery("removeAllBookings").executeUpdate();
        } catch(Exception e) {
            ctx.setRollbackOnly();
            throw new ManagementException("Removing bookings failed.");
        }
    }
}