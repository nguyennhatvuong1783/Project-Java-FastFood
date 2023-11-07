package GUI;

import GiaoDienChuan.ExportExcelButton;
import GiaoDienChuan.MyTable;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import DTO.NGUYENLIEU;

public class NhapHangGUI extends JPanel{
        public JFrame frame;                
        public JButton themnccbtn; 
        public JButton themspbtn;   
        public JPanel panel;
        public JPanel panelNCCselector;
        public JPanel panelSPselector;
        public JPanel panelSPoption;
        public JPanel panelLastoption;
        public JComboBox cb;
        public JTable tableNCC;
        public JTable tableSP;
        public DefaultTableModel modelNCC;
        public DefaultTableModel modelSP;
        public JScrollPane scrollPaneNCC;
        public JScrollPane scrollPaneSP;
        public JTextField txtSL;
        public JLabel txtSLhint;
        public XoaButton delbtn;
        public SuaButton modbtn;
        public JTextField txtMANV;
        public JLabel txtMANVhint;
        public JLabel txtTongtien;
        public JLabel txtTongtienhint;
        public JButton thanhtoanbtn;
        
         //dữ liệu cho tùy chỉnh   
        public String dataSoluong;
        
	public NhapHangGUI() {
		init();
	}
	
	public void init() {
            this.add(new JLabel("Nhập hàng GUI"));                                      
            frame = new JFrame();           
            themnccbtn = new JButton("Chọn nhà cung cấp");
            themspbtn = new JButton("Thêm sản phẩm");  
            ComboBoxNCC(); 
            khoitao2bang();                                            
            panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            panelNCCselector = new JPanel(new FlowLayout());
            panelSPselector = new JPanel(new FlowLayout());
            panelSPoption = new JPanel(new FlowLayout());
            panelLastoption = new JPanel(new FlowLayout());
            txtSL=new JTextField(20);            
            txtSLhint=new JLabel("Nhập số lượng");
            scrollPaneNCC = new JScrollPane(tableNCC);
            scrollPaneSP = new JScrollPane(tableSP);
            delbtn = new XoaButton();
            modbtn = new SuaButton();
            txtMANV=new JTextField(20);
            txtMANVhint = new JLabel("Nhập mã nhân viên: ");
            txtTongtien=new JLabel();
            txtTongtienhint=new JLabel("Tổng số tiền: ");
            thanhtoanbtn = new JButton("Thanh toán");
            
            // Thêm vào JPanel         
            panelNCCselector.add(themnccbtn);                
            panelNCCselector.add(cb);
            panel.add(panelNCCselector);
            panel.add(scrollPaneNCC);  
            panel.add(themspbtn);           
            panelSPselector.add(txtSLhint);
            panelSPselector.add(txtSL);
            panel.add(panelSPselector);
            panel.add(scrollPaneSP);
            panelSPoption.add(delbtn);
            panelSPoption.add(modbtn);
            panel.add(panelSPoption);
            panelLastoption.add(txtMANVhint);
            panelLastoption.add(txtMANV);
            panelLastoption.add(txtTongtienhint);
            panelLastoption.add(txtTongtien);
            panel.add(panelLastoption);
            
            //Thêm tiêu đề
            frame.setTitle("Phiếu nhập GUI");  
            frame.getContentPane().add(panel);
            // Đặt JFrame ở chế độ toàn màn hình
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // Đặt hành động mặc định khi đóng JFrame
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // Hiển thị JFrame
            frame.setVisible(true);
            
            themspbtn.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		int selectedRow = tableNCC.getSelectedRow();
            		if (selectedRow >= 0) {
            			String maNL = tableNCC.getValueAt(selectedRow, 0).toString();
            			
            		}
            	}
            });
            
            cb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    hienthisanpham();
                }
            });
            
            delbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    xoa((DefaultTableModel)tableSP.getModel());
                }
            });   
            
            modbtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0){
                    // Lấy chỉ số của hàng được chọn
                    int selectedRow = tableSP.getSelectedRow();
                    
                    // Kiểm tra xem có hàng nào được chọn không
                    if (selectedRow >= 0) {
                       // Lấy dữ liệu từ hàng được chọn                        
                        dataSoluong = tableSP.getValueAt(selectedRow, 2).toString();  
                        sua(modelSP,dataSoluong);
                    } 
                    else {
                        // Hiển thị thông báo nếu không có hàng nào được chọn
                        JOptionPane.showMessageDialog(frame, "Vui lòng chọn một hàng để sửa");
                    }                    
                }
            });
            
            thanhtoanbtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0){
                    //không biết chức năng này sẽ ra cái gì nên chưa làm 
                }
            });
	}
        
        public void ComboBoxNCC() {        
            String[] NCC = {"Nhà cung cấp 1", "Nhà cung cấp 2", "Nhà cung cấp 3", "Nhà cung cấp 4", "Nhà cung cấp 5"};
            cb = new JComboBox(NCC);    
        }
        
        public void khoitao2bang(){
            String[][] data = {};
            String[] columnNamesNCC = {"Mã sản phẩm", "Tên sản phẩm","Số lượng tồn kho","Loại", "Đơn Giá"};
            modelNCC = new DefaultTableModel(data,columnNamesNCC);
            tableNCC = new JTable(modelNCC);
            
            String[] columnNamesSP = {"Mã sản phẩm", "Tên sản phẩm","Số lượng nhập","Loại", "Đơn Giá","Nhà cung cấp"};
            modelSP = new DefaultTableModel(data,columnNamesSP);
            tableSP = new JTable(modelSP);  
                     
            Object[] row = new Object[] {"Dữ liệu cột 1", "Dữ liệu cột 2", "Dữ liệu cột 3","Dữ liệu cột 4","Dữ liệu cột 5","Dữ liệu cột 6"};
            modelSP.addRow(row);
        }
        //reset lại bảng sau khi có dữ liệu tương ứng với NCC
        public void hienthisanpham(){
            String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm","Số lượng tồn kho","Loại", "Đơn Giá"};
            String nhaCungCapChon = (String) cb.getItemAt(cb.getSelectedIndex());
            String[][] sanPham = getSanPhamTheoNhaCungCap(nhaCungCapChon);
            tableNCC.setModel(new DefaultTableModel(sanPham, columnNames));
        }
        
        private String[][] getSanPhamTheoNhaCungCap(String nhaCungCap) {
            // Thực hiện truy vấn dữ liệu từ cơ sở dữ liệu hoặc nguồn dữ liệu khác tại đây
            // và trả về một mảng 2 chiều chứa thông tin sản phẩm
            return new String[][]{};
        }
        
        public void xoa(DefaultTableModel model){
            // Lấy chỉ số của hàng được chọn
                    int selectedRow = tableSP.getSelectedRow();

                    // Kiểm tra xem có hàng nào được chọn không
                    if (selectedRow >= 0) {
                        // Xóa hàng được chọn từ model
                        model.removeRow(selectedRow);
                    } else {
                        // Hiển thị thông báo nếu không có hàng nào được chọn
                        JOptionPane.showMessageDialog(frame, "Vui lòng chọn một hàng để xóa");
                    }
        }
        
        public void sua(DefaultTableModel model,String Soluong){
            // Tạo một JTextField cho mỗi cột trong JTable      
            JTextField txtSol = new JTextField(Soluong);                                  
            // Tạo một JOptionPane để nhận dữ liệu từ người dùng
            Object[] message = {            
            "Nhập số lượng:", txtSol,           
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Nhập dữ liệu", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                // Lấy chỉ số của hàng được chọn
                int selectedRow = tableSP.getSelectedRow();
                
                // Cập nhật dữ liệu của hàng được chọn                
                model.setValueAt(txtSol.getText(), selectedRow, 2);              
            }
        }
               
}
