package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.NHANVIEN;

public class DaoNhanVien implements DaoInterface<NHANVIEN>{
	public static DaoNhanVien getInstance() {
		return new DaoNhanVien();
	}

	@Override
	public int insert(NHANVIEN t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "INSERT INTO NHANVIEN(MANV, TENNV, GIOITINH, NGAYSINH, DIACHI, SDT, TRANGTHAI) " +
			             " VALUES(?,?,?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaNV());
			pst.setNString(2, t.getTenNV());
			pst.setNString(3, t.getGioiTinh());
			String ngayString = t.getNgaySinh();
			Date date = Date.valueOf(ngayString);
			pst.setDate(4, date);
			pst.setNString(5, t.getDiaChi());
			pst.setString(6, t.getSdt());
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
	public int delete(NHANVIEN t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "DELETE FROM NHANVIEN " +
			             " WHERE MANV=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaNV());
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
	public int update(NHANVIEN t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE KHACHHANG " +
			             " SET TENNV=?" +
					     ", GIOITINH=? "+
			             ", NGAYSINH=?"+
			             ", DIACHI=?" +	
			             ", SDT=?" +
					     ", TRANGTHAI=?" +
			             " WHERE MANV=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setNString(1, t.getTenNV());
			pst.setNString(2, t.getGioiTinh());
			String ngayString = t.getNgaySinh();
			Date date = Date.valueOf(ngayString);
			pst.setDate(3, date);
			pst.setNString(4, t.getDiaChi());
			pst.setString(5, t.getSdt());
			pst.setInt(6, t.getTrangThai());
			pst.setString(7, t.getMaNV());
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
	public ArrayList<NHANVIEN> selectAll() {
		ArrayList<NHANVIEN> ketqua = new ArrayList<NHANVIEN>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM NHANVIEN";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maNV = rs.getString("MANV");
				String tenNV = rs.getNString("TENNV"); 
				String gioiTinh = rs.getNString("GIOITINH");
				Date date = rs.getDate("NGAYSINH");
				String ngaySinh = String.valueOf(date);
				String diaChi = rs.getNString("DIACHI");
				String sdt = rs.getString("SDT");
				int trangThai = rs.getInt("TRANGTHAI");
				
				NHANVIEN a = new NHANVIEN(maNV, tenNV, gioiTinh, ngaySinh, diaChi, sdt, trangThai);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;	
	}

	@Override
	public NHANVIEN selectById(NHANVIEN t) {
		NHANVIEN ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM NHANVIEN WHERE MANV=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaNV());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maNV = rs.getString("MANV");
				String tenNV = rs.getNString("TENNV"); 
				String gioiTinh = rs.getNString("GIOITINH");
				Date date = rs.getDate("NGAYSINH");
				String ngaySinh = String.valueOf(date);
				String diaChi = rs.getNString("DIACHI");
				String sdt = rs.getString("SDT");
				int trangThai = rs.getInt("TRANGTHAI");
				
			    ketqua = new NHANVIEN(maNV, tenNV, gioiTinh, ngaySinh, diaChi, sdt, trangThai);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;	
	}

	@Override
	public ArrayList<NHANVIEN> selectByCondition(String condition) {
		ArrayList<NHANVIEN> ketqua = new ArrayList<NHANVIEN>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM NHANVIEN WHERE " + condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maNV = rs.getString("MANV");
				String tenNV = rs.getNString("TENNV"); 
				String gioiTinh = rs.getNString("GIOITINH");
				Date date = rs.getDate("NGAYSINH");
				String ngaySinh = String.valueOf(date);
				String diaChi = rs.getNString("DIACHI");
				String sdt = rs.getString("SDT");
				int trangThai = rs.getInt("TRANGTHAI");
				
				NHANVIEN a = new NHANVIEN(maNV, tenNV, gioiTinh, ngaySinh, diaChi, sdt, trangThai);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;	
	}
	

}
