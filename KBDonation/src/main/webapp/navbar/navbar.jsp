<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/navbar/navbar.css">
    <link href="../IconLogo.png" rel="shortcut icon" type="image/x-icon">
    <title>NavBar</title>
    <script src="${pageContext.request.contextPath}/navbar/navbar.js" defer></script>
    <script src="https://kit.fontawesome.com/4163862d0f.js" crossorigin="anonymous"></script>
</head>
<body>
    <nav class="navbar">
        <div class="navbar__logo">
            <img alt="EarthFunding" src="${pageContext.request.contextPath}/IconLogo.png">
            <a href="${pageContext.request.contextPath}/index.jsp">EarthFunding</a>
        </div>

        <ul class="navbar__menu">
            <li><a href="#">지구 시뮬레이션</a></li>
            <li><a href="#">펀딩하기</a></li>
            <li><a href="#">펀딩만들기</a></li>
            <li><a href="#">heal the world</a></li>
            <li><a href="#">우리의 추구</a></li>
        </ul>

        <ul class="navbar__icons">
            <%
                if (session.getAttribute("UserID") == null) {
            %>
            <li><i class="fa-solid fa-arrow-right-to-bracket"></i><a href="${pageContext.request.contextPath}/login/login.jsp">로그인</a></li>
            <li><i class="fa-solid fa-file-signature"></i><a href="#">회원가입</a></li>
            <%
                } else {
            %>
            <li><%= session.getAttribute("UserName")%>님 함께해요!</li>
            <li><a href="${pageContext.request.contextPath}/login/logout.jsp">로그아웃</a></li>
            <%
                }
            %>
        </ul>

        <a href="#" class="navbar__toogleBtn">
            <i class="fa-solid fa-bars"></i>
        </a>
    </nav>
</body>
</html>