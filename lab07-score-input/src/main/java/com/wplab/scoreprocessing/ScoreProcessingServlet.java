package com.wplab.scoreprocessing;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ScoreProcessingServlet
 */
public class ScoreProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreProcessingServlet() {
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
		
		String[] scores = request.getParameterValues("score");
		
		ScoreDO scoreDO = new ScoreDO();
		int[] scoreValues = new int[scores.length];
		for (int i =0; i<scores.length; i++) {
			scoreValues[i] = Integer.parseInt(scores[i]);
		}
		
		scoreDO.setScores(scoreValues);
		// step #2. data processing
		ScoreProcessingService service = new ScoreProcessingService();
		service.processingScores(scoreDO);
		
		// step #3. output results
		// request forwarding to presentation logic servlet
		request.setAttribute("score_do", scoreDO);
		
		RequestDispatcher view = request.getRequestDispatcher("score_output.do");
		
		view.forward(request, response);
	}

}
