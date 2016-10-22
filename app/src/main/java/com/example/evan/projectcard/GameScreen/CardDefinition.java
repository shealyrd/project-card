package com.example.evan.projectcard.GameScreen;

import com.example.evan.projectcard.Engine.ResourceCost;

/**
 * Created by Evan on 8/18/2016.
 */
public class CardDefinition {
    public int ID;
    public int Drawable_ID;
    public String name;
    public String info;
    public int ATK;
    public int AP;
    public int HP;
    public KeywordCollection keywords;
    public CardType type;
    public ResourceCost cost;

    public CardDefinition(int ID, int Drawable_ID, String name, String info, CardType type, int ATK, int AP, int HP, ResourceCost cost, KeywordCollection keywords){
        this.AP = AP;
        this.ATK = ATK;
        this.Drawable_ID = Drawable_ID;
        this.HP = HP;
        this.ID = ID;
        this.name = name;
        this.info = info;
        this.keywords = keywords;
        this.type = type;
        this.cost = cost;
    }
}
