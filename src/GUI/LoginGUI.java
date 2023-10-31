package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BUS.LoginBUS;

public class LoginGUI {
	private JFrame f;
	private JLabel lblBg, lblLogo, lblTitle, lblUser, lblPass, lblLogin, lblClose;
	private JTextField txtUser;
	private JPasswordField txtPass;
	
	public LoginGUI() {
		init();
		new LoginBUS(f, txtUser, txtPass, lblLogin);
	}
	
	private void init() {
		f = new JFrame();
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("./icon_img/logo-fast-food-40.png")));
		f.setUndecorated(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
		f.setSize(new Dimension(400, 500));
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int w = d.width;
		int h = d.height;
		int x = (w - f.getSize().width) / 2;
		int y = (h - f.getSize().height) / 2;
		f.setLocation(x, y);
		
		lblBg = new JLabel(new ImageIcon(getClass().getResource("/icon_img/background-login.jpg")));
		lblBg.setBounds(0, 0, 400, 500);
		
		lblLogo = new JLabel(new ImageIcon(getClass().getResource("/icon_img/logo-fast-food-100.png")));
		lblLogo.setBounds(149, 0, 100, 100);
		lblLogo.setBackground(null);
		
		lblClose = new JLabel(new ImageIcon(getClass().getResource("/icon_img/login-close-white-25.png")));
		lblClose.setBounds(370, 5, 25, 25);
		lblClose.setBackground(null);
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblClose.setIcon(new ImageIcon(getClass().getResource("/icon_img/login-close-red-25.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lblClose.setIcon(new ImageIcon(getClass().getResource("/icon_img/login-close-white-25.png")));
			}
		});
		
		lblTitle = new JLabel("ĐĂNG NHẬP", JLabel.CENTER);
		lblTitle.setBounds(0, 100, 400, 80);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblTitle.setForeground(new Color(255,255,255));
		
		lblUser = new JLabel("Mã nhân viên");
		lblUser.setBounds(25, 180, 300, 30);
		lblUser.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblUser.setForeground(new Color(255,255,255));
		
		txtUser = new JTextField();
		txtUser.setBounds(20, 210, 360, 40);
		txtUser.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		lblPass = new JLabel("Mật khẩu");
		lblPass.setBounds(25, 270, 300, 30);
		lblPass.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPass.setForeground(new Color(255,255,255));
		
		txtPass = new JPasswordField();
		txtPass.setBounds(20, 300, 360, 40);
		txtPass.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		lblLogin = new JLabel("ĐĂNG NHẬP", JLabel.CENTER);
		lblLogin.setBounds(20, 390, 360, 55);
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblLogin.setForeground(new Color(255,255,255));
		lblLogin.setBackground(new Color(255,165,0));
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblLogin.setBackground(new Color(255,110,0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lblLogin.setBackground(new Color(255,165,0));
			}
		});
		lblLogin.setOpaque(true);

		
		f.add(lblLogo);
		f.add(lblClose);
		f.add(lblTitle);
		f.add(lblUser);
		f.add(txtUser);
		f.add(lblPass);
		f.add(txtPass);
		f.add(lblLogin);
		
		f.add(lblBg);
		
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		new LoginGUI();
	}
}
