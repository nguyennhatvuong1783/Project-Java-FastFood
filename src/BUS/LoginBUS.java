package BUS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import DAO.DaoNhanVien;
import DAO.DaoTaiKhoan;
import DTO.NHANVIEN;
import DTO.TAIKHOAN;
import GUI.MainLayoutGUI;

public class LoginBUS {
	private ArrayList<TAIKHOAN> taiKhoanList;
	
	public LoginBUS(JFrame f, JTextField txtUser, JPasswordField txtPass, JLabel lblLogin) {
		taiKhoanList = DaoTaiKhoan.getInstance().selectAll();
		CheckLogin(f, txtUser, txtPass, lblLogin);
	}
	
	public void CheckLogin(JFrame f, JTextField txtUser, JPasswordField txtPass, JLabel lblLogin) {
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				for (TAIKHOAN taikhoan : taiKhoanList) {
					if((txtUser.getText()).equals(taikhoan.getUserName())) {
						if((String.valueOf(txtPass.getPassword())).equals(taikhoan.getMatKhau())) {
							f.dispose();
							try {
								UIDefaults def = UIManager.getLookAndFeelDefaults();
								def.put("TabbedPane.font", new Font("Times New Roman", Font.PLAIN, 17));
								def.put("TabbedPane.foreground", new Color(255,255,255));
								def.put("TabbedPane.selected", new Color(79,148,205));
						        def.put("TabbedPane.background", new Color(190,190,190));
						        def.put("TabbedPane.tabInsets", new Insets(3,17,3,17));

						        NHANVIEN nhanvien = new NHANVIEN(taikhoan.getUserName(), "", "", "", "", "", 1);
						        nhanvien = DaoNhanVien.getInstance().selectById(nhanvien);
								new MainLayoutGUI(taikhoan, nhanvien);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}
		});
	}
}