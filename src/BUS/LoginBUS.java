package BUS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import DAO.DaoNhanVien;
import DAO.DaoTaiKhoan;
import DTO.NHANVIEN;
import DTO.TAIKHOAN;
import GUI.LoginGUI;
import GUI.MainLayoutGUI;

public class LoginBUS {

    private ArrayList<TAIKHOAN> taiKhoanList;

    public LoginBUS(JTextField txtUser, JPasswordField txtPass, JLabel lblLogin) {
        taiKhoanList = DaoTaiKhoan.getInstance().selectAll();
        CheckLogin(txtUser, txtPass, lblLogin);
    }

    public void CheckLogin(JTextField txtUser, JPasswordField txtPass, JLabel lblLogin) {
        lblLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if ((txtUser.getText()).equals("")) {
                    JOptionPane.showMessageDialog(LoginGUI.f, "Không được để trống tài khoản", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    txtUser.requestFocus();
                } else if ((String.valueOf(txtPass.getPassword())).equals("")) {
                    JOptionPane.showMessageDialog(LoginGUI.f, "Không được để trống mật khẩu", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    txtPass.requestFocus();
                } else {
                    Boolean tmp = false;
                    for (TAIKHOAN taikhoan : taiKhoanList) {
                        if ((txtUser.getText()).equals(taikhoan.getUserName())) {
                            tmp = true;
                            if ((String.valueOf(txtPass.getPassword())).equals(taikhoan.getMatKhau())) {
                                LoginGUI.f.dispose();
                                try {
                                    UIDefaults def = UIManager.getLookAndFeelDefaults();
                                    def.put("TabbedPane.font", new Font("Times New Roman", Font.PLAIN, 17));
                                    def.put("TabbedPane.foreground", new Color(255, 255, 255));
                                    def.put("TabbedPane.selected", new Color(79, 148, 205));
                                    def.put("TabbedPane.background", new Color(190, 190, 190));
                                    def.put("TabbedPane.tabInsets", new Insets(3, 17, 3, 17));

                                    NHANVIEN nhanvien = new NHANVIEN(taikhoan.getUserName(), "", "", "", "", "", 1);
                                    nhanvien = DaoNhanVien.getInstance().selectById(nhanvien);
                                    System.out.println("[LogBUS]: mainGUI");
                                    new MainLayoutGUI(taikhoan, nhanvien);
                                } catch (Exception ex) {
                                    System.out.println("[LogBUS]: error");
                                    ex.printStackTrace();
                                }
                                break;
                            } else {
                                JOptionPane.showMessageDialog(LoginGUI.f, "Sai mật khẩu", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                txtPass.requestFocus();
                                txtPass.selectAll();
                                break;
                            }
                        }
                    }
                    if (tmp == false) {
                        JOptionPane.showMessageDialog(LoginGUI.f, "Tài khoản không tồn tại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        txtUser.requestFocus();
                        txtUser.selectAll();
                    }
                }
            }
        });

        txtUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((txtUser.getText()).equals("")) {
                    JOptionPane.showMessageDialog(LoginGUI.f, "Không được để trống tài khoản", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    txtUser.requestFocus();
                } else if ((String.valueOf(txtPass.getPassword())).equals("")) {
                    JOptionPane.showMessageDialog(LoginGUI.f, "Không được để trống mật khẩu", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    txtPass.requestFocus();
                } else {
                    Boolean tmp = false;
                    for (TAIKHOAN taikhoan : taiKhoanList) {
                        if ((txtUser.getText()).equals(taikhoan.getUserName())) {
                            tmp = true;
                            if ((String.valueOf(txtPass.getPassword())).equals(taikhoan.getMatKhau())) {
                                LoginGUI.f.dispose();
                                try {
                                    UIDefaults def = UIManager.getLookAndFeelDefaults();
                                    def.put("TabbedPane.font", new Font("Times New Roman", Font.PLAIN, 17));
                                    def.put("TabbedPane.foreground", new Color(255, 255, 255));
                                    def.put("TabbedPane.selected", new Color(79, 148, 205));
                                    def.put("TabbedPane.background", new Color(190, 190, 190));
                                    def.put("TabbedPane.tabInsets", new Insets(3, 17, 3, 17));

                                    NHANVIEN nhanvien = new NHANVIEN(taikhoan.getUserName(), "", "", "", "", "", 1);
                                    nhanvien = DaoNhanVien.getInstance().selectById(nhanvien);
                                    new MainLayoutGUI(taikhoan, nhanvien);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(LoginGUI.f, "Sai mật khẩu", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                txtPass.requestFocus();
                                txtPass.selectAll();
                                break;
                            }
                        }
                    }
                    if (tmp == false) {
                        JOptionPane.showMessageDialog(LoginGUI.f, "Tài khoản không tồn tại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        txtUser.requestFocus();
                        txtUser.selectAll();
                    }
                }
            }
        });

        txtPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((txtUser.getText()).equals("")) {
                    JOptionPane.showMessageDialog(LoginGUI.f, "Không được để trống tài khoản", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    txtUser.requestFocus();
                } else if ((String.valueOf(txtPass.getPassword())).equals("")) {
                    JOptionPane.showMessageDialog(LoginGUI.f, "Không được để trống mật khẩu", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    txtPass.requestFocus();
                } else {
                    Boolean tmp = false;
                    for (TAIKHOAN taikhoan : taiKhoanList) {
                        if ((txtUser.getText()).equals(taikhoan.getUserName())) {
                            tmp = true;
                            if ((String.valueOf(txtPass.getPassword())).equals(taikhoan.getMatKhau())) {
                                LoginGUI.f.dispose();
                                try {
                                    UIDefaults def = UIManager.getLookAndFeelDefaults();
                                    def.put("TabbedPane.font", new Font("Times New Roman", Font.PLAIN, 17));
                                    def.put("TabbedPane.foreground", new Color(255, 255, 255));
                                    def.put("TabbedPane.selected", new Color(79, 148, 205));
                                    def.put("TabbedPane.background", new Color(190, 190, 190));
                                    def.put("TabbedPane.tabInsets", new Insets(3, 17, 3, 17));

                                    NHANVIEN nhanvien = new NHANVIEN(taikhoan.getUserName(), "", "", "", "", "", 1);
                                    nhanvien = DaoNhanVien.getInstance().selectById(nhanvien);
                                    new MainLayoutGUI(taikhoan, nhanvien);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(LoginGUI.f, "Sai mật khẩu", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                txtPass.requestFocus();
                                txtPass.selectAll();
                                break;
                            }
                        }
                    }
                    if (tmp == false) {
                        JOptionPane.showMessageDialog(LoginGUI.f, "Tài khoản không tồn tại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        txtUser.requestFocus();
                        txtUser.selectAll();
                    }
                }
            }
        });
    }
}
