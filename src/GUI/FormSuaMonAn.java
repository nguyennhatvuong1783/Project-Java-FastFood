package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BUS.MonAnBus;
import DAO.DaoMonAn;
import DTO.MONAN;
import GiaoDienChuan.CancelButton;
import GiaoDienChuan.FileButton;
import GiaoDienChuan.SaveButton;
import GiaoDienChuan.ThemButton;

public class FormSuaMonAn extends JFrame{
	MonAnBus monAnBus = new MonAnBus();
	MONAN monan;
	private JTextField txtMaMA;
	private JTextField txtTenMA;
	private JTextField txtSL;
	private JTextField txtDonViTinh;
	private JTextField txtDonGia;
	private JComboBox<String> cbLoai;
	private JLabel lblHinhAnh;
	private FileButton btnChonAnh;
	private String path;
	
	private String maMA, tenMA, soLuong, donViTinh, donGia, loai, hinhAnh;
	private FileDialog fd;
	public FormSuaMonAn() {
		init();
	}
	

	public FormSuaMonAn(String maMA, String tenMA, String soLuong, String donViTinh, String donGia, String loai,
			String hinhAnh) throws HeadlessException {
		super();
		this.maMA = maMA;
		this.tenMA = tenMA;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
		this.donGia = donGia;
		this.loai = loai;
		this.hinhAnh = hinhAnh;
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
		cbLoai = new JComboBox<String>(new String[] {"Thức ăn", "Đồ uống"});
		
		
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
		gbctxt.ipadx = 378;
		gbctxt.ipady = 5;
		gbctxt.gridx = 1;
		pnlInfo.add(cbLoai, gbctxt);
		
		JPanel pnlImage = new JPanel(null);
		pnlImage.setPreferredSize(new Dimension(450, 200));
		
		lblHinhAnh = new JLabel();
		lblHinhAnh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblHinhAnh.setBounds(30, 100, 200, 190);		
		btnChonAnh = new FileButton();
		btnChonAnh.setText("Chọn ảnh");
		btnChonAnh.setBounds(50, 303, 160, 40);
		btnChonAnh.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				MouseClickChonAnh();
			}
		});
		
		lblHinhAnh.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnChonAnh.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pnlImage.add(lblHinhAnh);
		pnlImage.add(Box.createRigidArea(new Dimension(0, 3)));
		pnlImage.add(btnChonAnh);
		
		pnlInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		inforPanel.add(pnlInfo);
		inforPanel.add(pnlImage);
		
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
		txtMaMA.setText(maMA);
		txtTenMA.setText(tenMA);
		txtSL.setText(soLuong);
		txtDonViTinh.setText(donViTinh);
		txtDonGia.setText(donGia);
		cbLoai.setSelectedItem(loai);
		lblHinhAnh.setIcon(loadImage(new File(hinhAnh), 200, 190));
		path = hinhAnh;
		
		this.add(inforPanel,BorderLayout.CENTER);
		this.add(panelChucNang,BorderLayout.SOUTH);
		this.setVisible(true);
		
	}
	
	private void MouseClickChonAnh() {
	    fd = new FileDialog(this);
		fd.setVisible(true);
		File[] file = fd.getFiles();
		if (file.length != 0) {
			lblHinhAnh.setIcon(loadImage(file[0], 200, 190));
			
		}
	}
	
	private void MouseClickHuy() {
		this.dispose();
	}
	
	private void MouseClickLuu() {
		if (checkInput()) {
			String ten = txtTenMA.getText();
			String donVi = txtDonViTinh.getText();
			String giaStr = txtDonGia.getText();
			String imagePath = path;
			String loaiString = (String) cbLoai.getSelectedItem();
			int sL = Integer.parseInt(soLuong);
			int gia = Integer.parseInt(giaStr);
			if (ten.equals(tenMA) && donVi.equals(donViTinh) && giaStr.equals(donGia) && path.equals(hinhAnh)) {
				JOptionPane.showMessageDialog(this,"Sửa thành công");
				this.dispose();
			}else {
				monan = new MONAN(maMA, ten, sL, donVi, gia, imagePath, loaiString, 1);
				if (DaoMonAn.getInstance().update(monan)!=0) {
					JOptionPane.showMessageDialog(this,"Sửa thành công");
					this.dispose();
				}
				
			}
		}
	}
	
	private Boolean checkInput() {
		String tenMa = txtTenMA.getText();
		String donViTinh = txtDonViTinh.getText();
		String donGia = txtDonGia.getText();
		if (tenMa.trim().equals("")) {
			return showErr(txtTenMA, "Tên món ăn không được để trống");	
		}else if (checkKyTuDacBiet(tenMa)== false) {
			return showErr(txtTenMA, "Tên món ăn không được chứa ký tự đặc biệt");	
		}else if (donViTinh.trim().equals("")) {
			return showErr(txtDonViTinh, "Đơn vị tính không được để trống");	
		}else if (checkKyTuDacBiet(donViTinh)==false) {
			return showErr(txtDonViTinh, "Đơn vị tính không được chứa ký tự đặc biệt");
		}else if (donGia.trim().equals("")) {
			return showErr(txtDonGia, "Đơn giá không được để trống");
		}else if (path==null) {
			return showErr(txtDonGia, "Chưa chọn hình ảnh");
		}
		else {
			try {
				int gia = Integer.parseInt(donGia);
				if (gia<10000) {
					return showErr(txtDonGia, "Đơn giá không hợp lệ");
				}
			} catch (Exception e) {
				return showErr(txtDonGia, "Đơn giá không hợp lệ");
			}
		}
		return true;
	}
	
	private Boolean showErr(JTextField tx, String erorr) {
		JOptionPane.showMessageDialog(tx, erorr);
		tx.requestFocus();
		return false;
	}
	
	private Boolean checkKyTuDacBiet(String tenMA) {
		Pattern pattern = Pattern.compile("^.*[#?!@$%^&*-]+.*$");
		for(int i=0; i<tenMA.length(); i++) {
			if (pattern.matcher(tenMA).find()) {
				return false;
			}
		}
		return true;
	}
	
	 public Icon loadImage(File file, int width, int height) {
	        System.out.println(file.getAbsolutePath());
	        path = file.getAbsolutePath();
	        BufferedImage image = null;
	        try {
				image = ImageIO.read(file);
			} catch (Exception e) {
				// TODO: handle exception
			}
	       if (image != null) {
	    	   ImageIcon img = new ImageIcon(image);
		        
		        int ix = img.getIconWidth();
		        int iy = img.getIconHeight();
		        int dx = 0, dy = 0;
		        if (width / height > ix / iy) {
		            dy = height;
		            dx = dy * ix / iy;
		        } else {
		            dx = width;
		            dy = dx * ix / iy;
		        }
		        Image imgScale = img.getImage().getScaledInstance(dx, dy, Image.SCALE_SMOOTH);
		        return new ImageIcon(imgScale);
			
		    }
	       return null;
	  }	

}
