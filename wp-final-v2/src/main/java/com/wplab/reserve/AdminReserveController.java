package com.wplab.reserve;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.wplab.service.*;

/**
 * Servlet implementation class AdminReserveController
 */
public class AdminReserveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReserveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String seatType = request.getParameter("seatType");
        System.out.println("Seat Type: " + seatType);

        DBConnectionInfo dbInfo = (DBConnectionInfo) getServletContext().getAttribute("dbInfo");
        if (dbInfo == null) {
            throw new ServletException("DBConnectionInfo not found in ServletContext.");
        }
        ReservationDAO dao = new ReservationDAO(dbInfo);
        
        List<Reservation> reservations = dao.getReservationsBySeatType(seatType);
        System.out.println("Reservations: " + reservations);

        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("adminReserveList.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
