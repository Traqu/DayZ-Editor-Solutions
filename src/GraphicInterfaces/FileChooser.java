package GraphicInterfaces;

import GraphicInterfaces.Constants.Enums.CallOrigin;
import GraphicInterfaces.Constants.Interfaces.UserInterfaceConstants;
import GraphicInterfaces.FileChoosersLogic.FileChooserLogic;
import Utilities.PropertiesReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.Objects;


public class FileChooser extends JFrame implements UserInterfaceConstants {
    private CallOrigin invocationOrigin;
    public static final JPanel PANEL = new JPanel();

    public FileChooser(CallOrigin invocationOrigin) {
        this.invocationOrigin = invocationOrigin;
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
        Dimension DIMENSION = new Dimension(600, 435);
        setPreferredSize(DIMENSION);
        pack();
        setLocation((DESKTOP_WIDTH / 2) - (getWidth() / 2), DESKTOP_HEIGTH / 2 - (getHeight() / 2));
        add(PANEL);

        FileChooserLogic
                fileChooserLogic = new FileChooserLogic(
                System.getProperty("user.home") + "\\Documents\\DayZ\\Editor", invocationOrigin, this
        );

        fileChooserLogic.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));

        PANEL.add(fileChooserLogic);


    }

    private void setTitleOnInvocation(CallOrigin invocationOrigin) {
        switch (invocationOrigin) {
            case TOOL_PICKER ->
                    setTitle(" Choose file from which you want to extract objects" + PropertiesReader.getVersion(true));
            case OTHER ->
                    setTitle(" Choose file from which you want to extract spawns" + PropertiesReader.getVersion(true));
        }

    }
}
