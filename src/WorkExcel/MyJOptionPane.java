package WorkExcel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GiaoDienChuan.MyTable;

public class MyJOptionPane extends JOptionPane{
	JComboBox<String> comboBox = new JComboBox<String>(new String[] {"Ghi đè","Bỏ qua"});
	JPanel panel = new JPanel();
	
	public MyJOptionPane(MyTable table, String select) {
		comboBox.setBorder(BorderFactory.createTitledBorder("Hành động"));
		comboBox.setSelectedItem(select);
		table.resizeColumnWidth();
		
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 600));
		panel.add(table,BorderLayout.CENTER);
		panel.add(comboBox, BorderLayout.SOUTH);
		
		
		this.showMessageDialog(null,panel,"Trùng mã", QUESTION_MESSAGE);
	}
	
	public String getAnswer() {
		return comboBox.getSelectedItem().toString();
	}

}
