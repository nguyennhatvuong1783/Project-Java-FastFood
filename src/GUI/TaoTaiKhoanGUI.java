package GUI;

import BUS.TaoTaiKhoanBUS;
import DTO.NHANVIEN;
import DTO.PHANQUYEN;
import DTO.TAIKHOAN;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Jhin
 */
public class TaoTaiKhoanGUI extends JDialog {

    private final Font lbFont = new Font("Times New Roman", Font.BOLD, 16);
    private final Font txtFont = new Font("Times New Roman", Font.PLAIN, 16);

    // List Data
    private ArrayList<NHANVIEN> listNhanVien;
    private ArrayList<TAIKHOAN> listTaiKhoan;
    private ArrayList<PHANQUYEN> listQuyen;

    private JPanel pnTitle, pnContent;
    private JLabel lbTitle, lbExit;

    private JLabel lbMaNhanVien, lbMatKhau, lbNhapLai, lbMaQuyen, borderPassword, borderRetype, lbHienThiMatKhau, lbHienThiNhapLai;
    private JComboBox<String> cbbMaNhanVien, cbbMaQuyen;
    private JPasswordField pwMatKhau, pwNhapLai;
    private JTextField txtMatKhau, txtNhapLai;

    private JButton btnThem, btnHuy;

    // Location
    private int pX, pY;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public TaoTaiKhoanGUI(ArrayList<NHANVIEN> listNhanVien, ArrayList<TAIKHOAN> listTaiKhoan, ArrayList<PHANQUYEN> listQuyen) {
        this.listNhanVien = listNhanVien;
        this.listTaiKhoan = listTaiKhoan;
        this.listQuyen = listQuyen;

        init();
        new TaoTaiKhoanBUS(this);
    }

