package GraphicInterfaces;

import GraphicInterfaces.Constants.Enums.CallOrigin;
import GraphicInterfaces.Constants.Interfaces.UserInterfaceConstants;
import GraphicInterfaces.FileChoosersLogic.FileChooser;
import Utilities.PropertiesReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static GraphicInterfaces.Constants.Enums.CallOrigin.OTHER;


public class LandEntitiesImporter implements UserInterfaceConstants {

    private final CallOrigin INVOCATION_ORIGIN = OTHER;

    public LandEntitiesImporter() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException exception) {
            throw new RuntimeException(exception);
        }
        JFrame frame = new JFrame(" Custom class import tool" + PropertiesReader.getVersion(true));

        frame.setResizable(false);

        JPanel anchorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        frame.add(anchorPanel);

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(LandEntitiesImporter.class.getClassLoader().getResource("graphics/dayz.jpg")));

        int desktopWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int desktopHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setupFrame(frame, logo, desktopWidth, desktopHeight, new Dimension(500, 205));

        JButton addButton = new JButton("Add");
        JTextArea prompt = new JTextArea(
                "You can add custom classes file.\n" +
                        "Provide file in xml format, containing lines corresponding to the example below:\n\n" +
                        "\t<group name=\"CLASSNAME\"[optional characters]\n\n" +
                        "All other lines will be ignored. Once added, they will stay, until manually removed."
        );

        prompt.setFocusable(false);
        prompt.setEditable(false);

        JButton skipButton = new JButton("Skip");

        anchorPanel.add(prompt);

        prompt.setFont(FONT_BAGHDAD);
        prompt.setBackground(new Color(240, 240, 240));
        prompt.setBorder(BorderFactory.createRaisedSoftBevelBorder());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JPanel optionalButtonPanel = new JPanel(new GridLayout(2, 1));
        anchorPanel.add(buttonPanel);

        Dimension optionalButtonsSize = new Dimension(140, 50);
        addButton.setPreferredSize(new Dimension(UNIFIED_BUTTON_WIDTH, UNIFIED_BUTTON_HEIGHT));
        skipButton.setPreferredSize(new Dimension(UNIFIED_BUTTON_WIDTH, UNIFIED_BUTTON_HEIGHT));

        buttonPanel.add(addButton);

        File customDirectory = new File(System.getProperty("user.home") + "\\AppData\\Local\\DayZ EditorPurifier\\custom");
        if (new File(customDirectory.toURI()).exists()) {
            if ((Objects.requireNonNull(customDirectory.listFiles()).length != 0)) {
                JButton openCustomDirectoryButton = new JButton("Browse custom files");
                JButton wipeCustomDirectoryButton = new JButton("Delete custom files");
                buttonPanel.add(Box.createHorizontalStrut(3));
                buttonPanel.add(optionalButtonPanel);
                optionalButtonPanel.setBackground(Color.BLACK);
                optionalButtonPanel.add(openCustomDirectoryButton);
                optionalButtonPanel.add(wipeCustomDirectoryButton);
                openCustomDirectoryButton.setFocusable(false);
                wipeCustomDirectoryButton.setFocusable(false);
                buttonPanel.add(Box.createHorizontalStrut(3));
                setupOptionalButtons(optionalButtonsSize, openCustomDirectoryButton);
                setupOptionalButtons(optionalButtonsSize, wipeCustomDirectoryButton);
                openCustomDirectoryButton.addActionListener(e -> {

                    try {
                        Desktop.getDesktop().open(customDirectory);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                wipeCustomDirectoryButton.addActionListener(e -> {

                    File[] files = customDirectory.listFiles();
                    if (files != null) {
                        for (File file : files) {
                            if (file.delete()) {
                                System.out.println("Custom file removed: " + file.getName());
                            }
                        }
                    }

                    skipButton.doClick();

                });
            } else {
                buttonPanel.add(Box.createHorizontalStrut(frame.getWidth() - 2 * UNIFIED_BUTTON_WIDTH - 2 * 28));
            }
        } else {
            buttonPanel.add(Box.createHorizontalStrut(frame.getWidth() - 2 * UNIFIED_BUTTON_WIDTH - 2 * 28));
        }

        buttonPanel.add(skipButton);
        addButton.setFocusable(false);
        skipButton.setFocusable(false);
        skipButton.setFont(FONT_BAGHDAD);
        addButton.setFont(FONT_BAGHDAD);

        addButton.addActionListener(e -> {
            frame.dispose();
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException exception) {
                throw new RuntimeException(exception);
            }

            JFrame fileChooserFrame = new JFrame();
            FileChooser fileChooser = new FileChooser(System.getProperty("user.home") + File.separator + "desktop", fileChooserFrame);
            setupFrame(fileChooserFrame, logo, desktopWidth, desktopHeight, DEFAULT_WINDOW_DIMENSION);
            fileChooserFrame.add(fileChooser);
            fileChooserFrame.pack();
            fileChooser.setFileFilter(new FileNameExtensionFilter(".xml", "xml"));
            fileChooserFrame.setTitle("Custom file importer" + PropertiesReader.getVersion(true));
        });

        skipButton.addActionListener(e -> {
            frame.dispose();
            new FileChooserFrame(INVOCATION_ORIGIN);
        });
    }

    private static void setupOptionalButtons(Dimension buttonsSize, JButton openCustomDirectory) {
        openCustomDirectory.setPreferredSize(new Dimension(buttonsSize.width + 9, buttonsSize.height / 2));
        openCustomDirectory.setFont(FONT_BAGHDAD);
    }

    private static void setupFrame(JFrame frame, ImageIcon logo, int DESKTOP_WIDTH, int DESKTOP_HEIGHT, Dimension dimension) {
        frame.setIconImage(logo.getImage());
        frame.setAlwaysOnTop(false);
        frame.setPreferredSize(dimension);
        frame.pack();
        frame.setLocation((DESKTOP_WIDTH / 2) - (frame.getWidth() / 2), DESKTOP_HEIGHT / 2 - (frame.getHeight() / 2));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}