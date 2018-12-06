package view.dialogs;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class AnswerTableDialog extends JDialog {
    JTable currentTable;

    public AnswerTableDialog(AbstractTableModel currentTableModel) {
        setName("Answer");
        setSize(800, 400);

        currentTable = new JTable(currentTableModel);
        add(new JScrollPane(currentTable));

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
