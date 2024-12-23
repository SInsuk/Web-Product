package com.wplab.reserve;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.wplab.service.*;

/**
 * Servlet implementation class ConfirmController
 */
public class ConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       request.getRequestDispatcher("confirm.jsp").forward(request, response);
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
        
        Reservation reservation = dao.getReservation(rsv_seq, passwd);
        
        request.setAttribute("reservation", reservation);
        request.getRequestDispatcher("confirmResult.jsp").forward(request, response);
    }
}