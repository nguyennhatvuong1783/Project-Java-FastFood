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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HOME
 */
public class KhuyenMaiBus {

    private DaoKhuyenMai daoKhuyenMai;
    private KhuyenMaiGUI khuyenMaiGui;
    private MyTable tableKM;
    private String id;

    private DefaultTableModel model;

    public KhuyenMaiBus(KhuyenMaiGUI khuyenMaiGui) {
        this.khuyenMaiGui = khuyenMaiGui ;
    }

    public void LoadDataToTable() {
        daoKhuyenMai = new DaoKhuyenMai();
        String[] header = {"Mã khuyến mãi", "Tên khuyến mãi", "Điều kiện", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"};
        model = new DefaultTableModel(header, 0);
        khuyenMaiGui.getTableKM().getTable().setModel(model);
        for (KHUYENMAI km : daoKhuyenMai.selectAll()) {
            if (km.getTrangThai() == 1) {
                String[] a = {km.getMaKM(), km.getTenKM(), km.getDieuKienKM(), Float.toString(km.getGiamGia()), km.dateBĐToString(), km.dateKTToString(), "Còn hiệu lực"};
                String[] b = {km.getMaKM(), km.getTenKM(), km.getDieuKienKM(), Float.toString(km.getGiamGia()), km.dateBĐToString(), km.dateKTToString(), "Hết hạn"};
                if (new Date().before(km.getNgayKT())) {
                    model.addRow(a);
                } else {
                    model.addRow(b);
                }
            }

        }
    }
    

    public int maKhuyenMaiMoiNhat() {
        daoKhuyenMai = new DaoKhuyenMai();
        return daoKhuyenMai.selectAll().size();
    }

    public int themKM() {
        daoKhuyenMai = new DaoKhuyenMai();
        if ((khuyenMaiGui.getTxt_TenKM().getText().equals(""))
                || (khuyenMaiGui.getTxt_dieuKien().getText().equals(""))
                || (khuyenMaiGui.getTxt_phanTram().getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin!");
            return 2;
        } else if (khuyenMaiGui.getTxt_phanTram().getText().matches("[a-z0-9A-Z]{0,}") == false
                || khuyenMaiGui.getTxt_phanTram().getText().matches("[0-9]{0,}[a-zA-Z]{1,}[0-9]{0,}") == true
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
            return 2;

        } else if (khuyenMaiGui.getDate_BĐ().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày bắt đầu hoặc kiểm tra lại ngày bắt đầu nếu đã nhập");
            return 2;
        } else if (khuyenMaiGui.getDate_KT().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày kết thức hoặc kiểm tra lại ngày kết thúc nếu đã nhập");
            return 2;
        } else {
            daoKhuyenMai = new DaoKhuyenMai();
            if (!khuyenMaiGui.getTxt_dieuKien().getText().matches("^TONGTIEN >= [1-9]{1}[0,9]{1,}")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập điều kiện có dạng: TONGTIEN >= x với x là 1 số tiền");
                return 2;
            }

            if (new Date().after(khuyenMaiGui.getDate_BĐ().getDate())) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải sau ngày hiện tại!");

                return 2;
            }
            if (khuyenMaiGui.getDate_BĐ().getDate().after(khuyenMaiGui.getDate_KT().getDate())) {
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau hoặc cùng ngày với ngày bắt đầu!");
                return 2;
            }

            int trangThai = 1;
            String maKM = String.valueOf(this.maKhuyenMaiMoiNhat() + 1);
            KHUYENMAI km = new KHUYENMAI(maKM,
                    khuyenMaiGui.getTxt_TenKM().getText(),
                    khuyenMaiGui.getTxt_dieuKien().getText(),
                    Float.valueOf(khuyenMaiGui.getTxt_phanTram().getText()),
                    khuyenMaiGui.getDate_BĐ().getDate(),
                    khuyenMaiGui.getDate_KT().getDate(),
                    trangThai);

            if (daoKhuyenMai.insert(km) != 0) {
                this.LoadDataToTable();
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
    }

    public void LoadThongTinKm() {
        int seclectedRow = khuyenMaiGui.getTableKM().getTable().getSelectedRow();
        if (seclectedRow != -1) {
            id = (String) khuyenMaiGui.getTableKM().getTable().getValueAt(seclectedRow, 0);
        } else {
            id = null;
        }
        if (id != null) {
            daoKhuyenMai = new DaoKhuyenMai();
            for (KHUYENMAI km : daoKhuyenMai.selectAll()) {
                if (id.equals(km.getMaKM())) {
                    khuyenMaiGui.getTxt_MaKM().setText(km.getMaKM());
                    khuyenMaiGui.getTxt_TenKM().setText(km.getTenKM());
                    khuyenMaiGui.getTxt_dieuKien().setText(km.getDieuKienKM());
                    String giamgia = Float.toString(km.getGiamGia());
                    khuyenMaiGui.getTxt_phanTram().setText(giamgia);
                    khuyenMaiGui.getDate_BĐ().setDate(km.getNgayBD());
                    khuyenMaiGui.getDate_KT().setDate(km.getNgayKT());
                }
            }
        }

    }

    public int SuaKm() {
        daoKhuyenMai = new DaoKhuyenMai();
        if ((khuyenMaiGui.getTxt_TenKM().getText().equals(""))
                || (khuyenMaiGui.getTxt_dieuKien().equals(""))
                || (khuyenMaiGui.getTxt_phanTram().getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin!");
            return 2;
        } else if (khuyenMaiGui.getTxt_phanTram().getText().matches("[a-z0-9A-Z]{0,}") == false
                || khuyenMaiGui.getTxt_phanTram().getText().matches("[0-9]{0,}[a-zA-Z]{1,}[0-9]{0,}") == true
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
            return 2;

        } else if (khuyenMaiGui.getDate_BĐ().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày bắt đầu hoặc kiểm tra lại ngày bắt đầu nếu đã nhập");
            return 2;
        } else if (khuyenMaiGui.getDate_KT().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày kết thức hoặc kiểm tra lại ngày kết thúc nếu đã nhập");
            return 2;
        } else {
            daoKhuyenMai = new DaoKhuyenMai();
            if (!khuyenMaiGui.getTxt_dieuKien().getText().matches("^TONGTIEN >= [1-9]{1}[0,9]{1,}")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập điều kiện có dạng: TONGTIEN >= x với x là 1 số tiền");
                return 2;
            }

            if (new Date().after(khuyenMaiGui.getDate_BĐ().getDate())) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải sau ngày hiện tại!");

                return 2;
            }
            if (khuyenMaiGui.getDate_BĐ().getDate().after(khuyenMaiGui.getDate_KT().getDate())) {
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau hoặc cùng ngày với ngày bắt đầu!");
                return 2;
            }

            int trangThai = 1;
            KHUYENMAI km = new KHUYENMAI(khuyenMaiGui.getTxt_MaKM().getText(),
                    khuyenMaiGui.getTxt_TenKM().getText(),
                    khuyenMaiGui.getTxt_dieuKien().getText(),
                    Float.valueOf(khuyenMaiGui.getTxt_phanTram().getText()),
                    khuyenMaiGui.getDate_BĐ().getDate(),
                    khuyenMaiGui.getDate_KT().getDate(),
                    trangThai);
            if (daoKhuyenMai.update(km) != 0) {
                this.LoadDataToTable();
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
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int XoaKm() {
        
        
        daoKhuyenMai = new DaoKhuyenMai();
        int trangThai = 0;
        KHUYENMAI km = new KHUYENMAI(khuyenMaiGui.getTxt_MaKM().getText(),
                khuyenMaiGui.getTxt_TenKM().getText(),
                khuyenMaiGui.getTxt_dieuKien().getText(),
                Float.valueOf(khuyenMaiGui.getTxt_phanTram().getText()),
                khuyenMaiGui.getDate_BĐ().getDate(),
                khuyenMaiGui.getDate_KT().getDate(),
                trangThai);
        for (KHUYENMAI km1 : daoKhuyenMai.selectAll()) {
            if (km1.getMaKM().equals(km.getMaKM())) {
                if (daoKhuyenMai.delete(km) != 0) {
                    this.LoadDataToTable();
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
        }
        return 0;

    }

}
