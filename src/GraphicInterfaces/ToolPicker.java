package GraphicInterfaces;

import GraphicInterfaces.Constants.Enums.CallOrigin;
import GraphicInterfaces.Constants.Interfaces.UserInterfaceConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static GraphicInterfaces.Constants.Enums.CallOrigin.TOOL_PICKER;

public class ToolPicker extends JFrame implements UserInterfaceConstants {

    private static final Dimension UNIFIED_BTN_DIM = new Dimension(UNIFIED_BUTTON_WIDTH, UNIFIED_BUTTON_HEIGTH);
    private final CallOrigin INVOCATION_ORIGIN = TOOL_PICKER;

    public ToolPicker(String title) throws HeadlessException {
        super(title);
        setupFrame();
    }

    private void setupFrame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("graphics/dayz.jpg")));
        this.setIconImage(logo.getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setPreferredSize(new Dimension(600, 435));
        setAlwaysOnTop(true);
        setupButtons();
        pack();

        /**Sets the window's location to be centered on user's screen*/
        setLocation((DESKTOP_WIDTH / 2) - (this.getWidth() / 2), DESKTOP_HEIGTH / 2 - (this.getHeight() / 2));
        setResizable(false);
        setVisible(true);
    }

    private void setupButtons() {

        List<JButton> buttonList = new ArrayList<>();

        JPanel toolSelectionPanel = new JPanel();
        add(toolSelectionPanel);

        JButton eventsButton = new JButton("Event adapter");
        buttonList.add(eventsButton);
        JButton protoButton = new JButton("MapGroupPos filter");
        buttonList.add(protoButton);

        for (JButton button : buttonList) {
            button.setPreferredSize(UNIFIED_BTN_DIM);
            button.setFont(FONT_BAGHDAD);
            button.setFocusable(false);
            button.setBorder(BorderFactory.createLineBorder(new Color(166, 192, 222), 1));
        }

        toolSelectionPanel.add(eventsButton);
        toolSelectionPanel.add(protoButton);

        eventsButton.addActionListener(actionEvent -> {
            dispose();
            new FileChooserFrame(INVOCATION_ORIGIN);
        });

        protoButton.addActionListener(actionEvent -> {
            dispose();
            new LandEntitiesImporter();
        });


    }
}