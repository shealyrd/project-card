package com.example.evan.projectcard.Network.Http;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.evan.projectcard.R;


/**
 * Created by Evan on 9/21/2016.
 */
public class HttpTestActivity extends Activity {

    Button downloadBtn;
    ImageView mImage;
    private final String URL = "http://projectcard-91355.onmodulus.net/img/1.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);

        downloadBtn = (Button) findViewById(R.id.button1);
        mImage = (ImageView) findViewById(R.id.imageView1);

        downloadBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                downloadBtn.setVisibility(View.INVISIBLE);
                OkHttpImageHandler handler = new OkHttpImageHandler();
                byte[] image = new byte[0];

                try {
                    image = handler.execute(URL).get();

                    if (image != null && image.length > 0) {

                        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,
                                image.length);
                        mImage.setImageBitmap(bitmap);
                        mImage.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                }
            }
        });

    }
}