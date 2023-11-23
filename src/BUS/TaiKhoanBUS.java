package BUS;

import DAO.DaoNhanVien;
import DAO.DaoQuyen;
import DAO.DaoTaiKhoan;
import DTO.NHANVIEN;
import DTO.PHANQUYEN;
import DTO.TAIKHOAN;
import GUI.SuaTaiKhoanGUI;
import GUI.TaiKhoanGUI;
import GUI.TaoTaiKhoanGUI;
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
public class TaiKhoanBUS {

    private static ArrayList<NHANVIEN> listNhanVien;
    private static ArrayList<TAIKHOAN> listTaiKhoan;
    private static ArrayList<PHANQUYEN> listQuyen;
    private static ArrayList<TAIKHOAN> listTimKiem;

    private TaiKhoanGUI taiKhoanGUI;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public TaiKhoanBUS(TaiKhoanGUI taiKhoanGUI) {
        listNhanVien = DaoNhanVien.getInstance().selectAll();
        listTaiKhoan = DaoTaiKhoan.getInstance().selectAll();
        listQuyen = DaoQuyen.getInstance().selectAll();
        listTimKiem = new ArrayList<>();
        this.taiKhoanGUI = taiKhoanGUI;

        addDataToTable();
        event();
    }

    private void addDataToTable() {
        sortTaiKhoanByMa(listTaiKhoan);
        for (TAIKHOAN tk : listTaiKhoan) {
            if (tk.getTrangThai() == 1) {
                String[] row = new String[]{tk.getUserName(), tk.getMatKhau(), tk.getMaQuyen()};
                taiKhoanGUI.getTbTaiKhoan().addRow(row);
            }
        }
    }

    private void event() {
        importEvent();
        exportEvent();
        addEvent();
        updateEvent();
        deleteEvent();
        searchEvent();
        refreshEvent();
    }

