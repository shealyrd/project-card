package com.example.evan.projectcard.Engine;

public class MoveGenerator {
	public static PossibleMoveCollection generatePossibleMoves(Board board, Player player){
		PossibleMoveCollection possibleMoves = new PossibleMoveCollection();
		Hand hand = player.getHand();
		for(Card card: hand){
			if(player.canPayCost(card.getCost())){
				possibleMoves.append(generatePossiblePlayCardMoves(card, board));
			}
		}
		CardCollection availableUnits = board.getCurrentUnits(player);
		for(Card unit: availableUnits){
			possibleMoves.append(generatePossibleRotateUnitMoves(unit, board));
			possibleMoves.append(generatePossibleMoveCardMoves(unit, board));
			possibleMoves.append(generateBattleMoves(unit, board));
			if(unit.getCell().canBeSeizedBy((IdentityCard) unit)){
				possibleMoves.append(new SeizeMove(player, (IdentityCard) unit, unit.getCell()));
			}
		}
		CardCollection availablePerms = board.getCurrentPersistentsAndItems(player);
		for(Card card: availablePerms){
			if(card.canActivate()){
				possibleMoves.append(new ActivateEffectMove(player, card));
			}
		}
		possibleMoves.append(new EndMainPhaseMove(player));
		return possibleMoves;
		
	}


	private static PossibleMoveCollection generateBattleMoves(Card unit, Board board) {
		PossibleMoveCollection possibleMoves = new PossibleMoveCollection();
		CellCollection adjacents = Algorithmics.getAdjacentCells(unit.getCell(), board);
		for(Cell cell: adjacents){
			if(cell.hasUnit()){
				if(((IdentityCard) unit).canAttack(cell.getIdentity())){
						possibleMoves.append(new AttackMove(unit.getOwner(), (IdentityCard) unit, cell.getIdentity()));
					}
				}
			}
		return possibleMoves;
	}
	
	private static PossibleMoveCollection generatePossibleMoveCardMoves(Card unit, Board board) {
		PossibleMoveCollection possibleMoves = new PossibleMoveCollection();
		//CellCollection adjacentCells = Algorithmics.getAdjacentCells(unit.getCell());
		//for(Cell cell: adjacentCells){
		if(((IdentityCard) unit).canMove()){
			possibleMoves.appendMoveCardMoves(unit.owner, (IdentityCard) unit);
		}
		//}
		return possibleMoves;
	}

	private static PossibleMoveCollection generatePossibleRotateUnitMoves(Card unit, Board board) {
		PossibleMoveCollection possibleMoves = new PossibleMoveCollection();
		if(((IdentityCard) unit).canTurn()){
			possibleMoves.appendRotateUnitMoves(unit.owner, (IdentityCard) unit);
		}
		return possibleMoves;
	}

	private static PossibleMoveCollection generatePossiblePlayCardMoves(Card card, Board board) {
		PossibleMoveCollection possibleMoves = new PossibleMoveCollection();
		CellGrid cells = board.getCellGrid();
		for(Cell cell: cells){
			if(card instanceof PersistentCard){
				if(((PersistentCard) card).canPlayIn(cell)){
					if(card instanceof IdentityCard){
						possibleMoves.appendPlayUnitMoves(card.owner, (IdentityCard) card, cell);
					}
					if(card instanceof ConstructionCard){
						possibleMoves.appendPlayConstructionMoves(card.owner, (ConstructionCard) card, cell);
					}
				}
			}
		}
		if(card instanceof CraftCard){
				CardCollection availableUnits = board.getCurrentUnits(card.owner);
				for(Card unit: availableUnits){
					if((((IdentityCard) unit).canUseCraft((CraftCard) card))){
						Move newMove = new PlayCraftMove(card.owner, card, (IdentityCard) unit);
						possibleMoves.append(newMove);
					}
				}
			}
		if(card instanceof ItemCard){
				CardCollection availableUnits = board.getCurrentUnits(card.owner);
				for(Card unit: availableUnits){
					if(((ItemCard) card).canAttachTo((IdentityCard) unit)){
						Move newMove = new PlayItemMove(card.owner, card, (IdentityCard) unit);
						possibleMoves.append(newMove);
					}
				}
		}
		return possibleMoves;
	}
}
