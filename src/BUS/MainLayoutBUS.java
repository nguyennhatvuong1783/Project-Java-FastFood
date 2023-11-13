package BUS;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import DAO.DaoChiTietPhanQuyen;
import DTO.CHITIETPHANQUYEN;
import DTO.TAIKHOAN;
import GUI.BanHangGUI;
import GUI.HoaDonGUI;
import GUI.KhachHangGUI;
import GUI.KhuyenMaiGUI;
import GUI.LoginGUI;
import GUI.MainLayoutGUI;
import GUI.MonAnGUI;
import GUI.NguyenLieuGUI;
import GUI.NhaCungCapGUI;
import GUI.NhanVienGUI;
import GUI.NhapHangGUI;
import GUI.PhieuNhapGUI;
import GUI.QuyenGUI;
import GUI.TaiKhoanGUI;
import GUI.ThongKeGUI;

public class MainLayoutBUS {
	private JLabel lblList[];
	private int flag[] = new int[9];
	private JPanel pnlContent;
	private CardLayout cardLayout = new CardLayout();
	private ArrayList<CHITIETPHANQUYEN> listCN;
	
	public MainLayoutBUS(JLabel lblBanHang, JLabel lblNhapHang, JLabel lblSanPham, 
			JLabel lblNhanVien, JLabel lblKhachHang, JLabel lblNhaCungCap, JLabel lblKhuyenMai, 
			JLabel lblTaiKhoan, JLabel lblThongKe, JPanel pnlContent, JTabbedPane tpBanHang, 
			JTabbedPane tpNhapHang, JTabbedPane tpSanPham, JTabbedPane tpTaiKhoan, JLabel lblLogOut, 
			TAIKHOAN taikhoan, JPanel pnlMenu) {
		JLabel list[] = {lblBanHang, lblNhapHang, lblSanPham, lblNhanVien, lblKhachHang, 
				lblNhaCungCap, lblKhuyenMai, lblTaiKhoan, lblThongKe};
		listCN = DaoChiTietPhanQuyen.getInstance()
				.selectByCondition("MAQUYEN='" + taikhoan.getMaQuyen() + "'");
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
		EventTaiKhoan(lblTaiKhoan, tpTaiKhoan);
		EventThongKe(lblThongKe);
		EventLogOut(lblLogOut);
		CheckQuyen(lblBanHang, lblNhapHang, lblSanPham, lblNhanVien, lblKhachHang, lblNhaCungCap, lblKhuyenMai, 
				lblTaiKhoan, lblThongKe, lblLogOut, listCN, pnlMenu);
		pnlContent.add(new JPanel(), "null");
	}
	
