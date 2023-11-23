package BUS;

import DAO.DaoChiTietPhanQuyen;
import DAO.DaoQuyen;
import DTO.CHITIETPHANQUYEN;
import DTO.CHUCNANG;
import DTO.PHANQUYEN;
import GUI.SuaQuyenGUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhin
 */
public class SuaQuyenBUS {

    private SuaQuyenGUI suaQuyenGUI;

    public SuaQuyenBUS(SuaQuyenGUI suaQuyenGUI) {
        this.suaQuyenGUI = suaQuyenGUI;
        event();
    }

    private void event() {
        windowsEvent();
        suaEvent();
    }

    private void windowsEvent() {
        suaQuyenGUI.getPnTitle().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                suaQuyenGUI.setpX(e.getX());
                suaQuyenGUI.setpY(e.getY());
            }
        });
        suaQuyenGUI.getPnTitle().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                suaQuyenGUI.setLocation(suaQuyenGUI.getLocation().x + e.getX() - suaQuyenGUI.getpX(), suaQuyenGUI.getLocation().y + e.getY() - suaQuyenGUI.getpY());
            }
        });

        suaQuyenGUI.getLbExit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                suaQuyenGUI.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                suaQuyenGUI.getLbExit().setBackground(new Color(255, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                suaQuyenGUI.getLbExit().setBackground(null);
            }
        });

        suaQuyenGUI.getTxtTenQuyen().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (suaQuyenGUI.getTxtTenQuyen().getText().equals("Tên quyền...")) {
                    suaQuyenGUI.getTxtTenQuyen().setText("");
                    suaQuyenGUI.getTxtTenQuyen().setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (suaQuyenGUI.getTxtTenQuyen().getText().equals("")) {
                    suaQuyenGUI.getTxtTenQuyen().setText("Tên quyền...");
                    suaQuyenGUI.getTxtTenQuyen().setForeground(Color.lightGray);
                }
            }
        });

        suaQuyenGUI.getTxtMoTa().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (suaQuyenGUI.getTxtMoTa().getText().equals("Mô tả...")) {
                    suaQuyenGUI.getTxtMoTa().setText("");
                    suaQuyenGUI.getTxtMoTa().setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (suaQuyenGUI.getTxtMoTa().getText().equals("")) {
                    suaQuyenGUI.getTxtMoTa().setText("Mô tả...");
                    suaQuyenGUI.getTxtMoTa().setForeground(Color.lightGray);
                }
            }
        });
    }

    private void suaEvent() {
        suaQuyenGUI.getBtnLuu().addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings("static-access")
            public void mouseClicked(MouseEvent e) {
                System.out.println("[SuaQuyenBUS]: Luu");
                String ten = suaQuyenGUI.getTxtTenQuyen().getText();
                String moTa;
                if (suaQuyenGUI.getTxtMoTa().getText().equals("Mô tả...") || suaQuyenGUI.getTxtMoTa().getText().replaceAll(" ", "").equals("")) {
                    moTa = null;
                } else {
                    moTa = suaQuyenGUI.getTxtMoTa().getText();
                }

                /* Sửa tên quyền, mô tả trong Database và (QuyenBUS)listQuyen */
                for (PHANQUYEN pq : suaQuyenGUI.getListQuyen()) {
                    if (pq.getMaQuyen().equals(suaQuyenGUI.getQuyen().getMaQuyen())) {
                        pq.setTenQuyen(ten);
                        pq.setMoTaQuyen(moTa);
                        DaoQuyen.getInstance().update(pq);
                        break;
                    }
                }

                /* Sửa chức năng của quyền trong Database và (QuyenBUS)listChucNang */
                // Thêm mã chức năng vào CHITIETPHANQUYEN và vào (QuyenBUS)listChucNang
                for (JCheckBox c : suaQuyenGUI.getChkChucNang()) {
                    if (c.isSelected()) {
                        // Lay ma cn cua ten chuc nang tu checkbox
                        String maCN = "";

                        for (CHUCNANG cn : suaQuyenGUI.getListChucNang()) {
                            if (c.getText().equals(cn.getTenChucNang())) {
                                maCN = cn.getMaChucNang();
                                break;
                            }
                        }

                        // Kiem tra trong danh sach chuc nang cua quyen dang sua
                        boolean exist = false;

                        for (CHITIETPHANQUYEN ctpq : suaQuyenGUI.getQuyenDuocSua()) {
                            // Neu da ton tai
                            if (maCN.equals(ctpq.getMaChucNang())) {
                                exist = true;
                                break;
                            }
                        }

                        // Neu chua ton tai
                        if (!exist) {
                            CHITIETPHANQUYEN ctpqMoi = new CHITIETPHANQUYEN(suaQuyenGUI.getQuyen().getMaQuyen(), maCN);
                            suaQuyenGUI.getListPhanQuyen().add(ctpqMoi);
                            DaoChiTietPhanQuyen.getInstance().insert(ctpqMoi);
                        }
                    } else {
                        // Lay ma cn cua ten chuc nang tu checkbox khong duoc chon
                        String maCN = "";

                        for (CHUCNANG cn : suaQuyenGUI.getListChucNang()) {
                            if (c.getText().equals(cn.getTenChucNang())) {
                                maCN = cn.getMaChucNang();
                                break;
                            }
                        }

                        // Kiem tra trong chi tiet phan quyen neu ton tai thi xoa
                        boolean exist = false;

                        for (CHITIETPHANQUYEN ctpq : suaQuyenGUI.getListPhanQuyen()) {
                            if (maCN.equals(ctpq.getMaChucNang())) {
                                exist = true;
                                break;
                            }
                        }
                        if (exist) {

                            int index = 0;
                            for (CHITIETPHANQUYEN ct : suaQuyenGUI.getListPhanQuyen()) {
                                if (ct.getMaQuyen().equals(suaQuyenGUI.getQuyen().getMaQuyen()) && ct.getMaChucNang().equals(maCN)) {
                                    DaoChiTietPhanQuyen.getInstance().delete(ct);
                                    suaQuyenGUI.getListPhanQuyen().remove(index);
                                    break;
                                }
                                index++;
                            }
                        }
                    }
                }

                JOptionPane option = new JOptionPane();
                option.showMessageDialog(suaQuyenGUI, "Sửa thành công");
                QuyenBUS.sortListQuyenByMaQuyen(suaQuyenGUI.getListQuyen());
                QuyenBUS.lamMoi();
                TaiKhoanBUS.setListQuyen(suaQuyenGUI.getListQuyen());
                suaQuyenGUI.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                suaQuyenGUI.getBtnLuu().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                suaQuyenGUI.getBtnLuu().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        suaQuyenGUI.getBtnHuy().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaoQuyenBUS]: Huy");
                suaQuyenGUI.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                suaQuyenGUI.getBtnHuy().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                suaQuyenGUI.getBtnHuy().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

}
