package com.codecool.polishdraughts;

public class Pawn {
    private Color color;
    private boolean isCrowned = false;

    public Pawn(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isCrowned() {
        return isCrowned;
    }

    public void crown() {
        isCrowned = true;
    }
}
