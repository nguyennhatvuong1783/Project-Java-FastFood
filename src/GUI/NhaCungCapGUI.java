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

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import BUS.NhaCungCapBUS;
import GiaoDienChuan.ExportExcelButton;
import GiaoDienChuan.ImportExcelButton;
import GiaoDienChuan.MyTable;
import GiaoDienChuan.RefreshButton;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;
import WorkExcel.XuatExcel;


public class NhaCungCapGUI extends JPanel {
	  	public static MyTable pnlNhaCungCapTable;
	    public JTextField txtMaNCC;
	    public JTextField txtTenNCC;
	    public JTextField txtSDT;
	    public JTextField txtDiaChi;
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

	    NhaCungCapBUS BUS = new NhaCungCapBUS();

	    public NhaCungCapGUI() {
	        init();
	    }

	    public void init() {
	        //this
	        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	        JLabel lblTitle = new JLabel("QUẢN LÝ nhà cung cấp", JLabel.CENTER);
	        lblTitle.setBorder(new EmptyBorder(5, 0, 0, 0));
	        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));

	        //pnlNhaCungCap
	        JLabel lblMaNCC = new JLabel("     Mã nhà cung cấp");
	        JLabel lblTenNCC = new JLabel("     Tên nhà cung cấp");
	        JLabel lblSDT = new JLabel("     Số điện thoại");
	        JLabel lblDiaChi = new JLabel("     Địa chỉ");

	        lblMaNCC.setFont(new Font("Times New Roman", Font.BOLD, 16));
	        lblTenNCC.setFont(new Font("Times New Roman", Font.BOLD, 16));
	        lblSDT.setFont(new Font("Times New Roman", Font.BOLD, 16));
	        lblDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 16));

	        JPanel pnlNhaCungCap = new JPanel();
	        BoxLayout boxLayout = new BoxLayout(pnlNhaCungCap, BoxLayout.X_AXIS);
	        pnlNhaCungCap.setLayout(boxLayout);

	        //pnlInfo
	        JPanel pnlInfo = new JPanel(new GridBagLayout());
	        GridBagConstraints gbclbl = new GridBagConstraints();
	        GridBagConstraints gbctxt = new GridBagConstraints();

	        txtMaNCC = new JTextField();
	        txtMaNCC.setEditable(false);
	        txtTenNCC = new JTextField();
	        txtTenNCC.setEditable(false);
	        txtSDT = new JTextField();
	        txtSDT.setEditable(false);
	        txtDiaChi = new JTextField();
	        txtDiaChi.setEditable(false);

	        txtMaNCC.setPreferredSize(new Dimension(300, 25));
	        txtTenNCC.setPreferredSize(new Dimension(300, 25));
	        txtSDT.setPreferredSize(new Dimension(300, 25));
	        txtDiaChi.setPreferredSize(new Dimension(300, 25));

	        gbclbl.ipadx = 9;
	        gbclbl.ipady = 5;
	        pnlInfo.add(lblMaNCC, gbclbl);
	        gbctxt.ipadx = 450;
	        gbctxt.ipady = 10;
	        gbctxt.gridx = 1;
	        pnlInfo.add(txtMaNCC, gbctxt);
	        gbclbl.ipadx = 5;
	        gbclbl.gridx = 0;
	        gbclbl.gridy = 1;
	        pnlInfo.add(lblTenNCC, gbclbl);
	        gbctxt.gridx = 1;
	        pnlInfo.add(txtTenNCC, gbctxt);
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

	       
	        pnlNhaCungCap.add(pnlInfo);

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
	                ThemNhaCungCapGUI GUI = new ThemNhaCungCapGUI();
	                GUI.addWindowListener(new WindowAdapter() {
	                    @Override
	                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
	                        clearInfo();
	                        BUS.readDB();
	                        BUS.setDataToTable(BUS.getDsNHACUNGCAP(), pnlNhaCungCapTable);
	                    }
	                });
	            }
	        });
	        btnLamMoi.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                clearInfo();
	                BUS.readDB();
	                BUS.setDataToTable(BUS.getDsNHACUNGCAP(), pnlNhaCungCapTable);
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
	                xuatExcel.xuatFileExcelNhaCungCap();
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
	        String[] headers = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ"};
	        pnlNhaCungCapTable.setHeaders(headers);
	        BUS.setDataToTable(BUS.getDsNHACUNGCAP(), pnlNhaCungCapTable);
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
	        this.add(pnlNhaCungCap);
	        this.add(Box.createRigidArea(new Dimension(0, 3)));
	        this.add(pnlTimKiem);
	        this.add(Box.createRigidArea(new Dimension(0, 3)));
	        this.add(pnlbtn);
	        this.add(Box.createRigidArea(new Dimension(0, 3)));
	        this.add(pnlNhaCungCapTable);

	    }

	    public void showInfo(int selectedIndex) {
	        if (selectedIndex != -1) {
	            String MaNCC = pnlNhaCungCapTable.getValueAt(selectedIndex, 0);
	            String tenMA = pnlNhaCungCapTable.getValueAt(selectedIndex, 1);
	            String soLuong = pnlNhaCungCapTable.getValueAt(selectedIndex, 2);
	            String donViTinh = pnlNhaCungCapTable.getValueAt(selectedIndex, 3);
	            setTextForTxt(MaNCC, tenMA, soLuong, donViTinh);
	        }
	    }

	    public void clearInfo() {
	        txtMaNCC.setText("");
	        txtTenNCC.setText("");
	        txtSDT.setText("");
	        txtDiaChi.setText("");
	    }

	    public void setTextForTxt(String MaNCC, String tenMA, String soLuong, String donViTinh) {
	        txtMaNCC.setText(MaNCC);
	        txtTenNCC.setText(tenMA);
	        txtSDT.setText(soLuong);
	        txtDiaChi.setText(donViTinh);
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
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp cần xóa");
	        } else {
	            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhà cung cấp", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
	                BUS.xoa(pnlNhaCungCapTable.getValueAt(index, 0));
	                JOptionPane.showMessageDialog(this, "Xóa thành công");
	                BUS.setDataToTable(BUS.getDsNHACUNGCAP(), pnlNhaCungCapTable);
	                clearInfo();
	            }
	        }
	    }

	    public void listenerSua() {
	        int index = pnlNhaCungCapTable.getTable().getSelectedRow();
	        if (index == -1) {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp cần sửa");
	        } else {
	            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa nhà cung cấp", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
	                SuaNhaCungCapGUI GUI = new SuaNhaCungCapGUI(txtMaNCC.getText(),txtTenNCC.getText(),txtSDT.getText(),txtDiaChi.getText());
	                GUI.addWindowListener(new WindowAdapter() {
	                    @Override
	                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
	                        clearInfo();
	                        BUS.readDB();
	                        BUS.setDataToTable(BUS.getDsNHACUNGCAP(), pnlNhaCungCapTable);
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
	        BUS.setDataToTable(BUS.searchNHACUNGCAP(txtTimKiem.getText()), pnlNhaCungCapTable);

	    }

}
