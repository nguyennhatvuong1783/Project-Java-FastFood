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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.EmptyBorder;

import BUS.MonAnBus;
import DTO.MONAN;
import GiaoDienChuan.ExportExcelButton;
import GiaoDienChuan.FileButton;
import GiaoDienChuan.ImportExcelButton;
import GiaoDienChuan.MyTable;
import GiaoDienChuan.RefreshButton;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;

public class MonAnGUI extends JPanel{
	public static MyTable pnlMonAnTable;
	public JTextField txtMaMA;
	public JTextField txtTenMA;
	public JTextField txtSL;
	public JTextField txtDonViTinh;
	public JTextField txtDonGia;
	public JTextField cbLoai;
	public JLabel lblHinhAnh;
	public JButton btnThem;
	public JButton btnSua;
	public JButton btnXoa;
	public JButton btnNhap;
	public JButton btnXuat;
	public JButton btnLamMoi;
	public JTextField txtTimKiem;
	
	MonAnBus mAnBus = new MonAnBus();
	
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
		txtTenMA.setEditable(false);
		txtSL = new JTextField();
		txtSL.setEditable(false);
		txtDonViTinh = new JTextField();
		txtDonViTinh.setEditable(false);
		txtDonGia = new JTextField();
		txtDonGia.setEditable(false);
		cbLoai = new JTextField();
		cbLoai.setEditable(false);
		
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
		gbctxt.gridx = 1;
		pnlInfo.add(cbLoai, gbctxt);
		
		//pnlImage
		JPanel pnlImage = new JPanel(null);
		pnlImage.setPreferredSize(new Dimension(450, 250));
		
		lblHinhAnh = new JLabel("", JLabel.CENTER);
		lblHinhAnh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblHinhAnh.setBounds(5, 20, 200, 190);		
				
		pnlImage.add(lblHinhAnh);
				
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
		
		//sự kiện
		addDocumentListener(txtTimKiem);
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FormThemMonAn a = new FormThemMonAn();
				a.addWindowListener(new WindowAdapter() {
					@Override
					 public void windowClosed(java.awt.event.WindowEvent windowEvent) {
		                clearInfo();
		                mAnBus.readDB();
		                mAnBus.setDataToTable(mAnBus.getDsMonAn(), pnlMonAnTable);
		            }
				});
			}
		});
		btnLamMoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearInfo();
				mAnBus.readDB();
				mAnBus.setDataToTable(mAnBus.getDsMonAn(), pnlMonAnTable);
			}
		});
		btnXoa.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				listenerXoa();
			}
		});
		
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
		mAnBus.setDataToTable(mAnBus.getDsMonAn(), pnlMonAnTable);
		pnlMonAnTable.getTable().addMouseListener(new MouseListener() {		
			@Override
			public void mouseReleased(MouseEvent e) {
				tableMouseClicked(e);
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}			
			@Override
			public void mouseExited(MouseEvent e) {
			}			
			@Override
			public void mouseEntered(MouseEvent e) {				
			}			
			@Override
			public void mouseClicked(MouseEvent e) {
								
			}
		});
		pnlMonAnTable.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {
			}			
			@Override
			public void keyReleased(KeyEvent e) {
				tableKeyRelease(e);
			}		
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

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
	
	 public void showInfo(int selectedIndex) {
		 if (selectedIndex!=-1) {
			 String maMA = pnlMonAnTable.getValueAt(selectedIndex, 0);
			 String tenMA = pnlMonAnTable.getValueAt(selectedIndex, 1);
			 String soLuong	= pnlMonAnTable.getValueAt(selectedIndex, 2);
			 String donViTinh	= pnlMonAnTable.getValueAt(selectedIndex, 3);
			 String donGia	= pnlMonAnTable.getValueAt(selectedIndex, 4);
			 String Loai	= pnlMonAnTable.getValueAt(selectedIndex, 5);
			 String path = mAnBus.getHinhAnh(maMA);
			 setTextForTxt(maMA, tenMA, soLuong, donViTinh, donGia, Loai, path);
		}
	 }
	 
	 public void clearInfo() {
		 txtMaMA.setText("");
		 txtTenMA.setText("");
		 txtSL.setText("");
		 txtDonViTinh.setText("");
		 txtDonGia.setText("");
		 cbLoai.setText("");
		 lblHinhAnh.setIcon(null);
	 }
	 
	 public void setTextForTxt(String maMA, String tenMA, String soLuong, String donViTinh, String donGia, String Loai, String path) {
		 txtMaMA.setText(maMA);
		 txtTenMA.setText(tenMA);
		 txtSL.setText(soLuong);
		 txtDonViTinh.setText(donViTinh);
		 txtDonGia.setText(donGia);
		 cbLoai.setText(Loai);
		 lblHinhAnh.setIcon(loadImage(path, 200, 190));
	 }
	 
	 public void tableMouseClicked(java.awt.event.MouseEvent evt) {
		 int index = pnlMonAnTable.getTable().getSelectedRow();
		 showInfo(index);
	 }
	 
	 public void tableKeyRelease(java.awt.event.KeyEvent key) {
		 if (key.getKeyCode() == KeyEvent.VK_UP || key.getKeyCode() == KeyEvent.VK_DOWN) {
			 int index = pnlMonAnTable.getTable().getSelectedRow();
			 showInfo(index);
		}
	 }
	 
	 public void listenerXoa() {
		 int index = pnlMonAnTable.getTable().getSelectedRow();
		 if (index==-1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn món ăn cần xóa");
		}else {
			if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa món ăn","Chú ý",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION) {
				mAnBus.xoaMonAn(pnlMonAnTable.getValueAt(index, 0));
				JOptionPane.showMessageDialog(this, "Xóa thành công");
				mAnBus.setDataToTable(mAnBus.getDsMonAn(), pnlMonAnTable);
				clearInfo();
			}
		}
	 }
	 
	 public void searchMonAn() {
		 String request = txtTimKiem.getText();
		 if (request!=null) {
			mAnBus.setDataToTable(mAnBus.getDsMonAn(), pnlMonAnTable);
		}
		 else {
			 mAnBus.setDataToTable(mAnBus.searchMonAn(request), pnlMonAnTable);
		}
	 }
	 
	 public Icon loadImage(String path, int width, int height) {
	        File file = new File(path);
	        BufferedImage image = null;
	        try {
				image = ImageIO.read(file);
			} catch (Exception e) {
				// TODO: handle exception
			}
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
	 
	 private void addDocumentListener(JTextField tx) {
	        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
	        tx.getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                txSearchOnChange();
	            }

				@Override
	            public void removeUpdate(DocumentEvent e) {
	                txSearchOnChange();
	            }

	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                txSearchOnChange();
	            }
	        });
	    }

	   private void txSearchOnChange() {
			mAnBus.setDataToTable(mAnBus.searchMonAn(txtTimKiem.getText()), pnlMonAnTable);
			
		}

	
}
