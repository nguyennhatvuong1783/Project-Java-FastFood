package BUS;

import java.util.ArrayList;
import DTO.NGUYENLIEU;
import DAO.DaoNguyenLieu;

public class NguyenLieuBUS {
	public NguyenLieuBUS() {
		
	}

	DaoNguyenLieu daoNL = new DaoNguyenLieu();
	ArrayList<NGUYENLIEU> list_nguyenlieu = new ArrayList<NGUYENLIEU>();
	
	//Hàm lấy hết nguyên liệu từ database
	public ArrayList<NGUYENLIEU> selectAllNL() {
		ArrayList<NGUYENLIEU> listNL = new ArrayList<NGUYENLIEU>();
		listNL = daoNL.selectAll();
		return listNL;
	}
	
	//Hàm tìm nguyên liệu theo id
	public void findNL_id(String maNL) {
		
	}
	
	//Hàm thêm nguyên liệu
	public void insertNL(NGUYENLIEU nl) {
		daoNL.insert(nl);
	}
	
	//Hàm sửa nguyên liệu
	public void updateNL(NGUYENLIEU nl) {
		daoNL.update(nl);
	}
	
	//Hàm xóa nguyên liệu
	//Update trạng thái là 0 chứ ko xóa hoàn toàn nguyên liệu
	public void setInactiveNL(NGUYENLIEU nl) {
		daoNL.update(nl);
	}
	
	// Hàm lấy tên nguyên liệu
	public String getTenNL(String maNL) {
		String reString = null;
		ArrayList<NGUYENLIEU> nguyenlieus = DaoNguyenLieu.getInstance().selectAll();
		for (NGUYENLIEU nguyenlieu : nguyenlieus) {
			if (nguyenlieu.getMaNL().equals(maNL)) {
				reString = nguyenlieu.getTenNL();
			}
		}
		return reString;
	}
	
	// Hàm lấy đơn giá
	public int getDonGia(String maNL) {
		int reString = 0;
		ArrayList<NGUYENLIEU> nguyenlieus = DaoNguyenLieu.getInstance().selectAll();
		for (NGUYENLIEU nguyenlieu : nguyenlieus) {
			if (nguyenlieu.getMaNL().equals(maNL)) {
				reString = nguyenlieu.getDonGia();
			}
		}
		return reString;
	}
	
}
