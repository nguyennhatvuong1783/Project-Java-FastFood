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

import BUS.KhachHangBUS;
import BUS.NhaCungCapBUS;
import GiaoDienChuan.ExportExcelButton;
import GiaoDienChuan.ImportExcelButton;
import GiaoDienChuan.MyTable;
import GiaoDienChuan.RefreshButton;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;
import WorkExcel.XuatExcel;


public class KhachHangGUI extends JPanel {
	  	public static MyTable pnlNhaCungCapTable;
	    public JTextField txtMaKH;
	    public JTextField txtTenKH;
	    public JTextField txtSDT;
	    public JTextField txtDiaChi;
	    public JButton btnThem;
	    public JButton btnSua;
	    public JButton btnXoa;
	    public JButton btnNhap;
	    public JButton btnXuat;
	    public JButton btnLamMoi;
	    public JTextField txtTimKiem;

	    KhachHangBUS BUS = new KhachHangBUS();

	    public KhachHangGUI() {
	        init();
	    }

	    public void init() {
	        //this
	        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	        JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG", JLabel.CENTER);
	        lblTitle.setBorder(new EmptyBorder(5, 0, 0, 0));
	        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));

	        //pnlMonAn
	        JLabel lblMaKH = new JLabel("     Mã khách hàng");
	        JLabel lblTenKH = new JLabel("     Tên khách hàng");
	        JLabel lblSDT = new JLabel("     Số điện thoại");
	        JLabel lblDiaChi = new JLabel("     Địa chỉ");

	        lblMaKH.setFont(new Font("Times New Roman", Font.BOLD, 16));
	        lblTenKH.setFont(new Font("Times New Roman", Font.BOLD, 16));
	        lblSDT.setFont(new Font("Times New Roman", Font.BOLD, 16));
	        lblDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 16));

	        JPanel pnlMonAn = new JPanel();
	        BoxLayout boxLayout = new BoxLayout(pnlMonAn, BoxLayout.X_AXIS);
	        pnlMonAn.setLayout(boxLayout);

	        //pnlInfo
	        JPanel pnlInfo = new JPanel(new GridBagLayout());
	        GridBagConstraints gbclbl = new GridBagConstraints();
	        GridBagConstraints gbctxt = new GridBagConstraints();

	        txtMaKH = new JTextField();
	        txtMaKH.setEditable(false);
	        txtTenKH = new JTextField();
	        txtTenKH.setEditable(false);
	        txtSDT = new JTextField();
	        txtSDT.setEditable(false);
	        txtDiaChi = new JTextField();
	        txtDiaChi.setEditable(false);

	        txtMaKH.setPreferredSize(new Dimension(300, 25));
	        txtTenKH.setPreferredSize(new Dimension(300, 25));
	        txtSDT.setPreferredSize(new Dimension(300, 25));
	        txtDiaChi.setPreferredSize(new Dimension(300, 25));

	        gbclbl.ipadx = 9;
	        gbclbl.ipady = 5;
	        pnlInfo.add(lblMaKH, gbclbl);
	        gbctxt.ipadx = 450;
	        gbctxt.ipady = 10;
	        gbctxt.gridx = 1;
	        pnlInfo.add(txtMaKH, gbctxt);
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

	       
	        pnlMonAn.add(pnlInfo);

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
	                ThemKhachHangGUI GUI = new ThemKhachHangGUI();
	                GUI.addWindowListener(new WindowAdapter() {
	                    @Override
	                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
	                        clearInfo();
	                        BUS.readDB();
	                        BUS.setDataToTable(BUS.getdsKHACHHANG(), pnlNhaCungCapTable);
	                    }
	                });
	            }
	        });
	        btnLamMoi.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                clearInfo();
	                BUS.readDB();
	                BUS.setDataToTable(BUS.getdsKHACHHANG(), pnlNhaCungCapTable);
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
	                xuatExcel.xuatFileExcelKhachHang();
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
	        String[] headers = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ"};
	        pnlNhaCungCapTable.setHeaders(headers);
	        BUS.setDataToTable(BUS.getdsKHACHHANG(), pnlNhaCungCapTable);
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
	        this.add(pnlMonAn);
	        this.add(Box.createRigidArea(new Dimension(0, 3)));
	        this.add(pnlTimKiem);
	        this.add(Box.createRigidArea(new Dimension(0, 3)));
	        this.add(pnlbtn);
	        this.add(Box.createRigidArea(new Dimension(0, 3)));
	        this.add(pnlNhaCungCapTable);

	    }

	    public void showInfo(int selectedIndex) {
	        if (selectedIndex != -1) {
	            String MaKH = pnlNhaCungCapTable.getValueAt(selectedIndex, 0);
	            String tenMA = pnlNhaCungCapTable.getValueAt(selectedIndex, 1);
	            String soLuong = pnlNhaCungCapTable.getValueAt(selectedIndex, 2);
	            String donViTinh = pnlNhaCungCapTable.getValueAt(selectedIndex, 3);
	            setTextForTxt(MaKH, tenMA, soLuong, donViTinh);
	        }
	    }

	    public void clearInfo() {
	        txtMaKH.setText("");
	        txtTenKH.setText("");
	        txtSDT.setText("");
	        txtDiaChi.setText("");
	    }

	    public void setTextForTxt(String MaKH, String tenMA, String soLuong, String donViTinh) {
	        txtMaKH.setText(MaKH);
	        txtTenKH.setText(tenMA);
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
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xóa");
	        } else {
	            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa khách hàng", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
	                BUS.xoa(pnlNhaCungCapTable.getValueAt(index, 0));
	                JOptionPane.showMessageDialog(this, "Xóa thành công");
	                BUS.setDataToTable(BUS.getdsKHACHHANG(), pnlNhaCungCapTable);
	                clearInfo();
	            }
	        }
	    }

	    public void listenerSua() {
	        int index = pnlNhaCungCapTable.getTable().getSelectedRow();
	        if (index == -1) {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần sửa");
	        } else {
	            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa khách hàng", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
	                SuaKhachHangGUI GUI = new SuaKhachHangGUI(txtMaKH.getText(),txtTenKH.getText(),txtSDT.getText(),txtDiaChi.getText());
	                GUI.addWindowListener(new WindowAdapter() {
	                    @Override
	                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
	                        clearInfo();
	                        BUS.readDB();
	                        BUS.setDataToTable(BUS.getdsKHACHHANG(), pnlNhaCungCapTable);
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
	        BUS.setDataToTable(BUS.searchKHACHHANG(txtTimKiem.getText()), pnlNhaCungCapTable);

	    }

}
