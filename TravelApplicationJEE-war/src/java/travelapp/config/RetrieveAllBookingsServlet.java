package travelapp.config;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travelapp.hotel.entity.Booking;
import travelapp.session.ManagerSession;

@SuppressWarnings("serial")
public class RetrieveAllBookingsServlet extends HttpServlet {
    
    @EJB
    private ManagerSession managerSession;
	
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Booking> bookings = managerSession.getBookings();
	req.setAttribute("bookings", bookings);
		
	try {
            req.getRequestDispatcher("bookings.jsp").forward(req, resp);
	} catch (ServletException e) {
            // redirect to home page
            resp.sendRedirect("index.jsp");
	}
    }
}
