

public class Deck {
	
	final static int DECK_SIZE = 52;
	
	protected Card[] cards;
	protected int numCards;

	public Deck() {
		numCards = DECK_SIZE;
		cards = new Card[numCards];
		createDeck();
	}
	
	/*
	 * Generate all cards for deck
	 */
	private void createDeck(){
		int total = 0;
		//Create Clubs
		for(int i = 2; i < 15; i++){
			cards[total] = new Card(i, 'C');
			total++;
		}
		//Create Diamonds
		for(int i = 2; i < 15; i++){
			cards[total] = new Card(i, 'D');
			total++;
		}
		//Create Hearts
		for(int i = 2; i < 15; i++){
			this.cards[total] = new Card(i, 'H');
			total++;
		}
		//Create Spades
		for(int i = 2; i < 15; i++){
			cards[total] = new Card(i, 'S');
			total++;
		}
	}
	
	public Card[] getCards(){
		return this.cards;
	}
	
	/*
	 * Shuffles the entire deck
	 */
	public void shuffle(){
		for(int i = 0; i < DECK_SIZE; i++){
			int r = (int) (Math.random() * DECK_SIZE);
			if(i != r){
				Card swap = cards[i];
				cards[i] = cards[r];
				cards[r] = swap;
			}
		}
	}
	
	/*
	 * Used to deal a card, remove it from deck like reality
	 */
	public Card dealFrom(){
		/*
		 * If we run out of cards in a deck, we regenerate the deck
		 */
		if(this.isEmpty() || numCards < 0){
			numCards = DECK_SIZE;
			cards = new Card[numCards];
			createDeck();
			shuffle();
		}
		return cards[--numCards];
	}
	
	public boolean isEmpty(){
		return(numCards == 0);
	}

}
