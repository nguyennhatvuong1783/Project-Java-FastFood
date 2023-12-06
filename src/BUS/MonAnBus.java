package BUS;

import java.util.ArrayList;

import DAO.DaoMonAn;
import DTO.MONAN;
import GiaoDienChuan.MyTable;

public class MonAnBus {
	DaoMonAn monAnDao = DaoMonAn.getInstance();
    ArrayList<MONAN> dsMonAn = new ArrayList<MONAN>();
	
	public MonAnBus() {
		dsMonAn = monAnDao.selectAll();
	}

	public ArrayList<MONAN> getDsMonAn(){
		return dsMonAn;
	}
	
	public void readDB() {
		dsMonAn = monAnDao.selectAll();
	}
	
	public String getLastID() {
		return dsMonAn.get(dsMonAn.size()-1).getMaMonAn();
	}
	
	
	public String nextId(String id) {
		String result = null;
		String arr[] = id.split("A");
		int stt = Integer.parseInt(arr[1]) + 1;
		if (stt<=9) {
			result = "MA00" + String.valueOf(stt);
		}else if (stt<=99) {
			result = "MA0" + String.valueOf(stt);
		}else {
			result = "MA" + String.valueOf(stt);
		}
		
		return result;
	}
	
	public void xoaMonAn(String maMA) {
		for (MONAN monan : dsMonAn) {
			if(monan.getMaMonAn().equals(maMA) ) {
				monan.setTrangThai(0);
				monAnDao.update(monan);
				break;
			}
		}
	}
	
	public ArrayList<MONAN> searchMonAn(String input) {
		ArrayList<MONAN> result = new ArrayList<MONAN>();
		for (MONAN monan : dsMonAn) {
			if ((monan.getTenMonAn().toLowerCase().contains(input.toLowerCase()) && monan.getTrangThai()!=0) || 
					(monan.getMaMonAn().toLowerCase().contains(input.toLowerCase()) && monan.getTrangThai()!=0)) {
				result.add(monan);
			}
		}
		return result;
	}
	
	public String getHinhAnh(String maMA) {
		for (MONAN monan : dsMonAn) {
			if (monan.getMaMonAn().equals(maMA)) {
				return monan.getHinhAnh();
			}
		}
		return null;
	}
	
	public void setDataToTable(ArrayList<MONAN> data, MyTable table) {
		table.clear();
		table.getModel().setRowCount(0);
		for (MONAN monan : data) {
			if (monan.getTrangThai()==1) {
				table.addRow(new String[] {monan.getMaMonAn(),monan.getTenMonAn(),monan.getDonViTinh(),String.valueOf(monan.getDonGia()),monan.getLoai()});
			}
		}
	}
	
	public MONAN getMonAn(String maMA) {
		MONAN result = null;
		for (MONAN monan : dsMonAn) {
			if (monan.getMaMonAn().equals(maMA)) {
				result = monan;
			}
		}
		return result;
	}
	
	public int getDonGia(String maMA) {
		int donGia = 0;
		for (MONAN monan : dsMonAn) {
			if (monan.getMaMonAn().equals(maMA)) {
				donGia = monan.getDonGia();
			}
		}
		return donGia;
	}
	
	
}
