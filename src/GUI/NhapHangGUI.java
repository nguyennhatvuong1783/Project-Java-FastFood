package GUI;

import GiaoDienChuan.ExportExcelButton;
import BUS.NhapHangBUS;
import GiaoDienChuan.MyTable;
import GiaoDienChuan.SuaButton;
import GiaoDienChuan.ThemButton;
import GiaoDienChuan.XoaButton;
import GiaoDienChuan.RefreshButton;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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
import DTO.NHANVIEN;
import DTO.PHIEUNHAP;
import BUS.PhieuNhapBUS;
import BUS.ChiTietPhieuNhapBUS;
import BUS.NguyenLieuBUS;
import BUS.NhaCungCapBUS;
import DTO.CHITIETPHIEUNHAP;

public class NhapHangGUI extends JPanel{
        //public JFrame frame;  
	
        public JLabel themnccbtn; 
        public JButton themspbtn;   
        public JPanel panel;
        public JPanel panelNCCselector;
        public JPanel panelSPselector;
        public JPanel panelSPoption;
        public JPanel panelLastoption;
        public JPanel NCCpnl;
        public JPanel SPpnl;
        public JComboBox cb;
        public MyTable tableNCC;
        public MyTable tableSP;
        public JScrollPane scrollPaneNCC;
        public JScrollPane scrollPaneSP;
        public JScrollPane scrollPane;
        public JTextField txtSL;
        public JLabel txtSLhint;
        public XoaButton delbtn;
        public SuaButton modbtn;
        public RefreshButton rFreshBtn;
        public JTextField txtMANV;
        public JLabel txtMANVhint;
        public JLabel txtTongtien;
        public JLabel txtTongtienhint;
        public JButton thanhtoanbtn;
        private TableRowSorter<DefaultTableModel> sorter1; 
        private TableRowSorter<DefaultTableModel> sorter2; 
         //dữ liệu cho tùy chỉnh   
        public String dataSoluong;
        
	public NhapHangGUI() {
		init();
	}
	
