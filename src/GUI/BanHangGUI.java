package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import GiaoDienChuan.FormatMoney;
import GiaoDienChuan.MoreButton;
import GiaoDienChuan.MyTable;
import GiaoDienChuan.RefreshButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;

public class BanHangGUI extends JPanel{
    public JButton btnHoanThanh;
    public JButton btnKhachHang;
    public JButton btnKhuyenMai;
    public JButton btnThem;
    public JButton btnXoa;
    public JButton btnLamMoi;
    public JPanel jPanel1;
    public JLabel lblDonGia;
    public JLabel lblGioHang;
    public JLabel lblKhachHang;
    public JLabel lblMaHD;
    public JLabel lblMaMA;
    public JLabel lblMonAn;
    public JLabel lblNgayNhap;
    public JLabel lblNhanVien;
    public JLabel lblSoLuong;
    public JLabel lblTenMA;
    public JLabel lblTienKhachDua;
    public JLabel lblTienThoi;
    public JLabel lblTimKiem;
    public JLabel lblTitleInfo;
    public JLabel lblTongTien;
    public JPanel pnlGioHang;
    public JPanel pnlInfo;
    public JPanel pnlInfoMonAn;
    public JPanel pnlMonAn;
    public JPanel pnlTable;
    public JSpinner spnSoLuong;
    public JTextField txtDonGia;
    public JTextField txtKhachHang;
    public JTextField txtKhuyenMai;
    public JTextField txtMaHD;
    public JTextField txtMaMA;
    public JTextField txtNgayNhap;
    public JTextField txtNhanVien;
    public JTextField txtTenMA;
    public JTextField txtTienKhachDua;
    public JTextField txtTienThoi;
    public JTextField txtTimKiem;
    public JTextField txtTongTien;
    public MyTable pnlTableMonAn;
    public MyTable pnlTableGioHang;
	
	public BanHangGUI() {
		init();
	}
	
