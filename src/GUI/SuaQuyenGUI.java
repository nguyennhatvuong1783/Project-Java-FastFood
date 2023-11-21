package GUI;

import BUS.SuaQuyenBUS;
import DAO.DaoChiTietPhanQuyen;
import DTO.CHITIETPHANQUYEN;
import DTO.CHUCNANG;
import DTO.PHANQUYEN;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Jhin
 */
public class SuaQuyenGUI extends JFrame {

    private final Font lbFont = new Font("Times New Roman", Font.BOLD, 16);
    private final Font txtFont = new Font("Times New Roman", Font.PLAIN, 16);

    private JPanel pnTitle;
    private JLabel lbTitle, lbExit;

    private PHANQUYEN quyen;

    private JLabel lbMaQuyen, maQuyen, lbTenQuyen, lbMoTa;
    private JTextField txtTenQuyen;
    private JTextArea txtMoTa;

    private JButton btnLuu, btnHuy;

    private ArrayList<PHANQUYEN> listQuyen;
    private ArrayList<CHITIETPHANQUYEN> listPhanQuyen;
    private ArrayList<CHUCNANG> listChucNang;    
    
    // Quyen dang duoc chinh sua
    private ArrayList<CHITIETPHANQUYEN> quyenDuocSua;
    private JCheckBox[] chkChucNang;

    // Location
    private int pX, pY;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public SuaQuyenGUI(PHANQUYEN quyen, ArrayList<PHANQUYEN> listQuyen, ArrayList<CHITIETPHANQUYEN> listPhanQuyen, ArrayList<CHUCNANG> listChucNang, ArrayList<CHITIETPHANQUYEN> quyenDuocSua) {
        this.quyen = quyen;
        this.listQuyen = listQuyen;
        this.listPhanQuyen = listPhanQuyen;
        this.listChucNang = listChucNang;
        this.quyenDuocSua = quyenDuocSua;
        init();
        new SuaQuyenBUS(this);
    }

