package BUS;

import DAO.DaoChiTietPhanQuyen;
import DAO.DaoQuyen;
import DTO.CHITIETPHANQUYEN;
import DTO.PHANQUYEN;
import java.util.ArrayList;

/**
 *
 * @author Jhin
 */
public class XoaQuyenBUS {

    private ArrayList<PHANQUYEN> listQuyen;
    private ArrayList<CHITIETPHANQUYEN> listPhanQuyen;
    public PHANQUYEN quyen;

    public XoaQuyenBUS(PHANQUYEN quyen) {
        this.listQuyen = QuyenBUS.getListQuyen();
        this.listPhanQuyen = QuyenBUS.getListPhanQuyen();
        this.quyen = quyen;
        xoaQuyen();
    }

    private void xoaQuyen() {
        xoaList();
        xoaDatabase();
    }

    @SuppressWarnings("element-type-mismatch")
    private void xoaList() {
        /* Xoa data trong listPhanQuyen */
        ArrayList<CHITIETPHANQUYEN> ctpqXoa = new ArrayList<>();
        for (CHITIETPHANQUYEN ctpq : listPhanQuyen) {
            if (quyen.getMaQuyen().equals(ctpq.getMaQuyen())) {
                CHITIETPHANQUYEN xoa = new CHITIETPHANQUYEN(ctpq.getMaQuyen(), ctpq.getMaChucNang());
                ctpqXoa.add(xoa);
            }
        }
        for (CHITIETPHANQUYEN ctpq : ctpqXoa) {
            for (CHITIETPHANQUYEN xoa : listPhanQuyen) {
                if (ctpq.getMaQuyen().equals(xoa.getMaQuyen()) && ctpq.getMaChucNang().equals(xoa.getMaChucNang())) {
                    listPhanQuyen.remove(xoa);
                    break;
                }
            }
        }
        
        /* Xoa data trong listQuyen */
        int index = 0;
        for (PHANQUYEN pq : listQuyen) {
            if (quyen.getMaQuyen().equals(pq.getMaQuyen())) {
                listQuyen.remove(index);
                break;
            }
            index++;
        }
    }

    private void xoaDatabase() {
        /* Xoa Database CHITIETPHANQUYEN */
        DaoChiTietPhanQuyen.getInstance().deleteAllByMaQuyen(quyen.getMaQuyen());
        
        /* Xoa Database QUYEN */
        DaoQuyen.getInstance().delete(quyen);
        
        /* Set lại (TaiKhoanBUS)listQuyen */
        TaiKhoanBUS.setListQuyen(listQuyen);
    }

}
