import GraphicInterfaces.WelcomeWindow;
import GraphicInterfaces.ToolPicker;

public class Main {
    public static void main(String[] args) {

//        new ToolPicker("Choose your tool" + PropertiesReader.getVersion(true));

        new WelcomeWindow();

        new ToolPicker("Choose your tool");

    }
}