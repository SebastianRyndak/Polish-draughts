package com.codecool.polishdraughts;


public class Board {
    private static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE
    private static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String RESET = "\033[0m";  // Text Reset
    Color color ;

    private Pawn[][] board;
    private int size;

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

    private void placePawns(){
        for(int y=0; y < size; y++){
            for (int x=0; x< 4; x++){
                if ((x+y)%2 ==0 ){
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

}
