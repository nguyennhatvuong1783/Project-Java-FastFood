package GiaoDienChuan;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImportExcelButton extends JButton {
	public ImportExcelButton() {
		this.setText("Nhập Excel");
		this.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8_ms_excel_30px.png")));
	}
	

}
