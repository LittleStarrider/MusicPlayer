import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MusicPlayer {
    private final String mp3FileToPlay;
    private Player jlPlayer;

    public MusicPlayer(String mp3FileToPlay) {
        this.mp3FileToPlay = mp3FileToPlay;
    }

    public void play() {
        try {
            FileInputStream fileInputStream = new FileInputStream(mp3FileToPlay);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println("Problem playing mp3 file " + mp3FileToPlay);
            System.out.println(e.getMessage());
        }

        new Thread() {
            public void run() {
                try {
                    jlPlayer.play();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();
    }

    public void close() {
        if (jlPlayer != null) jlPlayer.close();
    }
}
