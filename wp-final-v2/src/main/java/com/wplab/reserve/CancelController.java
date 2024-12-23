package com.wplab.reserve;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.wplab.service.*;

/**
 * Servlet implementation class CancelController
 */
public class CancelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 예약 취소 화면으로 이동
        int rsv_seq = Integer.parseInt(request.getParameter("rsv_seq"));
        String passwd = request.getParameter("passwd");

        DBConnectionInfo dbInfo = (DBConnectionInfo) getServletContext().getAttribute("dbInfo");
        if (dbInfo == null) {
            throw new ServletException("DBConnectionInfo not found in ServletContext.");
        }
        ReservationDAO dao = new ReservationDAO(dbInfo);
        
        Reservation reservation = dao.getReservation(rsv_seq, passwd);
        request.setAttribute("reservation", reservation);
        request.getRequestDispatcher("cancel.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rsv_seq = Integer.parseInt(request.getParameter("rsv_seq"));
        String passwd = request.getParameter("passwd");

        DBConnectionInfo dbInfo = (DBConnectionInfo) getServletContext().getAttribute("dbInfo");
        if (dbInfo == null) {
            throw new ServletException("DBConnectionInfo not found in ServletContext.");
        }
        ReservationDAO dao = new ReservationDAO(dbInfo);
        
        boolean success = dao.cancelReservation(rsv_seq, passwd);

        if (success) {
            request.setAttribute("message", "예약이 성공적으로 취소되었습니다.");
        } else {
            request.setAttribute("message", "예약 취소에 실패했습니다.");
        }
        
        // 예약 취소 후 main.html로 리다이렉트
        response.sendRedirect("main.html");
    }
}
