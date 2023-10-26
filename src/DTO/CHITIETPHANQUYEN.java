package DTO;

public class CHITIETPHANQUYEN {
	private String maQuyen;
	private String maChucNang;
	
	public CHITIETPHANQUYEN() {
	}

	public CHITIETPHANQUYEN(String maQuyen, String maChucNang) {
		super();
		this.maQuyen = maQuyen;
		this.maChucNang = maChucNang;
	}

	public String getMaQuyen() {
		return maQuyen;
	}

	public void setMaQuyen(String maQuyen) {
		this.maQuyen = maQuyen;
	}

	public String getMaChucNang() {
		return maChucNang;
	}

	public void setMaChucNang(String maChucNang) {
		this.maChucNang = maChucNang;
	}
	
	

}
