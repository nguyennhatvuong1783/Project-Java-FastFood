package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Connection.connec;
import DTO.TAIKHOAN;

public class DaoTaiKhoan implements DaoInterface<TAIKHOAN> {

    public static DaoTaiKhoan getInstance() {
        return new DaoTaiKhoan();
    }

    @Override
    public int insert(TAIKHOAN t) {
        int ketqua = 0;
        try {
            Connection c = connec.getConnection();
            String sql = "INSERT INTO TAIKHOAN(USERNAME, MATKHAU, TRANGTHAI, MAQUYEN) "
                    + " VALUES(?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getUserName());
            pst.setString(2, t.getMatKhau());
            pst.setInt(3, t.getTrangThai());
            pst.setString(4, t.getMaQuyen());
            ketqua = pst.executeUpdate();

            System.out.println("[DaoTaiKhoan]: " + sql);
            System.out.println("[DaoTaiKhoan]: " + ketqua + " dong bi thay doi");

            connec.closeConnection(c);
        } catch (SQLException e) {
        }
        return ketqua;
    }

    @Override
    public int delete(TAIKHOAN t) {
        int ketqua = 0;
        try {
            Connection c = connec.getConnection();
            String sql = "DELETE FROM TAIKHOAN "
                    + " WHERE USERNAME=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getUserName());
            ketqua = pst.executeUpdate();

            System.out.println("[DaoTaiKhoan]: " + sql);
            System.out.println("[DaoTaiKhoan]: " + ketqua + " dong bi thay doi");

            connec.closeConnection(c);
        } catch (SQLException e) {
        }
        return ketqua;
    }

    @Override
    public int update(TAIKHOAN t) {
        int ketqua = 0;
        try {
            Connection c = connec.getConnection();
            String sql = "UPDATE TAIKHOAN "
                    + " SET MATKHAU=?"
                    + ", MAQUYEN=?"
                    + " WHERE USERNAME=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getMatKhau());
            pst.setString(2, t.getMaQuyen());
            pst.setString(3, t.getUserName());
            ketqua = pst.executeUpdate();

            System.out.println("[DaoTaiKhoan]: " + sql);
            System.out.println("[DaoTaiKhoan]: " + ketqua + " dong bi thay doi");

            connec.closeConnection(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public ArrayList<TAIKHOAN> selectAll() {
        ArrayList<TAIKHOAN> ketqua = new ArrayList<>();
        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM TAIKHOAN";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                String userName = rs.getString("USERNAME");
                String matKhau = rs.getString("MATKHAU");
                int trangThai = rs.getInt("TRANGTHAI");
                String maQuyen = rs.getString("MAQUYEN");

                TAIKHOAN a = new TAIKHOAN(userName, matKhau, trangThai, maQuyen);
                ketqua.add(a);
            }
            connec.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public TAIKHOAN selectById(TAIKHOAN t) {
        TAIKHOAN ketqua = null;
        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM TAIKHOAN WHERE USERNAME=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getUserName());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String userName = rs.getString("USERNAME");
                String matKhau = rs.getString("MATKHAU");
                int trangThai = rs.getInt("TRANGTHAI");
                String maQuyen = rs.getString("MAQUYEN");

                ketqua = new TAIKHOAN(userName, matKhau, trangThai, maQuyen);
            }
            connec.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public ArrayList<TAIKHOAN> selectByCondition(String condition) {
        ArrayList<TAIKHOAN> ketqua = new ArrayList<TAIKHOAN>();
        try {
            Connection c = connec.getConnection();
            String sql = "SELECT * FROM TAIKHOAN WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String userName = rs.getString("USERNAME");
                String matKhau = rs.getString("MATKHAU");
                int trangThai = rs.getInt("TRANGTHAI");
                String maQuyen = rs.getString("MAQUYEN");

                TAIKHOAN a = new TAIKHOAN(userName, matKhau, trangThai, maQuyen);
                ketqua.add(a);
            }
            connec.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

}
