package GUI;

import GiaoDienChuan.ExportExcelButton;


import GiaoDienChuan.MyTable;
import GiaoDienChuan.RefreshButton;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;
import WorkExcel.XuatExcel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import BUS.PhieuNhapBUS;
import BUS.NhanVienBUS;
import BUS.NhaCungCapBUS;
import DTO.CHITIETPHIEUNHAP;
import DTO.NGUYENLIEU;
import DTO.PHIEUNHAP;
import DTO.NHANVIEN;
import DTO.NHACUNGCAP;


public class PhieuNhapGUI extends JPanel{
        //public JFrame frame;
        public JPanel panel1;
        public JPanel panel2;
        public JPanel tablePnl;
        public JPanel optionPnl;
        public JPanel searchPnl;
        public ExportExcelButton xuatbtn;
        public JButton timkiembtn; 
        public JButton chitietbtn;
        public RefreshButton refreshbtn;
        public MyTable table;        
        public JScrollPane scrollPane;
        public JTextField txtsearch;
        private TableRowSorter<DefaultTableModel> sorter;    
        private DefaultTableModel tableModel;
        
	public PhieuNhapGUI() {
		init();
	}
	
	public void init() {
            this.add(new JLabel("Phiếu nhập GUI"));  
            this.setLayout(new BorderLayout());     
            //frame = new JFrame();   
            refreshbtn =new RefreshButton();         
            xuatbtn = new ExportExcelButton();
            chitietbtn = new JButton("Chi tiết");
            timkiembtn = new JButton("Tìm kiếm");
            txtsearch = new JTextField(30);
            table = new MyTable();       
            String[] columnNames = {"Mã phiếu nhập", "Ngày lập", "Tổng tiền", "Tên nhân viên", "T nhà cung cấp"};   
            table.setHeaders(columnNames);    
            listNL(); 
            sorter = new TableRowSorter<>(table.getModel());                                         
                     
            // Tạo một JScrollPane mới và thêm JTable vào đó
            table.getTable().setRowSorter(sorter);            
            scrollPane = new JScrollPane(table);
    
            // Tạo một JPanel mới 
            panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panel2 = new JPanel(new GridLayout(1, 0));      
            tablePnl = new JPanel(new BorderLayout());
            tablePnl.setPreferredSize(new Dimension(0,200)) ;
            optionPnl = new JPanel(new BorderLayout());
            searchPnl=new JPanel(new FlowLayout(FlowLayout.CENTER));
            
            // Thêm hai JButton vào JPanel                     
            panel1.add(panel2);
            searchPnl.add(txtsearch); 
            searchPnl.add(timkiembtn);
            panel2.add(chitietbtn);                     
            panel2.add(xuatbtn);
            panel2.add(refreshbtn);
            tablePnl.add(scrollPane);
            optionPnl.add(searchPnl,BorderLayout.NORTH);
            optionPnl.add(panel1,BorderLayout.CENTER);
            /* 
            //Thêm tiêu đề
            frame.setTitle("Phiếu nhập GUI");
            // Thêm scrollpane A.K.A jtable vào JFrame
            frame.getContentPane().add(scrollPane,BorderLayout.NORTH);
            // Thêm JLabel vào JFrame
            frame.getContentPane().add(panel1,BorderLayout.SOUTH);              
            // Đặt JFrame ở chế độ toàn màn hình
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // Đặt hành động mặc định khi đóng JFrame
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // Hiển thị JFrame
            frame.setVisible(true);
            */
            this.add(tablePnl,BorderLayout.NORTH);
            this.add(optionPnl,BorderLayout.CENTER);
            //xử lý các jbutton
            timkiembtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    timkiem();             
                }            
            });
            
            chitietbtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0){
                    chitiet();
                }
            });
            
            xuatbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   XuatExcel xuatExcel = new XuatExcel();
                   xuatExcel.xuatFileExcelPhieuNhap();
                }
            });

            refreshbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sorter.setRowFilter(null);
                    txtsearch.setText("");
                    table.getModel().setRowCount(0);
                    listNL();
                }
            });

	}                             
                
        public void timkiem(){
            String text = txtsearch.getText();//lấy nội dung
                if (text.length() == 0) {
                    sorter.setRowFilter(null);// không có bộ lọc được áp dụng
                } else {
                	sorter.setRowFilter(RowFilter.regexFilter(text));// lọc dựa trên nội dung đã nhập
                }           
        }
        
        public void chitiet(){//không hiểu phần chi tiết này lắm nên làm tượng trưng thôi nha (:
            // Tạo thông tin hóa đơn
            // Lấy chỉ số của hàng được chọn
            int selectedRow =table.getTable().getSelectedRow();

            // Kiểm tra xem có hàng nào được chọn không
            if (selectedRow >= 0) {                
                String maPhieuNhap = (String)table.getModel().getValueAt(selectedRow, 0); // Lấy mã phiếu nhập được chọn
                ArrayList<CHITIETPHIEUNHAP> listCTPN = new ArrayList<CHITIETPHIEUNHAP>();
                PhieuNhapBUS bus = new PhieuNhapBUS();
                listCTPN = bus.selectAllPNbyID(maPhieuNhap);
                
                 // Tạo dữ liệu cho bảng
                String[][] data = {
                        {"John", "18"},
                        {"Anna", "20"},
                        {"Tom", "22"}
                    };

                // Tạo tiêu đề cho các cột
                String[] columnNames = {"Tên nguyên liệu", "Mã nguyên liệu", "Số lượng", "Đơn vị tính", "Đơn giá", "Loại"};

                // Tạo bảng
                tableModel = new DefaultTableModel(columnNames, 0);
                JTable tablemini = new JTable(tableModel);

                // Tạo JScrollPane chứa bảng
                JScrollPane scrollPane = new JScrollPane(tablemini);
                tablemini.setFillsViewportHeight(true);
                
                
                ArrayList<NGUYENLIEU> NLlist = new ArrayList<NGUYENLIEU>();
                NLlist = bus.selectAllNL();
                
                for(CHITIETPHIEUNHAP ctpn : listCTPN) {
                	for(NGUYENLIEU nl : NLlist) {
                		if(ctpn.getMaNL().equalsIgnoreCase(nl.getMaNL())) {
                			tableModel.addRow(new Object[] {nl.getTenNL(), ctpn.getMaNL(), ctpn.getSoLuong(), nl.getDonViTinh(), nl.getDonGia(), nl.getLoaiNL()});
                		}
                	}
                }

                // Hiển thị thông tin hóa đơn bằng JOptionPane
                JOptionPane.showMessageDialog(null,scrollPane, "Mã Phiếu Nhập: " + maPhieuNhap,JOptionPane.PLAIN_MESSAGE);
            } else {
                // Hiển thị thông báo nếu không có hàng nào được chọn
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xem chi tiết");
            }               
        }             
        
        
        public void listNL(){          
            //thêm dòng sau khởi tạo
            ArrayList<PHIEUNHAP> pnlist = new ArrayList<PHIEUNHAP>();
            PhieuNhapBUS pnbus = new PhieuNhapBUS();
            pnlist = pnbus.selectAllPN();
            
            NhanVienBUS NVbus = new NhanVienBUS();
            ArrayList<NHANVIEN> NVlist = NVbus.getDsNHANVIEN();
            NhaCungCapBUS NCCbus = new NhaCungCapBUS();
            ArrayList<NHACUNGCAP> NCClist = NCCbus.getDsNHACUNGCAP();
            
            int i=0;
            for(PHIEUNHAP pn : pnlist) {
            	table.getModel().addRow(new Object[] {pn.getMaPN(), pn.getNgayNhap(), pn.getTongTien(), null, null});
            	for(NHANVIEN nv : NVlist) {
            		if(pn.getMaNV().equalsIgnoreCase(nv.getMaNV())) {
            			table.getTable().setValueAt(nv.getTenNV(), i, 3);
            		}
            	}
            	for(NHACUNGCAP ncc : NCClist) {
            		if(pn.getMaNCC().equalsIgnoreCase(ncc.getMaNCC())) {
            			table.getTable().setValueAt(ncc.getTenNCC(), i, 4);
            		}
            	}
            	i++;
            }
            
            
        }
}
