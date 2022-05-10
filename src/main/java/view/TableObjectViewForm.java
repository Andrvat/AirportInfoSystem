package view;

import annotations.DbTable;
import controller.ControllerManager;
import converters.TitlesConverter;
import entities.Passenger;
import jdk.net.UnixDomainPrincipal;
import view.listenedButtons.DeleteRowByIdButton;
import view.listenedButtons.UpdateRowButton;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TableObjectViewForm extends JFrame {
    public TableObjectViewForm(ControllerManager controllerManager, String tableName,
                               String[] columns, String[] values)
            throws ClassNotFoundException {
        this.setSize(new Dimension(400, 700));
        this.setTitle("View object form");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());

        JLabel tableNameLabel = new JLabel("<HTML><span style='font-size:18px'><B>" + tableName + "</B></span></HTML>");

        ArrayList<JLabel> valueLabels = new ArrayList<>();
        ArrayList<JLabel> columnLabels = new ArrayList<>();
        for (int i = 0; i < columns.length; ++i) {
            valueLabels.add(new JLabel("<HTML><span style='font-size:14px'><U>" + values[i] + "</U></span></HTML>"));
            columnLabels.add(new JLabel("<HTML><span style='font-size:10px'>" + columns[i] + "</span></HTML>"));
        }

        int pkNumber = Class.forName("entities." + TitlesConverter.makeTitleFrom(tableName))
                .getAnnotation(DbTable.class).pkNumber();
        Map<String, String> primaryKey = new HashMap<>();
        for (int i = 0; i < pkNumber; ++i) {
            primaryKey.put(columns[i].replaceAll(" ", "_"), values[i]);
        }
        Map<String, String> existingValues = new HashMap<>();
        for (int i = 0; i < columns.length; ++i) {
            existingValues.put(columns[i], values[i]);
        }
        JButton deleteRowButton = new DeleteRowByIdButton(controllerManager, tableName, primaryKey, this);
        JButton updateRowButton = new UpdateRowButton(
                controllerManager, tableName,
                existingValues,
                primaryKey, this);

        JPanel fields = new JPanel(new GridBagLayout());

        GridBagConstraints labelGBC = new GridBagConstraints();
        labelGBC.insets = new Insets(3, 3, 3, 3);
        labelGBC.gridwidth = GridBagConstraints.REMAINDER;

        fields.add(tableNameLabel, labelGBC);
        fields.add(new JLabel(""), labelGBC);
        fields.add(new JLabel(""), labelGBC);
        for (int i = 0; i < columnLabels.size(); ++i) {
            fields.add(valueLabels.get(i), labelGBC);
            fields.add(columnLabels.get(i), labelGBC);
            fields.add(new JLabel(""), labelGBC);
        }

        JPanel buttons = new JPanel(new GridBagLayout());
        GridBagConstraints deleteButtonGBC = new GridBagConstraints();
        deleteButtonGBC.insets = new Insets(3, 3, 3, 3);
        GridBagConstraints modifyButtonGBC = new GridBagConstraints();
        modifyButtonGBC.insets = new Insets(3, 3, 3, 3);
        modifyButtonGBC.gridwidth = GridBagConstraints.REMAINDER;

        buttons.add(deleteRowButton, deleteButtonGBC);
        buttons.add(updateRowButton, modifyButtonGBC);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(fields, gbc);
        panel.add(buttons, gbc);

        this.add(panel, BorderLayout.NORTH);

        this.setVisible(true);
    }
}
