package BUS;

import DAO.DaoTaiKhoan;
import DTO.TAIKHOAN;
import GUI.SuaTaiKhoanGUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhin
 */
public class SuaTaiKhoanBUS {

    private SuaTaiKhoanGUI suaTaiKhoanGUI;

    public SuaTaiKhoanBUS(SuaTaiKhoanGUI suaTaiKhoanGUI) {
        this.suaTaiKhoanGUI = suaTaiKhoanGUI;
        event();
    }

    private void event() {
        windowsEvent();
        taiKhoanEvent();
    }

    private void windowsEvent() {
        suaTaiKhoanGUI.getPnTitle().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                suaTaiKhoanGUI.setpX(e.getX());
                suaTaiKhoanGUI.setpY(e.getY());
            }
        });

        suaTaiKhoanGUI.getPnTitle().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                suaTaiKhoanGUI.setLocation(suaTaiKhoanGUI.getLocation().x + e.getX() - suaTaiKhoanGUI.getpX(), suaTaiKhoanGUI.getLocation().y + e.getY() - suaTaiKhoanGUI.getpY());
            }
        });

        suaTaiKhoanGUI.getLbExit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaoTaiKhoanBUS]: Close");
                suaTaiKhoanGUI.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                suaTaiKhoanGUI.getLbExit().setBackground(new Color(255, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                suaTaiKhoanGUI.getLbExit().setBackground(null);
            }
        });

        suaTaiKhoanGUI.getCbbMaQuyen().requestFocusInWindow();
    }

    private void taiKhoanEvent() {
        // CheckBox
        suaTaiKhoanGUI.getChkDoiMaQuyen().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (suaTaiKhoanGUI.getChkDoiMaQuyen().isSelected()) {
                    System.out.println("[SuaTaiKhoanBUS]: Sua quyen Enable");
                    suaTaiKhoanGUI.getCbbMaQuyen().setEnabled(true);
                } else {
                    System.out.println("[SuaTaiKhoanBUS]: Sua quyen Disable");
                    suaTaiKhoanGUI.getCbbMaQuyen().setEnabled(false);
                }
            }
        });

        suaTaiKhoanGUI.getChkDoiMatKhau().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (suaTaiKhoanGUI.getChkDoiMatKhau().isSelected()) {
                    System.out.println("[SuaTaiKhoanBUS]: Sua mat khau Enable");
                    suaTaiKhoanGUI.getPwMatKhauCu().setEnabled(true);
                    suaTaiKhoanGUI.getPwMatKhau().setEnabled(true);
                    suaTaiKhoanGUI.getPwNhapLai().setEnabled(true);
                    suaTaiKhoanGUI.getTxtMatKhau().setEnabled(true);
                    suaTaiKhoanGUI.getTxtNhapLai().setEnabled(true);
                    suaTaiKhoanGUI.getLbHienThiMatKhau().setEnabled(true);
                    suaTaiKhoanGUI.getLbHienThiNhapLai().setEnabled(true);
                } else {
                    System.out.println("[SuaTaiKhoanBUS]: Sua mat khau Disable");
                    suaTaiKhoanGUI.getPwMatKhauCu().setEnabled(false);
                    suaTaiKhoanGUI.getPwMatKhau().setEnabled(false);
                    suaTaiKhoanGUI.getPwNhapLai().setEnabled(false);
                    suaTaiKhoanGUI.getTxtMatKhau().setEnabled(false);
                    suaTaiKhoanGUI.getTxtNhapLai().setEnabled(false);
                    suaTaiKhoanGUI.getLbHienThiMatKhau().setEnabled(false);
                    suaTaiKhoanGUI.getLbHienThiNhapLai().setEnabled(false);
                }
            }
        });

        // Show pass
        suaTaiKhoanGUI.getLbHienThiMatKhau().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (suaTaiKhoanGUI.getPwMatKhau().isVisible()) {
                    System.out.println("[SuaTaiKhoanBUS]: Show password");
                    String pass = "";

                    for (char c : suaTaiKhoanGUI.getPwMatKhau().getPassword()) {
                        pass += c + "";
                    }
                    suaTaiKhoanGUI.getPwMatKhau().setVisible(false);
                    suaTaiKhoanGUI.getTxtMatKhau().setVisible(true);
                    suaTaiKhoanGUI.getTxtMatKhau().setText(pass);
                    suaTaiKhoanGUI.getLbHienThiMatKhau().setIcon(new ImageIcon(getClass().getResource("../icon_img/showPassword.png")));
                } else {
                    System.out.println("[SuaTaiKhoanBUS]: Hide password");
                    suaTaiKhoanGUI.getTxtMatKhau().setVisible(false);
                    suaTaiKhoanGUI.getPwMatKhau().setVisible(true);
                    suaTaiKhoanGUI.getPwMatKhau().setText(suaTaiKhoanGUI.getTxtMatKhau().getText());
                    suaTaiKhoanGUI.getLbHienThiMatKhau().setIcon(new ImageIcon(getClass().getResource("../icon_img/hidePassword.png")));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                suaTaiKhoanGUI.getLbHienThiMatKhau().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        suaTaiKhoanGUI.getLbHienThiNhapLai().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (suaTaiKhoanGUI.getPwNhapLai().isVisible()) {
                    System.out.println("[TaoTaiKhoanBUS]: Show retype password");
                    String pass = "";

                    for (char c : suaTaiKhoanGUI.getPwNhapLai().getPassword()) {
                        pass += c + "";
                    }
                    suaTaiKhoanGUI.getPwNhapLai().setVisible(false);
                    suaTaiKhoanGUI.getTxtNhapLai().setVisible(true);
                    suaTaiKhoanGUI.getTxtNhapLai().setText(pass);
                    suaTaiKhoanGUI.getLbHienThiNhapLai().setIcon(new ImageIcon(getClass().getResource("../icon_img/showPassword.png")));
                } else {
                    System.out.println("[TaoTaiKhoanBUS]: Hide retype password");
                    suaTaiKhoanGUI.getTxtNhapLai().setVisible(false);
                    suaTaiKhoanGUI.getPwNhapLai().setVisible(true);
                    suaTaiKhoanGUI.getPwNhapLai().setText(suaTaiKhoanGUI.getTxtNhapLai().getText());
                    suaTaiKhoanGUI.getLbHienThiNhapLai().setIcon(new ImageIcon(getClass().getResource("../icon_img/hidePassword.png")));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                suaTaiKhoanGUI.getLbHienThiNhapLai().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        // Button
        suaTaiKhoanGUI.getBtnLuu().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[SuaTaiKhoanBUS]: Luu");
                @SuppressWarnings("UnusedAssignment")
                String maQuyen = "", matKhauCu = "", matKhau = "", nhapLai = "";
                TAIKHOAN taiKhoanSua = new TAIKHOAN();

                // Cac thong tin khong sua
                taiKhoanSua.setUserName(suaTaiKhoanGUI.getTaiKhoan().getUserName());
                taiKhoanSua.setTrangThai(suaTaiKhoanGUI.getTaiKhoan().getTrangThai());

                // Sua ma quyen
                if (suaTaiKhoanGUI.getChkDoiMaQuyen().isSelected()) {
                    maQuyen = checkMaQuyen();
                    for (TAIKHOAN tk : suaTaiKhoanGUI.getListTaiKhoan()) {
                        if (tk.getUserName().equals(suaTaiKhoanGUI.getTaiKhoan().getUserName())) {
                            // Sua list
                            tk.setMaQuyen(maQuyen);

                            // Sua ma quyen cua tai khoan se sua trong Database
                            taiKhoanSua.setMaQuyen(maQuyen);
                            break;
                        }
                    }
                } else {
                    // Truong hop khong chon check box sua ma quyen -> khong sua ma quyen, lay ma quyen cu
                    taiKhoanSua.setMaQuyen(suaTaiKhoanGUI.getTaiKhoan().getMaQuyen());
                }

                // Sua mat khau
                if (suaTaiKhoanGUI.getChkDoiMatKhau().isSelected()) {
                    String[] mk = checkMatKhau();
                    matKhauCu = mk[0];  // Lay tu mat khau cu
                    matKhau = mk[1];    // Lay tu mat khau
                    nhapLai = mk[2];    // Lay tu nhap lai mat khau

                    for (TAIKHOAN tk : suaTaiKhoanGUI.getListTaiKhoan()) {
                        if (tk.getUserName().equals(suaTaiKhoanGUI.getTaiKhoan().getUserName())) {
                            if (kiemTraThongTin(tk, matKhauCu, matKhau, nhapLai)) {
                                /* Thong tin hop le */
                                // Sua list
                                tk.setMatKhau(matKhau);

                                // Sua mat khau cua tai khoan se sua trong Database
                                taiKhoanSua.setMatKhau(matKhau);
                                break;
                            } else {
                                // Thong tin khong hop le
                                return;
                            }
                        }
                    }
                } else {
                    // Truong hop khong chon check box sua mat khau -> khong sua mat khau, lay mat khau hien tai
                    taiKhoanSua.setMatKhau(suaTaiKhoanGUI.getTaiKhoan().getMatKhau());
                }

                // Sua Database
                DaoTaiKhoan.getInstance().update(taiKhoanSua);

                JOptionPane.showMessageDialog(null, "Sửa thành công");
                suaTaiKhoanGUI.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                suaTaiKhoanGUI.getBtnLuu().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            private boolean kiemTraThongTin(TAIKHOAN tk, String matKhauCu, String matKhau, String nhapLai) {
                if (!matKhauCu.equals(tk.getMatKhau())) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                if (matKhau.replaceAll("\\s", "").equals("")) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu không được bỏ trống!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                if (matKhau.length() < 4) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu phải dài ít nhất 4 ký tự!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                if (matKhau.matches("[0-9]+")) {
                    String message = "Mật khẩu phải bao gồm:\nChữ cái A-Z a-z\nChữ số 0-9";
                    JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                if (matKhau.matches("[A-Za-z]+")) {
                    String message = "Mật khẩu phải bao gồm:\nChữ cái A-Z a-z\nChữ số 0-9";
                    JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                if (!nhapLai.equals(matKhau)) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu mới không trùng khớp!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                if (matKhauCu.equals(matKhau)) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu cũ và mật khẩu mới không được trùng nhau!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                return true;
            }
        });

        suaTaiKhoanGUI.getBtnHuy().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[SuaTaiKhoanBUS]: Huy");
                suaTaiKhoanGUI.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                suaTaiKhoanGUI.getBtnHuy().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    private String checkMaQuyen() {
        return suaTaiKhoanGUI.getCbbMaQuyen().getSelectedItem().toString().split(" - ")[0];
    }

    private String[] checkMatKhau() {
        String[] mk = new String[3];

        mk[0] = "";
        mk[1] = "";
        mk[2] = "";
        for (char c : suaTaiKhoanGUI.getPwMatKhauCu().getPassword()) {
            mk[0] += c;
        }
        if (suaTaiKhoanGUI.getPwMatKhau().isVisible()) {
            for (char c : suaTaiKhoanGUI.getPwMatKhau().getPassword()) {
                mk[1] += c;
            }
        } else {
            mk[1] = suaTaiKhoanGUI.getTxtMatKhau().getText();
        }
        if (suaTaiKhoanGUI.getPwNhapLai().isVisible()) {
            for (char c : suaTaiKhoanGUI.getPwNhapLai().getPassword()) {
                mk[2] += c;
            }
        } else {
            mk[2] = suaTaiKhoanGUI.getTxtNhapLai().getText();
        }

        return mk;
    }
}
