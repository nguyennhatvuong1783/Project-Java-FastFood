package BUS;

import DTO.PHIEUNHAP;

import java.util.ArrayList;

import DAO.DaoPhieuNhap;

public class PhieuNhapBUS {
	public void insertPN(PHIEUNHAP pn) {
		DaoPhieuNhap DAOpn = new DaoPhieuNhap();
		DAOpn.insert(pn);
	}
	
	public ArrayList<PHIEUNHAP> selectAllPN(){
		DaoPhieuNhap DAOpn = new DaoPhieuNhap();
		ArrayList<PHIEUNHAP> pnlist = new ArrayList<PHIEUNHAP>();
		pnlist = DAOpn.selectAll();
		return pnlist;
	}
}
