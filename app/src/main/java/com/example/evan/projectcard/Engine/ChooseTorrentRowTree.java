package com.example.evan.projectcard.Engine;

public class ChooseTorrentRowTree {
	Board board;
    Heuristic bestHeuristic = Heuristic.getMinimum();
    

    public ChooseTorrentRowTree(Board board){
        this.board = board;
    }
    
    public int getBestIndex(){
    	int result = 0;
        for(Cell cell: board.getColumnByIndex(1)){
        	Board newBoard = null;
			try {
				newBoard = (Board) Cloning.deepCopy(board);
			} catch (Exception e) {
				e.printStackTrace();
			}
			new TorrentEffectFrame(board.game, Algorithmics.getRow(cell.index)).launch();
            Heuristic localMax = UntrainedPolicy.getBestHeuristic(newBoard, newBoard.game.currentPlayer);
            if(localMax.isBetterThan(bestHeuristic)){
                bestHeuristic = localMax;
                result = Algorithmics.getRow(cell.index);
            }
        }
        return result;
    }
}
