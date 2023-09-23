package GraphicInterfaces;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FileSelector extends JFrame {
    private final int DESKTOP_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int DESKTOP_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final Dimension DIMENSION = new Dimension(500, 300);
    public static final JPanel PANEL = new JPanel();
    final private static String APPLICATION_TITLE = " Dayz Editor Purifier";

    public FileSelector(String mission, String icon) {
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("graphics/" + icon)));
        this.setIconImage(logo.getImage());
        this.setVisible(true);
        this.setTitle(APPLICATION_TITLE + " (" + mission + ")");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setPreferredSize(DIMENSION);
        this.pack();
        this.setLocation((DESKTOP_WIDTH / 2) - (this.getWidth() / 2), DESKTOP_HEIGHT / 2 - (this.getHeight() / 2));
        this.add(PANEL);
        PANEL.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        PANEL.setBackground(Color.PINK);
    }
}
