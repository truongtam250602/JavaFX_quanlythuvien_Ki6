/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package quanlythuvien;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import quanlythuvien.DBConnect;
/**
 *
 * @author truon
 */
public class QuanLyThuVien extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent rootLogin = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene sceneLogin = new Scene(rootLogin);
        sceneLogin.getStylesheets().add(getClass().getResource("LoginFormStyle.css").toExternalForm());
        
        
        stage.setScene(sceneLogin);

        Image icon = new Image("quanlythuvien/book.png");
        stage.getIcons().add(icon);
        stage.setTitle("Đăng nhập");
        stage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        launch(args);
       
    }
    
}
