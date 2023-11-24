package GUI;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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
import GiaoDienChuan.SaveButton;

public class SuaNhanVienGUI extends JFrame{
	NhanVienBUS BUS = new NhanVienBUS();
	NHANVIEN nv;
	private JTextField txtManv;
	private JTextField txtTennv;
	private JComboBox<String> cbGioiTinh;
	private JTextField txtNgaySinh;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	
	private String maNHANVIEN, Tennv, SDT, DiaChi,ngaySinh;
	private FileDialog fd;
	public SuaNhanVienGUI() {
		init();
	}
	

	public SuaNhanVienGUI(String maNHANVIEN, String Tennv,String GioiTinh,String NgaySinh ,String sdt, String DiaChi) throws HeadlessException {
		super();
		this.maNHANVIEN = maNHANVIEN;
		this.Tennv = Tennv;
		this.SDT = sdt;
		this.ngaySinh = NgaySinh;
		this.DiaChi = DiaChi;
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
        gbctxt.gridx = 1;
        pnlInfo.add(cbGioiTinh, gbctxt);
		
		
		inforPanel.add(pnlInfo);
	
		
		//Panel chuc nang
		JPanel panelChucNang = new JPanel(new FlowLayout(FlowLayout.CENTER,6,0));
		JButton btnLuu = new SaveButton();
		JButton btnHuy = new CancelButton();
		btnHuy.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				MouseClickHuy();
			}
		});
		btnLuu.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				MouseClickLuu();			
			}
		});
		panelChucNang.add(btnLuu);
		panelChucNang.add(btnHuy);
		
		// Set Value
		txtManv.setText(maNHANVIEN);
		txtTennv.setText(Tennv);
		txtNgaySinh.setText(ngaySinh);
		txtSDT.setText(SDT);
		txtDiaChi.setText(DiaChi);
		
		this.add(inforPanel,BorderLayout.CENTER);
		this.add(panelChucNang,BorderLayout.SOUTH);
		this.setVisible(true);
		
	}
	

	
	private void MouseClickHuy() {
		this.dispose();
	}
	
	private void MouseClickLuu() {
		if (checkInput()) {
			String ten = txtTennv.getText();
			String gioiTinh = (String)cbGioiTinh.getSelectedItem();
			String ngaySinh = txtNgaySinh.getText();
			String diachi = txtDiaChi.getText();
			String sdt = txtSDT.getText();
			if (ten.equals(Tennv) && diachi.equals(DiaChi)&&sdt.equals(SDT)) {
				JOptionPane.showMessageDialog(this,"Sửa thành công");
				this.dispose();
			}else {
				nv = new NHANVIEN(maNHANVIEN, ten,gioiTinh,ngaySinh ,diachi, sdt, 1);
				if (DaoNhanVien.getInstance().update(nv)!=0) {
					JOptionPane.showMessageDialog(this,"Sửa thành công");
					this.dispose();
				}
				
			}
		}
	}
	
	private Boolean checkInput() {
		String Tennv = txtTennv.getText();
		String DiaChi = txtDiaChi.getText();
		String sdt = txtSDT.getText();
		if (Tennv.trim().equals("")) {
			return showErr(txtTennv, "Tên nhà cung cấp không được để trống");	
		}else if (checkKyTuDacBiet(Tennv)== false) {
			return showErr(txtTennv, "Tên nhà cung cấp không được chứa ký tự đặc biệt");	
		}else if (DiaChi.trim().equals("")) {
			return showErr(txtDiaChi, "Đơn vị tính không được để trống");	
		}else if (checkKyTuDacBiet(DiaChi)==false) {
			return showErr(txtDiaChi, "Đơn vị tính không được chứa ký tự đặc biệt");
		}else if (sdt.trim().equals("")) {
			return showErr(txtSDT, "Đơn giá không được để trống");
		}
		
		return true;
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
