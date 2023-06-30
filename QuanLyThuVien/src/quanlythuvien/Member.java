/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien;

/**
 *
 * @author truon
 */
public class Member {
    private String maThanhVien;
    private String tenThanhVien;
    private String diaChi;
    private String soDienThoai;
    private String Email;
    private int sachDaMuon;
    private int sachChuaTra;
    private String soThe;

    public Member(String maThanhVien, String tenThanhVien, String diaChi, String soDienThoai, String Email, int sachDaMuon, int sachChuaTra, String soThe) {
        this.maThanhVien = maThanhVien;
        this.tenThanhVien = tenThanhVien;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.Email = Email;
        this.sachDaMuon = sachDaMuon;
        this.sachChuaTra = sachChuaTra;
        this.soThe = soThe;
    }

    public String getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(String maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getSachDaMuon() {
        return sachDaMuon;
    }

    public void setSachDaMuon(int sachDaMuon) {
        this.sachDaMuon = sachDaMuon;
    }

    public int getSachChuaTra() {
        return sachChuaTra;
    }

    public void setSachChuaTra(int sachChuaTra) {
        this.sachChuaTra = sachChuaTra;
    }

    public String getSoThe() {
        return soThe;
    }

    public void setSoThe(String soThe) {
        this.soThe = soThe;
    }
    
    
}
