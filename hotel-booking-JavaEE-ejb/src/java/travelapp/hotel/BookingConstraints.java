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
package travelapp.hotel;

import java.io.Serializable;
import java.util.Date;

/**
 * Class representing booking constraint
 * 
 * @author Stefan Walraven <stefan.walraven@cs.kuleuven.be>
 *
 * Date: 03-Dec-2010
 */
@SuppressWarnings("serial")
public class BookingConstraints implements Serializable {
    
    private Date startDate;
    private Date endDate;
    private double maxPricePerNight;
    private boolean smokingAllowed;
    private int nbOfBeds;
    
    /**
     * Constructor for <code>BookingConstraints</code>
     * @param start  the start of the booking period
     * @param end    the end of the booking period
     * @param nbOfBeds  the number of beds
     * @param maxPrice  the maximum price allowed
     * @param smoking   whether smoking should be allowed
     */
    public BookingConstraints(Date start, Date end, int nbOfBeds, double maxPrice, boolean smoking) {
        this.startDate = start;
        this.endDate = end;
        this.nbOfBeds = nbOfBeds;
	this.maxPricePerNight = maxPrice;
        this.smokingAllowed = smoking;
    }
    
    /**
     * Returns the start of the booking period
     * @return  the start
     */
    public Date getStartDate() {
        return startDate;
    }
    
    /**
     * Returns the end of the booking period
     * @return  the end
     */
    public Date getEndDate() {
        return endDate;
    }
    
    /**
     * Returns the number of beds
     * @return  the number of beds
     */
    public int getNbOfBeds() {
        return nbOfBeds;
    }

    /**
     * Returns the maximum price allowed for a booking
     * @return  the maximum price
     */
    public double getMaxPricePerNight() {
        return maxPricePerNight;
    }
    
    /**
     * Returns whether smoking is allowed
     * @return  True when smoking is allowed; false otherwise
     */
    public boolean isSmokingAllowed() {
        return smokingAllowed;
    }

@Override
    public String toString() {
        return "Booking constraints:<br />"
                + "From " + getStartDate() + " to " + getEndDate() + "<br />"
                + "# beds: " + getNbOfBeds() + "\t" + (isSmokingAllowed() ? "Smoking" : "Non-smoking") + "<br />"
                + "Max price: " + getMaxPricePerNight();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o instanceof BookingConstraints) {
            BookingConstraints other = (BookingConstraints) o;
            if(other.getStartDate().equals(getStartDate()) && (other.getEndDate().equals(getEndDate()))
                    && (other.getNbOfBeds() == getNbOfBeds()) && (other.isSmokingAllowed() == isSmokingAllowed())
                    && (Double.compare(other.getMaxPricePerNight(), getMaxPricePerNight()) == 0))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.startDate != null ? this.startDate.hashCode() : 0);
        hash = 59 * hash + (this.endDate != null ? this.endDate.hashCode() : 0);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.maxPricePerNight) ^ (Double.doubleToLongBits(this.maxPricePerNight) >>> 32));
        hash = 59 * hash + (this.smokingAllowed ? 1 : 0);
        hash = 59 * hash + this.nbOfBeds;
        return hash;
    }
}