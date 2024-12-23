package com.wplab.goodsmgr.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.wplab.goodsmgr.service.GoodsDO;
import com.wplab.goodsmgr.service.GoodsinfoDAO;
import com.wplab.goodsmgr.service.GoodsinfoDAOImplByDBCP;

/**
 * Servlet implementation class BooksDeleteController
 */
public class BooksDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BooksDeleteController() {
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
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("code");
			
		// step #2. data processing using service module
		// update book info on DB
		String dbcpResourceName = 
			getServletContext().getInitParameter("dbcp_resource_name");
		GoodsinfoDAO dao = new GoodsinfoDAOImplByDBCP(dbcpResourceName);
		
		GoodsDO goods = new GoodsDO();
		goods.setCode(code);
		
		dao.deleteGoods(goods);
				
		// step #3. output results -> view navigation
		// forwarding or redirection?
		// send redirection to client
		response.sendRedirect(
			request.getContextPath() +"/books/list");
	}

}
