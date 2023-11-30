package WorkExcel;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import BUS.MonAnBus;
import DAO.DaoKhachHang;
import DAO.DaoMonAn;
import DAO.DaoNhaCungCap;
import DAO.DaoNhanVien;
import DTO.KHACHHANG;
import DTO.MONAN;
import DTO.NHACUNGCAP;
import DTO.NHANVIEN;
import GiaoDienChuan.MyTable;

public class DocExcel {
	FileDialog fd = new FileDialog(new JFrame(),"Đọc excel", FileDialog.LOAD );
	
	public DocExcel() {
	}
	
	private String getFile() {
		fd.setFile("*.xls");
		fd.setVisible(true);
		String url = fd.getDirectory() + fd.getFile();
	    if (url.equals("nullnull")) {
	       return null;
	    }
	    return url;
	}
	
	// Đọc file excel món ăn
	public void docFileExcelMonAn() {
		fd.setTitle("Nhập dữ liệu món ăn");
		String url = getFile();
		if (url == null) {
			return;
		}
		
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(url));
			
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row1 = rowIterator.next();
			
			String hanhDongKhiTrung = "";
	        int countThem = 0;
	        int countGhiDe = 0;
	        int countBoQua = 0;
	        
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                
                while (cellIterator.hasNext()) {
                	 int stt = (int) cellIterator.next().getNumericCellValue();
                     String maMA = cellIterator.next().getStringCellValue();
                     String tenMA = cellIterator.next().getStringCellValue();
                     String donViTinh = cellIterator.next().getStringCellValue();
                     String loai = cellIterator.next().getStringCellValue();
                     int soLuong = (int) cellIterator.next().getNumericCellValue();
                     int donGia = (int) cellIterator.next().getNumericCellValue();
                     
                     MONAN monanOld = DaoMonAn.getInstance().selectById(new MONAN(maMA, null, 0, maMA, 0, null, null, 0));
                     if (monanOld != null) {
						if (!hanhDongKhiTrung.contains("Tất cả")) {
							MyTable table = new MyTable();
							table.setHeaders(new String[] {" ","Mã món ăn","Tên món ăn","Đơn vị tính","Loại","Số lượng","Đơn giá"});
							table.addRow(new String[] {
									"Cũ",
									monanOld.getMaMonAn(),
									monanOld.getTenMonAn(),
									monanOld.getDonViTinh(),
									monanOld.getLoai(),
									String.valueOf(monanOld.getSoLuong()),
								    String.valueOf(monanOld.getDonGia())
						    });
							table.addRow(new String[] {
									"Mới",
									maMA,
									tenMA,
									donViTinh,
									loai,
									String.valueOf(soLuong),
									String.valueOf(donGia)
							});
							table.resizeColumnWidth();
							
							 MyJOptionPane mop = new MyJOptionPane(table, hanhDongKhiTrung);
	                         hanhDongKhiTrung = mop.getAnswer();
						}
						if (hanhDongKhiTrung.contains("Ghi đè")) {
							MONAN monanNew = new MONAN(maMA, tenMA, soLuong, donViTinh, donGia, monanOld.getHinhAnh(), loai, 1);
							DaoMonAn.getInstance().update(monanNew);
							countGhiDe++;
						}else {
							countBoQua++;
						}
					}else {
						MONAN monan = new MONAN(maMA, tenMA, soLuong, donViTinh, donGia, " ", loai, 1);
						DaoMonAn.getInstance().insert(monan);
						countThem++;
					}
                     
				}

			}
	        JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + e.getMessage());
		}finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
	}
	
	// Đọc file excel nhân viên
	public void docFileExcelNhanVien() {
		fd.setTitle("Nhập dữ liệu nhân viên");
		String url = getFile();
		if (url == null) {
			return;
		}
		
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(url));
			
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row1 = rowIterator.next();
			
			String hanhDongKhiTrung = "";
	        int countThem = 0;
	        int countGhiDe = 0;
	        int countBoQua = 0;
	        
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                
                while (cellIterator.hasNext()) {
                	 int stt = (int) cellIterator.next().getNumericCellValue();
                     String maNV = cellIterator.next().getStringCellValue();
                     String tenNV = cellIterator.next().getStringCellValue();
                     String gioiTinh = cellIterator.next().getStringCellValue();
                     String ngaySinh = cellIterator.next().getStringCellValue();
                     String SĐT =  cellIterator.next().getStringCellValue();
                     String diaChi =  cellIterator.next().getStringCellValue();
                     
                     NHANVIEN nhanvienOld = DaoNhanVien.getInstance().selectById(new NHANVIEN(maNV, null, null, null, null, null, 1));
                     if (nhanvienOld != null) {
						if (!hanhDongKhiTrung.contains("Tất cả")) {
							MyTable table = new MyTable();
							table.setHeaders(new String[] {" ","Mã nhân viên","Tên nhân viên","Giới tính","Ngày sinh","Địa chỉ","Số điện thoại"});
							table.addRow(new String[] {
									"Cũ",
									nhanvienOld.getMaNV(),
									nhanvienOld.getTenNV(),
									nhanvienOld.getGioiTinh(),
									nhanvienOld.getNgaySinh(),
									nhanvienOld.getDiaChi(),
									nhanvienOld.getSdt()
								
						    });
							table.addRow(new String[] {
									"Mới",
									maNV,
									tenNV,
									gioiTinh,
									ngaySinh,
									diaChi,
									SĐT
							});
							table.resizeColumnWidth();
							
							 MyJOptionPane mop = new MyJOptionPane(table, hanhDongKhiTrung);
	                         hanhDongKhiTrung = mop.getAnswer();
						}
						if (hanhDongKhiTrung.contains("Ghi đè")) {
						    NHANVIEN nhanvienNew = new NHANVIEN(maNV, tenNV, gioiTinh, ngaySinh, diaChi, SĐT, 1);
							DaoNhanVien.getInstance().update(nhanvienNew);
							countGhiDe++;
						}else {
							countBoQua++;
						}
					}else {
						  NHANVIEN nhanvien = new NHANVIEN(maNV, tenNV, gioiTinh, ngaySinh, diaChi, SĐT, 1);
						   DaoNhanVien.getInstance().insert(nhanvien);
						   countThem++;
					}
                     
				}

			}
	        JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + e.getMessage());
		}finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
	}

	// Đọc file excel khách hàng
	public void docFileExcelKhachHang() {
		fd.setTitle("Nhập dữ liệu khách hàng");
		String url = getFile();
		if (url == null) {
			return;
		}
		
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(url));
			
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row1 = rowIterator.next();
			
			String hanhDongKhiTrung = "";
	        int countThem = 0;
	        int countGhiDe = 0;
	        int countBoQua = 0;
	        
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                
                while (cellIterator.hasNext()) {
                	 int stt = (int) cellIterator.next().getNumericCellValue();
                     String maKH = cellIterator.next().getStringCellValue();
                     String tenKH = cellIterator.next().getStringCellValue();
                     String SĐT =  cellIterator.next().getStringCellValue();
                     String diaChi =  cellIterator.next().getStringCellValue();
                     
                     KHACHHANG khachhangOld = DaoKhachHang.getInstance().selectById(new KHACHHANG(maKH, null, null, null, 1));
                     if (khachhangOld != null) {
						if (!hanhDongKhiTrung.contains("Tất cả")) {
							MyTable table = new MyTable();
							table.setHeaders(new String[] {" ","Mã khách hàng","Tên khách hàng","Địa chỉ","Số điện thoại"});
							table.addRow(new String[] {
									"Cũ",
								    khachhangOld.getMaKH(),
									khachhangOld.getTenKH(),							
									khachhangOld.getDiaChi(),
									khachhangOld.getSdt()
								
						    });
							table.addRow(new String[] {
									"Mới",
									maKH,
									tenKH,
									diaChi,
									SĐT
							});
							table.resizeColumnWidth();
							
							 MyJOptionPane mop = new MyJOptionPane(table, hanhDongKhiTrung);
	                         hanhDongKhiTrung = mop.getAnswer();
						}
						if (hanhDongKhiTrung.contains("Ghi đè")) {
						    KHACHHANG khachHangNew = new KHACHHANG(maKH, tenKH, SĐT, diaChi, 1);
							DaoKhachHang.getInstance().update(khachHangNew);
							countGhiDe++;
						}else {
							countBoQua++;
						}
					}else {
						  KHACHHANG khachHangNew = new KHACHHANG(maKH, tenKH, SĐT, diaChi, 1);
						  DaoKhachHang.getInstance().insert(khachHangNew);
						   countThem++;
					}
                     
				}

			}
	        JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + e.getMessage());
		}finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
	}

	// Đọc file excel nhà cung cấp
	public void docFileExcelNhaCungCap() {
		fd.setTitle("Nhập dữ liệu nhà cung cấp");
		String url = getFile();
		if (url == null) {
			return;
		}
		
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(url));
			
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row1 = rowIterator.next();
			
			String hanhDongKhiTrung = "";
	        int countThem = 0;
	        int countGhiDe = 0;
	        int countBoQua = 0;
	        
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                
                while (cellIterator.hasNext()) {
                	 int stt = (int) cellIterator.next().getNumericCellValue();
                     String maNCC = cellIterator.next().getStringCellValue();
                     String tenNCC = cellIterator.next().getStringCellValue();
                     String SĐT =  cellIterator.next().getStringCellValue();
                     String diaChi =  cellIterator.next().getStringCellValue();
                     
                     NHACUNGCAP nccOld = DaoNhaCungCap.getInstance().selectById(new NHACUNGCAP(maNCC, null, null, null, 1));
                     if (nccOld != null) {
						if (!hanhDongKhiTrung.contains("Tất cả")) {
							MyTable table = new MyTable();
							table.setHeaders(new String[] {" ","Mã nhà cung cấp","Tên nhà cung cấp","Địa chỉ","Số điện thoại"});
							table.addRow(new String[] {
									"Cũ",
								    nccOld.getMaNCC(),
									nccOld.getTenNCC(),							
									nccOld.getDiaChi(),
									nccOld.getSdt()
								
						    });
							table.addRow(new String[] {
									"Mới",
									maNCC,
									tenNCC,
									diaChi,
									SĐT
							});
							table.resizeColumnWidth();
							
							 MyJOptionPane mop = new MyJOptionPane(table, hanhDongKhiTrung);
	                         hanhDongKhiTrung = mop.getAnswer();
						}
						if (hanhDongKhiTrung.contains("Ghi đè")) {
						   NHACUNGCAP nccNew = new NHACUNGCAP(maNCC, tenNCC, SĐT, diaChi, 1);
						   DaoNhaCungCap.getInstance().update(nccNew);
							countGhiDe++;
						}else {
							countBoQua++;
						}
					}else {
						 NHACUNGCAP nccNew = new NHACUNGCAP(maNCC, tenNCC, SĐT, diaChi, 1);
						 DaoNhaCungCap.getInstance().insert(nccNew);
						   countThem++;
					}
                     
				}

			}
	        JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + e.getMessage());
		}finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
	}

}
