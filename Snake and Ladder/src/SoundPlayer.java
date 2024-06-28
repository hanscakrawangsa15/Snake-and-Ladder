/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : D
 * Group    : 05
 * Members  :
 * 1. 5026231130 - Hans Christian Cakrawangsa  
 * 2. 5026231145 - Abrorus Shobah
 * 3. 5026231161 - Muhammad Daniel Alfarisi 
 * ------------------------------------------------------
 */
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {

    public static void playSound(String soundFileName) {
        try {
            File soundFile = new File(soundFileName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
