package com.example.evan.projectcard.Engine;
import java.util.HashMap;

public class UntrainedPolicy{
	public static FeatureCollection features = new FeatureCollection();
	static{
		features.put("seizedCells", new Feature(0.996));
		features.put("proximityToSeizableCells", new Feature(0.971));
		features.put("opponentroximityToSeizableCells", new Feature(0.954));
		features.put("unitVirtue", new Feature(0.125));
		features.put("opponentUnitCount", new Feature(0.829));
		features.put("cardsOnBoard", new Feature(0.748));
		features.put("opponentCardsOnBoard", new Feature(0.062));
		features.put("opponentUnitVirtue", new Feature(0.277));
		features.put("unitsOnSeizableCells", new Feature(0.599));
		features.put("opponentUnitsOnSeizableCells", new Feature(0.016));
	}

	public static Heuristic getBestHeuristic(Board board, Player perspective) {
		FeatureCollection features = getFeatures();
		features.setValue("seizedCells", board.getCellsOwnerBy(perspective).size());
		features.setValue("proximityToSeizableCells", getProximityToSeizableCells(board, perspective));
		features.setValue("opponentroximityToSeizableCells", getProximityToSeizableCells(board, perspective.getOpponent()));
		features.setValue("unitVirtue", getUnitVirtue(board, perspective));
		features.setValue("opponentUnitCount", getOpponentUnitCount(board, perspective));
		features.setValue("cardsOnBoard", getCardsOnBoard(board, perspective));
		features.setValue("opponentCardsOnBoard", getCardsOnBoard(board, perspective.getOpponent()));
		features.setValue("opponentUnitVirtue", getUnitVirtue(board, perspective.getOpponent()));
		features.setValue("unitsOnSeizableCells", getUnitsOnSeizableCells(board, perspective));
		features.setValue("opponentUnitsOnSeizableCells", getUnitsOnSeizableCells(board, perspective.getOpponent()));
		double result = 0;
		for(Feature feature: features){
			result = result + feature.calculate();
		}
		return new Heuristic(result);
	}
	
	public static FeatureCollection getFeatures(){
		return features;
	}

	private static int getUnitVirtue(Board board, Player perspective) {
		int result = 0;
		CardCollection units = board.getCurrentUnits(perspective.getOpponent());
		for(Card card: units){
			result += ((IdentityCard) card).currentHP;
			result += ((IdentityCard) card).currentAP;
			result += ((IdentityCard) card).currentATK;
		}
		return result;
	}

	private static int getUnitsOnSeizableCells(Board board, Player perspective) {
		int result = 0;
		CardCollection units = board.getCurrentUnits(perspective);
		for(Card card: units){
			if((card.currentCell.owner != perspective)){
				result += 1;
			}
		}
		return result;
	}

	private static int getCardsOnBoard(Board board, Player perspective) {
		CardCollection units = board.getCurrentPersistentsAndItems(perspective);
		return units.size();
	}

	private static int getOpponentUnitCount(Board board, Player perspective) {
		CardCollection units = board.getCurrentUnits(perspective.getOpponent());
		return units.size();
	}

	private static int getAPHeuristic(Board board, Player perspective) {
		int result = 0;
		CardCollection units = board.getCurrentUnits(perspective);
		for(Card card: units){
			result += ((IdentityCard) card).currentAP;
		}
		return result;
	}

	public static int getProximityToSeizableCells(Board board, Player perspective){
		int result = 0;
		CardCollection units = board.getCurrentUnits(perspective);
		for(Card card: units){
			CellCollection adj = Algorithmics.getAdjacentCells(card.getCell(), board);
			for(Cell cell: adj){
				if((cell.owner != perspective) && !cell.hasUnit()){
					result++;
				}
			}
		}
		return result;
	}
}
