package GraphicInterfaces;

import GraphicInterfaces.Constants.Enums.CallOrigin;
import GraphicInterfaces.Constants.Interfaces.UserInterfaceConstants;
import GraphicInterfaces.Constants.Interfaces.UserPathConstants;
import GraphicInterfaces.FileChoosersLogic.FileChooser;
import Utilities.PropertiesReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Objects;


public class FileChooserFrame extends JFrame implements UserInterfaceConstants, UserPathConstants {
    public static final JPanel PANEL = new JPanel();

    public FileChooserFrame(CallOrigin invocationOrigin) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("graphics/livonia.jpg")));
        setIconImage(logo.getImage());
        setVisible(true);

        setTitleOnInvocation(invocationOrigin);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
        setPreferredSize(DEFAULT_WINDOW_DIMENSION);
        pack();
        setLocation((DESKTOP_WIDTH / 2) - (getWidth() / 2), DESKTOP_HEIGTH / 2 - (getHeight() / 2));
        add(PANEL);

        FileChooser
                fileChooser = new FileChooser(
                DAYZ_EDITOR_PATH, invocationOrigin, this
        );

        fileChooser.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));

        PANEL.add(fileChooser);
    }

    private void setTitleOnInvocation(CallOrigin invocationOrigin) {
        switch (invocationOrigin) {
            case TOOL_PICKER:
                setTitle(" Choose file from which you want to extract objects" + PropertiesReader.getVersion(true));
                break;
            case OTHER:
                setTitle(" Choose file from which you want to extract spawns" + PropertiesReader.getVersion(true));
                break;
        }
    }

}
