package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.connec;
import DTO.NGUYENLIEU;
import DTO.PHIEUNHAP;

public class DaoPhieuNhap implements DaoInterface<PHIEUNHAP>{
	public static DaoPhieuNhap getInstance() {
		return new DaoPhieuNhap();
	}

	@Override
	public int insert(PHIEUNHAP t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "INSERT INTO PHIEUNHAP(MAPN, NGAYNHAP, TONGTIEN, MANV, MANCC) " +
			             " VALUES(?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaPN());
			String ngay = t.getNgayNhap();
			Date date = Date.valueOf(ngay);
			pst.setDate(2, date);
			pst.setInt(3, t.getTongTien());
			pst.setString(4, t.getMaNV());
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
	public int delete(PHIEUNHAP t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "DELETE FROM PHIEUNHAP " +
			             " WHERE MAPN=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaPN());
			
			System.out.println("Bạn đã thực thi " + sql);
	        System.out.println("Có " + ketqua + " bị thay đổi");
	        
	        connec.closeConnection(c);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int update(PHIEUNHAP t) {
		int ketqua = 0;
		try {
			Connection c = connec.getConnection();
			String sql = "UPDATE HOADON " +
			             " SET NGAYNHAP=? "+
				         ", TONGTIEN=? "+
				         ", MANV=?"+
			             ", MANCC=?"+
			             " WHERE MAPN=?";
			PreparedStatement pst = c.prepareStatement(sql);
			String ngay = t.getNgayNhap();
			Date date = Date.valueOf(ngay);
			pst.setDate(1, date);
			pst.setInt(2, t.getTongTien());
			pst.setString(3, t.getMaNV());
			pst.setString(4, t.getMaNCC());
			pst.setString(5, t.getMaPN());
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
	public ArrayList<PHIEUNHAP> selectAll() {
		ArrayList<PHIEUNHAP> ketqua = new ArrayList<PHIEUNHAP>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM PHIEUNHAP";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maPN = rs.getString("MAPN");
				Date date = rs.getDate("NGAYNHAP");
				String ngayNhap = String.valueOf(date);
				int tongTien = rs.getInt("TONGTIEN");
				String maNV = rs.getString("MANV");
				String maNCC = rs.getString("MANCC");
				
				PHIEUNHAP a = new PHIEUNHAP(maPN, ngayNhap, tongTien, maNV, maNCC);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public PHIEUNHAP selectById(PHIEUNHAP t) {
		PHIEUNHAP ketqua = null;
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM PHIEUNHAP WHERE MAPN=?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaPN());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maPN = rs.getString("MAPN");
				Date date = rs.getDate("NGAYNHAP");
				String ngayNhap = String.valueOf(date);
				int tongTien = rs.getInt("TONGTIEN");
				String maNV = rs.getString("MANV");
				String maNCC = rs.getString("MANCC");
				
				ketqua = new PHIEUNHAP(maPN, ngayNhap, tongTien, maNV, maNCC);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<PHIEUNHAP> selectByCondition(String condition) {
		ArrayList<PHIEUNHAP> ketqua = new ArrayList<PHIEUNHAP>();
		try {
			Connection c = connec.getConnection();
			String sql = "SELECT * FROM PHIEUNHAP WHERE " + condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String maPN = rs.getString("MAPN");
				Date date = rs.getDate("NGAYNHAP");
				String ngayNhap = String.valueOf(date);
				int tongTien = rs.getInt("TONGTIEN");
				String maNV = rs.getString("MANV");
				String maNCC = rs.getString("MANCC");
				
				PHIEUNHAP a = new PHIEUNHAP(maPN, ngayNhap, tongTien, maNV, maNCC);
				ketqua.add(a);
			}
			connec.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int updateSL(String manl, int sl) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
