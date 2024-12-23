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
        <form action="test2" method="POST">
            <%
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try {
                    Class.forName("org.h2.Driver");
                    conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                    
                    // 첫 번째 질문
                    stmt = conn.prepareStatement("SELECT question_text FROM questions WHERE question_id = 1");
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        out.println("<div class='question'><p>1. " + rs.getString("question_text") + "</p>");
                        out.println("<div>");
                        out.println("<input type='radio' name='q1' value='2' required> a. 아침<br>");
                        out.println("<input type='radio' name='q1' value='4'> b. 오후나 이른 저녁<br>");
                        out.println("<input type='radio' name='q1' value='6'> c. 늦은 밤<br>");
                        out.println("</div></div>");
                    }
                    
                    // 두 번째 질문
                    stmt = conn.prepareStatement("SELECT question_text FROM questions WHERE question_id = 2");
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        out.println("<div class='question'><p>2. " + rs.getString("question_text") + "</p>");
                        out.println("<div>");
                        out.println("<input type='radio' name='q2' value='6' required> a. 보폭을 넓게, 빨리 걷는다<br>");
                        out.println("<input type='radio' name='q2' value='4'> b. 보폭을 좁게, 빨리 걷는다<br>");
                        out.println("<input type='radio' name='q2' value='7'> c. 머리를 들고, 세상을 정면으로 바라보며 덜 빠르게 걷는다<br>");
                        out.println("<input type='radio' name='q2' value='2'> d. 바닥을 보며 덜 빠르게 걷는다<br>");
                        out.println("<input type='radio' name='q2' value='1'> e. 아주 느리게 걷는다<br>");
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
