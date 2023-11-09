package BUS;

import DTO.CHITIETPHIEUNHAP;
import DAO.DaoChiTietPhieuNhap;

public class ChiTietPhieuNhapBUS {
	public void insertCTPN(CHITIETPHIEUNHAP ctpn) {
		DaoChiTietPhieuNhap DAOctpn = new DaoChiTietPhieuNhap();
		DAOctpn.insert(ctpn);
	}
}
