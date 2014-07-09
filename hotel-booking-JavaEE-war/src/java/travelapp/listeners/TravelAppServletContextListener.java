/**
 *     Copyright (c) KU Leuven Research and Development - iMinds-DistriNet
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
package travelapp.listeners;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import travelapp.hotel.ManagementException;

import travelapp.hotel.entity.RoomDetails;
import travelapp.session.ManagerSession;
import util.Address;

public class TravelAppServletContextListener implements ServletContextListener {
    
    @EJB
    private ManagerSession managerSession;
    
    /* 
     * (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // This will be invoked as part of a warming request, 
	// or the first user request if no warming request was invoked.
		
	// check if dummy data is available, and add if necessary
	if(!isDummyDataAvailable()) {
            addDummyData();
	}
    }
	
    private boolean isDummyDataAvailable() {
        if(managerSession.getHotels().isEmpty())
            return false;
        return true;
    }
	
    private void addDummyData() {
        try {
//          tx.begin();
            managerSession.addNewHotel("Fawlty Towers", new Address("Celestijnenlaan", 200, 3001, "Heverlee", "Belgium"));
            managerSession.addNewHotel("Rilton", new Address("Naamsestraat", 80, 3000, "Leuven", "Belgium"));
            for(String hotelName : managerSession.getHotels()) {
                RoomDetails details = new RoomDetails(1, 1, 70.0, false);
                managerSession.addNewRoomToHotel(hotelName, details);
                details = new RoomDetails(2, 2, 90.0, true);
	        managerSession.addNewRoomToHotel(hotelName, details);
	        details = new RoomDetails(3, 3, 100.0, false);
	        managerSession.addNewRoomToHotel(hotelName, details);
	        details = new RoomDetails(4, 4, 150.0, false);
                managerSession.addNewRoomToHotel(hotelName, details);
            }
//          try {
//              tx.commit();
//          } catch (Exception ex) {
//              tx.rollback();
//              System.err.println("Rollback of initialization");
//          }
        } catch (ManagementException ex) {
            Logger.getLogger(TravelAppServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
	
    /* 
     * (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {      
    }
}
