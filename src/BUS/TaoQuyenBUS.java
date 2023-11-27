package BUS;

import DAO.DaoChiTietPhanQuyen;
import DAO.DaoQuyen;
import DTO.CHITIETPHANQUYEN;
import DTO.PHANQUYEN;
import GUI.TaoQuyenGUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhin
 */
public class TaoQuyenBUS {

    private TaoQuyenGUI taoQuyenGUI;

    public TaoQuyenBUS(TaoQuyenGUI taoQuyenGUI) {
        this.taoQuyenGUI = taoQuyenGUI;
        event();
    }

    private void event() {
        windowsEvent();
        quyenEvent();
    }

    private void windowsEvent() {
        taoQuyenGUI.getPnTitle().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                taoQuyenGUI.setpX(e.getX());
                taoQuyenGUI.setpY(e.getY());
            }
        });
        taoQuyenGUI.getPnTitle().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                taoQuyenGUI.setLocation(taoQuyenGUI.getLocation().x + e.getX() - taoQuyenGUI.getpX(), taoQuyenGUI.getLocation().y + e.getY() - taoQuyenGUI.getpY());
            }
        });

        taoQuyenGUI.getLbExit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                taoQuyenGUI.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taoQuyenGUI.getLbExit().setBackground(new Color(255, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taoQuyenGUI.getLbExit().setBackground(null);
            }
        });

        taoQuyenGUI.getTxtTenQuyen().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (taoQuyenGUI.getTxtTenQuyen().getText().equals("Tên quyền...")) {
                    taoQuyenGUI.getTxtTenQuyen().setText("");
                    taoQuyenGUI.getTxtTenQuyen().setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (taoQuyenGUI.getTxtTenQuyen().getText().equals("")) {
                    taoQuyenGUI.getTxtTenQuyen().setText("Tên quyền...");
                    taoQuyenGUI.getTxtTenQuyen().setForeground(Color.lightGray);
                }
            }
        });

        taoQuyenGUI.getTxtMoTa().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (taoQuyenGUI.getTxtMoTa().getText().equals("Mô tả...")) {
                    taoQuyenGUI.getTxtMoTa().setText("");
                    taoQuyenGUI.getTxtMoTa().setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (taoQuyenGUI.getTxtMoTa().getText().equals("")) {
                    taoQuyenGUI.getTxtMoTa().setText("Mô tả...");
                    taoQuyenGUI.getTxtMoTa().setForeground(Color.lightGray);
                }
            }
        });
    }

    private void quyenEvent() {
        taoQuyenGUI.getBtnThem().addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings("static-access")
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaoQuyenBUS]: Them");
                String ten;
                String moTa;

                if (taoQuyenGUI.getTxtTenQuyen().getText().equals("Tên quyền...")) {
                    JOptionPane warning = new JOptionPane();
                    warning.showMessageDialog(null, "Tên quyền không được bỏ trống");
                    return;
                } else {
                    ten = taoQuyenGUI.getTxtTenQuyen().getText();
                }
                if (taoQuyenGUI.getTxtMoTa().getText().equals("Mô tả...")) {
                    moTa = null;
                } else {
                    moTa = taoQuyenGUI.getTxtMoTa().getText();
                }

                // Them Database Quyen, them (QuyenBUS)listQuyen va sua (TaiKhoanBUS)listQuyen
                PHANQUYEN quyen = new PHANQUYEN(taoQuyenGUI.getMaQuyenMoi(), ten, moTa, 0);
                DaoQuyen.getInstance().insert(quyen);
                taoQuyenGUI.getListQuyen().add(quyen);
                TaiKhoanBUS.setListQuyen(taoQuyenGUI.getListQuyen());

                // Them Database ChiTietPhanQuyen
                ArrayList<CHITIETPHANQUYEN> listPhanQuyen = new ArrayList<>();
                for (JCheckBox c : taoQuyenGUI.getChkChucNang()) {
                    if (c.isSelected()) {
                        CHITIETPHANQUYEN ctpq = new CHITIETPHANQUYEN(taoQuyenGUI.getMaQuyenMoi(), DaoQuyen.getInstance().getMaQuyenByTen(taoQuyenGUI.getListChucNang(), c.getText()));
                        listPhanQuyen.add(ctpq);
                    }
                }
                for (CHITIETPHANQUYEN ct : listPhanQuyen) {
                    DaoChiTietPhanQuyen.getInstance().insert(ct);
                    taoQuyenGUI.getListPhanQuyen().add(ct);
                }
                JOptionPane option = new JOptionPane();
                option.showMessageDialog(taoQuyenGUI, "Thêm thành công");
                QuyenBUS.sortListQuyenByMaQuyen(taoQuyenGUI.getListQuyen());
                QuyenBUS.lamMoi();
                TaiKhoanBUS.setListQuyen(taoQuyenGUI.getListQuyen());
                taoQuyenGUI.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taoQuyenGUI.getBtnThem().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taoQuyenGUI.getBtnThem().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        taoQuyenGUI.getBtnHuy().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaoQuyenBUS]: Huy");
                taoQuyenGUI.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taoQuyenGUI.getBtnHuy().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taoQuyenGUI.getBtnHuy().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

}
