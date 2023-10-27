package BUS;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import GUI.BanHangGUI;
import GUI.HoaDonGUI;
import GUI.KhachHangGUI;
import GUI.KhuyenMaiGUI;
import GUI.MonAnGUI;
import GUI.NguyenLieuGUI;
import GUI.NhaCungCapGUI;
import GUI.NhanVienGUI;
import GUI.NhapHangGUI;
import GUI.PhieuNhapGUI;
import GUI.TaiKhoanGUI;

public class MainLayoutBUS {
	private JLabel lblList[];
	private int flag[] = new int[9];
	private JPanel pnlContent;
	private CardLayout cardLayout = new CardLayout();
	
	public MainLayoutBUS(JLabel lblBanHang, JLabel lblNhapHang, JLabel lblSanPham, 
			JLabel lblNhanVien, JLabel lblKhachHang, JLabel lblNhaCungCap, JLabel lblKhuyenMai, 
			JLabel lblTaiKhoan, JLabel lblThongKe, JPanel pnlContent, JTabbedPane tpBanHang, 
			JTabbedPane tpNhapHang, JTabbedPane tpSanPham) {
		JLabel list[] = {lblBanHang, lblNhapHang, lblSanPham, lblNhanVien, lblKhachHang, 
				lblNhaCungCap, lblKhuyenMai, lblTaiKhoan, lblThongKe};
		lblList = list;
		this.pnlContent = pnlContent;
		this.pnlContent.setLayout(cardLayout);
		EventBanHang(lblBanHang, tpBanHang);
		EventNhapHang(lblNhapHang, tpNhapHang);
		EventSanPham(lblSanPham, tpSanPham);
		EventNhanVien(lblNhanVien);
		EventKhachHang(lblKhachHang);
		EventNhaCungCap(lblNhaCungCap);
		EventKhuyenMai(lblKhuyenMai);
		EventTaiKhoan(lblTaiKhoan);
		EventThongKe(lblThongKe);
		pnlContent.add(new JPanel(), "null");
	}
	
