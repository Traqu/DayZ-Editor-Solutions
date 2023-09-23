package GraphicInterfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MissionSelector extends JFrame implements ActionListener {

    public static final JPanel PANEL = new JPanel();
    final private static String APPLICATION_TITLE = " Dayz Editor Purifier";
    public static final JLabel LABEL = new JLabel(" Select your mission ");

    private final JButton chooseEnoch = new JButton("Livonia");
    private final JButton chooseChernarus = new JButton("Chernarus");

    public MissionSelector() throws HeadlessException {
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("graphics/logo.jpg")));
        this.setIconImage(logo.getImage());
        this.setAlwaysOnTop(false);
        this.setTitle(APPLICATION_TITLE);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension DIMENSION = new Dimension(302, 64);
        this.setPreferredSize(DIMENSION);
        this.pack();
        int DESKTOP_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
        int DESKTOP_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((DESKTOP_WIDTH / 2) - (this.getWidth() / 2), DESKTOP_HEIGHT / 2 - (this.getHeight() / 2));
        this.add(PANEL);
        PANEL.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        PANEL.add(chooseEnoch);
        PANEL.add(LABEL);
        PANEL.add(chooseChernarus);
        chooseEnoch.setFocusable(false);
        chooseChernarus.setFocusable(false);

        chooseEnoch.addActionListener(this);
        chooseChernarus.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String mission = "";
        if (e.getSource() == chooseEnoch) {
            mission = "livonia";
        }
        if (e.getSource() == chooseChernarus) {
            mission = "chernarus";
        }
        String mapgroupprotoPath = "/protoFiles/" + mission;
        new FileSelector(mission, mission + ".jpg");
        this.dispose();
    }
}