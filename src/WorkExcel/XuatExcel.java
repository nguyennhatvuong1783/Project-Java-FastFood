package WorkExcel;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import BUS.ChiTietHoaDonBUS;
import BUS.MonAnBus;
import DAO.DaoChiTietHoaDon;
import DAO.DaoHoaDon;
import DAO.DaoKhachHang;
import DAO.DaoMonAn;
import DAO.DaoNhanVien;
import DTO.CHITIETHOADON;
import DTO.HOADON;
import DTO.MONAN;

public class XuatExcel {
	FileDialog fd = new FileDialog(new JFrame(), "Xuất file excel", FileDialog.SAVE);
	
	public XuatExcel() {
	}
	
	private String getFile(String name) {
		fd.setFile(name+".xls");
		fd.setVisible(true);
		String url = fd.getDirectory() + fd.getFile();
		if (url.equals("nullnull ")) {
			return null ;
		}
		return url;
	}
	
	// Xuất file món ăn
	public void xuatFileExcelMonAn() {
		fd.setTitle("Xuất dữ liệu danh sách món ăn");
		String tenFile = JOptionPane.showInputDialog(null,"Tên file","Thông báo", JOptionPane.PLAIN_MESSAGE);        if (tenFile != null) {
        	String url = getFile(tenFile);
    		if (url == null) {
    			return;
    		}
    		
    		FileOutputStream outputStream = null;
    		try {
    			HSSFWorkbook workbook = new HSSFWorkbook();
    			HSSFSheet sheet = workbook.createSheet("Món ăn");
    			ArrayList<MONAN> monans = DaoMonAn.getInstance().selectAll();
    			
    			int rowNum = 0;
    			Row row = sheet.createRow(rowNum);
    			
    			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
    			row.createCell(1,CellType.STRING).setCellValue("Mã món ăn");
    			row.createCell(2,CellType.STRING).setCellValue("Tên món ăn");
    			row.createCell(3,CellType.STRING).setCellValue("Loại");
    			row.createCell(4,CellType.NUMERIC).setCellValue("Số lượng");
    			row.createCell(5,CellType.NUMERIC).setCellValue("Đơn giá");
    			
    			for (MONAN monan : monans) {
    				rowNum++;
    				row = sheet.createRow(rowNum);
    				
    				row.createCell(0, CellType.NUMERIC).setCellValue(rowNum);
    				row.createCell(1,CellType.STRING).setCellValue(monan.getMaMonAn());
    				row.createCell(2,CellType.STRING).setCellValue(monan.getTenMonAn());
    				row.createCell(3,CellType.STRING).setCellValue(monan.getLoai());
    				row.createCell(4,CellType.NUMERIC).setCellValue(monan.getSoLuong());
    				row.createCell(5,CellType.NUMERIC).setCellValue(monan.getDonGia());				
    			}
    			for (int i = 0; i < rowNum; i++) {
    				sheet.autoSizeColumn(i);
    			}
    			
    			File file = new File(url);
    			if (file != null) {
    				file.getParentFile().mkdirs();
    			}
    			outputStream = new FileOutputStream(file);
    			workbook.write(outputStream);
    			
    			JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());
    		} catch (FileNotFoundException ex) {
    			  Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
    		} catch (IOException ex) {
    			 Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
    		} finally {
    			try {
    				if (outputStream != null) {
    					outputStream.close();
    				}
    			} catch (IOException ex) {
    				 Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
    			}
    		}
		}
		
	}
	
	// xuất file hóa đơn "Mã hóa đơn, Tên khách hàng, Nhân viên lập hóa đơn, Ngày lập, Tổng tiền"
	public void xuatFileExcelHoaDon() {
		fd.setTitle("Xuất dữ liệu hóa đơn");
		String tenFile = JOptionPane.showInputDialog(null,"Tên file","Thông báo", JOptionPane.PLAIN_MESSAGE);
		if (tenFile != null) {
			String url = getFile(tenFile);
			if (url == null) {
				return;
			}
			
			FileOutputStream outputStream = null;
			try {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Hóa đơn");
				ArrayList<HOADON> hoadons = DaoHoaDon.getInstance().selectAll();
				ChiTietHoaDonBUS chiTietHoaDonBUS = new ChiTietHoaDonBUS();
				MonAnBus monAnBus = new MonAnBus();
				
				
				int rowNum = 0;
				int sttHoaDon = 0;
				Row row = sheet.createRow(rowNum);
				
				row.createCell(0, CellType.NUMERIC).setCellValue("STT");
				row.createCell(1,CellType.STRING).setCellValue("Mã hóa đơn");
				row.createCell(2,CellType.STRING).setCellValue("Tên khách hàng");
				row.createCell(3,CellType.STRING).setCellValue("Nhân viên lập hóa đơn");
				row.createCell(4,CellType.STRING).setCellValue("Ngày lập");
				row.createCell(5,CellType.STRING).setCellValue("Mã khuyến mãi");
				row.createCell(6,CellType.NUMERIC).setCellValue("Tổng tiền");
				
				row.createCell(7, CellType.STRING).setCellValue("Sản phẩm");
		        row.createCell(8, CellType.NUMERIC).setCellValue("Số lượng");
		        row.createCell(9, CellType.NUMERIC).setCellValue("Đơn giá");
		        row.createCell(10, CellType.NUMERIC).setCellValue("Thành tiền");
				
				for (HOADON hoadon : hoadons) {
					rowNum++;
					sttHoaDon++;
					row = sheet.createRow(rowNum);
					
					row.createCell(0, CellType.NUMERIC).setCellValue(sttHoaDon);
					row.createCell(1,CellType.STRING).setCellValue(hoadon.getMaHD());
					row.createCell(2,CellType.STRING).setCellValue(hoadon.getMaKH());
					row.createCell(3,CellType.STRING).setCellValue(hoadon.getMaNV());
					row.createCell(4,CellType.STRING).setCellValue(hoadon.getNgayLap());
					row.createCell(5,CellType.STRING).setCellValue(hoadon.getMaKM());
					row.createCell(6,CellType.NUMERIC).setCellValue(hoadon.getTongTien());
					
					for(CHITIETHOADON cthoaDon : chiTietHoaDonBUS.getAllChiTietHD(hoadon.getMaHD())) {
						rowNum++;
						row = sheet.createRow(rowNum);
						
						String maMA = cthoaDon.getMaMonAn();
		
						row.createCell(7, CellType.STRING).setCellValue(maMA+" - "+monAnBus.getMonAn(maMA).getTenMonAn());
				        row.createCell(8, CellType.NUMERIC).setCellValue(cthoaDon.getSoLuong());
				        row.createCell(9, CellType.NUMERIC).setCellValue(monAnBus.getDonGia(maMA));
				        row.createCell(10, CellType.NUMERIC).setCellValue(cthoaDon.getSoLuong() * monAnBus.getDonGia(maMA));
					}
					
				}
				for (int i = 0; i < rowNum; i++) {
					sheet.autoSizeColumn(i);
				}
				
				File file = new File(url);
				file.getParentFile().mkdirs();
				outputStream = new FileOutputStream(file);
				workbook.write(outputStream);
				
				JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());
			} catch (FileNotFoundException ex) {
				  Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				 Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					if (outputStream != null) {
						outputStream.close();
					}
				} catch (IOException ex) {
					 Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

}
