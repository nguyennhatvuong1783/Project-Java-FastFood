package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.CHITIETHOADON;
import DTO.CHITIETPHANQUYEN;

public class DaoChiTietPhanQuyen implements DaoInterface<CHITIETPHANQUYEN>{
	public static DaoChiTietPhanQuyen getInstance() {
		return new DaoChiTietPhanQuyen();
	}

	@Override
	public int insert(CHITIETPHANQUYEN t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "INSERT INTO CHITIETPHANQUYEN(MAQUYEN, MACN) " +
			             " VALUES(?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaQuyen());
			pst.setString(2, t.getMaChucNang());
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
	public int delete(CHITIETPHANQUYEN t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "DELETE FROM CHITIETPHANQUYEN WHERE MAQUYEN=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaQuyen());
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
	public int update(CHITIETPHANQUYEN t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE CHITIETPHANQUYEN "
					    +" SET MACN=?"
			            +" WHERE MAQUYEN=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaChucNang());
			pst.setString(2, t.getMaQuyen());
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
	public ArrayList<CHITIETPHANQUYEN> selectAll() {
		ArrayList<CHITIETPHANQUYEN> ketqua = new ArrayList<CHITIETPHANQUYEN>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHITIETPHANQUYEN";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maQuyen = rs.getString("MAQUYEN");
				String maChucNang = rs.getString("MACN");
				
				CHITIETPHANQUYEN a = new CHITIETPHANQUYEN(maQuyen, maChucNang);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public CHITIETPHANQUYEN selectById(CHITIETPHANQUYEN t) {
		CHITIETPHANQUYEN ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHITIETPHANQUYEN WHERE MAQUYEN=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaQuyen());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maQuyen = rs.getString("MAQUYEN");
				String maChucNang = rs.getString("MACN");
				
				ketqua = new CHITIETPHANQUYEN(maQuyen, maChucNang);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<CHITIETPHANQUYEN> selectByCondition(String condition) {
		ArrayList<CHITIETPHANQUYEN> ketqua = new ArrayList<CHITIETPHANQUYEN>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHITIETPHANQUYEN WHERE " + condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maQuyen = rs.getString("MAQUYEN");
				String maChucNang = rs.getString("MACN");
				
				CHITIETPHANQUYEN a = new CHITIETPHANQUYEN(maQuyen, maChucNang);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

}
