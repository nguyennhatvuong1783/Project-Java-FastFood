package DTO;

public class TAIKHOAN {

    private String userName;
    private String matKhau;
    private int trangThai;
    private String maQuyen;

    public TAIKHOAN() {
    }

    public TAIKHOAN(String userName, String matKhau, int trangThai, String maQuyen) {
        super();
        this.userName = userName;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.maQuyen = maQuyen;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    @Override
    public String toString() {
        return "%s:%s - %d - %s".formatted(userName, matKhau, trangThai, maQuyen);
    }

}
