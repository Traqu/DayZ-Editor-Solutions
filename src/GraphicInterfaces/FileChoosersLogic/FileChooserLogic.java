package GraphicInterfaces.FileChoosersLogic;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import GraphicInterfaces.Constants.Enums.CallOrigin;
import GraphicInterfaces.FileChooser;
import Utilities.CustomClassExtractor;
import Utilities.DynamicEventAdapter;
import Utilities.MapGroupPosExtractor;
import org.xml.sax.SAXException;

import static GraphicInterfaces.Constants.Enums.CallOrigin.OTHER;


public class FileChooserLogic extends JFileChooser {
    private final CallOrigin INVOCATION_ORIGIN = OTHER;

    private boolean isCustomFileChooser = false;
    private JFrame frame;

    private CallOrigin invocationOrigin;
    private FileChooser invocationFileChooser;

    public FileChooserLogic(String defaultDirectoryPath, CallOrigin invocationOrigin, FileChooser thisFileChooser) {
        this.invocationOrigin = invocationOrigin;
        setCurrentDirectory(new File(defaultDirectoryPath));
        this.setApproveButtonText("Export");
        this.setApproveButtonToolTipText("Export from selected file");
        invocationFileChooser = thisFileChooser;
    }

    public FileChooserLogic(String defaultDirectoryPath, JFrame frame) {
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
            new DynamicEventAdapter(this.getSelectedFile().getPath(), invocationFileChooser);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private void createProtoExtractor() {
        try {
            new MapGroupPosExtractor(this.getSelectedFile().getPath(), this.getSelectedFile().getName());
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