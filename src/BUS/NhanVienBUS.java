/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;



import DAO.DaoNhanVien;
import DTO.NHANVIEN;

import GiaoDienChuan.MyTable;

import java.util.ArrayList;


public class NhanVienBUS {
   DaoNhanVien DAO = DaoNhanVien.getInstance();
   ArrayList<NHANVIEN> dsNHANVIEN = new ArrayList<NHANVIEN>();
	
	public NhanVienBUS() {
		dsNHANVIEN = DAO.selectAll();
	}

	public ArrayList<NHANVIEN> getDsNHANVIEN(){
		return dsNHANVIEN;
	}
	
	public void readDB() {
		dsNHANVIEN = DAO.selectAll();
	}
	
	public String getLastID() {
		return dsNHANVIEN.get(dsNHANVIEN.size()-1).getMaNV();
	}
	
	
	public String nextId(String id) {
		String result = null;
		String arr[] = id.split("V");
		int stt = Integer.parseInt(arr[1]) + 1;
		if (stt<=9) {
			result = "NV00" + String.valueOf(stt);
		}else {
			result = "NV0" + String.valueOf(stt);
		}
		
		return result;
	}
	
	public void xoa(String maNV) {
		for (NHANVIEN NHANVIEN : dsNHANVIEN) {
			if(NHANVIEN.getMaNV().equals(maNV) ) {
				NHANVIEN.setTrangThai(0);
				DAO.update(NHANVIEN);
				break;
			}
		}
	}
	
	public ArrayList<NHANVIEN> searchNHANVIEN(String input) {
		ArrayList<NHANVIEN> result = new ArrayList<NHANVIEN>();
		for (NHANVIEN NHANVIEN : dsNHANVIEN) {
			if ((NHANVIEN.getTenNV().toLowerCase().contains(input.toLowerCase()) && NHANVIEN.getTrangThai()!=0) || 
					(NHANVIEN.getMaNV().toLowerCase().contains(input.toLowerCase()) && NHANVIEN.getTrangThai()!=0)) {
				result.add(NHANVIEN);
			}
		}
		return result;
	}
	
		
	public void setDataToTable(ArrayList<NHANVIEN> data, MyTable table) {
		table.clear();
		table.getModel().setRowCount(0);
		for (NHANVIEN NHANVIEN : data) {
			if (NHANVIEN.getTrangThai()==1) {
				table.addRow(new String[] {NHANVIEN.getMaNV(),NHANVIEN.getTenNV(),NHANVIEN.getGioiTinh(),NHANVIEN.getNgaySinh(),NHANVIEN.getSdt(),NHANVIEN.getDiaChi()});
			}
		}
	}
	
	public NHANVIEN getNHANVIEN(String maNV) {
		NHANVIEN result = null;
		for (NHANVIEN NHANVIEN : dsNHANVIEN) {
			if (NHANVIEN.getMaNV().equals(maNV)) {
				result = NHANVIEN;
			}
		}
		return result;
	}
	
	
	
}






