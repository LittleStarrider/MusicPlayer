import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicPlayer {
    private String mp3FileToPlay = null;
    private AdvancedPlayer player;
    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;
    private int totalLength;
    private int pauseLength = 0;
    private Thread resumeThread;
    private Thread playThread;

    private Runnable runnablePlay = () -> {
        try {
            //code for play button
            createPlayer(mp3FileToPlay);
            totalLength = fileInputStream.available();
            player.play();//starting music
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    private Runnable runnableResume = () -> {

        try {
            //code for resume button
            createPlayer(mp3FileToPlay);
            fileInputStream.skip(totalLength - pauseLength);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public MusicPlayer(String mp3FileToPlay) {
        this.mp3FileToPlay = mp3FileToPlay;
    }

    public void start() {
        //starting play thread
        playThread = new Thread(runnablePlay);
        playThread.start();
    }

    public void resume() {
        //starting resume thread
        if (mp3FileToPlay != null && resumeThread == null) {
            resumeThread = new Thread(runnableResume);
            resumeThread.start();
        }
    }

    public void pause() {
        //code for pause button
        if (player != null && mp3FileToPlay != null) {
            try {
                pauseLength = fileInputStream.available();
                player.close();
                System.out.println("Playback paused...");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (resumeThread != null) {
            resumeThread = null;
        }
    }

    public void stop() {
        //code for stop button
        if (player != null) {
            player.close();
        }
    }

    private void createPlayer(String mp3FileToPlay) throws FileNotFoundException, JavaLayerException {
        fileInputStream = new FileInputStream(mp3FileToPlay);
        bufferedInputStream = new BufferedInputStream(fileInputStream);
        player = new AdvancedPlayer(bufferedInputStream);
        player.setPlayBackListener(new PlaybackListener() {
            public void playbackStarted(PlaybackEvent playbackEvent)
            {
                System.out.println("Playback started..");
                //		   thread.resume();
            }

            public void playbackFinished(PlaybackEvent playbackEvent)
            {
                System.out.println("Playback finished..");
                try {
                    pauseLength = fileInputStream.available();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
