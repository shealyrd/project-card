package com.example.evan.projectcard.Utilities;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.evan.projectcard.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Evan on 8/4/2016.
 *
 * Modified from source code found here:
 * http://androidosbeginning.blogspot.com/2010/09/gif-animation-in-android.html
 */


public class GifView extends View {
    public Movie movie;
    private long moviestart = 0;
    protected boolean fullScreenModeOn = false;
    protected boolean scaled = false;
    protected int imgID;
    protected float scale;

    public GifView(Context context) throws IOException {
        super(context);
        initialize();
    }
    public GifView(Context context, AttributeSet attrs) throws IOException{
        super(context, attrs);
        initialize();
    }
    public GifView(Context context, AttributeSet attrs, int defStyle) throws IOException {
        super(context, attrs, defStyle);
        initialize();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (movie == null) {
            return;
        }

        long now=android.os.SystemClock.uptimeMillis();

        if (moviestart == 0) moviestart = now;

        int relTime = 0;
        if(movie.duration()!= 0){
            relTime = (int)((now - moviestart) % movie.duration());
        }
        movie.setTime(relTime);
        if(fullScreenModeOn){
            float scaleX = canvas.getHeight() / movie.height();
            float scaleY = canvas.getHeight() / movie.height();
            canvas.scale(scaleX, (scaleY + scaleY/10));
        }
        if(scaled){
            canvas.scale(scale, scale);
        }
        movie.draw(canvas,0,0);
        this.invalidate();
    }

    public void setValues(){}

    public void setScale(float scale){
        scaled = true;
        this.scale = scale;
    }

    public void initialize(){
        setValues();
        movie=Movie.decodeStream(getResources().openRawResource(imgID));
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public void setFullScreenMode(){
        fullScreenModeOn = true;
    }

    public void setImgID(int id){
        imgID = id;
    }
}
