package GraphicInterfaces.FileChoosersLogic;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import GraphicInterfaces.Constants.Enums.CallOrigin;
import GraphicInterfaces.Constants.Interfaces.UserPathConstants;
import GraphicInterfaces.FileChooserFrame;
import Utilities.CustomClassExtractor;
import Utilities.DynamicEventAdapter;
import Utilities.MapGroupPosExtractor;
import org.xml.sax.SAXException;

import static GraphicInterfaces.Constants.Enums.CallOrigin.OTHER;

public class FileChooser extends JFileChooser implements UserPathConstants {
    private final CallOrigin INVOCATION_ORIGIN = OTHER;

    private boolean isCustomFileChooser = false;
    private JFrame frame;

    private CallOrigin invocationOrigin;
    private FileChooserFrame invocationFileChooser;

    public FileChooser(String defaultDirectoryPath, CallOrigin invocationOrigin, FileChooserFrame thisFileChooserFrame) {
        this.invocationOrigin = invocationOrigin;
        setCurrentDirectory(new File(defaultDirectoryPath));
        this.setApproveButtonText("Export");
        this.setApproveButtonToolTipText("Export from selected file");
        invocationFileChooser = thisFileChooserFrame;
        Action viewTypeDetails = getActionMap().get("viewTypeDetails");
        if (viewTypeDetails != null) {
            viewTypeDetails.actionPerformed(null);
        }
    }

    public FileChooser(String defaultDirectoryPath, JFrame frame) {
        this.frame = frame;
        this.setApproveButtonText("Import");
        this.setApproveButtonToolTipText("Import from selected file");
        this.setCurrentDirectory(new File(defaultDirectoryPath));
        isCustomFileChooser = true;
        Action viewTypeDetails = getActionMap().get("viewTypeDetails");
        if (viewTypeDetails != null) {
            viewTypeDetails.actionPerformed(null);
        }
    }

    /** Na podstawie invocationOrigin, w momencie potwierdzenia wykonania operacji na wybranym pliku, wywołane zostaje odpowiedznie narzędzie */
    @Override
    public void approveSelection() {
        if (!isCustomFileChooser) {
            switch (invocationOrigin) {
                case TOOL_PICKER:
                    createDynamicEventParser();
                    break;
                case OTHER:
                    createProtoExtractor();
                    break;
                default:
                    break;
            }
        }
        else {
            try {
                new CustomClassExtractor(this.getSelectedFile().getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frame.setVisible(false);
            new FileChooserFrame(INVOCATION_ORIGIN);
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
            new MapGroupPosExtractor(this.getSelectedFile().getPath(), this.getSelectedFile().getName(), invocationFileChooser);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cancelSelection() {
        if (isCustomFileChooser) {
            new FileChooserFrame(INVOCATION_ORIGIN);
            frame.setVisible(false);
        } else {
            System.exit(0);
        }
    }
}