    private void init() {
        /**
         * Title Panel
         */
        pnTitle = new JPanel(null);
        pnTitle.setBounds(0, 0, 600, 32);
        pnTitle.setBackground(new Color(255, 165, 0));

        lbTitle = new JLabel("Sửa quyền", JLabel.CENTER);
        lbTitle.setBounds(0, 0, 558, 32);
        lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lbTitle.setForeground(Color.white);

        lbExit = new JLabel(new ImageIcon(getClass().getResource("../icon_img/close-lblexit-20.png")));
        lbExit.setBounds(558, 0, 42, 32);
        lbExit.setBackground(new Color(255, 165, 0));
        lbExit.setOpaque(true);

        pnTitle.add(lbTitle);
        pnTitle.add(lbExit);

        /**
         * Content Panel
         */
        JPanel pnContent = new JPanel(null);
        pnContent.setBounds(0, 32, 295, 368);
        pnContent.setBackground(Color.white);

        lbMaQuyen = new JLabel("Mã quyền");
        lbMaQuyen.setBounds(15, 10, 80, 20);
        lbMaQuyen.setFont(lbFont);

        maQuyen = new JLabel(quyen.getMaQuyen());
        maQuyen.setBounds(10, 35, 275, 30);
        maQuyen.setBorder(new LineBorder(new Color(255, 165, 0), 2));
        maQuyen.setFont(txtFont);

        lbTenQuyen = new JLabel("Tên quyền");
        lbTenQuyen.setBounds(15, 75, 80, 20);
        lbTenQuyen.setFont(lbFont);

        txtTenQuyen = new JTextField(quyen.getTenQuyen());
        txtTenQuyen.setBounds(10, 100, 275, 30);
        txtTenQuyen.setBorder(new LineBorder(new Color(255, 165, 0), 2));
        txtTenQuyen.setFont(txtFont);

        lbMoTa = new JLabel("Mô tả");
        lbMoTa.setBounds(15, 140, 80, 20);
        lbMoTa.setFont(lbFont);

        txtMoTa = new JTextArea(quyen.getMoTaQuyen());
        txtMoTa.setBounds(10, 165, 275, 150);
        txtMoTa.setBorder(new LineBorder(new Color(255, 165, 0), 2));
        txtMoTa.setFont(txtFont);

        btnLuu = new JButton("Lưu", new ImageIcon(getClass().getResource("../icon_img/icons8-tick-32.png")));
        btnLuu.setBounds(10, 325, 125, 36);

        btnHuy = new JButton("Hủy", new ImageIcon(getClass().getResource("../icon_img/icons8-cancel-32.png")));
        btnHuy.setBounds(160, 325, 125, 36);

        pnContent.add(lbMaQuyen);
        pnContent.add(maQuyen);
        pnContent.add(lbTenQuyen);
        pnContent.add(txtTenQuyen);
        pnContent.add(lbMoTa);
        pnContent.add(txtMoTa);
        pnContent.add(btnLuu);
        pnContent.add(btnHuy);

        /**
         * Panel ChucNang
         */
        // Chuc nang label
        JLabel lbChucNang = new JLabel("Chức năng", JLabel.CENTER);
        lbChucNang.setFont(new Font("Times New Roman", Font.BOLD, 28));
        int length = listChucNang.size(), row;

        if (length % 2 != 0) {
            row = length / 2 + 1;
        } else {
            row = length / 2;
        }

        // Add check box
        JPanel pnCheckBox = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        pnCheckBox.setPreferredSize(new Dimension(300, row * 50));
        chkChucNang = new JCheckBox[length];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < 2; j++) {
                if (i * 2 + j == length) {
                    break;
                }
                chkChucNang[i * 2 + j] = new JCheckBox(listChucNang.get(i * 2 + j).getTenChucNang());
                chkChucNang[i * 2 + j].setPreferredSize(new Dimension(150, 50));
                chkChucNang[i * 2 + j].setFont(new Font("Times New Roman", Font.PLAIN, 16));
                pnCheckBox.add(chkChucNang[i * 2 + j]);
            }
        }

        // Lay ten chuc nang theo maQuyen
        ArrayList<String> tenChucNang = new ArrayList<>();
        for(CHITIETPHANQUYEN ctpq : quyenDuocSua) {
            for (CHUCNANG c : listChucNang) {
                if (c.getMaChucNang().equals(ctpq.getMaChucNang())) {
                    tenChucNang.add(c.getTenChucNang());
                }
            }
        }
        
        // Hien thi chuc nang check box
        for (JCheckBox jcb : chkChucNang){
            for (String s : tenChucNang) {
                if (jcb.getText().equals(s)) {
                    jcb.setSelected(true);
                }
            }
        }

        // ScrollPane Chuc nang
        JScrollPane paneChucNang = new JScrollPane(pnCheckBox);
        paneChucNang.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel pnChucNang = new JPanel(new BorderLayout(0, 0));
        pnChucNang.setBounds(300, 32, 300, 368);
        pnChucNang.setBackground(Color.white);
        pnChucNang.add(lbChucNang, BorderLayout.NORTH);
        pnChucNang.add(paneChucNang, BorderLayout.CENTER);

        // JFrame
        getRootPane().setBorder(new LineBorder(Color.black, 1));
        setLayout(null);
        add(pnTitle);
        add(pnContent);
        add(pnChucNang);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }

    // Getter
    public JPanel getPnTitle() {
        return pnTitle;
    }

    public JLabel getLbExit() {
        return lbExit;
    }

    public JTextField getTxtTenQuyen() {
        return txtTenQuyen;
    }

    public JTextArea getTxtMoTa() {
        return txtMoTa;
    }

    public JButton getBtnLuu() {
        return btnLuu;
    }

    public JButton getBtnHuy() {
        return btnHuy;
    }

    public ArrayList<PHANQUYEN> getListQuyen() {
        return listQuyen;
    }

    public ArrayList<CHITIETPHANQUYEN> getListPhanQuyen() {
        return listPhanQuyen;
    }

    public ArrayList<CHUCNANG> getListChucNang() {
        return listChucNang;
    }

    public ArrayList<CHITIETPHANQUYEN> getQuyenDuocSua() {
        return quyenDuocSua;
    }

    public JCheckBox[] getChkChucNang() {
        return chkChucNang;
    }

    public int getpX() {
        return pX;
    }

    public PHANQUYEN getQuyen() {
        return quyen;
    }

    public int getpY() {
        return pY;
    }

    // Setter
    public void setListChucNang(ArrayList<CHUCNANG> listChucNang) {
        this.listChucNang = listChucNang;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public void setpY(int pY) {
        this.pY = pY;
    }
}
