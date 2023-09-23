package GraphicInterfaces;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.Objects;

public class FileSelector extends JFrame {
    public static final JPanel PANEL = new JPanel();
    final private static String APPLICATION_TITLE = " Dayz Editor Purifier";

    public FileSelector(String mission, String icon) {
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("graphics/" + icon)));
        this.setIconImage(logo.getImage());
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        this.setVisible(true);
        this.setTitle(APPLICATION_TITLE + " (" + mission + ")");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        Dimension DIMENSION = new Dimension(600, 435);
        this.setPreferredSize(DIMENSION);
        this.pack();
        int DESKTOP_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
        int DESKTOP_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((DESKTOP_WIDTH / 2) - (this.getWidth() / 2), DESKTOP_HEIGHT / 2 - (this.getHeight() / 2));
        this.add(PANEL);
        FileChooser fileChooser = new FileChooser(mission);
        fileChooser.setFileFilter(new FileNameExtensionFilter("MapGroupPos files","xml"));
        PANEL.add(fileChooser);
    }
}