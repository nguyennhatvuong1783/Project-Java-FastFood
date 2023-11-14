package BUS;

import java.rmi.Remote;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.DaoChiTietHoaDon;
import DAO.DaoHoaDon;
import DAO.DaoKhachHang;
import DAO.DaoMonAn;
import DTO.CHITIETHOADON;
import DTO.HOADON;
import DTO.KHACHHANG;
import DTO.MONAN;
import GUI.MainLayoutGUI;
import GiaoDienChuan.MyTable;

public class BanHangBus {
	private DaoMonAn daoMonAn = DaoMonAn.getInstance();
	private DaoHoaDon daoHoaDon = DaoHoaDon.getInstance();
	private DaoKhachHang daoKhachHang = DaoKhachHang.getInstance();
	public BanHangBus() {
		
	}
	
	public void addDataToTableBanHang(MyTable myTable, String maMA, String tenMA, String soLuong, String donViTinh,String donGia, String loai) {
		int compare = 0;
		for(int i=0; i<myTable.getTable().getRowCount(); i++) {
			if (myTable.getValueAt(i, 0).equals(maMA)) {
				int current = Integer.parseInt(myTable.getValueAt(i, 2));
				int update = current + Integer.parseInt(soLuong);
				myTable.getModel().setValueAt(String.valueOf(update), i, 2);
				break;
			}else {
				compare++;
			}
		}
		if (compare==myTable.getTable().getRowCount()) {
			myTable.addRow(new String[] {maMA,tenMA,soLuong,donViTinh,donGia,loai});
		}
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
	
	public void tongTien(JTextField txt,int donGia, int soLuong) {
		int tongTien = 0;
		if (txt.getText().equals("")) {
			tongTien = donGia * soLuong;
		}else {
			tongTien = Integer.parseInt(txt.getText())+(donGia*soLuong);
		}
		txt.setText(String.valueOf(tongTien));
	}
	
	public void tongTienXoa(JTextField txt,int donGia, int soLuong) {
		int tongTien = 0;
		tongTien = Integer.parseInt(txt.getText())-(donGia*soLuong);
		txt.setText(String.valueOf(tongTien));
	}
	
	public void setMaHD(JTextField txt) {
		ArrayList<HOADON> result = DaoHoaDon.getInstance().selectAll();
		String id;
		String idLast = result.get(result.size()-1).getMaHD();
		String arr[] = idLast.split("D");
		int stt = Integer.parseInt(arr[1]) + 1;
		if (stt<=9) {
			id = "HD00" + String.valueOf(stt);
		}else if (stt<=99) {
			id = "HD0" + String.valueOf(stt);
		}else {
			id = "HD" + String.valueOf(stt);
		}
		txt.setText(id);	
	}
	
	public void setMaKH(JTextField txt) {
		ArrayList<KHACHHANG> khachhangs = DaoKhachHang.getInstance().selectAll();
		String id;
		String idLast = khachhangs.get(khachhangs.size()-1).getMaKH();
		String arr[] = idLast.split("H");
		int stt = Integer.parseInt(arr[1]) + 1;
		if (stt<=9) {
			id = "KH00" + String.valueOf(stt);
		}else if (stt<=99) {
			id = "KH0" + String.valueOf(stt);
		}else {
			id = "KH" + String.valueOf(stt);
		}
		txt.setText(id);	
	}
	
	public void setTenKH(JTextField txt, String maKH) {
		ArrayList<KHACHHANG> khachHang = DaoKhachHang.getInstance().selectAll();
		for (KHACHHANG khachhang2 : khachHang) {
			if (khachhang2.getMaKH().equals(maKH)) {
				txt.setText(khachhang2.getTenKH());
				break;
			}
		}
	}
	
	public void setDataFormChonKH(MyTable table) {
		ArrayList<KHACHHANG> khachHang = DaoKhachHang.getInstance().selectAll();
		table.clear();
		for (KHACHHANG khachhang2 : khachHang) {
			if (khachhang2.getTrangThai()==1) {
				table.addRow(new String[] {khachhang2.getMaKH(), khachhang2.getTenKH(), khachhang2.getSdt(), khachhang2.getDiaChi()});
			}
		}
	}
	
	public Boolean checkTienNhap(JTextField txt, int tongTien) {
		String input = txt.getText();
		if (input.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tiền khách đưa");
			return false;
		}else {
			try {
				int money = Integer.parseInt(input);
				if (money<tongTien) {
					JOptionPane.showMessageDialog(null, "Tiền khách đưa không đủ");
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Tiền phải là số nguyên");
				return false;
			}
		}
		return true;
	}
	
	public Boolean checkBtnHoanThanh(MyTable table, JTextField txtKH, JTextField tienThoi) {
		if (table.getModel().getRowCount()==0) {
			JOptionPane.showMessageDialog(null, "Chưa có món ăn cần thanh toán");
			return false;
		}else if (tienThoi.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tiền khách đưa");
			return false;
		}
		else {
			if (txtKH.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Chưa chọn thông tin khách hàng");
				return false;
			}
		}
		return true;
	}
	
	public String formatNgayLap(String ngayLap) {
		String arr[] = ngayLap.split("/");
		return arr[2] + "-" + arr[1] + "-" + arr[0];
	}
	
	public void mouseClickBtnHoanThanh(MyTable table, JTextField txtHD, JTextField txtNgayLap, JTextField txtTongTien, String Manv, JTextField txtKH,String maKH, JTextField txtKM, JTextField txtTienThoi) {
		if (checkBtnHoanThanh(table, txtKH, txtTienThoi)) {
			int soLuongBan = 0, soLuongMoi = 0;
			String maHD = txtHD.getText();
			String ngayLap = formatNgayLap(txtNgayLap.getText());
			int tongTien = Integer.parseInt(txtTongTien.getText());
			int ketqua = DaoHoaDon.getInstance().insert(new HOADON(maHD, ngayLap, tongTien, 1, Manv, maKH, null, null, null));
			if (ketqua!=0) {
				JOptionPane.showMessageDialog(null, "Thanh toán thành công");
				ArrayList<MONAN> monans = DaoMonAn.getInstance().selectAll();
			    for (int i = 0; i < table.getModel().getRowCount(); i++) {
			    	for (MONAN monan : monans) {
			    		int j=0;
						if (monan.getMaMonAn().equals(table.getValueAt(j, 0))) {
							soLuongBan = Integer.parseInt(String.valueOf(table.getValueAt(j, 2)));
							soLuongMoi = monan.getSoLuong() - soLuongBan;
							monan.setSoLuong(soLuongMoi);
							DaoMonAn.getInstance().update(monan);
							DaoChiTietHoaDon.getInstance().insert(new CHITIETHOADON(maHD, monan.getMaMonAn(), soLuongBan, ngayLap));
						}else {
							j++;
						}
					}
				}
			    
			    int lengh =  table.getModel().getRowCount();
			    for (int i = 0; i < lengh; i++) {
			        table.getModel().removeRow(0);
			    }
				setMaHD(txtHD);
				txtTongTien.setText("");
				txtKH.setText("");
				txtTienThoi.setText("");
				txtKM.setText("");
			}
			
		}
	}
	


}
