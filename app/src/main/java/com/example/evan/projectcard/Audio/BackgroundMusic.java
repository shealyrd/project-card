package com.example.evan.projectcard.Audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import com.example.evan.projectcard.R;

/**
 *}
 Created by Evan on 10/14/2016.
 */
public class BackgroundMusic extends AsyncTask<Void, Void, Void> {
    Context context;
    int musicID;
    MediaPlayer player;
    boolean hasBeenActivated;

    public BackgroundMusic(Context context, int musicID) {
        this.context = context;
        this.musicID = musicID;
    }

    public boolean isRunning(){
        return player.isPlaying();
    }

    public void stop(){
        player.pause();
    }

    public void start(){
        player.start();
    }

    public boolean hasBeenActivated(){
        return hasBeenActivated;
    }

    @Override
    protected Void doInBackground(Void... params) {
        hasBeenActivated = true;
        player = MediaPlayer.create(context, musicID);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        player.start();
        return null;
    }
}