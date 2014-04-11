package travelapp;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travelapp.config.RetrieveAllHotelsServlet;
import travelapp.hotel.HotelInfo;
import travelapp.session.BookingSession;

@SuppressWarnings("serial")
public class CheckAvailabilityServlet extends HttpServlet {
    
    @EJB
    private BookingSession bookingSession;
	
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	try {
            // TODO we gaan ervan uit dat alles ingevuld is
            DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
            Date startDate = formatter.parse(req.getParameter("startdate"));
            Date endDate = formatter.parse(req.getParameter("enddate"));

            List<HotelInfo> hotels = bookingSession.findAllHotelsWithFreeRoomsInPeriod(startDate, endDate);
            req.setAttribute(RetrieveAllHotelsServlet.key, hotels);
	} catch (ParseException e) {
            System.err.println(e.getMessage());
	}
		
	try {
            req.getRequestDispatcher("hotels.jsp").forward(req, resp);
	} catch (ServletException e) {
            // redirect to home page
            resp.sendRedirect("index.jsp");
	}
    }
}
