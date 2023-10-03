package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class THEMButton extends JButton{
	public  THEMButton() {
		this.setText("THÊM");
		this.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8-add-64.png")));
	}

}
