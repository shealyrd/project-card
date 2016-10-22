package com.example.evan.projectcard.Engine;
public class MoveNode{
    Move move;
    Heuristic bestHeuristic;
    
    public MoveNode(Move move){
    	this.move = move;
    }
    
    public Heuristic getBestHeuristic(Board board, int depth){
        PossibleMoveCollection moves = new PossibleMoveCollection();
        Board newBoard = board.applyMove(move);
        bestHeuristic = UntrainedPolicy.getBestHeuristic(newBoard, newBoard.game.currentPlayer);
        if(depth <= 0){
            return bestHeuristic;
        }
        for(Move move: moves){
            Heuristic h = new MoveNode(move).getBestHeuristic(newBoard, depth - 1);
                if(h.isBetterThan(bestHeuristic)){
                    bestHeuristic = h;
                }
        }
        return bestHeuristic;
    }
}
