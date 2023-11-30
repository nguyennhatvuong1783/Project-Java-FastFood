package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BUS.KhachHangBUS;
import DAO.DaoKhachHang;
import DTO.KHACHHANG;
import GiaoDienChuan.CancelButton;
import GiaoDienChuan.SaveButton;

public class SuaKhachHangGUI extends JFrame{
	KhachHangBUS BUS = new KhachHangBUS();
	KHACHHANG kh;
	private JTextField txtmaKH;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	
	private String maKH, TenKH, SDT, DiaChi;
	private FileDialog fd;
	public SuaKhachHangGUI() {
		init();
	}
	

	public SuaKhachHangGUI(String maKH, String TenKH, String sdt, String DiaChi) throws HeadlessException {
		super();
		this.maKH = maKH;
		this.TenKH = TenKH;
		this.SDT = sdt;
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
		
		JLabel lblmaKH = new JLabel("     Mã khách hàng");
        JLabel lblTenKH = new JLabel("     Tên khách hàng");
        JLabel lblSDT = new JLabel("     Số điện thoại");
        JLabel lblDiaChi = new JLabel("     Địa chỉ");

        lblmaKH.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblTenKH.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblSDT.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		
		JPanel pnlInfo = new JPanel(new GridBagLayout());
		GridBagConstraints gbclbl = new GridBagConstraints();
		GridBagConstraints gbctxt = new GridBagConstraints();
		
		txtmaKH = new JTextField();
		txtmaKH.setEditable(false);
		txtTenKH = new JTextField();
		txtSDT = new JTextField();
		txtDiaChi = new JTextField();
		
		txtmaKH.setPreferredSize(new Dimension(300, 25));
		txtTenKH.setPreferredSize(new Dimension(300, 25));
		txtSDT.setPreferredSize(new Dimension(300, 25));
		txtDiaChi.setPreferredSize(new Dimension(300, 25));
		
		// defaut value
		txtmaKH.setText(BUS.nextId(BUS.getLastID()));
		
		
		 gbclbl.ipadx = 9;
	        gbclbl.ipady = 5;
	        pnlInfo.add(lblmaKH, gbclbl);
	        gbctxt.ipadx = 450;
	        gbctxt.ipady = 10;
	        gbctxt.gridx = 1;
	        pnlInfo.add(txtmaKH, gbctxt);
	        gbclbl.ipadx = 5;
	        gbclbl.gridx = 0;
	        gbclbl.gridy = 1;
	        pnlInfo.add(lblTenKH, gbclbl);
	        gbctxt.gridx = 1;
	        pnlInfo.add(txtTenKH, gbctxt);
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
		txtmaKH.setText(maKH);
		txtTenKH.setText(TenKH);
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
			String ten = txtTenKH.getText();
			String diachi = txtDiaChi.getText();
			String sdt = txtSDT.getText();
			if (ten.equals(TenKH) && diachi.equals(DiaChi)&&sdt.equals(SDT)) {
				JOptionPane.showMessageDialog(this,"Sửa thành công");
				this.dispose();
			}else {
				kh = new KHACHHANG(maKH, ten, diachi, sdt, 1);
				if (DaoKhachHang.getInstance().update(kh)!=0) {
					JOptionPane.showMessageDialog(this,"Sửa thành công");
					this.dispose();
				}
				
			}
		}
	}
	
	private Boolean checkInput() {
		String TenKH = txtTenKH.getText();
		String DiaChi = txtDiaChi.getText();
		String SDT = txtSDT.getText();
		if (TenKH.trim().equals("")) {
			return showErr(txtTenKH, "Tên khách hàng không được để trống");	
		}else if (checkKyTuDacBiet(TenKH)== false) {
			return showErr(txtTenKH, "Tên khách hàng không được chứa ký tự đặc biệt");	
		}else if (DiaChi.trim().equals("")) {
			return showErr(txtDiaChi, "Địa chỉ không được để trống");	
		}else if (checkKyTuDacBiet(DiaChi)==false) {
			return showErr(txtDiaChi, "Địa chỉkhông được chứa ký tự đặc biệt");
		}else if (checkSoLuongSo(SDT)==false) {
			return showErr(txtSDT,"Số điện thoại phải có 10 số");
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
	
	private Boolean checkKyTuDacBiet(String TenKH) {
		Pattern pattern = Pattern.compile("^.*[#?!@$%^&*-]+.*$");
		for(int i=0; i<TenKH.length(); i++) {
			if (pattern.matcher(TenKH).find()) {
				return false;
			}
		}
		return true;
	}
	
	
}
