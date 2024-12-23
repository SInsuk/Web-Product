<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    String name = (String)session.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>재미있는 2분 성격 테스트</title>
    <style>
        body {
            width: 600px;
            margin-top: 50px;
            margin-left: 50px;
        }
        header {
            height: 100px;
        }
        .question {
            padding-top: 25px;
        }
        .buttons {
            padding-top: 50px; 
            text-align: right;
        }
    </style>
</head>
<body>
    <header>
        <h1>재미있는 2분 성격 테스트</h1>
        <div style="text-align: right;">
            <p>반갑습니다, <%= name %> 님!</p>
        </div>
        <hr>
    </header>
    <article>
        <form action="test5" method="POST">
            <%
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try {
                    Class.forName("org.h2.Driver");
                    conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                    
                    // 일곱 번째 질문
                    stmt = conn.prepareStatement("SELECT question_text FROM questions WHERE question_id = 7");
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        out.println("<div class='question'><p>7. " + rs.getString("question_text") + "</p>");
                        out.println("<div>");
                        out.println("<input type='radio' name='q7' value='6' required> a. 휴식의 기회를 반갑게 맞이한다<br>");
                        out.println("<input type='radio' name='q7' value='2'> b. 열라 짜증이 훨훨난다<br>");
                        out.println("<input type='radio' name='q7' value='4'> c. 그 중간 어딘가쯤<br>");
                        out.println("</div></div>");
                    }
                    
                    // 여덟 번째 질문
                    stmt = conn.prepareStatement("SELECT question_text FROM questions WHERE question_id = 8");
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        out.println("<div class='question'><p>8. " + rs.getString("question_text") + "</p>");
                        out.println("<div>");
                        out.println("<input type='radio' name='q8' value='6' required> a. 빨강이나 오렌지<br>");
                        out.println("<input type='radio' name='q8' value='7'> b. 까망<br>");
                        out.println("<input type='radio' name='q8' value='5'> c. 노랑이나 연한 파랑<br>");
                        out.println("<input type='radio' name='q8' value='4'> d. 녹색<br>");
                        out.println("<input type='radio' name='q8' value='3'> e. 짙은 파랑이나 보라<br>");
                        out.println("<input type='radio' name='q8' value='2'> f. 하양<br>");
                        out.println("<input type='radio' name='q8' value='1'> g. 갈색이나 회색<br>");
                        out.println("</div></div>");
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                    if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                    if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                }
            %>
            <div class="buttons">
                <input type="submit" value="다음 >>" />
            </div>
        </form>
    </article>
</body>
</html>