	public void init() {
            this.add(new JLabel("Nhập hàng GUI")); 
            this.setLayout(new BorderLayout());    
            
            //fix
            //frame = new JFrame();      
            
            themnccbtn = new JLabel("Chọn nhà cung cấp");
            themspbtn = new JButton("Thêm sản phẩm"); 
            tableNCC = new MyTable();
            tableSP = new MyTable();
            ComboBoxNCC(); 
            khoitao2bang(); 

            sorter1 = new TableRowSorter<>(tableNCC.getModel());
            sorter2 = new TableRowSorter<>(tableSP.getModel());

            tableNCC.getTable().setRowSorter(sorter1);
            tableSP.getTable().setRowSorter(sorter2);

            scrollPaneNCC = new JScrollPane(tableNCC);
            scrollPaneSP = new JScrollPane(tableSP);

            panel = new JPanel();
            panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
            panelNCCselector = new JPanel(new FlowLayout());
            panelSPselector = new JPanel(new FlowLayout());
            panelSPoption = new JPanel(new FlowLayout());
            panelLastoption = new JPanel(new FlowLayout());
            SPpnl = new JPanel(new BorderLayout());
            NCCpnl = new JPanel(new BorderLayout());
            SPpnl.setPreferredSize(new Dimension(0,200)) ;
            NCCpnl.setPreferredSize(new Dimension(0,200)) ;

            txtSL=new JTextField("0",20);            
            txtSLhint=new JLabel("Nhập số lượng");
            delbtn = new XoaButton();
            modbtn = new SuaButton();
            rFreshBtn = new RefreshButton();
            txtMANV=new JTextField(20);
            txtMANVhint = new JLabel("Nhập mã nhân viên: ");
            txtTongtien=new JLabel("0");
            txtTongtienhint=new JLabel("Tổng số tiền: ");
            thanhtoanbtn = new JButton("Thanh toán");
            scrollPane=new JScrollPane();
            
            // Thêm vào JPanel         
            panelNCCselector.add(themnccbtn);                
            panelNCCselector.add(cb);   
            panelNCCselector.add(rFreshBtn);
            NCCpnl.add(scrollPaneNCC,BorderLayout.CENTER);  
            panelSPselector.add(txtSLhint);
            panelSPselector.add(txtSL);
            panelSPselector.add(themspbtn);
            panelSPoption.add(delbtn);
            panelSPoption.add(modbtn);
            SPpnl.add(scrollPaneSP,BorderLayout.CENTER);
            panelLastoption.add(txtMANVhint);
            panelLastoption.add(txtMANV);
            panelLastoption.add(txtTongtienhint);
            panelLastoption.add(txtTongtien);
            panelLastoption.add(thanhtoanbtn);
            panel.add(panelNCCselector);
            panel.add(NCCpnl); 
            panel.add(panelSPselector);
            panel.add(panelSPoption);
            panel.add(SPpnl);
            panel.add(panelLastoption);

            this.add(panel,BorderLayout.CENTER);
            
            /*
            //Thêm tiêu đề             
            frame.getContentPane().add(panel);
            // Đặt JFrame ở chế độ toàn màn hình
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // Đặt hành động mặc định khi đóng JFrame
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //Hiển thị JFrame
            frame.setVisible(true);
            */
            
            rFreshBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	tableNCC.getModel().setRowCount(0);
                    khoitao2bang();
                }
            });   
            
           
            themspbtn.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		int selectedRow = tableNCC.getTable().getSelectedRow();
            		try {
            			int i = Integer.parseInt(txtSL.getText());
            			if(i > 0) {
		            		if (selectedRow >= 0) {
			                    //thêm vào bảng mới
			                    tableSP.getModel().addRow(new Object[] {tableNCC.getValueAt(selectedRow, 0), tableNCC.getValueAt(selectedRow, 1), txtSL.getText()
			                    ,tableNCC.getValueAt(selectedRow, 3), tableNCC.getValueAt(selectedRow, 4),(String) cb.getSelectedItem()});
			                    //cập nhật giá
			                    txtTongtien.setText(calculateColumnTotal(tableSP.getTable()));
			                    txtSL.setText("0");
		                    } else {
		                        // Hiển thị thông báo nếu không có hàng nào được chọn
		                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để thêm");
		                    }
            			} else {
            				JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng lớn hơn 0");
            			}
            		} catch (NumberFormatException e1) {
            			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng kí tự số vào số lượng và số lượng lớn hơn 0");
                    }
            	}
            });
            
            delbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    xoa(tableSP.getModel());
                }
            });   
            
            modbtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0){
                    // Lấy chỉ số của hàng được chọn
                    int selectedRow = tableSP.getTable().getSelectedRow();
                    
                    // Kiểm tra xem có hàng nào được chọn không
                    if (selectedRow >= 0) {
                       // Lấy dữ liệu từ hàng được chọn                        
                        dataSoluong = tableSP.getValueAt(selectedRow, 2).toString();  
                        sua(tableSP.getModel(),dataSoluong);
                        //cập nhật giá
                        txtTongtien.setText(calculateColumnTotal(tableSP.getTable()));
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
                	//Kiểm tra mã nhân viên có tồn tại trong hệ thống ko
            		NhapHangBUS bus = new NhapHangBUS();
            		ArrayList<NHANVIEN> listNV = bus.selectAllNV();
            		int t=0;
            		for(NHANVIEN nv : listNV) {
            			if(nv.getMaNV().equalsIgnoreCase(txtMANV.getText())) {
            				t=1;
            			}
            		}
            		if(txtMANV.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên");
                    } else if(t == 1) {
            			showtt_thanhtoan();
            		} else {
            			JOptionPane.showMessageDialog(null, "Nhân viên không tồn tại trong hệ thống vui lòng nhập lại");
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
            String[] columnNamesNCC = {"Mã sản phẩm", "Tên sản phẩm","Số lượng tồn kho","Loại", "Đơn Giá"};
            tableNCC.setHeaders(columnNamesNCC);
            
            //Đổ dữ liệu lên bảng NCC
            NhapHangBUS nhap = new NhapHangBUS();
            ArrayList<NGUYENLIEU> listnl = nhap.selectAll();
            for(NGUYENLIEU nl : listnl) {
            	if(nl.getTrangThai() == 1) {
            		tableNCC.getModel().addRow(new Object[] {nl.getMaNL(), nl.getTenNL(), nl.getSoLuong(), nl.getLoaiNL(), nl.getDonGia()});
            	}
            }
            
            String[] columnNamesSP = {"Mã sản phẩm", "Tên sản phẩm","Số lượng nhập","Loại", "Đơn Giá","Nhà cung cấp"};
            tableSP.setHeaders(columnNamesSP);  
            
        }
        
        public void xoa(DefaultTableModel model){
            // Lấy chỉ số của hàng được chọn
                int selectedRow = tableSP.getTable().getSelectedRow();

                // Kiểm tra xem có hàng nào được chọn không
                if (selectedRow >= 0) {
                    // Xóa hàng được chọn từ model
                    model.removeRow(selectedRow);
                    //cập nhật giá
                    txtTongtien.setText(calculateColumnTotal(tableSP.getTable()));
                } else {
                    // Hiển thị thông báo nếu không có hàng nào được chọn
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa");
                }
        }
        
        public void sua(DefaultTableModel model,String Soluong){
        	try {
	            // Tạo một JTextField cho mỗi cột trong JTable      
	            JTextField txtSol = new JTextField(Soluong);                                  
	            // Tạo một JOptionPane để nhận dữ liệu từ người dùng
	            Object[] message = {            
	            "Nhập số lượng:", txtSol,           
	            };
	            
	
	            int option = JOptionPane.showConfirmDialog(null, message, "Nhập dữ liệu", JOptionPane.OK_CANCEL_OPTION);
	            int i = Integer.parseInt(txtSol.getText());
	            if (option == JOptionPane.OK_OPTION) {
	                // Lấy chỉ số của hàng được chọn
	                int selectedRow = tableSP.getTable().getSelectedRow();
	                
	                // Cập nhật dữ liệu của hàng được chọn                
	                model.setValueAt(txtSol.getText(), selectedRow, 2);              
	            }
        	} catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Sai kiểu dữ liệu vui lòng nhập lại");
            }
        }
        
        public void showtt_thanhtoan(){
            JTextField txtManv = new JTextField(txtMANV.getText());
            JTextField txtTong = new JTextField(txtTongtien.getText());
            txtManv.setEditable(false);
            txtTong.setEditable(false);
            
            Object[] options = {"OK", "Cancel"};
            Object[] message = {
               "Bạn có xác nhận thanh toán ???"                   
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
        	for(int i=0; i < tableSP.getTable().getRowCount(); i++) {
        		String sltxt = tableSP.getValueAt(i, 2).toString();
        		int sl = Integer.parseInt(sltxt);
        		NhapHangBUS nhap = new NhapHangBUS();
        		nhap.updateSL_NL(tableSP.getValueAt(i, 0).toString(), sl);
        		System.out.println(sl);
        	}
        	
        	//Tạo phiếu nhập cho mỗi phiên nhập hàng
        	String maNCC = getMaNCC(tableSP.getValueAt(0, 5).toString());
        	String manv = txtMANV.getText().toString();
    		String tt = txtTongtien.getText();
    		int tongtien = Integer.parseInt(tt);
    		
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
        	for(int i=0; i < tableSP.getTable().getRowCount(); i++) {
                //Lưu thông tin chi tiết phiếu nhập
            	CHITIETPHIEUNHAP ctpn = new CHITIETPHIEUNHAP(maPN, tableSP.getValueAt(i, 0).toString(), Integer.parseInt(tableSP.getValueAt(i, 2).toString()));
            	ChiTietPhieuNhapBUS ctpnbus = new ChiTietPhieuNhapBUS();
            	ctpnbus.insertCTPN(ctpn);
        	}

            //Reset lại bảng giỏ hàng
        	DefaultTableModel model = tableSP.getModel();
            model.setRowCount(0);
            calculateColumnTotal(tableSP.getTable());
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
