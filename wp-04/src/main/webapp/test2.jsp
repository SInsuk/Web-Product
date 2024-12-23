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
        <form action="test3" method="POST">
            <%
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try {
                    Class.forName("org.h2.Driver");
                    conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                    
                    // 세 번째 질문
                    stmt = conn.prepareStatement("SELECT question_text FROM questions WHERE question_id = 3");
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        out.println("<div class='question'><p>3. " + rs.getString("question_text") + "</p>");
                        out.println("<div>");
                        out.println("<input type='radio' name='q3' value='4' required> a. 내 팔짱을 끼고 서서<br>");
                        out.println("<input type='radio' name='q3' value='2'> b. 두손을 마주잡고<br>");
                        out.println("<input type='radio' name='q3' value='5'> c. 한손이나 양손을 힙에 얹고<br>");
                        out.println("<input type='radio' name='q3' value='7'> d. 얘기 나누는 상대방을 건드리거나 살짝 밀면서<br>");
                        out.println("<input type='radio' name='q3' value='6'> e. 내 귀나 턱을 만지작거리거나 손가락으로 머리를 빗으면서<br>");
                        out.println("</div></div>");
                    }
                    
                    // 네 번째 질문
                    stmt = conn.prepareStatement("SELECT question_text FROM questions WHERE question_id = 4");
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        out.println("<div class='question'><p>4. " + rs.getString("question_text") + "</p>");
                        out.println("<div>");
                        out.println("<input type='radio' name='q4' value='4' required> a. 다리를 굽힌 채로 나란히 두고 앉는다.<br>");
                        out.println("<input type='radio' name='q4' value='6'> b. 다리를 꼬고 앉는다<br>");
                        out.println("<input type='radio' name='q4' value='2'> c. 다리를 쭉 펴고 앉는다<br>");
                        out.println("<input type='radio' name='q4' value='1'> d. 한 쪽 다리를 접어 깔고 앉는다<br>");
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
