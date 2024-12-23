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
 * Servlet implementation class ReserveController
 */
public class ReserveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnectionInfo dbInfo = (DBConnectionInfo) getServletContext().getAttribute("dbInfo");
        if (dbInfo == null) {
            throw new ServletException("DBConnectionInfo not found in ServletContext.");
        }
        SeatDAO seatDAO = new SeatDAO(dbInfo);
        List<Seat> seats = seatDAO.getAllSeats();
        request.setAttribute("seats", seats);
        request.getRequestDispatcher("reserve.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");
        String phone = request.getParameter("phone");
        int seat_number = Integer.parseInt(request.getParameter("seat_number"));

        DBConnectionInfo dbInfo = (DBConnectionInfo) getServletContext().getAttribute("dbInfo");
        if (dbInfo == null) {
            throw new ServletException("DBConnectionInfo not found in ServletContext.");
        }
        ReservationDAO dao = new ReservationDAO(dbInfo);
        
        Reservation reservation = new Reservation();
        reservation.setName(name);
        reservation.setPasswd(passwd);
        reservation.setPhone(phone);
        reservation.setSeat_number(seat_number);

        try {
            dao.reserveSeat(reservation);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Reservation failed");
            return;
        }

        // 예약 후 예약 정보를 가져옴
        Reservation newReservation = dao.getLatestReservation();
        
        request.setAttribute("reservation", newReservation);
        request.getRequestDispatcher("confirmResult.jsp").forward(request, response);
    }
}
