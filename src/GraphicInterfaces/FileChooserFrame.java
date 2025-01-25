package GraphicInterfaces;

import GraphicInterfaces.Constants.Enums.CallOrigin;
import GraphicInterfaces.Constants.Interfaces.UserInterfaceConstants;
import GraphicInterfaces.Constants.Interfaces.UserPathConstants;
import GraphicInterfaces.FileChoosersLogic.FileChooser;
import Utilities.PropertiesReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.Objects;


public class FileChooserFrame extends JFrame implements UserInterfaceConstants, UserPathConstants {
    public static final JPanel PANEL = new JPanel();
    private final JCheckBox useCustomFilesCheckbox = new JCheckBox("INCLUDE CUSTOM PROTOFILES IN FILTERING: ", true);

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


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
        setPreferredSize(DEFAULT_WINDOW_DIMENSION);
        setTitleOnInvocation(invocationOrigin);
        pack();
        setLocation((DESKTOP_WIDTH / 2) - (getWidth() / 2), DESKTOP_HEIGHT / 2 - (getHeight() / 2));
        add(PANEL);

        FileChooser fileChooser = new FileChooser(DAYZ_EDITOR_PATH, invocationOrigin, this);

        fileChooser.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));

        PANEL.add(fileChooser);
        if (invocationOrigin == CallOrigin.OTHER) {
            PANEL.setLayout(new FlowLayout(FlowLayout.TRAILING));
            useCustomFilesCheckbox.setFont(UserInterfaceConstants.FONT_BAGHDAD);
            useCustomFilesCheckbox.setHorizontalTextPosition(SwingConstants.LEFT);
            useCustomFilesCheckbox.setFocusable(false);
            PANEL.add(useCustomFilesCheckbox);
        }
    }

    private void setTitleOnInvocation(CallOrigin invocationOrigin) {
        switch (invocationOrigin) {
            case TOOL_PICKER:
                setTitle(" Event adapter" + PropertiesReader.getVersion(true) + " " + PropertiesReader.getAuthor());
                break;
            case OTHER:
                setPreferredSize(new Dimension(UserInterfaceConstants.DEFAULT_WINDOW_DIMENSION.width, UserInterfaceConstants.DEFAULT_WINDOW_DIMENSION.height + 38));
                setTitle(" MapGroupPos filter" + PropertiesReader.getVersion(true) + " " + PropertiesReader.getAuthor());
                break;
        }
    }

    public boolean isUseCustomFiles() {
        return useCustomFilesCheckbox.isSelected();
    }
}