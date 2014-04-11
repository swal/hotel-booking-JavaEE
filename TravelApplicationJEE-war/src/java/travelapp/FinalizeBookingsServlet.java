package travelapp;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import travelapp.hotel.BookingException;

import travelapp.hotel.entity.Booking;
import travelapp.session.BookingSession;

@SuppressWarnings("serial")
public class FinalizeBookingsServlet extends HttpServlet {

    @EJB
    private BookingSession bookingSession;
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        HttpSession session = req.getSession();
                    
        PlannedTrip trip = (PlannedTrip)session.getAttribute("trip");
        if(trip == null || trip.getBookings().isEmpty()) {
            req.setAttribute("msg", "There are no tentative bookings to present.");
            try {
                req.getRequestDispatcher("output.jsp").forward(req, resp);
                return;
            } catch (ServletException e) {
                // redirect to home page
                resp.sendRedirect("index.jsp");
                return;
            }
        }
            
        List<Booking> bookings = null;
        try{
            bookings = bookingSession.finalizeBookings(trip.getBookings());
        } catch(BookingException ex) {
            req.setAttribute("msg",ex.getMessage());
            try {
                req.getRequestDispatcher("output.jsp").forward(req, resp);
                return;
            } catch (ServletException e) {
                // redirect to home page
                resp.sendRedirect("index.jsp");
                return;
            }
        }
            
        try {
            req.setAttribute("booked", bookings);
            // send email to guest with bill
            // give overview of finalized bookings
            req.getRequestDispatcher("finalized.jsp").forward(req, resp);
        } catch (ServletException e) {
            // redirect to home page
            resp.sendRedirect("index.jsp");
        }
    }
}
