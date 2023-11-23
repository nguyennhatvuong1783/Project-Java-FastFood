package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.HOADON;
import DTO.THONGKE;

public class DaoHoaDon implements DaoInterface<HOADON>{	
	public static DaoHoaDon getInstance() {
		return new DaoHoaDon();
	}

	@Override
	public int insert(HOADON t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "INSERT INTO HOADON(MAHD, NGAYLAPHD, TONGTIEN, TRANGTHAI, MANV, MAKH, MAKM) " +
			             " VALUES(?,?,?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaHD());
			String ngay = t.getNgayLap();
			Date date = Date.valueOf(ngay);
			pst.setDate(2, date);
			pst.setInt(3, t.getTongTien());
			pst.setInt(4, t.getTrangThai());
			pst.setString(5, t.getMaNV());
			pst.setString(6, t.getMaKH());
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
	public int delete(HOADON t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "DELETE FROM HOADON " +
			             " WHERE MAHD=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaHD());
			
			ketqua = pst.executeUpdate();
			
			System.out.println("Bạn đã thực thi " + sql);
	        System.out.println("Có " + ketqua + " bị thay đổi");
	        
	        connec.closeConnection(c);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;	}

	@Override
	public int update(HOADON t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE HOADON " +
			             " SET NGAYLAPHD=? "+
				         ", TONGTIEN=? "+
			             ", TRANGTHAI=?"+
				         ", MANV=?"+
			             ", MAKH=?"+
				         ", MAKM=?"+
			             " WHERE MAHD=?";
			PreparedStatement pst = c.prepareStatement(sql);
			String ngay = t.getNgayLap();
			Date date = Date.valueOf(ngay);
			pst.setDate(1, date);
			pst.setInt(2, t.getTongTien());
			pst.setInt(3, t.getTrangThai());
			pst.setString(4, t.getMaNV());
			pst.setString(5, t.getMaKH());
			pst.setString(6, t.getMaKM());
			pst.setString(7, t.getMaHD());
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
	public ArrayList<HOADON> selectAll() {
		ArrayList<HOADON> ketqua = new ArrayList<HOADON>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM HOADON";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maHD = rs.getString("MAHD");
				Date date = rs.getDate("NGAYLAPHD");
				String ngayNhap = String.valueOf(date);
				int tongTien = rs.getInt("TONGTIEN");
				int trangThai = rs.getInt("TRANGTHAI");
				String maNV = rs.getString("MANV");
				String maKH = rs.getString("MAKH");
				String maKM = rs.getString("MAKM");
				
				HOADON a = new HOADON(maHD, ngayNhap, tongTien, trangThai, maNV, maKH, maKM, 
						selectTenNVbyMaNV(maNV), selectTenKHbyMaKH(maKH));
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public HOADON selectById(HOADON t) {
		HOADON ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM HOADON WHERE MAHD=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaHD());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maHD = rs.getString("MAHD");
				Date date = rs.getDate("NGAYLAPHD");
				String ngayNhap = String.valueOf(date);
				int tongTien = rs.getInt("TONGTIEN");
				int trangThai = rs.getInt("TRANGTHAI");
				String maNV = rs.getString("MANV");
				String maKH = rs.getString("MAKH");
				String maKM = rs.getString("MAKM");
				
				ketqua = new HOADON(maHD, ngayNhap, tongTien, trangThai, maNV, maKH, maKM, 
						selectTenNVbyMaNV(maNV), selectTenKHbyMaKH(maKH));
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<HOADON> selectByCondition(String condition) {
		ArrayList<HOADON> ketqua = new ArrayList<HOADON>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM HOADON WHERE " + condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maHD = rs.getString("MAHD");
				Date date = rs.getDate("NGAYLAPHD");
				String ngayNhap = String.valueOf(date);
				int tongTien = rs.getInt("TONGTIEN");
				int trangThai = rs.getInt("TRANGTHAI");
				String maNV = rs.getString("MANV");
				String maKH = rs.getString("MAKH");
				String maKM = rs.getString("MAKM");
				
				HOADON a = new HOADON(maHD, ngayNhap, tongTien, trangThai, maNV, maKH, maKM, 
						selectTenNVbyMaNV(maNV), selectTenKHbyMaKH(maKH));
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}
	
	public String selectTenKHbyMaKH(String MaKH) {
		String ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT TENKH FROM KHACHHANG WHERE MAKH=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, MaKH);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				ketqua = rs.getString("TENKH");
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}
	
	public String selectTenNVbyMaNV(String MaNV) {
		String ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT TENNV FROM NHANVIEN WHERE MANV=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, MaNV);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				ketqua = rs.getString("TENNV");
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}
        public ArrayList<THONGKE> getListThongKe() {
        ArrayList<THONGKE> list = new ArrayList<>();

        try {
            Connection c = connec.getConnection1();
            String sql = "select SUM(TONGTIEN), MONTH(NGAYLAPHD), YEAR(NGAYLAPHD)  from HOADON group by MONTH(NGAYLAPHD), YEAR(NGAYLAPHD)";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                THONGKE s = new THONGKE(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
