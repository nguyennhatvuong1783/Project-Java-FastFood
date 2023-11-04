package BUS;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import DAO.DaoHoaDon;
import DTO.HOADON;
import GUI.ChiTietHoaDonGUI;
import GUI.MainLayoutGUI;
import GiaoDienChuan.FormatMoney;
import GiaoDienChuan.MyTable;

public class HoaDonBUS {
	private ArrayList<HOADON> DSHD;
	private DefaultTableModel dtm;
	private DaoHoaDon DAO = DaoHoaDon.getInstance();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public HoaDonBUS(MyTable pnlTable, JComboBox<String> cbTimKiem, JTextField txtTimKiem, JDateChooser dcTuNgay, JDateChooser dcDenNgay, 
			JTextField txtTongTienTu, JTextField txtTongTienDen, JButton btnLamMoi) {
		dtm = pnlTable.getModel();
		UploadData(pnlTable);
		showCTHD(pnlTable);
		TimKiem(pnlTable, cbTimKiem, txtTimKiem);
		FilterByNgayLap(pnlTable, dcTuNgay, dcDenNgay);
		FilterByTongTien(pnlTable, txtTongTienTu, txtTongTienDen);
		LamMoi(pnlTable, txtTimKiem, dcTuNgay, dcDenNgay, txtTongTienTu, txtTongTienDen, btnLamMoi);
	}
	
	public void UploadData(MyTable pnlTable) {
		DSHD = DAO.selectAll();
		
		dtm.setRowCount(0);
		for(HOADON hoadon : DSHD) {
			try {
				Date date = dateFormat.parse(hoadon.getNgayLap());
				dtm.addRow(new Object[]{hoadon.getMaHD(), DAO.selectTenKHbyMaKH(hoadon.getMaKH()), DAO.selectTenNVbyMaNV(hoadon.getMaNV()), 
						formatter.format(date.getTime()), FormatMoney.getFormat((long) hoadon.getTongTien())});
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void showCTHD(MyTable pnlTable) {
		JTable tableHD = pnlTable.getTable();
		tableHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(tableHD.getSelectedRow() != -1) {
					String MaHD = String.valueOf(tableHD.getValueAt(tableHD.getSelectedRow(), 0));
					ChiTietHoaDonGUI dialog = new ChiTietHoaDonGUI(MainLayoutGUI.f, true, MaHD);
					Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (d.width - dialog.getSize().width) / 2;
					int y = (d.height - dialog.getSize().height) / 2;
					dialog.setLocation(x, y);
	                dialog.setVisible(true);
	                tableHD.clearSelection();
				}
			}
		});
	}
	
