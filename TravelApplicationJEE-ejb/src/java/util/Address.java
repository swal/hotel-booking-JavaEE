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
package util;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class representing an address
 * 
 * @author Stefan Walraven <stefan.walraven@cs.kuleuven.be>
 *
 * Date: 03-Dec-2010
 */
@SuppressWarnings("serial")
@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String city;
    private int postalCode;
    private String street;
    private int number;
    private String country;

    protected Address() {}

    public Address(String street, int number, int postalCode, String city, String country) {
        setStreet(street);
        setNumber(number);
        setPostalCode(postalCode);
    	setCity(city);
        setCountry(country);
    }

    /**
     * @return  the city
     */
    public String getCity() {
        return city;
    }
    
    private void setCity(String city) {
        this.city = city;
    }

    /**
     * @return  the postal code
     */
    public int getPostalCode() {
        return postalCode;
    }
    
    private void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return  the street
     */
    public String getStreet() {
        return street;
    }
    
    private void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return  the number
     */
    public int getNumber() {
        return number;
    }
    
    private void setNumber(int number) {
        this.number = number;
    }
    
    public String getCountry() {
        return country;
    }
    
    private void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return  the id
     */
    public Long getId() {
        return id;
    }
    
    private void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return getStreet() + "  " + getNumber() + "<br />" 
        + getPostalCode() + "  " + getCity().toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o instanceof Address) {
            Address other = (Address) o;
            if(getPostalCode() == other.getPostalCode() && getStreet().equals(other.getStreet())
                    && getNumber() == other.getNumber() && getCity().equals(other.getCity()))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 53 * hash + this.postalCode;
        hash = 53 * hash + (this.street != null ? this.street.hashCode() : 0);
        hash = 53 * hash + this.number;
        return hash;
    }
}