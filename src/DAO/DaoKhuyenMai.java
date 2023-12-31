package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.KHUYENMAI;

public class DaoKhuyenMai implements DaoInterface<KHUYENMAI> {

    public static DaoKhuyenMai getInstance() {
        return new DaoKhuyenMai();
    }

    @Override
    public int insert(KHUYENMAI t) {
        int ketqua = 0;
        try {
            Connection c = connec.getConnection();
            String sql = "INSERT INTO KHUYENMAI(MAKM, TENKM, DIEUKIEN, GIAMGIA, NGAY_BD, NGAY_KT, TRANGTHAI) "
                    + " VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getMaKM());
            pst.setNString(2, t.getTenKM());
            pst.setNString(3, t.getDieuKienKM());
            pst.setFloat(4, t.getGiamGia());

            pst.setTimestamp(5, new java.sql.Timestamp(t.getNgayBD().getTime()));
            pst.setTimestamp(6, new java.sql.Timestamp(t.getNgayKT().getTime()));
            pst.setInt(7, t.getTrangThai());
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
    public int delete(KHUYENMAI t) {
        int ketqua = 0;
        try {
            Connection c = connec.getConnection();
            String sql = "UPDATE KHUYENMAI "
                    + "SET TRANGTHAI=?"
                    + " WHERE MAKM=?";
            PreparedStatement pst = c.prepareStatement(sql);
             pst.setInt(1, t.getTrangThai());
            pst.setString(2, t.getMaKM());
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
    public int update(KHUYENMAI t) {
        int ketqua = 0;
        try {
            Connection c = connec.getConnection();
            String sql = "UPDATE KHUYENMAI "
                    + " SET TENKM=? "
                    + ", DIEUKIEN=? "
                    + ", GIAMGIA=? "
                    + ", NGAY_BD=?"
                    + ", NGAY_KT=?"
                    + ", TRANGTHAI=? "
                    + " WHERE MAKM=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setNString(1, t.getTenKM());
            pst.setNString(2, t.getDieuKienKM());
            pst.setFloat(3, t.getGiamGia());

            pst.setTimestamp(4, new java.sql.Timestamp(t.getNgayBD().getTime()));
            pst.setTimestamp(5, new java.sql.Timestamp(t.getNgayKT().getTime()));
            pst.setInt(6, t.getTrangThai());
            pst.setString(7, t.getMaKM());

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
    public ArrayList<KHUYENMAI> selectAll() {
        ArrayList<KHUYENMAI> ketqua = new ArrayList<KHUYENMAI>();
        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM KHUYENMAI";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String maKM = rs.getString("MAKM");
                String tenKM = rs.getNString("TENKM");
                String dieuKien = rs.getNString("DIEUKIEN");
                Float giamGia = rs.getFloat("GIAMGIA");
                Date date = rs.getDate("NGAY_BD");
                Date date2 = rs.getDate("NGAY_KT");

                int trangThai = rs.getInt("TRANGTHAI");

                KHUYENMAI a = new KHUYENMAI(maKM, tenKM, dieuKien, giamGia, date, date2, trangThai);
                ketqua.add(a);
            }
            connec.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public KHUYENMAI selectById(KHUYENMAI t) {
        KHUYENMAI ketqua = null;
        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM KHUYENMAI WHERE MAKM=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getMaKM());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String maKM = rs.getString("MAKM");
                String tenKM = rs.getNString("TENKM");
                String dieuKien = rs.getNString("DIEUKIEN");
                Float giamGia = rs.getFloat("GIAMGIA");
                Date date = rs.getDate("NGAY_BD");
                Date date2 = rs.getDate("NGAY_KT");

                int trangThai = rs.getInt("TRANGTHAI");

                ketqua = new KHUYENMAI(maKM, tenKM, dieuKien, giamGia, date, date2, trangThai);
            }
            connec.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public ArrayList<KHUYENMAI> selectByCondition(String condition) {
        ArrayList<KHUYENMAI> ketqua = new ArrayList<KHUYENMAI>();
        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM KHUYENMAI WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String maKM = rs.getString("MAKM");
                String tenKM = rs.getNString("TENKM");
                String dieuKien = rs.getNString("DIEUKIEN");
                Float giamGia = rs.getFloat("GIAMGIA");
                Date date = rs.getDate("NGAY_BD");
                Date date2 = rs.getDate("NGAY_KT");

                int trangThai = rs.getInt("TRANGTHAI");

                KHUYENMAI a = new KHUYENMAI(maKM, tenKM, dieuKien, giamGia, date, date2, trangThai);
                ketqua.add(a);
            }
            connec.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

}
