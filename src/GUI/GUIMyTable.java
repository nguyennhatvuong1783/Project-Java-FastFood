package GUI;

import DTO.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class GUIMyTable extends JPanel {

    public JTable tb;
    public DefaultTableModel tbModel;
    public JScrollPane pane;

    public GUIMyTable() {
        setLayout(new BorderLayout());

        tb = new JTable();
        //https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
        tbModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
        
        };
        
        pane = new JScrollPane(tb);
        // setUnitIncrement giúp thanh cuộn mượt mà hơn , số càng nhỏ càng mượt ( chậm )
        pane.getVerticalScrollBar().setUnitIncrement(8);
        
        tb.setFillsViewportHeight(true);
        tb.setFont(new Font("Segoe UI", 0, 16));
        tb.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        tb.setRowHeight(40);
        
        // color
        int bgColor = 235;
        int color = 0;
        tb.getTableHeader().setBackground(new Color(bgColor, bgColor, bgColor));
        tb.getTableHeader().setForeground(new Color(color, color, color));
        tb.setBackground(new Color(bgColor, bgColor, bgColor));
        tb.setForeground(new Color(color, color, color));
        // lệnh này chặn trường hợp chọn 1 lúc nhiều row
        tb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        
        add(pane, BorderLayout.CENTER);
    }
    // hàm thiết lập header với mảng chuỗi
    public void setHeaders(String[] headers) {
        // lệnh tùy biến header theo mảng đổ vào 
        tbModel.setColumnIdentifiers(headers);
        tb.setModel(tbModel);
    }
    // hàm thiết lập header với ArrayList
    public void setHeaders(ArrayList<String> headers) {
        tbModel.setColumnIdentifiers(headers.toArray());
        tb.setModel(tbModel);
    }
    // hàm thiết lập cho dữ liệu căn giữa
    // https://stackoverflow.com/questions/7433602/how-to-center-in-jtable-cell-a-value
    public void setAlignment(int column, int align) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(align);
        tb.getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
    }

    // https://stackoverflow.com/questions/28823670/how-to-sort-jtable-in-shortest-way
    public void setupSort() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tb.getModel());
        tb.setRowSorter(sorter);

        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        for (int i = 0; i < tbModel.getColumnCount(); i++) {
            sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
        }
        sorter.setSortKeys(sortKeys);
    }

    public void addRow(String[] data) {
        tbModel.addRow(data);
    }
    public void addRow(MONAN data) {
        addRow(new String[]{
                    data.getMaMonAn(),
                    data.getTenMonAn(),
                    data.getDonViTinh(),
                    String.valueOf(data.getDonGia()),
                    data.getHinhAnh(),
                    data.getLoai(),
                    String.valueOf(data.getSoLuong())
                });
    }
    public void addRow(NGUYENLIEU data) {
        addRow(new String[]{
                    data.getMaNL(),
                    data.getTenNL(),                   
                    String.valueOf(data.getDonGia()),
                    data.getHinhAnh(),
                    data.getLoaiNL(),
                    data.getDonViTinh(),
                    String.valueOf(data.getSoLuong())
                });
    }
    public void addRow(KHACHHANG data) {
        addRow(new String[]{
                    data.getMaKH(),
                    data.getTenKH(),
                    data.getDiaChi(),
                    data.getSdt(),
                });
    }
    public void addRow(NHANVIEN data) {
        addRow(new String[]{
                    data.getMaNV(),
                    data.getTenNV(),
                    data.getGioiTinh(),
                    data.getSdt(),
                    data.getNgaySinh()
                });
    }
    public void addRow(HOADON data) {
        addRow(new String[]{
                    data.getMaHD(),
                    data.getMaNV(),
                    data.getMaKH(),
                    data.getMaKM(),
                    String.valueOf(data.getNgayLap()),
                    String.valueOf(data.getTongTien())
                });
    }
    public void addRow(NHACUNGCAP data) {
        addRow(new String[]{
                    data.getMaNCC(),
                    data.getTenNCC(),
                    data.getSdt(),
                    data.getDiaChi()
                });
    }
    public void addRow(TAIKHOAN data) {
        addRow(new String[]{
                    data.getUserName(),
                    data.getMaQuyen(),
                    data.getMatKhau()
                });
    }
    public void addRow(PHANQUYEN data) {
        addRow(new String[]{
                    data.getMaQuyen(),
                    data.getTenQuyen(),
                    data.getMoTaQuyen()
                });
    }
    public void addRow(KHUYENMAI data) {
        addRow(new String[]{
                    data.getMaKM(),
                    data.getTenKM(),
                    String.valueOf(data.getGiamGia()),
                    String.valueOf(data.getNgayBD()),
                    String.valueOf(data.getNgayKT()),
                    data.getDieuKienKM()                    
                });
    }
//    public void addRow(ChiTietNguyenLieuDTO data) {
//        addRow(new String[]{
//                    data.getIDNguyenLieu(),
//                    String.valueOf(data.getSoLuong()),
//                    
//                });
//    }
    public JTable getTable() {
        return tb;
    }

    public DefaultTableModel getModel() {
        return tbModel;
    }

    public void clear() {
        tbModel.setRowCount(0);
    }
    // Hàm thiết lập độ rộng của cột
    // https://www.codejava.net/java-se/swing/setting-column-width-and-row-height-for-jtable
    public void setColumnsWidth(double[] percentages) {

        double total = 0;
        for (int i = 0; i < tb.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < tb.getColumnModel().getColumnCount(); i++) {
            TableColumn column = tb.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (getPreferredSize().width * (percentages[i] / total)));
        }
    }
    // Hàm thiết lập tự động set size cho cột
    //https://stackoverflow.com/questions/17627431/auto-resizing-the-jtable-column-widths
    public void resizeColumnWidth() {
        final TableColumnModel columnModel = tb.getColumnModel();
        for (int column = 0; column < tb.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < tb.getRowCount(); row++) {
                TableCellRenderer renderer = tb.getCellRenderer(row, column);
                Component comp = tb.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }

            width += 15;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
}




















































