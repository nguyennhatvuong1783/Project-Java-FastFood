package GiaoDienChuan;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FileButton extends JButton{
	public FileButton() {
		this.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8_open_folder_20px.png")));
	}

}
