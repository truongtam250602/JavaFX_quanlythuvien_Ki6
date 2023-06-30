/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package quanlythuvien;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author truon
 */
public class FXMLAddBookController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtMaSach;
    @FXML
    private TextField txtTenSach;
    @FXML
    private TextField txtTacGia;
    @FXML
    private DatePicker txtNamXB;
    @FXML
    private TextField txtNhaXB;
    @FXML
    private TextField txtSoLuong;
    @FXML
    private Button btnLuu;
    @FXML
    private Button btnHuy;
    @FXML
    private ComboBox cbbTheLoai;
    
    ObservableList<String> listTheLoai = FXCollections.observableArrayList();
    
    private Connection conn;
    private PreparedStatement prt;
    private ResultSet result;
    private String sql;
    
    private void clean() {
        txtMaSach.setText(null);
        txtTenSach.setText(null);
        txtTacGia.setText(null);
        txtNamXB.setValue(null);
        txtNhaXB.setText(null);
        txtSoLuong.setText(null);
        cbbTheLoai.setValue(null);
        txtMaSach.requestFocus();
    }
    
    private boolean isValidNumber(String text) {
        try {
            NumberFormat.getInstance().parse(text);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public void loadDataComboBox(){
        sql =  "select * from GenresBook;";
        conn = DBConnect.ConnectDB();
        try {
            prt = conn.prepareStatement(sql);
            ResultSet rst = prt.executeQuery();
            while(rst.next()){
                listTheLoai.add(rst.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAddBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbbTheLoai.setItems(listTheLoai);
    }
    
    @FXML
    public void selectedTheLoai(){
        
    }
    
    @FXML
    public void btnLuu_Click(){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        sql = "insert into dbo.[Books] (ID,BookName,AuthorName,YearOfPublication,Publisher,Quantity,DateOfEntry,Genres) Values (?,?,?,?,?,?,?,?)";
        conn = DBConnect.ConnectDB();
        String dateString = String.valueOf(txtNamXB.getValue());
        try{
            if(txtMaSach.getText().isEmpty()
                    || txtTenSach.getText().isEmpty()
                    || txtTacGia.getText().isEmpty()
                    || dateString.isEmpty()
                    || txtNhaXB.getText().isEmpty()
                    || txtSoLuong.getText().isEmpty()
                    || cbbTheLoai.getSelectionModel().getSelectedItem().toString().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Chưa nhập đủ thông tin ");
                    alert.showAndWait();
            }
            else if(!isValidNumber(txtSoLuong.getText())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Định dạng số lượng không đúng ! ");
                alert.showAndWait();
            }
            else{
                String check = "select ID from dbo.[Books] where ID = '" + txtMaSach.getText() + "'";
                prt = conn.prepareStatement(check);
                result = prt.executeQuery();
                if(result.next()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Mã sách: "+txtMaSach.getText()+" đã tồn tại !");
                    alert.showAndWait();
                }
                else{
                    prt = conn.prepareStatement(sql);
                    prt.setString(1,txtMaSach.getText());
                    prt.setString(2,txtTenSach.getText());
                    prt.setString(3,txtTacGia.getText());
                    prt.setString(4,dateString);
                    prt.setString(5,txtNhaXB.getText());
                    prt.setString(6,txtSoLuong.getText());
                    prt.setString(7,String.valueOf(sqlDate));
                    prt.setString(8,cbbTheLoai.getSelectionModel().getSelectedItem().toString());
                    prt.execute();
                    
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Sách đã được thêm !");
                    alert.showAndWait();
                    
                    clean();
                }
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void btnHuy_Click(){
        btnHuy.getScene().getWindow().hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDataComboBox();
    }    
    
}
