package com.example.evan.projectcard.Network.Bluetooth;

import android.content.ClipData;
import android.util.Log;

import com.example.evan.projectcard.Engine.ActivateEffectFrame;
import com.example.evan.projectcard.Engine.ActivateEffectMove;
import com.example.evan.projectcard.Engine.AttackFrame;
import com.example.evan.projectcard.Engine.AttackMove;
import com.example.evan.projectcard.Engine.Card;
import com.example.evan.projectcard.Engine.ConstructionCard;
import com.example.evan.projectcard.Engine.CraftCard;
import com.example.evan.projectcard.Engine.EndMainPhaseMove;
import com.example.evan.projectcard.Engine.IdentityCard;
import com.example.evan.projectcard.Engine.ItemCard;
import com.example.evan.projectcard.Engine.Move;
import com.example.evan.projectcard.Engine.MoveCardFrame;
import com.example.evan.projectcard.Engine.MoveCardMove;
import com.example.evan.projectcard.Engine.PersistentCard;
import com.example.evan.projectcard.Engine.PlayCardFrame;
import com.example.evan.projectcard.Engine.PlayCardMove;
import com.example.evan.projectcard.Engine.PlayConstructionMove;
import com.example.evan.projectcard.Engine.PlayItemMove;
import com.example.evan.projectcard.Engine.PlayUnitMove;
import com.example.evan.projectcard.Engine.Player;
import com.example.evan.projectcard.Engine.RotateUnitFrame;
import com.example.evan.projectcard.Engine.RotateUnitMove;
import com.example.evan.projectcard.Engine.SeizeFrame;
import com.example.evan.projectcard.Engine.SeizeMove;
import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.Network.PerceptionFlipper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Evan on 9/23/2016.
 */
public class BluetoothMoveSync {

    public static Move sync(GameActivity context, Move input){
        Move result = new Move();
        /*result.game = context.game;
        result.owner = context.game.getPlayerByName(input.owner.getName());*/
        if(input instanceof PlayCardMove){
            if(input instanceof PlayUnitMove){
                result = new PlayUnitMove();
                result.game = context.game;
                result.owner = context.game.currentPlayer;
                result.overNetwork = true;
                ((PlayUnitMove) result).toPlayOn = context.game.getBoard().getCellAtIndex(PerceptionFlipper.flipIndex(((PlayCardMove) input).toPlayOn.getIndex()));
                ((PlayUnitMove) result).toPlay = createIdentity(context, ((PlayCardMove) input).toPlay);
                ((PlayUnitMove) result).direction = PerceptionFlipper.flipDirection(((PlayUnitMove) input).getDirection());
            }
            else if(input instanceof PlayItemMove){
                result = new PlayItemMove();
                result.game = context.game;
                result.owner = context.game.currentPlayer;
                result.overNetwork = true;
                ((PlayItemMove) result).toPlay = (ItemCard) createItem(context, ((PlayItemMove) input).toPlay);
                ((PlayItemMove) result).attachedTo = (IdentityCard) syncPersistentCard(context, ((PlayItemMove) input).attachedTo);
            }
            else if(input instanceof PlayConstructionMove){
                result = new PlayConstructionMove();
                result.game = context.game;
                result.owner = context.game.currentPlayer;
                result.overNetwork = true;
                ((PlayConstructionMove) result).toPlayOn = context.game.getBoard().getCellAtIndex(PerceptionFlipper.flipIndex(((PlayConstructionMove) input).toPlayOn.getIndex()));
                ((PlayConstructionMove) result).toPlay = (ConstructionCard) createConstruction(context, ((PlayConstructionMove) input).toPlay);
                ((PlayConstructionMove) result).direction = PerceptionFlipper.flipDirection(((PlayConstructionMove) input).getDirection());
            }
            else{
                result.game = context.game;
                result.owner = context.game.currentPlayer;
                result.overNetwork = true;
                ((PlayCardMove) result).toPlayOn = context.game.getBoard().getCellAtIndex(PerceptionFlipper.flipIndex(((PlayCardMove) input).toPlayOn.getIndex()));
                ((PlayCardMove) result).toPlay = create(context, ((PlayCardMove) input).toPlay);
            }
        }
        if(input instanceof RotateUnitMove){
            result = new RotateUnitMove();
            result.game = context.game;
            result.owner = context.game.currentPlayer;
            result.overNetwork = true;
            ((RotateUnitMove) result).toRotate = (IdentityCard) syncPersistentCard(context, ((RotateUnitMove) input).toRotate);
            ((RotateUnitMove) result).direction = PerceptionFlipper.flipDirection(((RotateUnitMove) input).direction);
        }
        if(input instanceof MoveCardMove){
            result = new MoveCardMove();
            result.game = context.game;
            result.owner = context.game.currentPlayer;
            result.overNetwork = true;
            ((MoveCardMove) result).toMoveTo = context.game.getBoard().getCellAtIndex(PerceptionFlipper.flipIndex(((MoveCardMove) input).toMoveTo.getIndex()));
            Log.d("toMoveTo", PerceptionFlipper.flipIndex(((MoveCardMove) result).toMoveTo.getIndex()) + "");
            Log.d("toMoveTo_Notflipped", ((MoveCardMove) result).toMoveTo.getIndex() + "");
            ((MoveCardMove) result).toMove = (IdentityCard) syncPersistentCard(context, ((MoveCardMove) input).toMove);
            ((MoveCardMove) result).direction = ((MoveCardMove) input).direction;
        }
        if(input instanceof AttackMove){
            result = new AttackMove();
            result.game = context.game;
            result.owner = context.game.currentPlayer;
            result.overNetwork = true;
            ((AttackMove) result).attacker = createIdentity(context, ((AttackMove) input).attacker);
            ((AttackMove) result).attacked = createIdentity(context, ((AttackMove) input).attacked);
        }
        if(input instanceof ActivateEffectMove){
            ((ActivateEffectMove) result).activator = (IdentityCard) syncPersistentCard(context, ((ActivateEffectMove) input).activator);
        }
        if(input instanceof SeizeMove){
            result = new SeizeMove();
            result.game = context.game;
            result.owner = context.game.currentPlayer;
            result.overNetwork = true;
            ((SeizeMove) result).seizer = (IdentityCard) syncPersistentCard(context, ((SeizeMove) input).seizer);
            ((SeizeMove) result).toSeize = context.game.board.getCellAtIndex(PerceptionFlipper.flipIndex(((SeizeMove) input).toSeize.getIndex()));
        }
        if(input instanceof EndMainPhaseMove){
            result = new EndMainPhaseMove();
            result.game = context.game;
            result.owner = context.game.currentPlayer;
            result.overNetwork = true;
        }
        return result;
    }

