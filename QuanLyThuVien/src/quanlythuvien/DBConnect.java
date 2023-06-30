/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author truon
 */
public class DBConnect {
    public static String url = "jdbc:sqlserver://DESKTOP-NVN3H18\\SQLEXPRESS01:1433;databaseName=QLThuVien; encrypt=true; trustServerCertificate=true; characterEncoding=UTF-8";    
    public static String user = "sa";
    public static String pass = "1234";

    public static Connection ConnectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Kết Nối Thất Bại" + e);
            return null;
        }
    }
}
 
