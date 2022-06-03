package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class KakaoLogin
 */
@WebServlet("/KakaoLogin.do")
public class KakaoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();
		 	request.setCharacterEncoding("UTF-8");
	        String id = request.getParameter("id");
	        String name = request.getParameter("name");
	        //이름이 "홍길동" 식으로 넘어오기 때문에 "으로 짤라줌
	        String[] names = name.split("\"");
	        String PWD = "Kakaohash";
//	        String Gender = request.getParameter("gender");
	        //회원이 있는지 검사, 없으면 회원가입부터 진행 후 로그인
	        
	        System.out.println("개발자 Value 확인 로직");
	        System.out.println(id);
	        System.out.println(names[1]);
	        System.out.println(PWD);
//	        System.out.println(Gender);
	        
	        KakaoDAO kdao = new KakaoDAO();	
	        KakaoVO data = new KakaoVO();
	        int result = kdao.getKakaoLogin(id, name);
	        
	        System.out.println(result);
	        // 로그인 성공여부
	        if(result == 1) {
	        	System.out.println("가입이 완료된 사용자 처리");
	        	session = request.getSession();
		        session.setAttribute(data.getID(),"UserID");
		        session.setAttribute(data.getName(),"UserName");
		        System.out.println("---------------------------\n");
	        } else if (result == 2) {
	        	System.out.println("회원가입 처리중..");	        	
	        	data.setID(id);
	        	data.setName(names[1]);
	        	data.setPWD(PWD);
	        	kdao.KakaoJoin(data);
	        	System.out.println("---------------------------\n");
	        } else if (result == 0) {
	        	System.out.println("몰?루");
	        }
	        response.getWriter().append("/");
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
