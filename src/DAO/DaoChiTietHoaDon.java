package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.CHITIETHOADON;
import DTO.HOADON;

public class DaoChiTietHoaDon implements DaoInterface<CHITIETHOADON> {
	
	public static DaoChiTietHoaDon getInstance() {
		return new DaoChiTietHoaDon();
	}

	@Override
	public int insert(CHITIETHOADON t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "INSERT INTO CHITIETHOADON(MAHD, MAMA, SL, GHICHU) " +
			             " VALUES(?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaHD());
			pst.setString(2, t.getMaMonAn());
			pst.setInt(3, t.getSoLuong());
			pst.setNString(4, t.getGhiChu());
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
	public int delete(CHITIETHOADON t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "DELETE FROM CHITIETHOADON WHERE MAHD=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaHD());
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
	public int update(CHITIETHOADON t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE CHITIETHOADON "
					    + " SET MAMA=?" 
					    + ", SL=?"
					    +", GHICHU=?"
					    + " WHERE MAHD=?";                    
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1,t.getMaMonAn());
			pst.setInt(2, t.getSoLuong());
			pst.setNString(3, t.getGhiChu());
			pst.setString(4, t.getMaHD());
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
	public ArrayList<CHITIETHOADON> selectAll() {
		ArrayList<CHITIETHOADON> ketqua = new ArrayList<CHITIETHOADON>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHITIETHOADON";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maHD = rs.getString("MAHD");
				String maMA = rs.getString("MAMA");
				int soLuong = rs.getInt("SL");
				String ghiChu = rs.getNString("GHICHU");
				
				CHITIETHOADON a = new CHITIETHOADON(maHD, maMA, soLuong, ghiChu);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public CHITIETHOADON selectById(CHITIETHOADON t) {
		CHITIETHOADON ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHITIETHOADON "
					    + " WHERE MAHD=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaHD());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maHD = rs.getString("MAHD");
				String maMA = rs.getString("MAMA");
				int soLuong = rs.getInt("SL");
				String ghiChu = rs.getNString("GHICHU");
				
				ketqua = new CHITIETHOADON(maHD, maMA, soLuong, ghiChu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<CHITIETHOADON> selectByCondition(String condition) {
	    ArrayList<CHITIETHOADON> ketqua = new ArrayList<CHITIETHOADON>();
	    try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHITIETHOADON WHERE " + condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maHD = rs.getString("MAHD");
				String maMA = rs.getString("MAMA");
				int soLuong = rs.getInt("SL");
				String ghiChu = rs.getNString("GHICHU");
				
				CHITIETHOADON a = new CHITIETHOADON(maHD, maMA, soLuong, ghiChu);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