    public static Card create(GameActivity context, Card input){
        Card result = new Card();
        result.game = context.game;
        result.owner = context.game.getPlayerByName(input.owner.getName());
        result.currentCell = context.game.getBoard().getCellAtIndex(PerceptionFlipper.flipIndex(input.getCell().getIndex()));
        if(input instanceof CraftCard){
            ((CraftCard) result).activator =  syncPersistentCard(context, ((CraftCard) input).activator);
        }
        return result;
    }

    public static IdentityCard createIdentity(GameActivity context, Card input){
        Class<?> cls = input.getClass();
        Object result = null;
        try {
            result = cls.getConstructor(Player.class).newInstance(context.game.currentPlayer);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //((IdentityCard) result).game = context.game;
        //((IdentityCard) result).owner = context.game.getPlayerByName(input.owner.getName());
        ((IdentityCard) result).game = context.game;
        ((IdentityCard) result).currentCell = context.game.getBoard().getCellAtIndex(PerceptionFlipper.flipIndex(input.getCell().getIndex()));
        return (IdentityCard) result;
    }

    public static ItemCard createItem(GameActivity context, Card input){
        Class<?> cls = input.getClass();
        Object result = null;
        try {
            result = cls.getConstructor(Player.class).newInstance(context.game.currentPlayer);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //((IdentityCard) result).game = context.game;
        //((IdentityCard) result).owner = context.game.getPlayerByName(input.owner.getName());
        ((ItemCard) result).game = context.game;
        //((ItemCard) result).currentCell = context.game.getBoard().getCellAtIndex(PerceptionFlipper.flipIndex(input.getCell().getIndex()));
        return (ItemCard) result;
    }

    public static ConstructionCard createConstruction(GameActivity context, Card input){
        Class<?> cls = input.getClass();
        Object result = null;
        try {
            result = cls.getConstructor(Player.class).newInstance(context.game.currentPlayer);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        ((ConstructionCard) result).game = context.game;
        return (ConstructionCard) result;
    }

    public static Card syncPersistentCard(GameActivity context, Card input){
        Log.d("syncPersistentCard", PerceptionFlipper.flipIndex(((PersistentCard) input).currentCell.getIndex()) + "");
        if(context.game.board.getCellAtIndex(PerceptionFlipper.flipIndex(((PersistentCard) input).currentCell.getIndex())).hasUnit()){
            Log.d("syncPersistentCard", "Has unit");
        }
        return context.game.board.getCellAtIndex(PerceptionFlipper.flipIndex(((PersistentCard) input).currentCell.getIndex())).getIdentity();
    }


}
