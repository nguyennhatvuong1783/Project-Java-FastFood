package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.CHITIETPHIEUNHAP;

public class DaoChiTietPhieuNhap implements DaoInterface<CHITIETPHIEUNHAP>{
	public static DaoChiTietPhieuNhap getInstance() {
		return new DaoChiTietPhieuNhap();
	}

	@Override
	public int insert(CHITIETPHIEUNHAP t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "INSERT INTO CHITIETPHIEUNHAP(MAPN, MANL, SL) " +
			             " VALUES(?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaPN());
			pst.setString(2, t.getMaNL());
			pst.setInt(3, t.getSoLuong());
			
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
	public int delete(CHITIETPHIEUNHAP t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "DELETE * FROM CHITIETPHIEUNHAP " +
			             " WHERE MAPN=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaPN());
			
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
	public int update(CHITIETPHIEUNHAP t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE CHITIETPHIEUNHAP " +
			             " SET MANL=?"
					    +", SL=?"
			            +" WHERE MAPN=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaNL());
			pst.setInt(2, t.getSoLuong());
			pst.setString(3, t.getMaPN());
			
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
	public ArrayList<CHITIETPHIEUNHAP> selectAll() {
		ArrayList<CHITIETPHIEUNHAP> ketqua = new ArrayList<CHITIETPHIEUNHAP>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHITIETPHIEUNHAP";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maPN = rs.getString("MAPN");
				String maNL = rs.getString("MANL");
				int soLuong = rs.getInt("SL");
				
				
				CHITIETPHIEUNHAP a = new CHITIETPHIEUNHAP(maPN, maNL, soLuong);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public CHITIETPHIEUNHAP selectById(CHITIETPHIEUNHAP t) {
		CHITIETPHIEUNHAP ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHITIETPHIEUNHAP WHERE MAPN=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaPN());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maPN = rs.getString("MAPN");
				String maNL = rs.getString("MANL");
				int soLuong = rs.getInt("SL");
				
				
				ketqua = new CHITIETPHIEUNHAP(maPN, maNL, soLuong);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<CHITIETPHIEUNHAP> selectByCondition(String condition) {
		ArrayList<CHITIETPHIEUNHAP> ketqua = new ArrayList<CHITIETPHIEUNHAP>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHITIETPHIEUNHAP WHERE " + condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maPN = rs.getString("MAPN");
				String maNL = rs.getString("MANL");
				int soLuong = rs.getInt("SL");
				
				
				CHITIETPHIEUNHAP a = new CHITIETPHIEUNHAP(maPN, maNL, soLuong);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

}
