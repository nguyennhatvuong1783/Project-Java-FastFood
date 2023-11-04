/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DaoKhuyenMai;
import GUI.KhuyenMaiGUI;
import DTO.KHUYENMAI;
import GiaoDienChuan.MyTable;
import java.awt.BorderLayout;
import java.util.Date;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.text.SimpleDateFormat;

/**
 *
 * @author HOME
 */
public class KhuyenMaiBus {

    private DaoKhuyenMai daoKhuyenMai;
    private KhuyenMaiGUI khuyenMaiGui;
    private MyTable tableKM;
    private String id;

    public KhuyenMaiBus(KhuyenMaiGUI khuyenMaiGui) {
        this.khuyenMaiGui = khuyenMaiGui;
    }

    public void LoadDataToTable() {
        daoKhuyenMai = new DaoKhuyenMai();
        for (KHUYENMAI km : daoKhuyenMai.selectAll()) {
            khuyenMaiGui.addRow(km);
        }

    }

    public int themKM() {
        daoKhuyenMai = new DaoKhuyenMai();
        if ((khuyenMaiGui.getTxt_MaKM().getText() == "")
                || (khuyenMaiGui.getTxt_TenKM().getText() == "")
                || (khuyenMaiGui.getTxt_dieuKien().getText() == "")
                || (khuyenMaiGui.getTxt_phanTram().getText() == "")) {
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
                || khuyenMaiGui.getTxt_phanTram().getText().contains(",")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("&")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("*")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("-")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("+")
                || khuyenMaiGui.getTxt_phanTram().getText().contains("/")) {
            JOptionPane.showMessageDialog(null, "Phần trăm giảm phải là số nguyên hoặc số thập phân(x.y)");
            return 0;

        } else if (khuyenMaiGui.getDate_BĐ().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày bắt đầu hoặc kiểm tra lại ngày bắt đầu nếu đã nhập");
            return 0;
        } else if (khuyenMaiGui.getDate_KT().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày kết thức hoặc kiểm tra lại ngày kết thúc nếu đã nhập");
            return 0;
        } else if (khuyenMaiGui.getTxt_MaKM().getText().matches("[0-9a-zA-Z]{1,}") == false) {
            JOptionPane.showMessageDialog(null, "Mã khuyến mãi không chứa dấu và kí tự đặc biệt!");
        } else {
            daoKhuyenMai = new DaoKhuyenMai();
            for (KHUYENMAI km1 : daoKhuyenMai.selectAll()) {
                if (khuyenMaiGui.getTxt_MaKM().getText().equals(km1.getMaKM())) {
                    JOptionPane.showMessageDialog(null, "Mã khuyến mãi đã tồn tại!");
                    return 0;
                }
            }

            if (new Date().after(khuyenMaiGui.getDate_BĐ().getDate())) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải sau ngày hiện tại!");

                return 0;
            }
            if (khuyenMaiGui.getDate_BĐ().getDate().after(khuyenMaiGui.getDate_KT().getDate())) {
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau hoặc cùng ngày với ngày bắt đầu!");
                return 0;
            }

            int trangThai = 1;
            KHUYENMAI km = new KHUYENMAI(khuyenMaiGui.getTxt_MaKM().getText(),
                    khuyenMaiGui.getTxt_TenKM().getText(),
                    khuyenMaiGui.getTxt_dieuKien().getText(),
                    Float.valueOf(khuyenMaiGui.getTxt_phanTram().getText()),
                    khuyenMaiGui.getDate_BĐ().getDate(),
                    khuyenMaiGui.getDate_KT().getDate(),
                    trangThai);
            if (daoKhuyenMai.insert(km) != 0) {
                khuyenMaiGui.addRow(km);
                khuyenMaiGui.getTxt_MaKM().setText("");
                khuyenMaiGui.getTxt_TenKM().setText("");
                khuyenMaiGui.getTxt_dieuKien().setText("");
                khuyenMaiGui.getTxt_phanTram().setText("");
                khuyenMaiGui.getDate_BĐ().setDate(null);
                khuyenMaiGui.getDate_KT().setDate(null);
                return 1;
            } else {
                return 0;
            }
        }
        return 0;

    }

    public int SuaKm() {
        int seclectedRow = khuyenMaiGui.getTableKM().getTable().getSelectedRow();
        if (seclectedRow !=-1) {
            id = (String) khuyenMaiGui.getTableKM().getTable().getValueAt(seclectedRow, 0);
        } else {
            id = null;
        }
        if(id!=null){
            daoKhuyenMai=new DaoKhuyenMai();
            for(KHUYENMAI km:daoKhuyenMai.selectAll()){
                if(id.equals(km.getMaKM())){
                    khuyenMaiGui.getTxt_MaKM().setText(km.getMaKM());
                    khuyenMaiGui.getTxt_MaKM().setEnabled(false);
                    khuyenMaiGui.getTxt_TenKM().setText(km.getTenKM());
                    khuyenMaiGui.getTxt_dieuKien().setText(km.getDieuKienKM());
                    String giamgia= Float.toString(km.getGiamGia());
                    khuyenMaiGui.getTxt_phanTram().setText(giamgia);
                    khuyenMaiGui.getDate_BĐ().setDate(km.getNgayBD());
                    khuyenMaiGui.getDate_KT().setDate(km.getNgayKT());
                }
            }
        }

        return 0;

    }

}
