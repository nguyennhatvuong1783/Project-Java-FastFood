package BUS;

import DAO.DaoChiTietPhanQuyen;
import DAO.DaoChucNang;
import DAO.DaoQuyen;
import DTO.CHITIETPHANQUYEN;
import DTO.CHUCNANG;
import DTO.PHANQUYEN;
import GUI.QuyenGUI;
import GUI.SuaQuyenGUI;
import GUI.TaoQuyenGUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhin
 */
public class QuyenBUS {

    private static ArrayList<PHANQUYEN> listQuyen, listTimKiem;
    private static ArrayList<CHITIETPHANQUYEN> listPhanQuyen;
    private static ArrayList<CHUCNANG> listChucNang;

    private static QuyenGUI quyenGUI;

    @SuppressWarnings("static-access")
    public QuyenBUS(QuyenGUI quyenGUI) {
        this.listQuyen = DaoQuyen.getInstance().selectAll();
        this.listTimKiem = new ArrayList<>();
        this.listPhanQuyen = DaoChiTietPhanQuyen.getInstance().selectAll();
        this.listChucNang = DaoChucNang.getInstance().selectAll();
        this.quyenGUI = quyenGUI;
        sortListQuyenByMaQuyen(listQuyen);
        addDataToTable();
        event();
    }

    private void addDataToTable() {
        for (PHANQUYEN q : listQuyen) {
            String[] row = new String[]{q.getMaQuyen(), q.getTenQuyen(), q.getMoTaQuyen()};
            quyenGUI.getTbQuyen().addRow(row);
        }
    }

    private void event() {
        themEvent();
        suaEvent();
        xoaEvent();
        searchEvent();
        lamMoiEvent();
    }

    private void searchEvent() {
        // Key
        quyenGUI.getTxtTimKiem().addKeyListener(new KeyAdapter() {
            @Override
            @SuppressWarnings("IndexOfReplaceableByContains")
            public void keyTyped(KeyEvent e) {
                String findText;
                
                if (e.getKeyChar() == KeyEvent.VK_D) { System.out.println("DDDD");}

                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE) {
                    findText = quyenGUI.getTxtTimKiem().getText();
                } else {
                    findText = quyenGUI.getTxtTimKiem().getText() + e.getKeyChar();
                }
                findText = findText.toLowerCase();
                System.out.println("[QuyenBUS: txtTimKiem.toLower]: \"" + findText + "\"");

                if (findText.replaceAll("\\s", "").equals("")) {
                    // Truong hop txtTimKiem trong
                    if (!listTimKiem.isEmpty()) {
                        listTimKiem.removeAll(listTimKiem);
                    }
                } else {
                    listTimKiem.removeAll(listTimKiem);

                    // Tìm theo mã quyền và tên quyền
                    for (PHANQUYEN q : listQuyen) {
                        if (q.getMaQuyen().toLowerCase().indexOf(findText) >= 0 || q.getTenQuyen().toLowerCase().indexOf(findText) >= 0) {
                            listTimKiem.add(q);
                        }
                    }
                    if (listTimKiem.isEmpty()) {
                        // Tim kiem khong co ket qua, danh sach tim kiem them mot doi tuong co maquyen empty
                        listTimKiem.add(new PHANQUYEN("empty", "", "", 0));
                    }
                }

                sortListQuyenByMaQuyen(listTimKiem);
                lamMoi();
            }
        });

