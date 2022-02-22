package com.codecool.polishdraughts;

public class Pawn {
    private Color color;
    private Coordinates coordinates; // todo czy to potrzebne ???
    private boolean isCrowned = false;

    public Pawn(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }

    public Color getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void crown() {
        isCrowned = true;
    }
}
