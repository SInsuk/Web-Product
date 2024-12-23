package com.wplab.scoreprocessing;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ScoreInputServlet
 */
public class ScoreInputServletByCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreInputServletByCookie() {
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
		
		String score = request.getParameter("score");
		String action = request.getParameter("action");
		
		// step #2. data processing
		Cookie[] cookies = request.getCookies();
		
		Cookie scores = findCookie(cookies, "score");
		if(scores == null) {
			scores =  new Cookie("scores", "");
		}
		
		Cookie count = findCookie(cookies, "count");
		if(count == null) {
			count =  new Cookie("count", "0");
		}
		
		if (action.equals("입력")) {
			// store input score to cookie
			String scoresStr = scores.getValue();
			scoresStr =  scoresStr + "+" + score;
			scores.setValue(scoresStr);
			
			int countValue = Integer.parseInt(count.getValue());
			countValue++;
			count.setValue(String.valueOf(countValue));
			
			response.addCookie(count);
			response.addCookie(scores);
			
			response.sendRedirect(request.getHeader("Referer"));
			
		}
		else if(action.equals("출력")) {
			//check if input scores exist in cookie
			if(count==null || count.getValue().equals("0")) {
				response.sendRedirect(request.getHeader("Referer"));
			}
			else {
				//score processing
				ScoreDO scoreDO = new ScoreDO();
				String[] scoresArray = scores.getValue().split("\\+");
				int[] scoresValue = new int[scoresArray.length];
				for (int i =0; i<scoresArray.length; i++) {
					scoresValue[i] = Integer.parseInt(scoresArray[i]);
				}
				
				scoreDO.setScores(scoresValue);
				
				ScoreProcessingService service = new ScoreProcessingService();
				service.processingScores(scoreDO);
				
				//output processing results
				request.setAttribute("score_do", scoreDO);
				
				scores.setMaxAge(0);
				count.setMaxAge(0);
				response.addCookie(scores);
				response.addCookie(count);
				
				RequestDispatcher view = request.getRequestDispatcher("score_output.do");
				view.forward(request, response);
				
				
			}
		}
		
		// step #3. output results
		
	
		
	}

}