	public void EventBanHang(JLabel lblBanHang, JTabbedPane tpBanHang) {
		lblBanHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblBanHang.setBackground(new Color(39,199,183));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				if(flag[0] < 2) {
					lblBanHang.setBackground(new Color(62,78,94));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				for(int i = 0; i < 9; i++) {
					if(flag[i] == 2) {
						flag[i] = 1;
						lblList[i].setBackground(new Color(62,78,94));
						break;
					}
				}
				
				if(flag[0] == 0) {
					JPanel pnlBanHang = new BanHangGUI();
					JPanel pnlHoaDon = new HoaDonGUI();
					tpBanHang.setPreferredSize(new Dimension(200, 20));
					tpBanHang.add("Bán hàng", pnlBanHang);
					tpBanHang.add("Hóa đơn", pnlHoaDon);
					pnlContent.add(tpBanHang, "0");
					cardLayout.show(pnlContent, "0");
				}else {
					cardLayout.show(pnlContent, "0");
				}
				
				flag[0] = 2;
				lblBanHang.setBackground(new Color(39,199,183));
			}
		});
	}
	
	public void EventNhapHang(JLabel lblNhapHang, JTabbedPane tpNhapHang) {
		lblNhapHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblNhapHang.setBackground(new Color(39,199,183));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				if(flag[1] < 2) {
					lblNhapHang.setBackground(new Color(62,78,94));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				for(int i = 0; i < 9; i++) {
					if(flag[i] == 2) {
						flag[i] = 1;
						lblList[i].setBackground(new Color(62,78,94));
						break;
					}
				}
				
				if(flag[1] == 0) {
					JPanel pnlNhapHang = new NhapHangGUI();
					JPanel pnlPhieuNhap = new PhieuNhapGUI();
					tpNhapHang.add("Nhập hàng", pnlNhapHang);
					tpNhapHang.add("Phiếu Nhập", pnlPhieuNhap);
					pnlContent.add(tpNhapHang, "1");
					cardLayout.show(pnlContent, "1");
				}else {
					cardLayout.show(pnlContent, "1");
				}
				
				flag[1] = 2;
				lblNhapHang.setBackground(new Color(39,199,183));
			}
		});
	}
	
	public void EventSanPham(JLabel lblSanPham, JTabbedPane tpSanPham) {
		lblSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblSanPham.setBackground(new Color(39,199,183));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				if(flag[2] < 2) {
					lblSanPham.setBackground(new Color(62,78,94));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				for(int i = 0; i < 9; i++) {
					if(flag[i] == 2) {
						flag[i] = 1;
						lblList[i].setBackground(new Color(62,78,94));
						break;
					}
				}
				
				if(flag[2] == 0) {
					JPanel pnlMonAn = new MonAnGUI();
					JPanel pnlNguyenLieu = new NguyenLieuGUI();
					tpSanPham.add("Món ăn", pnlMonAn);
					tpSanPham.add("Nguyên liệu", pnlNguyenLieu);
					pnlContent.add(tpSanPham, "2");
					cardLayout.show(pnlContent, "2");
				}else {
					cardLayout.show(pnlContent, "2");
				}
				
				flag[2] = 2;
				lblSanPham.setBackground(new Color(39,199,183));
			}
		});
	}
	
	public void EventNhanVien(JLabel lblNhanVien) {
		lblNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblNhanVien.setBackground(new Color(39,199,183));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				if(flag[3] < 2) {
					lblNhanVien.setBackground(new Color(62,78,94));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				for(int i = 0; i < 9; i++) {
					if(flag[i] == 2) {
						flag[i] = 1;
						lblList[i].setBackground(new Color(62,78,94));
						break;
					}
				}
				
				if(flag[3] == 0) {
					JPanel pnlNhanVien = new NhanVienGUI();
					pnlContent.add(pnlNhanVien, "3");
					cardLayout.show(pnlContent, "3");
				}else {
					cardLayout.show(pnlContent, "3");
				}
				
				flag[3] = 2;
				lblNhanVien.setBackground(new Color(39,199,183));
			}
		});
	}
	
	public void EventKhachHang(JLabel lblKhachHang) {
		lblKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblKhachHang.setBackground(new Color(39,199,183));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				if(flag[4] < 2) {
					lblKhachHang.setBackground(new Color(62,78,94));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				for(int i = 0; i < 9; i++) {
					if(flag[i] == 2) {
						flag[i] = 1;
						lblList[i].setBackground(new Color(62,78,94));
						break;
					}
				}
				
				if(flag[4] == 0) {
					JPanel pnlKhachHang = new KhachHangGUI();
					pnlContent.add(pnlKhachHang, "4");
					cardLayout.show(pnlContent, "4");
				}else {
					cardLayout.show(pnlContent, "4");
				}
				
				flag[4] = 2;
				lblKhachHang.setBackground(new Color(39,199,183));
			}
		});
	}
	
	public void EventNhaCungCap(JLabel lblNhaCungCap) {
		lblNhaCungCap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblNhaCungCap.setBackground(new Color(39,199,183));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				if(flag[5] < 2) {
					lblNhaCungCap.setBackground(new Color(62,78,94));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				for(int i = 0; i < 9; i++) {
					if(flag[i] == 2) {
						flag[i] = 1;
						lblList[i].setBackground(new Color(62,78,94));
						break;
					}
				}
				
				if(flag[5] == 0) {
					JPanel pnlNhaCungCap = new NhaCungCapGUI();
					pnlContent.add(pnlNhaCungCap, "5");
					cardLayout.show(pnlContent, "5");
				}else {
					cardLayout.show(pnlContent, "5");
				}
				
				flag[5] = 2;
				lblNhaCungCap.setBackground(new Color(39,199,183));
			}
		});
	}
	
	public void EventKhuyenMai(JLabel lblKhuyenMai) {
		lblKhuyenMai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblKhuyenMai.setBackground(new Color(39,199,183));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				if(flag[6] < 2) {
					lblKhuyenMai.setBackground(new Color(62,78,94));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				for(int i = 0; i < 9; i++) {
					if(flag[i] == 2) {
						flag[i] = 1;
						lblList[i].setBackground(new Color(62,78,94));
						break;
					}
				}
				
				if(flag[6] == 0) {
					JPanel pnlKhuyenMai = new KhuyenMaiGUI();
					pnlContent.add(pnlKhuyenMai, "6");
					cardLayout.show(pnlContent, "6");
				}else {
					cardLayout.show(pnlContent, "6");
				}
				
				flag[6] = 2;
				lblKhuyenMai.setBackground(new Color(39,199,183));
			}
		});
	}
	
	public void EventTaiKhoan(JLabel lblTaiKhoan) {
		lblTaiKhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblTaiKhoan.setBackground(new Color(39,199,183));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				if(flag[7] < 2) {
					lblTaiKhoan.setBackground(new Color(62,78,94));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				for(int i = 0; i < 9; i++) {
					if(flag[i] == 2) {
						flag[i] = 1;
						lblList[i].setBackground(new Color(62,78,94));
						break;
					}
				}
				
				if(flag[7] == 0) {
					JPanel pnlTaiKhoan = new TaiKhoanGUI();
					pnlContent.add(pnlTaiKhoan, "7");
					cardLayout.show(pnlContent, "7");
				}else {
					cardLayout.show(pnlContent, "7");
				}
				
				flag[7] = 2;
				lblTaiKhoan.setBackground(new Color(39,199,183));
			}
		});
	}
	
	public void EventThongKe(JLabel lblThongKe) {
		lblThongKe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblThongKe.setBackground(new Color(39,199,183));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				if(flag[8] < 2) {
					lblThongKe.setBackground(new Color(62,78,94));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				for(int i = 0; i < 9; i++) {
					if(flag[i] == 2) {
						flag[i] = 1;
						lblList[i].setBackground(new Color(62,78,94));
						break;
					}
				}
				
				if(flag[8] == 0) {
					JPanel pnlThongKe = new JPanel();
					pnlThongKe.add(new JLabel("Thống kê"));
					pnlContent.add(pnlThongKe, "8");
					cardLayout.show(pnlContent, "8");
				}else {
					cardLayout.show(pnlContent, "8");
				}
				
				flag[8] = 2;
				lblThongKe.setBackground(new Color(39,199,183));
			}
		});
	}
}
