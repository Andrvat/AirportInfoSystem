package view.utilities;

import javax.swing.*;
import java.awt.*;

public class ChooseTableViewer {
    private final JComboBox<String> comboBox;

    public ChooseTableViewer(String[] items) {
        this.comboBox = new JComboBox<>(items);
    }

    public int showChoosingTableView() {
        JPanel comboPanel = new JPanel(new GridLayout(0, 1));
        comboPanel.add(comboBox);
        return JOptionPane.showConfirmDialog(null, comboPanel, "Choose the table",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
