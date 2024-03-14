package GraphicInterfaces;

import GraphicInterfaces.Constants.UserInterfaceConstants;
import GraphicInterfaces.FileChoosersLogic.FileChooser;
import Utilities.PropertiesReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.Objects;


public class FileSelector extends JFrame implements UserInterfaceConstants {
    public static final JPanel PANEL = new JPanel();
    final private static String APPLICATION_TITLE = " Choose file from which you want to extract spawns" + PropertiesReader.getVersion(true);

    public FileSelector() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("graphics/livonia.jpg")));
        this.setIconImage(logo.getImage());
        this.setVisible(true);
        this.setTitle(APPLICATION_TITLE);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        Dimension DIMENSION = new Dimension(600, 435);
        this.setPreferredSize(DIMENSION);
        this.pack();
        this.setLocation((DESKTOP_WIDTH / 2) - (this.getWidth() / 2), DESKTOP_HEIGTH / 2 - (this.getHeight() / 2));
        this.add(PANEL);
        FileChooser fileChooser = new FileChooser(System.getProperty("user.home") + "\\Documents\\DayZ\\Editor");
        fileChooser.setFileFilter(new FileNameExtensionFilter("MapGroupPos files", "xml"));
        PANEL.add(fileChooser);
    }
}