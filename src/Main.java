import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filename = "C:/Users/fabio/Downloads/King Dedede_'s Theme.mp3";
        MusicPlayer mp3Player = new MusicPlayer(filename);
        mp3Player.play();

        Scanner sc = new Scanner(System.in);

        System.out.println("Write stop to stop the music: ");

        if (sc.nextLine().equalsIgnoreCase("stop")) {
            mp3Player.close();
        }
    }
}