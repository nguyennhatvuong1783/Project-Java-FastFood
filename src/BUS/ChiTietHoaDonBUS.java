package BUS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import DAO.DaoChiTietHoaDon;
import DAO.DaoHoaDon;
import DAO.DaoKhuyenMai;
import DAO.DaoMonAn;
import DTO.CHITIETHOADON;
import DTO.HOADON;
import DTO.KHUYENMAI;
import DTO.MONAN;
import GiaoDienChuan.FormatMoney;
import GiaoDienChuan.MyTable;

public class ChiTietHoaDonBUS {
	private HOADON hoadon;
	private DaoHoaDon DAO = DaoHoaDon.getInstance();
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public ChiTietHoaDonBUS(MyTable pnlTable, JLabel lblMaHD, JLabel lblTenKH, JLabel lblTenNV, JLabel lblNgayLap, JLabel lblKhuyenMai, 
			JLabel lblTongTien, String MaHD) {
		UploadHD(lblMaHD, lblTenKH, lblTenNV, lblNgayLap, lblKhuyenMai, lblTongTien, MaHD);
		UploadCTHD(pnlTable, MaHD);
	}
	
	public void UploadHD(JLabel lblMaHD, JLabel lblTenKH, JLabel lblTenNV, JLabel lblNgayLap, JLabel lblKhuyenMai, 
			JLabel lblTongTien, String MaHD) {
		HOADON hoadonTmp = new HOADON(MaHD, "", 0, 0, "", "", "");
		hoadon = DAO.selectById(hoadonTmp);
		lblMaHD.setText(MaHD);
		lblTenKH.setText(DAO.selectTenKHbyMaKH(hoadon.getMaKH()));
		lblTenNV.setText(DAO.selectTenNVbyMaNV(hoadon.getMaNV()));
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(hoadon.getNgayLap());
			lblNgayLap.setText(formatter.format(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(hoadon.getMaKM() != null) {
			KHUYENMAI khuyenmaiTmp = new KHUYENMAI(hoadon.getMaKM(), "", "", null, "", "", 0);
			KHUYENMAI khuyenmai = DaoKhuyenMai.getInstance().selectById(khuyenmaiTmp);
			lblKhuyenMai.setText(khuyenmai.getTenKM());
		}
		lblTongTien.setText(FormatMoney.getFormat((long) hoadon.getTongTien()));
	}
	
	public void UploadCTHD(MyTable pnlTable, String MaHD) {
		DefaultTableModel dtm = pnlTable.getModel();
		String condition = "MAHD='" + MaHD + "'";
		ArrayList<CHITIETHOADON> CTHDList = DaoChiTietHoaDon.getInstance().selectByCondition(condition);
		MONAN monan;
		
		dtm.setRowCount(0);
		for(CHITIETHOADON CTHD : CTHDList) {
			monan = new MONAN(CTHD.getMaMonAn(), "", 0, "", 0, "", "", 0);
			monan = DaoMonAn.getInstance().selectById(monan);
			dtm.addRow(new Object[]{monan.getMaMonAn(), monan.getTenMonAn(), CTHD.getSoLuong(), monan.getDonViTinh(), 
					monan.getDonGia(), monan.getLoai()});
		}
	}
}