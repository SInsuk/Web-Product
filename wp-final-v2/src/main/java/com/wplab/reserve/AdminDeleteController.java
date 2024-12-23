package com.wplab.reserve;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.wplab.service.*;

/**
 * Servlet implementation class AdminDeleteController
 */
public class AdminDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rsv_seq = Integer.parseInt(request.getParameter("rsv_seq"));
        int seat_number = Integer.parseInt(request.getParameter("seat_number"));
        String seatType = request.getParameter("seatType");

        DBConnectionInfo dbInfo = (DBConnectionInfo) getServletContext().getAttribute("dbInfo");
        if (dbInfo == null) {
            throw new ServletException("DBConnectionInfo not found in ServletContext.");
        }
        ReservationDAO dao = new ReservationDAO(dbInfo);
        
        boolean success = dao.deleteReservation(rsv_seq, seat_number);

        if (success) {
            request.setAttribute("message", "예약이 성공적으로 삭제되었습니다.");
        } else {
            request.setAttribute("message", "예약 삭제에 실패했습니다.");
        }
        
        // 예약 삭제 후 이전 페이지로 리다이렉트
        response.sendRedirect("admin?seatType=" + seatType);
    }
}
