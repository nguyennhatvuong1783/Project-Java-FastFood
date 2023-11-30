package BUS;

import DTO.CHITIETPHIEUNHAP;

import java.util.ArrayList;

import DAO.DaoChiTietPhieuNhap;

public class ChiTietPhieuNhapBUS {
	public void insertCTPN(CHITIETPHIEUNHAP ctpn) {
		DaoChiTietPhieuNhap DAOctpn = new DaoChiTietPhieuNhap();
		DAOctpn.insert(ctpn);
	}
	
	public ArrayList<CHITIETPHIEUNHAP> getAllCTPhieuNhap(String maPN){
		ArrayList<CHITIETPHIEUNHAP> chitietphieunhaps = DaoChiTietPhieuNhap.getInstance().selectAll();
		ArrayList<CHITIETPHIEUNHAP> result = new ArrayList<CHITIETPHIEUNHAP>();
		for (CHITIETPHIEUNHAP chitietphieunhap : chitietphieunhaps) {
			if (chitietphieunhap.getMaPN().equals(maPN)) {
				result.add(chitietphieunhap);
			}
		}
		return result;
	}
}
