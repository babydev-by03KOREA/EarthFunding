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


	public void KakaoJoin(KakaoVO data) {
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO KBUSERKakaoTest VALUES(?,?,?)";
        try{
            // dataSource로 부터 connection을 가져옴
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,data.getID());
            pstmt.setString(2,data.getName());
            pstmt.setString(3,data.getPWD());
//            pstmt.setString(4,data.getGender());
//            pstmt.setString(5,data.getAddress());
//            pstmt.setString(6,data.getPhone());
//            pstmt.setString(7,data.getBirth());
//            pstmt.setString(8,data.getEmail());
            pstmt.executeUpdate();
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
	
	public KakaoVO getKakaoLogin(String ID){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql;
        KakaoVO kuv = new KakaoVO();
        sql = "SELECT * FROM KBUserKakaoTest WHERE ID = ?";
        try{
            // dataSource로 부터 connection을 가져옴
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ID);
            rs = pstmt.executeQuery();
            if (rs.next()){
            	kuv.setID(rs.getString("ID").trim());
                kuv.setName(rs.getString("Name").trim());
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
        return kuv;
    }

}


