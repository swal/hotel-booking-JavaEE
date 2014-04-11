package travelapp.config;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import travelapp.hotel.ManagementException;
import travelapp.session.ManagerSession;

@SuppressWarnings("serial")
public class RemoveAllBookingsServlet extends HttpServlet {
    
    @EJB
    private ManagerSession managerSession;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            managerSession.removeAllBookings();
        } catch (ManagementException e) {
            req.setAttribute("msg", e.getMessage());
            try {
		req.getRequestDispatcher("output.jsp").forward(req, resp);
		return;
            } catch (ServletException ex) {
		// redirect to home page
		resp.sendRedirect("index.jsp");
		return;
            }
	}
	resp.sendRedirect("index.jsp");
    }
}