package GiaoDienChuan;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MoreButton extends JButton {
	public MoreButton() {
		this.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8-more-20.png")));
		this.setPreferredSize(new Dimension(20, 20));
	}

}
