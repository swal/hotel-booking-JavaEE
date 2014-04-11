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

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 * Entity class representing the details of a booking
 * 
 * @author Stefan Walraven <stefan.walraven@cs.kuleuven.be>
 *
 * Date: 03-Dec-2010
 */
@SuppressWarnings("serial")
@Entity
public class Booking implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String guest;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    private double price;
    private int roomNb;
    private String hotelName;

    protected Booking() {}
    
    /**
     * Constructor for <code>Booking</code>
     * @param guest    the client name
     * @param startDate  the start of the booking period
     * @param endDate    the end of the booking period
     * @param roomnb    the reserved room number
     * @param hotelName the hotel name
     * @param price	the price of the booking
     */
    Booking(String guest, Date startDate, Date endDate,
                            int roomnb, String hotelName, double price) {
        this.guest = guest;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.roomNb = roomnb;
        this.hotelName = hotelName;
    }
    
    /**
     * Returns the guest's name
     * @return  the guest's name
     */
    public String getGuest() {
        return guest;
    }
    
    /**
     * Returns the start of de booking period
     * @return  the start of the booking period
     */
    public Date getStartDate() {
        return startDate;
    }
    
    /**
     * Returns the end of the booking period
     * @return the end of the booking period
     */
    public Date getEndDate() {
        return endDate;
    }
    
    /**
     * Returns the price of the booking
     * @return the price of the booking
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Returns the booked room number
     * @return  the booked room number
     */
    public int getRoomNb() {
        return roomNb;
    }
    
    /** 
     * Returns the hotel name
     * @return the hotel name
     */
    public String getHotelName() {
        return hotelName;
    }
    
    /**
     * @return  the id
     */
    public Long getId() {
        return id;
    }

    /*
     * toString method
     */
    @Override
    public String toString() {
        return "Booking for " + getGuest() + "<br />"
                + "Hotel: "+ getHotelName() +", Room: " + getRoomNb() + "<br />"
                + "From " + getStartDate() + " to " + getEndDate() + "<br />"
                + "Total price: " + getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o instanceof Booking) {
            Booking other = (Booking) o;
            if(other.getStartDate().equals(getStartDate()) && other.getEndDate().equals(getEndDate())
                    && other.getGuest().equals(getGuest()) && other.getHotelName().equals(getHotelName())
                    && (other.getRoomNb() == getRoomNb()) && (Double.compare(other.getPrice(), getPrice()) == 0))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.startDate != null ? this.startDate.hashCode() : 0);
        hash = 43 * hash + (this.endDate != null ? this.endDate.hashCode() : 0);
        hash = 43 * hash + (this.guest != null ? this.guest.hashCode() : 0);
        hash = 43 * hash + (this.hotelName != null ? this.hotelName.hashCode() : 0);
        hash = 43 * hash + this.roomNb;
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        return hash;
    }
}