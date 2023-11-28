package BUS;

import DTO.PHIEUNHAP;
import DTO.CHITIETPHIEUNHAP;
import DTO.NGUYENLIEU;
import DTO.NHANVIEN;

import java.util.ArrayList;

import DAO.DaoPhieuNhap;
import DAO.DaoChiTietPhieuNhap;
import DAO.DaoNguyenLieu;
import DAO.DaoNhanVien;
import DAO.DaoNhaCungCap;

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
	
	public ArrayList<CHITIETPHIEUNHAP> selectAllPNbyID(String maPN){
		DaoChiTietPhieuNhap DAOctpn = new DaoChiTietPhieuNhap();
		ArrayList<CHITIETPHIEUNHAP> pnlist = new ArrayList<CHITIETPHIEUNHAP>();
		pnlist = DAOctpn.selectByCondition(maPN);
		return pnlist;
	}
	
	public ArrayList<NGUYENLIEU> selectAllNL(){
		DaoNguyenLieu DAOnl = new DaoNguyenLieu();
		ArrayList<NGUYENLIEU> nllist = new ArrayList<NGUYENLIEU>();
		nllist = DAOnl.selectAll();
		return nllist;
	}
	
}
