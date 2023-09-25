package GraphicInterfaces.FileChoosers;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import GraphicInterfaces.FileSelector;
import Logic.CustomClassExtractor;
import Logic.Script;

public class FileChooser extends JFileChooser {
    private boolean isCustomFileChooser = false;
    private JFrame frame = null;

    public FileChooser(String defaultDirectoryPath) {
        setCurrentDirectory(new File(defaultDirectoryPath));
        this.setApproveButtonText("Export");
        this.setApproveButtonToolTipText("Export from selected file");
    }

    public FileChooser(String defaultDirectoryPath, JFrame frame) {
        this.frame = frame;
        this.setApproveButtonText("Import");
        this.setApproveButtonToolTipText("Import from selected file");
        this.setCurrentDirectory(new File(defaultDirectoryPath));
        isCustomFileChooser = true;
    }

    @Override
    public void approveSelection() {
        if (!isCustomFileChooser) {
            try {
                new Script(this.getSelectedFile().getPath(), getSelectedFile().getName());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                new CustomClassExtractor(this.getSelectedFile().getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frame.setVisible(false);
            new FileSelector();
        }
    }

    @Override
    public void cancelSelection() {
        if (isCustomFileChooser) {
            new FileSelector();
            frame.setVisible(false);
        } else {
            System.exit(0);
        }
    }
}