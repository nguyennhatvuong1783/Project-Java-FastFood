package DTO;

public class CHITIETHOADON {
	private String maHD;
	private String maMonAn;
	private int soLuong;
	private String ghiChu;
	
	public CHITIETHOADON() {
	}

	public CHITIETHOADON(String maHD, String maMonAn, int soLuong, String ghiChu) {
		super();
		this.maHD = maHD;
		this.maMonAn = maMonAn;
		this.soLuong = soLuong;
		this.ghiChu = ghiChu;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaMonAn() {
		return maMonAn;
	}

	public void setMaMonAn(String maMonAn) {
		this.maMonAn = maMonAn;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	

}
