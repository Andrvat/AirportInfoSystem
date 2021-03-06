package view.listenedButtons;

import controller.ControllerManager;
import forms.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OpenRequestGridButton extends JButton {
    public OpenRequestGridButton(ControllerManager controllerManager) {
        this.setText("Open requests grid");
        this.addActionListener(event -> {
            JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));
            panel.setPreferredSize(new Dimension(1200, 800));

            JOptionPane jOptionPane = new JOptionPane();
            jOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

            List<AbstractRequestProvider> requestProviders = new ArrayList<>();
            requestProviders.add(new Request1Provider());
            requestProviders.add(new Request2Provider());
            requestProviders.add(new Request3Provider());
            requestProviders.add(new Request4Provider());
            requestProviders.add(new Request5Provider());
            requestProviders.add(new Request6Provider());
            requestProviders.add(new Request7Provider());
            requestProviders.add(new Request8AProvider());
            requestProviders.add(new Request8BProvider());
            requestProviders.add(new Request9AProvider());
            requestProviders.add(new Request9BProvider());
            requestProviders.add(new Request10Provider());
            requestProviders.add(new Request11Provider());
            requestProviders.add(new Request12Provider());
            requestProviders.add(new Request13Provider());

            List<MakeRequestButton> requestButtons = new ArrayList<>();

            for (var provider : requestProviders) {
                var requestButton = new MakeRequestButton(controllerManager, provider);
                requestButtons.add(requestButton);
                JPanel inPanel = new JPanel(new BorderLayout());
                inPanel.add(new JLabel(provider.getDescription()), BorderLayout.CENTER);
                inPanel.add(requestButton, BorderLayout.SOUTH);
                panel.add(inPanel);
            }

            jOptionPane.setMessage(panel);
            JDialog dialog = jOptionPane.createDialog(this, "Requests grid");

            // set current grid dialog to all request buttons for calling dialog.dispose()
            for (var button : requestButtons) {
                button.setParentDialog(dialog);
            }

            dialog.setVisible(true);
        });
    }
}
