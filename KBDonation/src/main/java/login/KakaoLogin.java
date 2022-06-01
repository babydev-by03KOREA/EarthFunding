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
@WebServlet("/KakaoLogin")
public class KakaoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakaoLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();
		 	request.setCharacterEncoding("UTF-8");
	        String id = request.getParameter("id");
	        String name = request.getParameter("name");
	        //이름이 "홍길동" 식으로 넘어오기 때문에 "으로 짤라줌
	        String[] names = name.split("\"");
	        String PWD = "Kakaohash";
//	        String Gender =  request.getParameter("gender");
	        //회원이 있는지 검사, 없으면 회원가입부터 진행 후 로그인
	        KakaoVO data = new KakaoVO();
	       
	        System.out.println(id);
	        System.out.println(names[1]);
	        System.out.println(PWD);
	        KakaoDAO kdao = new KakaoDAO();
	        KakaoVO kvo = kdao.getKakaoLogin(id);
	        
	        if(kvo.getID() != null) {
	        	session = request.getSession();
	        	session.setAttribute("UserID", KakaoVO.getID());
		        session.setAttribute("UserName", KakaoVO.getName());
		        data.setID(id);
		        data.setName(names[1]);
		        data.setPWD(PWD);
		        System.out.println("if문 통과");
	        } else {
	        	kdao.KakaoJoin(data);
	        	System.out.println("else문 통과");
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
