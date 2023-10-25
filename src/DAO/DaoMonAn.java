package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.CHUCNANG;
import DTO.MONAN;

public class DaoMonAn implements DaoInterface<MONAN>{
	public static DaoMonAn getInstance() {
		return new DaoMonAn();
	}

	@Override
	public int insert(MONAN t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "INSERT INTO MONAN(MAMA, TENMA, SL, DONVITINH, DONGIA, HINHANH, LOAI, TRANGTHAI) " +
			             " VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaMonAn());
			pst.setNString(2, t.getTenMonAn());
			pst.setInt(3, t.getSoLuong());
			pst.setNString(4, t.getDonViTinh());
			pst.setInt(5, t.getDonGia());
			pst.setString(6, t.getHinhAnh());
			pst.setNString(7, t.getLoai());
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
	public int delete(MONAN t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "DELETE FROM MONAN " +
			             " WHERE MAMA=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaMonAn());
			
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
	public int update(MONAN t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE MONAN " +
			             " SET TENMA=? "+
					     ", SL=? "+
			             ", DONVITINH "+
					     ", DONGIA "+
			             ", HINHANH "+
					     ", LOAI "+
			             ", TRANGTHAI "+
					     " WHERE MAMA=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setNString(1, t.getTenMonAn());
			pst.setInt(2, t.getSoLuong());
			pst.setNString(3, t.getDonViTinh());
			pst.setInt(4, t.getDonGia());
			pst.setString(5, t.getHinhAnh());
			pst.setNString(6, t.getLoai());
			pst.setInt(7, t.getTrangThai());
			pst.setString(8, t.getMaMonAn());
			
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
	public ArrayList<MONAN> selectAll() {
		ArrayList<MONAN> ketqua = new ArrayList<MONAN>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM MONAN";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maMA = rs.getString("MAMA");
				String tenMA = rs.getNString("TENMA");
				int soLuong = rs.getInt("SL");
				String donViTinh = rs.getNString("DONVITINH");
				int donGia = rs.getInt("DONGIA");
				String hinhAnh = rs.getString("HINHANH");
				String loai = rs.getNString("LOAI");
				int trangThai = rs.getInt("TRANGTHAI");
				
				MONAN a = new MONAN(maMA, tenMA, soLuong, donViTinh, donGia, hinhAnh, loai, trangThai);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public MONAN selectById(MONAN t) {
		MONAN ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM MONAN WHERE MAMA=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaMonAn());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maMA = rs.getString("MAMA");
				String tenMA = rs.getNString("TENMA");
				int soLuong = rs.getInt("SL");
				String donViTinh = rs.getNString("DONVITINH");
				int donGia = rs.getInt("DONGIA");
				String hinhAnh = rs.getString("HINHANH");
				String loai = rs.getNString("LOAI");
				int trangThai = rs.getInt("TRANGTHAI");
				
				ketqua = new MONAN(maMA, tenMA, soLuong, donViTinh, donGia, hinhAnh, loai, trangThai);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<MONAN> selectByCondition(String condition) {
		ArrayList<MONAN> ketqua = new ArrayList<MONAN>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM MONAN WHERE " + condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maMA = rs.getString("MAMA");
				String tenMA = rs.getNString("TENMA");
				int soLuong = rs.getInt("SL");
				String donViTinh = rs.getNString("DONVITINH");
				int donGia = rs.getInt("DONGIA");
				String hinhAnh = rs.getString("HINHANH");
				String loai = rs.getNString("LOAI");
				int trangThai = rs.getInt("TRANGTHAI");
				
				MONAN a = new MONAN(maMA, tenMA, soLuong, donViTinh, donGia, hinhAnh, loai, trangThai);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}
	
}
