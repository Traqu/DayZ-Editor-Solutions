package GraphicInterfaces.FileChoosersLogic;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import GraphicInterfaces.Constants.Enums.CallOrigin;
import Logic.CustomClassExtractor;
import Logic.DynamicEventParser;
import Logic.ProtoExtractor;
import org.xml.sax.SAXException;

import static GraphicInterfaces.Constants.Enums.CallOrigin.OTHER;


public class FileChooser extends JFileChooser {
    private final CallOrigin INVOCATION_ORIGIN = OTHER;

    private boolean isCustomFileChooser = false;
    private JFrame frame;

    private CallOrigin invocationOrigin;

    public FileChooser(String defaultDirectoryPath, CallOrigin invocationOrigin) {
        this.invocationOrigin = invocationOrigin;
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
            switch (invocationOrigin){
                case TOOL_PICKER -> createDynamicEventParser();
                case OTHER -> createProtoExtractor();
            }
        } else {
            try {
                new CustomClassExtractor(this.getSelectedFile().getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frame.setVisible(false);
                new GraphicInterfaces.FileChooser(INVOCATION_ORIGIN);
        }
    }

    private void createDynamicEventParser() {
        try {
            new DynamicEventParser(this.getSelectedFile().getPath(), this.getSelectedFile().getName());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private void createProtoExtractor() {
        try {
            new ProtoExtractor(this.getSelectedFile().getPath(), this.getSelectedFile().getName());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cancelSelection() {
        if (isCustomFileChooser) {
                new GraphicInterfaces.FileChooser(INVOCATION_ORIGIN);
            frame.setVisible(false);
        } else {
            System.exit(0);
        }
    }
}