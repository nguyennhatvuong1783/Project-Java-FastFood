<<<<<<< HEAD:src/main.java
import DAO.DaoChiTietHoaDon;
import DTO.CHITIETHOADON;
=======
package Main;
>>>>>>> ba08f632d9033675ee40916c819c33adf955be32:src/Main/MAIN.java

public class MAIN {

	public static void main(String[] args) {
		CHITIETHOADON aChitiethoadon = new CHITIETHOADON("HD001", "ABC", 2, "khong co");
		DaoChiTietHoaDon.getInstance().insert(aChitiethoadon);

	}

}
