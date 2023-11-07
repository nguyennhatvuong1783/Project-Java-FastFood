package BUS;

import java.util.ArrayList;
import DTO.NGUYENLIEU;
import DAO.DaoNguyenLieu;

public class NguyenLieuBUS {
	DaoNguyenLieu daoNL = new DaoNguyenLieu();
	ArrayList<NGUYENLIEU> list_nguyenlieu = new ArrayList<NGUYENLIEU>();
	
	//Hàm tìm nguyên liệu theo id
	public void findNL_id(String maNL) {
		
	}
	
	//Hàm thêm nguyên liệu
	public void insertNL(NGUYENLIEU nl) {
		daoNL.insert(nl);
	}
	
	//Hàm sửa nguyên liệu
	public void updateNL(NGUYENLIEU nl) {
		NGUYENLIEU test = daoNL.selectById(nl);
		if(test != null) {
			test = nl;
			daoNL.update(test);
		}
	}
	
	//Hàm xóa nguyên liệu
	public void deleteNL(NGUYENLIEU nl) {
		daoNL.delete(nl);
	}
}
