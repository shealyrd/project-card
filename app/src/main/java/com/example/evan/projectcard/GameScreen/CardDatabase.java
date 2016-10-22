package com.example.evan.projectcard.GameScreen;
import android.util.Log;

import com.example.evan.projectcard.Engine.Card;
import com.example.evan.projectcard.Engine.CardEngineLookup;
import com.example.evan.projectcard.Engine.ResourceCost;
import com.example.evan.projectcard.Utilities.CardData;
import com.example.evan.projectcard.R;

import java.util.HashMap;

/**
 * Created by Evan on 8/18/2016.
 */
public class CardDatabase {
    public static HashMap<Integer, CardDefinition> ID_MAP = new HashMap<Integer, CardDefinition>();
    static{
        addCard(1,
                new CardDefinition(
                        1,
                        R.raw.card_ancient_seal,
                        CardData.ANCIENT_SEAL_NAME,
                        CardData.ANCIENT_SEAL_INFO,
                        CardType.ITEM,
                        0,
                        0,
                        0,
                        new ResourceCost(5, 0, 0, 0),
                        new KeywordCollection()
                        ));
        addCard(2,
                new CardDefinition(
                        2,
                        R.raw.card_assassin,
                        CardData.ASSASSIN_NAME,
                        CardData.ASSASSIN_INFO,
                        CardType.IDENTITY,
                        20,
                        3,
                        75,
                        new ResourceCost(15, 0, 0, 0),
                        new KeywordCollection(CardData.KEYWORD_MELEE)
                ));
        addCard(3,
                new CardDefinition(
                        3,
                        R.raw.card_bigass_anime_sword,
                        CardData.BIGASS_ANIME_SWORD_NAME,
                        CardData.BIGASS_ANIME_SWORD_INFO,
                        CardType.ITEM,
                        0,
                        0,
                        0,
                        new ResourceCost(10, 0, 0, 0),
                        new KeywordCollection()
                ));
        addCard(4,
                new CardDefinition(
                        4,
                        R.raw.card_blue_obelisk,
                        CardData.BLUE_OBELISK_NAME,
                        CardData.BLUE_OBELISK_INFO,
                        CardType.CONSTRUCTION,
                        0,
                        0,
                        0,
                        new ResourceCost(5, 0, 0, 0),
                        new KeywordCollection(CardData.KEYWORD_WATER)
                ));
        addCard(5,
                new CardDefinition(
                        5,
                        R.raw.card_bomb,
                        CardData.BOMB_NAME,
                        CardData.BOMB_INFO,
                        CardType.ITEM,
                        0,
                        0,
                        0,
                        new ResourceCost(15, 0, 0, 0),
                        new KeywordCollection()
                ));
        addCard(6,
                new CardDefinition(
                        6,
                        R.raw.card_butterfly_blade,
                        CardData.BUTTERFLY_BLADE_NAME,
                        CardData.BUTTERFLY_BLADE_INFO,
                        CardType.ITEM,
                        0,
                        0,
                        0,
                        new ResourceCost(15, 0, 0, 0),
                        new KeywordCollection()
                ));
        addCard(7,
                new CardDefinition(
                        7,
                        R.raw.card_castle,
                        CardData.CASTLE_NAME,
                        CardData.CASTLE_INFO,
                        CardType.CONSTRUCTION,
                        0,
                        0,
                        0,
                        new ResourceCost(20, 0, 0, 0),
                        new KeywordCollection()
                ));
        addCard(8,
                new CardDefinition(
                        8,
                        R.raw.card_church,
                        CardData.CHURCH_NAME,
                        CardData.CHURCH_INFO,
                        CardType.CONSTRUCTION,
                        0,
                        0,
                        0,
                        new ResourceCost(20, 0, 0, 0),
                        new KeywordCollection()
                ));
        addCard(9,
                new CardDefinition(
                        9,
                        R.raw.card_dark_knight,
                        CardData.DARK_KNIGHT_NAME,
                        CardData.DARK_KNIGHT_INFO,
                        CardType.IDENTITY,
                        75,
                        2,
                        120,
                        new ResourceCost(25, 0, 0, 0),
                        new KeywordCollection(CardData.KEYWORD_MELEE)
                ));
        addCard(10,
                new CardDefinition(
                        10,
                        R.raw.card_fireball,
                        CardData.FIREBALL_NAME,
                        CardData.FIREBALL_INFO,
                        CardType.CRAFT,
                        0,
                        0,
                        0,
                        new ResourceCost(10, 3, 0, 0),
                        new KeywordCollection(CardData.KEYWORD_SPELL, CardData.KEYWORD_FIRE)
                ));
        addCard(11,
                new CardDefinition(
                        11,
                        R.raw.card_grasshopper_gloves,
                        CardData.GRASSHOPPER_GLOVES_NAME,
                        CardData.GRASSHOPPER_GLOVES_INFO,
                        CardType.ITEM,
                        0,
                        0,
                        0,
                        new ResourceCost(15, 0, 0, 0),
                        new KeywordCollection()
                ));
        addCard(12,
                new CardDefinition(
                        12,
                        R.raw.card_grove_of_rite,
                        CardData.GROVE_OF_RITE_NAME,
                        CardData.GROVE_OF_RITE_INFO,
                        CardType.CONSTRUCTION,
                        0,
                        0,
                        0,
                        new ResourceCost(15, 0, 0, 0),
                        new KeywordCollection()
                ));
        addCard(13,
                new CardDefinition(
                        13,
                        R.raw.card_merchant,
                        CardData.MERCHANT_NAME,
                        CardData.MERCHANT_INFO,
                        CardType.IDENTITY,
                        25,
                        3,
                        100,
                        new ResourceCost(10, 0, 0, 0),
                        new KeywordCollection()
                ));
        addCard(14,
                new CardDefinition(
                        14,
                        R.raw.card_necromancer,
                        CardData.NECROMANCER_NAME,
                        CardData.NECROMANCER_INFO,
                        CardType.IDENTITY,
                        50,
                        2,
                        75,
                        new ResourceCost(25, 0, 0, 0),
                        new KeywordCollection(CardData.KEYWORD_MAGICAL)
                ));
        addCard(15,
                new CardDefinition(
                        15,
                        R.raw.card_priest,
                        CardData.PRIEST_NAME,
                        CardData.PRIEST_INFO,
                        CardType.IDENTITY,
                        10,
                        2,
                        50,
                        new ResourceCost(15, 0, 0, 0),
                        new KeywordCollection(CardData.KEYWORD_HOLY)
                ));
        addCard(16,
                new CardDefinition(
                        16,
                        R.raw.card_rank_and_file,
                        CardData.RANK_AND_FILE_NAME,
                        CardData.RANK_AND_FILE_INFO,
                        CardType.IDENTITY,
                        50,
                        2,
                        100,
                        new ResourceCost(9, 0, 0, 0),
                        new KeywordCollection(CardData.KEYWORD_MELEE)
                ));
        addCard(17,
                new CardDefinition(
                        17,
                        R.raw.card_red_knight,
                        CardData.RED_KNIGHT_NAME,
                        CardData.RED_KNIGHT_INFO,
                        CardType.IDENTITY,
                        50,
                        2,
                        100,
                        new ResourceCost(20, 0, 0, 0),
                        new KeywordCollection(CardData.KEYWORD_MELEE, CardData.KEYWORD_MAGICAL)
                ));
        addCard(18,
                new CardDefinition(
                        18,
                        R.raw.card_red_obelisk,
                        CardData.RED_OBELISK_NAME,
                        CardData.RED_OBELISK_INFO,
                        CardType.CONSTRUCTION,
                        0,
                        0,
                        0,
                        new ResourceCost(10, 0, 0, 0),
                        new KeywordCollection(CardData.KEYWORD_FIRE)
                ));
        addCard(19,
                new CardDefinition(
                        19,
                        R.raw.card_ring_of_constitution,
                        CardData.RING_OF_CONSTITUTION_NAME,
                        CardData.RING_OF_CONSTITUTION_INFO,
                        CardType.ITEM,
                        0,
                        0,
                        0,
                        new ResourceCost(15, 0, 0, 0),
                        new KeywordCollection()
                ));
        addCard(20,
                new CardDefinition(
                        20,
                        R.raw.card_scout,
                        CardData.SCOUT_NAME,
                        CardData.SCOUT_INFO,
                        CardType.IDENTITY,
                        25,
                        5,
                        75,
                        new ResourceCost(10, 0, 0, 0),
                        new KeywordCollection()
                ));
        addCard(21,
                new CardDefinition(
                        21,
                        R.raw.card_shove,
                        CardData.SHOVE_NAME,
                        CardData.SHOVE_INFO,
                        CardType.CRAFT,
                        0,
                        0,
                        0,
                        new ResourceCost(10, 0, 0, 0),
                        new KeywordCollection()
                ));
        addCard(22,
                new CardDefinition(
                        22,
                        R.raw.card_thunderbolt,
                        CardData.THUNDERBOLT_NAME,
                        CardData.THUNDERBOLT_INFO,
                        CardType.CRAFT,
                        0,
                        0,
                        0,
                        new ResourceCost(),
                        new KeywordCollection(CardData.KEYWORD_SPELL, CardData.KEYWORD_LIGHTNING)
                ));
        addCard(23,
                new CardDefinition(
                        23,
                        R.raw.card_torrent,
                        CardData.TORRENT_NAME,
                        CardData.TORRENT_INFO,
                        CardType.CRAFT,
                        0,
                        0,
                        0,
                        new ResourceCost(10, 0, 3, 0),
                        new KeywordCollection(CardData.KEYWORD_SPELL, CardData.KEYWORD_WATER)
                ));
        addCard(24,
                new CardDefinition(
                        24,
                        R.raw.card_wide_heal,
                        CardData.WIDE_HEAL_NAME,
                        CardData.WIDE_HEAL_INFO,
                        CardType.CRAFT,
                        0,
                        0,
                        0,
                        new ResourceCost(15, 0, 0, 0),
                        new KeywordCollection(CardData.KEYWORD_SPELL)
                ));
        addCard(25,
                new CardDefinition(
                        25,
                        R.raw.card_wizard,
                        CardData.WIZARD_NAME,
                        CardData.WIZARD_INFO,
                        CardType.IDENTITY,
                        5,
                        2,
                        50,
                        new ResourceCost(10, 0, 0, 0),
                        new KeywordCollection(CardData.KEYWORD_MAGICAL)
                ));
        addCard(26,
                new CardDefinition(
                        26,
                        R.raw.card_yellow_obelisk,
                        CardData.YELLOW_OBELISK_NAME,
                        CardData.YELLOW_OBELISK_INFO,
                        CardType.CONSTRUCTION,
                        0,
                        0,
                        0,
                        new ResourceCost(10, 0, 0, 0),
                        new KeywordCollection(CardData.KEYWORD_LIGHTNING)
                ));
    }


