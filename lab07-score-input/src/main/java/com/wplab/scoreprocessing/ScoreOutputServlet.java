package com.wplab.scoreprocessing;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ScoreOutputServlet
 */
public class ScoreOutputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreOutputServlet() {
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
		
		// step #2. data processing
		ScoreDO scoreDO = (ScoreDO)request.getAttribute("score_do");
		
		// step #3. output results
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>성적 처리 결과</title>");
		out.println("</head>");
		out.println("<body style='padding-left; 100px'>");
		out.println("<h1>성적 처리 결과</h1><hr>");
		out.println("<div style='font-size: 1.2rem;'>");
		out.println("1. 현재 입력 성적: <br>");
		int[] scores = scoreDO.getScores();
		for(int i=0; i<scores.length; i++) {
			out.printf("&nbsp;&nbsp&nbsp;#%d : %d<br>\n", (i+1), scores[i]);
		}
		out.println("<br<br>");
		out.println("<p>2. 총합: " + scoreDO.getTotal() + "</p>");
		out.println("<p>3. 평균: " + scoreDO.getAverage() + "</p>");
		out.println("<p>4. 표준 편차: " + scoreDO.getStdDev() + "</p>");
		out.println("</div>");
		out.println("<p><button onclick='window.history.back();'>들어가기</button></p>");
		out.println("</body>");
		out.println("</html>");
		
		out.println("this is ScoreOutputSerblet!...");
	}

}
