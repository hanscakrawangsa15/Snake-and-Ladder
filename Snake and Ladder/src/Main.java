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
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;
public class Main {

    public static void main(String[] args) throws IOException,LineUnavailableException, UnsupportedAudioFileException{
        SnL g1 = new SnL(100);
        g1.play();
    }
}
