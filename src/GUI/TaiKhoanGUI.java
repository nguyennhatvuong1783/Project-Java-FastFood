package GUI;

import BUS.TaiKhoanBUS;
import GiaoDienChuan.ExportExcelButton;
import GiaoDienChuan.ImportExcelButton;
import GiaoDienChuan.MyTable;
import GiaoDienChuan.RefreshButton;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TaiKhoanGUI extends JPanel {

    private JPanel pnTimKiem;

    private JLabel lbTimKiem;
    private JTextField txtTimKiem;
    private JPanel pnButton;

    private JButton btnThem, btnSua, btnXoa, btnLamMoi;

    private MyTable tbTaiKhoan;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public TaiKhoanGUI() {
        init();
        new TaiKhoanBUS(this);
    }

    private void init() {
        /* Control Panel */
        JPanel pnControl = new JPanel(new GridLayout(2, 1, 0, 5));

        // Panel button
        pnButton = new JPanel(new GridLayout(1, 5, 5, 0));

        btnThem = new ThemButton();
        btnSua = new SuaButton();
        btnXoa = new XoaButton();

        Dimension dimensionButton = new Dimension(0, 30);
        btnThem.setPreferredSize(dimensionButton);
        btnSua.setPreferredSize(dimensionButton);
        btnXoa.setPreferredSize(dimensionButton);

        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);

        // Panel tim kiem
        pnTimKiem = new JPanel(new BorderLayout(0, 0));

        lbTimKiem = new JLabel(" Tìm kiếm ");
        lbTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));

        txtTimKiem = new JTextField("Tìm kiếm...");
        txtTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtTimKiem.setForeground(Color.lightGray);

        btnLamMoi = new RefreshButton();
        btnLamMoi.setPreferredSize(new Dimension(120, 30));

        pnTimKiem.add(lbTimKiem, BorderLayout.WEST);
        pnTimKiem.add(txtTimKiem, BorderLayout.CENTER);
        pnTimKiem.add(btnLamMoi, BorderLayout.EAST);

        // Controll Panel add
        pnControl.add(pnButton);
        pnControl.add(pnTimKiem);

        /* Tai khoan table */
        tbTaiKhoan = new MyTable();
        tbTaiKhoan.setHeaders(new String[]{"Tên đăng nhập", "Mật khẩu", "Mã quyền"});
        tbTaiKhoan.getTable().setRowHeight(20);

        // TaiKhoanGUI
        setLayout(new BorderLayout(0, 5));
        add(pnControl, BorderLayout.NORTH);
        add(tbTaiKhoan, BorderLayout.CENTER);
    }

    // Getter
    public JTextField getTxtTimKiem() {
        return txtTimKiem;
    }

    public JButton getBtnThem() {
        return btnThem;
    }

    public JButton getBtnSua() {
        return btnSua;
    }

    public JButton getBtnXoa() {
        return btnXoa;
    }

    public JButton getBtnLamMoi() {
        return btnLamMoi;
    }

    public MyTable getTbTaiKhoan() {
        return tbTaiKhoan;
    }

    // Setter
}
