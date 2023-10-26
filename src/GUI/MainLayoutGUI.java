package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainLayoutGUI {
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	
	private JFrame f;
	private JPanel pnlTitle;
	private JPanel pnlLeftMenu;
	private JPanel pnlContent;
	public JLabel lblBanHang;
	public JLabel lblNhapHang;
	public JLabel lblSanPham;
	public JLabel lblNhanVien;
	public JLabel lblKhachHang;
	public JLabel lblNhaCungCap;
	public JLabel lblKhuyenMai;
	public JLabel lblTaiKhoan;
	public JLabel lblThongKe;
	
	public MainLayoutGUI() {
		init();
	}
	
	private void init() {
		f = new JFrame();
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("./icon_img/logo-fast-food-40.png")));
		f.setUndecorated(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		f.setSize(new Dimension(1000, 600));
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int w = d.width;
		int h = d.height;
		int x = (w - f.getSize().width) / 2;
		int y = (h - f.getSize().height) / 2;
		f.setLocation(x, y);
		
		pnlTitle = Title();
		pnlLeftMenu = LeftMenu();
		f.add(pnlTitle, BorderLayout.NORTH);
		f.add(pnlLeftMenu, BorderLayout.WEST);
		
		f.setVisible(true);
	}
	
	private JPanel Title() {
		JPanel pnlTitle = new JPanel();
		pnlTitle.setLayout(new BorderLayout());
		pnlTitle.setBackground(new Color(255,165,0));
		
		JPanel pnlControl = new JPanel();
		pnlControl.setLayout(new FlowLayout(0, 0, FlowLayout.RIGHT));
		pnlControl.setBackground(new Color(255,165,0));
		
		JLabel lblTitle = new JLabel("Phần mềm quản lý cửa hàng thức ăn nhanh", JLabel.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTitle.setForeground(new Color(255,255,255));
		
		JLabel lblLogo = new JLabel(new ImageIcon(getClass().getResource("/icon_img/logo-fast-food-30.png")));
		
		JLabel lblExit = new JLabel(new ImageIcon(getClass().getResource("/icon_img/close-lblexit-20.png")));
		lblExit.setPreferredSize(new Dimension(42,32));
		lblExit.setBackground(new Color(255,165,0));
		lblExit.setOpaque(true);
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblExit.setBackground(new Color(255,0,0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lblExit.setBackground(new Color(255,165,0));
			}
		});
				
		JLabel lblScreen = new JLabel(new ImageIcon(getClass().getResource("/icon_img/square-lblscreen-20.png")));
		lblScreen.setPreferredSize(new Dimension(42,32));
		lblScreen.setBackground(new Color(255,165,0));
		lblScreen.setOpaque(true);
		lblScreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
	        	if(device.getFullScreenWindow() == null) {
		            device.setFullScreenWindow(f);
		            lblScreen.setIcon(new ImageIcon(getClass().getResource("/icon_img/restore-down-lblscreen-20.png")));
	        	}else {
		            device.setFullScreenWindow(null);
		            lblScreen.setIcon(new ImageIcon(getClass().getResource("/icon_img/square-lblscreen-20.png")));
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblScreen.setBackground(new Color(255,140,0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lblScreen.setBackground(new Color(255,165,0));
			}
		});

		JLabel lblHide = new JLabel(new ImageIcon(getClass().getResource("/icon_img/line-lblhide-20.png")));
		lblHide.setPreferredSize(new Dimension(42,32));
		lblHide.setBackground(new Color(255,165,0));
		lblHide.setOpaque(true);
		lblHide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				f.setState(JFrame.ICONIFIED);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblHide.setBackground(new Color(255,140,0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lblHide.setBackground(new Color(255,165,0));
			}
		});
		
		pnlControl.add(lblHide);
		pnlControl.add(lblScreen);
		pnlControl.add(lblExit);
		
		pnlTitle.add(pnlControl, BorderLayout.EAST);
		pnlTitle.add(lblTitle, BorderLayout.CENTER);
		pnlTitle.add(lblLogo, BorderLayout.WEST);

		return pnlTitle;
	}
	
	private JPanel LeftMenu() {
		JPanel pnlLeftMenu = new JPanel(new BorderLayout());
		pnlLeftMenu.setBackground(new Color(62,78,94));
		
		JLabel lblLogo = new JLabel(new ImageIcon(getClass().getResource("/icon_img/logo-fast-food-150.png")));
		lblLogo.setPreferredSize(new Dimension(200, 170));
		
		JPanel pnlMenu = new JPanel(new GridLayout(9, 1));
		pnlMenu.setBackground(new Color(62,78,94));
		
		lblBanHang = new JLabel("Bán hàng", JLabel.CENTER);
		lblBanHang.setIcon(new ImageIcon(getClass().getResource("/icon_img/close-lblexit-20.png")));
		lblBanHang.setForeground(new Color(255,255,255));
		lblBanHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBanHang.setBackground(new Color(39,199,183));
		lblBanHang.setOpaque(true);
		
		lblNhapHang = new JLabel("Nhập hàng", JLabel.CENTER);
		lblNhapHang.setIcon(new ImageIcon(getClass().getResource("/icon_img/close-lblexit-20.png")));
		lblNhapHang.setForeground(new Color(255,255,255));
		lblNhapHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		lblSanPham = new JLabel("Sản phẩm", JLabel.CENTER);
		lblSanPham.setIcon(new ImageIcon(getClass().getResource("/icon_img/close-lblexit-20.png")));
		lblSanPham.setForeground(new Color(255,255,255));
		lblSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		lblNhanVien = new JLabel("Nhân viên", JLabel.CENTER);
		lblNhanVien.setIcon(new ImageIcon(getClass().getResource("/icon_img/close-lblexit-20.png")));
		lblNhanVien.setForeground(new Color(255,255,255));
		lblNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		lblKhachHang = new JLabel("Khách hàng", JLabel.CENTER);
		lblKhachHang.setIcon(new ImageIcon(getClass().getResource("/icon_img/close-lblexit-20.png")));
		lblKhachHang.setForeground(new Color(255,255,255));
		lblKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		lblNhaCungCap = new JLabel("Nhà cung cấp", JLabel.CENTER);
		lblNhaCungCap.setIcon(new ImageIcon(getClass().getResource("/icon_img/close-lblexit-20.png")));
		lblNhaCungCap.setForeground(new Color(255,255,255));
		lblNhaCungCap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		lblKhuyenMai = new JLabel("Khuyến mãi", JLabel.CENTER);
		lblKhuyenMai.setIcon(new ImageIcon(getClass().getResource("/icon_img/close-lblexit-20.png")));
		lblKhuyenMai.setForeground(new Color(255,255,255));
		lblKhuyenMai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		lblTaiKhoan = new JLabel("Tài khoản", JLabel.CENTER);
		lblTaiKhoan.setIcon(new ImageIcon(getClass().getResource("/icon_img/close-lblexit-20.png")));
		lblTaiKhoan.setForeground(new Color(255,255,255));
		lblTaiKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		lblThongKe = new JLabel("Thống kê", JLabel.CENTER);
		lblThongKe.setIcon(new ImageIcon(getClass().getResource("/icon_img/close-lblexit-20.png")));
		lblThongKe.setForeground(new Color(255,255,255));
		lblThongKe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		pnlMenu.add(lblBanHang);
		pnlMenu.add(lblNhapHang);
		pnlMenu.add(lblSanPham);
		pnlMenu.add(lblNhanVien);
		pnlMenu.add(lblKhachHang);
		pnlMenu.add(lblNhaCungCap);
		pnlMenu.add(lblKhuyenMai);
		pnlMenu.add(lblTaiKhoan);
		pnlMenu.add(lblThongKe);
		
		pnlLeftMenu.add(lblLogo, BorderLayout.NORTH);
		pnlLeftMenu.add(pnlMenu, BorderLayout.CENTER);
		
		return pnlLeftMenu;
	}
	
	public static void main(String[] args) {
		new MainLayoutGUI();
	}
}
