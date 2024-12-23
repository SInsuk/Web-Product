package com.wplab.scoreprocessing;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ScoreOutputServlet
 */
public class ScoreInputServletBySession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreInputServletBySession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		
		
		// step #2. data processing
		int count=0;
		
		HttpSession session = request.getSession();
		List<Integer> scores = new ArrayList<Integer>();
		if(session.isNew()) {
			session.setAttribute("scores", scores);
		}
		else {
			scores = (List<Integer>)session.getAttribute("scores");
			count = scores.size();
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
		
		String score = request.getParameter("score");
		String action = request.getParameter("action");
		
		// step #2. data processing
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}
		
		List<Integer> scores = (List<Integer>)session.getAttribute("scores");
		
		
		if (action.equals("입력")) {
			//store input score to session
			scores.add(Integer.parseInt(score));
			
			session.setAttribute("scores", scores);
			
			
			// step #3. output results
			response.sendRedirect(request.getHeader("Referer"));
			
		}
		else if(action.equals("출력")) {
			//check if input scores exist in cookie
			if(scores.size() == 0) {
				response.sendRedirect(request.getHeader("Referer"));
			}
			else {
				//score processing
				ScoreDO scoreDO = new ScoreDO();
				
				int[] scoresValue = scores.stream().mapToInt(Integer::intValue).toArray();
				
				scoreDO.setScores(scoresValue);
				
				ScoreProcessingService service = new ScoreProcessingService();
				service.processingScores(scoreDO);
				
				//output processing results
				request.setAttribute("score_do", scoreDO);
				
				session.invalidate();
				
				RequestDispatcher view = request.getRequestDispatcher("score_output.do");
				view.forward(request, response);
				
				
			}
		}
		
		// step #3. output results
	
	}

}
