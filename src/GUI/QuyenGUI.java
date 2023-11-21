package GUI;

import BUS.QuyenBUS;
import GiaoDienChuan.MyTable;
import GiaoDienChuan.RefreshButton;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QuyenGUI extends JPanel {

    private JPanel pnTimKiem;

    private JLabel lbTimKiem;
    private JTextField txtTimKiem;
    private JPanel pnButton;

    private JButton btnLamMoi, btnThem, btnSua, btnXoa;

    private MyTable tbQuyen;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public QuyenGUI() {
        init();
        new QuyenBUS(this);
    }

    private void init() {
        lbTimKiem = new JLabel(" Tìm kiếm ");
        lbTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));

        txtTimKiem = new JTextField("Tìm kiếm...");
        txtTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtTimKiem.setForeground(Color.lightGray);

        // Panel button
        pnButton = new JPanel(new FlowLayout(FlowLayout.LEADING, 4, 0));

        btnLamMoi = new RefreshButton();
        btnThem = new ThemButton();
        btnSua = new SuaButton();
        btnXoa = new XoaButton();

        Dimension dimensionButton = new Dimension(100, 30);
        btnLamMoi.setPreferredSize(new Dimension(120, 30));
        btnThem.setPreferredSize(dimensionButton);
        btnSua.setPreferredSize(dimensionButton);
        btnXoa.setPreferredSize(dimensionButton);

        pnButton.add(btnLamMoi);
        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);

        // Panel tim kiem
        pnTimKiem = new JPanel(new BorderLayout(0, 0));
        pnTimKiem.add(lbTimKiem, BorderLayout.WEST);
        pnTimKiem.add(txtTimKiem, BorderLayout.CENTER);
        pnTimKiem.add(pnButton, BorderLayout.EAST);

        // Quyen table
        tbQuyen = new MyTable();
        tbQuyen.setHeaders(new String[]{"Mã quyền", "Tên quyền", "Mô tả"});
        tbQuyen.getTable().setRowHeight(20);

        // QuyenGui
        setLayout(new BorderLayout(0, 5));
        add(pnTimKiem, BorderLayout.NORTH);
        add(tbQuyen, BorderLayout.CENTER);
    }

    // Getter
    public JTextField getTxtTimKiem() {
        return txtTimKiem;
    }

    public JButton getBtnLamMoi() {
        return btnLamMoi;
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

    public MyTable getTbQuyen() {
        return tbQuyen;
    }

    // Setter
    public void setTbQuyen(MyTable tbQuyen) {
        this.tbQuyen = tbQuyen;
    }

}
