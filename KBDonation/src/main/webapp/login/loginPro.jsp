<%@ page contentType="text/html;charset=UTF-8" language="java"  import="login.UserDAO" import="login.UserVO" import="CookieManagement.CookieAdmin"%>
<%@ page import="login.KakaoVO" import="login.KakaoDAO" %>
<html>
<head>
    <title>이 창에서 로그인이 처리됩니다.</title>
</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        String UserID = request.getParameter("UserID");
        String UserPWD = request.getParameter("PWD");
        String Save_Check = request.getParameter("cookieCheck");

        UserDAO dao = new UserDAO();
        UserVO userVo = dao.getUserVO(UserID, UserPWD);

        if (userVo.getID() != null){
            session.setAttribute("UserID", userVo.getID());
            session.setAttribute("UserName", userVo.getNICKNAME());
            if (Save_Check != null && Save_Check.equals("Y")) {
                CookieAdmin.makeCookie(response, "UserID", UserID, 86400);
            } else {
                CookieAdmin.deleteCookie(response, "UserID");
            }
            response.sendRedirect("../index.jsp");
        }
        else {
            request.setAttribute("LogErrorMsg","아이디와 비밀번호를 확인해주세요.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        
    %>
</body>
</html>
