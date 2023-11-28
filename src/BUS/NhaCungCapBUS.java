/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;


import DAO.DaoNhaCungCap;
import DTO.NHACUNGCAP;

import GiaoDienChuan.MyTable;

import java.util.ArrayList;



/**
 *
 * @author HP
 */
public class NhaCungCapBUS {
   DaoNhaCungCap DAO = DaoNhaCungCap.getInstance();
   ArrayList<NHACUNGCAP> dsNHACUNGCAP = new ArrayList<NHACUNGCAP>();
	
	public NhaCungCapBUS() {
		dsNHACUNGCAP = DAO.selectAll();
	}

	public ArrayList<NHACUNGCAP> getDsNHACUNGCAP(){
		return dsNHACUNGCAP;
	}
	
	public void readDB() {
		dsNHACUNGCAP = DAO.selectAll();
	}
	
	public String getLastID() {
		return dsNHACUNGCAP.get(dsNHACUNGCAP.size()-1).getMaNCC();
	}
	
	public ArrayList<NHACUNGCAP> selectAllNCC(){
		DaoNhaCungCap nccdao = new DaoNhaCungCap();
		ArrayList<NHACUNGCAP> ncclist = nccdao.selectAll();
		return ncclist;
	}
	
	
	public String nextId(String id) {
		String result = null;
		String arr[] = id.split("C");
		int stt = Integer.parseInt(arr[2]) + 1;
		if (stt<=9) {
			result = "NCC00" + String.valueOf(stt);
		}else {
			result = "NCC0" + String.valueOf(stt);
		}
		
		return result;
	}
	
	public void xoa(String maNCC) {
		for (NHACUNGCAP NHACUNGCAP : dsNHACUNGCAP) {
			if(NHACUNGCAP.getMaNCC().equals(maNCC) ) {
				NHACUNGCAP.setTrangThai(0);
				DAO.update(NHACUNGCAP);
				break;
			}
		}
	}
	
	public ArrayList<NHACUNGCAP> searchNHACUNGCAP(String input) {
		ArrayList<NHACUNGCAP> result = new ArrayList<NHACUNGCAP>();
		for (NHACUNGCAP NHACUNGCAP : dsNHACUNGCAP) {
			if ((NHACUNGCAP.getTenNCC().toLowerCase().contains(input.toLowerCase()) && NHACUNGCAP.getTrangThai()!=0) || 
					(NHACUNGCAP.getMaNCC().toLowerCase().contains(input.toLowerCase()) && NHACUNGCAP.getTrangThai()!=0)) {
				result.add(NHACUNGCAP);
			}
		}
		return result;
	}
	
		
	public void setDataToTable(ArrayList<NHACUNGCAP> data, MyTable table) {
		table.clear();
		table.getModel().setRowCount(0);
		for (NHACUNGCAP NHACUNGCAP : data) {
			if (NHACUNGCAP.getTrangThai()==1) {
				table.addRow(new String[] {NHACUNGCAP.getMaNCC(),NHACUNGCAP.getTenNCC(),NHACUNGCAP.getSdt(),NHACUNGCAP.getDiaChi()});
			}
		}
	}
	
	public NHACUNGCAP getNHACUNGCAP(String maNCC) {
		NHACUNGCAP result = null;
		for (NHACUNGCAP NHACUNGCAP : dsNHACUNGCAP) {
			if (NHACUNGCAP.getMaNCC().equals(maNCC)) {
				result = NHACUNGCAP;
			}
		}
		return result;
	}
	
	
	
}






