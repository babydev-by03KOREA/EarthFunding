package DataBase;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class DBOpen {
    public Connection con = null;
    public PreparedStatement pstmt = null;
    public ResultSet rs = null;

    public DBOpen() {
        try {
            Context initCtx = new InitialContext();
            Context ctx = (Context)initCtx.lookup("java:comp/env");
            DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");
            con = source.getConnection();
            System.out.println("Connection Pull Succesful");
        }catch(Exception e) {
            System.out.println("You're ConnectionPull Commands was denied for "+e);
        }finally {
            try {
                if (con != null) con.close();
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
