package DTO;

public class TAIKHOAN {
	private String userName;
	private String matKhau;
	private int trangThai;
	
	public TAIKHOAN() {
	}

	public TAIKHOAN(String userName, String matKhau, int trangThai) {
		super();
		this.userName = userName;
		this.matKhau = matKhau;
		this.trangThai = trangThai;
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
}