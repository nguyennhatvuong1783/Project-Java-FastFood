package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Connection.connec;
import DTO.CHUCNANG;
import DTO.PHANQUYEN;

public class DaoQuyen implements DaoInterface<PHANQUYEN> {

    public static DaoQuyen getInstance() {
        return new DaoQuyen();
    }

    @Override
    public int insert(PHANQUYEN t) {
        int ketqua = 0;
        try {
            Connection c = connec.getConnection();
            String sql = "INSERT INTO QUYEN(MAQUYEN, TENQUYEN, MOTA, TRANGTHAI) "
                    + " VALUES(?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getMaQuyen());
            pst.setString(2, t.getTenQuyen());
            pst.setNString(3, t.getMoTaQuyen());
            pst.setInt(4, t.getTrangThai());
            ketqua = pst.executeUpdate();

//            System.out.println("Bạn đã thực thi " + sql);
//            System.out.println("Có " + ketqua + " bị thay đổi");
            connec.closeConnection(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int delete(PHANQUYEN quyen) {
        int ketqua = 0;
        try {
            Connection c = connec.getConnection();
            String sql = "DELETE FROM QUYEN "
                    + "WHERE MAQUYEN=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, quyen.getMaQuyen());
            ketqua = pst.executeUpdate();
            
            System.out.println("[DaoQUYEN]: " + sql);
            System.out.println("[DaoQUYEN]: " + ketqua + " dong bi thay doi");

            connec.closeConnection(c);

        } catch (SQLException e) {
        }
        return ketqua;
    }

    @Override
    public int update(PHANQUYEN t) {
        int ketqua = 0;
        try {
            Connection c = connec.getConnection();
            String sql = "UPDATE QUYEN "
                    + " SET TENQUYEN=?"
                    + ", MOTA=?"
                    + ", TRANGTHAI=? "
                    + " WHERE MAQUYEN=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getTenQuyen());
            pst.setNString(2, t.getMoTaQuyen());
            pst.setInt(3, t.getTrangThai());
            pst.setString(4, t.getMaQuyen());
            ketqua = pst.executeUpdate();

            System.out.println("Bạn đã thực thi " + sql);
            System.out.println("Có " + ketqua + " bị thay đổi");

            connec.closeConnection(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public ArrayList<PHANQUYEN> selectAll() {
        ArrayList<PHANQUYEN> ketqua = new ArrayList<PHANQUYEN>();
        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM QUYEN";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String maQuyen = rs.getString("MAQUYEN");
                String tenQuyen = rs.getString("TENQUYEN");
                String moTa = rs.getNString("MOTA");
                int trangThai = rs.getInt("TRANGTHAI");

                PHANQUYEN a = new PHANQUYEN(maQuyen, tenQuyen, moTa, trangThai);
                ketqua.add(a);
            }
            connec.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public PHANQUYEN selectById(PHANQUYEN t) {
        PHANQUYEN ketqua = null;
        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM QUYEN WHERE MAQUYEN=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getMaQuyen());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String maQuyen = rs.getString("MAQUYEN");
                String tenQuyen = rs.getString("TENQUYEN");
                String moTa = rs.getNString("MOTA");
                int trangThai = rs.getInt("TRANGTHAI");

                ketqua = new PHANQUYEN(maQuyen, tenQuyen, maQuyen, trangThai);
            }
            connec.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public ArrayList<PHANQUYEN> selectByCondition(String condition) {
        ArrayList<PHANQUYEN> ketqua = new ArrayList<PHANQUYEN>();
        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM QUYEN WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String maQuyen = rs.getString("MAQUYEN");
                String tenQuyen = rs.getString("TENQUYEN");
                String moTa = rs.getNString("MOTA");
                int trangThai = rs.getInt("TRANGTHAI");

                PHANQUYEN a = new PHANQUYEN(maQuyen, tenQuyen, maQuyen, trangThai);
                ketqua.add(a);
            }
            connec.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public String getMaQuyenByTen(ArrayList<CHUCNANG> listChucNang, String ten) {
        for (CHUCNANG c : listChucNang) {
            if (c.getTenChucNang().equals(ten)) {
                return c.getMaChucNang();
            }
        }
        return "Wrong";
    }
}
