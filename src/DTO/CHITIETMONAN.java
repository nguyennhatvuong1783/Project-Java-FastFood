package DTO;

public class CHITIETMONAN {
	private String maMonAn;
	private String maNguyenLieu;
	private int soLuong;
	
	public CHITIETMONAN() {
	}

	public CHITIETMONAN(String maMonAn, String maNguyenLieu, int soLuong) {
		super();
		this.maMonAn = maMonAn;
		this.maNguyenLieu = maNguyenLieu;
		this.soLuong = soLuong;
	}

	public String getMaMonAn() {
		return maMonAn;
	}

	public void setMaMonAn(String maMonAn) {
		this.maMonAn = maMonAn;
	}

	public String getMaNguyenLieu() {
		return maNguyenLieu;
	}

	public void setMaNguyenLieu(String maNguyenLieu) {
		this.maNguyenLieu = maNguyenLieu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	

}
