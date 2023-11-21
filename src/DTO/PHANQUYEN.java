package DTO;

public class PHANQUYEN {

    private String maQuyen;
    private String tenQuyen;
    private String moTaQuyen;
    private int trangThai;

    public PHANQUYEN() {
    }

    public PHANQUYEN(String maQuyen, String tenQuyen, String moTaQuyen, int trangThai) {
        super();
        this.maQuyen = maQuyen;
        this.tenQuyen = tenQuyen;
        this.moTaQuyen = moTaQuyen;
        this.trangThai = trangThai;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public String getMoTaQuyen() {
        return moTaQuyen;
    }

    public void setMoTaQuyen(String moTaQuyen) {
        this.moTaQuyen = moTaQuyen;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "PHANQUYEN{" + "maQuyen=" + maQuyen + ", tenQuyen=" + tenQuyen + ", moTaQuyen=" + moTaQuyen + ", trangThai=" + trangThai + '}';
    }

}