        // Place Holder
        quyenGUI.getTxtTimKiem().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (quyenGUI.getTxtTimKiem().getText().equals("Tìm kiếm...")) {
                    quyenGUI.getTxtTimKiem().setText("");
                    quyenGUI.getTxtTimKiem().setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (quyenGUI.getTxtTimKiem().getText().equals("")) {
                    quyenGUI.getTxtTimKiem().setText("Tìm kiếm...");
                    quyenGUI.getTxtTimKiem().setForeground(Color.lightGray);
                }
            }
        });
    }

    private void lamMoiEvent() {
        quyenGUI.getBtnLamMoi().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[QuyenBUS]: Refresh");
                sortListQuyenByMaQuyen(listQuyen);
                lamMoi();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                quyenGUI.getBtnLamMoi().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quyenGUI.getBtnLamMoi().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void themEvent() {
        quyenGUI.getBtnThem().addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings("ResultOfObjectAllocationIgnored")
            public void mouseClicked(MouseEvent e) {
                System.out.println("[QuyenBUS]: Them");
                new TaoQuyenGUI();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                quyenGUI.getBtnThem().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quyenGUI.getBtnThem().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void suaEvent() {
        quyenGUI.getBtnSua().addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings({"static-access", "ResultOfObjectAllocationIgnored"})
            public void mouseClicked(MouseEvent e) {
                System.out.println("[QuyenBUS]: Sua");
                int selectedRow = quyenGUI.getTbQuyen().getTable().getSelectedRow();
                String[] quyenStr = new String[3];

                if (selectedRow == -1) {
                    JOptionPane option = new JOptionPane();
                    option.showMessageDialog(null, "Chọn quyền cần sửa!");
                } else {
                    for (int i = 0; i < quyenGUI.getTbQuyen().getTable().getColumnCount(); i++) {
                        Object value = quyenGUI.getTbQuyen().getTable().getValueAt(selectedRow, i);
                        quyenStr[i] = (String) value;
                    }

                    // Lấy dữ liêu của dòng được chọn, tạo đối tượng quyen
                    PHANQUYEN quyen = new PHANQUYEN(quyenStr[0], quyenStr[1], quyenStr[2], 0);
                    // Lấy danh sách quyền của đối tượng quyen
                    ArrayList<CHITIETPHANQUYEN> ctpqDuocSua = DaoChiTietPhanQuyen.getInstance().selectAllById(quyen.getMaQuyen());

                    new SuaQuyenGUI(quyen, ctpqDuocSua);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                quyenGUI.getBtnSua().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quyenGUI.getBtnSua().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void xoaEvent() {
        quyenGUI.getBtnXoa().addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings({"static-access", "ResultOfObjectAllocationIgnored"})
            public void mouseClicked(MouseEvent e) {
                System.out.println("[QuyenBUS]: Xoa");
                int selectedRow = quyenGUI.getTbQuyen().getTable().getSelectedRow();
                String[] quyenStr = new String[3];

                if (selectedRow == -1) {
                    JOptionPane option = new JOptionPane();
                    option.showMessageDialog(null, "Chọn quyền cần xóa");
                } else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa quyền", "Xóa quyền", JOptionPane.YES_NO_OPTION);
                    switch (confirm) {
                        case 0 -> {
                            System.out.println("[QuyenBUS]: Chon yes");
                            for (int i = 0; i < quyenGUI.getTbQuyen().getTable().getColumnCount(); i++) {
                                Object value = quyenGUI.getTbQuyen().getTable().getValueAt(selectedRow, i);
                                quyenStr[i] = (String) value;
                            }

                            PHANQUYEN quyen = new PHANQUYEN(quyenStr[0], quyenStr[1], quyenStr[2], 0);

                            new XoaQuyenBUS(quyen);
                        }
                        case 1 ->
                            System.out.println("[QuyenBUS]: Chon no");
                        default ->
                            throw new AssertionError();
                    }
                }
                lamMoi();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                quyenGUI.getBtnXoa().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quyenGUI.getBtnXoa().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    // Method
    public static void setRowData(int index, String[] data) {
        for (int i = 0; i < data.length; i++) {
            quyenGUI.getTbQuyen().getTable().setValueAt(data[i], index, i);
        }
    }

    public static void sortListQuyenByMaQuyen(ArrayList<PHANQUYEN> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int quyenI = Integer.parseInt(list.get(i).getMaQuyen().substring(1));
                int quyenJ = Integer.parseInt(list.get(j).getMaQuyen().substring(1));
                if (quyenI > quyenJ) {
                    String ma = list.get(i).getMaQuyen();
                    String ten = list.get(i).getTenQuyen();
                    String moTa = list.get(i).getMoTaQuyen();
                    int trangThai = list.get(i).getTrangThai();

                    list.get(i).setMaQuyen(list.get(j).getMaQuyen());
                    list.get(i).setTenQuyen(list.get(j).getTenQuyen());
                    list.get(i).setMoTaQuyen(list.get(j).getMoTaQuyen());
                    list.get(i).setTrangThai(list.get(j).getTrangThai());

                    list.get(j).setMaQuyen(ma);
                    list.get(j).setTenQuyen(ten);
                    list.get(j).setMoTaQuyen(moTa);
                    list.get(j).setTrangThai(trangThai);
                }
            }
        }
    }

    public static void lamMoi() {
        ArrayList<PHANQUYEN> tableList;
        int listRow;
        int tableRow = quyenGUI.getTbQuyen().getTable().getRowCount();

        if (listTimKiem.isEmpty()) {
            tableList = listQuyen;
            listRow = listQuyen.size();
        } else {
            if (listTimKiem.size() == 1 && listTimKiem.get(0).getMaQuyen().equals("empty")) {
                // Truong hop tim kiem khong co ket qua
                tableList = listTimKiem;
                listRow = 0;
            } else {
                //Truong hop tim kiem co ket qua
                tableList = listTimKiem;
                listRow = listTimKiem.size();
            }
        }

        if (listRow > tableRow) {
            int i = 0;
            while (i < listRow) {
                if (i < tableRow) {
                    setRowData(i, new String[]{tableList.get(i).getMaQuyen(), tableList.get(i).getTenQuyen(), tableList.get(i).getMoTaQuyen()});
                    i++;
                } else {
                    quyenGUI.getTbQuyen().addRow(new String[]{tableList.get(i).getMaQuyen(), tableList.get(i).getTenQuyen(), tableList.get(i).getMoTaQuyen()});
                    i++;
                }
            }
        } else if (listRow < tableRow) {
            int i = 0;
            while (i < tableRow) {
                if (i < listRow) {
                    setRowData(i, new String[]{tableList.get(i).getMaQuyen(), tableList.get(i).getTenQuyen(), tableList.get(i).getMoTaQuyen()});
                    i++;
                } else {
                    quyenGUI.getTbQuyen().getModel().removeRow(listRow);
                    i++;
                }
            }
        } else {
            int i = 0;

            for (PHANQUYEN p : listTimKiem) {
                setRowData(i++, new String[]{p.getMaQuyen(), p.getTenQuyen(), p.getMoTaQuyen()});
            }
        }
    }

    // Getter
    public static ArrayList<PHANQUYEN> getListQuyen() {
        return listQuyen;
    }

    public static ArrayList<PHANQUYEN> getListTimKiem() {
        return listTimKiem;
    }

    public static ArrayList<CHITIETPHANQUYEN> getListPhanQuyen() {
        return listPhanQuyen;
    }

    public static ArrayList<CHUCNANG> getListChucNang() {
        return listChucNang;
    }

}
