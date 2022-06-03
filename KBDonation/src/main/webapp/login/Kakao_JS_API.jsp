<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>카카오로 로그인 with JS API</title>
    <link href="../IconLogo.png" rel="shortcut icon" type="image/x-icon">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
        // JS SDK로 처리하는방법
        Kakao.init('0b2e79009a9bb6e215fa4d63df7558e5');

        Kakao.Auth.login({
            success: function(authObj) {
                Kakao.API.request({
                    url: '/v2/user/me',
                    success: function(res){
                        $.ajax({
	                        	url:"<%=request.getContextPath()%>/KakaoLogin.do",
	                            data:{"id":res.id, "name":JSON.stringify(res.properties.nickname), "gender":JSON.stringify(res.properties.gender)},
	                            Type:"post",
	                            success:function(data){
                                //성공적으로 하고나면 이동할 url
                                location.href="<%=request.getContextPath()%>/";
                            }
                        });

                    },
                    fail : function(error){
                    	alert(JSON.stringify(error));
                    }
                });
                console.log(authObj);
               /*  const token = authObj.access_token; */
            },
            fail: function(err) {
                alert(JSON.stringify(err));
            }
        });
    </script>
   
</body>
</html>
