package DTO;

import javax.swing.JTextField;

public class NGUYENLIEU {
	private String maNL;
	private String tenNL;
	private int  soLuong;
	private String donViTinh;
	private int donGia;
	private String hinhAnh;
	private String loaiNL;
	private int trangThai;
	
	public NGUYENLIEU() {
	}

	public NGUYENLIEU(String maNL, String tenNL, int soLuong, String donViTinh, int donGia, String hinhAnh,
			String loaiNL, int trangThai) {
		super();
		this.maNL = maNL;
		this.tenNL = tenNL;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
		this.donGia = donGia;
		this.hinhAnh = hinhAnh;
		this.loaiNL = loaiNL;
		this.trangThai = trangThai;
	}

	public String getMaNL() {
		return maNL;
	}

	public void setMaNL(String txtMa) {
		this.maNL = txtMa;
	}

	public String getTenNL() {
		return tenNL;
	}

	public void setTenNL(String tenNL) {
		this.tenNL = tenNL;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getLoaiNL() {
		return loaiNL;
	}

	public void setLoaiNL(String loaiNL) {
		this.loaiNL = loaiNL;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
	
	

}
