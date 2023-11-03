package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import GiaoDienChuan.CancelButton;
import GiaoDienChuan.MyTable;

public class FormChonKH extends JDialog{
    private JButton btnChon;
    private JButton btnHuy;
    private JButton jButtonChon;
    private JButton jButtonHuy;
    private JPanel jPanelButton;
    private JLabel lblDiaChi;
    private JLabel lblMaKH;
    private JLabel lblSDT;
    private JLabel lblTenKH;
    private JLabel lblTimKiem;
    private JPanel pnlButton;
    private JPanel pnlDSKH;
    private JPanel pnlKH;
    private JPanel pnlNewKH;
    private MyTable pnlTableKH;
    private JPanel pnlTimKiem;
    private JTabbedPane tpKhachHang;
    private JTextField txtDiaChi;
    private JTextField txtMaKH;
    private JTextField txtSDT;
    private JTextField txtTenKH;
    private JTextField txtTimKiem;

	
	public FormChonKH(JFrame parent, boolean modal) {
		super(parent, modal);
		init();
	}

	private void init() {
        GridBagConstraints gridBagConstraints;

        tpKhachHang = new JTabbedPane();
        pnlDSKH = new JPanel();
        pnlTimKiem = new JPanel();
        lblTimKiem = new JLabel();
        txtTimKiem = new JTextField();
        pnlTableKH = new MyTable();
        pnlButton = new JPanel();
        btnChon = new JButton();
        btnHuy = new CancelButton();
        pnlNewKH = new JPanel();
        pnlKH = new JPanel();
        lblMaKH = new JLabel();
        lblTenKH = new JLabel();
        lblSDT = new JLabel();
        lblDiaChi = new JLabel();
        txtMaKH = new JTextField();
        txtTenKH = new JTextField();
        txtSDT = new JTextField();
        txtDiaChi = new JTextField();
        jPanelButton = new JPanel();
        jButtonChon = new JButton();
        jButtonHuy = new CancelButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(800, 800));
        setUndecorated(true);

        pnlDSKH.setLayout(new BorderLayout());

        lblTimKiem.setFont(new Font("Segoe UI", 1, 18)); // lblTimKiem
        lblTimKiem.setText("Tìm kiếm");
        pnlTimKiem.add(lblTimKiem);

        txtTimKiem.setPreferredSize(new Dimension(700, 30));
        pnlTimKiem.add(txtTimKiem);

        pnlDSKH.add(pnlTimKiem, BorderLayout.NORTH);
        
        String[] headers = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ"};
        pnlTableKH.setHeaders(headers);

        pnlDSKH.add(pnlTableKH, BorderLayout.CENTER);

        pnlButton.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

        btnChon.setText("Chọn");
        btnChon.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8-complete-30.png")));
        pnlButton.add(btnChon);

        btnHuy.setText("Hủy");
        pnlButton.add(btnHuy);
        btnHuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

        pnlDSKH.add(pnlButton, BorderLayout.SOUTH);

        tpKhachHang.addTab("Danh sách khách hàng", pnlDSKH);

        pnlNewKH.setLayout(new BorderLayout());

        pnlKH.setPreferredSize(new Dimension(884, 170));
        pnlKH.setLayout(new GridBagLayout());

        lblMaKH.setFont(new Font("Segoe UI", 1, 18)); // lblMaKH
        lblMaKH.setText("Mã khách hàng");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        pnlKH.add(lblMaKH, gridBagConstraints);

        lblTenKH.setFont(new Font("Segoe UI", 1, 18)); // lblTenKH
        lblTenKH.setText("Tên khách hàng");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        pnlKH.add(lblTenKH, gridBagConstraints);

        lblSDT.setFont(new Font("Segoe UI", 1, 18)); // lblSDT
        lblSDT.setText("Số điện thoại");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        pnlKH.add(lblSDT, gridBagConstraints);

        lblDiaChi.setFont(new Font("Segoe UI", 1, 18)); // lblDiaChi
        lblDiaChi.setText("Địa chỉ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        pnlKH.add(lblDiaChi, gridBagConstraints);

        txtMaKH.setEditable(false);
        txtMaKH.setPreferredSize(new Dimension(500, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        pnlKH.add(txtMaKH, gridBagConstraints);

        txtTenKH.setPreferredSize(new Dimension(500, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        pnlKH.add(txtTenKH, gridBagConstraints);

        txtSDT.setPreferredSize(new Dimension(500, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        pnlKH.add(txtSDT, gridBagConstraints);

        txtDiaChi.setPreferredSize(new Dimension(500, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        pnlKH.add(txtDiaChi, gridBagConstraints);

        pnlNewKH.add(pnlKH, BorderLayout.NORTH);

        jPanelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

        jButtonChon.setText("Chọn");
        jButtonChon.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8-complete-30.png")));
        jPanelButton.add(jButtonChon);

        jButtonHuy.setText("Hủy");
        jPanelButton.add(jButtonHuy);
        jButtonHuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

        pnlNewKH.add(jPanelButton, BorderLayout.SOUTH);

        tpKhachHang.addTab("Khách hàng mới", pnlNewKH);

        getContentPane().add(tpKhachHang, BorderLayout.CENTER);

        pack();
	}
}
