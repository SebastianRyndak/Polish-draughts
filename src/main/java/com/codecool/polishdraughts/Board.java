package com.codecool.polishdraughts;


import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String RESET = "\033[0m";  // Text Reset
    Color color ;


    private int greenLeft = 0;
    private int whiteLeft = 0;
    private Pawn[][] board;
    private int size;
    private int queenGreen = 0;
    private int queenWhite = 0;

    public Board(int size) {
        this.board = new Pawn[size][size];
        this.size = size;
        placePawns();
    }

    @Override
    public String toString() {
        printFirstLine(board.length);
        for (int i = 0; i < board.length; i++){
            System.out.print((char)(65 + i) + " ");
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == null && (i+j)%2!=0 ){
                    System.out.print(Color.WHITE_BACKGROUND_BRIGHT+"   "+RESET);
                }
                else if (board[i][j] == null){
                    System.out.print(Color.ANSI_BLACK_BACKGROUND+"   "+RESET);
                }
                else if (board[i][j].getColor().getColorValue().equals(WHITE_BRIGHT) && board[i][j].isCrowned()){
                    System.out.print(Color.ANSI_BLACK_BACKGROUND+Color.ANSI_PURPLE+" ◕ "+RESET);
                }
                else if (board[i][j].getColor().getColorValue().equals(GREEN_BRIGHT) && board[i][j].isCrowned()){
                    System.out.print(Color.ANSI_BLACK_BACKGROUND+" ◕ "+RESET);
                }
                else if (board[i][j].getColor().getColorValue().equals(WHITE_BRIGHT) ){
                    System.out.print(Color.ANSI_BLACK_BACKGROUND+Color.ANSI_PURPLE+" ● "+RESET);
                }
                else {
                    System.out.print(Color.ANSI_BLACK_BACKGROUND+" ● "+RESET);
                }
            }
            System.out.println();
        }
        return "";
    }

    public List<Integer> getPawnsLeft() {
        List<Integer> pawnsLeft = new ArrayList<Integer>();
        return pawnsLeft;
    }

    public void addPawnCount (int value) {
        whiteLeft += value;
        greenLeft += value;
    }

    public void printFirstLine(int len){
        System.out.print("  ");
        for (int i = 0; i < len; i++){
            if (i < 9){
                System.out.print(i  + 1 + "  ");
            }
            else {
                System.out.print(i  + 1 + " ");
            }
        }
        System.out.println();
    }

    public void removePawn(int x, int y){
        if (board[x][y].getColor().getColorValue().equals(GREEN_BRIGHT)) {
            greenLeft -= 1;
            if (board[x][y].isCrowned()){
                setQueenGreen(-1);
            }
        } else {
            whiteLeft -= 1;
            if (board[x][y].isCrowned()){
                setQueenWhite(-1);
            }
        }
        board[x][y] = null;
    }

    public Pawn getPawn(int x, int y){
        return board[x][y];
    }

    public Pawn[][] getBoard() {
        return board;
    }

    public int getSize(){
        return size;
    }

    public void validateMove(){
        // walidacja czy można przesunąć pionek na dane miejsce
    }

    public void movePawn(Pawn pawn, int[] currentCoordinates, int[] nextCoordinates){
        board[currentCoordinates[0]][currentCoordinates[1]] = null;
        board[nextCoordinates[0]][nextCoordinates[1]] = pawn;
    }

    public boolean checkDraw(){
        if (queenGreen == 1 && queenWhite == 1 && whiteLeft == 1 && greenLeft == 1 ){
            return true;
        }
        return false;
    }

    public void displayTheResultOfATie(){
            System.out.println(board);
            System.out.println("A Draw!");
            System.exit(0);
    }

    public boolean checkWin(){
        if (whiteLeft == 0 || greenLeft == 0) {
            return true;
        }
        return false;
    }

    public void declareWin(){
        String winner;
        if (whiteLeft == 0) {
            winner = "GREEN";
        } else {
            winner = "WHITE";
        }
        System.out.println("Player " + winner + " had won the game!");
    }

    public int getQueenGreen() {
        return queenGreen;
    }

    public int getQueenWhite() {
        return queenWhite;
    }

    public void setQueenGreen(int queenGreen) {
        this.queenGreen += 1;
    }

    public void setQueenWhite(int queenWhite) {
        this.queenWhite += 1;
    }

    private void placePawns(){
        int defaultRowsValue = 4;
        if (size < 10) {
            defaultRowsValue = 1;
        }
        for(int y=0; y < size; y++){
            for (int x=0; x< defaultRowsValue; x++){
                if ((x+y)%2 ==0 ){
                    addPawnCount(1);
                    board[x][y] = createPawn(WHITE_BRIGHT, x, y);
                    board[size-x-1][size-y-1] = createPawn(GREEN_BRIGHT, size-x-1, size-y-1);
                }
            }
        }
    }

    private Pawn createPawn(String colorValue, int x, int y){
        Color color = new Color(colorValue);
        Coordinates coordinates = new Coordinates(x, y);
        return new Pawn(color, coordinates);
    }

    public String getEnemyColor(String color) {
        if (color.equals(WHITE_BRIGHT)){
            return GREEN_BRIGHT;
        } else {
            return WHITE_BRIGHT;
        }
    }
}