	public void TimKiem(MyTable pnlTable, JComboBox<String> cbTimKiem, JTextField txtTimKiem) {
		cbTimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbTimKiem.getSelectedIndex() == 0) {
			        txtTimKiem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Tên khách hàng", 
			        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14)));
			        txtTimKiem.setText(null);
				}else {
			        txtTimKiem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Tên nhân viên", 
			        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14)));
			        txtTimKiem.setText(null);
				}
			}
		});
		
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				ArrayList<HOADON> ketqua = new ArrayList<HOADON>();

				if(cbTimKiem.getSelectedIndex() == 0) {
					for(HOADON hoadon : DSHD) {
						if(((DAO.selectTenKHbyMaKH(hoadon.getMaKH())).toLowerCase()).contains((txtTimKiem.getText()).toLowerCase())) {
							ketqua.add(hoadon);
						}
					}
					
					dtm.setRowCount(0);
					for(HOADON hoadon : ketqua) {
						try {
							Date date = dateFormat.parse(hoadon.getNgayLap());
							dtm.addRow(new Object[]{hoadon.getMaHD(), DAO.selectTenKHbyMaKH(hoadon.getMaKH()), 
									DAO.selectTenNVbyMaNV(hoadon.getMaNV()), formatter.format(date.getTime()), 
									FormatMoney.getFormat((long) hoadon.getTongTien())});
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				}else {
					for(HOADON hoadon : DSHD) {
						if(((DAO.selectTenNVbyMaNV(hoadon.getMaNV())).toLowerCase()).contains((txtTimKiem.getText()).toLowerCase())) {
							ketqua.add(hoadon);
						}
					}
					
					dtm.setRowCount(0);
					for(HOADON hoadon : ketqua) {
						try {
							Date date = dateFormat.parse(hoadon.getNgayLap());
							dtm.addRow(new Object[]{hoadon.getMaHD(), DAO.selectTenKHbyMaKH(hoadon.getMaKH()), 
									DAO.selectTenNVbyMaNV(hoadon.getMaNV()), formatter.format(date.getTime()), 
									FormatMoney.getFormat((long) hoadon.getTongTien())});
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				}
					
			}
		});
	}
	
	public void FilterByNgayLap(MyTable pnlTable, JDateChooser dcTuNgay, JDateChooser dcDenNgay) {
		dcTuNgay.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
	        @Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if("date".equals(e.getPropertyName())) {
	            	if(dcTuNgay.getDate() != null && dcDenNgay.getDate() != null) {
	            		ArrayList<HOADON> ketqua = new ArrayList<HOADON>();
		            	LocalDate dateTu = ((Date) e.getNewValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		            	LocalDate dateDen = dcDenNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		            	LocalDate d;
						for(HOADON hoadon : DSHD) {
							try {
								d = (dateFormat.parse(hoadon.getNgayLap())).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
								if(dateTu.compareTo(d) <= 0 && dateDen.compareTo(d) >= 0) {
									ketqua.add(hoadon);
								}
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}
						
						dtm.setRowCount(0);
						for(HOADON hoadon : ketqua) {
							try {
								Date date = dateFormat.parse(hoadon.getNgayLap());
								dtm.addRow(new Object[]{hoadon.getMaHD(), DAO.selectTenKHbyMaKH(hoadon.getMaKH()), 
										DAO.selectTenNVbyMaNV(hoadon.getMaNV()), formatter.format(date.getTime()), 
										FormatMoney.getFormat((long) hoadon.getTongTien())});
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}
	            	}
	            }
	        }
		});
		
		dcDenNgay.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
	        @Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if("date".equals(e.getPropertyName())) {
	            	if(dcTuNgay.getDate() != null && dcDenNgay.getDate() != null) {
	            		ArrayList<HOADON> ketqua = new ArrayList<HOADON>();
		            	LocalDate dateTu = dcTuNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		            	LocalDate dateDen = ((Date) e.getNewValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		            	LocalDate d;
						for(HOADON hoadon : DSHD) {
							try {
								d = (dateFormat.parse(hoadon.getNgayLap())).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
								if(dateTu.compareTo(d) <= 0 && dateDen.compareTo(d) >= 0) {
									ketqua.add(hoadon);
								}
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}
						
						dtm.setRowCount(0);
						for(HOADON hoadon : ketqua) {
							try {
								Date date = dateFormat.parse(hoadon.getNgayLap());
								dtm.addRow(new Object[]{hoadon.getMaHD(), DAO.selectTenKHbyMaKH(hoadon.getMaKH()), 
										DAO.selectTenNVbyMaNV(hoadon.getMaNV()), formatter.format(date.getTime()), 
										FormatMoney.getFormat((long) hoadon.getTongTien())});
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}
	            	}
	            }
	        }
	    });
	}
	
	public void FilterByTongTien(MyTable pnlTable, JTextField txtTongTienTu, JTextField txtTongTienDen) {
		txtTongTienTu.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		super.keyReleased(e);
        		String text = txtTongTienTu.getText();
        		text = text.replace(",", "");
        		if(isNumber(text)) {
            		long tienTu = Long.parseLong(text);
            		if(!text.isEmpty() && !(txtTongTienDen.getText()).isEmpty()) {
            			ArrayList<HOADON> ketqua = new ArrayList<HOADON>();
            			text = txtTongTienDen.getText();
                		text = text.replace(",", "");
                		long tienDen = Long.parseLong(text);
						for(HOADON hoadon : DSHD) {
							if(tienTu <= hoadon.getTongTien() && tienDen >= hoadon.getTongTien()) {
								ketqua.add(hoadon);
							}
						}
						
						dtm.setRowCount(0);
						for(HOADON hoadon : ketqua) {
							try {
								Date date = dateFormat.parse(hoadon.getNgayLap());
								dtm.addRow(new Object[]{hoadon.getMaHD(), DAO.selectTenKHbyMaKH(hoadon.getMaKH()), 
										DAO.selectTenNVbyMaNV(hoadon.getMaNV()), formatter.format(date.getTime()), 
										FormatMoney.getFormat((long) hoadon.getTongTien())});
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}
            		}
            		String money = FormatMoney.getFormat(tienTu);
            		money = money.replace(" VNĐ", "");
            		txtTongTienTu.setText(money);
        		}
        	}
		});
		
		txtTongTienDen.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		super.keyReleased(e);
        		String text = txtTongTienDen.getText();
        		text = text.replace(",", "");
        		if(isNumber(text)) {
            		long tienDen = Long.parseLong(text);
            		if(!text.isEmpty() && !(txtTongTienTu.getText()).isEmpty()) {
            			ArrayList<HOADON> ketqua = new ArrayList<HOADON>();
            			text = txtTongTienTu.getText();
                		text = text.replace(",", "");
                		long tienTu = Long.parseLong(text);
						for(HOADON hoadon : DSHD) {
							if(tienTu <= hoadon.getTongTien() && tienDen >= hoadon.getTongTien()) {
								ketqua.add(hoadon);
							}
						}
						
						dtm.setRowCount(0);
						for(HOADON hoadon : ketqua) {
							try {
								Date date = dateFormat.parse(hoadon.getNgayLap());
								dtm.addRow(new Object[]{hoadon.getMaHD(), DAO.selectTenKHbyMaKH(hoadon.getMaKH()), 
										DAO.selectTenNVbyMaNV(hoadon.getMaNV()), formatter.format(date.getTime()), 
										FormatMoney.getFormat((long) hoadon.getTongTien())});
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}
            		}
            		String money = FormatMoney.getFormat(tienDen);
            		money = money.replace(" VNĐ", "");
            		txtTongTienDen.setText(money);
        		}
        		
        		
        	}
		});
	}
	
	public void LamMoi(MyTable pnlTable, JTextField txtTimKiem, JDateChooser dcTuNgay, JDateChooser dcDenNgay, 
			JTextField txtTongTienTu, JTextField txtTongTienDen, JButton btnLamMoi) {
		btnLamMoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtTimKiem.setText(null);
				dcTuNgay.setDate(null);
				dcDenNgay.setDate(null);
				txtTongTienTu.setText(null);
				txtTongTienDen.setText(null);
				UploadData(pnlTable);
			}
		});
	}
	
	public boolean isNumber(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
