<%@ page contentType="text/html;charset=UTF-8" language="java" import="CookieManagement.CookieAdmin" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="../IconLogo.png" rel="shortcut icon" type="image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="login.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/4163862d0f.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <title>로그인 | KBD</title>
    <script src="login.js"></script>
</head>
<body>
<%
    String UserID = CookieAdmin.readCookie(request,"UserID");
    String cookieCheck = "";
    if (!UserID.equals("")){
        cookieCheck = "checked";
    }
%>
    <jsp:include page="../navbar/navbar.jsp"></jsp:include>
    <article class="section">
        <div class="login__group">
            <form action="loginPro.jsp" method="post" class="login__form" onsubmit="return val1(this);">
                <div>
                    <h1 class="login__title">로그인</h1>
                </div>
                <div>
                    <label class="login__set">아이디를 입력하세요.</label>
                    <label>
                        <input type="text" id="uid" name="UserID" class="login__input__ID" value="<%=UserID%>" />
                    </label>
                </div>
                <div>
                    <label class="login__set">비밀번호를 입력하세요.</label>
                    <label>
                        <input type="password" id="pwd" name="PWD" class="login__input__PWD" />
                    </label>
                </div>
                <div>
                    <label class="login__errmsg">
                        <%=
                        request.getAttribute("LogErrorMsg") == null ?
                                "" : request.getAttribute("LogErrorMsg")
                        %>
                    </label>
                </div>
                <div>
                    <div class="id__save__box">
                        <label for="svchk">
                            <input type="checkbox" id="svchk" name="cookieCheck" value="Y" <%=cookieCheck%>/>
                            <b>아이디 저장</b>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="login__sub">
                        <input type="submit" value="로그인">
                    </div>
                </div>
            </form>
            <div>
                <ul class="find__menu">
                    <li><a href="${pageContext.request.contextPath}/find/findID.jsp">아이디 찾기 ></a></li>
                    <li><a href="${pageContext.request.contextPath}/find/findPWD.jsp">비밀번호 찾기 ></a></li>
                </ul>
            </div>

<%--            <div class="hr-sect">SNS Login</div>--%>
            <hr/>
            <%-- Kakao Rest API KEY: f1d8a29b6067fda24f51d5763ff97cc1 --%>
            <div class="SNS">
                <ul>
                    <li><a href="Kakao_JS_API.jsp"><img src="KakaoBtn.png" alt="카카오로 로그인" class="SNS__Kakao"></a></li>
                </ul>
            </div>

    </article>

</body>
</html>
