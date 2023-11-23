package GUI;

import BUS.SuaTaiKhoanBUS;
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
import javax.swing.JCheckBox;
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
public class SuaTaiKhoanGUI extends JDialog {

    private final Font titleFont = new Font("Times New Roman", Font.BOLD, 20);
    private final Font lbFont = new Font("Times New Roman", Font.BOLD, 16);
    private final Font txtFont = new Font("Times New Roman", Font.PLAIN, 16);

    private ArrayList<TAIKHOAN> listTaiKhoan;
    private ArrayList<PHANQUYEN> listQuyen;
    private TAIKHOAN taiKhoan;

    private JPanel pnTitle, pnContent;
    private JLabel lbTitle, lbExit, lbHienThiMatKhau, lbHienThiNhapLai;

    private JCheckBox chkDoiMaQuyen, chkDoiMatKhau;
    private JTextField txtMaNV, txtMatKhau, txtNhapLai;
    private JComboBox<String> cbbMaQuyen;
    private JPasswordField pwMatKhauCu, pwMatKhau, pwNhapLai;

    private JButton btnLuu, btnHuy;

    // Location
    private int pX, pY;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public SuaTaiKhoanGUI(ArrayList<TAIKHOAN> listTaiKhoan, ArrayList<PHANQUYEN> listQuyen, TAIKHOAN taiKhoan) {
        this.listTaiKhoan = listTaiKhoan;
        this.listQuyen = listQuyen;
        this.taiKhoan = taiKhoan;
        init();
        new SuaTaiKhoanBUS(this);
    }

