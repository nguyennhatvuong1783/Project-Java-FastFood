package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import GiaoDienChuan.ExportExcelButton;
import GiaoDienChuan.FormatMoney;
import GiaoDienChuan.MyTable;
import GiaoDienChuan.RefreshButton;

public class HoaDonGUI extends JPanel{
    public JButton btnLamMoi;
    public JButton btnXuat;
    public JComboBox<String> cbTimKiem;
    public JDateChooser dcDenNgay;
    public JDateChooser dcTuNgay;
    public JPanel pnlControl;
    public JPanel pnlNgayLap;
    public MyTable pnlTable;
    public JPanel pnlTimKiem;
    public JPanel pnlTongTien;
    public JPanel pnlXuat;
    public JTextField txtTimKiem;
    public JTextField txtTongTienDen;
    public JTextField txtTongTienTu;

	
	public HoaDonGUI() {
		init();
	}
	
	public void init() {

        pnlTable = new MyTable();
        pnlControl = new JPanel();
        pnlTimKiem = new JPanel();
        cbTimKiem = new JComboBox<>();
        txtTimKiem = new JTextField();
        pnlNgayLap = new JPanel();
        dcTuNgay = new JDateChooser();
        dcDenNgay = new JDateChooser();
        pnlTongTien = new JPanel();
        txtTongTienTu = new JTextField();
        txtTongTienDen = new JTextField();
        btnLamMoi = new RefreshButton();
        pnlXuat = new JPanel();
        btnXuat = new ExportExcelButton();

        setLayout(new BorderLayout());

        String[] headers = {"Mã hóa đơn", "Tên khách hàng", "Nhân viên lập hóa đơn", "Ngày lập", "Tổng tiền"};
        pnlTable.setHeaders(headers);

        add(pnlTable, BorderLayout.CENTER);

        pnlTimKiem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "Tìm kiếm", 
        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 18))); // pnlTimKiem
        pnlTimKiem.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        
        txtTimKiem.setFont(new Font("Segoe UI", 0, 14)); // txtTimKiem
        txtTimKiem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Tên khách hàng", 
        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14)));
        txtTimKiem.setPreferredSize(new Dimension(140, 45));

        cbTimKiem.setFont(new Font("Segoe UI", 1, 14)); // cbTimKiem
        cbTimKiem.setModel(new DefaultComboBoxModel<>(new String[] { "Tên khách hàng", "Tên nhân viên" }));
        cbTimKiem.setPreferredSize(new Dimension(145, 30));
        cbTimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbTimKiem.getSelectedIndex() == 0) {
			        txtTimKiem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Tên khách hàng", 
			        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14)));
				}else {
			        txtTimKiem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Tên nhân viên", 
			        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14)));
				}
			}
		});
        pnlTimKiem.add(cbTimKiem);
        pnlTimKiem.add(txtTimKiem);

        pnlControl.add(pnlTimKiem);

        pnlNgayLap.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "Ngày lập", 
        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 18))); // pnlNgayLap

        dcTuNgay.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Từ ngày", 
        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14)));
        dcTuNgay.setDateFormatString("dd/MM/yyyy");
        dcTuNgay.setFont(new Font("Segoe UI", 0, 14)); // dcTuNgay
        dcTuNgay.setPreferredSize(new Dimension(120, 55));
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dcTuNgay.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(new Color(255,255,255));
        dcTuNgay.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
        	        @Override
        	        public void propertyChange(PropertyChangeEvent e) {
        	            if("date".equals(e.getPropertyName())) {
        	                System.out.println(dcTuNgay.getDate());
        	            }
        	        }
        });
        pnlNgayLap.add(dcTuNgay);

        dcDenNgay.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Đến ngày", 
        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14)));
        dcDenNgay.setDateFormatString("dd/MM/yyyy");
        dcDenNgay.setFont(new Font("Segoe UI", 0, 14)); // dcDenNgay
        dcDenNgay.setPreferredSize(new Dimension(120, 55));
        editor = (JTextFieldDateEditor) dcDenNgay.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(new Color(255,255,255));
        dcDenNgay.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
	        @Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if("date".equals(e.getPropertyName())) {
	                System.out.println(dcDenNgay.getDate());
	            }
	        }
	    });
        pnlNgayLap.add(dcDenNgay);

        pnlControl.add(pnlNgayLap);

        pnlTongTien.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "Tổng tiền", 
        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 18))); // pnlTongTien
        pnlTongTien.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        txtTongTienTu.setFont(new Font("Segoe UI", 0, 14)); // txtTongTienTu
        txtTongTienTu.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Từ", 
        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14)));
        txtTongTienTu.setPreferredSize(new Dimension(120, 45));
        txtTongTienTu.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		super.keyReleased(e);
        		String text = txtTongTienTu.getText();
        		text = text.replace(",", "");
        		if(isNumber(text)) {
            		long number = Long.parseLong(text);
            		String money = FormatMoney.getFormat(number);
            		money = money.replace(" VNĐ", "");
            		txtTongTienTu.setText(money);
        		}
        	}
		});
        pnlTongTien.add(txtTongTienTu);

        txtTongTienDen.setFont(new Font("Segoe UI", 0, 14)); // txtTongTienDen
        txtTongTienDen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Đến", 
        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14)));
        txtTongTienDen.setPreferredSize(new Dimension(120, 45));
        txtTongTienDen.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		super.keyReleased(e);
        		String text = txtTongTienDen.getText();
        		text = text.replace(",", "");
        		if(isNumber(text)) {
            		long number = Long.parseLong(text);
            		String money = FormatMoney.getFormat(number);
            		money = money.replace(" VNĐ", "");
            		txtTongTienDen.setText(money);
        		}
        	}
		});
        pnlTongTien.add(txtTongTienDen);

        pnlControl.add(pnlTongTien);

        pnlControl.add(btnLamMoi);

        add(pnlControl, BorderLayout.NORTH);

        pnlXuat.add(btnXuat);

        add(pnlXuat, BorderLayout.SOUTH);
	}
	
	public boolean isNumber(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
