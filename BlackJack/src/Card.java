
public class Card {
	protected char suit;
	protected int value;
	protected boolean isShown;
	
	public Card(int value, char suit) {
		this.value = value;
		this.suit = suit;
	}
	
	/*
	 * Used to retrieve type of card (value, suit, etc.)
	 */
	public int getValue(){
		return this.value;
	}
	
	/*
	 * Returns the actual numerical value of a card
	 */
	public int getScore(){
		if(this.value != 14 && this.value > 10){
			return 10;
		}
		else if(this.value == 14){
			return 11;
		}
		else{
			return this.value;
		}
	}
	
	public char getSuit(){
		return this.suit;
	}

}
