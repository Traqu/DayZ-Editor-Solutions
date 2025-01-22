package GraphicInterfaces;

import GraphicInterfaces.Constants.Interfaces.UserInterfaceConstants;

import javax.swing.*;
import java.net.URL;

public class WelcomeWindow extends JWindow implements UserInterfaceConstants {
    public WelcomeWindow() {
        setAlwaysOnTop(true);
        URL graphicURL = WelcomeWindow.class.getResource("/graphics/traqu.jpg");
        JLabel welcomeGraphic;

        if (graphicURL != null) {
            welcomeGraphic = new JLabel(new ImageIcon(graphicURL));
            add(welcomeGraphic);
            setLocation(DESKTOP_WIDTH / 2 - welcomeGraphic.getPreferredSize().width / 2, DESKTOP_HEIGHT / 2 - (welcomeGraphic.getPreferredSize().height / 2));

            pack();
            setVisible(true);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        dispose();
    }
}
