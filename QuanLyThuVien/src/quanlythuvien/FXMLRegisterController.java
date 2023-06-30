/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author truon
 */
public class FXMLRegisterController {
     @FXML
    private TextField register_username;
    @FXML
    private PasswordField register_password;
    @FXML
    private PasswordField register_repassword;
    @FXML
    private Button btnRegister;
    @FXML
    private ImageView btnExitRegister;
    
    Connection conn;
    ResultSet result;
    PreparedStatement prt;
    
    public String checkedNewAccount() throws SQLException{
        String sql = "select count(*) as KiemTra from dbo.[User] where Username=?";
        String stringResult = "";
        try{
            prt = conn.prepareStatement(sql);
            prt.setString(1,register_username.getText());
            result = prt.executeQuery();
            while(result.next()){
                if(result.getInt("KiemTra") > 0){
                    stringResult = "Tên tài khoản đã tồn tại !";
                }
                else if(register_username.getText().isEmpty()){
                    stringResult = "Vui lòng đăng ký tên tài khoản !";
                }
                else if(register_password.getText().isEmpty()){
                    stringResult = "Vui lòng đăng ký mật khẩu !";
                }
                else if(register_username.getText().contains(" ")){
                    stringResult = "Tên tài khoản không được chứa dấu cách !";
                }
                else if(register_password.getText().contains(" ")){
                    stringResult = "Mật khẩu không được chứa dấu cách !";
                }
                else if(!(register_repassword.getText().equals(register_password.getText()))){
                    stringResult = "Xác nhận mật khẩu chưa chính xác !";
                }
                else if(register_password.getText().length() < 6){
                    stringResult = "Mật khẩu cần chứa ít nhất 6 kí tự !";
                }
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return stringResult;
    }
    @FXML
    public void handleBtnRegister() throws SQLException  {
        String sql = "insert into dbo.[User] (Username,Password,ImageAvatar) values(?,?,?)";
        conn = DBConnect.ConnectDB();
        try(PreparedStatement prt = conn.prepareStatement(sql)){
            if(checkedNewAccount().isEmpty()){
                String imageDefaultPath = "D://Learning//Ki6//Java//img_avatar_default.png";
                java.nio.file.Path pathDefault = Paths.get(imageDefaultPath);
                byte[] imageDefaultData = Files.readAllBytes((java.nio.file.Path) pathDefault);
                
                prt.setString(1,register_username.getText());
                prt.setString(2,register_password.getText());
                prt.setBytes(3,imageDefaultData);
                
                prt.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notify message");
                alert.setHeaderText(null);
                alert.setContentText("Đăng ký thành công !");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notify message");
                alert.setHeaderText(null);
                alert.setContentText("Đăng ký thất bại : " + checkedNewAccount());
                alert.showAndWait();
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
             Logger.getLogger(FXMLRegisterController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    @FXML
    public void handleBtnExitRegister() throws IOException{
        btnExitRegister.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stageLogin = new Stage();
        Scene scene = new Scene(root);  
        scene.getStylesheets().add(getClass().getResource("LoginFormStyle.css").toExternalForm());
        Image icon = new Image("quanlythuvien/book.png");
        stageLogin.getIcons().add(icon);
        stageLogin.setTitle("Đăng nhập");
        stageLogin.setScene(scene);
        stageLogin.show();
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
