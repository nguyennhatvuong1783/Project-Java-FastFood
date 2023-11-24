package DTO;

public class NHANVIEN {
	private String maNV;
	private String tenNV;
	private String gioiTinh;
	private String ngaySinh;
	private String sdt;
	private String diaChi;
	private int trangThai;
	
	public NHANVIEN() {
	}
	public NHANVIEN(String maNV, String tenNV, String gioiTinh, String ngaySinh, String diaChi, String sdt,
			int trangThai) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.trangThai = trangThai;
	}
	public void setNhanVien(NHANVIEN nhanVien) {
		this.maNV = nhanVien.maNV;
		this.tenNV = nhanVien.tenNV;
		this.gioiTinh = nhanVien.gioiTinh;
		this.ngaySinh = nhanVien.ngaySinh;
		this.sdt = nhanVien.sdt;
		this.diaChi = nhanVien.diaChi;
		this.trangThai = nhanVien.trangThai;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
	
	
	

}
