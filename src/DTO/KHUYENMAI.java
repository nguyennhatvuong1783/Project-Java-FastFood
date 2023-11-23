package DTO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KHUYENMAI {

    private String maKM;
    private String tenKM;
    private String dieuKienKM;
    private Float giamGia;
    private Date ngayBD;
    private Date ngayKT;
    private int trangThai;

    public KHUYENMAI() {
    }

    public KHUYENMAI(String maKM, String tenKM, String dieuKienKM, Float giamGia, Date ngayBD, Date ngayKT,
            int trangThai) {
        super();
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.dieuKienKM = dieuKienKM;
        this.giamGia = giamGia;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.trangThai = trangThai;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public String getDieuKienKM() {
        return dieuKienKM;
    }

    public void setDieuKienKM(String dieuKienKM) {
        this.dieuKienKM = dieuKienKM;
    }

    public Float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(Float giamGia) {
        this.giamGia = giamGia;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String dateBĐToString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(ngayBD); 
        return dateString;
    }

    public String dateKTToString() {
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(ngayKT); 
        return dateString;
    }

}
