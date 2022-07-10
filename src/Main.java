import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filename = "C:/Users/fabio/Downloads/King Dedede_'s Theme.mp3";
        MusicPlayer mp3Player = new MusicPlayer(filename);
        mp3Player.start();

        Scanner sc = new Scanner(System.in);

        System.out.println("Write stop to stop the music: ");

        String input;
        do {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("pause")) {
                mp3Player.pause();
            } else if (input.equalsIgnoreCase("resume")) {
                mp3Player.resume();
            }
        } while (!input.equalsIgnoreCase("stop"));
        mp3Player.stop();
    }
}