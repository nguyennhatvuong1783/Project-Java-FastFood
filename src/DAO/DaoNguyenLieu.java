package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.NGUYENLIEU;

public class DaoNguyenLieu implements DaoInterface<NGUYENLIEU>{
	public static DaoNguyenLieu getInstance() {
		return new DaoNguyenLieu();
	}

	@Override
	public int insert(NGUYENLIEU t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "INSERT INTO NGUYENLIEU(MANL, TENNL, SL, DONVITINH, DONGIA, HINHANH, LOAI, TRANGTHAI) " +
			             " VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaNL());
			pst.setNString(2, t.getTenNL());
			pst.setInt(3, t.getSoLuong());
			pst.setNString(4, t.getDonViTinh());
			pst.setInt(5, t.getDonGia());
			pst.setString(6, t.getHinhAnh());
			pst.setNString(7, t.getLoaiNL());
			pst.setInt(8, t.getTrangThai());
			
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
	public int delete(NGUYENLIEU t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "DELETE FROM NGUYENLIEU " +
			             " WHERE MANL=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaNL());
			
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
	public int update(NGUYENLIEU t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE NGUYENLIEU " +
			             " SET TENNL=? "+
					     ", SL=? "+
			             ", DONVITINH "+
					     ", DONGIA "+
			             ", HINHANH "+
					     ", LOAI "+
			             ", TRANGTHAI "+
					     " WHERE MANL=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setNString(1, t.getTenNL());
			pst.setInt(2, t.getSoLuong());
			pst.setNString(3, t.getDonViTinh());
			pst.setInt(4, t.getDonGia());
			pst.setString(5, t.getHinhAnh());
			pst.setNString(6, t.getLoaiNL());
			pst.setInt(7, t.getTrangThai());
			pst.setString(8, t.getMaNL());
			
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
	public int updateSL(String manl, int sl) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE NGUYENLIEU " +
					     ", SL=? "+
					     " WHERE MANL=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(2, sl);
			
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
	public ArrayList<NGUYENLIEU> selectAll() {
		ArrayList<NGUYENLIEU> ketqua = new ArrayList<NGUYENLIEU>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM NGUYENLIEU";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maNL = rs.getString("MANL");
				String tenNL = rs.getNString("TENNL");
				int soLuong = rs.getInt("SL");
				String donViTinh = rs.getNString("DONVITINH");
				int donGia = rs.getInt("DONGIA");
				String hinhAnh = rs.getString("HINHANH");
				String loai = rs.getNString("LOAI");
				int trangThai = rs.getInt("TRANGTHAI");
				
			    NGUYENLIEU a = new NGUYENLIEU(maNL, tenNL, soLuong, donViTinh, donGia, hinhAnh, loai, trangThai);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public NGUYENLIEU selectById(NGUYENLIEU t) {
		NGUYENLIEU ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM NGUYENLIEU WHERE MANL=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaNL());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maNL = rs.getString("MANL");
				String tenNL = rs.getNString("TENNL");
				int soLuong = rs.getInt("SL");
				String donViTinh = rs.getNString("DONVITINH");
				int donGia = rs.getInt("DONGIA");
				String hinhAnh = rs.getString("HINHANH");
				String loai = rs.getNString("LOAI");
				int trangThai = rs.getInt("TRANGTHAI");
				
			  ketqua = new NGUYENLIEU(maNL, tenNL, soLuong, donViTinh, donGia, hinhAnh, loai, trangThai);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<NGUYENLIEU> selectByCondition(String condition) {
		ArrayList<NGUYENLIEU> ketqua = new ArrayList<NGUYENLIEU>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM NGUYENLIEU WHERE " + condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maNL = rs.getString("MANL");
				String tenNL = rs.getNString("TENNL");
				int soLuong = rs.getInt("SL");
				String donViTinh = rs.getNString("DONVITINH");
				int donGia = rs.getInt("DONGIA");
				String hinhAnh = rs.getString("HINHANH");
				String loai = rs.getNString("LOAI");
				int trangThai = rs.getInt("TRANGTHAI");
				
			    NGUYENLIEU a = new NGUYENLIEU(maNL, tenNL, soLuong, donViTinh, donGia, hinhAnh, loai, trangThai);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}


}
