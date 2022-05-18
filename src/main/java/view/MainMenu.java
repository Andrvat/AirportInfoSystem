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
        menuButtons.add(mainLabel, gridBagConstraints);
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

        JButton openRequestGridButton = new OpenRequestGridButton(controllerManager);
        menuButtons.add(openRequestGridButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        if (ApplicationConstants.ADMIN.equals(controllerManager.getProvider().getRole())) {
            mainLabel.setText("    MAIN MENU");
            JButton newAccountButton = new AddNewAccountButton(controllerManager);
            menuButtons.add(newAccountButton, gridBagConstraints);
            menuButtons.add(new JLabel(" "), gridBagConstraints);

            JButton generateDeparturesButton = new GenerateDeparturesButton(controllerManager);
            menuButtons.add(generateDeparturesButton, gridBagConstraints);
            menuButtons.add(new JLabel(" "), gridBagConstraints);
        }

        if (ApplicationConstants.PASSENGER.equals(controllerManager.getProvider().getRole())) {
            JButton buyTicketButton = new BuyTicketButton(controllerManager);
            menuButtons.add(buyTicketButton, gridBagConstraints);
            menuButtons.add(new JLabel(" "), gridBagConstraints);

            JButton commitPurchaseButton = new CommitPurchaseButton(controllerManager);
            menuButtons.add(commitPurchaseButton, gridBagConstraints);
            menuButtons.add(new JLabel(" "), gridBagConstraints);

            JButton cancelPurchaseButton = new CancelPurchaseButton(controllerManager);
            menuButtons.add(cancelPurchaseButton, gridBagConstraints);
            menuButtons.add(new JLabel(" "), gridBagConstraints);
        }

        gridBagConstraints.weighty = 1;
        this.add(menuButtons, gridBagConstraints);
    }
}
