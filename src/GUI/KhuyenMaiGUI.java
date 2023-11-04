package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.XoaButton;
import GiaoDienChuan.MyTable;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import BUS.KhuyenMaiBus;
import DAO.DaoKhuyenMai;
import DTO.KHUYENMAI;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class KhuyenMaiGUI extends JPanel implements ActionListener {

    private KhuyenMaiBus khuyenMaiBus;
    private DaoKhuyenMai daoKhuyenMai;
    private JTextField txt_MaKM;
    private JTextField txt_TenKM;
    private JTable tbl_KM;
    private JTextField txt_MaKMCT;
    private JTextField txt_phanTram;
    private JTextField txt_dieuKien;
    private JTable tbl_CTKM;
    JDateChooser date_BĐ;
    JDateChooser date_KT;
    private JPanel jp;
    private JPanel jp1;
    private JPanel jp2;
    private JPanel panel_2_1;
    private JPanel panel_2;
    private JPanel panel_2_Center;
    private JPanel panel_2_2;
    private ThemButton buttonThem;
    private XoaButton buttonXoa;
    private SuaButton buttonSua;
    private Border lineBorder = BorderFactory.createLineBorder(Color.black);
    private Border empty = BorderFactory.createEmptyBorder();
    private Border raisedBevel = BorderFactory.createRaisedBevelBorder();
    private Border loweredBevel = BorderFactory.createLoweredBevelBorder();
    private Border compoundBorder = new CompoundBorder(raisedBevel, new CompoundBorder(raisedBevel, new CompoundBorder(empty, raisedBevel)));
    private Border compoundBorder1 = new CompoundBorder(empty, new CompoundBorder(raisedBevel, new CompoundBorder(raisedBevel, raisedBevel)));
    private Border compoundBorder2 = new CompoundBorder(raisedBevel, new CompoundBorder(raisedBevel, new CompoundBorder(raisedBevel, raisedBevel)));
    private JPanel jp_Center;
    private KhuyenMaiGUI khuyenMaiGui;
    private JPanel panel_4;
    private MyTable tableKM;
    private String id;

    public KhuyenMaiGUI() {
        init();
    }

    public void init() {
        this.setLayout(new BorderLayout());
        jp_Center = new JPanel();
        jp_Center.setLayout(new GridLayout(2, 1));
        jp = new JPanel();
        jp.setLayout(null);
        jp.setBorder(loweredBevel);
        jp1 = new JPanel();
        jp1.setBounds(125, 50, 750, 325);
        jp1.setLayout(new BorderLayout());
        jp2 = new JPanel();
        jp2.setLayout(new BorderLayout());
        panel_2 = new JPanel();
        panel_2.setLayout(new BorderLayout());
        panel_2_1 = new JPanel();
        panel_2_1.setLayout(new GridLayout(6, 2));
        panel_2_2 = new JPanel();
        panel_2_2.setLayout(new GridLayout(1, 3));
        panel_2.setBorder(new TitledBorder(raisedBevel, "Thông tin khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2_2.setBorder(loweredBevel);
        JLabel lblNewLabel_2_1 = new JLabel("Mã khuyến mãi :");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2_1.setBorder(loweredBevel);
        panel_2_1.add(lblNewLabel_2_1);
        txt_MaKM = new JTextField();
        txt_MaKM.setBorder(loweredBevel);
        panel_2_1.add(txt_MaKM);
        txt_MaKM.setColumns(10);
        JLabel lblNewLabel_2_2 = new JLabel("Tên khuyến mãi :");
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2_2.setBorder(loweredBevel);
        panel_2_1.add(lblNewLabel_2_2);
        txt_TenKM = new JTextField();
        txt_TenKM.setBorder(loweredBevel);
        txt_TenKM.setColumns(10);
        panel_2_1.add(txt_TenKM);
        JLabel lblNewLabel_2_3 = new JLabel("Phần trăm giảm:");
        lblNewLabel_2_3.setBorder(loweredBevel);
        lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_2_1.add(lblNewLabel_2_3);
        txt_phanTram = new JTextField();
        txt_phanTram.setBorder(loweredBevel);
        txt_phanTram.setColumns(10);
        panel_2_1.add(txt_phanTram);

        JLabel lblNewLabel_2_4 = new JLabel("Điều kiện :");
        lblNewLabel_2_4.setBorder(loweredBevel);
        lblNewLabel_2_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_2_1.add(lblNewLabel_2_4);
        txt_dieuKien = new JTextField();
        txt_dieuKien.setBorder(loweredBevel);
        txt_dieuKien.setColumns(10);
        panel_2_1.add(txt_dieuKien);

        JLabel lblNewLabel_2_5 = new JLabel("Ngày bắt đầu:");
        lblNewLabel_2_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2_5.setBorder(loweredBevel);
        panel_2_1.add(lblNewLabel_2_5);
        date_BĐ = new JDateChooser();

        date_BĐ.setBorder(loweredBevel);
        date_BĐ.setDateFormatString("dd/MM/yyyy");
        //date_BĐ.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 15));
        date_BĐ.getDateEditor().getUiComponent().setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_2_1.add(date_BĐ);
        JLabel lblNewLabel_2_6 = new JLabel("Ngày kết thúc:");
        lblNewLabel_2_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2_6.setBorder(loweredBevel);
        panel_2_1.add(lblNewLabel_2_6);
        date_KT = new JDateChooser();

        date_KT.setBorder(loweredBevel);
        date_KT.setDateFormatString("dd/MM/yyyy");
        //date_KT.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 15));
        date_KT.getDateEditor().getUiComponent().setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_2_1.add(date_KT);
        buttonThem = new ThemButton();
        buttonThem.addActionListener((ActionListener) this);
        panel_2_2.add(buttonThem);
        buttonSua = new SuaButton();
        buttonSua.addActionListener((ActionListener) this);
        panel_2_2.add(buttonSua);
        buttonXoa = new XoaButton();
        buttonXoa.addActionListener((ActionListener) this);
        panel_2_2.add(buttonXoa);
        panel_2.add(panel_2_1, BorderLayout.CENTER);
        panel_2.add(panel_2_2, BorderLayout.SOUTH);

        //-----------------------------------------------
        //-----------------------------------------------------
        panel_4 = new JPanel();
        panel_4.setBorder(raisedBevel);
        panel_4.setBorder(new TitledBorder(null, "Danh sách khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_4.setLayout(new BorderLayout());
        tableKM = new MyTable();
        tableKM.setHeaders(new String[]{
            "Mã khuyến mãi", "Tên khuyến mãi", "Điều kiện", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc"
        });
        panel_4.add(tableKM, BorderLayout.CENTER);
        khuyenMaiBus = new KhuyenMaiBus(this);
        khuyenMaiBus.LoadDataToTable();
        this.getTableKM().getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    khuyenMaiBus.SuaKm();
                }
            }
        });

        //---------------------------------------------------------------	
        JLabel lblNewLabel = new JLabel("Quản lý khuyến mãi", JLabel.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
        this.add(lblNewLabel, BorderLayout.NORTH);

        jp1.add(panel_2, BorderLayout.CENTER);
        jp2.add(panel_4, BorderLayout.CENTER);
        jp_Center.add(jp);
        jp_Center.add(jp2);
        jp.add(jp1);
        this.add(jp_Center, BorderLayout.CENTER);

    }

    public void addRow(KHUYENMAI km) {
        if (km.getTrangThai() != 0) {
            String[] a = {km.getMaKM(), km.getTenKM(), km.getDieuKienKM(), Float.toString(km.getGiamGia()), km.dateBĐToString(), km.dateKTToString()};
            this.tableKM.addRow(a);
        }
    }

    public MyTable getTableKM() {
        return this.tableKM;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        khuyenMaiBus = new KhuyenMaiBus(this);
        if (e.getSource() == buttonThem) {

            if (khuyenMaiBus.themKM() == 1) {
                JOptionPane.showMessageDialog(null, "Thêm thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại!");
            }
        }
    }


    public JDateChooser getDate_BĐ() {
        return date_BĐ;
    }

    public int getDate_BĐDay() {
        int date = date_BĐ.getDate().getDay();
        return date;
    }

    public int getDate_BĐMonth() {
        int date = date_BĐ.getDate().getMonth();
        return date;
    }

    public int getDate_BĐYear() {
        int date = date_BĐ.getDate().getYear();
        return date + 1900;
    }

    public void setDate_BĐ(JDateChooser date_BĐ) {
        this.date_BĐ = date_BĐ;
    }

    public JDateChooser getDate_KT() {
        return date_KT;
    }

    public int getDate_KTDay() {
        int date = date_KT.getDate().getDay();
        return date;
    }

    public int getDate_KTMonth() {
        int date = date_KT.getDate().getMonth();
        return date;
    }

    public int getDate_KTYear() {
        int date = date_KT.getDate().getYear();
        return date + 1900;
    }

    public String dateBĐToString() {
        String date = Integer.toString(getDate_BĐDay()) + "/" + Integer.toString(getDate_BĐMonth()) + "/" + Integer.toString(getDate_BĐYear());
        return date;
    }

    public String dateKTToString() {
        String date = Integer.toString(getDate_KTDay()) + "/" + Integer.toString(getDate_KTMonth()) + "/" + Integer.toString(getDate_KTYear());
        return date;
    }

    public void setDate_KT(JDateChooser date_KT) {
        this.date_KT = date_KT;
    }

    public JTextField getTxt_MaKM() {
        return txt_MaKM;
    }

    public void setTxt_MaKM(JTextField txt_MaKM) {
        this.txt_MaKM = txt_MaKM;
    }

    public JTextField getTxt_TenKM() {
        return txt_TenKM;
    }

    public void setTxt_TenKM(JTextField txt_TenKM) {
        this.txt_TenKM = txt_TenKM;
    }

    public JTextField getTxt_MaKMCT() {
        return txt_MaKMCT;
    }

    public void setTxt_MaKMCT(JTextField txt_MaKMCT) {
        this.txt_MaKMCT = txt_MaKMCT;
    }

    public JTextField getTxt_phanTram() {
        return txt_phanTram;
    }

    public void setTxt_phanTram(JTextField txt_phanTram) {
        this.txt_phanTram = txt_phanTram;
    }

    public JTextField getTxt_dieuKien() {
        return txt_dieuKien;
    }

    public void setTxt_dieuKien(JTextField txt_dieuKien) {
        this.txt_dieuKien = txt_dieuKien;
    }

    public JPanel getPanel_4() {
        return panel_4;
    }

    public void setPanel_4(JPanel panel_4) {
        this.panel_4 = panel_4;
    }

    public static void main(String args[]) {
        JFrame test = new JFrame();
        KhuyenMaiGUI a = new KhuyenMaiGUI();
        test.add(a);
        test.setLocationRelativeTo(null);
        test.setSize(1000, 1000);
        test.setVisible(true);
    }

}
