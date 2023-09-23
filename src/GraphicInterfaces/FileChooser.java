package GraphicInterfaces;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

import Logic.Script;

public class FileChooser extends JFileChooser {
    String mission;

    public FileChooser(String mission) {
        this.mission = mission;
        setCurrentDirectory(new File(System.getProperty("user.home") + "\\Documents\\DayZ\\Editor"));
    }

    @Override
    public void approveSelection() {
        try {
            new Script(this.getSelectedFile().getPath(), getSelectedFile().getName());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cancelSelection() {
        System.exit(0);
    }
}
