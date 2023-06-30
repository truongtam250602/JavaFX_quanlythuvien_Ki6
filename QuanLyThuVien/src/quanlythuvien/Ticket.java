/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author truon
 */
public class Ticket {
    private String maPhieu;
    private String tenNguoiMuon;
    private String maNguoiMuon;
    private String nguoiChoMuon;
    private String ngayMuon;
    private int soLuong;
    private int tienPhat;


    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getTenNguoiMuon() {
        return tenNguoiMuon;
    }

    public void setTenNguoiMuon(String tenNguoiMuon) {
        this.tenNguoiMuon = tenNguoiMuon;
    }

    public String getMaNguoiMuon() {
        return maNguoiMuon;
    }

    public void setMaNguoiMuon(String maNguoiMuon) {
        this.maNguoiMuon = maNguoiMuon;
    }

    public String getNguoiChoMuon() {
        return nguoiChoMuon;
    }

    public void setNguoiChoMuon(String nguoiChoMuon) {
        this.nguoiChoMuon = nguoiChoMuon;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public int getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(int tienPhat) {
        this.tienPhat = tienPhat;
    }

    
    public Ticket(String maPhieu, String tenNguoiMuon, String maNguoiMuon, String nguoiChoMuon, String ngayMuon, int soLuong, int tienPhat) {
        this.maPhieu = maPhieu;
        this.tenNguoiMuon = tenNguoiMuon;
        this.maNguoiMuon = maNguoiMuon;
        this.nguoiChoMuon = nguoiChoMuon;
        this.ngayMuon = ngayMuon;
        this.soLuong = soLuong;
        this.tienPhat = tienPhat;
    }
    
}
