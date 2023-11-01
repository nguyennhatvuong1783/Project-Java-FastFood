package GiaoDienChuan;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CancelButton extends JButton{
	public CancelButton() {
		this.setText("Hủy");
		this.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8-cancel-30.png")));
	}

}
