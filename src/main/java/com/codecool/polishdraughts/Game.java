package com.codecool.polishdraughts;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
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
            System.out.println("Enter coordinates chosen pawn: ");
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
                emptyMove(coordinates);
                enemyMove(coordinates);
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
        int[] nextCoordinates;
        List<Integer[]> enemyMove = enemyMove(currentCoordinates);
        List<Integer[]> emptyMove = emptyMove(currentCoordinates);
        Pawn selectedPawn = this.board.getPawn(currentCoordinates[0], currentCoordinates[1]);
        if (!enemyMove.isEmpty()) {
            nextCoordinates = getMove();
            for (int i = 0; i < enemyMove.toArray().length; i++) {
                if (enemyMove.get(i)[0] == nextCoordinates[0] && enemyMove.get(i)[1] == nextCoordinates[1]){
                    this.board.movePawn(selectedPawn, currentCoordinates, nextCoordinates);
                    board.removePawn((currentCoordinates[0]+nextCoordinates[0])/2, (currentCoordinates[1]+nextCoordinates[1])/2);
                    break;
                }
            }
        } else if (!emptyMove.isEmpty()) {
            nextCoordinates = getMove();
            for (int i = 0; i < emptyMove.toArray().length; i++) {
                if (emptyMove.get(i)[0] == nextCoordinates[0] && emptyMove.get(i)[1] == nextCoordinates[1]){
                    this.board.movePawn(selectedPawn, currentCoordinates, nextCoordinates);
                    break;
                }
            }
        }
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

    public List<Integer[]> emptyMove(int[] coordinates){
        List<Integer[]> moves = new ArrayList<>();
        int x = coordinates[0];
        int y = coordinates[1];
        if(x-1 >= 0 && y-1 >= 0 && this.board.getBoard()[x-1][y-1] == null){
            moves.add(new Integer[]{x-1, y-1});
            System.out.println((x-1) + "  " + (y-1));
        }
        if(x-1 >= 0 && y+1 <= this.board.getSize()-1 && this.board.getBoard()[x-1][y+1] == null){
            moves.add(new Integer[]{x-1, y+1});
            System.out.println((x-1) + "  " + (y+1));
        }
        if(x+1 <= this.board.getSize()-1 && y+1 <= this.board.getSize()-1 && this.board.getBoard()[x+1][y+1] == null){
            moves.add(new Integer[]{x+1, y+1});
            System.out.println((x+1) + "  " + (y+1));
        }
        if(x+1 <= this.board.getSize()-1 && y-1 >= 0 && this.board.getBoard()[x+1][y-1] == null){
            moves.add(new Integer[]{x+1, y-1});
            System.out.println((x+1) + "  " + (y-1));
        }
        return moves;
    }

    public List<Integer[]> enemyMove(int[] coordinates){
        List<Integer[]> moves = new ArrayList<>();
        int x = coordinates[0];
        int y = coordinates[1];
        Pawn pawn = board.getPawn(coordinates[0], coordinates[1]);
        if (pawn != null) {
            String pawnColor = pawn.getColor().getColorValue();
            String enemyColor = board.getEnemyColor(pawnColor);
            if (x - 1 >= 0 && y - 1 >= 0 && this.board.getBoard()[x - 1][y - 1] != null && this.board.getBoard()[x - 1][y - 1].getColor().getColorValue().equals(enemyColor) &&
                    x - 2 >= 0 && y - 2 >= 0 && this.board.getBoard()[x - 2][y - 2] == null) {
                moves.add(new Integer[]{x - 2, y - 2});
                System.out.println((x - 2) + "  " + (y - 2));
            }
            if (x - 1 >= 0 && y + 1 <= this.board.getSize() - 1 && this.board.getBoard()[x - 1][y + 1] != null && this.board.getBoard()[x - 1][y + 1].getColor().getColorValue().equals(enemyColor) &&
                    x - 2 >= 0 && y + 2 <= this.board.getSize() - 1 && this.board.getBoard()[x - 2][y + 2] == null) {
                moves.add(new Integer[]{x - 2, y + 2});
                System.out.println((x - 2) + "  " + (y + 2));
            }
            if (x + 1 <= this.board.getSize() - 1 && y + 1 <= this.board.getSize() - 1 && this.board.getBoard()[x + 1][y + 1] != null && this.board.getBoard()[x + 1][y + 1].getColor().getColorValue().equals(enemyColor) &&
                    x + 2 <= this.board.getSize() - 1 && y + 2 <= this.board.getSize() - 1 && this.board.getBoard()[x + 2][y + 2] == null) {
                moves.add(new Integer[]{x + 2, y + 2});
                System.out.println((x + 2) + "  " + (y + 2));
            }
            if (x + 1 <= this.board.getSize() - 1 && y - 1 >= 0 && this.board.getBoard()[x + 1][y - 1] != null && this.board.getBoard()[x + 1][y - 1].getColor().getColorValue().equals(enemyColor) &&
                    x + 2 <= this.board.getSize() - 1 && y - 2 >= 0 && this.board.getBoard()[x + 2][y - 2] == null) {
                moves.add(new Integer[]{x + 2, y - 2});
                System.out.println((x + 2) + "  " + (y - 2));
            }
        }
        return moves;
    }
}
