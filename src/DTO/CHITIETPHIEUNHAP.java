package DTO;

public class CHITIETPHIEUNHAP {
	private String maPN;
	private String maNL;
	private int soLuong;
	
	public CHITIETPHIEUNHAP() {
	}

	public CHITIETPHIEUNHAP(String maPN, String maNL, int soLuong) {
		super();
		this.maPN = maPN;
		this.maNL = maNL;
		this.soLuong = soLuong;
	}

	public String getMaPN() {
		return maPN;
	}

	public void setMaPN(String maPN) {
		this.maPN = maPN;
	}

	public String getMaNL() {
		return maNL;
	}

	public void setMaNL(String maNL) {
		this.maNL = maNL;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	

}
