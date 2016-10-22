package com.example.evan.projectcard.Engine;

public class ChooseThunderboltCellTree {
	Board board;
    Heuristic bestHeuristic = Heuristic.getMinimum();
    

    public ChooseThunderboltCellTree(Board board){
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
			new ThunderboltEffectFrame(board.game, cell.index).launch();
            Heuristic localMax = UntrainedPolicy.getBestHeuristic(newBoard, newBoard.game.currentPlayer);
            if(localMax.isBetterThan(bestHeuristic)){
                bestHeuristic = localMax;
                result = cell.index;
            }
        }
        return result;
    }
}
