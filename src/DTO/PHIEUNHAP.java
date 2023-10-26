package DTO;

public class PHIEUNHAP {
	private String maPN;
	private String ngayNhap;
	private int tongTien;
	private String maNV;
	private String maNCC;
	
	public PHIEUNHAP() {
	}

	public PHIEUNHAP(String maPN, String ngayNhap, int tongTien, String maNV, String maNCC) {
		super();
		this.maPN = maPN;
		this.ngayNhap = ngayNhap;
		this.tongTien = tongTien;
		this.maNV = maNV;
		this.maNCC = maNCC;
	}

	public String getMaPN() {
		return maPN;
	}

	public void setMaPN(String maPN) {
		this.maPN = maPN;
	}

	public String getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(String ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	
	

}
