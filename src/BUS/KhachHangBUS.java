/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;


import java.util.ArrayList;

import DAO.DaoKhachHang;
import DTO.KHACHHANG;
import GiaoDienChuan.MyTable;



/**
 *
 * @author HP
 */
public class KhachHangBUS {
   DaoKhachHang DAO = DaoKhachHang.getInstance();
   ArrayList<KHACHHANG> dsKHACHHANG = new ArrayList<KHACHHANG>();
	
	public KhachHangBUS() {
		dsKHACHHANG = DAO.selectAll();
	}

	public ArrayList<KHACHHANG> getdsKHACHHANG(){
		return dsKHACHHANG;
	}
	
	public void readDB() {
		dsKHACHHANG = DAO.selectAll();
	}
	
	public String getLastID() {
		return dsKHACHHANG.get(dsKHACHHANG.size()-1).getMaKH();
	}
	
	
	public String nextId(String id) {
		String result = null;
		String arr[] = id.split("H");
		int stt = Integer.parseInt(arr[1]) + 1;
		if (stt<=9) {
			result = "KH00" + String.valueOf(stt);
		}else {
			result = "KH0" + String.valueOf(stt);
		}
		
		return result;
	}
	
	public void xoa(String maNCC) {
		for (KHACHHANG KHACHHANG : dsKHACHHANG) {
			if(KHACHHANG.getMaKH().equals(maNCC) ) {
				KHACHHANG.setTrangThai(0);
				DAO.update(KHACHHANG);
				break;
			}
		}
	}
	
	public ArrayList<KHACHHANG> searchKHACHHANG(String input) {
		ArrayList<KHACHHANG> result = new ArrayList<KHACHHANG>();
		for (KHACHHANG KHACHHANG : dsKHACHHANG) {
			if ((KHACHHANG.getTenKH().toLowerCase().contains(input.toLowerCase()) && KHACHHANG.getTrangThai()!=0) || 
					(KHACHHANG.getMaKH().toLowerCase().contains(input.toLowerCase()) && KHACHHANG.getTrangThai()!=0)) {
				result.add(KHACHHANG);
			}
		}
		return result;
	}
	
		
	public void setDataToTable(ArrayList<KHACHHANG> data, MyTable table) {
		table.clear();
		table.getModel().setRowCount(0);
		for (KHACHHANG KHACHHANG : data) {
			if (KHACHHANG.getTrangThai()==1) {
				table.addRow(new String[] {KHACHHANG.getMaKH(),KHACHHANG.getTenKH(),KHACHHANG.getSdt(),KHACHHANG.getDiaChi()});
			}
		}
	}
	
	public KHACHHANG getKHACHHANG(String maNCC) {
		KHACHHANG result = null;
		for (KHACHHANG kh : dsKHACHHANG) {
			if (kh.getMaKH().equals(maNCC)) {
				result = kh;
			}
		}
		return result;
	}
	
	
	
}






