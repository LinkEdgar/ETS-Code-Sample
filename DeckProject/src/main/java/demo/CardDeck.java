import java.util.*;
public class CardDeck{
	/*
	This class represents a deck of cards and on creation the deck is initialized in order by
	suit. Starting with the hearts suit and ending with diamonds. The cards are represented 
	with their value so ace = 1, jack = 11, queen = 12, and kind =13.


	*/
	//keeps tracks of how many cards we've dealt
	private int cardsDealt = 0;
	//an array that will hold all of our cards
	private String[] deck;
	private static final int DECK_SIZE = 52;
	private String[] faceValuesArray = {"hearts", "spades", "clubs", "diamonds"};
	
	public CardDeck(){
		deck = initializeDeck();
	}

	private String[] initializeDeck(){
		String[] deck = new String[DECK_SIZE];
		int valueCounter = 0;
		String currentFaceValue = faceValuesArray[valueCounter];
		//each suit has 13 cards therefore 13 is the limit per suit
		int suitChangeLimit = 13;
		//we'll fill the deck with each card where the number represents its face value 
		for(int x = 0; x< DECK_SIZE; x++){
			//using 13 modulus arithmatic on card number we are able to stay in the bound of our card numbers
			int cardNumber = (x+1)%13;
			//if the card is equal to our limit we must change the face value and our suit limit 
			if(x+1 == suitChangeLimit){
				//we must change this value since the modulus arithmatic yields 0
				cardNumber = 13;
				deck[x] = getProperCardName(cardNumber) + " of " + currentFaceValue;
				//we'll change out suit value so that it properly corresponds to the array index
				suitChangeLimit = suitChangeLimit +13;
				//changes our face value once we reach the limit of our current suit
				valueCounter = valueCounter +1;
				// a check so that we don't get an array out of bound error
				if(valueCounter <= 3){
					currentFaceValue = faceValuesArray[valueCounter];

				}
			}
			else{
			//deck[x]  = cardNumber + " of " + currentFaceValue;
				deck[x] = getProperCardName(cardNumber) + " of " + currentFaceValue;
			}
		}

		return deck;
	}

	public void printDeck(){
		for(int i = 0; i < DECK_SIZE; i++){
			System.out.println(deck[i]);
		}
	}

	public static void main(String[] args){
		CardDeck deck = new CardDeck();
		//prints the initialized deck as is
		deck.printDeck();
		deck.shuffle();
		//a line space for the sake of sanity
		System.out.println("  ");
		//prints the deck after it's shuffled 
		deck.printDeck();
		//another space for sanity 
		System.out.println("  ");
		//test for the dealOneCard function 
		for(int x =0 ; x <52; x++){
			System.out.println(deck.dealOneCard());
		
		}
	
				
	}
	//generates a random number between 0 and 51 for array indices 
	public int generateRandomNum(){
		int min = 0;
		int max = 51;
		Random rand = new Random();
		int n = rand.nextInt((max-min)+1)+min;
		return n;
	}

	public void shuffle(){
		for(int i = 0; i < DECK_SIZE;i++){
			int swapPosition = generateRandomNum();
			String swapString = deck[swapPosition];
			deck[swapPosition] = deck[i];
			deck[i] = swapString; 
		}

	}

	public String dealOneCard(){
		//dealtCardSet.add(deck[0]);
		String cardToReturn = deck[cardsDealt];
		//if no card can be dealth then a statement is printed and null is returne
		if(cardsDealt == DECK_SIZE){
			System.out.println("All cards have been dealt");
			return null;
		}
		cardsDealt++;
		return cardToReturn;
	}
	//this method will convert the value of the card to its proper name if it has one
	// so a card with the value of 1 will be relabelled to ace 
	private String getProperCardName(int cardValue){
		String stringCardValue = null;

		switch(cardValue){
			case 1: stringCardValue = "Ace";
					break;
			case 11: stringCardValue = "Jack";
					break;
			case 12: stringCardValue = "Queen";
			
			case 13: stringCardValue = "King";
					break;
			default: stringCardValue = ""+cardValue;
		}

		return stringCardValue;
	}
}