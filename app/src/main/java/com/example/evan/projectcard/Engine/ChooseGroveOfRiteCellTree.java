package com.example.evan.projectcard.Engine;

public class ChooseGroveOfRiteCellTree {
	Board board;
	Card_GroveOfRite activating;
    Heuristic bestHeuristic = Heuristic.getMinimum();
    

    public ChooseGroveOfRiteCellTree(Board board, Card_GroveOfRite activating){
        this.board = board;
        this.activating = activating;
    }
    
    public int getBestIndex(){
    	int result = 0;
        for(Cell cell: Algorithmics.getAdjacentCells(activating.getCell(), board)){
        	Board newBoard = null;
			try {
				newBoard = (Board) Cloning.deepCopy(board);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Card_GroveOfRite groveClone = null;
			try {
				groveClone = (Card_GroveOfRite) Cloning.deepCopy(activating);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new GroveOfRiteEffectFrame(board.game, groveClone, cell.index).launch();
            Heuristic localMax = UntrainedPolicy.getBestHeuristic(newBoard, newBoard.game.currentPlayer);
            if(localMax.isBetterThan(bestHeuristic)){
                bestHeuristic = localMax;
                result = cell.index;
            }
        }
        return result;
    }
}
