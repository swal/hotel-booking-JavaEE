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
