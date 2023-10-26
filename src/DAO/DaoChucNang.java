package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.CHITIETHOADON;
import DTO.CHUCNANG;

public class DaoChucNang implements DaoInterface<CHUCNANG>{
	public static DaoChucNang getInstance() {
		return new DaoChucNang();
	}

	@Override
	public int insert(CHUCNANG t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "INSERT INTO CHUCNANG(MACN, TENCN, MOTA) " +
			             " VALUES(?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaChucNang());
			pst.setString(2, t.getTenChucNang());
			pst.setNString(3, t.getMoTa());
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
	public int delete(CHUCNANG t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "DELETE FROM CHUCNANG " +
			             " WHERE MACN=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaChucNang());
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
	public int update(CHUCNANG t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE CHUCNANG " +
			             " SET TENCN=?"
					    +", MOTA=?"
			             +" WHERE MACN=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getTenChucNang());
			pst.setNString(2, t.getMoTa());
			pst.setString(3, t.getMaChucNang());
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
	public ArrayList<CHUCNANG> selectAll() {
		ArrayList<CHUCNANG> ketqua = new ArrayList<CHUCNANG>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHUCNANG";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maCN = rs.getString("MACN");
				String tenCN = rs.getString("TENCN");
				String moTa = rs.getNString("MOTA");
				
				CHUCNANG a = new CHUCNANG(maCN, tenCN, moTa);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public CHUCNANG selectById(CHUCNANG t) {
		CHUCNANG ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHUCNANG WHERE MACN=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaChucNang());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maCN = rs.getString("MACN");
				String tenCN = rs.getString("TENCN");
				String moTa = rs.getNString("MOTA");
				
				ketqua = new CHUCNANG(maCN, tenCN, moTa);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<CHUCNANG> selectByCondition(String condition) {
		ArrayList<CHUCNANG> ketqua = new ArrayList<CHUCNANG>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM CHUCNANG WHERE " + condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maCN = rs.getString("MACN");
				String tenCN = rs.getString("TENCN");
				String moTa = rs.getNString("MOTA");
				
				CHUCNANG a = new CHUCNANG(maCN, tenCN, moTa);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

}
