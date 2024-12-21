package database;

import java.sql.*;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/hw_02_part_02"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static boolean isValidUserById(Integer id, String password) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean found = false;
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM users WHERE id=? AND password=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                found = true;
            }
        } catch (SQLException e) {
            
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            }
        }
        return found;
    }
    
    public static boolean isValidUserByUsername(String username, String password) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean found = false;

        try {
            conn = getConnection();
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                found = true;
            }
        } catch (SQLException e) {

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {

            }
        }
        return found;
    }
    
    public static int getTotalUsersCount() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        
        try {
            conn = getConnection();
            String sql = "SELECT COUNT(*) AS total FROM users";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                // Handle or log the exception
            }
        }
        return count;
    }
}
