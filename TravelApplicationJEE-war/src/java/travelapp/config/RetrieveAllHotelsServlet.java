package travelapp.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import travelapp.hotel.HotelInfo;

import travelapp.session.ManagerSession;

@SuppressWarnings("serial")
public class RetrieveAllHotelsServlet extends HttpServlet {
	
    public static final String key = "hotels";
    
    @EJB
    private ManagerSession managerSession;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        List<HotelInfo> result = new ArrayList<HotelInfo>();
        for(String hName : managerSession.getHotels()) {
            result.add(new HotelInfo(hName, managerSession.getHotelAddress(hName)));
        }

        req.setAttribute(key, result);
		
	try {
		req.getRequestDispatcher("hotels.jsp").forward(req, resp);
	} catch (ServletException e) {
		// redirect to home page
		resp.sendRedirect("index.jsp");
	} 
    }
}
