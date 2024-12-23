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
        <form action="result" method="POST">
            <%
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try {
                    Class.forName("org.h2.Driver");
                    conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                    
                    // 아홉 번째 질문
                    stmt = conn.prepareStatement("SELECT question_text FROM questions WHERE question_id = 9");
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        out.println("<div class='question'><p>9. " + rs.getString("question_text") + "</p>");
                        out.println("<div>");
                        out.println("<input type='radio' name='q9' value='7' required> a. 몸을 똑바로 펴고 누운 포즈이다<br>");
                        out.println("<input type='radio' name='q9' value='6'> b. 엎드린 채로 몸을 죽 편 포즈이다<br>");
                        out.println("<input type='radio' name='q9' value='4'> c. 약간 몸을 둥그린 채로 옆으로 누운포즈이다<br>");
                        out.println("<input type='radio' name='q9' value='2'> d. 한팔을 베고 있다<br>");
                        out.println("<input type='radio' name='q9' value='1'> e. 머리를 이불 밑에 넣고있다<br>");
                        out.println("</div></div>");
                    }
                    
                    // 열 번째 질문
                    stmt = conn.prepareStatement("SELECT question_text FROM questions WHERE question_id = 10");
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        out.println("<div class='question'><p>10. " + rs.getString("question_text") + "</p>");
                        out.println("<div>");
                        out.println("<input type='radio' name='q10' value='4' required> a. 싸우거나 애쓰는꿈<br>");
                        out.println("<input type='radio' name='q10' value='2'> b. 무엇이나 누군가를 찾는꿈<br>");
                        out.println("<input type='radio' name='q10' value='3'> c. 날아오르거나 떠오르는 꿈<br>");
                        out.println("<input type='radio' name='q10' value='5'> d. 꿈은 잘 꾸지 않는다<br>");
                        out.println("<input type='radio' name='q10' value='1'> e. 항상 좋은 느낌의 꿈이다<br>");
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
                <input type="submit" value="결과 확인 >>" />
            </div>
        </form>
    </article>
</body>
</html>
