package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.NHACUNGCAP;

public class DaoNhaCungCap implements DaoInterface<NHACUNGCAP>{
	public static DaoNhaCungCap getInstance() {
		return new DaoNhaCungCap();
	}

	@Override
	public int insert(NHACUNGCAP t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "INSERT INTO NHACUNGCAP(MANCC, TENNCC, SDT, DIACHI, TRANGTHAI) " +
			             " VALUES(?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaNCC());
			pst.setNString(2, t.getTenNCC());
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
	public int delete(NHACUNGCAP t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "DELETE FROM NHACUNGCAP " +
			             " WHERE MANCC=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaNCC());
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
	public int update(NHACUNGCAP t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE KHACHHANG " +
			             " SET TENNCC=?" +
					     ", SDT=?" +
			             ", DIACHI=?" +	
					     ", TRANGTHAI=?" +
			             " WHERE MANCC=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setNString(1, t.getTenNCC());
			pst.setString(2, t.getSdt());
			pst.setNString(3, t.getDiaChi());
			pst.setInt(4, t.getTrangThai());
			pst.setString(5, t.getMaNCC());
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
	public ArrayList<NHACUNGCAP> selectAll() {
		ArrayList<NHACUNGCAP> ketqua = new ArrayList<NHACUNGCAP>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM NHACUNGCAP";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maNCC = rs.getString("MANCC");
				String tenNCC = rs.getNString("TENNCC");
				String sdt = rs.getString("SDT");
				String diaChi = rs.getNString("DIACHI");
				int trangThai = rs.getInt("TRANGTHAI");
				
				
				NHACUNGCAP a = new NHACUNGCAP(maNCC, tenNCC, sdt, diaChi, trangThai);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;	
	}

	@Override
	public NHACUNGCAP selectById(NHACUNGCAP t) {
		NHACUNGCAP ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM NHACUNGCAP WHERE MANCC=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaNCC());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maNCC = rs.getString("MANCC");
				String tenNCC = rs.getNString("TENNCC");
				String sdt = rs.getString("SDT");
				String diaChi = rs.getNString("DIACHI");
				int trangThai = rs.getInt("TRANGTHAI");
				
				
				ketqua = new NHACUNGCAP(maNCC, tenNCC, sdt, diaChi, trangThai);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;	
	}
	
	public String selectByName(String tennnc) {
		NHACUNGCAP ketqua = null;
		String maNCC = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT MANCC FROM NHACUNGCAP WHERE TENNCC=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, tennnc);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				maNCC = rs.getString("MANCC");
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maNCC;	
	}

	@Override
	public ArrayList<NHACUNGCAP> selectByCondition(String condition) {
		ArrayList<NHACUNGCAP> ketqua = new ArrayList<NHACUNGCAP>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM NHACUNGCAP WHERE " + condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maNCC = rs.getString("MANCC");
				String tenNCC = rs.getNString("TENNCC");
				String sdt = rs.getString("SDT");
				String diaChi = rs.getNString("DIACHI");
				int trangThai = rs.getInt("TRANGTHAI");
				
				
				NHACUNGCAP a = new NHACUNGCAP(maNCC, tenNCC, sdt, diaChi, trangThai);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;	
	}
	

}
