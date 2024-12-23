package com.wp.board;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BoardServlet
 */
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
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
		//step #1. get request parameters
		// 폼 매개변수 가져오기
        String title = request.getParameter("title");
        String name = request.getParameter("name");
        String contents = request.getParameter("contents");
        
        // 통계 계산
        int wordCount = countWords(contents);
        int characterCount = countCharacters(contents);
        int sentenceCount = countSentences(contents);

        
        //step #2. data processing
        // 현재 날짜와 시간 가져오기
        String writeTime = String.format("%TF %TT", new Date(), new Date());

        // 통계 문자열 생성
        String stat = String.format("글자 수: %d, 단어 수: %d, 문장 수: %d", characterCount, wordCount, sentenceCount);

        // JSON 객체 준비
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("title", title);
        jsonMap.put("name", name);
        jsonMap.put("contents", contents);
        jsonMap.put("writeTime", writeTime);
        jsonMap.put("statistics", stat);

        // JSON 객체를 문자열로 변환
        String jsonData = toJson(jsonMap);

        // 파일에 내용 저장
        saveToFile(jsonData);

        
      //step #3. output results
        // 응답 콘텐츠 타입 설정
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // 응답 HTML 출력
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>게시판 글쓰기 결과</title></head>");
        out.println("<body>");
        out.println("<h1>게시판 글쓰기 결과</h1><hr>");
        out.println("<p>제목: " + title + "</p>");
        out.println("<p>작성자: " + name + "</p>");
        out.println("<p>작성 일자: " + writeTime + "</p>");
        out.println("<p>통계: " + stat + "</p>");
        out.println("<p><button onclick='window.history.back();'>글쓰기</button></p>");
        out.println("</body>");
        out.println("</html>");
    }

    // 단어 개수 세는 메서드
    private int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] words = text.split("\\s+");
        return words.length;
    }

    // 문자 개수 세는 메서드
    private int countCharacters(String text) {
        return text.replaceAll("[^a-zA-Zㄱ-힣]", "").length();
    }

    // 문장 개수 세는 메서드
    private int countSentences(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] sentences = text.split("[.!?]+");
        return sentences.length;
    }

    // 맵을 JSON 문자열로 변환하는 메서드
    private String toJson(Map<String, String> map) {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
        }
        sb.deleteCharAt(sb.length() - 1); // 마지막 쉼표 제거
        sb.append("}");
        return sb.toString();
    }

    // 파일에 내용 저장하는 메서드
    private void saveToFile(String jsonData) {
        // 파일 이름 정의
        String fileName = "board_" + new Date().toString().replace(" ", "_").replace(":", "-") + ".json";
        
        // 파일 경로 정의
        String filePath = "C:\\Users\\Inseok\\Desktop\\" + fileName; // 파일 경로
        
        // 파일 객체 생성
        File file = new File(filePath);
        
        // JSON 데이터를 파일에 쓰기
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
