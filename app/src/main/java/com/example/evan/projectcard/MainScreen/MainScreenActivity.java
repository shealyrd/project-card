package com.example.evan.projectcard.MainScreen;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;

import com.example.evan.projectcard.Utilities.DeckFileHandler;
import com.example.evan.projectcard.R;

public class MainScreenActivity extends AppCompatActivity {
    MainScreenState currentState = MainScreenState.MAIN;
    MainActivityButtonListView currentButtonList;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        currentButtonList = (MainActivityButtonListView) findViewById(R.id.button_holder_main);
        sharedPreferences = getSharedPreferences("ProjectCard", MODE_PRIVATE);
        if(isFirstRun()){
            onFirstLoad();
        }
    }

    public void changeButtonListView(int id){
        MainActivityButtonListView  newButtons = (MainActivityButtonListView) findViewById(id);
        Animation anim = ChangeButtonListAnimationFactory.create(currentButtonList, newButtons);
        currentButtonList.startAnimation(anim);
        currentButtonList = newButtons;
    }


    public boolean isFirstRun(){
        if(sharedPreferences.getBoolean("hasRun", false)){
            return false;
        }
        sharedPreferences.edit().putBoolean("hasRun", true).commit();
        return true;
    }

    public void onFirstLoad(){
        new DeckFileHandler(this).writeDefaultDeck();
    }

    @Override
    public void onBackPressed()
    {
        switch(currentButtonList.getId()){
            case R.id.button_holder_main: super.onBackPressed(); break;
            case R.id.button_holder_multi: changeButtonListView(R.id.button_holder_main); break;
            case R.id.button_holder_options: changeButtonListView(R.id.button_holder_main); break;
            default: super.onBackPressed(); break;
        }

    }
}
