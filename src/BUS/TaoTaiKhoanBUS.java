package BUS;

import DAO.DaoTaiKhoan;
import DTO.TAIKHOAN;
import GUI.TaoTaiKhoanGUI;
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
public class TaoTaiKhoanBUS {

    private TaoTaiKhoanGUI taoTaiKhoanGUI;
    
    public TaoTaiKhoanBUS(TaoTaiKhoanGUI taoTaiKhoanGUI) {
        this.taoTaiKhoanGUI = taoTaiKhoanGUI;
        event();
    }

    private void event() {
        windowsEvent();
        taiKhoanEvent();
    }

    private void windowsEvent() {
        taoTaiKhoanGUI.getPnTitle().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                taoTaiKhoanGUI.setpX(e.getX());
                taoTaiKhoanGUI.setpY(e.getY());
            }
        });

        taoTaiKhoanGUI.getPnTitle().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                taoTaiKhoanGUI.setLocation(taoTaiKhoanGUI.getLocation().x + e.getX() - taoTaiKhoanGUI.getpX(), taoTaiKhoanGUI.getLocation().y + e.getY() - taoTaiKhoanGUI.getpY());
            }
        });

        taoTaiKhoanGUI.getLbExit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaoTaiKhoanBUS]: Close");
                taoTaiKhoanGUI.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taoTaiKhoanGUI.getLbExit().setBackground(new Color(255, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taoTaiKhoanGUI.getLbExit().setBackground(null);
            }
        });
    }

    private void taiKhoanEvent() {
        taoTaiKhoanGUI.getPwMatKhau().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                taoTaiKhoanGUI.getPwMatKhau().setCursor(new Cursor(Cursor.TEXT_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taoTaiKhoanGUI.getPwMatKhau().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        taoTaiKhoanGUI.getLbHienThiMatKhau().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (taoTaiKhoanGUI.getPwMatKhau().isVisible()) {
                    System.out.println("[TaoTaiKhoanBUS]: Show password");
                    String pass = "";

                    for (char c : taoTaiKhoanGUI.getPwMatKhau().getPassword()) {
                        pass += c + "";
                    }
                    taoTaiKhoanGUI.getPwMatKhau().setVisible(false);
                    taoTaiKhoanGUI.getTxtMatKhau().setVisible(true);
                    taoTaiKhoanGUI.getTxtMatKhau().setText(pass);
                    taoTaiKhoanGUI.getLbHienThiMatKhau().setIcon(new ImageIcon(getClass().getResource("../icon_img/showPassword.png")));
                } else {
                    System.out.println("[TaoTaiKhoanBUS]: Hide password");
                    taoTaiKhoanGUI.getTxtMatKhau().setVisible(false);
                    taoTaiKhoanGUI.getPwMatKhau().setVisible(true);
                    taoTaiKhoanGUI.getPwMatKhau().setText(taoTaiKhoanGUI.getTxtMatKhau().getText());
                    taoTaiKhoanGUI.getLbHienThiMatKhau().setIcon(new ImageIcon(getClass().getResource("../icon_img/hidePassword.png")));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taoTaiKhoanGUI.getLbHienThiMatKhau().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        taoTaiKhoanGUI.getPwNhapLai().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                taoTaiKhoanGUI.getPwNhapLai().setCursor(new Cursor(Cursor.TEXT_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taoTaiKhoanGUI.getPwNhapLai().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        taoTaiKhoanGUI.getLbHienThiNhapLai().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (taoTaiKhoanGUI.getPwNhapLai().isVisible()) {
                    System.out.println("[TaoTaiKhoanBUS]: Show retype password");
                    String pass = "";

                    for (char c : taoTaiKhoanGUI.getPwNhapLai().getPassword()) {
                        pass += c + "";
                    }
                    taoTaiKhoanGUI.getPwNhapLai().setVisible(false);
                    taoTaiKhoanGUI.getTxtNhapLai().setVisible(true);
                    taoTaiKhoanGUI.getTxtNhapLai().setText(pass);
                    taoTaiKhoanGUI.getLbHienThiNhapLai().setIcon(new ImageIcon(getClass().getResource("../icon_img/showPassword.png")));
                } else {
                    System.out.println("[TaoTaiKhoanBUS]: Hide retype password");
                    taoTaiKhoanGUI.getTxtNhapLai().setVisible(false);
                    taoTaiKhoanGUI.getPwNhapLai().setVisible(true);
                    taoTaiKhoanGUI.getPwNhapLai().setText(taoTaiKhoanGUI.getTxtNhapLai().getText());
                    taoTaiKhoanGUI.getLbHienThiNhapLai().setIcon(new ImageIcon(getClass().getResource("../icon_img/hidePassword.png")));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taoTaiKhoanGUI.getLbHienThiNhapLai().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        taoTaiKhoanGUI.getBtnThem().addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings({"static-access", "UnnecessaryReturnStatement"})
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaoTaiKhoanBUS]: Them");
                String maNhanVien;
                String matKhau = "";
                String nhapLai = "";
                String maQuyen;

                maNhanVien = taoTaiKhoanGUI.getCbbMaNhanVien().getSelectedItem().toString();
                if (taoTaiKhoanGUI.getPwMatKhau().isVisible()) {
                    for (char c : taoTaiKhoanGUI.getPwMatKhau().getPassword()) {
                        matKhau += c + "";
                    }
                } else {
                    matKhau = taoTaiKhoanGUI.getTxtMatKhau().getText();
                }
                if (taoTaiKhoanGUI.getPwNhapLai().isVisible()) {
                    for (char c : taoTaiKhoanGUI.getPwNhapLai().getPassword()) {
                        nhapLai += c + "";
                    }
                } else {
                    nhapLai = taoTaiKhoanGUI.getTxtNhapLai().getText();
                }
                maQuyen = taoTaiKhoanGUI.getCbbMaQuyen().getSelectedItem().toString().split(" - ")[0];

//                System.out.println("Ma nv: " + maNhanVien);
//                System.out.println("password: \"" + matKhau + "\"");
//                System.out.println("retype: " + nhapLai);
//                System.out.println("ma quyen: " + maQuyen);
                if (!kiemTraThongTin(matKhau, nhapLai)) {
                    return;
                }
                xuLyDuLieu(maNhanVien, matKhau, maQuyen);

                JOptionPane.showMessageDialog(null, "Thêm thành công");
                taoTaiKhoanGUI.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taoTaiKhoanGUI.getBtnThem().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taoTaiKhoanGUI.getBtnThem().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @SuppressWarnings("UnnecessaryReturnStatement")
            private boolean kiemTraThongTin(String matKhau, String nhapLai) {
                if (matKhau.replaceAll("\\s", "").equals("")) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu không được bỏ trống", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                if (matKhau.length() < 4) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu phải dài ít nhất 4 ký tự", "Warning", JOptionPane.WARNING_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "Mật khẩu không trùng khớp", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                return true;
            }

            private void xuLyDuLieu(String maNhanVien, String matKhau, String maQuyen) {
                TAIKHOAN tk;
                        
                if (maQuyen.equals("QN")) {
                    tk = new TAIKHOAN(maNhanVien, matKhau, 1, null);
                } else {
                    tk = new TAIKHOAN(maNhanVien, matKhau, 1, maQuyen);
                }
                taoTaiKhoanGUI.getListTaiKhoan().add(tk);
                DaoTaiKhoan.getInstance().insert(tk);
            }
        });

        taoTaiKhoanGUI.getBtnHuy().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaoTaiKhoanBUS]: Huy");
                taoTaiKhoanGUI.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taoTaiKhoanGUI.getBtnHuy().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taoTaiKhoanGUI.getBtnHuy().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

}
