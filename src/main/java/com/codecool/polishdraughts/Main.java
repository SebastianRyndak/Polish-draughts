package com.codecool.polishdraughts;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        gameMenu();
    }

    public static boolean gameMenu() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;

        while (shouldContinue) {
            System.out.println(Color.PURPLE_BRIGHT+
                    "        _______ _                 _                     \n" +
                    "       (_______) |               | |                    \n" +
                    "        _      | |__  _____  ____| |  _ _____  ____ ___ \n" +
                    "       | |     |  _ \\| ___ |/ ___) |_/ ) ___ |/ ___)___)\n" +
                    "       | |_____| | | | ____( (___|  _ (| ____| |  |___ |\n" +
                    "        \\______)_| |_|_____)\\____)_| \\_)_____)_|  (___/ \n" +
                    "                                                 "+ Color.RESET);
            PlayMusic.playMusic();
            System.out.println(Color.WHITE_BRIGHT+"          ===============================");
            System.out.println(Color.WHITE_BRIGHT+"          ||         Wybierz opcję     ||"+Color.RESET);
            System.out.println(Color.WHITE_BRIGHT+"          ===============================");
            System.out.println();
            System.out.println(Color.WHITE_BRIGHT+"            1. Nowa Gra.              "+Color.RESET);
            System.out.println(Color.WHITE_BRIGHT+"            2. Wyświetl Autorów Gry. "+Color.RESET);
            System.out.println(Color.WHITE_BRIGHT+"            3. Exit Game.            "+Color.RESET);
            System.out.println(Color.WHITE_BRIGHT+"          ===============================");
            try {
                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1 -> startGame();
                    case 2 -> System.out.println("2");
                    case 3 -> shouldContinue = false;
                }
            } catch (InputMismatchException a) {
                System.out.println("Proszę podać poprawny parametr !!!");
                return true;
            }
        }
        return false;
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
                return 4;
            }
        }
    }

    public static void startGame(){
        Board board = getBoardSize();
        Game game = new Game(board);
        boolean currentPlayer = false;
        while(true) {
            game.move(currentPlayer);
            if (board.checkWin()){
                board.declareWin();
                break;
            }else if (board.checkDraw()){
                board.displayTheResultOfATie();
                break;
            }
            currentPlayer = !currentPlayer;
        }
    }



}
