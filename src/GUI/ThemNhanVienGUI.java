package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BUS.NhanVienBUS;
import DAO.DaoNhanVien;
import DTO.NHANVIEN;
import GiaoDienChuan.CancelButton;
import GiaoDienChuan.ThemButton;

public class ThemNhanVienGUI extends JFrame{
	NhanVienBUS BUS = new NhanVienBUS();
	NHANVIEN nv;
	private JTextField txtManv;
	private JTextField txtTennv;
	private JTextField txtNgaySinh;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JComboBox<String> cbGioiTinh;
	
	public ThemNhanVienGUI() {
		init();
	}
	

	public void init() {
		JPanel inforPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(inforPanel, BoxLayout.X_AXIS);	
		inforPanel.setLayout(boxLayout);
		this.setLayout(new BorderLayout());
		this.setSize(850, 500);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JLabel lblManv = new JLabel("     Mã nhân viên");
        JLabel lblTennv = new JLabel("     Tên nhân viên");
        JLabel lblNgaySinh = new JLabel("     Ngày Sinh");
        JLabel lblSDT = new JLabel("     Số điện thoại");
        JLabel lblDiaChi = new JLabel("     Địa chỉ");
        JLabel lblGioiTinh = new JLabel("             Giới tính");
		
		lblManv.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTennv.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSDT.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblGioiTinh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNgaySinh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		JPanel pnlInfo = new JPanel(new GridBagLayout());
		GridBagConstraints gbclbl = new GridBagConstraints();
		GridBagConstraints gbctxt = new GridBagConstraints();
		
		txtManv = new JTextField();
		txtManv.setEditable(false);
		txtTennv = new JTextField();
		txtSDT = new JTextField();
		txtDiaChi = new JTextField();
		cbGioiTinh = new JComboBox<String>(new String[] {"Nam", "Nữ"});
		txtNgaySinh = new JTextField();
		
		txtManv.setPreferredSize(new Dimension(300, 25));
		txtTennv.setPreferredSize(new Dimension(300, 25));
		txtSDT.setPreferredSize(new Dimension(300, 25));
		txtDiaChi.setPreferredSize(new Dimension(300, 25));
		cbGioiTinh.setPreferredSize(new Dimension(300, 25));
		txtNgaySinh.setPreferredSize(new Dimension(300, 25));

		
		// defaut value
		txtManv.setText(BUS.nextId(BUS.getLastID()));
		
        gbclbl.ipadx = 9;
        gbclbl.ipady = 5;
        pnlInfo.add(lblManv, gbclbl);
        gbctxt.ipadx = 450;
        gbctxt.ipady = 10;
        gbctxt.gridx = 1;
        pnlInfo.add(txtManv ,gbctxt);
        gbclbl.ipadx = 5;
        gbclbl.gridx = 0;
        gbclbl.gridy = 1;
        pnlInfo.add(lblTennv, gbclbl);
        gbctxt.gridx = 1;
        pnlInfo.add(txtTennv, gbctxt);
        gbclbl.ipadx = 21;
        gbclbl.gridx = 0;
        gbclbl.gridy = 2;
        pnlInfo.add(lblSDT, gbclbl);
        gbctxt.gridx = 1;
        pnlInfo.add(txtSDT, gbctxt);
        gbclbl.ipadx = 6;
        gbclbl.gridx = 0;
        gbclbl.gridy = 3;
        pnlInfo.add(lblDiaChi, gbclbl);
        gbctxt.gridx = 1;
        pnlInfo.add(txtDiaChi, gbctxt);
        gbclbl.ipadx = 29;
        gbclbl.gridx = 0;
        gbclbl.gridy = 4;
        pnlInfo.add(lblNgaySinh, gbclbl);
        gbctxt.gridx = 1;
        pnlInfo.add(txtNgaySinh, gbctxt);
        gbclbl.ipadx = 49;
        gbclbl.gridx = 0;
        gbclbl.gridy = 5;
        pnlInfo.add(lblGioiTinh, gbclbl);
        gbctxt.ipadx = 401;
		gbctxt.ipady = 5;
        gbctxt.gridx = 1;
        pnlInfo.add(cbGioiTinh, gbctxt);
		
		
		inforPanel.add(pnlInfo);
	
		
		//Panel chuc nang
		JPanel panelChucNang = new JPanel(new FlowLayout(FlowLayout.CENTER,6,0));
		JButton btnThem = new ThemButton();
		JButton btnHuy = new CancelButton();
		panelChucNang.add(btnThem);
		panelChucNang.add(btnHuy);
		btnHuy.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				MouseClicnvuy();				
			}
		});
		btnThem.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				MouseClickThem();		
			}
		});
		this.add(inforPanel,BorderLayout.CENTER);
		this.add(panelChucNang,BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	
	
	private void MouseClickThem() {
		if (checkInput()) {
			String Manv = txtManv.getText();
			String Tennv = txtTennv.getText();
			String DiaChi = txtDiaChi.getText();
			String NgaySinh = txtNgaySinh.getText();
			String GioiTinh = (String)cbGioiTinh.getSelectedItem();
			String SDT =txtSDT.getText();
			 nv = new NHANVIEN(Manv, Tennv,GioiTinh,NgaySinh,SDT , DiaChi, 1);
		    if (DaoNhanVien.getInstance().insert(nv)!=0) {
		    	JOptionPane.showMessageDialog(this,"Thêm thành công");
				this.dispose();
			}
		}
	}
	
	private void MouseClicnvuy() {
		this.dispose();
	}
	
	private Boolean checkInput() {
		String Tennv = txtTennv.getText();
		String DiaChi = txtDiaChi.getText();
		String SDT = txtSDT.getText();
		if (Tennv.trim().equals("")) {
			return showErr(txtTennv, "Tên nhân viên không được để trống");	
		}else if (checkKyTuDacBiet(Tennv)== false) {
			return showErr(txtTennv, "Tên nhân viên không được chứa ký tự đặc biệt");	
		}else if (DiaChi.trim().equals("")) {
			return showErr(txtDiaChi, "Địa chỉ không được để trống");	
		}else if (checkKyTuDacBiet(DiaChi)==false) {
			return showErr(txtDiaChi, "Địa chỉ không được chứa ký tự đặc biệt");
		}else if (checkSoLuongSo(SDT)==false) {
			return showErr(txtSDT,"Số điện thoại chỉ có 10 số");
		}
		else if (checkPhoneNumber(SDT)==false) {
			return showErr(txtSDT,"Ký tự đầu của số điện thoại phải là 0");
		}else if (checkKyTuDacBiet(SDT)==false) {
			return showErr(txtSDT,"Số điện thoại không được chứa ký tự đặc biệt");
		}
		return true;
	}
	private Boolean checkSoLuongSo(String sdt) {
		String regex ="\\d{10}";
		if(sdt.matches(regex)) {
			return true;
		}
		return false;
	}
	private Boolean checkPhoneNumber(String sdt) {
		String regex = "^0\\d*";
		if(sdt.matches(regex))return true;
		return false;
	}
	
	private Boolean showErr(JTextField tx, String erorr) {
		JOptionPane.showMessageDialog(tx, erorr);
		tx.requestFocus();
		return false;
	}
	
	private Boolean checkKyTuDacBiet(String Tennv) {
		Pattern pattern = Pattern.compile("^.*[#?!@$%^&*-]+.*$");
		for(int i=0; i<Tennv.length(); i++) {
			if (pattern.matcher(Tennv).find()) {
				return false;
			}
		}
		return true;
	}
	
	 
	

}
