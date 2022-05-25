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

        JLabel mainLabel = new JLabel();
        mainLabel.setText("  MAIN MENU");
        mainLabel.setFont(DisplaysManager.LABELS_DEFAULT_FONT);
        this.addComponentTo(menuButtons, gridBagConstraints, mainLabel);

        this.addComponentTo(menuButtons, gridBagConstraints, new DisconnectButton(controllerManager, parentDisplay));
        this.addComponentTo(menuButtons, gridBagConstraints, new InsertDataButton(controllerManager));
        if (!ApplicationConstants.PASSENGER.equals(controllerManager.getProvider().getRole())) {
            this.addComponentTo(menuButtons, gridBagConstraints, new ManageTablesButton(controllerManager));
        }
        this.addComponentTo(menuButtons, gridBagConstraints, new OpenRequestGridButton(controllerManager));

        if (ApplicationConstants.ADMIN.equals(controllerManager.getProvider().getRole())) {
            mainLabel.setText("    MAIN MENU");
            this.addComponentTo(menuButtons, gridBagConstraints, new AddNewAccountButton(controllerManager));
            this.addComponentTo(menuButtons, gridBagConstraints, new GenerateDeparturesButton(controllerManager));
        }

        this.addComponentTo(menuButtons, gridBagConstraints, new PrettyViewingTablesButton(controllerManager));
        if (ApplicationConstants.PASSENGER.equals(controllerManager.getProvider().getRole())) {
            this.addComponentTo(menuButtons, gridBagConstraints, new BuyTicketButton(controllerManager));
            this.addComponentTo(menuButtons, gridBagConstraints, new CommitPurchaseButton(controllerManager));
            this.addComponentTo(menuButtons, gridBagConstraints, new CancelPurchaseButton(controllerManager));
        }

        if (ApplicationConstants.AIRPORT_MANAGER.equals(controllerManager.getProvider().getRole())) {
            this.addComponentTo(menuButtons, gridBagConstraints, new ManageEmployeesButton(controllerManager));
        }

        gridBagConstraints.weighty = 1;
        this.add(menuButtons, gridBagConstraints);
    }

    private void addComponentTo(JPanel menu, GridBagConstraints gridBagConstraints, JComponent component) {
        menu.add(component, gridBagConstraints);
        menu.add(new JLabel(" "), gridBagConstraints);
    }
}
