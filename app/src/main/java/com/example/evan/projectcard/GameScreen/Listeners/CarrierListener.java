package com.example.evan.projectcard.GameScreen.Listeners;

/**
 * Created by Evan on 8/30/2016.
 */
public abstract class CarrierListener implements BasicListener {
    Object obj;
    public Object getObject() {return obj;};
    public void setObject(Object obj) {this.obj = obj;}
}
