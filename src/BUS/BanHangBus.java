package BUS;

import DAO.DaoHoaDon;
import DAO.DaoKhachHang;
import DAO.DaoMonAn;
import GiaoDienChuan.MyTable;

public class BanHangBus {
	private DaoMonAn daoMonAn = DaoMonAn.getInstance();
	private DaoHoaDon daoHoaDon = DaoHoaDon.getInstance();
	private DaoKhachHang daoKhachHang = DaoKhachHang.getInstance();

	public BanHangBus() {
		
	}
	
	public void addDataToTableBanHang(MyTable myTable, String maMA, String tenMA, String soLuong, String donViTinh,String donGia, String loai) {
		myTable.addRow(new String[] {maMA,tenMA,soLuong,donViTinh,donGia,loai});
	}
	
	public void updateSoLuong(MyTable table ,int soLuongBan, String maMA) {
		int sumRow = table.getTable().getRowCount();
		String arr[] = new String[sumRow];
		String ma;
		int soLuong=0, soLuongNew=0;
		for (int i = 0; i < sumRow; i++) {
			ma = (String) table.getTable().getValueAt(i, 0);
			if (ma.equals(maMA)) {
				soLuong = Integer.parseInt(String.valueOf(table.getTable().getValueAt(i, 2)));
				soLuongNew = soLuong - soLuongBan;
				table.getTable().setValueAt(String.valueOf(soLuongNew), i, 2);
				break;
			}
		}		
	}
	
	public void updateSoLuong2(MyTable table ,int soLuongBan, String maMA) {
		int sumRow = table.getTable().getRowCount();
		String arr[] = new String[sumRow];
		String ma;
		int soLuong=0, soLuongNew=0;
		for (int i = 0; i < sumRow; i++) {
			ma = (String) table.getTable().getValueAt(i, 0);
			if (ma.equals(maMA)) {
				soLuong = Integer.parseInt(String.valueOf(table.getTable().getValueAt(i, 2)));
				soLuongNew = soLuong + soLuongBan;
				table.getTable().setValueAt(String.valueOf(soLuongNew), i, 2);
				break;
			}
		}		
	}

}
