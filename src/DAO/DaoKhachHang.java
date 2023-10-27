package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.KHACHHANG;

public class DaoKhachHang implements DaoInterface<KHACHHANG>{
	public static DaoKhachHang getInstance() {
		return new DaoKhachHang();
	}

	@Override
	public int insert(KHACHHANG t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "INSERT INTO KHACHHANG(MAKH, TENKH, SDT, DIACHI, TRANGTHAI) " +
			             " VALUES(?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaKH());
			pst.setNString(2, t.getTenKH());
			pst.setString(3, t.getSdt());
			pst.setNString(4, t.getDiaChi());
			pst.setInt(5, t.getTrangThai());
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
	public int delete(KHACHHANG t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "DELETE FROM KHACHHANG " +
			             " WHERE MAKH=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaKH());
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
	public int update(KHACHHANG t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE KHACHHANG " +
			             " SET TENKH=?" +
					     ", SDT=?" +
			             ", DIACHI=?" +	
					     ", TRANGTHAI=?" +
			             " WHERE MAKH=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setNString(1, t.getTenKH());
			pst.setString(2, t.getSdt());
			pst.setNString(3, t.getDiaChi());
			pst.setInt(4, t.getTrangThai());
			pst.setString(5, t.getMaKH());
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
	public ArrayList<KHACHHANG> selectAll() {
		ArrayList<KHACHHANG> ketqua = new ArrayList<KHACHHANG>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM KHACHHANG";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maKH = rs.getString("MAKH");
				String tenKH = rs.getNString("TENKH");
				String sdt = rs.getString("SDT");
				String diaChi = rs.getNString("DIACHI");
				int trangThai = rs.getInt("TRANGTHAI");
				
				KHACHHANG a = new KHACHHANG(maKH, tenKH, sdt, diaChi, trangThai);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;	
	}

	@Override
	public KHACHHANG selectById(KHACHHANG t) {
		KHACHHANG ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM KHACHHANG WHERE MAKH=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaKH());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maKH = rs.getString("MAKH");
				String tenKH = rs.getNString("TENKH");
				String sdt = rs.getString("SDT");
				String diaChi = rs.getNString("DIACHI");
				int trangThai = rs.getInt("TRANGTHAI");
				
				ketqua = new KHACHHANG(maKH, tenKH, sdt, diaChi, trangThai);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<KHACHHANG> selectByCondition(String condition) {
		ArrayList<KHACHHANG> ketqua = new ArrayList<KHACHHANG>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM KHACHHANG WHERE " + condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maKH = rs.getString("MAKH");
				String tenKH = rs.getNString("TENKH");
				String sdt = rs.getString("SDT");
				String diaChi = rs.getNString("DIACHI");
				int trangThai = rs.getInt("TRANGTHAI");
				
				KHACHHANG a = new KHACHHANG(maKH, tenKH, sdt, diaChi, trangThai);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;	
	}
	

}
