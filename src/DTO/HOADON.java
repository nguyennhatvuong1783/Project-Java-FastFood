package DTO;

public class HOADON {
	private String maHD;
	private String ngayLap;
	private int tongTien;
	private int trangThai;
	private String maNV;
	private String maKH;
	private String maKM;
	private String tenNV;
	private String tenKH;
	
	public HOADON() {
	}

	public HOADON(String maHD, String ngayLap, int tongTien, int trangThai, String maNV, String maKH, String maKM, 
			String tenNV, String tenKH) {
		super();
		this.maHD = maHD;
		this.ngayLap = ngayLap;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
		this.maNV = maNV;
		this.maKH = maKH;
		this.maKM = maKM;
		this.tenNV = tenNV;
		this.tenKH = tenKH;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(String ngayLap) {
		this.ngayLap = ngayLap;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getMaKM() {
		return maKM;
	}

	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	
	

}
