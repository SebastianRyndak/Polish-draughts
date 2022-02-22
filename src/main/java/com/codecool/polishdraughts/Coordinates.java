package com.codecool.polishdraughts;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void changeCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
}