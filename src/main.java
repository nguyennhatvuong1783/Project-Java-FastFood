import DAO.DaoChiTietHoaDon;
import DTO.CHITIETHOADON;

public class main {

	public static void main(String[] args) {
		CHITIETHOADON aChitiethoadon = new CHITIETHOADON("HD001", "ABC", 2, "khong co");
		DaoChiTietHoaDon.getInstance().insert(aChitiethoadon);

	}

}
