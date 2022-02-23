package com.codecool.polishdraughts;

public class Color {
    private String colorValue;
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN

    public Color(String colorValue) { this.colorValue = colorValue;}

    public String getColorValue() {
        return colorValue;
    }

}
