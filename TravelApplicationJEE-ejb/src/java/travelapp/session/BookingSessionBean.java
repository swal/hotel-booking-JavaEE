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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import travelapp.feature.price.PriceCalculation;
import travelapp.hotel.BookingConstraints;
import travelapp.hotel.BookingException;
import travelapp.hotel.HotelInfo;
import travelapp.hotel.entity.Booking;
import travelapp.hotel.entity.Hotel;

/**
 *
 * 
 * @author Stefan Walraven <stefan.walraven@cs.kuleuven.be>
 * 
 * Date: 01-Jun-2012
 */
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Stateless
public class BookingSessionBean implements BookingSession {
    
    @PersistenceContext
    private EntityManager em;
    
    @Resource
    private SessionContext ctx;
    
    @Inject
    private PriceCalculation calc;
    
    /***********
     * LOOK UP *
     ***********/

    @Override
    public List<HotelInfo> findAllHotelsInCity(String city) { 
        List<String> hotelnames = em.createNamedQuery("findAllHotelsInCity")
                .setParameter("city", city)
                .getResultList();
        return getHotelInfos(hotelnames);
    }

    @Override
    public List<HotelInfo> findAllHotelsWithFreeRoomsInPeriod(Date start, Date end) {
        List<String> hotelnames = em.createNamedQuery("findAllHotelsWithFreeRoomsBetweenGivenPeriod")
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
        return getHotelInfos(hotelnames);
    }
    
    private List<HotelInfo> getHotelInfos(List<String> hotelnames) {
        List<HotelInfo> hotelInfos = new ArrayList<HotelInfo>();
        for(String hName : hotelnames)
            hotelInfos.add(createHotelInfo(hName));
        return hotelInfos;
    }
    
    private HotelInfo createHotelInfo(String hotelName) {
        return new HotelInfo(hotelName, em.find(Hotel.class, hotelName).getAddress());
    }
    
    private Hotel getHotel(String name) {
        return em.find(Hotel.class, name);
    }
    
    /************
     * BOOKINGS *
     ************/

    @Override
    public Booking createBooking(String hotelName, BookingConstraints bookingConstraints, String client) throws BookingException {
        return getHotel(hotelName).createBooking(bookingConstraints, client, calc);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public List<Booking> finalizeBookings(List<Booking> tentativeBookings) throws BookingException {
        for (Booking b : tentativeBookings) {
            try {
                Hotel hotel = getHotel(b.getHotelName());
                hotel.finalizeBooking(b);
            } catch (BookingException ex) {
                ctx.setRollbackOnly();
                throw ex;
            }
        }
        List<Booking> finalized = Collections.unmodifiableList(tentativeBookings);
        return finalized;
    }
}