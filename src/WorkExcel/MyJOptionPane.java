package WorkExcel;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GiaoDienChuan.MyTable;

public class MyJOptionPane extends JOptionPane{
	JComboBox<String> comboBox = new JComboBox<String>(new String[] {"Ghi đè","Bỏ qua","Ghi đè tất cả","Bỏ qua tất cả"});
	JPanel panel = new JPanel();
	
	public MyJOptionPane(MyTable table, String select) {
		comboBox.setBorder(BorderFactory.createTitledBorder("Hành động"));
		comboBox.setSelectedItem(select);
		table.resizeColumnWidth();
		
		panel.setLayout(new BorderLayout());
		panel.add(table,BorderLayout.CENTER);
		panel.add(comboBox, BorderLayout.SOUTH);
		
		this.showMessageDialog(null,panel,"Trùng mã", QUESTION_MESSAGE);
	}
	
	public String getAnswer() {
		return comboBox.getSelectedItem().toString();
	}

}
