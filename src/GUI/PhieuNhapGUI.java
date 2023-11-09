package GUI;

import GiaoDienChuan.ExportExcelButton;

import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
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


public class PhieuNhapGUI extends JPanel{
        //public JFrame frame;
        public JPanel panel1;
        public JPanel panel2;
        public ExportExcelButton xuatbtn;
        public JButton timkiembtn; 
        public JButton chitietbtn;
        public JTable table;        
        public JScrollPane scrollPane;
        public JTextField txtsearch;
        private TableRowSorter<DefaultTableModel> sorter;               
        
	public PhieuNhapGUI() {
		init();
	}
	
	public void init() {
            this.add(new JLabel("Phiếu nhập GUI"));
            DefaultTableModel model =listNL();
            //frame = new JFrame();            
            xuatbtn = new ExportExcelButton();
            chitietbtn = new JButton("Chi tiết");
            timkiembtn = new JButton("Tìm kiếm");
            table = new JTable(model);              
            sorter = new TableRowSorter<>(model);
            txtsearch = new JTextField();                                            
                     
            // Tạo một JScrollPane mới và thêm JTable vào đó
            table.setRowSorter(sorter);            
            scrollPane = new JScrollPane(table);
    
            // Tạo một JPanel mới với FlowLayout và gridlayout
            panel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panel2 = new JPanel(new GridLayout(1, 0));        
            
            // Thêm hai JButton vào JPanel                     
            panel1.add(panel2);
            panel2.add(txtsearch); 
            panel2.add(timkiembtn);
            panel2.add(chitietbtn);                     
            panel2.add(xuatbtn);
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
            this.add(scrollPane);
            this.add(panel1);
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
                    xuat(model);
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
            int selectedRow =table.getSelectedRow();

            // Kiểm tra xem có hàng nào được chọn không
            if (selectedRow >= 0) {                
                String maPhieuNhap = (String)table.getModel().getValueAt(selectedRow, 0); // Lấy mã phiếu nhập được chọn
                String maNguyenLieu = "NL01"; // Thay "NL01" bằng mã nguyên liệu thực tế
                String soLuong = "10"; // Thay "10" bằng số lượng thực tế

                // Hiển thị thông tin hóa đơn bằng JOptionPane
                JOptionPane.showMessageDialog(null, "Mã Phiếu Nhập: " + maPhieuNhap + "\nMã Nguyên Liệu: " + maNguyenLieu + "\nSố Lượng: " + soLuong);
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
        
        public DefaultTableModel listNL(){           
            // Tạo một DefaultTableModel mới với 8 cột
            String[] columnNames = {"Mã phiếu nhập", "Ngày lập", "Tổng tiền", "Mã nhân viên", "Mã nhà cung cấp"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);                                        
           
            //thêm dòng sau khởi tạo
            model.addRow(new Object[]{"PN01", "24/11/2003","10000","NV01","NCC007"});
            model.addRow(new Object[]{"PN02", "24/11/2003","12000","NV06","NCC008"});
            model.addRow(new Object[]{"PN03", "24/11/2003","10000","NV08","NCC003"});
            
            return model;
        }
}
