package com.example.evan.projectcard.Update;

import com.example.evan.projectcard.Update.Structs.Entry;

import org.w3c.dom.Node;

/**
 * Created by Evan on 9/21/2016.
 */
public class UpdatableEntry {
    Entry data;

    public UpdatableEntry(Node node){
        data = new Entry(node);
    }

}
