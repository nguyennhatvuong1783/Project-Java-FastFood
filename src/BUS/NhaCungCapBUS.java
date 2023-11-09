package BUS;

import java.util.ArrayList;

import DAO.DaoNhaCungCap;
import DTO.NHACUNGCAP;

public class NhaCungCapBUS {
	public ArrayList<NHACUNGCAP> selectAllNCC(){
		DaoNhaCungCap nccdao = new DaoNhaCungCap();
		ArrayList<NHACUNGCAP> ncclist = nccdao.selectAll();
		return ncclist;
	}
	
}
