/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package quanlythuvien;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 *
 * @author truon
 */

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnLogin;
   
    Connection conn;
    PreparedStatement prt;
    ResultSet result;

    FXMLDashBoardController DBCtrl = new FXMLDashBoardController(); 
    
    @FXML
    void handleBtnGoRegister(MouseEvent event) {
        
    }

    @FXML
    void handleButtonClick(MouseEvent event) {
        String sql = "Select Username from dbo.[User] where Username = ? and Password = ?";
        conn = DBConnect.ConnectDB();
        try{    
             prt = conn.prepareStatement(sql);
                    prt.setString(1,username.getText());
                    prt.setString(2,password.getText());
                    
                    result = prt.executeQuery();
                    Alert alert;
                    if(username.getText().isEmpty() || password.getText().isEmpty()){
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Chưa nhập đủ thông tin tài khoản");
                        alert.showAndWait();
                    }
                    else{
                        
                        if(result.next()){
                            getDataUserName.username = username.getText();
                            alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Notify message");
                            alert.setHeaderText(null);
                            alert.setContentText("Đăng nhập thành công !");
                            alert.showAndWait();
                        
                            DBCtrl.getUserNameOnStream = username.getText();
                        
                            btnLogin.getScene().getWindow().hide();
                        
                            Parent root = FXMLLoader.load(getClass().getResource("FXMLDashBoard.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);  
                            scene.getStylesheets().add(getClass().getResource("fxmldashboard.css").toExternalForm());
                            Image icon = new Image("quanlythuvien/book.png");
                            stage.getIcons().add(icon);
                            stage.setTitle("Hệ thống quản lý thư viện");
                            stage.setScene(scene);
                            stage.show();
                        }
                        else {
                            alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Notify message");
                            alert.setHeaderText(null);
                            alert.setContentText("Tên tài khoản hoặc mật khẩu không chính xác !");
                            alert.showAndWait();
                        }
                    }
                }
                catch(SQLException e){
                    e.printStackTrace();
                } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void handleBtnGoRegisters() throws IOException{
        btnLogin.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLRegister.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        scene.getStylesheets().add(getClass().getResource("LoginFormStyle.css").toExternalForm());
        Image icon = new Image("quanlythuvien/book.png");
        stage.getIcons().add(icon);
        stage.setTitle("Đăng ký");
        stage.setScene(scene);
        stage.show();                
        System.out.println("alo");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
