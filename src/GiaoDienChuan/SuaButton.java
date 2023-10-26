package GiaoDienChuan;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SuaButton extends JButton{
	public SuaButton()
	{
		this.setText("Sửa");
		this.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8-edit-30.png")));
	}

}
