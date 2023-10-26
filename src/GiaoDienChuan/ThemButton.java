package GiaoDienChuan;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ThemButton extends JButton{
	public ThemButton() {
		this.setText("Thêm");
		this.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8-add-30.png")));
	}

}
