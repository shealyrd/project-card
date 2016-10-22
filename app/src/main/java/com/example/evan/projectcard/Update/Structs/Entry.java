package com.example.evan.projectcard.Update.Structs;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by Evan on 10/6/2016.
 */
public class Entry {
    public String id;
    public String imguri;
    public String nameuri;

    public Entry(Node entryNode){
        NodeList children = entryNode.getChildNodes();
        Node nID = children.item(1);
        Node nImgURI = children.item(3);
        Node nNameURI = children.item(5);
        id = nID.getTextContent();
        imguri = nImgURI.getTextContent();
        nameuri = nNameURI.getTextContent();
    }


}