    private void init() {
        /**
         * Title Panel
         */
        pnTitle = new JPanel(new BorderLayout(0, 0));
        pnTitle.setBackground(new Color(255, 165, 0));

        lbTitle = new JLabel("Thêm tài khoản", JLabel.CENTER);
        lbTitle.setPreferredSize(new Dimension(0, 32));
        lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lbTitle.setForeground(Color.white);

        lbExit = new JLabel(new ImageIcon(getClass().getResource("../icon_img/close-lblexit-20.png")));
        lbExit.setPreferredSize(new Dimension(42, 32));
        lbExit.setBackground(new Color(255, 165, 0));
        lbExit.setOpaque(true);

        pnTitle.add(lbTitle, BorderLayout.CENTER);
        pnTitle.add(lbExit, BorderLayout.EAST);

        /**
         * Content
         */
        pnContent = new JPanel(null);
        pnContent.setBackground(Color.white);

        lbMaNhanVien = new JLabel(" Mã nhân viên");
        lbMaNhanVien.setBounds(20, 10, 100, 30);
        lbMaNhanVien.setFont(lbFont);

        cbbMaNhanVien = new JComboBox<>(getMaNhanVienChuaCoTaiKhoan());
        cbbMaNhanVien.setBounds(20, 40, 320, 30);
        cbbMaNhanVien.setBorder(new LineBorder(new Color(255, 165, 0), 2));
        cbbMaNhanVien.setFont(txtFont);

        lbMatKhau = new JLabel(" Mật khẩu");
        lbMatKhau.setBounds(20, 90, 100, 30);
        lbMatKhau.setFont(lbFont);

        borderPassword = new JLabel();
        borderPassword.setBounds(20, 120, 320, 30);
        borderPassword.setBorder(new LineBorder(new Color(255, 165, 0), 2));

        pwMatKhau = new JPasswordField(20);
        pwMatKhau.setBounds(22, 122, 284, 26);
        pwMatKhau.setBorder(null);
        pwMatKhau.setFont(txtFont);

        JLabel separatePass = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(0, 0, 0, 24);
            }
        };
        separatePass.setBounds(310, 123, 1, 24);

        lbHienThiMatKhau = new JLabel(new ImageIcon(getClass().getResource("../icon_img/hidePassword.png")));
        lbHienThiMatKhau.setBounds(313, 123, 24, 24);

        lbNhapLai = new JLabel(" Nhập lại mật khẩu");
        lbNhapLai.setBounds(20, 170, 150, 30);
        lbNhapLai.setFont(lbFont);

        borderRetype = new JLabel();
        borderRetype.setBounds(20, 200, 320, 30);
        borderRetype.setBorder(new LineBorder(new Color(255, 165, 0), 2));

        pwNhapLai = new JPasswordField(20);
        pwNhapLai.setBounds(22, 202, 284, 26);
        pwNhapLai.setBorder(null);
        pwNhapLai.setFont(txtFont);

        JLabel separateRepass = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(0, 0, 0, 24);
            }
        };
        separateRepass.setBounds(310, 203, 1, 24);

        lbHienThiNhapLai = new JLabel(new ImageIcon(getClass().getResource("../icon_img/hidePassword.png")));
        lbHienThiNhapLai.setBounds(313, 203, 24, 24);

        txtMatKhau = new JTextField("");
        txtMatKhau.setBounds(22, 122, 284, 26);
        txtMatKhau.setBorder(null);
        txtMatKhau.setFont(txtFont);
        txtMatKhau.setVisible(false);

        txtNhapLai = new JTextField("");
        txtNhapLai.setBounds(22, 202, 284, 26);
        txtNhapLai.setBorder(null);
        txtNhapLai.setFont(txtFont);
        txtNhapLai.setVisible(false);

        JPanel separator = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(0, 0, 340, 0);
            }
        };
        separator.setBounds(10, 245, 340, 1);
        separator.setBackground(Color.white);

        lbMaQuyen = new JLabel(" Mã quyền");
        lbMaQuyen.setBounds(20, 250, 100, 30);
        lbMaQuyen.setFont(lbFont);

        cbbMaQuyen = new JComboBox<>(getQuyenTuList());
        cbbMaQuyen.setBounds(20, 280, 320, 30);
        cbbMaQuyen.setBorder(new LineBorder(new Color(255, 165, 0), 2));
        cbbMaQuyen.setFont(txtFont);

        btnThem = new JButton("Thêm", new ImageIcon(getClass().getResource("../icon_img/icons8-add-32.png")));
        btnThem.setBounds(40, 350, 120, 40);

        btnHuy = new JButton("Hủy", new ImageIcon(getClass().getResource("../icon_img/icons8-cancel-32.png")));
        btnHuy.setBounds(200, 350, 120, 40);

        // Add component
        pnContent.add(lbMaNhanVien);
        pnContent.add(cbbMaNhanVien);

        pnContent.add(lbMatKhau);
        pnContent.add(pwMatKhau);
        pnContent.add(txtMatKhau);
        pnContent.add(separatePass);
        pnContent.add(lbHienThiMatKhau);
        pnContent.add(borderPassword);

        pnContent.add(lbNhapLai);
        pnContent.add(pwNhapLai);
        pnContent.add(txtNhapLai);
        pnContent.add(separateRepass);
        pnContent.add(lbHienThiNhapLai);
        pnContent.add(borderRetype);

        pnContent.add(separator);
        pnContent.add(lbMaQuyen);
        pnContent.add(cbbMaQuyen);

        pnContent.add(btnThem);
        pnContent.add(btnHuy);

        /**
         * JFrame
         */
        getRootPane().setBorder(new LineBorder(Color.black, 1));
        setLayout(new BorderLayout(0, 0));
        add(pnTitle, BorderLayout.NORTH);
        add(pnContent, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(360, 440);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }

    // Method
    private String[] getMaNhanVienChuaCoTaiKhoan() {
        ArrayList<String> list = new ArrayList<>();

        // Lay ma nv co trang thai 1
        for (NHANVIEN nv : listNhanVien) {
            if (nv.getTrangThai() == 1) {
                boolean exits = false;

                for (TAIKHOAN tk : listTaiKhoan) {
                    // Truong hop nhan vien chua co tai khoan
                    if (nv.getMaNV().equals(tk.getUserName())) {
                        exits = true;
                        break;
                    }
                }
                if (!exits) {
                    list.add(nv.getMaNV());
                }
            }
        }

        String[] listMaNV = new String[list.size()];
        int i = 0;
        for (String s : list) {
            listMaNV[i++] = s;
        }

        return listMaNV;
    }

    private String[] getQuyenTuList() {
        String[] list = new String[listQuyen.size() + 1];

        list[0] = "QN - Chưa có quyền";
        sortListQuyenByMaQuyen(listQuyen);
        for (int i = 0; i < listQuyen.size(); i++) {
            if (listQuyen.get(i).getMoTaQuyen() == null) {
                list[i + 1] = listQuyen.get(i).getMaQuyen() + " - " + listQuyen.get(i).getTenQuyen();
            } else {
                list[i + 1] = listQuyen.get(i).getMaQuyen() + " - " + listQuyen.get(i).getTenQuyen() + " - " + listQuyen.get(i).getMoTaQuyen();
            }
        }

        return list;
    }

    private void sortListQuyenByMaQuyen(ArrayList<PHANQUYEN> list) {
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

    // Getter
    public JLabel getBorderPassword() {
        return borderPassword;
    }

    public JLabel getBorderRetype() {
        return borderRetype;
    }

    public JPanel getPnTitle() {
        return pnTitle;
    }

    public ArrayList<NHANVIEN> getListNhanVien() {
        return listNhanVien;
    }

    public ArrayList<TAIKHOAN> getListTaiKhoan() {
        return listTaiKhoan;
    }

    public ArrayList<PHANQUYEN> getListQuyen() {
        return listQuyen;
    }

    public JLabel getLbTitle() {
        return lbTitle;
    }

    public JLabel getLbExit() {
        return lbExit;
    }

    public JLabel getLbHienThiMatKhau() {
        return lbHienThiMatKhau;
    }

    public JLabel getLbHienThiNhapLai() {
        return lbHienThiNhapLai;
    }

    public JComboBox<String> getCbbMaNhanVien() {
        return cbbMaNhanVien;
    }

    public JComboBox<String> getCbbMaQuyen() {
        return cbbMaQuyen;
    }

    public JPasswordField getPwMatKhau() {
        return pwMatKhau;
    }

    public JPasswordField getPwNhapLai() {
        return pwNhapLai;
    }

    public JTextField getTxtMatKhau() {
        return txtMatKhau;
    }

    public JTextField getTxtNhapLai() {
        return txtNhapLai;
    }

    public JButton getBtnThem() {
        return btnThem;
    }

    public JButton getBtnHuy() {
        return btnHuy;
    }

    public int getpX() {
        return pX;
    }

    public int getpY() {
        return pY;
    }

    // Setter
    public void setpX(int pX) {
        this.pX = pX;
    }

    public void setpY(int pY) {
        this.pY = pY;
    }
}
