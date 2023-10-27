package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.CHITIETMONAN;

public class DaoChiTietMonAn implements DaoInterface<CHITIETMONAN>{
	public static DaoChiTietMonAn getInstance() {
		return new DaoChiTietMonAn();
	}

	@Override
	public int insert(CHITIETMONAN t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "INSERT INTO CHITIETMONAN(MAMA, MANL, SLNL) " +
			             " VALUES(?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaMonAn());
			pst.setString(2, t.getMaNguyenLieu());
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
	public int delete(CHITIETMONAN t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "DELETE FROM CHITIETMONAN WHERE MAMA=?";
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
	public int update(CHITIETMONAN t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE CHITIETMONAN "
					    + " SET MANL=?" 
					    + ", SLNL=?"
					    + " WHERE MAMA=?";                    
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1,t.getMaNguyenLieu());
			pst.setInt(2, t.getSoLuong());
			pst.setNString(3, t.getMaMonAn());
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
	public ArrayList<CHITIETMONAN> selectAll() {
		ArrayList<CHITIETMONAN> ketqua = new ArrayList<CHITIETMONAN>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHITIETHOADON";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maMA = rs.getString("MAMA");
				String maNL = rs.getNString("MANL");
				int soLuong = rs.getInt("SLNL");
							
				CHITIETMONAN a = new CHITIETMONAN(maMA,maNL, soLuong);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public CHITIETMONAN selectById(CHITIETMONAN t) {
	    CHITIETMONAN ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHITIETHOADON WHERE MAMA=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaMonAn());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maMA = rs.getString("MAMA");
				String maNL = rs.getNString("MANL");
				int soLuong = rs.getInt("SLNL");
							
				ketqua = new CHITIETMONAN(maMA,maNL, soLuong);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<CHITIETMONAN> selectByCondition(String condition) {
		ArrayList<CHITIETMONAN> ketqua = new ArrayList<CHITIETMONAN>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHITIETHOADON WHERE " + condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maMA = rs.getString("MAMA");
				String maNL = rs.getNString("MANL");
				int soLuong = rs.getInt("SLNL");
							
				CHITIETMONAN a = new CHITIETMONAN(maMA,maNL, soLuong);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

}
