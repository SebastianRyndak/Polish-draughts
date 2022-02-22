package com.codecool.polishdraughts;

import javax.swing.*;

public class Board {
    private static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE
    private static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN

    private Pawn[][] board;
    private int size;

    public Board(int size) {
        this.board = new Pawn[size][size];
        this.size = size;
        placePawns();
    }

    @Override
    public String toString() {
        // wyświetlanie tablicy
        return "Dupa{}";
    }

    public void removePawn(int x, int y){
        board[x][y] = null;
    }

    public Pawn getPawn(int x, int y){
        return board[x][y];
    }

    public void validateMove(){
        // walidacja czy można przesunąć pionek na dane miejsce
    }

    public void movePawn(Pawn pawn, int x, int y){

    }

    private void placePawns(){
        createPawn(WHITE_BRIGHT, 0, 0);
        // ustawianie 2 x size pionków
    }

    private Pawn createPawn(String colorValue, int x, int y){
        Color color = new Color(colorValue);
        Coordinates coordinates = new Coordinates(x, y);
        return new Pawn(color, coordinates);
    }
}
