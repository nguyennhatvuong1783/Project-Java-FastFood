package BUS;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.DaoTaiKhoan;
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
							new MainLayoutGUI();
						}
					}
				}
			}
		});
	}
}