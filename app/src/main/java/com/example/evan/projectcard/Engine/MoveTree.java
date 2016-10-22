package com.example.evan.projectcard.Engine;

public class MoveTree{
    PossibleMoveCollection moves;
    Board board;
    Heuristic bestHeuristic = Heuristic.getMinimum();
    Move bestMove;
    int depth;

    public MoveTree(PossibleMoveCollection moves, Board board, int depth){
        this.moves = moves;
        this.board = board;
        this.depth = depth;
    }
 
    
    public Move getBestMove(){
        for(Move move: moves){
        	MoveNode node = new MoveNode(move);
            Heuristic localMax = node.getBestHeuristic(board, depth);
            if(localMax.isBetterThan(bestHeuristic)){
                bestHeuristic = localMax;
                bestMove = move;
            }
        }
        return bestMove;
    }
}
