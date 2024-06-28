import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Coba {

    public static void main(String[] args) throws IOException,UnsupportedAudioFileException,LineUnavailableException {
        Scanner scanner = new Scanner(System.in);
        File file = new File("src//rolldice.wav");
        AudioInputStream audio = AudioSystem.getAudioInputStream(file);

        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
        String response = scanner.next();
        clip.stop();
    }
}
