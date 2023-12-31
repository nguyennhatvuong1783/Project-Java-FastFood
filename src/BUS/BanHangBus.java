package BUS;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


import DAO.DaoChiTietHoaDon;
import DAO.DaoHoaDon;
import DAO.DaoKhachHang;
import DAO.DaoKhuyenMai;
import DAO.DaoMonAn;
import DTO.CHITIETHOADON;
import DTO.HOADON;
import DTO.KHACHHANG;
import DTO.KHUYENMAI;
import DTO.MONAN;
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
	
	public String getMaKM(int tongTien) {
		ArrayList<KHUYENMAI> dsKhuyenMai = DaoKhuyenMai.getInstance().selectAll();
		int dieuKien[] = new int[dsKhuyenMai.size()];
		int i = 0;
		int index = 0;
		Boolean flag = false;
		String maKM = null;
		for (KHUYENMAI khuyenmai : dsKhuyenMai) {
			if (khuyenmai.getTrangThai() != 1) {
				dsKhuyenMai.remove(khuyenmai);
			}else {
				dieuKien[i] = splitDieuKienKM(khuyenmai.getDieuKienKM());
				i++;
			}
		}
		
		Arrays.sort(dieuKien);
		for(int j =0; j<dieuKien.length; j++) {
			if (tongTien >= dieuKien[j]) {
				index = j;
				flag = true;
				break;
			}
		}
		
		if (flag == true) {
			maKM = dsKhuyenMai.get(index).getMaKM();
		}
		return maKM;		
	}
	
	private Float getDieuKienKM(String maKM) {
		Float result = 0.0f;
		ArrayList<KHUYENMAI> dsKhuyenMai = DaoKhuyenMai.getInstance().selectAll();
		for (KHUYENMAI khuyenmai : dsKhuyenMai) {
			if (khuyenmai.getMaKM().equals(maKM)) {
				result = khuyenmai.getGiamGia();
				break;
			}
		}
		return result;
	}
	
	public void MouseclickBtnKhuyenMai(JTextField txtKhuyenMai, JTextField txtTongTien, JTextField tienKhachDua, JTextField tienThoi) {
		if (txtTongTien.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa có hóa đơn để áp dụng");
		}else {
			int tongTien = Integer.parseInt(txtTongTien.getText());
			String maKM = getMaKM(tongTien);
			if (maKM!=null) {
				txtKhuyenMai.setText(maKM);
				int tongTienNew = (int)(tongTien * getDieuKienKM(maKM));
				txtTongTien.setText(String.valueOf(tongTienNew));
				JOptionPane.showMessageDialog(null, "Đã áp dụng khuyến mãi, vui lòng nhập lại tiền khách đưa");
				tienThoi.setText("");
				tienKhachDua.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "Hóa đơn chưa đủ điều kiện áp dụng");
			}
		}
	}
	
	private int splitDieuKienKM(String dieuKien) {
		String result[] = dieuKien.split("=");
		int kq = Integer.parseInt(result[1].replaceAll(" ", ""));
		return kq;
	}
	
	public void mouseClickBtnHoanThanh(MyTable table, JTextField txtHD, JTextField txtNgayLap, JTextField txtTongTien, String Manv, JTextField txtKH,String maKH, JTextField txtKM, JTextField txtTienThoi, JTextField txtTienKhachDua) {
		if (checkBtnHoanThanh(table, txtKH, txtTienThoi)) {
			if (txtKM.getText().trim().equals("") && getMaKM(Integer.parseInt(txtTongTien.getText()))!=null) {
				if(JOptionPane.showConfirmDialog(null, "Hóa đơn có khuyến mãi chưa áp dụng, bạn có muốn áp dụng khuyến mãi","Thông báo",
						JOptionPane.YES_NO_OPTION)== JOptionPane.OK_OPTION) {
					MouseclickBtnKhuyenMai(txtKM, txtTongTien, txtTienKhachDua, txtTienThoi);	
					String maHD = txtHD.getText();
					String ngayLap = formatNgayLap(txtNgayLap.getText());
					String maKM = txtKM.getText();			
					if (txtTienKhachDua.getText().trim().equals("")==false) {
						int tongTien = Integer.parseInt(txtTongTien.getText());
						int ketqua = DaoHoaDon.getInstance().insert(new HOADON(maHD, ngayLap, tongTien, 1, Manv, maKH, maKM, null, null));
						if (ketqua!=0) {
							JOptionPane.showMessageDialog(null, "Thanh toán thành công");
						    
						    int lengh =  table.getModel().getRowCount();
						    for (int i = 0; i < lengh; i++) {
						    	String soLuong = String.valueOf(table.getValueAt(0, 2));
						    	DaoChiTietHoaDon.getInstance().insert(new CHITIETHOADON(maHD, String.valueOf(table.getValueAt(0, 0)),Integer.parseInt(soLuong) , null));
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
			}else {
				String maHD = txtHD.getText();
				String ngayLap = formatNgayLap(txtNgayLap.getText());
				String maKM = null;
				if (txtKM.getText().trim().equals("")==false) {
					maKM = txtKM.getText();
				}		
				int tongTien = Integer.parseInt(txtTongTien.getText());
				int ketqua = DaoHoaDon.getInstance().insert(new HOADON(maHD, ngayLap, tongTien, 1, Manv, maKH, maKM, null, null));
				if (ketqua!=0) {
					JOptionPane.showMessageDialog(null, "Thanh toán thành công");
				    int lengh =  table.getModel().getRowCount();
				    for (int i = 0; i < lengh; i++) {
				    	String soLuong = String.valueOf(table.getValueAt(0, 2));
				    	DaoChiTietHoaDon.getInstance().insert(new CHITIETHOADON(maHD, String.valueOf(table.getValueAt(0, 0)),Integer.parseInt(soLuong) , null));
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
	


}
