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
