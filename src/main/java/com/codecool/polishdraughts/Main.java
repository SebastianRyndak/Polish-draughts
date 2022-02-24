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
        PlayMusic.playMusic();
        while (shouldContinue) {
            System.out.println(Color.PURPLE_BRIGHT+
                    "        _______ _                 _                     \n" +
                    "       (_______) |               | |                    \n" +
                    "        _      | |__  _____  ____| |  _ _____  ____ ___ \n" +
                    "       | |     |  _ \\| ___ |/ ___) |_/ ) ___ |/ ___)___)\n" +
                    "       | |_____| | | | ____( (___|  _ (| ____| |  |___ |\n" +
                    "        \\______)_| |_|_____)\\____)_| \\_)_____)_|  (___/ \n" +
                    "                                                 "+ Color.RESET);

            System.out.println(Color.WHITE_BRIGHT+"               ===============================");
            System.out.println(Color.WHITE_BRIGHT+"               ||         Game Menu         ||"+Color.RESET);
            System.out.println(Color.WHITE_BRIGHT+"               ===============================");
            System.out.println();
            System.out.println(Color.WHITE_BRIGHT+"                 1. New Game.              "+Color.RESET);
            System.out.println(Color.WHITE_BRIGHT+"                 2. Game Developers.      "+Color.RESET);
            System.out.println(Color.WHITE_BRIGHT+"                 3. Exit Game.            "+Color.RESET);
            System.out.println();
            System.out.println(Color.WHITE_BRIGHT+"               ==============================="+Color.RESET);
            try {
                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1 -> startGame();
                    case 2 -> gameDevelopers();
                    case 3 -> shouldContinue = false;

                }
            } catch (InputMismatchException a) {
                System.out.println("Please specify the correct parameter!");
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
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
            System.out.println(Color.WHITE_BRIGHT+"               Enter board size (10-20):"+Color.RESET);
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.matches("1[0-9]|20")) {
                return Integer.parseInt(line);
            } else if (line.matches("secrettest")) {
                return 5;
            }
        }
    }

    public static void startGame(){
        Board board = getBoardSize();
        Game game = new Game(board);
        boolean currentPlayer = true;
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


    public static void gameDevelopers() throws InterruptedException {
        System.out.println("Many thanks for a great cooperation to Justyna, Krzysztof, Sebastian. Great Job !!!");
        Thread.sleep(5000);
    }

}
