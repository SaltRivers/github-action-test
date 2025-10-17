package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlInjectionExample {

    public void doLogin(String user, String pass) {
        // 使用参数化查询与 try-with-resources 防止 SQL 注入并确保资源正确关闭
        String url = "jdbc:mysql://localhost/test?user=myuser&password=mypass";
        String sql = "SELECT * FROM users WHERE user = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            try (ResultSet rs = pstmt.executeQuery()) {
                // 处理结果集（示例中省略具体逻辑）
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
//    public void doLogin(String user, String pass) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=myuser&password=mypass");
//
//            // Safe SQL query using prepared statements.
//            String query = "SELECT * FROM users WHERE user = ? AND password = ?";
//
//            pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, user);
//            pstmt.setString(2, pass);
//
//            rs = pstmt.executeQuery();
//
//            // ...
//
//        } catch (SQLException se) {
//            // Handle errors for JDBC
//            se.printStackTrace();
//        } finally {
//            // Finally block used to close resources
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//    }
}
