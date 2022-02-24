package com.codecool.polishdraughts;

public class Pawn {
    private Color color;
    private Coordinates coordinates; // todo czy to potrzebne ???
                                     // tak to jest potrzebne inaczej nic nie pójdzie
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

    public boolean isCrowned() {
        return isCrowned;
    }

    public void crown() {
        isCrowned = true;
    }
}
