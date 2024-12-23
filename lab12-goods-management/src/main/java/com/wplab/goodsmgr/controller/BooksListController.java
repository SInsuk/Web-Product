package com.wplab.goodsmgr.controller;

import java.io.IOException;
import java.util.List;

import com.wplab.goodsmgr.service.GoodsDO;
import com.wplab.goodsmgr.service.GoodsinfoDAO;
import com.wplab.goodsmgr.service.GoodsinfoDAOImplByDBCP;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BooksListController
 */
public class BooksListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BooksListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		
		// step #2. data processing using service module
		// get books list from DB
		String dbcpResourceName = 
			getServletContext().getInitParameter("dbcp_resource_name");
		GoodsinfoDAO dao = new GoodsinfoDAOImplByDBCP(dbcpResourceName);
		
		List<GoodsDO> goodsList = dao.listGoods();
		
		// step #3. output results -> view navigation
		request.setAttribute("goods_list", goodsList);
		
		RequestDispatcher view = 
			request.getRequestDispatcher("../WEB-INF/views/list_goods.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
