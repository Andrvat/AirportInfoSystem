package view.listenedButtons;

import controller.ControllerManager;
import forms.AbstractRequestProvider;
import forms.Request1Provider;

import javax.swing.*;
import java.awt.*;

public class OpenRequestGridButton extends JButton {
    public OpenRequestGridButton(ControllerManager controllerManager) {
        this.setText("Open requests grid");
        this.addActionListener(event -> {
            JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));
            panel.setPreferredSize(new Dimension(1200, 1000));

            AbstractRequestProvider request1Provider = new Request1Provider();
            JPanel in = new JPanel(new GridLayout(0, 1, 5, 5));
            in.add(new JLabel(request1Provider.getDescription()));
            in.add(new MakeRequestButton(controllerManager, request1Provider));
            panel.add(in);

            for (var i = 0; i < 14; ++i) {
                in = new JPanel(new GridLayout(0, 1, 5, 5));
                in.add(new JLabel("<html>Получить список и общее число всех pаботников аэpопоpта, " +
                        "начальников отделов, pаботников указанного отдела, по стажу pаботы в аэpопоpту, " +
                        "половому пpизнаку, возpасту, пpизнаку наличия и количеству детей, " +
                        "по pазмеpу заpаботной платы</html>"));
                in.add(new JButton("Перейти к заполнению формы"));
                panel.add(in);
            }

            int result = JOptionPane.showConfirmDialog(this, panel, "Requests grid", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                return;
            }
        });
    }
}
