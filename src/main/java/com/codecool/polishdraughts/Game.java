package com.codecool.polishdraughts;

import java.util.Scanner;

public class Game {
    private Board board;

    public Game(Board board) {
        this.board = board;
    }

    public int[] getMove() {
        while (true){
            System.out.println("Enter coordinates: ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.equals("quit")){
                System.exit(0);
            }
            if (line.length() < 2 || !isFormatCoordinates(line)){
                continue;
            }
            int[] coordinates = new int[2];
            coordinates[1] = Integer.parseInt(line.substring(1)) - 1;
            coordinates[0] = changeMark(line.charAt(0));
            if(isCoordinatesInBoard(coordinates) && isEmptyPosition(coordinates)){
                return coordinates;
            }
        }
    }

    public int[] startMove() {
        while (true){
            System.out.println("Enter coordinates: ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.equals("quit")){
                System.exit(0);
            }
            if (line.length() < 2 || !isFormatCoordinates(line)){
                continue;
            }
            int[] coordinates = new int[2];
            coordinates[1] = Integer.parseInt(line.substring(1)) - 1;
            coordinates[0] = changeMark(line.charAt(0));
            if(isCoordinatesInBoard(coordinates) && isFilledPosition(coordinates)){
                return coordinates;
            }
        }
    }

    public int changeMark(char letter) {
        int acs = letter;
        if (90 >= acs && acs >= 65){
            return acs - 65;
        }
        return acs - 97;
    }

    public void movePawn(){
        int[] currentCoordinates = startMove();
        Pawn selectedPawn = this.board.getPawn(currentCoordinates[0],currentCoordinates[1]);
        int[] nextCoordinates = getMove();
        this.board.movePawn(selectedPawn, currentCoordinates, nextCoordinates);
    }

    public boolean isEmptyPosition(int[] coordinates){
        return this.board.getPawn(coordinates[0], coordinates[1]) == null;
    }

    public boolean isFilledPosition(int[] coordinates){
        return this.board.getPawn(coordinates[0], coordinates[1]) != null;
    }

    public boolean isCoordinatesInBoard(int[] coordinates){
        return this.board.getSize() > coordinates[0] && this.board.getSize() > coordinates[1];
    }

    public boolean isFormatCoordinates(String coordinates){
        String[] digits = coordinates.substring(1).split("");
        for (String digit: digits){
            char digit_char = digit.charAt(0);
            if (!Character.isDigit(digit_char)){
                return false;
            }
        }
        return !Character.isDigit(coordinates.charAt(0));
    }
}
