package com.example.evan.projectcard.Engine;

public class ChooseFireballCellTree {
	Board board;
    Heuristic bestHeuristic = Heuristic.getMinimum();
    

    public ChooseFireballCellTree(Board board){
        this.board = board;
    }
    
    public int getBestIndex(){
    	int result = 0;
        for(Cell cell: board.cellGrid){
        	Board newBoard = null;
			try {
				newBoard = (Board) Cloning.deepCopy(board);
			} catch (Exception e) {
				e.printStackTrace();
			}
			new FireballEffectFrame(board.game, cell.index).launch();
            Heuristic localMax = UntrainedPolicy.getBestHeuristic(newBoard, newBoard.game.currentPlayer);
            if(localMax.isBetterThan(bestHeuristic)){
                bestHeuristic = localMax;
                result = cell.index;
            }
        }
        return result;
    }
}
