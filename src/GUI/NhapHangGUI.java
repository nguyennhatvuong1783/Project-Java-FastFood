package GUI;

import GiaoDienChuan.ExportExcelButton;


import BUS.NhapHangBUS;
import GiaoDienChuan.MyTable;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import DTO.NHACUNGCAP;
import DTO.PHIEUNHAP;
import BUS.PhieuNhapBUS;
import BUS.ChiTietPhieuNhapBUS;
import BUS.NhaCungCapBUS;
import DTO.CHITIETPHIEUNHAP;

public class NhapHangGUI extends JPanel{
        //public JFrame frame;                
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
            //frame = new JFrame();           
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
            txtSL=new JTextField("0",20);            
            txtSLhint=new JLabel("Nhập số lượng");
            scrollPaneNCC = new JScrollPane(tableNCC);
            scrollPaneSP = new JScrollPane(tableSP);
            delbtn = new XoaButton();
            modbtn = new SuaButton();
            txtMANV=new JTextField(20);
            txtMANVhint = new JLabel("Nhập mã nhân viên: ");
            txtTongtien=new JLabel("0");
            txtTongtienhint=new JLabel("Tổng số tiền: ");
            thanhtoanbtn = new JButton("Thanh toán");
            
            // Thêm vào JPanel         
            panelNCCselector.add(themnccbtn);                
            panelNCCselector.add(cb);
            panel.add(panelNCCselector);
            panel.add(scrollPaneNCC);             
            panelSPselector.add(txtSLhint);
            panelSPselector.add(txtSL);
            panel.add(panelSPselector);
            panel.add(themspbtn);
            panel.add(scrollPaneSP);
            panelSPoption.add(delbtn);
            panelSPoption.add(modbtn);
            panel.add(panelSPoption);
            panelLastoption.add(txtMANVhint);
            panelLastoption.add(txtMANV);
            panelLastoption.add(txtTongtienhint);
            panelLastoption.add(txtTongtien);
            panelLastoption.add(thanhtoanbtn);
            panel.add(panelLastoption);
            /* 
            //Thêm tiêu đề             
            frame.getContentPane().add(panel);
            // Đặt JFrame ở chế độ toàn màn hình
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // Đặt hành động mặc định khi đóng JFrame
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // Hiển thị JFrame
            frame.setVisible(true);
            */
            this.add(panel);
            
