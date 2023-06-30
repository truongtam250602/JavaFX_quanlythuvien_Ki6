/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package quanlythuvien;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.octicons.OctIcon;
import de.jensd.fx.glyphs.octicons.OctIconView;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author truon
 */
public class FXMLDashBoardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    BorderPane wrapperView;
    
    @FXML
    BorderPane wrapperContain;
//    Variable for DashBoard
    
    @FXML
    HBox quanlythuvien;
    @FXML
    HBox quanlysach;
    @FXML
    HBox quanlythanhvien;
    @FXML
    HBox quanlymuontra;
    @FXML
    HBox thongtinphanmem;
    @FXML
    RadioButton radioBtnQuanLyThuVien;
    @FXML
    RadioButton radioBtnQuanLySach;
    @FXML
    RadioButton radioBtnQuanLyThanhVien;
    @FXML
    RadioButton radioBtnQuanLyMuonTra;
    @FXML
    RadioButton radioBtnThongTinPhanMem;
    @FXML
    ToggleGroup toggleGroup;
    
//    Variable for HeaderView
    String getUserNameOnStream;
    
    @FXML
    FontAwesomeIconView iconView;
    @FXML
    Label labelView;
    @FXML
    Label userNameOnStream;
    @FXML
    Circle circle;
    @FXML
    StackPane wrapperAvatar;
    @FXML
    VBox listChangedUser;
    @FXML
    FontAwesomeIconView angleDown;    
    @FXML
    private Button btnLogOffAcc;
    @FXML
    private Button btnChangedPassWordAcc;
    @FXML
    private Button btnChooseFileImg;
    
//    Variable for MainContrainView
    @FXML 
    BorderPane tongQuanView;
    @FXML
    BorderPane quanLySachView;   
    @FXML
    BorderPane quanLyThanhVienView;
    @FXML
    BorderPane quanLyMuonTraView;
    
    //variable cho group chart
    
    @FXML
    private HBox itemTongQuanChung;

    @FXML
    private HBox itemTongQuanDuLieuSach;

    @FXML
    private HBox itemTongQuanDuLieuThanhVien;

    @FXML
    private HBox itemTongQuanDuLieuPhieuMuon;
    
    private AnchorPane selectedAnchorPane;
    
    @FXML
    private AnchorPane viewChartTongQuanChung;
    @FXML
    private AnchorPane viewChartTongQuanDuLieuSach;
    @FXML
    private AnchorPane viewChartTongQuanDuLieuThanhVien;
    @FXML
    private AnchorPane viewChartTongQuanDuLieuPhieuMuon;
    @FXML
    private TextField txtSearchBook;
    
    // variable cho tong quan chung
    @FXML
    LineChart chartTongQuanChung;
    @FXML
    BarChart barChartTongQuanSach;
    @FXML
    PieChart pieChartTongQuanSach;
    @FXML
    AreaChart areaChartTongQuanThanhVien;
    @FXML
    PieChart pieChartTongQuanThanhVien;
    @FXML
    LineChart lineChartTongQuanPhieuMuon;
    @FXML
    PieChart pieChartTongQuanPhieuMuon;
    @FXML
    ComboBox cbbNam;
    
//    Variable for danhmucsachContain
   
    @FXML
    public TableView<Book> tableListBookView;
    @FXML
    private TableColumn<Book, String> maSachColumn;
    @FXML
    private TableColumn<Book, String> tenSachColumn;
    @FXML
    private TableColumn<Book, String> tacGiaColumn;
    @FXML
    private TableColumn<Book, String> namXBColumn;
    @FXML
    private TableColumn<Book, String> nhaXBColumn;
    @FXML
    private TableColumn<Book, String> soLuongColumn;
    @FXML
    private TableColumn<Book, String> theLoaiColumn;
    @FXML
    private TableColumn<Book, String> ngayNhapColumn;
    @FXML
    private TableColumn<Book, String> tuyChonColumn;
    
//    Variable for add book
    
    @FXML
    private HBox btnAddBook;
    @FXML
    private HBox btnRefresh;
    
    public ObservableList<Book> listDataBook = FXCollections.observableArrayList();
    
//     Variable for edit book
    @FXML
    private VBox viewEditBook;
    @FXML
    private TextField txtMaSach_Edit;

    @FXML
    private TextField txtTenSach_Edit;

    @FXML
    private TextField txtTacGia_Edit;

    @FXML
    private DatePicker txtNamXB_Edit;

    @FXML
    private TextField txtNhaXB_Edit;

    @FXML
    private TextField txtSoLuong_Edit;

    @FXML
    private Button btnSua_Edit;

    @FXML
    private Button btnHuy_Edit;
    
//    Variable for Member List
    
    @FXML
    private TableView<Member> tableListMemberView;
    
    @FXML
    private TableColumn<Member, String> maThanhVienColumn;

    @FXML
    private TableColumn<Member, String> tenThanhVienColumn;

    @FXML
    private TableColumn<Member, String> daichiColumn;

    @FXML
    private TableColumn<Member, String> sdtColumn;

    @FXML
    private TableColumn<Member, String> emailColumn;

    @FXML
    private TableColumn<Member, String> sachDaMuonColumn;

    @FXML
    private TableColumn<Member, String> sachChuaTraColumn;

    @FXML
    private TableColumn<Member, String> theThanhVienColumn;
    
    @FXML
    private TableColumn<Member, String> tuyChonMember;
    
    public ObservableList<Member> listDataMember = FXCollections.observableArrayList();
    
//  Variable for search member
    @FXML
    private TextField txtSearchMember;
    
    //Variable for add member
    
    @FXML
    private VBox viewAddMember;

    @FXML
    private TextField txtMaThanhVien_Add;

    @FXML
    private TextField txtTenThanhVien_Add;

    @FXML
    private TextField txtDiaChi_Add;

    @FXML
    private TextField txtSoDienThoai_Add;

    @FXML
    private TextField txtEmail_Add;
    
    @FXML
    private TextField txtTheThanhVien_Add;

    @FXML
    private Button btnSua_AddMember;

    @FXML
    private Button btnHuy_AddMember;
    
    //Variable for edit member
    @FXML
    private VBox viewEditMember;

    @FXML
    private TextField txtMaThanhVien_Edit;

    @FXML
    private TextField txtTenThanhVien_Edit;

    @FXML
    private TextField txtDiaChi_Edit;

    @FXML
    private TextField txtSoDienThoai_Edit;

    @FXML
    private TextField txtEmail_Edit;

    @FXML
    private TextField txtTheThanhVien_Edit;

    @FXML
    private Button btnSua_EditMember;

    @FXML
    private Button btnHuy_EditMember;
    
    //variable for list ticket
    @FXML
    private TableView<Ticket> tableListTicketView;

    @FXML
    private TableColumn<Ticket, String> maPhieuColumn;

    @FXML
    private TableColumn<Ticket, String> tenNguoiMuonColumn;

    @FXML
    private TableColumn<Ticket, String> maNguoiMuonColumn;

    @FXML
    private TableColumn<Ticket, String> nguoiChoMuonColumn;

    @FXML
    private TableColumn<Ticket, String> ngayMuonColumn;

    @FXML
    private TableColumn<Ticket, String> soLuongSachColumn;

    @FXML
    private TableColumn<Ticket, String> tienPhatColumn;

    @FXML
    private TableColumn<Ticket, String> tuyChonTicketColumn;
    
    public ObservableList<Ticket> listDataTicket = FXCollections.observableArrayList();
    
    @FXML 
    private VBox viewAddTicket;

    @FXML
    private TextField txtSearchTicket;

    @FXML
    private HBox btnAddTicket;

    @FXML
    private HBox btnRefreshTicketList;
    
    @FXML
    private TextField txtMaPhieu;

    @FXML
    private TextField txtHoTenNguoiMuon;
   
    @FXML
    private TextField txtSoLuongSach;

    @FXML
    private Button btnLuu_AddTicket;

    @FXML
    private Button btnHuy_AddTicket;
    
    //variable for edit ticket
    @FXML
    private VBox viewEditTicket;

    @FXML
    private TextField txtMaPhieu_Edit;

    @FXML
    private TextField txtHoTenNguoiMuon_Edit;

    @FXML
    private TextField txtSoLuongSach_Edit;

    @FXML
    private Button btnLuu_EditTicket;

    @FXML
    private Button btnHuy_EditTicket;
        
    private double mouseX, mouseY;
    
