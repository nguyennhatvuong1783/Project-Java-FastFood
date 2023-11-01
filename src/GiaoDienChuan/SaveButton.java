package GiaoDienChuan;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SaveButton extends JButton{
	public SaveButton() {
		this.setText("Lưu");
		this.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8-save-30.png")));
	}

}
