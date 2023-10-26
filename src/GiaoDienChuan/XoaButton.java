package GiaoDienChuan;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class XoaButton extends JButton{
	public XoaButton()
	{
		this.setText("Xoá");
		this.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8-delete-30.png")));
	}

}
