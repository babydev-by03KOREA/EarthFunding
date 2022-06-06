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


	public int getKakaoLogin(String id, String vname){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql;
//        KakaoVO kvo = new KakaoVO();
        int ok = 0;
        try{
            // dataSource로 부터 connection을 가져옴
            con = dataSource.getConnection();
            sql = "SELECT * FROM KBUserKakaoTest WHERE ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
//            	kvo.setID(rs.getString("id"));            	
//            	kvo.setName(rs.getString("name"));
            	System.out.println(rs.getString("name"));
            	System.out.println(vname);
            	System.out.println("입력된값과 DB값 비교값");
            	System.out.println(rs.getString("name").equals(vname));
            	boolean isT = rs.getString("name").equals(vname);
            	System.out.println(isT);
            	if(isT = true) {
                	System.out.println("입력된 값과 일치함");
            		ok = 1;
                } 
            	else if (isT = false){
                	System.out.println("회원가입 필요");
                	ok = 2;
                }
            }
           
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
	
	public void KakaoJoin(KakaoVO data) {
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
//        KakaoVO kvo = new KakaoVO();
        String sql;
        try{
            // dataSource로 부터 connection을 가져옴
            con = dataSource.getConnection();
            sql = "INSERT INTO KBUSERKakaoTest VALUES(?,?,?)";
            pstmt = con.prepareStatement(sql);
            System.out.println("DAO가입 처리중..");
            System.out.println(data.getID());
            System.out.println(data.getName());
            System.out.println(data.getPWD());
            pstmt.setString(1, data.getID());
            pstmt.setString(2, data.getName());
            pstmt.setString(3, data.getPWD());
            pstmt.executeUpdate();
//            rs = pstmt.executeQuery();
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
	
	

}