	public void init() {
        pnlInfo = new JPanel();
        lblTitleInfo = new JLabel();
        pnlInfoMonAn = new JPanel();
        lblMaMA = new JLabel();
        lblMaHD = new JLabel();
        lblSoLuong = new JLabel();
        lblTenMA = new JLabel();
        lblDonGia = new JLabel();
        txtTienKhachDua = new JTextField();
        txtMaHD = new JTextField();
        txtMaMA = new JTextField();
        txtTenMA = new JTextField();
        txtDonGia = new JTextField();
        btnThem = new ThemButton();
        btnXoa = new XoaButton();
        btnHoanThanh = new JButton();
        lblNhanVien = new JLabel();
        lblTienKhachDua = new JLabel();
        lblNgayNhap = new JLabel();
        txtNhanVien = new JTextField();
        txtKhachHang = new JTextField();
        txtNgayNhap = new JTextField();
        spnSoLuong = new JSpinner();
        btnKhachHang = new MoreButton();
        btnKhuyenMai = new JButton();
        lblTienThoi = new JLabel();
        txtTienThoi = new JTextField();
        lblKhachHang = new JLabel();
        lblTongTien = new JLabel();
        txtKhuyenMai = new JTextField();
        txtTongTien = new JTextField();
        pnlTable = new JPanel();
        pnlMonAn = new JPanel();
        lblMonAn = new JLabel();
        jPanel1 = new JPanel();
        lblTimKiem = new JLabel();
        txtTimKiem = new JTextField();
        pnlGioHang = new JPanel();
        lblGioHang = new JLabel();
        btnLamMoi = new RefreshButton();
        
        String[] headers = {"Mã món ăn", "Tên món ăn", "Số lượng", "Đơn vị tính", "Đơn giá", "Loại"};
        pnlTableMonAn = new MyTable();
        pnlTableMonAn.setHeaders(headers);
        pnlTableGioHang = new MyTable();
        pnlTableGioHang.setHeaders(headers);

        setLayout(new BorderLayout());

        pnlInfo.setPreferredSize(new Dimension(400, 0));
        pnlInfo.setLayout(new BorderLayout());

        lblTitleInfo.setFont(new Font("Segoe UI", 1, 24)); // lblTitleInfo
        lblTitleInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitleInfo.setText("Thông tin bán hàng");
        pnlInfo.add(lblTitleInfo, BorderLayout.NORTH);

        pnlInfoMonAn.setLayout(null);

        lblMaMA.setFont(new Font("Segoe UI", 1, 18)); // lblMaMA
        lblMaMA.setText("Mã món ăn");
        pnlInfoMonAn.add(lblMaMA);
        lblMaMA.setBounds(20, 60, 110, 30);

        lblMaHD.setFont(new Font("Segoe UI", 1, 18)); // lblMaHD
        lblMaHD.setText("Mã hóa đơn");
        pnlInfoMonAn.add(lblMaHD);
        lblMaHD.setBounds(20, 20, 110, 30);

        lblSoLuong.setFont(new Font("Segoe UI", 1, 18)); // lblSoLuong
        lblSoLuong.setText("Số lượng");
        pnlInfoMonAn.add(lblSoLuong);
        lblSoLuong.setBounds(20, 180, 110, 30);

        lblTenMA.setFont(new Font("Segoe UI", 1, 18)); // lblTenMA
        lblTenMA.setText("Tên món ăn");
        pnlInfoMonAn.add(lblTenMA);
        lblTenMA.setBounds(20, 100, 110, 30);

        lblDonGia.setFont(new Font("Segoe UI", 1, 18)); // lblDonGia
        lblDonGia.setText("Đơn giá");
        pnlInfoMonAn.add(lblDonGia);
        lblDonGia.setBounds(20, 140, 110, 30);

        txtTienKhachDua.setFont(new Font("Segoe UI", 0, 18)); // txtTienKhachDua
        pnlInfoMonAn.add(txtTienKhachDua);
        txtTienKhachDua.setBounds(20, 450, 360, 30);
        txtTienKhachDua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Format Tiền
//				String tien = (String) txtTienKhachDua.getText();
//        		if(tien != "") {
//        			for (int i = 0; i < tien.length(); i++) {
//        				if(String.valueOf(tien.charAt(i)).matches("\\d+")) {
//        					txtTienKhachDua.setText(FormatMoney.getFormat(Long.parseLong(tien)));
//                		}
//    				}
//        		}
			}
		});

        txtMaHD.setEditable(false);
        txtMaHD.setFont(new Font("Segoe UI", 0, 18)); // txtMaHD
        pnlInfoMonAn.add(txtMaHD);
        txtMaHD.setBounds(130, 20, 250, 30);

        txtMaMA.setEditable(false);
        txtMaMA.setFont(new Font("Segoe UI", 0, 18)); // txtMaMA
        pnlInfoMonAn.add(txtMaMA);
        txtMaMA.setBounds(130, 60, 250, 30);

        txtTenMA.setEditable(false);
        txtTenMA.setFont(new Font("Segoe UI", 0, 18)); // txtTenMA
        pnlInfoMonAn.add(txtTenMA);
        txtTenMA.setBounds(130, 100, 250, 30);

        txtDonGia.setEditable(false);
        txtDonGia.setFont(new Font("Segoe UI", 0, 18)); // txtDonGia
        pnlInfoMonAn.add(txtDonGia);
        txtDonGia.setBounds(130, 140, 250, 30);

        btnThem.setText("Thêm");
        pnlInfoMonAn.add(btnThem);
        btnThem.setBounds(100, 223, 100, 31);

        btnXoa.setText("Xóa");
        pnlInfoMonAn.add(btnXoa);
        btnXoa.setBounds(214, 223, 95, 31);

        btnHoanThanh.setText("Hoàn thành");
        btnHoanThanh.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8-complete-30.png")));
        
        btnHoanThanh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            }
        });
        pnlInfoMonAn.add(btnHoanThanh);
        btnHoanThanh.setBounds(55, 580, 140, 40);

        btnLamMoi.setText("Làm mới");
        pnlInfoMonAn.add(btnLamMoi);
        btnLamMoi.setBounds(205, 580, 140, 40);
        
        lblNhanVien.setFont(new Font("Segoe UI", 1, 18)); // lblNhanVien
        lblNhanVien.setText("Nhân viên");
        pnlInfoMonAn.add(lblNhanVien);
        lblNhanVien.setBounds(20, 310, 110, 30);

        lblTienKhachDua.setFont(new Font("Segoe UI", 1, 18)); // lblTienKhachDua
        lblTienKhachDua.setText("Tiền khách đưa");
        pnlInfoMonAn.add(lblTienKhachDua);
        lblTienKhachDua.setBounds(130, 420, 140, 30);

        lblNgayNhap.setFont(new Font("Segoe UI", 1, 18)); // lblNgayNhap
        lblNgayNhap.setText("Ngày nhập");
        pnlInfoMonAn.add(lblNgayNhap);
        lblNgayNhap.setBounds(20, 270, 110, 30);

        txtNhanVien.setEditable(false);
        txtNhanVien.setFont(new Font("Segoe UI", 0, 18)); // txtNhanVien
        pnlInfoMonAn.add(txtNhanVien);
        txtNhanVien.setBounds(130, 310, 250, 30);
        txtNhanVien.setText(MainLayoutGUI.nhanvien.getTenNV());

        txtKhachHang.setEditable(false);
        txtKhachHang.setFont(new Font("Segoe UI", 0, 18)); // txtKhachHang
        pnlInfoMonAn.add(txtKhachHang);
        txtKhachHang.setBounds(130, 350, 210, 30);

        txtNgayNhap.setEditable(false);
        txtNgayNhap.setFont(new Font("Segoe UI", 0, 18)); // txtNgayNhap
        pnlInfoMonAn.add(txtNgayNhap);
        txtNgayNhap.setBounds(130, 270, 250, 30);
        
        // Ngày nhập
        Date date = Date.valueOf(LocalDate.now());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        txtNgayNhap.setText(strDate);

        spnSoLuong.setFont(new Font("Segoe UI", 0, 18)); // spnSoLuong
        pnlInfoMonAn.add(spnSoLuong);
        spnSoLuong.setBounds(130, 180, 250, 30);

        pnlInfoMonAn.add(btnKhachHang);
        btnKhachHang.setBounds(350, 350, 30, 30);
        btnKhachHang.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FormChonKH dialog = new FormChonKH(MainLayoutGUI.f, true);
				Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
				int x = (d.width - dialog.getSize().width) / 2;
				int y = (d.height - dialog.getSize().height) / 2;
				dialog.setLocation(x, y);
                dialog.setVisible(true);
			}
		});

        btnKhuyenMai.setFont(new Font("Segoe UI", 1, 12)); // btnKhuyenMai
        btnKhuyenMai.setText("Khuyến mãi");
        pnlInfoMonAn.add(btnKhuyenMai);
        btnKhuyenMai.setBounds(20, 390, 100, 30);

        lblTienThoi.setFont(new Font("Segoe UI", 1, 18)); // lblTienThoi
        lblTienThoi.setText("Tiền thối");
        pnlInfoMonAn.add(lblTienThoi);
        lblTienThoi.setBounds(20, 530, 110, 30);

        txtTienThoi.setEditable(false);
        txtTienThoi.setFont(new Font("Segoe UI", 0, 18)); // txtTienThoi
        pnlInfoMonAn.add(txtTienThoi);
        txtTienThoi.setBounds(130, 530, 250, 30);

        lblKhachHang.setFont(new Font("Segoe UI", 1, 18)); // lblKhachHang
        lblKhachHang.setText("Khách hàng");
        pnlInfoMonAn.add(lblKhachHang);
        lblKhachHang.setBounds(20, 350, 110, 30);

        lblTongTien.setFont(new Font("Segoe UI", 1, 18)); // lblTongTien
        lblTongTien.setText("Tổng tiền");
        pnlInfoMonAn.add(lblTongTien);
        lblTongTien.setBounds(20, 490, 110, 30);

        txtKhuyenMai.setEditable(false);
        txtKhuyenMai.setFont(new Font("Segoe UI", 0, 18)); // txtKhuyenMai
        pnlInfoMonAn.add(txtKhuyenMai);
        txtKhuyenMai.setBounds(130, 390, 250, 30);

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new Font("Segoe UI", 0, 18)); // txtTongTien
        pnlInfoMonAn.add(txtTongTien);
        txtTongTien.setBounds(130, 490, 250, 30);

        pnlInfo.add(pnlInfoMonAn, BorderLayout.CENTER);

        add(pnlInfo, BorderLayout.EAST);

        pnlTable.setLayout(new BoxLayout(pnlTable, BoxLayout.Y_AXIS));

        pnlMonAn.setPreferredSize(new Dimension(0, 600));
        pnlMonAn.setLayout(new BorderLayout());

        lblMonAn.setFont(new Font("Segoe UI", 1, 24)); // lblMonAn
        lblMonAn.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonAn.setText("Danh sách món ăn");
        pnlMonAn.add(lblMonAn, BorderLayout.NORTH);

        lblTimKiem.setFont(new Font("Segoe UI", 1, 18)); // lblTimKiem
        lblTimKiem.setText("Tìm kiếm");
        jPanel1.add(lblTimKiem);

        txtTimKiem.setFont(new Font("Segoe UI", 0, 18)); // txtTimKiem
        txtTimKiem.setPreferredSize(new Dimension(490, 30));
        jPanel1.add(txtTimKiem);

        pnlMonAn.add(jPanel1, BorderLayout.SOUTH);

        pnlMonAn.add(pnlTableMonAn, BorderLayout.CENTER);

        pnlTable.add(pnlMonAn);

        pnlGioHang.setLayout(new BorderLayout());

        lblGioHang.setFont(new Font("Segoe UI", 1, 24)); // lblGioHang
        lblGioHang.setHorizontalAlignment(SwingConstants.CENTER);
        lblGioHang.setText("Giỏ hàng");
        pnlGioHang.add(lblGioHang, BorderLayout.NORTH);

        pnlGioHang.add(pnlTableGioHang, BorderLayout.CENTER);

        pnlTable.add(pnlGioHang);

        add(pnlTable, BorderLayout.CENTER);
	}
}
