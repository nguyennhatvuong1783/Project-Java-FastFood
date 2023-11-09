package BUS;

import DTO.NGUYENLIEU;
import java.util.ArrayList;
import DAO.DaoNguyenLieu;
import GUI.NhapHangGUI;
import DAO.DaoNhaCungCap;
import DAO.DaoNhanVien;

public class NhapHangBUS {
	DaoNguyenLieu daoNL = new DaoNguyenLieu();
	
	//Hàm lấy all nguyên liệu
	public ArrayList<NGUYENLIEU> selectAll() {
		return daoNL.selectAll();
	}
	
	//Hàm cập nhật sl nguyên liệu khi nhập hàng
	public void updateSL_NL(String manl, int sl) {
		daoNL.updateSL(manl, sl);
	}
	
	public String getMaNCC_BUS(String tennnc) {
		DaoNhaCungCap ncc = new DaoNhaCungCap();
		String TenNCC = ncc.selectByName(tennnc);
		return TenNCC;
	}
	
	public String getMaNV_BUS(String tennv) {
		DaoNhanVien nv = new DaoNhanVien();
		String TenNV = nv.selectByName(tennv);
		return TenNV;
	}
}
