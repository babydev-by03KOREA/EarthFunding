package login;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO{
    DataSource dataSource;

    public UserDAO(){
        try{
            // Connection Pool을 찾는 과정
            Context context = new InitialContext(); // context 생
            dataSource = (DataSource)context.lookup("java:comp/env/dbcp_myoracle"); // context.xml에서 설정해준 Resource의 name부분
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public UserVO getUserVO(String ID, String PWD){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql;
        UserVO uv = new UserVO();
        sql = "SELECT * FROM KBUser WHERE ID = ? AND PWD = ?";
        try{
            // dataSource로 부터 connection을 가져옴
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ID);
            pstmt.setString(2, PWD);
            rs = pstmt.executeQuery();
            if (rs.next()){
                uv.setID(rs.getString("ID").trim());
                uv.setPWD(rs.getString("pwd").trim());
                uv.setNICKNAME(rs.getString(3).trim());
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
        return uv;
    }
    
}

