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
package travelapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import travelapp.hotel.entity.Booking;

@SuppressWarnings("serial")
public class RemoveBookingServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
	PlannedTrip trip = (PlannedTrip)session.getAttribute("trip");
	List<Booking> toRemove = new ArrayList<Booking>();
	for(Booking b : trip.getBookings()) {
            // TODO b.toString() is niet uniek!!!
            if(req.getParameter(b.toString()) != null
                    && req.getParameter(b.toString()).equals("T")) {
		toRemove.add(b);
            }
	}
	for(Booking b : toRemove) {
            trip.remove(b);
	}
	session.setAttribute("trip", trip);
	
	resp.sendRedirect("bookings.jsp");
    }
}
