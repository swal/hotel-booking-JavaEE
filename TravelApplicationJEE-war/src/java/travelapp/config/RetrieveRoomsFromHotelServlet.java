package travelapp.config;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travelapp.hotel.entity.RoomDetails;
import travelapp.session.ManagerSession;

@SuppressWarnings("serial")
public class RetrieveRoomsFromHotelServlet extends HttpServlet {
    
    @EJB
    private ManagerSession managerSession;
	
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
        String hotelName = req.getParameter("hotel");
        List<RoomDetails> rooms = managerSession.getRooms(hotelName);
        req.setAttribute("rooms", rooms); 
		
	try {
            req.getRequestDispatcher("rooms.jsp").forward(req, resp);
	} catch (ServletException e) {
            // redirect to home page
            resp.sendRedirect("index.jsp");
	} 	
    }
}
