package view.utilities;

import javax.swing.*;
import java.awt.*;

public class ChooseOptionViewer {
    private final JComboBox<String> comboBox;
    private final String viewerName;

    public ChooseOptionViewer(String[] items, String name) {
        this.viewerName = name;
        this.comboBox = new JComboBox<>(items);
    }

    public int showView() {
        JPanel comboPanel = new JPanel(new GridLayout(0, 1));
        comboPanel.add(comboBox);
        return JOptionPane.showConfirmDialog(null, comboPanel, this.viewerName,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