//    Variable for getDatabase
    Connection conn;
    ResultSet result;
    PreparedStatement prt;
    Statement statement;
    
    @FXML
    AnchorPane anchorpane;
    private boolean isLabelVisible = false;
    
    public void onDragMoveView(VBox view){
        view.setOnMousePressed(event ->{
           mouseX = event.getSceneX();
           mouseY = event.getSceneY();
        });
        
        view.setOnMouseDragged(event ->{
            double deltaX = event.getSceneX() - mouseX;
            double deltaY = event.getSceneY() - mouseY;
            
            view.setLayoutX(view.getLayoutX() + deltaX);
            view.setLayoutY(view.getLayoutY() + deltaY);
            
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });
    }
    
    public void setUserName_AvatarOnStream() throws SQLException, IOException{
        // lay ten va anh dai dien
            userNameOnStream.setText(getDataUserName.username);
        // insert anh hoac update anh dai dien
         
        btnChooseFileImg.setOnAction(e ->{
            try {
                FileChooser fileImgChooser = new FileChooser();
                java.io.File selectFileImg = fileImgChooser.showOpenDialog(null);
                if(selectFileImg != null){
                    String imagePath = selectFileImg.getAbsolutePath();
                    java.nio.file.Path path = Paths.get(imagePath);
                    byte[] imageData = Files.readAllBytes((java.nio.file.Path) path);
                    
                    String updateSql = "update dbo.[User] set ImageAvatar = ? where Username = ? ;"; 
                    prt = conn.prepareStatement(updateSql);
                    prt.setBytes(1,imageData);
                    prt.setString(2,getDataUserName.username);
                    prt.executeUpdate();
                    
                    String query = "Select ImageAvatar from dbo.[User] where Username = '" + getDataUserName.username + "';";
                    prt = conn.prepareStatement(query);
                    ResultSet rst = prt.executeQuery();
                    if(rst.next()){
                        byte[] imageDataLoad = rst.getBytes("ImageAvatar");
                        InputStream inputStream = new ByteArrayInputStream(imageDataLoad);
                        Image image = new Image(inputStream);
                        circle.setFill(new ImagePattern(image));
                        listChangedUser.setVisible(false);
                    }
                }
                else{
                    // lay ten va anh dai dien truong hop khong choose
                    userNameOnStream.setText(getDataUserName.username);
                    String query = "Select ImageAvatar from dbo.[User] where Username = N'" + getDataUserName.username + "';";
                    prt = conn.prepareStatement(query);
                    ResultSet rst = prt.executeQuery();
                    if(rst.next()){
                        byte[] imageData = rst.getBytes("ImageAvatar");
                        InputStream inputStream = new ByteArrayInputStream(imageData);
                        Image image = new Image(inputStream);
                        circle.setFill(new ImagePattern(image));
                        listChangedUser.setVisible(false);
                    }
                }
            } 
            catch (IOException ex) {
                Logger.getLogger(FXMLDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // load avatar
        userNameOnStream.setText(getDataUserName.username);
        String query = "Select ImageAvatar from dbo.[User] where Username = '" + getDataUserName.username + "';";
        prt = conn.prepareStatement(query);
        ResultSet rst = prt.executeQuery();
        if(rst.next()){
            byte[] imageData = rst.getBytes("ImageAvatar");
            InputStream inputStream = new ByteArrayInputStream(imageData);
            Image image = new Image(inputStream);
            circle.setFill(new ImagePattern(image));
        }
    }
    
    public void selectedItemDashboard(){
        toggleGroup = new ToggleGroup();

        radioBtnQuanLyThuVien.setToggleGroup(toggleGroup);
        radioBtnQuanLySach.setToggleGroup(toggleGroup);
        radioBtnQuanLyThanhVien.setToggleGroup(toggleGroup);
        radioBtnQuanLyMuonTra.setToggleGroup(toggleGroup);
        radioBtnThongTinPhanMem.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            quanlythuvien.getStyleClass().remove("selected");
            quanlysach.getStyleClass().remove("selected");
            quanlythanhvien.getStyleClass().remove("selected");
            quanlymuontra.getStyleClass().remove("selected");
            thongtinphanmem.getStyleClass().remove("selected");

            HBox selectedHBox = (HBox) ((RadioButton) newValue).getParent();
            if (newValue != null) {
                selectedHBox.getStyleClass().add("selected");
                labelView.setText(((RadioButton) newValue).getText());
                for(Node node : selectedHBox.getChildren()){
                    if(node instanceof FontAwesomeIconView){
                        iconView.setGlyphName((String) ((FontAwesomeIconView) node).getGlyphName());
                    }
                }
                switch(((RadioButton) newValue).getText()){
                    case "Quản lý danh mục sách":
                        quanLySachView.setVisible(true);
                        quanLyThanhVienView.setVisible(false);
                        quanLyMuonTraView.setVisible(false);
                        tongQuanView.setVisible(false);
                        break;
                    case "Quản lý thành viên":
                        quanLyThanhVienView.setVisible(true);
                        quanLySachView.setVisible(false);
                        quanLyMuonTraView.setVisible(false);
                        tongQuanView.setVisible(false);
                        break;
                    case "Quản lý mượn/trả sách":
                        quanLySachView.setVisible(false);
                        quanLyThanhVienView.setVisible(false);
                        quanLyMuonTraView.setVisible(true);
                        tongQuanView.setVisible(false);
                        break;
                    case "Tổng quan thư viện":
                        tongQuanView.setVisible(true);
                        quanLySachView.setVisible(false);
                        quanLyThanhVienView.setVisible(false);
                        quanLyMuonTraView.setVisible(false);
                        break;    
                }
            }
        });
    }
    
    private void toggleLabelVisibility() {
        if (isLabelVisible == false) {
            isLabelVisible = true;
            listChangedUser.setVisible(isLabelVisible);
        } else {
            isLabelVisible = false;
            listChangedUser.setVisible(isLabelVisible);
        }
    }
   
    @FXML
    public void btnChangedPassWord_Click() throws IOException{
        btnChangedPassWordAcc.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLChangedPassword.fxml"));
        Stage stageLogin = new Stage();
        Scene scene = new Scene(root);  
        scene.getStylesheets().add(getClass().getResource("LoginFormStyle.css").toExternalForm());
        Image icon = new Image("quanlythuvien/book.png");
        stageLogin.getIcons().add(icon);
        stageLogin.setTitle("Đổi mật khẩu");
        stageLogin.setScene(scene);
        stageLogin.show();
    }
    
    @FXML
    public void btnLogOffUser_Click() throws IOException{
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cofirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Đăng xuất tài khoản này ?");
        Optional<ButtonType> option = alert.showAndWait();
                            
        if(option.get().equals(ButtonType.OK)){
            btnLogOffAcc.getScene().getWindow().hide();
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
    }
    
    private boolean isValidNumber(String text) {
        try {
            NumberFormat.getInstance().parse(text);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    private List<Integer> getYears() {
        List<Integer> years = new ArrayList<>();

        // Lấy năm hiện tại và các năm từ 1900 đến năm hiện tại
        int currentYear = Year.now().getValue();
        for (int year = 2000; year <= currentYear; year++) {
            years.add(year);
        }

        return years;
    }
    
    public void loadCbbNam(){
        List<Integer> years = getYears();
        cbbNam.getItems().addAll(years);
        cbbNam.setValue(null);
    }
    
    public void clickItemChart(){
        viewChartTongQuanChung.setVisible(true);
        itemTongQuanChung.setOnMouseClicked(event -> hanldeItemChartClick(viewChartTongQuanChung));
        itemTongQuanDuLieuSach.setOnMouseClicked(event -> hanldeItemChartClick(viewChartTongQuanDuLieuSach));
        itemTongQuanDuLieuThanhVien.setOnMouseClicked(event -> hanldeItemChartClick(viewChartTongQuanDuLieuThanhVien));
        itemTongQuanDuLieuPhieuMuon.setOnMouseClicked(event -> hanldeItemChartClick(viewChartTongQuanDuLieuPhieuMuon));
    }

    public void hanldeItemChartClick(AnchorPane clickedAnchorPane){
        if(clickedAnchorPane == viewChartTongQuanChung){
            clickedAnchorPane.setVisible(true);
            viewChartTongQuanDuLieuSach.setVisible(false);
            viewChartTongQuanDuLieuThanhVien.setVisible(false);
            viewChartTongQuanDuLieuPhieuMuon.setVisible(false);
        }
        else if(clickedAnchorPane == viewChartTongQuanDuLieuSach){
            clickedAnchorPane.setVisible(true);
            viewChartTongQuanChung.setVisible(false);
            viewChartTongQuanDuLieuThanhVien.setVisible(false);
            viewChartTongQuanDuLieuPhieuMuon.setVisible(false);
        }
        else if(clickedAnchorPane == viewChartTongQuanDuLieuThanhVien){
            clickedAnchorPane.setVisible(true);
            viewChartTongQuanChung.setVisible(false);
            viewChartTongQuanDuLieuSach.setVisible(false);
            viewChartTongQuanDuLieuPhieuMuon.setVisible(false);
        }
        else if(clickedAnchorPane == viewChartTongQuanDuLieuPhieuMuon){
            clickedAnchorPane.setVisible(true);
            viewChartTongQuanChung.setVisible(false);
            viewChartTongQuanDuLieuSach.setVisible(false);
            viewChartTongQuanDuLieuThanhVien.setVisible(false);
        }
    }
    
    
    public void loadChartTongQuanChung(){
        cbbNam.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            chartTongQuanChung.getData().clear();
            
            conn =  DBConnect.ConnectDB();
            String sqlBook = "select MONTH(DateOfEntry),sum(Quantity) from Books where YEAR(DateOfEntry) = " + newValue + " group by MONTH(DateOfEntry);";
            String sqlMember = "select MONTH(DateOFRegister), count(ID) from Members where YEAR(DateOFRegister) = " + newValue + " group by MONTH(DateOFRegister);";
            String sqlTicket = "select MONTH(DateOfBorrowing), count(IDTickKet) from Tickets where YEAR(DateOfBorrowing) = " + newValue + " group by MONTH(DateOfBorrowing);";
            try{
                XYChart.Series chartBook = new XYChart.Series();
                chartBook.setName("Số sách");
                prt = conn.prepareStatement(sqlBook);
                result = prt.executeQuery();
                while(result.next()){
                    String thang = "Tháng ";
                    chartBook.getData().add(new XYChart.Data(thang+result.getString(1),result.getInt(2)));
                }
                chartTongQuanChung.getData().add(chartBook);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            try{
                XYChart.Series chartMember = new XYChart.Series();
                chartMember.setName("Số thành viên");
                prt = conn.prepareStatement(sqlMember);
                result = prt.executeQuery();
                while(result.next()){
                    String thang = "Tháng ";
                    chartMember.getData().add(new XYChart.Data(thang+result.getString(1),result.getInt(2)));
                }
                chartTongQuanChung.getData().add(chartMember);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            try{
                XYChart.Series chartTicket = new XYChart.Series();
                chartTicket.setName("Số phiếu mượn");
                prt = conn.prepareStatement(sqlTicket);
                result = prt.executeQuery();
                while(result.next()){
                    String thang = "Tháng ";
                    chartTicket.getData().add(new XYChart.Data(thang+result.getString(1),result.getInt(2)));
                }
                chartTongQuanChung.getData().add(chartTicket);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }
    
    //chart cho tong quan du lieu sach
    ObservableList<PieChart.Data> pieChartDataBook = FXCollections.observableArrayList();
    
    public void loadChartTongQuanSach(){
        cbbNam.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            barChartTongQuanSach.getData().clear();
            pieChartTongQuanSach.getData().clear();
            pieChartDataBook.clear();
            
            conn =  DBConnect.ConnectDB();
            String sqlBookQuantity = "select MONTH(DateOfEntry), sum(Quantity) from Books where YEAR(DateOfEntry) = " + newValue + " group by MONTH(DateOfEntry);";
            try{
                XYChart.Series chartBook = new XYChart.Series();
                chartBook.setName("Số sách");
                prt = conn.prepareStatement(sqlBookQuantity);
                result = prt.executeQuery();
                while(result.next()){
                    String thang = "Tháng ";
                    chartBook.getData().add(new XYChart.Data(thang+result.getString(1),result.getInt(2)));
                }
                barChartTongQuanSach.getData().add(chartBook);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            String sqlBookGenres = "select Genres, count(*) from Books where YEAR(DateOfEntry) = " + newValue + " group by Genres;";
            try{
                prt = conn.prepareStatement(sqlBookGenres);
                result = prt.executeQuery();
                while(result.next()){
                    pieChartDataBook.add(new PieChart.Data(result.getString(1), result.getInt(2)));
                }
                pieChartTongQuanSach.getData().addAll(pieChartDataBook);
                for(PieChart.Data data : pieChartTongQuanSach.getData()){
                    double percentrage = (data.getPieValue() / pieChartTongQuanSach.getData().stream().mapToDouble(PieChart.Data::getPieValue).sum()) * 100;
                    data.nameProperty().bind(Bindings.concat(data.getName(),": ", String.format("%.2f%%", percentrage)));
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }
    
    //chart cho tong quan du lieu sach
    ObservableList<PieChart.Data> pieChartDataMember = FXCollections.observableArrayList();
    
    public void loadChartTongQuanThanhVien(){
        cbbNam.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            areaChartTongQuanThanhVien.getData().clear();
            pieChartTongQuanThanhVien.getData().clear();
            pieChartDataMember.clear();
            
            conn =  DBConnect.ConnectDB();
            String sqlMemberCount = "select MONTH(DateOFRegister), count(ID) from Members where YEAR(DateOFRegister) = " + newValue + " group by MONTH(DateOFRegister);";
            try{
                XYChart.Series chartMember = new XYChart.Series();
                chartMember.setName("Số thành viên");
                prt = conn.prepareStatement(sqlMemberCount);
                result = prt.executeQuery();
                while(result.next()){
                    String thang = "Tháng ";
                    chartMember.getData().add(new XYChart.Data(thang+result.getString(1),result.getInt(2)));
                }
                areaChartTongQuanThanhVien.getData().add(chartMember);
            }catch(Exception ex){
                ex.printStackTrace();
            }
//            String sqlMemberFine = "select Genres, count(*) from Books where YEAR(DateOfEntry) = " + newValue + " group by Genres;";
//            try{
//                prt = conn.prepareStatement(sqlMemberFine);
//                result = prt.executeQuery();
//                while(result.next()){
//                    pieChartDataMember.add(new PieChart.Data(result.getString(1), result.getInt(2)));
//                }
//                pieChartTongQuanSach.getData().addAll(pieChartDataBook);
//                for(PieChart.Data data : pieChartTongQuanSach.getData()){
//                    double percentrage = (data.getPieValue() / pieChartTongQuanSach.getData().stream().mapToDouble(PieChart.Data::getPieValue).sum()) * 100;
//                    System.out.println(String.format("%.2f%%", percentrage));
//                    data.nameProperty().bind(Bindings.concat(data.getName(),": ", String.format("%.2f%%", percentrage)));
//                }
//            }catch(Exception ex){
//                ex.printStackTrace();
//            }
        });
    }
    
    //chart cho tong quan du lieu sach
    ObservableList<PieChart.Data> pieChartDataTicket = FXCollections.observableArrayList();
    
    public void loadChartTongQuanPhieuMuon(){
        cbbNam.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            lineChartTongQuanPhieuMuon.getData().clear();
            pieChartTongQuanPhieuMuon.getData().clear();
            pieChartDataTicket.clear();
            
            conn =  DBConnect.ConnectDB();
            String sqlTicketCount = "select MONTH(DateOfBorrowing), count(IDTickKet) from Tickets where YEAR(DateOfBorrowing) = " + newValue + " group by MONTH(DateOfBorrowing);";
            try{
                XYChart.Series chartTicket = new XYChart.Series();
                chartTicket.setName("Số phiếu mượn");
                prt = conn.prepareStatement(sqlTicketCount);
                result = prt.executeQuery();
                while(result.next()){
                    String thang = "Tháng ";
                    chartTicket.getData().add(new XYChart.Data(thang+result.getString(1),result.getInt(2)));
                }
                lineChartTongQuanPhieuMuon.getData().add(chartTicket);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            String sqlTicket = "select N'Số phiếu bị phạt' as Label, count(*) as Total from Tickets where Fine > 0 and YEAR(DateOfBorrowing) = " + newValue +
                    "UNION ALL "
                    + "select N'Số phiếu không phạt' as Label, count(*) as Total from Tickets where Fine = 0 and YEAR(DateOfBorrowing) = " + newValue +";";
            try{
                prt = conn.prepareStatement(sqlTicket);
                result = prt.executeQuery();
                while(result.next()){
                    pieChartDataTicket.add(new PieChart.Data(result.getString(1), result.getInt(2)));
                }
                pieChartTongQuanPhieuMuon.getData().addAll(pieChartDataTicket);
                for(PieChart.Data data : pieChartTongQuanPhieuMuon.getData()){
                    double percentrage = (data.getPieValue() / pieChartTongQuanPhieuMuon.getData().stream().mapToDouble(PieChart.Data::getPieValue).sum()) * 100;
                    data.nameProperty().bind(Bindings.concat(data.getName(),": ", String.format("%.2f%%", percentrage)));
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }
    
    @FXML
    public void btnAddBook_Click() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAddBook.fxml"));
        Stage stageAddBook = new Stage();
        Scene scene = new Scene(root);  
        scene.getStylesheets().add(getClass().getResource("fxmladdbook.css").toExternalForm());
        Image icon = new Image("quanlythuvien/book.png");
        stageAddBook.getIcons().add(icon);
        stageAddBook.setTitle("Thêm sách");
        stageAddBook.setScene(scene);
        stageAddBook.show();
        refreshTable();
    }
    
    @FXML
    public void btnRefreshListBook_Click(){
        refreshTable();
    }
    
    public void refreshTable(){
        try {
            listDataBook.clear();
            
            String sql = "select ID,BookName,AuthorName,YEAR(YearOfPublication) AS Year,Publisher,Genres,Quantity,CONVERT(varchar, DateOfEntry, 103) AS FormattedDate from Books";
            prt = conn.prepareStatement(sql);
            result = prt.executeQuery();
            
            while (result.next()){
                listDataBook.add(new Book(result.getString(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getInt(7),result.getString(8),result.getString(6)));
            }
            tableListBookView.setItems(listDataBook);
            loadChartTongQuanChung();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void loadDataBook(){
        conn = DBConnect.ConnectDB();
        refreshTable();
        
        maSachColumn.setCellValueFactory(new PropertyValueFactory<>("maSach"));
        tenSachColumn.setCellValueFactory(new PropertyValueFactory<>("tenSach"));
        tacGiaColumn.setCellValueFactory(new PropertyValueFactory<>("tacGia"));
        namXBColumn.setCellValueFactory(new PropertyValueFactory<>("namXB"));
        nhaXBColumn.setCellValueFactory(new PropertyValueFactory<>("nhaXB"));
        soLuongColumn.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        ngayNhapColumn.setCellValueFactory(new PropertyValueFactory<>("ngayNhap"));
        theLoaiColumn.setCellValueFactory(new PropertyValueFactory<>("theLoai"));
        
        Callback<TableColumn<Book, String>,TableCell<Book, String>> cellFoctory = (TableColumn<Book, String> param) -> {
            
            final TableCell<Book, String> cell = new TableCell<Book, String>() {
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else {

                        OctIconView deleteIcon = new OctIconView(OctIcon.TRASHCAN);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill: #FB539B;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill: #4adaec;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event)->{
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText("Bạn có muốn tiếp tục xóa ?");
                            alert.setContentText("Bấm OK để tiếp tục hoặc Cancel để thoát.");

                            // Thiết lập các nút hiển thị trong cửa sổ thông báo
                            ButtonType buttonTypeOK = new ButtonType("OK");
                            ButtonType buttonTypeCancel = new ButtonType("Cancel");

                            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

                            // Hiển thị cửa sổ thông báo và chờ người dùng chọn
                            alert.showAndWait().ifPresent(response -> {
                                if (response == buttonTypeOK) {
                                    try {
                                
                                        Book book = tableListBookView.getSelectionModel().getSelectedItem();
                                        String query = "DELETE FROM dbo.[Books] WHERE ID  =" + "'" + book.getMaSach() + "'";
                                        conn = DBConnect.ConnectDB();
                                        prt = conn.prepareStatement(query);
                                        prt.execute();
                                        refreshTable();
                                
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                else if(response == buttonTypeCancel){
                                    alert.close();
                                }
                            });
                    });
                           
                editIcon.setOnMouseClicked((MouseEvent event)->{
                    viewEditBook.setVisible(true);
                    Book book = tableListBookView.getSelectionModel().getSelectedItem();
                    txtMaSach_Edit.setText(book.getMaSach());
                    txtTenSach_Edit.setText(book.getTenSach());
                    txtTacGia_Edit.setText(book.getTacGia());
                    txtSoLuong_Edit.setText(String.valueOf(book.getSoLuong()));
                    txtNhaXB_Edit.setText(book.getNhaXB());
                    String maSachTemp = txtMaSach_Edit.getText();
                    
                    conn = DBConnect.ConnectDB();
                    String sqlquery = "Select YearOfPublication from dbo.[Books] where ID = '" + book.getMaSach() + "';";
                    PreparedStatement prepare;
                            try {
                                prepare = conn.prepareStatement(sqlquery);
                                result = prepare.executeQuery();
                                if(result.next()){
                                    txtNamXB_Edit.setValue(result.getDate(1).toLocalDate());
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println("Lõi");
                            }
                    btnSua_Edit.setOnMouseClicked((MouseEvent ev)->{
                        String sql = "update dbo.[Books] SET ID = '"
                            + txtMaSach_Edit.getText() + "', BookName = N'"
                            + txtTenSach_Edit.getText() + "', AuthorName = N'"
                            + txtTacGia_Edit.getText() + "', YearOfPublication = '"
                            + String.valueOf(txtNamXB_Edit.getValue()) + "', Publisher = N'"
                            + txtNhaXB_Edit.getText() + "', Quantity = '"
                            + txtSoLuong_Edit.getText() + "'" +"where ID = '" + book.getMaSach() + "'";
                    
                        conn = DBConnect.ConnectDB();
                        try{
                        if(txtMaSach_Edit.getText().isEmpty()
                                || txtTenSach_Edit.getText().isEmpty()
                                || txtTacGia_Edit.getText().isEmpty()
                                || String.valueOf(txtNamXB_Edit.getValue()).isEmpty()
                                || txtNhaXB_Edit.getText().isEmpty()
                                || txtSoLuong_Edit.getText().isEmpty()){
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Chưa nhập đủ thông tin !");
                            alert.showAndWait();
                        }
                        else if(!isValidNumber(txtSoLuong_Edit.getText())){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Định dạng số lượng sách không đúng ! ");
                            alert.showAndWait();
                        }
                        else{
                            String query = "Select ID from dbo.[Books] where ID = N'" + txtMaSach_Edit.getText() + "';";
                            prt = conn.prepareCall(query);
                            ResultSet rst = prt.executeQuery();
                            if(rst.next()){
                                if(rst.getString(1).equals(maSachTemp)){
                                    Alert alert = new Alert(AlertType.CONFIRMATION);
                                    alert.setTitle("Cofirmation Message");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Bạn chắc chắn muốn sửa thông tin cuốn sách này ?");
                                    Optional<ButtonType> option = alert.showAndWait();
                            
                                    if(option.get().equals(ButtonType.OK)){
                                        prt = conn.prepareStatement(sql);
                                        int rslt = prt.executeUpdate();
                                        if(rslt > 0){
                                            viewEditBook.setVisible(false);
                                            refreshTable();
                                        }
                                    }   
                                    else{
                                        alert.close();
                                    }
                                }
                                else{
                                    Alert alert = new Alert(AlertType.CONFIRMATION);
                                    alert.setTitle("Error Message");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Mã sách đã tồn tại !");
                                    alert.showAndWait();
                                }
                            }
                            else{
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Cofirmation Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Bạn chắc chắn muốn sửa thông tin cuốn sách này ?");
                                Optional<ButtonType> option = alert.showAndWait();
                            
                                if(option.get().equals(ButtonType.OK)){
                                    prt = conn.prepareStatement(sql);
                                    int rslt = prt.executeUpdate();
                                if(rslt > 0){
                                    viewEditBook.setVisible(false);
                                    refreshTable();
                                }
                                }
                                else{
                                alert.close();
                                }
                            }
                        }
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                    });
                    btnHuy_Edit.setOnMouseClicked((MouseEvent e)->{
                        viewEditBook.setVisible(false);
                    });
                });
                
                HBox managebtn = new HBox(editIcon,deleteIcon);
                managebtn.setStyle("-fx-alignment: center");
                HBox.setMargin(deleteIcon, new Insets(0, 56, 0, 10));
                HBox.setMargin(editIcon, new Insets(4, 0, 0, 0));
                setGraphic(managebtn);
                
                setText(null);
                }
            }
        };
                return cell;
        };
        tuyChonColumn.setCellFactory(cellFoctory);
        tableListBookView.setItems(listDataBook);
    }
    
    public void fillterBook(){
        
        // Tạo ra fillter list chứa tất cả các fillter tìm kiếm
        
        FilteredList<Book> filteredDataBook = new FilteredList<>(listDataBook, b -> true);
        txtSearchBook.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataBook.setPredicate(Book -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String  searchKeyword = newValue.toLowerCase();
                
                if(Book.getMaSach().toLowerCase().indexOf(searchKeyword) > -1){
                    System.out.println("Sach");
                    return true;
                }
                else if(Book.getTenSach().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(Book.getNhaXB().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(Book.getTacGia().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(String.valueOf(Book.getNamXB()).toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(String.valueOf(Book.getSoLuong()).toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(Book.getNgayNhap().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(Book.getTheLoai().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else 
                    return false;
            });
        });
        
        // Add fillter list vừa tạo vào SortList
        SortedList<Book> sortedData = new SortedList<>(filteredDataBook);
        
        // Bind Sorteddata -> tableview
        sortedData.comparatorProperty().bind(tableListBookView.comparatorProperty());
        // setitems tableview là sortedData
        tableListBookView.setItems(sortedData);
    }
    
    public void cleanFormAddMember(){
        txtMaThanhVien_Add.setText(null);
        txtTenThanhVien_Add.setText(null);
        txtDiaChi_Add.setText(null);
        txtSoDienThoai_Add.setText(null);
        txtEmail_Add.setText(null);
        txtTheThanhVien_Add.setText(null);
        
        txtMaThanhVien_Add.requestFocus();
    }
    
    
    @FXML
    public void btnRefreshMember_Click(MouseEvent event) {
        refreshTableListMember();
    }
    
    public void refreshTableListMember(){
        try {
            listDataMember.clear();
            String sql = "UPDATE Members\n" +
                            "SET NumberOfBooks = ISNULL((SELECT SUM(Quantity) FROM Tickets WHERE Tickets.ID = Members.ID), 0);\n" +
                            "SELECT * FROM Members;";
            
            prt = conn.prepareStatement(sql);
            result = prt.executeQuery();
            
            while (result.next()){
                listDataMember.add(new Member(result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getInt(6),result.getInt(7),result.getString(8)));
            }
            tableListMemberView.setItems(listDataMember);
            loadChartTongQuanChung();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void btnAddMember_Click(MouseEvent event) {
        viewAddMember.setVisible(true);
        
        btnSua_AddMember.setOnMouseClicked((MouseEvent e) -> {
            conn = DBConnect.ConnectDB();
            String sql = "Insert into dbo.[Members] (ID, MemberName, MemberAddress, Phone, Email, NumberOfLoans, MemberCard, DateOFRegister) values (?,?,?,?,?,?,?,?)";
        
            try{
                if(txtMaThanhVien_Add.getText().isEmpty()
                    || txtTenThanhVien_Add.getText().isEmpty()
                    || txtDiaChi_Add.getText().isEmpty()
                    || txtSoDienThoai_Add.getText().isEmpty()
                    || txtEmail_Add.getText().isEmpty()
                    || txtTheThanhVien_Add.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Chưa nhập đủ thông tin !");
                    alert.showAndWait();
                }
                else{
                    String check = "select ID from dbo.[Members] where ID = '" + txtMaThanhVien_Add.getText() + "'";
                    prt = conn.prepareStatement(check);
                    result = prt.executeQuery();
                    if(result.next()){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Thành viên này đã tồn tại !");
                        alert.showAndWait();
                    }
                    else{
                        prt = conn.prepareStatement(sql);
                        prt.setString(1,txtMaThanhVien_Add.getText());
                        prt.setString(2,txtTenThanhVien_Add.getText());
                        prt.setString(3,txtDiaChi_Add.getText());
                        prt.setString(4,txtSoDienThoai_Add.getText());
                        prt.setString(5,txtEmail_Add.getText());
                        prt.setInt(6,0);
                        prt.setString(7,txtTheThanhVien_Add.getText());
                        prt.setDate(8,java.sql.Date.valueOf(LocalDate.now()));
                        prt.execute();
                    
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Thành viên đã được thêm !");
                        alert.showAndWait();
                    
                        cleanFormAddMember();
                        refreshTableListMember();
                        viewAddMember.setVisible(false);
                    }
                }
            
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        
        btnHuy_AddMember.setOnMouseClicked((MouseEvent e) ->{
           viewAddMember.setVisible(false);
        });
    }
    
    
    public void loadDataMember(){
        conn = DBConnect.ConnectDB();
        refreshTableListMember();
        
        maThanhVienColumn.setCellValueFactory(new PropertyValueFactory<>("maThanhVien"));
        tenThanhVienColumn.setCellValueFactory(new PropertyValueFactory<>("tenThanhVien"));
        daichiColumn.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        sdtColumn.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        sachDaMuonColumn.setCellValueFactory(new PropertyValueFactory<>("sachDaMuon"));
        sachChuaTraColumn.setCellValueFactory(new PropertyValueFactory<>("sachChuaTra"));
        theThanhVienColumn.setCellValueFactory(new PropertyValueFactory<>("soThe"));
        
        Callback<TableColumn<Member, String>,TableCell<Member, String>> cellFoctory = (TableColumn<Member, String> param) -> {
            
            final TableCell<Member, String> cell = new TableCell<Member, String>() {
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else {

                        OctIconView deleteIcon = new OctIconView(OctIcon.TRASHCAN);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill: #FB539B;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill: #4adaec;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event)->{
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText("Bạn có muốn tiếp tục xóa ?");
                            alert.setContentText("Bấm OK để tiếp tục hoặc Cancel để thoát.");
                            // Thiết lập các nút hiển thị trong cửa sổ thông báo
                            ButtonType buttonTypeOK = new ButtonType("OK");
                            ButtonType buttonTypeCancel = new ButtonType("Cancel");
                            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
                            // Hiển thị cửa sổ thông báo và chờ người dùng chọn
                            alert.showAndWait().ifPresent(response -> {
                                if (response == buttonTypeOK) {
                                    try {
                                
                                        Member member = tableListMemberView.getSelectionModel().getSelectedItem();
                                        String sql = "DELETE FROM dbo.[Members] WHERE ID  =" + "'" + member.getMaThanhVien()+ "';";
                                        conn = DBConnect.ConnectDB();
                                        prt = conn.prepareStatement(sql);
                                        prt.execute();
                                        refreshTableListMember();
                                
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                else{
                                    alert.close();
                                }
                            });
                    });
                           
                editIcon.setOnMouseClicked((MouseEvent event)->{
                    viewEditMember.setVisible(true);
                    Member member = tableListMemberView.getSelectionModel().getSelectedItem();
                    txtMaThanhVien_Edit.setText(member.getMaThanhVien());
                    txtTenThanhVien_Edit.setText(member.getTenThanhVien());
                    txtDiaChi_Edit.setText(member.getDiaChi());
                    txtSoDienThoai_Edit.setText(member.getSoDienThoai());
                    txtEmail_Edit.setText(member.getEmail());
                    txtTheThanhVien_Edit.setText(member.getSoThe());
                    String maThanhVienTemp = txtMaThanhVien_Edit.getText();
                    
                    btnSua_EditMember.setOnMouseClicked((MouseEvent ev)->{
                        String sql = "update dbo.[Members] SET ID = '"
                            + txtMaThanhVien_Edit.getText() + "', MemberName = '"
                            + txtTenThanhVien_Edit.getText() + "', MemberAddress = '"
                            + txtDiaChi_Edit.getText() + "', Phone = '"
                            + txtSoDienThoai_Edit.getText() + "', Email = '"
                            + txtEmail_Edit.getText() + "', MemberCard = '"
                            + txtTheThanhVien_Edit.getText() + "' where ID = '" + member.getMaThanhVien() + "'";
                    
                        conn = DBConnect.ConnectDB();
                        try{
                        if(txtMaThanhVien_Edit.getText().isEmpty()
                                || txtTenThanhVien_Edit.getText().isEmpty()
                                || txtDiaChi_Edit.getText().isEmpty()
                                || txtSoDienThoai_Edit.getText().isEmpty()
                                || txtEmail_Edit.getText().isEmpty()
                                || txtTheThanhVien_Edit.getText().isEmpty()){
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Chưa nhập đủ thông tin !");
                            alert.showAndWait();
                        }
                        else{
                            String checked = "Select ID from dbo.[Members] where ID = '" + txtMaThanhVien_Edit.getText() + "';";
                            prt = conn.prepareStatement(checked);
                            ResultSet rst = prt.executeQuery();
                            if(rst.next()){
                                if(rst.getString(1).equals(maThanhVienTemp)){
                                    Alert alert = new Alert(AlertType.CONFIRMATION);
                                    alert.setTitle("Cofirmation Message");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Bạn chắc chắn muốn sửa thông tin thành viên này ?");
                                    Optional<ButtonType> option = alert.showAndWait();
                            
                                    if(option.get().equals(ButtonType.OK)){
                                        prt = conn.prepareStatement(sql);
                                        int rslt = prt.executeUpdate();
                                        if(rslt > 0){
                                            viewEditMember.setVisible(false);
                                            refreshTableListMember();
                                        }
                                    }
                                    else{
                                        alert.close();
                                    }
                                }
                                else{
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Error Message");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Thành viên này đã tồn tại !");
                                    alert.showAndWait();
                                }
                            }
                            else{
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Cofirmation Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Bạn chắc chắn muốn sửa thông tin thành viên này ?");
                                Optional<ButtonType> option = alert.showAndWait();
                            
                                if(option.get().equals(ButtonType.OK)){
                                    prt = conn.prepareStatement(sql);
                                    int rslt = prt.executeUpdate();
                                    if(rslt > 0){
                                        viewEditMember.setVisible(false);
                                        refreshTableListMember();
                                    }
                                }
                                else{
                                    alert.close();
                                }
                            }
                        }
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                    });
                    btnHuy_EditMember.setOnMouseClicked((MouseEvent e)->{
                        viewEditMember.setVisible(false);
                    });
                });
                HBox managebtn = new HBox(editIcon,deleteIcon);
                managebtn.setStyle("-fx-alignment: center");
                HBox.setMargin(deleteIcon, new Insets(0, 56, 0, 10));
                HBox.setMargin(editIcon, new Insets(4, 0, 0, 0));
                setGraphic(managebtn);
                setText(null);
                }
            }
        };
                return cell;
        };
        tuyChonMember.setCellFactory(cellFoctory);
        tableListMemberView.setItems(listDataMember);
    }
    
    public void fillterMember(){
        
        // Tạo ra fillter list chứa tất cả các fillter tìm kiếm
        FilteredList<Member> filteredDataMember = new FilteredList<>(listDataMember, b -> true);
        txtSearchMember.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataMember.setPredicate(Member -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String  searchKeyword = newValue.toLowerCase();
                
                if(Member.getMaThanhVien().toLowerCase().indexOf(searchKeyword) > -1){
                    System.out.println("thanh vien");
                    return true;
                }
                else if(Member.getTenThanhVien().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(Member.getDiaChi().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(Member.getSoDienThoai().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(Member.getEmail().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(String.valueOf(Member.getSachDaMuon()).toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(String.valueOf(Member.getSachChuaTra()).toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(Member.getSoThe().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else 
                    return false;
            });
        });
        // fillter list vừa tạo vào SortList
        SortedList<Member> sortedData = new SortedList<>(filteredDataMember);
        // Bind Sorteddata -> tableview
        sortedData.comparatorProperty().bind(tableListMemberView.comparatorProperty());
        // setitems tableview là sortedData
        tableListMemberView.setItems(sortedData);
    }
    
    
    public void reFreshTableListTicket(){
        try {
            listDataTicket.clear();
            
            String sql = "select IDTickKet, MemberName, ID, CONVERT(varchar, DateOfBorrowing, 103) as FormattedDate, Fine, UserName, Quantity from Tickets;";
            prt = conn.prepareStatement(sql);
            result = prt.executeQuery();
            while (result.next()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateOld = LocalDate.parse(result.getString(4), formatter);
                LocalDate dateNow = LocalDate.now();
            
                LocalDate dateReturn = dateOld.plusDays(5);
                long numberOfDateFine = dateNow.until(dateReturn, ChronoUnit.DAYS);
                int fine;
                if(numberOfDateFine < 0){
                    fine = (int)numberOfDateFine * (-20000);
                }
                else {
                    fine = 0;
                }
                //update tien phat 
                String updateFine = "UPDATE Tickets SET Fine = ? WHERE IDTickKet = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateFine);
                updateStmt.setInt(1, fine);
                updateStmt.setString(2, result.getString("IDTickKet"));
                updateStmt.executeUpdate();
                    listDataTicket.add(new Ticket(result.getString(1),result.getString(2),result.getString(3),result.getString(6),result.getString(4),result.getInt(7),fine));
            }
            tableListTicketView.setItems(listDataTicket);
            loadChartTongQuanChung();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void btnRefreshListTickert_Click(MouseEvent event) {
        reFreshTableListTicket();
    }
    
    public void cleanFormAddTicket(){
        txtMaPhieu.setText(null);
        txtHoTenNguoiMuon.setText(null);
        txtSoLuongSach.setText(null);
        txtMaPhieu.requestFocus();
    }
    
    @FXML
    public void btnAddTicket_Click(MouseEvent event) {
        viewAddTicket.setVisible(true);
        
        btnLuu_AddTicket.setOnMouseClicked((MouseEvent e) -> {
            conn = DBConnect.ConnectDB();
            String sql = "Insert into dbo.[Tickets] (IDTickKet, MemberName, ID, UserName, DateOfBorrowing, Quantity) values(?,?,?,?,?,?) ";
            try{
                if(txtMaPhieu.getText().isEmpty()
                    || txtHoTenNguoiMuon.getText().isEmpty()
                    || txtSoLuongSach.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Chưa nhập đủ thông tin ");
                    alert.showAndWait();
                }
                else if(!isValidNumber(txtSoLuongSach.getText())){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Định dạng số lượng sách không đúng ! ");
                        alert.showAndWait();
                        }
                else{
                    String check = "select IDTickKet from dbo.[Tickets] where IDTickKet = '" + txtMaPhieu.getText() + "'";
                    prt = conn.prepareStatement(check);
                    result = prt.executeQuery();
                    if(result.next()){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Mã phiếu: " + txtMaPhieu.getText() + " đã tồn tại !");
                        alert.showAndWait();
                    }
                    else{
                        prt = conn.prepareStatement(sql);
                        System.out.println(txtHoTenNguoiMuon.getText());
                        String query = "select ID from dbo.[Members] where MemberName = N'" + txtHoTenNguoiMuon.getText() + "';";
                        PreparedStatement prepare = conn.prepareStatement(query);
                        ResultSet rs = prepare.executeQuery();
                        if(rs.next()){
                            prt.setString(1,txtMaPhieu.getText());
                            prt.setString(2,txtHoTenNguoiMuon.getText());
                            prt.setString(3,rs.getString(1));
                            prt.setString(4,userNameOnStream.getText());
                            prt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
                            prt.setString(6,txtSoLuongSach.getText());
                            prt.execute();
                    
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Phiếu mượn đã được thêm !");
                            alert.showAndWait();
                    
                            cleanFormAddTicket();
                            reFreshTableListTicket();
                            refreshTableListMember();
                            viewAddTicket.setVisible(false);
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Thành viên : " + txtHoTenNguoiMuon.getText() + " không tồn tại !");
                            alert.showAndWait();
                        }
                    }
                }
            
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        
        btnHuy_AddTicket.setOnMouseClicked((MouseEvent e) ->{
        viewAddTicket.setVisible(false);
        });
    }
    
    public void loadDataTicket(){
        conn = DBConnect.ConnectDB();
        
        reFreshTableListTicket();
        
        maPhieuColumn.setCellValueFactory(new PropertyValueFactory<>("maPhieu"));
        tenNguoiMuonColumn.setCellValueFactory(new PropertyValueFactory<>("tenNguoiMuon"));
        maNguoiMuonColumn.setCellValueFactory(new PropertyValueFactory<>("maNguoiMuon"));
        nguoiChoMuonColumn.setCellValueFactory(new PropertyValueFactory<>("nguoiChoMuon"));
        ngayMuonColumn.setCellValueFactory(new PropertyValueFactory<>("ngayMuon"));
        soLuongSachColumn.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        tienPhatColumn.setCellValueFactory(new PropertyValueFactory<>("tienPhat"));
        
        Callback<TableColumn<Ticket, String>,TableCell<Ticket, String>> cellFoctory = (TableColumn<Ticket, String> param) -> {
            
            final TableCell<Ticket, String> cell = new TableCell<Ticket, String>() {
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else {

                        OctIconView deleteIcon = new OctIconView(OctIcon.TRASHCAN);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill: #FB539B;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill: #4adaec;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event)->{
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText("Bạn có muốn tiếp tục xóa ?");
                            alert.setContentText("Bấm OK để tiếp tục hoặc Cancel để thoát.");
                            // Thiết lập các nút hiển thị trong cửa sổ thông báo
                            ButtonType buttonTypeOK = new ButtonType("OK");
                            ButtonType buttonTypeCancel = new ButtonType("Cancel");
                            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
                            // Hiển thị cửa sổ thông báo và chờ người dùng chọn
                            alert.showAndWait().ifPresent(response -> {
                                if (response == buttonTypeOK) {
                                    try {
                                        Ticket ticket = tableListTicketView.getSelectionModel().getSelectedItem();
                                        String query = "DELETE FROM dbo.[Tickets] WHERE IDTickKet  =" + "'" + ticket.getMaPhieu()+ "';";
                                        conn = DBConnect.ConnectDB();
                                        prt = conn.prepareStatement(query);
                                        prt.execute();
                                        
                                        // sau khi xoa phieu thi so luong sach da muon trong bang Member phai tu tang len
                                        String updateSql = "update Members set NumberOfLoans = NumberOfLoans + " + ticket.getSoLuong() + " where ID = '" + ticket.getMaNguoiMuon() + "';" ;
                                        prt = conn.prepareStatement(updateSql);
                                        prt.executeUpdate();  
                                        reFreshTableListTicket();
                                        refreshTableListMember();
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                else if(response == buttonTypeCancel){
                                    alert.close();
                                }
                            });
                    });
                editIcon.setOnMouseClicked((MouseEvent event)->{
                    viewEditTicket.setVisible(true);
                    Ticket ticket = tableListTicketView.getSelectionModel().getSelectedItem();
                    txtMaPhieu_Edit.setText(ticket.getMaPhieu());
                    txtHoTenNguoiMuon_Edit.setText(ticket.getTenNguoiMuon());
                    txtSoLuongSach_Edit.setText(String.valueOf(ticket.getSoLuong()));
                    
                    String maPhieuTemp = txtMaPhieu_Edit.getText();
                    
                    btnLuu_EditTicket.setOnMouseClicked((MouseEvent ev)->{
                        conn = DBConnect.ConnectDB();
                        try{
                            String query = "select ID from dbo.[Members] where MemberName = N'" + txtHoTenNguoiMuon_Edit.getText() + "';";
                            PreparedStatement prepare = conn.prepareStatement(query);
                            ResultSet rs = prepare.executeQuery();
                            if(rs.next()) {
                                String sql = "update dbo.[Tickets] SET "
                                    + "IDTickKet = N'" + txtMaPhieu_Edit.getText() + "',"
                                    + "MemberName = N'" + txtHoTenNguoiMuon_Edit.getText() + "',"
                                    + "ID = N'" + rs.getString(1) + "',"
                                    + "Quantity = " + txtSoLuongSach_Edit.getText() + " where IDTickKet = N'" + maPhieuTemp + "';";
                                if(txtMaPhieu_Edit.getText().isEmpty()
                                    || txtHoTenNguoiMuon_Edit.getText().isEmpty()
                                    || txtSoLuongSach_Edit.getText().isEmpty()){
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Error Message");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Chưa nhập đủ thông tin ");
                                    alert.showAndWait();
                                }
                                else if(!isValidNumber(txtSoLuongSach_Edit.getText())){
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Error Message");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Định dạng số lượng sách không đúng ! ");
                                    alert.showAndWait();
                                }
                                else{
                                   
                                    String checked = "select IDTickKet from Tickets where IDTickKet = N'" + txtMaPhieu_Edit.getText() + "';";
                                    System.out.println(checked);
                                    prt = conn.prepareStatement(checked);
                                    ResultSet rst = prt.executeQuery();
                                    if(rst.next()){
                                        if((rst.getString(1)).equals(maPhieuTemp)){
                                            Alert alert = new Alert(AlertType.CONFIRMATION);
                                            alert.setTitle("Cofirmation Message");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Bạn chắc chắn muốn sửa thông tin phiếu cho mượn này ?");
                                            Optional<ButtonType> option = alert.showAndWait();
                            
                                            if(option.get().equals(ButtonType.OK)){
                                                prt = conn.prepareStatement(sql);
                                                int rslt = prt.executeUpdate();
                                                if(rslt > 0){
                                                viewEditTicket.setVisible(false);
                                                reFreshTableListTicket();
                                                }
                                            }
                                            else{
                                                alert.close();
                                            }
                                        }
                                        else{
                                            System.out.println(rst.getString(1) +"!="+maPhieuTemp);
                                            System.out.println(maPhieuTemp +"!="+rst.getString(1));
                                            Alert alert = new Alert(AlertType.INFORMATION);
                                            alert.setTitle("Error Message");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Mã phiếu đã tồn tại ! ");
                                            alert.showAndWait();
                                        }
                                    }
                                    else{
                                        Alert alert = new Alert(AlertType.CONFIRMATION);
                                        alert.setTitle("Cofirmation Message");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Bạn chắc chắn muốn sửa thông tin phiếu cho mượn này ?");
                                        Optional<ButtonType> option = alert.showAndWait();
                            
                                        if(option.get().equals(ButtonType.OK)){
                                        prt = conn.prepareStatement(sql);
                                        int rslt = prt.executeUpdate();
                                        if(rslt > 0){
                                            viewEditTicket.setVisible(false);
                                            reFreshTableListTicket();
                                            }
                                        }
                                        else{
                                        alert.close();
                                        }
                                    }
                                }
                            }
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                    });
                    btnHuy_EditTicket.setOnMouseClicked((MouseEvent e)->{
                        viewEditTicket.setVisible(false);
                    });
                });
                HBox managebtn = new HBox(editIcon,deleteIcon);
                managebtn.setStyle("-fx-alignment: center");
                HBox.setMargin(deleteIcon, new Insets(0, 56, 0, 10));
                HBox.setMargin(editIcon, new Insets(4, 0, 0, 0));
                setGraphic(managebtn);
                setText(null);
                }
            }
        };
                return cell;
        };
        tuyChonTicketColumn.setCellFactory(cellFoctory);
        tableListTicketView.setItems(listDataTicket);
    }
    
    public void fillterTicket(){
        // Tạo ra fillter list chứa tất cả các fillter tìm kiếm
        
        FilteredList<Ticket> filteredDataTicket = new FilteredList<>(listDataTicket, b -> true);
        txtSearchTicket.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataTicket.setPredicate(Ticket -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String  searchKeyword = newValue.toLowerCase();
                
                if(Ticket.getMaPhieu().toLowerCase().indexOf(searchKeyword) > -1){
                    System.out.println("alo");
                    return true;
                }
                else if(Ticket.getTenNguoiMuon().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(Ticket.getMaNguoiMuon().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(Ticket.getNguoiChoMuon().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(String.valueOf(Ticket.getNgayMuon()).toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(String.valueOf(Ticket.getNgayMuon()).toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(String.valueOf(Ticket.getSoLuong()).toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(String.valueOf(Ticket.getTienPhat()).toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else
                    return false;
            });
        });
        // Add fillter list vừa tạo vào SortList
        SortedList<Ticket> sortedData = new SortedList<>(filteredDataTicket);
        // Bind Sorteddata -> tableview
        sortedData.comparatorProperty().bind(tableListTicketView.comparatorProperty());
        // setitems tableview là sortedData
        tableListTicketView.setItems(sortedData);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        selectedItemDashboard();
        
        angleDown.setOnMouseClicked(event -> toggleLabelVisibility());
        
        clickItemChart();
        
        loadCbbNam();
        
        loadChartTongQuanChung();
        
        loadChartTongQuanSach();
        
        loadChartTongQuanThanhVien();
        
        loadChartTongQuanPhieuMuon();
        
        loadDataBook();
        
        fillterBook();
        
        loadDataMember();
        
        fillterMember();
        
        loadDataTicket();
        
        fillterTicket();
        
        try {
           setUserName_AvatarOnStream();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        onDragMoveView(viewEditBook);
        
        onDragMoveView(viewAddMember);
        
        onDragMoveView(viewEditMember);
        
        onDragMoveView(viewAddTicket);
        
        onDragMoveView(viewEditTicket);
        
    }
}
      
