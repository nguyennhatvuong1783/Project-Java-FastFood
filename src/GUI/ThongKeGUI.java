package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.DaoHoaDon;
import DAO.DaoPhieuNhap;
import DTO.THONGKE;
import java.awt.Font;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ThongKeGUI extends JPanel {

    public JPanel jpanel1=new JPanel();
    public JPanel jpanel2=new JPanel();
    public JPanel jpanel_center=new JPanel();
    ArrayList<THONGKE> list = new ArrayList<>();

    public ThongKeGUI() {
        init();
    }
    
    public void init() {             
        this.setLayout(new BorderLayout());
        JLabel lblNewLabel = new JLabel("Thống Kê", JLabel.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
        this.add(lblNewLabel, BorderLayout.NORTH); 
        this.add(jpanel_center, BorderLayout.CENTER);
        jpanel_center.setLayout(new GridLayout(2,1));
        jpanel_center.add(jpanel1);
        jpanel_center.add(jpanel2);
        ThongKeThu(jpanel1);
        ThongKeNhap(jpanel2);
        
    }

    public void ThongKeThu(JPanel jpanel) {
        DaoHoaDon daoHoaDon = new DaoHoaDon();
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (daoHoaDon.getListThongKe() != null) {
            
            for (THONGKE s : daoHoaDon.getListThongKe()) {              
                dataset.addValue(s.getSoTien(), "Tổng tiền", s.getThang() + "/" + s.getNam());
            }
            
            JFreeChart barChart = ChartFactory.createBarChart("Biểu đồ biểu thị doanh thu theo tháng năm".toUpperCase(), "Thời gian", "Số tiền", dataset, PlotOrientation.VERTICAL, false, true, false);
            
            ChartPanel chartPanel = new ChartPanel(barChart);
            
            jpanel1.removeAll();
            jpanel1.setLayout(new BorderLayout());
            jpanel1.add(chartPanel);
            
        }
    }
    public void ThongKeNhap(JPanel jpanel) {
        DaoPhieuNhap daoPhieuNhap=new DaoPhieuNhap();
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (daoPhieuNhap.getListThongKe() != null) {
            
            for (THONGKE s : daoPhieuNhap.getListThongKe()) {              
                dataset.addValue(s.getSoTien(), "Tổng tiền", s.getThang() + "/" + s.getNam());
            }
            
            JFreeChart barChart = ChartFactory.createBarChart("Biểu đồ biểu thị tiền nhập theo tháng năm".toUpperCase(), "Thời gian", "Số tiền", dataset, PlotOrientation.VERTICAL, false, true, false);
            
            ChartPanel chartPanel = new ChartPanel(barChart);
            
            jpanel2.removeAll();
            jpanel2.setLayout(new BorderLayout());
            jpanel2.add(chartPanel);
            
        }
    } 
     public static void main(String arg[]){
        JFrame jp=new JFrame();
        ThongKeGUI tk=new ThongKeGUI();
        jp.setLayout(new BorderLayout());
        jp.add(tk, BorderLayout.CENTER);
        jp.setSize(600,600);
        jp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jp.setLocationRelativeTo(null);
        jp.setVisible(true);
    }
}
