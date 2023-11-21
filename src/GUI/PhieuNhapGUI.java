package GUI;

import GiaoDienChuan.ExportExcelButton;

import GiaoDienChuan.MyTable;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import BUS.PhieuNhapBUS;
import DTO.CHITIETPHIEUNHAP;
import DTO.PHIEUNHAP;


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
            xuatbtn = new ExportExcelButton();
            chitietbtn = new JButton("Chi tiết");
            timkiembtn = new JButton("Tìm kiếm");
            txtsearch = new JTextField(30);
            table = new MyTable();       
            String[] columnNames = {"Mã phiếu nhập", "Ngày lập", "Tổng tiền", "Mã nhân viên", "Mã nhà cung cấp"};   
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
                    xuat(table.getModel());
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
                String[] columnNames = {"Mã nguyên liệu", "Số lượng"};

                // Tạo bảng
                tableModel = new DefaultTableModel(columnNames, 0);
                JTable tablemini = new JTable(tableModel);

                // Tạo JScrollPane chứa bảng
                JScrollPane scrollPane = new JScrollPane(tablemini);
                tablemini.setFillsViewportHeight(true);
                
                for(CHITIETPHIEUNHAP ctpn : listCTPN) {
                	tableModel.addRow(new Object[] {ctpn.getMaNL(), ctpn.getSoLuong()});
                }

                // Hiển thị thông tin hóa đơn bằng JOptionPane
                JOptionPane.showMessageDialog(null,scrollPane, "Mã Phiếu Nhập: " + maPhieuNhap,JOptionPane.PLAIN_MESSAGE);
            } else {
                // Hiển thị thông báo nếu không có hàng nào được chọn
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xem chi tiết");
            }               
        }             
        
        public void xuat(DefaultTableModel model){
            //đường dẫn lưu file trong ngoặc nha
            try (FileWriter writer = new FileWriter("NguyenLieu.csv")) {                        
                // Xuất tiêu đề cột
                for (int i = 0; i < model.getColumnCount(); i++) {
                    writer.write(model.getColumnName(i) + ",");
                }
                writer.write("\n");

                // Xuất dữ liệu từng hàng
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        writer.write(model.getValueAt(i, j).toString() + ",");
                    }
                    writer.write("\n");
                }

                JOptionPane.showMessageDialog(null, "Dữ liệu đã được xuất thành công!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        public void listNL(){          
            //thêm dòng sau khởi tạo
            ArrayList<PHIEUNHAP> pnlist = new ArrayList<PHIEUNHAP>();
            PhieuNhapBUS pnbus = new PhieuNhapBUS();
            pnlist = pnbus.selectAllPN();
            for(PHIEUNHAP pn : pnlist) {
            	table.getModel().addRow(new Object[] {pn.getMaPN(), pn.getNgayNhap(), pn.getTongTien(), pn.getMaNV(), pn.getMaNCC()});
            }
        }
}