    private void importEvent() {
        taiKhoanGUI.getBtnImport().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaiKhoanBUS]: Import");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taiKhoanGUI.getBtnImport().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taiKhoanGUI.getBtnImport().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void exportEvent() {
        taiKhoanGUI.getBtnExport().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaiKhoanBUS]: Export");                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taiKhoanGUI.getBtnExport().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taiKhoanGUI.getBtnExport().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void addEvent() {
        taiKhoanGUI.getBtnThem().addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings("ResultOfObjectAllocationIgnored")
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaiKhoanBUS]: Add");
                new TaoTaiKhoanGUI(listNhanVien, listTaiKhoan, listQuyen);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taiKhoanGUI.getBtnThem().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taiKhoanGUI.getBtnThem().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void updateEvent() {
        taiKhoanGUI.getBtnSua().addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings("ResultOfObjectAllocationIgnored")
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaiKhoanBUS]: Update");
                int selectedRow = taiKhoanGUI.getTbTaiKhoan().getTable().getSelectedRow();
                String[] rowData = new String[3];

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Chọn tài khoản cần sửa!");
                } else {
                    for (int i = 0; i < taiKhoanGUI.getTbTaiKhoan().getTable().getColumnCount(); i++) {
                        Object value = taiKhoanGUI.getTbTaiKhoan().getTable().getValueAt(selectedRow, i);
                        rowData[i] = (String) value;
                    }
                    TAIKHOAN tkSua = new TAIKHOAN(rowData[0], rowData[1], 1, rowData[2]);
                    new SuaTaiKhoanGUI(listTaiKhoan, listQuyen, tkSua);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taiKhoanGUI.getBtnSua().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taiKhoanGUI.getBtnSua().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void deleteEvent() {
        taiKhoanGUI.getBtnXoa().addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings({"element-type-mismatch", "ResultOfObjectAllocationIgnored"})
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaiKhoanBUS]: Delete");
                int selectedRow = taiKhoanGUI.getTbTaiKhoan().getTable().getSelectedRow();
                String[] taiKhoanStr = new String[3];

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Chọn tài khoản muốn xóa!");
                } else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa quyền", "Xóa quyền", JOptionPane.YES_NO_OPTION);
                    switch (confirm) {
                        case 0 -> {
                            System.out.println("[TaiKhoanBUS]: Chon yes");
                            for (int i = 0; i < taiKhoanGUI.getTbTaiKhoan().getTable().getColumnCount(); i++) {
                                Object value = taiKhoanGUI.getTbTaiKhoan().getTable().getValueAt(selectedRow, i);
                                taiKhoanStr[i] = (String) value;
                            }

                            TAIKHOAN taiKhoanXoa = new TAIKHOAN(taiKhoanStr[0], taiKhoanStr[1], 1, taiKhoanStr[2]);
                            new XoaTaiKhoanBUS(listTaiKhoan, taiKhoanXoa);
                        }
                        case 1 ->
                            System.out.println("[TaiKhoanBUS]: Chon no");
                        default ->
                            throw new AssertionError();
                    }
                }
                refresh();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taiKhoanGUI.getBtnXoa().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taiKhoanGUI.getBtnXoa().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void searchEvent() {
        // Search
        taiKhoanGUI.getTxtTimKiem().addKeyListener(new KeyAdapter() {
            @Override
            @SuppressWarnings("IndexOfReplaceableByContains")
            public void keyTyped(KeyEvent e) {
                String findText;

                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    findText = taiKhoanGUI.getTxtTimKiem().getText();
                } else {
                    findText = taiKhoanGUI.getTxtTimKiem().getText() + e.getKeyChar();
                }
                findText = findText.toLowerCase();
                System.out.println("[TaiKhoanBUS: txtTimKiem.toLower]:" + findText);

                if (findText.replaceAll("\\s", "").equals("")) {
                    // Trường hợp ô tìm kiếm trống
                    listTimKiem.removeAll(listTimKiem);
                } else {
                    listTimKiem.removeAll(listTimKiem);
                    // Tim kiem theo username va ma quyen
                    for (TAIKHOAN tk : listTaiKhoan) {
                        if (tk.getTrangThai() == 1) {
                            if (tk.getUserName().toLowerCase().indexOf(findText) >= 0) {
                                listTimKiem.add(tk);
                            }
                            if (tk.getMaQuyen() != null && tk.getMaQuyen().toLowerCase().indexOf(findText) >= 0) {
                                listTimKiem.add(tk);
                            }
                        }
                    }
                    if (listTimKiem.isEmpty()) {
                        // Trường hợp không tìm thấy kết quả, tạo một tài khoản với username emtpy
                        listTimKiem.add(new TAIKHOAN("empty", "", 0, ""));
                    }
                }
                refresh();
            }
        });

        // Focus
        taiKhoanGUI.getTxtTimKiem().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (taiKhoanGUI.getTxtTimKiem().getText().equals("Tìm kiếm...")) {
                    taiKhoanGUI.getTxtTimKiem().setText("");
                    taiKhoanGUI.getTxtTimKiem().setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (taiKhoanGUI.getTxtTimKiem().getText().equals("")) {
                    taiKhoanGUI.getTxtTimKiem().setText("Tìm kiếm...");
                    taiKhoanGUI.getTxtTimKiem().setForeground(Color.lightGray);
                }
            }
        });
    }

    private void refreshEvent() {
        taiKhoanGUI.getBtnLamMoi().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[TaiKhoanBUS]: Refresh");
                sortTaiKhoanByMa(listTaiKhoan);
                refresh();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                taiKhoanGUI.getBtnLamMoi().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taiKhoanGUI.getBtnLamMoi().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    // Method
    public void sortTaiKhoanByMa(ArrayList<TAIKHOAN> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getUserName().toLowerCase().compareTo(list.get(j).getUserName().toLowerCase()) > 0) {
                    TAIKHOAN tk = new TAIKHOAN(list.get(i).getUserName(), list.get(i).getMatKhau(), list.get(i).getTrangThai(), list.get(i).getMaQuyen());

                    list.get(i).setUserName(list.get(j).getUserName());
                    list.get(i).setMatKhau(list.get(j).getMatKhau());
                    list.get(i).setTrangThai(list.get(j).getTrangThai());
                    list.get(i).setMaQuyen(list.get(j).getMaQuyen());

                    list.get(j).setUserName(tk.getUserName());
                    list.get(j).setMatKhau(tk.getMatKhau());
                    list.get(j).setTrangThai(tk.getTrangThai());
                    list.get(j).setMaQuyen(tk.getMaQuyen());
                }
            }
        }
    }

    public void refresh() {
        ArrayList<TAIKHOAN> tableList;
        int listRow;
        int tableRow = taiKhoanGUI.getTbTaiKhoan().getTable().getRowCount();

        if (listTimKiem.isEmpty()) {
            tableList = new ArrayList<>();

            for (TAIKHOAN tk : listTaiKhoan) {
                if (tk.getTrangThai() == 1) {
                    tableList.add(tk);
                }
            }
            listRow = tableList.size();
        } else {
            if (listTimKiem.size() == 1 && listTimKiem.get(0).getUserName().equals("empty")) {
                // Không tìm thấy kết quả
                tableList = listTimKiem;
                listRow = 0;
            } else {
                // Tìm thấy kết quả
                tableList = listTimKiem;
                listRow = listTimKiem.size();
            }
        }

        if (listRow > tableRow) {
            int i = 0;
            while (i < listRow) {
                if (i < tableRow) {
                    setRowData(i, new String[]{tableList.get(i).getUserName(), tableList.get(i).getMatKhau(), tableList.get(i).getMaQuyen()});
                    i++;
                } else {
                    taiKhoanGUI.getTbTaiKhoan().addRow(new String[]{tableList.get(i).getUserName(), tableList.get(i).getMatKhau(), tableList.get(i).getMaQuyen()});
                    i++;
                }
            }
        } else if (listRow < tableRow) {
            int i = 0;
            while (i < tableRow) {
                if (i < listRow) {
                    setRowData(i, new String[]{tableList.get(i).getUserName(), tableList.get(i).getMatKhau(), tableList.get(i).getMaQuyen()});
                    i++;
                } else {
                    taiKhoanGUI.getTbTaiKhoan().getModel().removeRow(listRow);
                    i++;
                }
            }
        } else {
            int i = 0;
            for (TAIKHOAN tk : tableList) {
                setRowData(i++, new String[]{tk.getUserName(), tk.getMatKhau(), tk.getMaQuyen()});
            }
        }
    }

    public void setRowData(int index, String[] data) {
        for (int i = 0; i < data.length; i++) {
            taiKhoanGUI.getTbTaiKhoan().getTable().setValueAt(data[i], index, i);
        }
    }

    // Getter
    public static ArrayList<NHANVIEN> getListNhanVien() {
        return listNhanVien;
    }

    public static ArrayList<TAIKHOAN> getListTaiKhoan() {
        return listTaiKhoan;
    }

    public static ArrayList<PHANQUYEN> getListQuyen() {
        return listQuyen;
    }
    
    
    // Setter
    public static void setListQuyen(ArrayList<PHANQUYEN> listQuyen) {
        TaiKhoanBUS.listQuyen = listQuyen;
    }
    
}
