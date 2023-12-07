package Lab10;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isClicked;
    private int row;
    private DefaultTableModel tableModel;

    public ButtonEditor(JCheckBox checkBox, DefaultTableModel tableModel) {
        super(checkBox);
        this.tableModel = tableModel;

        button = new JButton();
        button.setOpaque(true);

        button.addActionListener(e -> {
            fireEditingStopped();
            if (isClicked) {
                deleteRow(row);
                isClicked = false;
            }
        });
    }

    private void deleteRow(int row) {
        tableModel.removeRow(row);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isClicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isClicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
