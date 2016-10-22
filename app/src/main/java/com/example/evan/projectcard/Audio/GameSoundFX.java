package com.example.evan.projectcard.Audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.evan.projectcard.R;

import java.util.HashMap;

/**
 * Created by Evan on 10/14/2016.
 */
public class GameSoundFX {
    private SoundPool mShortPlayer= null;
    private HashMap mSounds = new HashMap();

    public GameSoundFX(Context pContext)
    {
        this.mShortPlayer = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);

        mSounds.put(R.raw.draw, this.mShortPlayer.load(pContext, R.raw.draw, 1));
        mSounds.put(R.raw.playcard, this.mShortPlayer.load(pContext, R.raw.playcard, 1));
        mSounds.put(R.raw.error, this.mShortPlayer.load(pContext, R.raw.error, 1));
    }

    public void playShortResource(int piResource) {
        int iSoundId = (Integer) mSounds.get(piResource);
        this.mShortPlayer.play(iSoundId, 0.99f, 0.99f, 0, 0, 1);
    }

    public void release() {
        this.mShortPlayer.release();
        this.mShortPlayer = null;
    }
}
