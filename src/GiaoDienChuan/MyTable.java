package GiaoDienChuan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class MyTable extends JPanel{
	
	JTable table;
	JScrollPane pane;
	DefaultTableModel model;
	
	public MyTable() {
		setLayout(new BorderLayout());
		
		table = new JTable();
		model = new DefaultTableModel() {
				 @Override
			     public boolean isCellEditable(int row, int column) {
			        return false;
			     }
		};
		pane = new JScrollPane(table);
		pane.getVerticalScrollBar().setUnitIncrement(8);
		
		table.setPreferredScrollableViewportSize(new Dimension(0, 500));
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		//Color
		table.getTableHeader().setBackground(new Color(255,165,0));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		table.setForeground(new Color(0, 0, 0));
		
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		add(pane,BorderLayout.CENTER);
		
	}
	
	public void resizeColumnWidth() {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }

            width += 15;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
	
     

	
	public void setHeaders(String[] header) {
		model.setColumnIdentifiers(header);
		table.setModel(model);
	}
	
	public void setHeaders(ArrayList<String> header) {
		model.setColumnIdentifiers(header.toArray());
		table.setModel(model);
	}
	
	public void addRow(String[] data) {
		model.addRow(data);
	}
	
	public JTable getTable() {
		return table;
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
	
	public void clear() {
		model.setRowCount(0);
	}
	
	
	public String getValueAt(int row, int col) {
		return String.valueOf(table.getValueAt(row, col));
	}
	

}
