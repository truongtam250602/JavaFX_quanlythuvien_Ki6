/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package quanlythuvien;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author truon
 */
public class FXMLChangedPasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PasswordField txtOldPassword;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private PasswordField txtConfirmNewPassword;

    @FXML
    private Button btnConfirmChangedPassWord;
    
    Connection conn;
    PreparedStatement prt;
    ResultSet rst;
    
    @FXML
    public void btnConfirmClicked(MouseEvent event) {
            conn = DBConnect.ConnectDB();
            String sqlCheck = "select Password,Username from dbo.[User] where Username = ?;";
            String sqlConfirm = "update dbo.[User] set Password = ? where Username = ?";
            try{
                if(txtOldPassword.getText().isEmpty() || txtNewPassword.getText().isEmpty() || txtConfirmNewPassword.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Chưa nhập đủ thông tin !");
                    alert.showAndWait();
                }else if(!txtNewPassword.getText().equals(txtConfirmNewPassword.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Xác nhận mật khẩu mới không chính xác !");
                    alert.showAndWait();
                }else if(txtNewPassword.getText().length() < 6){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Mật khẩu cần ít nhất 6 kí tự !");
                    alert.showAndWait();
                }
                else{
                    prt = conn.prepareStatement(sqlCheck);
                    prt.setString(1, getDataUserName.username);
                    rst = prt.executeQuery();
                    if(rst.next()){
                        if(rst.getString(1).equals(txtOldPassword.getText())){
                            prt = conn.prepareCall(sqlConfirm);
                            prt.setString(1,txtNewPassword.getText());
                            prt.setString(2,getDataUserName.username);
                            prt.executeUpdate();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Mật khẩu đã được thay đổi thành công !");
                            alert.showAndWait();
                            
                            btnConfirmChangedPassWord.getScene().getWindow().hide();
                        
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
                        else{
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Nhập sai mật khẩu cũ !");
                            alert.showAndWait();
                        }
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     
}
