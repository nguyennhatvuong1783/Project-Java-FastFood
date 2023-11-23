/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author HOME
 */
public class THONGKE {
    private int SoTien;
    private String Thang,Nam;

    public THONGKE(int SoTien, String Thang, String Nam) {
        this.SoTien = SoTien;
        this.Thang = Thang;
        this.Nam = Nam;
    }

    public int getSoTien() {
        return SoTien;
    }

    public void setSoTien(int SoTien) {
        this.SoTien = SoTien;
    }

    public String getThang() {
        return Thang;
    }

    public void setThang(String Thang) {
        this.Thang = Thang;
    }

    public String getNam() {
        return Nam;
    }

    public void setNam(String Nam) {
        this.Nam = Nam;
    }

}
