package DTO;

public class MONAN {
	private String maMonAn;
	private String tenMonAn;
	private int soLuong;
	private String donViTinh;
	private int donGia;
	private String hinhAnh;
	private String loai;
	private int trangThai;
	
	public MONAN() {
	}

	public MONAN(String maMonAn, String tenMonAn, int soLuong, String donViTinh, int donGia, String hinhAnh,
			String loai, int trangThai) {
		super();
		this.maMonAn = maMonAn;
		this.tenMonAn = tenMonAn;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
		this.donGia = donGia;
		this.hinhAnh = hinhAnh;
		this.loai = loai;
		this.trangThai = trangThai;
	}

	public String getMaMonAn() {
		return maMonAn;
	}

	public void setMaMonAn(String maMonAn) {
		this.maMonAn = maMonAn;
	}

	public String getTenMonAn() {
		return tenMonAn;
	}

	public void setTenMonAn(String tenMonAn) {
		this.tenMonAn = tenMonAn;
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

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return maMonAn+";"+tenMonAn+";"+donGia+";"+trangThai;
	}
	

}
