package BUS;

import DAO.DaoTaiKhoan;
import DTO.TAIKHOAN;
import java.util.ArrayList;

/**
 *
 * @author Jhin
 */
public class XoaTaiKhoanBUS {

    private ArrayList<TAIKHOAN> listTaiKhoan;
    private TAIKHOAN taiKhoanXoa;

    public XoaTaiKhoanBUS(ArrayList<TAIKHOAN> listTaiKhoan, TAIKHOAN taiKhoanXoa) {
        this.listTaiKhoan = listTaiKhoan;
        this.taiKhoanXoa = taiKhoanXoa;
        xoaTaiKhoan();
    }

    private void xoaTaiKhoan() {
        xoaList();
        xoaDatabase();
    }

    private void xoaList() {
        int index = 0;
        
        for (TAIKHOAN tk : listTaiKhoan) {
            if (taiKhoanXoa.getUserName().equals(tk.getUserName())) {
                listTaiKhoan.remove(index);
                break;
            }
            index++;
        }
    }

    private void xoaDatabase() {
        DaoTaiKhoan.getInstance().delete(taiKhoanXoa);
    }

}
