package simplepaintswing.view;

import java.awt.*;

public class Container {
    private int currentAction = 1;
    private Color cursorColor = Color.BLACK;
    private String image = "";
    private int thicknessValue = 5;

    public int getThicknessValue() {
        return thicknessValue;
    }

    public void setThicknessValue(int thicknessValue) {
        this.thicknessValue = thicknessValue;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }

    public Color getCursorColor() {
        return cursorColor;
    }

    public void setCursorColor(Color cursorColor) {
        this.cursorColor = cursorColor;
    }
}
