package com.example.evan.projectcard.Engine;

public class RotateUnitMove extends Move {
	public IdentityCard toRotate;
	public Direction direction;

	public RotateUnitMove(){}
	public RotateUnitMove(Player owner, IdentityCard unit, Direction direction) {
		super(owner);
		this.toRotate = unit;
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public IdentityCard getRotatedCard() {
		return toRotate;
	}

	public String toString(){
		return "Rotate " + toRotate.getName() + " " + direction.toString();
	}
}
