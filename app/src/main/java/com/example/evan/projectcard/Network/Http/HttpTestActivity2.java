package com.example.evan.projectcard.Network.Http;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.evan.projectcard.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by Evan on 10/8/2016.
 */
public class HttpTestActivity2 extends Activity {
    private EditText edtText;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_test_2_activity);
        edtText = (EditText) findViewById(R.id.editText1);
        outputText = (TextView) findViewById(R.id.textView1);
    }



    public void downloadUrl(View view) {

        //String url = "http://" + edtText.getText().toString();
        String url = "http://projectcard-91355.onmodulus.net/api/getgames";
        OkHttpXmlHandler handler = new OkHttpXmlHandler();
        String result = null;
        try {
            result = handler.execute(url).get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        outputText.append(result + "\n");
    }
}
