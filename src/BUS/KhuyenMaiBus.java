/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DaoKhuyenMai;
import GUI.KhuyenMaiGUI;
import DTO.KHUYENMAI;
import GiaoDienChuan.MyTable;
import java.util.Date;
import javax.swing.JOptionPane;
import java.time.LocalDate;

/**
 *
 * @author HOME
 */
public class KhuyenMaiBus {

    private DaoKhuyenMai daoKhuyenMai;
    private KhuyenMaiGUI khuyenMaiGui;
    private MyTable tableKM;

    public MyTable LoadDataToTable() {
        daoKhuyenMai = new DaoKhuyenMai();
        tableKM = new MyTable();
        tableKM.setHeaders(new String[]{
            "Mã khuyến mãi", "Tên khuyến mãi", "Điều kiện", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc"
        });
        for (KHUYENMAI km : daoKhuyenMai.selectAll()) {
            if (km.getTrangThai() != 0) {
                String[] a = {km.getMaKM(), km.getTenKM(), km.getDieuKienKM(), Float.toString(km.getGiamGia()), km.getNgayBD(), km.getNgayKT()};
                tableKM.addRow(a);
            }
        }
        return tableKM;
    }

    public boolean checkDate(Date date) {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        if (date.toString().matches("[0-3]{1}[0-9]{1}/[0-1]{1}[0-9]{1,}/20[2-9]{1}[0-9]{1}")) {
            JOptionPane.showMessageDialog(null, "Sai định dạng ngày!");
            return false;
        } else {
            if (day < 1 && day > 31) {
                JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại ngày!");
                return false;
            } else if (month < 1 && month > 12) {
                JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại tháng!");
                return false;
            } else if (year < 2023) {
                JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại năm!");
                return false;
            } else if (month == 2 && day > 29) {
                JOptionPane.showMessageDialog(null, "Tháng 2 có nhiều nhất 29 ngày, vui lòng kiểm tra lại!");
                return false;
            }
        }
        return true;
    }

    public int themKM() {
        khuyenMaiGui = new KhuyenMaiGUI();
        daoKhuyenMai=new DaoKhuyenMai();
        if ((khuyenMaiGui.getTxt_MaKM().getText() == "")
                || (khuyenMaiGui.getTxt_TenKM().getText() == "")
                || (khuyenMaiGui.getTxt_dieuKien().getText() == "")
                || (khuyenMaiGui.getTxt_phanTram().getText() == "")
                || (khuyenMaiGui.getDate_BĐ().getDate().toString() == "")
                || (khuyenMaiGui.getDate_KT().getDate().toString() == "")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin!");
            return 0;
        } else if (khuyenMaiGui.getTxt_phanTram().getText().matches("[a-zA-Z]") == true
                || khuyenMaiGui.getTxt_phanTram().getText().contains("~")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("!")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("@")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("#")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("$")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("%")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("^")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("&")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("*")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("-")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("+")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("/")) {
            JOptionPane.showMessageDialog(null, "Giảm giá phải là số!");
            return 0;

        } else if (checkDate(khuyenMaiGui.getDate_BĐ().getDate()) == false) {
            return 0;
        } else if (checkDate(khuyenMaiGui.getDate_KT().getDate()) == false) {
            return 0;
        } else if(khuyenMaiGui.getTxt_MaKM().getText().matches("[0-9a-zA-Z]{1,}")==false){
            JOptionPane.showMessageDialog(null, "Thêm thất bại, mã khuyến mãi không chứa dấu và kí tự đặc biệt!");
        } else{
            int trangThai=1;
            KHUYENMAI km=new KHUYENMAI(khuyenMaiGui.getTxt_MaKM().getText(),
                    khuyenMaiGui.getTxt_TenKM().getText(),
                    khuyenMaiGui.getTxt_dieuKien().getText(),
                    Float.valueOf(khuyenMaiGui.getTxt_phanTram().getText()), 
                    khuyenMaiGui.getDate_BĐ().getDateFormatString(), 
                    khuyenMaiGui.getDate_KT().getDateFormatString(), 
                    trangThai);
           if(daoKhuyenMai.insert(km)!=0){
               return 1;
           }else{
               return 0;
           }
        }
        return 0;

    }

}
