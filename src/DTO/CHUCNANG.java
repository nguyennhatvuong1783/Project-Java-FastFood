package DTO;

public class CHUCNANG {

    private String maChucNang;
    private String tenChucNang;
    private String moTa;

    public CHUCNANG() {
    }

    public CHUCNANG(String maChucNang, String tenChucNang, String moTa) {
        super();
        this.maChucNang = maChucNang;
        this.tenChucNang = tenChucNang;
        this.moTa = moTa;
    }

    public String getMaChucNang() {
        return maChucNang;
    }

    public void setMaChucNang(String maChucNang) {
        this.maChucNang = maChucNang;
    }

    public String getTenChucNang() {
        return tenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        this.tenChucNang = tenChucNang;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return String.format("%s - %s: %s", maChucNang, tenChucNang, moTa);
    }
}
