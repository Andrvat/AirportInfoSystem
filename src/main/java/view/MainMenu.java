package view;

import controller.ControllerManager;
import model.ApplicationConstants;
import view.listenedButtons.*;
import view.utilities.DisplaysManager;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu(ControllerManager controllerManager, JFrame parentDisplay) {

        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel menuButtons = new JPanel(new GridBagLayout());

        menuButtons.add(new JLabel() {{
            setText("  MAIN MENU");
            setFont(DisplaysManager.LABELS_DEFAULT_FONT);
        }}, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        JButton disconnectButton = new DisconnectButton(controllerManager, parentDisplay);
        menuButtons.add(disconnectButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        JButton insertDataButton = new InsertDataButton(controllerManager);
        menuButtons.add(insertDataButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        JButton manageTablesButton = new ManageTablesButton(controllerManager);
        menuButtons.add(manageTablesButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        if (ApplicationConstants.ADMIN.equals(controllerManager.getProvider().getRole())) {
            JButton newAccountButton = new AddNewAccountButton(controllerManager);
            menuButtons.add(newAccountButton, gridBagConstraints);
            menuButtons.add(new JLabel(" "), gridBagConstraints);
        }

        gridBagConstraints.weighty = 1;
        this.add(menuButtons, gridBagConstraints);
    }
}
