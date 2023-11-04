package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import BUS.ChiTietHoaDonBUS;
import GiaoDienChuan.CancelButton;
import GiaoDienChuan.ExportExcelButton;
import GiaoDienChuan.MyTable;

public class ChiTietHoaDonGUI extends JDialog{
    public JButton btnDong;
    public JButton btnXuat;
    public JLabel lbl1;
    public JLabel lbl2;
    public JLabel lbl3;
    public JLabel lbl4;
    public JLabel lbl5;
    public JLabel lbl6;
    public JLabel lblKhuyenMai;
    public JLabel lblLeftIcon;
    public JLabel lblMaHD;
    public JLabel lblNgayLap;
    public JLabel lblRightIcon;
    public JLabel lblTenKH;
    public JLabel lblTenNV;
    public JLabel lblTitle;
    public JLabel lblTitleCTHD;
    public JLabel lblTongTien;
    public JPanel pnlButton;
    public JPanel pnlCTHD;
    public JPanel pnlCenter;
    public JPanel pnlHoaDon;
    public MyTable pnlTable;
    public JPanel pnlTitle;

	
	public ChiTietHoaDonGUI(JFrame parent, boolean modal, String MaHD) {
		super(parent, modal);
		init();
		new ChiTietHoaDonBUS(pnlTable, lblMaHD, lblTenKH, lblTenNV, lblNgayLap, lblKhuyenMai, lblTongTien, MaHD);
	}

	private void init() {
        pnlCenter = new JPanel();
        pnlCTHD = new JPanel();
        pnlTable = new MyTable();
        lblTitleCTHD = new JLabel();
        pnlHoaDon = new JPanel();
        lbl5 = new JLabel();
        lbl1 = new JLabel();
        lbl2 = new JLabel();
        lblKhuyenMai = new JLabel();
        lbl4 = new JLabel();
        lbl3 = new JLabel();
        lblMaHD = new JLabel();
        lblTenKH = new JLabel();
        lblTenNV = new JLabel();
        lblNgayLap = new JLabel();
        lbl6 = new JLabel();
        lblTongTien = new JLabel();
        pnlTitle = new JPanel();
        lblLeftIcon = new JLabel();
        lblTitle = new JLabel();
        lblRightIcon = new JLabel();
        pnlButton = new JPanel();
        btnXuat = new ExportExcelButton();
        btnDong = new CancelButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(720, 900));
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 2));
        
        String[] headers = {"Mã món ăn", "Tên món ăn", "Số lượng", "Đơn vị tính", "Đơn giá", "Loại"};
        pnlTable.setHeaders(headers);

        pnlCenter.setLayout(new BorderLayout());

        pnlCTHD.setLayout(new BorderLayout());

        pnlCTHD.add(pnlTable, BorderLayout.CENTER);

        lblTitleCTHD.setFont(new Font("Segoe UI", 1, 20)); // lblTitleCTHD
        lblTitleCTHD.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitleCTHD.setText("CHI TIẾT HÓA ĐƠN");
        pnlCTHD.add(lblTitleCTHD, BorderLayout.NORTH);

        pnlCenter.add(pnlCTHD, BorderLayout.CENTER);

        pnlHoaDon.setPreferredSize(new Dimension(720, 155));
        pnlHoaDon.setLayout(null);

        lbl1.setFont(new Font("Segoe UI", 0, 18)); // lbl1
        lbl1.setText("Mã hóa đơn:");
        pnlHoaDon.add(lbl1);
        lbl1.setBounds(20, 10, 150, 40);
        
        lbl2.setFont(new Font("Segoe UI", 0, 18)); // lbl2
        lbl2.setText("Tên khách hàng:");
        pnlHoaDon.add(lbl2);
        lbl2.setBounds(20, 60, 150, 40);
        
        lbl3.setFont(new Font("Segoe UI", 0, 18)); // lbl3
        lbl3.setText("Nhân viên lập HĐ:");
        pnlHoaDon.add(lbl3);
        lbl3.setBounds(20, 110, 150, 40);
        
        lbl4.setFont(new Font("Segoe UI", 0, 18)); // lbl4
        lbl4.setText("Ngày lập HĐ:");
        pnlHoaDon.add(lbl4);
        lbl4.setBounds(400, 10, 110, 40);
        
        lbl5.setFont(new Font("Segoe UI", 0, 18)); // lbl5
        lbl5.setText("Khuyến mãi:");
        pnlHoaDon.add(lbl5);
        lbl5.setBounds(400, 60, 110, 40);
        
        lbl6.setFont(new Font("Segoe UI", 0, 18)); // lbl6
        lbl6.setText("Tổng tiền:");
        pnlHoaDon.add(lbl6);
        lbl6.setBounds(400, 110, 110, 40);
        
        lblMaHD.setFont(new Font("Segoe UI", 1, 18)); // lblMaHD
        pnlHoaDon.add(lblMaHD);
        lblMaHD.setBounds(170, 10, 225, 40);
        
        lblTenKH.setFont(new Font("Segoe UI", 0, 18)); // lblTenKH
        pnlHoaDon.add(lblTenKH);
        lblTenKH.setBounds(170, 60, 225, 40);
        
        lblTenNV.setFont(new Font("Segoe UI", 0, 18)); // lblTenNV
        pnlHoaDon.add(lblTenNV);
        lblTenNV.setBounds(170, 110, 225, 40);
        
        lblNgayLap.setFont(new Font("Segoe UI", 0, 18)); // lblNgayLap
        pnlHoaDon.add(lblNgayLap);
        lblNgayLap.setBounds(515, 10, 200, 40);

        lblKhuyenMai.setFont(new Font("Segoe UI", 0, 18)); // lblKhuyenMai
        lblKhuyenMai.setText("Không có");
        pnlHoaDon.add(lblKhuyenMai);
        lblKhuyenMai.setBounds(515, 60, 200, 40);

        lblTongTien.setFont(new Font("Segoe UI", 0, 18)); // lblTongTien
        pnlHoaDon.add(lblTongTien);
        lblTongTien.setBounds(515, 110, 200, 40);

        pnlCenter.add(pnlHoaDon, BorderLayout.NORTH);

        getContentPane().add(pnlCenter, BorderLayout.CENTER);

        pnlTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

        lblLeftIcon.setIcon(new ImageIcon(getClass().getResource("/icon_img/logo-fast-food-40.png"))); // lblLeftIcon
        pnlTitle.add(lblLeftIcon);

        lblTitle.setFont(new Font("Times New Roman", 1, 32)); // NOI18N
        lblTitle.setText("HÓA ĐƠN BÁN HÀNG");
        pnlTitle.add(lblTitle);

        lblRightIcon.setIcon(new ImageIcon(getClass().getResource("/icon_img/logo-fast-food-40.png"))); // lblRightIcon
        pnlTitle.add(lblRightIcon);

        getContentPane().add(pnlTitle, BorderLayout.NORTH);

        pnlButton.add(btnXuat);

        btnDong.setText("Đóng");
        btnDong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        pnlButton.add(btnDong);

        getContentPane().add(pnlButton, BorderLayout.SOUTH);

        pack();
	}
}
