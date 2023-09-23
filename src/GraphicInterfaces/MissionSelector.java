package GraphicInterfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class MissionSelector extends JFrame implements ActionListener{

    public static final JPanel PANEL = new JPanel();
    final private static String APPLICATION_TITLE = " Dayz Editor Purifier";
    public static final JLabel LABEL = new JLabel(" Select your mission ");

    private final int DESKTOP_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int DESKTOP_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final Dimension DIMENSION = new Dimension(302, 64);
    private final JButton chooseEnoch = new JButton("Livonia");
    private final JButton chooseChernarus = new JButton("Chernarus");
    private String mapgroupprotoPath;

    public MissionSelector() throws HeadlessException {
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("graphics/logo.jpg")));
        this.setIconImage(logo.getImage());
        this.setAlwaysOnTop(true);
        this.setTitle(APPLICATION_TITLE);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(DIMENSION);
        this.pack();
        this.setLocation((DESKTOP_WIDTH / 2) - (this.getWidth() / 2), DESKTOP_HEIGHT / 2 - (this.getHeight() / 2));
        this.add(PANEL);
        PANEL.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        PANEL.add(chooseEnoch);
        PANEL.add(LABEL);
        PANEL.add(chooseChernarus);

        chooseEnoch.addActionListener(this);
        chooseChernarus.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chooseEnoch) {
            mapgroupprotoPath = "/mapgroupproto/mapgroupproto_-_livonia.xml";
            System.out.println("Loot spawns will be assigned to assets as for Livonia, using file: " + mapgroupprotoPath + "\n");
            this.dispose();
            new FileSelector(chooseEnoch.getText(), "livonia.jpg");
        }
        if (e.getSource() == chooseChernarus) {
            mapgroupprotoPath = "/mapgroupproto/mapgroupproto_-_chernarus.xml";
            System.out.println("Loot spawns will be assigned to assets as for Chernarus, using file: " + mapgroupprotoPath + "\n");
            this.dispose();
            new FileSelector(chooseChernarus.getText(), "chernarus.jpg");
        }
        System.out.println("pressed");
    }
}