            themspbtn.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		int selectedRow = tableNCC.getSelectedRow();
            		if (selectedRow >= 0) {
                    //thêm vào bảng mới
                    modelSP.addRow(new Object[] {tableNCC.getValueAt(selectedRow, 0), tableNCC.getValueAt(selectedRow, 1), txtSL.getText()
                    ,tableNCC.getValueAt(selectedRow, 3), tableNCC.getValueAt(selectedRow, 4),(String) cb.getSelectedItem()});
                    //cập nhật giá
                    txtTongtien.setText(calculateColumnTotal(tableSP));
                    txtSL.setText("0");
                    } else {
                        // Hiển thị thông báo nếu không có hàng nào được chọn
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để thêm");
                    }
            	}
            });
            
            /*đây là chức năng hiển thị sp theo ncc được chọn 
            cb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    hienthisanpham();
                }
            });*/
            
            delbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    xoa(modelSP);
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
                        //cập nhật giá
                        txtTongtien.setText(calculateColumnTotal(tableSP));
                    } 
                    else {
                        // Hiển thị thông báo nếu không có hàng nào được chọn
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa");
                    }                    
                }
            });
            
            thanhtoanbtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0){
                    if(txtMANV.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên");
                    }
                    else{
                        showtt_thanhtoan();
                    }
                }
            });
	}
        
        public void ComboBoxNCC() {        
        	NhaCungCapBUS nccbus = new NhaCungCapBUS();
        	ArrayList<NHACUNGCAP> ncclist = new ArrayList<NHACUNGCAP>();
        	int list_count = ncclist.size()-1;
        	ncclist = nccbus.selectAllNCC();
            String[] NCC = new String[ncclist.size()];
            for(int i=0; i < ncclist.size(); i++) {
            	NCC[i] = ncclist.get(i).getTenNCC();
            }
            cb = new JComboBox(NCC);    
        }
        
        public void khoitao2bang(){
            String[][] data = {};
            String[] columnNamesNCC = {"Mã sản phẩm", "Tên sản phẩm","Số lượng tồn kho","Loại", "Đơn Giá"};
            modelNCC = new DefaultTableModel(data,columnNamesNCC);
            tableNCC = new JTable(modelNCC);

            /*
            modelNCC.addRow(new Object[] {"Dữ liệu cột 1", "Dữ liệu cột 2", "Dữ liệu cột 3","Dữ liệu cột 4","10000"});
            modelNCC.addRow(new Object[] {"Dữ liệu cột 1", "Dữ liệu cột 2", "Dữ liệu cột 3","Dữ liệu cột 4","20000"});
            */
            
            //Đổ dữ liệu lên bảng NCC
            NhapHangBUS nhap = new NhapHangBUS();
            ArrayList<NGUYENLIEU> listnl = nhap.selectAll();
            for(NGUYENLIEU nl : listnl) {
            	modelNCC.addRow(new Object[] {nl.getMaNL(), nl.getTenNL(), nl.getSoLuong(), nl.getLoaiNL(), nl.getDonGia()});
            }
            
            String[] columnNamesSP = {"Mã sản phẩm", "Tên sản phẩm","Số lượng nhập","Loại", "Đơn Giá","Nhà cung cấp"};
            modelSP = new DefaultTableModel(data,columnNamesSP);
            tableSP = new JTable(modelSP);  
                     
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
                        //cập nhật giá
                        txtTongtien.setText(calculateColumnTotal(tableSP));
                    } else {
                        // Hiển thị thông báo nếu không có hàng nào được chọn
                        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa");
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
        
        public void showtt_thanhtoan(){
                JTextField txtManv = new JTextField(txtMANV.getText());
                JTextField txtTong = new JTextField(txtTongtien.getText());
                //không biết chức năng này sẽ ra cái gì nên chưa làm 
                Object[] options = {"OK", "Cancel"};
                Object[] message = {
                    "Mã nhân viên: ", txtManv,
                    "Tổng số tiền: ", txtTong,   
                    scrollPaneSP                   
                };
                        
                int option = JOptionPane.showOptionDialog(null, message, "Thông tin",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
                if (option == 0) {
                    // Người dùng nhấn OK
                    thanhtoan();
                } 
        }
         
        public void thanhtoan() {
        	//Lưu số lượng nguyên liệu mới nhập
        	for(int i=0; i < tableSP.getRowCount(); i++) {
        		String sltxt = tableSP.getValueAt(i, 2).toString();
        		int sl = Integer.parseInt(sltxt);
        		NhapHangBUS nhap = new NhapHangBUS();
        		nhap.updateSL_NL(tableSP.getValueAt(i, 0).toString(), sl);
        		
        		System.out.println(sl);
        	}
        	
        	//Tạo phiếu nhập cho mỗi phiên nhập hàng
        	String maNCC = getMaNCC(tableSP.getValueAt(0, 5).toString());
        	String manv = txtMANV.getText();
    		String tt = txtTongtien.getText();
    		int tongtien = Integer.parseInt(tt);
    		
    		System.out.println(manv);
    		
    		String maPN = null;
    		
    		//Lệnh tạo ngày tháng tạo phiếu nhập
    		LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateString = currentDate.format(formatter);
            int count = LaySLMaPN();
            maPN = "PN" + (count+1);
            
            
            PHIEUNHAP pn = new PHIEUNHAP(maPN, dateString, tongtien, manv, maNCC);
            PhieuNhapBUS bus = new PhieuNhapBUS();
            bus.insertPN(pn);
        	for(int i=0; i < tableSP.getRowCount(); i++) {
                //Lưu thông tin chi tiết phiếu nhập
            	CHITIETPHIEUNHAP ctpn = new CHITIETPHIEUNHAP(maPN, tableSP.getValueAt(i, 0).toString(), Integer.parseInt(tableSP.getValueAt(i, 2).toString()));
            	ChiTietPhieuNhapBUS ctpnbus = new ChiTietPhieuNhapBUS();
            	ctpnbus.insertCTPN(ctpn);
        	}
        	
        }
         
        //Lấy số lượng phiếu nhập đã có trong database để tạo mã phiếu nhập
        public int LaySLMaPN() {
        	PhieuNhapBUS bus = new PhieuNhapBUS();
        	ArrayList<PHIEUNHAP> listpn = bus.selectAllPN();
        	int count = listpn.size();
        	return count;
        }
        
        public String getMaNCC(String tennnc) {
        	NhapHangBUS bus = new NhapHangBUS();
        	return bus.getMaNCC_BUS(tennnc);
        }
        
        public String getMaNV(String tennv) {
        	NhapHangBUS bus = new NhapHangBUS();
        	return bus.getMaNV_BUS(tennv);
        }

        public String calculateColumnTotal(JTable table) {
            int total = 0;
            for (int i = 0; i < table.getRowCount(); i++) {
                String value = table.getValueAt(i, 4).toString();
                String soluong = table.getValueAt(i, 2).toString();
                total += Integer.parseInt(value)*Integer.parseInt(soluong);
                //total += Double.parseDouble(value)*Double.parseDouble(soluong);
            }
            return String.valueOf(total);
        }
}
