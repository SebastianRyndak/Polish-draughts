package com.codecool.polishdraughts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = getBoardSize();
        Game game = new Game(board);
        System.out.println(board);
    }

    public static Board getBoardSize() {
        int size = validateBoardSize();
        return new Board(size);
    }

    public static int validateBoardSize() {
        while (true) {
            System.out.println("Enter board size (10-20):");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.matches("1[0-9]|20")) {
                return Integer.parseInt(line);
            }
        }
    }
}
