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

import javax.persistence.Embeddable;

/**
 * Entity class representing the details of a room
 * 
 * @author Stefan Walraven <stefan.walraven@cs.kuleuven.be>
 *
 * Date: 03-Dec-2010
 */
@SuppressWarnings("serial")
@Embeddable
public class RoomDetails implements Serializable {
    
    private int roomNb;
    private int nbOfBeds;
    private double pricePerNight;
    private boolean smokingAllowed;

    protected RoomDetails() {}
    
    /**
     * Constructor for <code>RoomDetails</code>
     * @param roomNb    the room number
     * @param nbOfBeds  the number of beds
     * @param pricePerNight  the price per night
     * @param smoking   smoking/non-smoking
     */
    public RoomDetails(int roomNb, int nbOfBeds, double pricePerNight,
                    boolean smoking){
        this.roomNb = roomNb;
        this.nbOfBeds = nbOfBeds;
        this.pricePerNight = pricePerNight;
        this.smokingAllowed = smoking;
    }
    
    /**
     * @return  the room number
     */
    public int getRoomNb(){
        return roomNb;
    }
    
    /**
     * @return  the number of beds
     */
    public int getNbOfBeds(){
        return nbOfBeds;
    }
    
    /**
     * @return  the price per night
     */
    public double getPricePerNight() {
        return pricePerNight;
    }
    
    /**
     * @return  True if smoking is allowed; False otherwise
     */
    public boolean isSmokingAllowed() {
        return smokingAllowed;
    }

    @Override
    public String toString() {
        String r = "Room " + getRoomNb() + ", " + getNbOfBeds() + " bed(s), "
                + getPricePerNight() + " euro per night, ";
        if (isSmokingAllowed())
            r += "smoking allowed.";
        else
            r += "non-smoking.";
        return r;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o instanceof RoomDetails) {
            RoomDetails other = (RoomDetails) o;
            if((other.getRoomNb() == getRoomNb()) && (other.isSmokingAllowed() == isSmokingAllowed())
                    && (other.getNbOfBeds() == getNbOfBeds()) 
                    && (Double.compare(other.getPricePerNight(), getPricePerNight()) == 0))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.smokingAllowed ? 1 : 0);
        hash = 79 * hash + this.roomNb;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.pricePerNight) ^ (Double.doubleToLongBits(this.pricePerNight) >>> 32));
        hash = 79 * hash + this.nbOfBeds;
        return hash;
    }
}