    private void init() {
        /**
         * Title Panel
         */
        pnTitle = new JPanel(new BorderLayout(0, 0));
        pnTitle.setBackground(new Color(255, 165, 0));

        lbTitle = new JLabel("Sửa tài khoản", JLabel.CENTER);
        lbTitle.setPreferredSize(new Dimension(0, 32));
        lbTitle.setFont(titleFont);
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

        chkDoiMaQuyen = new JCheckBox();
        chkDoiMaQuyen.setBounds(10, 10, 20, 30);
        chkDoiMaQuyen.setBackground(Color.white);
        chkDoiMaQuyen.setSelected(true);

        JLabel lbDoiMaQuyen = new JLabel(" Đổi mã quyền");
        lbDoiMaQuyen.setBounds(30, 10, 160, 30);
        lbDoiMaQuyen.setFont(titleFont);
        lbDoiMaQuyen.setForeground(new Color(255, 165, 0));

        JLabel lbMaNhanVien = new JLabel(" Mã nhân viên");
        lbMaNhanVien.setBounds(20, 40, 100, 30);
        lbMaNhanVien.setFont(lbFont);

        txtMaNV = new JTextField(taiKhoan.getUserName());
        txtMaNV.setBounds(20, 70, 320, 30);
        txtMaNV.setFont(txtFont);
        txtMaNV.setBorder(new LineBorder(new Color(255, 165, 0), 2));
        txtMaNV.setEnabled(false);

        JLabel lbMaQuyen = new JLabel(" Mã quyền");
        lbMaQuyen.setBounds(20, 120, 100, 30);
        lbMaQuyen.setFont(lbFont);

        cbbMaQuyen = new JComboBox<>(getQuyenTuList());
        cbbMaQuyen.setBounds(20, 150, 320, 30);
        cbbMaQuyen.setFont(txtFont);
        cbbMaQuyen.setBorder(new LineBorder(new Color(255, 165, 0), 2));
        cbbMaQuyen.setSelectedItem(getQuyenTaiKhoan(taiKhoan));

        JLabel separate = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(0, 0, 340, 0);
            }
        };
        separate.setBounds(10, 200, 340, 1);

        chkDoiMatKhau = new JCheckBox();
        chkDoiMatKhau.setBounds(10, 210, 20, 30);
        chkDoiMatKhau.setBackground(Color.white);
        chkDoiMatKhau.setSelected(true);

        JLabel lbDoiMatKhau = new JLabel(" Đổi mật khẩu");
        lbDoiMatKhau.setBounds(30, 210, 160, 30);
        lbDoiMatKhau.setFont(titleFont);
        lbDoiMatKhau.setForeground(new Color(255, 165, 0));

        JLabel lbMatKhauCu = new JLabel(" Mật khẩu cũ");
        lbMatKhauCu.setBounds(20, 250, 100, 30);
        lbMatKhauCu.setFont(lbFont);

        pwMatKhauCu = new JPasswordField();
        pwMatKhauCu.setBounds(20, 280, 320, 30);
        pwMatKhauCu.setBorder(new LineBorder(new Color(255, 165, 0), 2));
        pwMatKhauCu.setFont(txtFont);

        JLabel lbMatKhau = new JLabel(" Mật khẩu");
        lbMatKhau.setBounds(20, 330, 100, 30);
        lbMatKhau.setFont(lbFont);

        JLabel borderPassword = new JLabel();
        borderPassword.setBounds(20, 360, 320, 30);
        borderPassword.setBorder(new LineBorder(new Color(255, 165, 0), 2));

        pwMatKhau = new JPasswordField(20);
        pwMatKhau.setBounds(22, 362, 284, 26);
        pwMatKhau.setBorder(null);
        pwMatKhau.setFont(txtFont);

        txtMatKhau = new JTextField("");
        txtMatKhau.setBounds(22, 362, 284, 26);
        txtMatKhau.setBorder(null);
        txtMatKhau.setFont(txtFont);
        txtMatKhau.setVisible(false);

        JLabel separatePass = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(0, 0, 0, 24);
            }
        };
        separatePass.setBounds(310, 363, 1, 24);

        lbHienThiMatKhau = new JLabel(new ImageIcon(getClass().getResource("../icon_img/hidePassword.png")));
        lbHienThiMatKhau.setBounds(313, 363, 24, 24);

        JLabel lbNhapLai = new JLabel(" Nhập lại mật khẩu");
        lbNhapLai.setBounds(20, 410, 150, 30);
        lbNhapLai.setFont(lbFont);

        JLabel borderRetype = new JLabel();
        borderRetype.setBounds(20, 440, 320, 30);
        borderRetype.setBorder(new LineBorder(new Color(255, 165, 0), 2));

        pwNhapLai = new JPasswordField(20);
        pwNhapLai.setBounds(22, 442, 284, 26);
        pwNhapLai.setBorder(null);
        pwNhapLai.setFont(txtFont);

        txtNhapLai = new JTextField("");
        txtNhapLai.setBounds(22, 442, 284, 26);
        txtNhapLai.setBorder(null);
        txtNhapLai.setFont(txtFont);
        txtNhapLai.setVisible(false);

        JLabel separateRetype = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(0, 0, 0, 24);
            }
        };
        separateRetype.setBounds(310, 443, 1, 24);

        lbHienThiNhapLai = new JLabel(new ImageIcon(getClass().getResource("../icon_img/hidePassword.png")));
        lbHienThiNhapLai.setBounds(313, 443, 24, 24);

        btnLuu = new JButton("Lưu", new ImageIcon(getClass().getResource("../icon_img/icons8-tick-32.png")));
        btnLuu.setBounds(40, 490, 120, 40);

        btnHuy = new JButton("Hủy", new ImageIcon(getClass().getResource("../icon_img/icons8-cancel-32.png")));
        btnHuy.setBounds(200, 490, 120, 40);

        // Add component
        pnContent.add(chkDoiMaQuyen);
        pnContent.add(lbDoiMaQuyen);

        pnContent.add(lbMaNhanVien);
        pnContent.add(txtMaNV);

        pnContent.add(lbMaQuyen);
        pnContent.add(cbbMaQuyen);
        pnContent.add(separate);

        pnContent.add(chkDoiMatKhau);
        pnContent.add(lbDoiMatKhau);
        pnContent.add(lbMatKhauCu);
        pnContent.add(pwMatKhauCu);

        pnContent.add(lbMatKhau);
        pnContent.add(pwMatKhau);
        pnContent.add(txtMatKhau);
        pnContent.add(separatePass);
        pnContent.add(lbHienThiMatKhau);
        pnContent.add(borderPassword);

        pnContent.add(lbNhapLai);
        pnContent.add(pwNhapLai);
        pnContent.add(txtNhapLai);
        pnContent.add(separateRetype);
        pnContent.add(lbHienThiNhapLai);
        pnContent.add(borderRetype);

        pnContent.add(btnLuu);
        pnContent.add(btnHuy);

        /**
         * JFrame
         */
        getRootPane().setBorder(new LineBorder(Color.black, 1));
        setLayout(new BorderLayout(0, 0));
        add(pnTitle, BorderLayout.NORTH);
        add(pnContent, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(360, 580);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }

    // Method
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

    public String getQuyenTaiKhoan(TAIKHOAN tk) {
        String cbbStr = "";
        if (tk.getMaQuyen() == null) {
            cbbStr = "QN - Chưa có quyền";
        } else {
            for (PHANQUYEN pq : listQuyen) {
                if (pq.getMaQuyen().equals(tk.getMaQuyen())) {
                    cbbStr = pq.getMaQuyen() + " - " + pq.getTenQuyen();
                }
            }
        }
        return cbbStr;
    }

    // Getter
    public ArrayList<TAIKHOAN> getListTaiKhoan() {
        return listTaiKhoan;
    }

    public JCheckBox getChkDoiMaQuyen() {
        return chkDoiMaQuyen;
    }

    public JCheckBox getChkDoiMatKhau() {
        return chkDoiMatKhau;
    }

    public JLabel getLbHienThiMatKhau() {
        return lbHienThiMatKhau;
    }

    public JLabel getLbHienThiNhapLai() {
        return lbHienThiNhapLai;
    }

    public JTextField getTxtMatKhau() {
        return txtMatKhau;
    }

    public JTextField getTxtNhapLai() {
        return txtNhapLai;
    }

    public JComboBox<String> getCbbMaQuyen() {
        return cbbMaQuyen;
    }

    public JPasswordField getPwMatKhauCu() {
        return pwMatKhauCu;
    }

    public JPasswordField getPwMatKhau() {
        return pwMatKhau;
    }

    public JPasswordField getPwNhapLai() {
        return pwNhapLai;
    }

    public JButton getBtnLuu() {
        return btnLuu;
    }

    public JButton getBtnHuy() {
        return btnHuy;
    }

    public TAIKHOAN getTaiKhoan() {
        return taiKhoan;
    }

    public JPanel getPnTitle() {
        return pnTitle;
    }

    public JLabel getLbExit() {
        return lbExit;
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

//    @SuppressWarnings("ResultOfObjectAllocationIgnored")
//    public static void main(String[] args) {
//        try {
//            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
//            java.awt.EventQueue.invokeLater(() -> {
//                new SuaTaiKhoanGUI(DAO.DaoTaiKhoan.getInstance().selectAll(), DAO.DaoQuyen.getInstance().selectAll(), new TAIKHOAN("NV2", "haha2", 1, "Q4"));
//            });
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(SuaTaiKhoanGUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
