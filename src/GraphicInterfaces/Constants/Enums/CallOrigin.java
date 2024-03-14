package GraphicInterfaces.Constants.Enums;

public enum CallOrigin {
    OTHER(0),
    TOOL_PICKER(1);

    private final int value;

    CallOrigin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
