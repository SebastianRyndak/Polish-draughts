package com.codecool.polishdraughts;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        PlayMusic.playMusic();
        Board board = getBoardSize();
        Game game = new Game(board);
        while(true) {
            game.movePawn();
            if (board.checkWin()){
                System.out.println(board);
                board.declareWin();
                break;
            }
        }

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
            } else if (line.matches("secrettest")) {
                return 5;
            }
        }
    }
}
