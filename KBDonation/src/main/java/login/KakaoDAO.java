
package login;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KakaoDAO{
    DataSource dataSource;

	public KakaoDAO(){
        try{
            // Connection Pool을 찾는 과정
            Context context = new InitialContext(); // context 생
            dataSource = (DataSource)context.lookup("java:comp/env/dbcp_myoracle"); // context.xml에서 설정해준 Resource의 name부분
        }catch (Exception e){
            e.printStackTrace();
        }
    }


	public int getKakaoLogin(String id, String name){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql;
		/* KakaoVO kvo = new KakaoVO(); */
        int ok = 1;
        try{
            // dataSource로 부터 connection을 가져옴
            con = dataSource.getConnection();
            sql = "SELECT * FROM KBUserKakaoTest WHERE ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            rs = pstmt.executeQuery();
//            rs.next();
//            System.out.println(rs.getString(1));
//            if (rs.next()) {
//				/*
//				 * kvo.setID(rs.getString("id")); kvo.setName(rs.getString("name"));
//				 */
//            	System.out.println(rs.getString(1));
//            	System.out.println(id);
//            	System.out.println(rs.getString(1).equals(id));
//            	if(rs.getString(1).equals(id) == true) {
//                	System.out.println("입력된 값과 일치함");
//            		ok = 1;
//                } else {
//                	System.out.println("회원가입 필요");
//                	ok = 0;
//                }
//            
//            System.out.println("ID 확인처리 완료");
//            }
        }catch (Exception e2){
            System.out.println("You're Login Java was Denied for "+e2);
        }finally {
            try {
                if (con != null) con.close();
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
        return ok;
    }
	
	public void KakaoJoin(String id, String name, String PWD) {
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql;
//        KakaoVO kvo = new KakaoVO();
        try{
            // dataSource로 부터 connection을 가져옴
            con = dataSource.getConnection();
            sql = "INSERT INTO KBUSERKakaoTest VALUES(?,?,?)";
            pstmt = con.prepareStatement(sql);
            System.out.println("DAO가입 처리중..");
            
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, PWD);
            pstmt.executeUpdate();
            rs = pstmt.executeQuery();
//            while (rs.next()){
//            	kvo.setID(rs.getString("ID"));
//            	kvo.setName(rs.getString("Name"));
//            }
        }catch (Exception e2){
            System.out.println("You're Login Kakao was Denied for "+e2);
        }finally {
            try {
                if (con != null) con.close();
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
            
        }

        
    }
	
	// 값이 넘어왔음 / 없는 사용자면 ResultSet에 담길 내용도 없음. 그렇기 때문에 0 에러가 발생하는게 맞음.
	// ok에 1과 2 값 모두 넣어서 테스트 완료. -> 해당 returnal funcrtion 수정 필요.

}