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
import BUS.ChiTietPhieuNhapBUS;
import BUS.MonAnBus;
import BUS.NguyenLieuBUS;
import BUS.PhieuNhapBUS;
import DAO.DaoChiTietHoaDon;
import DAO.DaoHoaDon;
import DAO.DaoKhachHang;
import DAO.DaoMonAn;
import DAO.DaoNguyenLieu;
import DAO.DaoNhaCungCap;
import DAO.DaoNhanVien;
import DAO.DaoPhieuNhap;
import DTO.CHITIETHOADON;
import DTO.CHITIETPHIEUNHAP;
import DTO.HOADON;
import DTO.KHACHHANG;
import DTO.MONAN;
import DTO.NGUYENLIEU;
import DTO.NHACUNGCAP;
import DTO.NHANVIEN;
import DTO.PHIEUNHAP;

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
    			row.createCell(3,CellType.STRING).setCellValue("Đơn vị tính");
    			row.createCell(4,CellType.STRING).setCellValue("Loại");
    			row.createCell(5,CellType.NUMERIC).setCellValue("Số lượng");
    			row.createCell(6,CellType.NUMERIC).setCellValue("Đơn giá");
    			
    			for (MONAN monan : monans) {
    				rowNum++;
    				row = sheet.createRow(rowNum);
    				
    				row.createCell(0, CellType.NUMERIC).setCellValue(rowNum);
    				row.createCell(1,CellType.STRING).setCellValue(monan.getMaMonAn());
    				row.createCell(2,CellType.STRING).setCellValue(monan.getTenMonAn());
    				row.createCell(3,CellType.STRING).setCellValue(monan.getDonViTinh());
    				row.createCell(4,CellType.STRING).setCellValue(monan.getLoai());
    				row.createCell(5,CellType.NUMERIC).setCellValue(monan.getSoLuong());
    				row.createCell(6,CellType.NUMERIC).setCellValue(monan.getDonGia());				
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
	
	// xuất file hóa đơn
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
	
	// Xuất file excel nhân viên
	public void xuatFileExcelNhanVien() {
		fd.setTitle("Xuất dữ liệu danh sách nhân viên");
		String tenFile = JOptionPane.showInputDialog(null,"Tên file","Thông báo", JOptionPane.PLAIN_MESSAGE);
        if (tenFile != null) {
        	String url = getFile(tenFile);
    		if (url == null) {
    			return;
    		}
    		
    		FileOutputStream outputStream = null;
    		try {
    			HSSFWorkbook workbook = new HSSFWorkbook();
    			HSSFSheet sheet = workbook.createSheet("Nhân viên");
    			ArrayList<NHANVIEN> nhanviens = DaoNhanVien.getInstance().selectAll();
    			
    			int rowNum = 0;
    			Row row = sheet.createRow(rowNum);
    			
    			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
    			row.createCell(1,CellType.STRING).setCellValue("Mã nhân viên");
    			row.createCell(2,CellType.STRING).setCellValue("Họ và tên");
    			row.createCell(3,CellType.STRING).setCellValue("Giới tính");
    			row.createCell(4, CellType.STRING).setCellValue("Ngày sinh");
    			row.createCell(5,CellType.STRING).setCellValue("Số điện thoại");
    			row.createCell(6,CellType.STRING).setCellValue("Địa chỉ");
    			
    			for (NHANVIEN nhanvien : nhanviens) {
    				rowNum++;
    				row = sheet.createRow(rowNum);
    				
    				row.createCell(0, CellType.NUMERIC).setCellValue(rowNum);
    				row.createCell(1,CellType.STRING).setCellValue(nhanvien.getMaNV());
    				row.createCell(2,CellType.STRING).setCellValue(nhanvien.getTenNV());
    				row.createCell(3,CellType.STRING).setCellValue(nhanvien.getGioiTinh());
    				row.createCell(4,CellType.STRING).setCellValue(nhanvien.getNgaySinh());
    				row.createCell(5,CellType.STRING).setCellValue(nhanvien.getSdt());		
    				row.createCell(6,CellType.STRING).setCellValue(nhanvien.getSdt());	
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

	// Xuất file excel khách hàng
	public void xuatFileExcelKhachHang() {
		fd.setTitle("Xuất dữ liệu danh sách khách hàng");
		String tenFile = JOptionPane.showInputDialog(null,"Tên file","Thông báo", JOptionPane.PLAIN_MESSAGE);
        if (tenFile != null) {
        	String url = getFile(tenFile);
    		if (url == null) {
    			return;
    		}
    		
    		FileOutputStream outputStream = null;
    		try {
    			HSSFWorkbook workbook = new HSSFWorkbook();
    			HSSFSheet sheet = workbook.createSheet("Khách hàng");
    			ArrayList<KHACHHANG> khachhangs = DaoKhachHang.getInstance().selectAll();
    			
    			int rowNum = 0;
    			Row row = sheet.createRow(rowNum);
    			
    			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
    			row.createCell(1,CellType.STRING).setCellValue("Mã khách hàng");
    			row.createCell(2,CellType.STRING).setCellValue("Họ và tên");
    			row.createCell(3,CellType.STRING).setCellValue("Số điện thoại");
    			row.createCell(4,CellType.STRING).setCellValue("Địa chỉ");

    			
    			for (KHACHHANG khachhang : khachhangs) {
    				rowNum++;
    				row = sheet.createRow(rowNum);
    				
    				row.createCell(0, CellType.NUMERIC).setCellValue(rowNum);
    				row.createCell(1,CellType.STRING).setCellValue(khachhang.getMaKH());
    				row.createCell(2,CellType.STRING).setCellValue(khachhang.getTenKH());
    				row.createCell(3,CellType.STRING).setCellValue(khachhang.getSdt());
    				row.createCell(4,CellType.STRING).setCellValue(khachhang.getDiaChi());			
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
	
	// Xuất file excel nhà cung cấp
	public void xuatFileExcelNhaCungCap() {
		fd.setTitle("Xuất dữ liệu danh sách nhà cung cấp");
		String tenFile = JOptionPane.showInputDialog(null,"Tên file","Thông báo", JOptionPane.PLAIN_MESSAGE);
        if (tenFile != null) {
        	String url = getFile(tenFile);
    		if (url == null) {
    			return;
    		}
    		
    		FileOutputStream outputStream = null;
    		try {
    			HSSFWorkbook workbook = new HSSFWorkbook();
    			HSSFSheet sheet = workbook.createSheet("Nhà cung cấp");
    			ArrayList<NHACUNGCAP> nhacungcaps = DaoNhaCungCap.getInstance().selectAll();
    			
    			int rowNum = 0;
    			Row row = sheet.createRow(rowNum);
    			
    			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
    			row.createCell(1,CellType.STRING).setCellValue("Mã nhà cung cấp");
    			row.createCell(2,CellType.STRING).setCellValue("Tên nhà cung cấp");
    			row.createCell(3,CellType.STRING).setCellValue("Số điện thoại");
    			row.createCell(4,CellType.NUMERIC).setCellValue("Địa chỉ");
    			
    			for (NHACUNGCAP nhacungcap : nhacungcaps) {
    				rowNum++;
    				row = sheet.createRow(rowNum);
    				
    				row.createCell(0, CellType.NUMERIC).setCellValue(rowNum);
    				row.createCell(1,CellType.STRING).setCellValue(nhacungcap.getMaNCC());
    				row.createCell(2,CellType.STRING).setCellValue(nhacungcap.getTenNCC());
    				row.createCell(3,CellType.STRING).setCellValue(nhacungcap.getSdt());
    				row.createCell(4,CellType.STRING).setCellValue(nhacungcap.getDiaChi());		
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
	
	// Xuất file nguyên liệu
	public void xuatFileExcelNguyenLieu() {
		fd.setTitle("Xuất dữ liệu danh sách nguyên liệu");
		String tenFile = JOptionPane.showInputDialog(null,"Tên file","Thông báo", JOptionPane.PLAIN_MESSAGE);
		 if (tenFile != null) {
	        	String url = getFile(tenFile);
	    		if (url == null) {
	    			return;
	    		}
	    		
	    		FileOutputStream outputStream = null;
	    		try {
	    			HSSFWorkbook workbook = new HSSFWorkbook();
	    			HSSFSheet sheet = workbook.createSheet("Nguyên liệu");
	    			ArrayList<NGUYENLIEU> nguyenlieus = DaoNguyenLieu.getInstance().selectAll();
	    			
	    			int rowNum = 0;
	    			Row row = sheet.createRow(rowNum);
	    			
	    			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
	    			row.createCell(1,CellType.STRING).setCellValue("Mã nguyên liệu");
	    			row.createCell(2,CellType.STRING).setCellValue("Tên nguyên liệu");
	    			row.createCell(3,CellType.STRING).setCellValue("Đơn vị tính");
	    			row.createCell(4,CellType.STRING).setCellValue("Loại");
	    			row.createCell(5,CellType.NUMERIC).setCellValue("Số lượng");
	    			row.createCell(6,CellType.NUMERIC).setCellValue("Đơn giá");
	    			
	    			for (NGUYENLIEU nguyenlieu : nguyenlieus) {
	    				rowNum++;
	    				row = sheet.createRow(rowNum);
	    				
	    				row.createCell(0, CellType.NUMERIC).setCellValue(rowNum);
	    				row.createCell(1,CellType.STRING).setCellValue(nguyenlieu.getMaNL());
	    				row.createCell(2,CellType.STRING).setCellValue(nguyenlieu.getTenNL());
	    				row.createCell(3,CellType.STRING).setCellValue(nguyenlieu.getDonViTinh());
	    				row.createCell(4,CellType.STRING).setCellValue(nguyenlieu.getLoaiNL());
	    				row.createCell(5,CellType.NUMERIC).setCellValue(nguyenlieu.getSoLuong());
	    				row.createCell(6,CellType.NUMERIC).setCellValue(nguyenlieu.getDonGia());				
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
	
	// Xuất file phiếu nhập
	public void xuatFileExcelPhieuNhap() {
		fd.setTitle("Xuất dữ liệu phiếu nhập");
		String tenFile = JOptionPane.showInputDialog(null,"Tên file","Thông báo", JOptionPane.PLAIN_MESSAGE);
		if (tenFile != null) {
			String url = getFile(tenFile);
			if (url == null) {
				return;
			}
			
			FileOutputStream outputStream = null;
			try {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Phiếu nhập");
				ArrayList<PHIEUNHAP> phieunhaps = DaoPhieuNhap.getInstance().selectAll();
				ChiTietPhieuNhapBUS chiTietPhieuNhapBUS = new ChiTietPhieuNhapBUS();
				NguyenLieuBUS nguyenLieuBUS = new NguyenLieuBUS();
				
				int rowNum = 0;
				int sttPhieuNhap = 0;
				Row row = sheet.createRow(rowNum);
				
				row.createCell(0, CellType.NUMERIC).setCellValue("STT");
				row.createCell(1,CellType.STRING).setCellValue("Mã phiếu nhập");
				row.createCell(2,CellType.STRING).setCellValue("Mã nhà cung cấp");
				row.createCell(3,CellType.STRING).setCellValue("Nhân viên lập phiếu nhập");
				row.createCell(4,CellType.STRING).setCellValue("Ngày lập");
				row.createCell(5,CellType.NUMERIC).setCellValue("Tổng tiền");
				
				row.createCell(6, CellType.STRING).setCellValue("Nguyên liệu");
		        row.createCell(7, CellType.NUMERIC).setCellValue("Số lượng");
		        row.createCell(8, CellType.NUMERIC).setCellValue("Đơn giá");
		        row.createCell(9, CellType.NUMERIC).setCellValue("Thành tiền");
				
				for (PHIEUNHAP phieunhap : phieunhaps) {
					rowNum++;
					sttPhieuNhap++;
					row = sheet.createRow(rowNum);
					
					row.createCell(0, CellType.NUMERIC).setCellValue(sttPhieuNhap);
					row.createCell(1,CellType.STRING).setCellValue(phieunhap.getMaPN());
					row.createCell(2,CellType.STRING).setCellValue(phieunhap.getMaNCC());
					row.createCell(3,CellType.STRING).setCellValue(phieunhap.getMaNV());
					row.createCell(4,CellType.STRING).setCellValue(phieunhap.getNgayNhap());
					row.createCell(5,CellType.NUMERIC).setCellValue(phieunhap.getTongTien());
					
					for(CHITIETPHIEUNHAP ctphieuNhap : chiTietPhieuNhapBUS.getAllCTPhieuNhap(phieunhap.getMaPN())) {
						rowNum++;
						row = sheet.createRow(rowNum);
						
						String maNL = ctphieuNhap.getMaNL();
		
						row.createCell(6, CellType.STRING).setCellValue(maNL+" - "+nguyenLieuBUS.getTenNL(maNL));
				        row.createCell(7, CellType.NUMERIC).setCellValue(ctphieuNhap.getSoLuong());
				        row.createCell(8, CellType.NUMERIC).setCellValue(nguyenLieuBUS.getDonGia(maNL));
				        row.createCell(9, CellType.NUMERIC).setCellValue(ctphieuNhap.getSoLuong() * nguyenLieuBUS.getDonGia(maNL));
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
