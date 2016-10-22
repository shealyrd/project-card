package com.example.evan.projectcard.Engine;
import java.io.Serializable;
import java.util.ArrayList;

public class IdentityCard extends PersistentCard implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6315089267727751812L;
	int defaultHP;
	int defaultATK;
	int defaultAP;
	
	int currentMaxHP;
	int currentHP;
	int currentATK;
	//int currentMaxAP;
	int currentAP;
	
	
	boolean canSeize = true;
	CardCollection items = new CardCollection();
	//CounterAttackRightManager canCounterAttack = new CounterAttackRightManager();
	
	public IdentityCard(){}
	

	public IdentityCard(String name, KeywordCollection keywords, Player owner, String text, ResourceCost cost, int HP, int ATK, int AP) {
		super(name, keywords, owner, text, cost);
		this.defaultHP = HP;
		this.defaultATK = ATK;
		this.defaultAP = AP;
		this.currentMaxHP = HP;
		this.currentHP = HP;
		this.currentATK = ATK;
		//this.currentMaxAP = AP;
		this.currentAP = AP;
	}
	

	public void resetHP(){
		currentMaxHP = defaultHP;
	}
	
	public void resetATK(){
		currentATK = defaultATK;
	}
	
	public void resetAP(){
		//currentMaxAP = currentMaxAP;
	}
	
	public void restoreAllHP(){
		currentHP = currentMaxHP;
	}
	
	public void restoreAllAP(){
		currentAP = defaultAP;
	}
	
	public void resetStats(){
		resetHP();
		resetATK();
		resetAP();
	}
	

	public Frame onPlay(PlayUnitFrame playUnitFrame) {
		// TODO Auto-generated method stub
		return null;
	}


	public void addItem(ItemCard toPlay) {
		items.add(toPlay);
	}


	public void inflictDamage(int ATK) {
		int calc = currentHP - ATK;
		if(calc > 0){
			currentHP = calc;
		}
		if(calc <= 0){
			destroy();
		}

	}
	
	public void destroy() {
		new DestroyUnitFrame(game, this).launch();
	}

	public int getCurrentHP() {
		return currentHP;
	}
	public int getCurrentAP() { return currentAP; }
	public int getCurrentATK() {
		return currentATK;
	}


	public boolean isAlive() {
		if(status == FieldStatus.Field){
			return true;
		}
		return false;
	}
	
	public boolean canAttack(IdentityCard toAttack){
		if(!canPayAPCost(1)){
			return false;
		}
		CellCollection adjacent = Algorithmics.getAdjacentCells(getCell(), game.getBoard());
		if(!adjacent.contains(toAttack.getCell())){
			return false;
		}
		if(!isFacing(toAttack.getCell())){
			return false;
		}
		if(toAttack.getOwner() == getOwner()){
			return false;
		}
		CellSideStatus sideBetween = null;
		switch(getDirection()){
		case UP:
			sideBetween = toAttack.getCell().downSide;
			break;
		case RIGHT:
			sideBetween = toAttack.getCell().leftSide;
			break;
		case DOWN:
			sideBetween = toAttack.getCell().upSide;
			break;
		case LEFT:
			sideBetween = toAttack.getCell().rightSide;
			break;
		}
		if(sideBetween == CellSideStatus.BLOCKED){
			return false;
		}
		return true;
	}


	public boolean isFacing(IdentityCard attacker) {
		//we will assume that they are adjacent
		if(direction == Algorithmics.getDirectionNeededToFace(currentCell, attacker.getCell())){
			return true;
		}
		return false;
	}


	public boolean hasItem(String string) {
		for(Card item: items){
			if(item.name == string){
				return true;
			}
		}
		return false;
	}


	public boolean canUseCraft(CraftCard craft) {
		if(!craft.canBeUsedBy(this)){
			return false;
		}
		return true;
	}
	
	public void destroyAllItems(){
		ArrayList<Card> itemAL = (ArrayList<Card>) items.getCopyOfArrayList();
		for(Card item: itemAL){
			new DestroyItemFrame(game, (ItemCard) item).launch();
		}
	}

	public void onBattleResolve(BattleFrame frame){}

   /*
	public void addCounterAttackRight(CounterAttackRight right) {
		canCounterAttack.addRight(right);
	}
	
	public void removeCounterAttackRight(CounterAttackRight right){
		canCounterAttack.removeRight(right);
	}
	*/


	public int getMaxHP() {
		return currentMaxHP;
	}


	public void setMaxHP(int i) {
		currentMaxHP = i;
	}


	public boolean isFacingAUnit() {
		if(Algorithmics.isFacingCell(this)){
			Cell cell = Algorithmics.getCellFacing(this);
			if(cell.hasUnit()){
				return true;
			}
		}
		return false;
	}



	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("CurrentHP: " + currentHP + "\n");
		builder.append("ATK: " + currentATK + "\n");
		builder.append("AP: " + currentAP + "\n");
		builder.append("Direction: " + direction + "\n");
		return builder.toString();
	}


	public boolean hasItem() {
		if(items.size() > 0){
			return true;
		}
		return false;
	}


	public CardCollection getItems() {
		return items;
	}


	public void removeItem(ItemCard destroyed) {
		items.remove(destroyed);
		destroyed.onUnattach(this);
	}


	public void refreshItems() {
		for(Card card: items){
			card.setCell(this.getCell());
		}
	}
	
	public void reduceAPBy(int i){
		currentAP = currentAP - i;
	}
	
	public boolean canPayAPCost(int i){
		return (currentAP >= i);
	}


	public CardCollection getAllCards() {
		CardCollection result = new CardCollection();
		result.add(this);
		result.add(items);
		return result;
	}


	public boolean canSeize(Cell cell) {
		if(canSeize == false){
			return false;
		}
		if(canPayAPCost(1) == false){
			return false;
		}
		if(getOwner() == cell.getOwner()){
			return false;
		}
		return true;
	}
	
	public void onAttack(){
		if(!game.ignoreResourceModeOn()){
			reduceAPBy(1);
			if(hasItem("Bigass Anime Sword")){
				setAP(0);
			}
		}
	}

	public boolean canTurn() {
		if(canPayAPCost(1) == false){
			return false;
		}
		return true;
	}
	
	public boolean canMove() {
		if(canPayAPCost(1) == false){
			return false;
		}
		return true;
	}
	

	public void setAP(int i) {
		currentAP = i;
	}












	
	
}
