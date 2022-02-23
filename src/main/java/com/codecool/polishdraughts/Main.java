package com.codecool.polishdraughts;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        PlayMusic.playMusic();
        gameMenu();
    }

    public static boolean gameMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;

        while (shouldContinue) {
            System.out.println(
                    " _______ _                 _                     \n" +
                    "(_______) |               | |                    \n" +
                    " _      | |__  _____  ____| |  _ _____  ____ ___ \n" +
                    "| |     |  _ \\| ___ |/ ___) |_/ ) ___ |/ ___)___)\n" +
                    "| |_____| | | | ____( (___|  _ (| ____| |  |___ |\n" +
                    " \\______)_| |_|_____)\\____)_| \\_)_____)_|  (___/ \n" +
                    "                                                 ");
            System.out.println("    Wybierz opcję   ");
            System.out.println();
            System.out.println("1. Nowa Gra.");
            System.out.println("2. Wyświetl Autorów Gry.");
            System.out.println("3. Exit Game.");

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
        while(true) {
            game.move();
            if (board.checkWin()){
                board.declareWin();
                break;
            }else if (board.checkDraw()){
                board.displayTheResultOfATie();
                break;
            }
        }
    }



}
