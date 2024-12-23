package com.wplab.goodsmgr.controller;

import jakarta.servlet.RequestDispatcher;
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
 * Servlet implementation class BooksUpdateController
 */
public class BooksUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BooksUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("code");
		
		// step #2. data processing using service module
		// get book info from DB
		String dbcpResourceName = 
			getServletContext().getInitParameter("dbcp_resource_name");
		GoodsinfoDAO dao = new GoodsinfoDAOImplByDBCP(dbcpResourceName);
		
		GoodsDO goods = new GoodsDO();
		goods.setCode(code);
		
		goods = dao.getGoods(goods);
		
		// step #3. output results -> view navigation
		request.setAttribute("goods", goods);
		
		RequestDispatcher view = 
			request.getRequestDispatcher("../WEB-INF/views/update_form.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("code");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String price = request.getParameter("price");
			
		// step #2. data processing using service module
		// update book info on DB
		String dbcpResourceName = 
			getServletContext().getInitParameter("dbcp_resource_name");
		GoodsinfoDAO dao = new GoodsinfoDAOImplByDBCP(dbcpResourceName);
		
		GoodsDO goods = new GoodsDO();
		goods.setCode(code);
		goods.setTitle(title);
		goods.setWriter(writer);
		goods.setPrice(Integer.parseInt(price));
		
		dao.updateGoods(goods);
				
		// step #3. output results -> view navigation
		// forwarding or redirection?
		// send redirection to client
		response.sendRedirect(
			request.getContextPath() +"/books/list");
	}

}
