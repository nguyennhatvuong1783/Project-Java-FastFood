package GUI;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import GiaoDienChuan.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import DTO.NGUYENLIEU;
import BUS.NguyenLieuBUS;

public class NguyenLieuGUI extends JPanel{
	
        //public JFrame frame;
	
        public JPanel panel1;
        public JPanel panel2;
        public JPanel tablepnl;
        public JPanel optionpnl;
        public JPanel searchpnl;
        
        public ThemButton addbtn;
        
        public XoaButton delbtn;
        public SuaButton modbtn;
        public ExportExcelButton xuatbtn;
        public JButton timkiembtn;      
        public MyTable tableNL;        
        public JScrollPane scrollPane;
        public JTextField txtsearch;
        private TableRowSorter<DefaultTableModel> sorter;
        
        //dữ liệu cho tùy chỉnh 
        public String dataMa;
        public String dataTen;
        public String dataSoluong;
        public String dataDonvi;
        public String dataDongia;
        public String dataHinhanh;
        public String dataLoai;
        public String dataTrangthai;
            
	public NguyenLieuGUI() {
		init();
	}
	
	public void init() {
            this.setLayout(new BorderLayout(0, 5));
            //frame = new JFrame();
            addbtn = new ThemButton();
            
            
            delbtn = new XoaButton();
            modbtn = new SuaButton();
            xuatbtn = new ExportExcelButton();
            timkiembtn = new JButton("Tìm kiếm");
            
            tableNL = new MyTable();
            String[] columnNames = {"Mã Nguyên Liệu", "Tên Nguyên Liệu", "Số Lượng", "Đơn Vị Tính", "Đơn Giá", "Hình Ảnh", "Loại", "Trạng Thái"};
            tableNL.setHeaders(columnNames);
            
            //thêm dòng sau khởi tạo
            ArrayList<NGUYENLIEU> listnl = new ArrayList<NGUYENLIEU>();
            NguyenLieuBUS bus = new NguyenLieuBUS();
            listnl = bus.selectAllNL();
            for(NGUYENLIEU nl : listnl) {
            	if(nl.getTrangThai() == 1) {
	            	tableNL.getModel().addRow(new Object[] {nl.getMaNL(), nl.getTenNL(), nl.getSoLuong(), nl.getDonViTinh(), 
	            			nl.getDonGia(), nl.getHinhAnh(), nl.getLoaiNL(), nl.getTrangThai()});
            	}
            }
            
            sorter = new TableRowSorter<>(tableNL.getModel());
            txtsearch = new JTextField(30);                                            
                     
            // Tạo một JScrollPane mới và thêm JTable vào đó
            tableNL.getTable().setRowSorter(sorter);            
            scrollPane = new JScrollPane(tableNL);
    
            // Tạo một JPanel mới với FlowLayout và gridlayout
            panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panel2 = new JPanel(new GridLayout(1, 0));  
            tablepnl = new JPanel(new BorderLayout());
            tablepnl.setPreferredSize(new Dimension(0,200)) ; 
            optionpnl = new JPanel(new BorderLayout());    
            searchpnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
            
            // Thêm hai JButton vào JPanel                     
            searchpnl.add(txtsearch); 
            searchpnl.add(timkiembtn);
            optionpnl.add(searchpnl,BorderLayout.NORTH);
            optionpnl.add(panel1,BorderLayout.CENTER);
            panel1.add(panel2);
            
            panel2.add(addbtn);
            
            panel2.add(modbtn);
            panel2.add(delbtn);           
            panel2.add(xuatbtn);
            
            tablepnl.add(scrollPane);
            
            /* 
            //Thêm tiêu đề
            frame.setTitle("Nguyên Liệu GUI");
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
            
            this.add(tablepnl,BorderLayout.NORTH);
            this.add(optionpnl,BorderLayout.CENTER);
            //xử lý các jbutton
            timkiembtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    timkiem();             
                }            
            });
            
            addbtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0){
                    them(tableNL.getModel());
                }
            });
            
            modbtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0){
                    // Lấy chỉ số của hàng được chọn
                    int selectedRow = tableNL.getTable().getSelectedRow();
                    
                    // Kiểm tra xem có hàng nào được chọn không
                    if (selectedRow >= 0) {
                       // Lấy dữ liệu từ hàng được chọn
                        dataMa = tableNL.getValueAt(selectedRow, 0).toString();
                        dataTen = tableNL.getValueAt(selectedRow, 1).toString();
                        dataSoluong = tableNL.getValueAt(selectedRow, 2).toString();
                        dataDonvi = tableNL.getValueAt(selectedRow, 3).toString();
                        dataDongia = tableNL.getValueAt(selectedRow, 4).toString();
                        dataHinhanh = tableNL.getValueAt(selectedRow, 5).toString();
                        dataLoai = tableNL.getValueAt(selectedRow, 6).toString();
                        dataTrangthai = tableNL.getValueAt(selectedRow, 7).toString();                              
                        sua(tableNL.getModel(),dataMa,dataTen,dataSoluong,dataDonvi,dataDongia,dataHinhanh,dataLoai,dataTrangthai);                        
                    } 
                    else {
                        // Hiển thị thông báo nếu không có hàng nào được chọn
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa");
                    }                    
                }
            });       
            
            delbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    xoa(tableNL.getModel());
                }
            });
            
            xuatbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    xuat(tableNL.getModel());
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
        
        public int LaySLNL() {
        	NguyenLieuBUS bus = new NguyenLieuBUS();
        	ArrayList<NGUYENLIEU> listNL = new ArrayList<NGUYENLIEU>();
        	listNL = bus.selectAllNL();
        	int i = listNL.size();
        	return i;
        }
        
        public void them(DefaultTableModel model){
        	// Tạo một JTextField cho mỗi cột trong JTable
            JTextField txtTen = new JTextField();
            JTextField txtSol = new JTextField();
            JTextField txtDonvi = new JTextField();
            JTextField txtDongia = new JTextField();
            JTextField txtHinhanh = new JTextField();
            JTextField txtLoai = new JTextField();
            JTextField txtTrangThai = new JTextField();                       

            // Tạo một JOptionPane để nhận dữ liệu từ người dùng
            Object[] message = {
            "Nhập tên nguyên liệu:", txtTen,
            "Nhập số lượng:", txtSol,
            "Nhập đơn vị:", txtDonvi,
            "Nhập đơn giá:", txtDongia,
            //"Nhập hình ảnh:", txtHinhanh,
            "Nhập loại:", txtLoai,
            "Nhập trạng thái:", txtTrangThai,
            };
            
            
            int option = JOptionPane.showConfirmDialog(null, message, "Nhập dữ liệu", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                // Thêm dòng mới vào model với dữ liệu từ JTextField
                model.addRow(new Object[]{txtTen.getText(),txtSol.getText(),txtDonvi.getText(),txtDongia.getText(),txtHinhanh.getText(),txtLoai.getText(),txtTrangThai.getText()});
            }

            //Đưa nguyên liệu vào đối tượng và gọi hàm thêm nguyên liệu ở BUS để thêm DL vào database
            NGUYENLIEU nl = new NGUYENLIEU();
            int i = LaySLNL()+1;
            String manl = "NL"+i;
            nl.setMaNL(manl);
            nl.setTenNL(txtTen.getText());
            nl.setSoLuong(Integer.parseInt(txtSol.getText()));
            nl.setDonViTinh(txtDonvi.getText());
            nl.setDonGia(Integer.parseInt(txtDongia.getText()));
            nl.setHinhAnh(txtHinhanh.getText());
            nl.setLoaiNL(txtLoai.getText());
            nl.setTrangThai(Integer.parseInt(txtTrangThai.getText()));
            NguyenLieuBUS nlBUS = new NguyenLieuBUS();
            nlBUS.insertNL(nl);
            
       
        }
        
        public void sua(DefaultTableModel model,String Ma,String Ten,String Soluong,String Donvi,String Dongia,String Hinhanh,String Loai,String Trangthai){
            // Tạo một JTextField cho mỗi cột trong JTable
            JTextField txtMa = new JTextField(Ma);
            JTextField txtTen = new JTextField(Ten);
            JTextField txtSol = new JTextField(Soluong);
            JTextField txtDonvi = new JTextField(Donvi);
            JTextField txtDongia = new JTextField(Dongia);
            JTextField txtHinhanh = new JTextField(Hinhanh);
            JTextField txtLoai = new JTextField(Loai);
            JTextField txtTrangThai = new JTextField(Trangthai);                       

            // Tạo một JOptionPane để nhận dữ liệu từ người dùng
            Object[] message = {
            "Nhập mã nguyên liệu:", txtMa,
            "Nhập tên nguyên liệu:", txtTen,
            "Nhập số lượng:", txtSol,
            "Nhập đơn vị:", txtDonvi,
            "Nhập đơn giá:", txtDongia,
            //"Nhập hình ảnh:", txtHinhanh,
            "Nhập loại:", txtLoai,
            "Nhập trạng thái:", txtTrangThai,
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Nhập dữ liệu", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
            	// Thêm dòng mới vào model với dữ liệu từ JTextField
                model.addRow(new Object[]{txtMa.getText(), txtTen.getText(),txtSol.getText(),txtDonvi.getText(),txtDongia.getText(),txtHinhanh.getText(),txtLoai.getText(),txtTrangThai.getText()});
            }
            
          //Đưa nguyên liệu vào đối tượng và gọi hàm thêm nguyên liệu ở BUS để thêm DL vào database
            NGUYENLIEU nl = new NGUYENLIEU();
            nl.setMaNL(txtMa.getText());
            nl.setTenNL(txtTen.getText());
            nl.setSoLuong(Integer.parseInt(txtSol.getText()));
            nl.setDonViTinh(txtDonvi.getText());
            nl.setDonGia(Integer.parseInt(txtDongia.getText()));
            nl.setHinhAnh(txtHinhanh.getText());
            nl.setLoaiNL(txtLoai.getText());
            nl.setTrangThai(Integer.parseInt(txtTrangThai.getText()));
            NguyenLieuBUS nlBUS = new NguyenLieuBUS();
            nlBUS.insertNL(nl);    
        }
         
        public void xoa(DefaultTableModel model){
            // Lấy chỉ số của hàng được chọn
            int selectedRow = tableNL.getTable().getSelectedRow();

            // Kiểm tra xem có hàng nào được chọn không
            if (selectedRow >= 0) {
                
                //Xóa dữ liệu trong database
                //Lấy dữ liệu từ hàng được chọn và lưu vào đối tượng
                NGUYENLIEU nl = new NGUYENLIEU();
                nl.setMaNL(tableNL.getValueAt(selectedRow, 0).toString());
                nl.setTenNL(tableNL.getValueAt(selectedRow, 1).toString());
                nl.setSoLuong(Integer.parseInt(tableNL.getValueAt(selectedRow, 2).toString()));
                nl.setDonViTinh(tableNL.getValueAt(selectedRow, 3).toString());
                nl.setDonGia(Integer.parseInt(tableNL.getValueAt(selectedRow, 4).toString()));
                nl.setHinhAnh(tableNL.getValueAt(selectedRow, 5).toString());
                nl.setLoaiNL(tableNL.getValueAt(selectedRow, 6).toString());
                nl.setTrangThai(0); 
                NguyenLieuBUS nlBUS = new NguyenLieuBUS();
                nlBUS.setInactiveNL(nl);
                
             // Xóa hàng được chọn từ model
                model.removeRow(selectedRow);
                
            } else {
                // Hiển thị thông báo nếu không có hàng nào được chọn
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa");
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
}