    public static void addCard(Integer id, CardDefinition def){
        ID_MAP.put(id, def);
    }

    public static int getDrawableID(int ID){
        Log.d("CardDatabase", "ID: " + ID);
        CardDefinition def = ID_MAP.get(ID);
        return def.Drawable_ID;
    }
    public static int getIDFromDrawable(int ID){
        CardDefinition def = ID_MAP.get(ID);
        return def.Drawable_ID;
    }

    public static int getIDFromName(String name){
        for(CardDefinition def: ID_MAP.values()){
            if(def.name == name){
                return def.ID;
            }
        }
        return 0;
    }

    public static int getDrawableFromCard(Card c){
        return getDrawableID(CardEngineLookup.getIDFromCard(c));
    }

    public static CardDefinition getCardDefinition(int Id){
        return ID_MAP.get(Id);
    }

    public static int[] getDrawableIDs(){
        int[] result = new int[size()];
        for(int i = 0; i < size(); i++){
            result[i] = getCardDefinition(i + 1).Drawable_ID;
        }
        return result;
    }

    public static String[] getNames(){
        String[] result = new String[size()];
        for(int i = 0; i < size(); i++){
            result[i] = getCardDefinition(i + 1).name;
        }
        return result;
    }

    public static int size(){
        return ID_MAP.size();
    }
}
