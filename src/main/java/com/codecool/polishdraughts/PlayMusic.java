package com.codecool.polishdraughts;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class PlayMusic {

    public static void playMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        File file = new File("music/daily_download_20190711_128 (online-audio-converter.com).wav");

        Scanner scanner = new Scanner(System.in);

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        String response = "";

        while (!response.equals("Q")){
            System.out.print("Do you want your game to be accompanied by music? Y/N: ");
            response = scanner.next();
            response = response.toUpperCase();

            switch (response){
                case "Y" -> clip.start();
                case "S" -> clip.stop();
                case "R" -> clip.setMicrosecondPosition(0);
                case "N" -> clip.close();
            }
            break;
        }
    }
}
