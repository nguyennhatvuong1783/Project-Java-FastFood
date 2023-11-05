package GiaoDienChuan;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RefreshButton extends JButton {

    public RefreshButton() {
        this.setText("Làm mới");
        this.setIcon(new ImageIcon(getClass().getResource("/icon_img/icons8-refresh-30.png")));
    }

}
