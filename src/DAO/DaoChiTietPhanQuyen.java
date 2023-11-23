package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.CHITIETPHANQUYEN;

public class DaoChiTietPhanQuyen implements DaoInterface<CHITIETPHANQUYEN> {

    public static DaoChiTietPhanQuyen getInstance() {
        return new DaoChiTietPhanQuyen();
    }

    @Override
    @SuppressWarnings("ConvertToTryWithResources")
    public int insert(CHITIETPHANQUYEN ctpq) {
        int ketqua = 0;
        try {
            Connection c = connec.getConnection();
            String sql = "INSERT CHITIETPHANQUYEN(MAQUYEN, MACN)"
                    + "VALUES(?,?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, ctpq.getMaQuyen());
            pst.setString(2, ctpq.getMaChucNang());
            ketqua = pst.executeUpdate();

            System.out.println("[DaoCTPQ]: " + sql);
            System.out.println("[DaoCTPQ]: " + ketqua + " dong bi thay doi");

            pst.close();
            connec.closeConnection(c);
        } catch (SQLException e) {
            System.out.println("[DaoCTPQ error]: " + e);
        }
        return ketqua;
    }

    @Override
    @SuppressWarnings("ConvertToTryWithResources")
    public int delete(CHITIETPHANQUYEN ctpq) {
        int ketqua = 0;

        try {
            Connection c = connec.getConnection();
            String sql = "DELETE FROM CHITIETPHANQUYEN WHERE MAQUYEN=? AND MACN=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, ctpq.getMaQuyen());
            pst.setString(2, ctpq.getMaChucNang());
            ketqua = pst.executeUpdate();

            System.out.println("[DaoCTPQ]: " + sql);
            System.out.println("[DaoCTPQ]: " + ketqua + " dong bi thay doi");

            pst.close();
            connec.closeConnection(c);

        } catch (SQLException e) {
        }

        return ketqua;
    }

    @Override
    public int update(CHITIETPHANQUYEN ctpq) {
        int ketqua = 0;
        try {
            Connection c = connec.getConnection();
            String sql = "UPDATE CHITIETPHANQUYEN "
                    + " SET MACN=?"
                    + " WHERE MAQUYEN=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, ctpq.getMaChucNang());
            pst.setString(2, ctpq.getMaQuyen());
            ketqua = pst.executeUpdate();

            System.out.println("[DaoCTPQ]: " + sql);
            System.out.println("[DaoCTPQ]: " + ketqua + " dong bi thay doi");

            connec.closeConnection(c);

        } catch (SQLException e) {
        }
        return ketqua;
    }

    @Override
    public ArrayList<CHITIETPHANQUYEN> selectAll() {
        ArrayList<CHITIETPHANQUYEN> ketqua = new ArrayList<>();
        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM CHITIETPHANQUYEN";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String maQuyen = rs.getString("MAQUYEN");
                String maChucNang = rs.getString("MACN");

                CHITIETPHANQUYEN a = new CHITIETPHANQUYEN(maQuyen, maChucNang);
                ketqua.add(a);
            }
            connec.closeConnection(c);
        } catch (SQLException e) {
        }
        return ketqua;
    }

    @Override
    public CHITIETPHANQUYEN selectById(CHITIETPHANQUYEN t) {
        CHITIETPHANQUYEN ketqua = null;
        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM CHITIETPHANQUYEN WHERE MAQUYEN=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getMaQuyen());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String maQuyen = rs.getString("MAQUYEN");
                String maChucNang = rs.getString("MACN");

                ketqua = new CHITIETPHANQUYEN(maQuyen, maChucNang);
            }
            connec.closeConnection(c);
        } catch (SQLException e) {
        }
        return ketqua;
    }

    @Override
    public ArrayList<CHITIETPHANQUYEN> selectByCondition(String condition) {
        ArrayList<CHITIETPHANQUYEN> ketqua = new ArrayList<>();
        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM CHITIETPHANQUYEN WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String maQuyen = rs.getString("MAQUYEN");
                String maChucNang = rs.getString("MACN");

                CHITIETPHANQUYEN a = new CHITIETPHANQUYEN(maQuyen, maChucNang);
                ketqua.add(a);
            }
            connec.closeConnection(c);
        } catch (SQLException e) {
        }
        return ketqua;
    }

    public ArrayList<CHITIETPHANQUYEN> selectAllById(String maQuyen) {
        ArrayList<CHITIETPHANQUYEN> ctpq = new ArrayList<>();

        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM CHITIETPHANQUYEN WHERE MAQUYEN=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, maQuyen);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ctpq.add(new CHITIETPHANQUYEN(rs.getString("MAQUYEN"), rs.getString("MACN")));
            }

            connec.closeConnection(c);
            return ctpq;
        } catch (SQLException e) {
        }
        return null;
    }

    @SuppressWarnings("UnusedAssignment")
    public int deleteAllByMaQuyen(String maQuyen) {
        int ketqua = 0;

        try {
            Connection c = connec.getConnection();
            String sql = "DELETE CHITIETPHANQUYEN WHERE MAQUYEN=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, maQuyen);
            ketqua = pst.executeUpdate();

            System.out.println("[DaoCTPQ]: " + sql);
            System.out.println("[DaoCTPQ]: " + ketqua + " dong bi thay doi");

            pst.close();
            connec.closeConnection(c);

        } catch (SQLException e) {
        }

        return ketqua;
    }
}
