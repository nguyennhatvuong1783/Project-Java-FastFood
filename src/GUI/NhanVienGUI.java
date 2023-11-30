/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import BUS.NhanVienBUS;
import GiaoDienChuan.ExportExcelButton;
import GiaoDienChuan.ImportExcelButton;
import GiaoDienChuan.MyTable;
import GiaoDienChuan.RefreshButton;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;
import WorkExcel.DocExcel;
import WorkExcel.XuatExcel;


public class NhanVienGUI extends JPanel {
	  	public static MyTable pnlNhaCungCapTable;
	    public JTextField txtMaNV;
	    public JTextField txtTenNV;
	    public JTextField txtSDT;
	    public JTextField txtDiaChi;
	    public JTextField txtGioiTinh;
	    public JTextField txtNgaySinh;
	    public JTextField cbLoai;
	    public JLabel lblHinhAnh;
	    public JButton btnThem;
	    public JButton btnSua;
	    public JButton btnXoa;
	    public JButton btnNhap;
	    public JButton btnXuat;
	    public JButton btnLamMoi;
	    public JTextField txtTimKiem;
	    public JComboBox<String> cbGioiTinh;

	    NhanVienBUS BUS = new NhanVienBUS();

	    public NhanVienGUI() {
	        init();
	    }

	    public void init() {
	        
	        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	        JLabel lblTitle = new JLabel("QUẢN LÝ nhân viên", JLabel.CENTER);
	        lblTitle.setBorder(new EmptyBorder(5, 0, 0, 0));
	        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));

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
			
			JPanel pnlNhanVien = new JPanel();
	        BoxLayout boxLayout = new BoxLayout(pnlNhanVien, BoxLayout.X_AXIS);
	        pnlNhanVien.setLayout(boxLayout);
			
			JPanel pnlInfo = new JPanel(new GridBagLayout());
			GridBagConstraints gbclbl = new GridBagConstraints();
			GridBagConstraints gbctxt = new GridBagConstraints();
			
			txtMaNV = new JTextField();
			txtMaNV.setEditable(false);
			txtTenNV = new JTextField();
			txtTenNV.setEditable(false);
			txtSDT = new JTextField();
			txtSDT.setEditable(false);
			txtDiaChi = new JTextField();
			txtDiaChi.setEditable(false);
			txtNgaySinh = new JTextField();
			txtNgaySinh.setEditable(false);
			txtGioiTinh = new JTextField();
			txtGioiTinh.setEditable(false);
			
			txtMaNV.setPreferredSize(new Dimension(300, 25));
			txtTenNV.setPreferredSize(new Dimension(300, 25));
			txtSDT.setPreferredSize(new Dimension(300, 25));
			txtDiaChi.setPreferredSize(new Dimension(300, 25));
			txtNgaySinh.setPreferredSize(new Dimension(300, 25));
			txtGioiTinh.setPreferredSize(new Dimension(300, 25));
			
			// defaut value
			txtMaNV.setText(BUS.nextId(BUS.getLastID()));
			
	        gbclbl.ipadx = 9;
	        gbclbl.ipady = 5;
	        pnlInfo.add(lblManv, gbclbl);
	        gbctxt.ipadx = 450;
	        gbctxt.ipady = 10;
	        gbctxt.gridx = 1;
	        pnlInfo.add(txtMaNV ,gbctxt);
	        gbclbl.ipadx = 5;
	        gbclbl.gridx = 0;
	        gbclbl.gridy = 1;
	        pnlInfo.add(lblTennv, gbclbl);
	        gbctxt.gridx = 1;
	        pnlInfo.add(txtTenNV, gbctxt);
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
	        pnlInfo.add(txtGioiTinh, gbctxt);
			
			
			pnlNhanVien.add(pnlInfo);
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
	                ThemNhanVienGUI GUI = new ThemNhanVienGUI();
	                GUI.addWindowListener(new WindowAdapter() {
	                    @Override
	                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
	                        clearInfo();
	                        BUS.readDB();
	                        BUS.setDataToTable(BUS.getDsNHANVIEN(), pnlNhaCungCapTable);
	                    }
	                });
	            }
	        });
	        btnLamMoi.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                clearInfo();
	                BUS.readDB();
	                BUS.setDataToTable(BUS.getDsNHANVIEN(), pnlNhaCungCapTable);
	            }
	        });
	        btnXoa.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                listenerXoa();
	            }
	        });
	        btnSua.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                listenerSua();
	            }
	        });
	        btnXuat.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                XuatExcel xuatExcel = new XuatExcel();
	                xuatExcel.xuatFileExcelNhanVien();
	            }
	        });
	        btnNhap.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					DocExcel docExcel = new DocExcel();
					docExcel.docFileExcelNhanVien();
					
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
	        pnlNhaCungCapTable = new MyTable();
	        String[] headers = {"Mã nhân viên", "Tên nhân viên","Giới tính","Ngày sinh" ,"Số điện thoại", "Địa chỉ"};
	        pnlNhaCungCapTable.setHeaders(headers);
	        BUS.setDataToTable(BUS.getDsNHANVIEN(), pnlNhaCungCapTable);
	        pnlNhaCungCapTable.getTable().addMouseListener(new MouseListener() {
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
	        pnlNhaCungCapTable.addKeyListener(new KeyListener() {
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
	        this.add(Box.createRigidArea(new Dimension(0, 3)));
	        this.add(pnlNhanVien);
	        this.add(Box.createRigidArea(new Dimension(0, 3)));
	        this.add(pnlTimKiem);
	        this.add(Box.createRigidArea(new Dimension(0, 3)));
	        this.add(pnlbtn);
	        this.add(Box.createRigidArea(new Dimension(0, 3)));
	        this.add(pnlNhaCungCapTable);

	    }

	    public void showInfo(int selectedIndex) {
	        if (selectedIndex != -1) {
	            String MaNV = pnlNhaCungCapTable.getValueAt(selectedIndex, 0);
	            String tenNV = pnlNhaCungCapTable.getValueAt(selectedIndex, 1);
	            String gioiTinh = pnlNhaCungCapTable.getValueAt(selectedIndex, 2);
	            String ngaySinh = pnlNhaCungCapTable.getValueAt(selectedIndex, 3);
	            String sdt = pnlNhaCungCapTable.getValueAt(selectedIndex, 4);
	            String	diaChi = pnlNhaCungCapTable.getValueAt(selectedIndex, 5);
	            setTextForTxt(MaNV, tenNV, gioiTinh, ngaySinh,sdt,diaChi);
	        }
	    }

	    public void clearInfo() {
	        txtMaNV.setText("");
	        txtTenNV.setText("");
	        txtSDT.setText("");
	        txtDiaChi.setText("");
	        txtNgaySinh.setText("");
	        txtGioiTinh.setText("");
	    }

	    public void setTextForTxt(String MaNV, String tenNv, String gioiTinh,String NgaySinh,String sdt,String diaChi) {
	        txtMaNV.setText(MaNV);
	        txtTenNV.setText(tenNv);
	        txtGioiTinh.setText(gioiTinh);
	        txtNgaySinh.setText(NgaySinh);
	        txtSDT.setText(sdt);
	        txtDiaChi.setText(diaChi);
	    }

	    public void tableMouseClicked(java.awt.event.MouseEvent evt) {
	        int index = pnlNhaCungCapTable.getTable().getSelectedRow();
	        showInfo(index);
	    }

	    public void tableKeyRelease(java.awt.event.KeyEvent key) {
	        if (key.getKeyCode() == KeyEvent.VK_UP || key.getKeyCode() == KeyEvent.VK_DOWN) {
	            int index = pnlNhaCungCapTable.getTable().getSelectedRow();
	            showInfo(index);
	        }
	    }

	    public void listenerXoa() {
	        int index = pnlNhaCungCapTable.getTable().getSelectedRow();
	        if (index == -1) {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa");
	        } else {
	            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhân viên", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
	                BUS.xoa(pnlNhaCungCapTable.getValueAt(index, 0));
	                JOptionPane.showMessageDialog(this, "Xóa thành công");
	                BUS.setDataToTable(BUS.getDsNHANVIEN(), pnlNhaCungCapTable);
	                clearInfo();
	            }
	        }
	    }

	    public void listenerSua() {
	        int index = pnlNhaCungCapTable.getTable().getSelectedRow();
	        if (index == -1) {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa");
	        } else {
	            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa nhân viên", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
	                SuaNhanVienGUI GUI = new SuaNhanVienGUI(txtMaNV.getText(),txtTenNV.getText(),txtGioiTinh.getText(),txtNgaySinh.getText(),txtSDT.getText(),txtDiaChi.getText());
	                GUI.addWindowListener(new WindowAdapter() {
	                    @Override
	                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
	                        clearInfo();
	                        BUS.readDB();
	                        BUS.setDataToTable(BUS.getDsNHANVIEN(), pnlNhaCungCapTable);
	                    }
	                });
	            }

	        }
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
	        BUS.setDataToTable(BUS.searchNHANVIEN(txtTimKiem.getText()), pnlNhaCungCapTable);

	    }

}
