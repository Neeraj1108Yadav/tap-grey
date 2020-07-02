package com.example.tapcolor;

public class TapGameModel {

    private boolean isOrange;
    private boolean isBlue;
    private boolean isYellow;
    private boolean isGreen;
    private int tapCount;
    private boolean isTapped;

    public TapGameModel() {}

    public boolean isOrange() {
        return isOrange;
    }

    public void setOrange(boolean orange) {
        isOrange = orange;
    }

    public boolean isBlue() {
        return isBlue;
    }

    public void setBlue(boolean blue) {
        isBlue = blue;
    }

    public boolean isYellow() {
        return isYellow;
    }

    public void setYellow(boolean yellow) {
        isYellow = yellow;
    }

    public boolean isGreen() {
        return isGreen;
    }

    public void setGreen(boolean green) {
        isGreen = green;
    }

    public int getTapCount() {
        return tapCount;
    }

    public void setTapCount(int tapCount) {
        this.tapCount = tapCount;
    }

    public boolean isTapped() {
        return isTapped;
    }

    public void setTapped(boolean tapped) {
        isTapped = tapped;
    }
}
