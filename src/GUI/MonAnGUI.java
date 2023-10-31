package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import GiaoDienChuan.ExportExcelButton;
import GiaoDienChuan.FileButton;
import GiaoDienChuan.ImportExcelButton;
import GiaoDienChuan.MyTable;
import GiaoDienChuan.RefreshButton;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;

public class MonAnGUI extends JPanel{
	public MyTable pnlMonAnTable;
	public JTextField txtMaMA;
	public JTextField txtTenMA;
	public JTextField txtSL;
	public JTextField txtDonViTinh;
	public JTextField txtDonGia;
	public JComboBox<String> cbLoai;
	public JLabel lblHinhAnh;
	public JButton btnChonAnh;
	public JButton btnThem;
	public JButton btnSua;
	public JButton btnXoa;
	public JButton btnNhap;
	public JButton btnXuat;
	public JButton btnLamMoi;
	public JTextField txtTimKiem;
	
	public MonAnGUI() {
		init();
	}
	
	public void init() {
		//this
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel lblTitle = new JLabel("QUẢN LÝ MÓN ĂN", JLabel.CENTER);
		lblTitle.setBorder(new EmptyBorder(5, 0, 0, 0));
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		//pnlMonAn
		JLabel lblMaMA = new JLabel("     Mã món ăn");
		JLabel lblTenMA = new JLabel("     Tên món ăn");
		JLabel lblSL = new JLabel("     Số lượng");
		JLabel lblDonViTinh = new JLabel("     Đơn vị tính");
		JLabel lblDonGia = new JLabel("     Đơn giá");
		JLabel lblLoai = new JLabel("     Loại");
		
		lblMaMA.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTenMA.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSL.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDonViTinh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDonGia.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLoai.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		JPanel pnlMonAn = new JPanel();
		BoxLayout boxLayout = new BoxLayout(pnlMonAn, BoxLayout.X_AXIS);
		pnlMonAn.setLayout(boxLayout);
		
		//pnlInfo
		JPanel pnlInfo = new JPanel(new GridBagLayout());
		GridBagConstraints gbclbl = new GridBagConstraints();
		GridBagConstraints gbctxt = new GridBagConstraints();
		
		txtMaMA = new JTextField();
		txtMaMA.setEditable(false);
		txtTenMA = new JTextField();
		txtSL = new JTextField();
		txtSL.setEditable(false);
		txtDonViTinh = new JTextField();
		txtDonGia = new JTextField();
		cbLoai = new JComboBox<String>();
		
		txtMaMA.setPreferredSize(new Dimension(300, 25));
		txtTenMA.setPreferredSize(new Dimension(300, 25));
		txtSL.setPreferredSize(new Dimension(300, 25));
		txtDonViTinh.setPreferredSize(new Dimension(300, 25));
		txtDonGia.setPreferredSize(new Dimension(300, 25));
		cbLoai.setPreferredSize(new Dimension(326, 30));
		
		gbclbl.ipadx = 9;
		gbclbl.ipady = 5;
		pnlInfo.add(lblMaMA, gbclbl);
		gbctxt.ipadx = 450;
		gbctxt.ipady = 10;
		gbctxt.gridx = 1;
		pnlInfo.add(txtMaMA, gbctxt);
		gbclbl.ipadx = 5;
		gbclbl.gridx = 0;
		gbclbl.gridy = 1;
		pnlInfo.add(lblTenMA, gbclbl);
		gbctxt.gridx = 1;
		pnlInfo.add(txtTenMA, gbctxt);
		gbclbl.ipadx = 21;
		gbclbl.gridx = 0;
		gbclbl.gridy = 2;
		pnlInfo.add(lblSL, gbclbl);
		gbctxt.gridx = 1;
		pnlInfo.add(txtSL, gbctxt);
		gbclbl.ipadx = 6;
		gbclbl.gridx = 0;
		gbclbl.gridy = 3;
		pnlInfo.add(lblDonViTinh, gbclbl);
		gbctxt.gridx = 1;
		pnlInfo.add(txtDonViTinh, gbctxt);
		gbclbl.ipadx = 29;
		gbclbl.gridx = 0;
		gbclbl.gridy = 4;
		pnlInfo.add(lblDonGia, gbclbl);
		gbctxt.gridx = 1;
		pnlInfo.add(txtDonGia, gbctxt);
		gbclbl.ipadx = 49;
		gbclbl.gridx = 0;
		gbclbl.gridy = 5;
		pnlInfo.add(lblLoai, gbclbl);
		gbctxt.ipadx = 423;
		gbctxt.ipady = 5;
		gbctxt.gridx = 1;
		pnlInfo.add(cbLoai, gbctxt);
		
		//pnlImage
		JPanel pnlImage = new JPanel();
		BoxLayout layout = new BoxLayout(pnlImage, BoxLayout.Y_AXIS);
		pnlImage.setLayout(layout);
		pnlImage.setPreferredSize(new Dimension(450, 250));
		
		lblHinhAnh = new JLabel();
		lblHinhAnh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblHinhAnh.setMaximumSize(new Dimension(200, 190));		
		btnChonAnh = new FileButton();
		btnChonAnh.setText("Chọn ảnh");
		
		lblHinhAnh.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnChonAnh.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pnlImage.add(lblHinhAnh);
		pnlImage.add(Box.createRigidArea(new Dimension(0, 3)));
		pnlImage.add(btnChonAnh);
				
		pnlMonAn.add(pnlInfo);
		pnlMonAn.add(pnlImage);
		
		//pnlTimKiem
		JLabel lblTimKiem = new JLabel("Tìm kiếm");
		lblTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 16));

		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(new Dimension(400, 25));
		
		JPanel pnlTimKiem = new JPanel(new GridBagLayout());
		
		gbclbl.ipadx = 5;
		gbclbl.gridx = 0;
		gbclbl.gridy = 0;
		pnlTimKiem.add(lblTimKiem, gbclbl);
		gbctxt.ipadx = 500;
		gbctxt.ipady = 10;
		gbctxt.gridx = 1;
		pnlTimKiem.add(txtTimKiem, gbctxt);
		
		//pnlbtn
		btnThem = new ThemButton();
		btnSua = new SuaButton();
		btnXoa = new XoaButton();
		btnNhap = new ImportExcelButton();
		btnXuat = new ExportExcelButton();
		btnLamMoi = new RefreshButton();
		
		JPanel pnlbtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
		pnlbtn.add(btnThem);
		pnlbtn.add(btnSua);
		pnlbtn.add(btnXoa);
		pnlbtn.add(btnNhap);
		pnlbtn.add(btnXuat);
		pnlbtn.add(btnLamMoi);
		
		//Table
		pnlMonAnTable = new MyTable();
		String[] headers = {"Mã món ăn", "Tên món ăn", "Số lượng", "Đơn vị tính", "Đơn giá", "Loại"};
		pnlMonAnTable.setHeaders(headers);
		
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//this add
		this.add(lblTitle);
		this.add(Box.createRigidArea(new Dimension(0,3)));
		this.add(pnlMonAn);
		this.add(Box.createRigidArea(new Dimension(0,3)));
		this.add(pnlTimKiem);
		this.add(Box.createRigidArea(new Dimension(0,3)));
		this.add(pnlbtn);
		this.add(Box.createRigidArea(new Dimension(0,3)));
		this.add(pnlMonAnTable);
	}
}
