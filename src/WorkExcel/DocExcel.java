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
import DAO.DaoMonAn;
import DTO.MONAN;
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

}