	public void CheckQuyen(JLabel lblBanHang, JLabel lblNhapHang, JLabel lblSanPham, JLabel lblNhanVien,
			JLabel lblKhachHang, JLabel lblNhaCungCap, JLabel lblKhuyenMai, JLabel lblTaiKhoan, JLabel lblThongKe,
			JLabel lblLogOut, ArrayList<CHITIETPHANQUYEN> listCN, JPanel pnlMenu) {
		
		int tmpBanHang=0, tmpNhapHang=0, tmpSanPham=0, tmpNhanVien=0, tmpKhachHang=0, tmpNhaCungCap=0, 
				tmpKhuyenMai=0, tmpTaiKhoan=0, tmpThongKe=0;
		for (CHITIETPHANQUYEN cn : listCN) {
			if(cn.getMaChucNang().equals("CN01")) {
				tmpBanHang++;
			}else if (cn.getMaChucNang().equals("CN02")) {
				tmpBanHang++;
			}else if (cn.getMaChucNang().equals("CN03")) {
				tmpNhapHang++;
			}else if (cn.getMaChucNang().equals("CN04")) {
				tmpNhapHang++;
			}else if (cn.getMaChucNang().equals("CN05")) {
				tmpSanPham++;
			}else if (cn.getMaChucNang().equals("CN06")) {
				tmpSanPham++;
			}else if (cn.getMaChucNang().equals("CN07")) {
				tmpNhanVien++;
			}else if (cn.getMaChucNang().equals("CN08")) {
				tmpKhachHang++;
			}else if (cn.getMaChucNang().equals("CN09")) {
				tmpNhaCungCap++;
			}else if (cn.getMaChucNang().equals("CN10")) {
				tmpKhuyenMai++;
			}else if (cn.getMaChucNang().equals("CN11")) {
				tmpTaiKhoan++;
			}else if (cn.getMaChucNang().equals("CN12")) {
				tmpTaiKhoan++;
			}else if (cn.getMaChucNang().equals("CN13")) {
				tmpThongKe++;
			}
		}
		
		if(tmpBanHang == 0) {
			pnlMenu.remove(lblBanHang);
		}
		if (tmpNhapHang == 0) {
			pnlMenu.remove(lblNhapHang);
		}
		if (tmpSanPham == 0) {
			pnlMenu.remove(lblSanPham);
		}
		if (tmpNhanVien == 0) {
			pnlMenu.remove(lblNhanVien);
		}
		if (tmpKhachHang == 0) {
			pnlMenu.remove(lblKhachHang);
		}
		if (tmpNhaCungCap == 0) {
			pnlMenu.remove(lblNhaCungCap);
		}
		if (tmpKhuyenMai == 0) {
			pnlMenu.remove(lblKhuyenMai);
		}
		if (tmpTaiKhoan == 0) {
			pnlMenu.remove(lblTaiKhoan);
		}
		if (tmpThongKe == 0) {
			pnlMenu.remove(lblThongKe);
		}
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
					for (CHITIETPHANQUYEN cn : listCN) {
						if(cn.getMaChucNang().equals("CN01")) {
							JPanel pnlBanHang = new BanHangGUI();
							tpBanHang.add("Bán hàng", pnlBanHang);
						}else if(cn.getMaChucNang().equals("CN02")) {
							JPanel pnlHoaDon = new HoaDonGUI();
							tpBanHang.add("Hóa đơn", pnlHoaDon);
						}
					}
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
					for (CHITIETPHANQUYEN cn : listCN) {
						if(cn.getMaChucNang().equals("CN03")) {
							JPanel pnlNhapHang = new NhapHangGUI();
							tpNhapHang.add("Nhập hàng", pnlNhapHang);
						}else if(cn.getMaChucNang().equals("CN04")) {
							JPanel pnlPhieuNhap = new PhieuNhapGUI();
							tpNhapHang.add("Phiếu Nhập", pnlPhieuNhap);
						}
					}
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
					for (CHITIETPHANQUYEN cn : listCN) {
						if(cn.getMaChucNang().equals("CN05")) {
							JPanel pnlMonAn = new MonAnGUI();
							tpSanPham.add("Món ăn", pnlMonAn);
						}else if(cn.getMaChucNang().equals("CN06")) {
							JPanel pnlNguyenLieu = new NguyenLieuGUI();
							tpSanPham.add("Nguyên liệu", pnlNguyenLieu);
						}
					}
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
	
	public void EventTaiKhoan(JLabel lblTaiKhoan, JTabbedPane tpTaiKhoan) {
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
					for (CHITIETPHANQUYEN cn : listCN) {
						if(cn.getMaChucNang().equals("CN11")) {
							JPanel pnlTaiKhoan = new TaiKhoanGUI();
							tpTaiKhoan.add("Tài khoản", pnlTaiKhoan);
						}else if(cn.getMaChucNang().equals("CN12")) {
							JPanel pnlQuyen = new QuyenGUI();
							tpTaiKhoan.add("Quyền", pnlQuyen);
						}
					}
					pnlContent.add(tpTaiKhoan, "7");
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
					JPanel pnlThongKe = new ThongKeGUI();
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
	
	public void EventLogOut(JLabel lblLogOut) {
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				MainLayoutGUI.f.dispose();
				new LoginGUI();
			}
		});
	}
}