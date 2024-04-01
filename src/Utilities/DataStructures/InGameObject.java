package Utilities.DataStructures;

public class InGameObject {
    private String className;
    public double x;
    public double y;
    public double a;
    public double z;

    public InGameObject(String className, String x, String y, String a, String z) {
        this.className = className;
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.a = Double.parseDouble(a);
        this.z = Double.parseDouble(z);
    }

    public String getObjectClassName() {

        return className;
    }

    @Override
    public String toString() {
        return className;
    }
}
