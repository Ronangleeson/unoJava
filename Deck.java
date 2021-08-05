import java.util.*;
public class Deck {

    // Attributes
    Card topCard;
    ArrayList<Card> cards;
    ArrayList<Card> discardedCards;

    // Constructor
    Deck() {
        this.cards = createCards();
        this.topCard = drawCard();
        this.discardedCards = new ArrayList<>();
    }

    // Fill deck with cards & shuffle their order
    public ArrayList<Card> createCards() {
        // Create the deck of cards & shuffle them
        String[] colors = {"Red", "Blue", "Green", "Yellow"};
        ArrayList<Card> cards = new ArrayList<>();
        for (String color : colors) {
            Card zero = new Card(color,0);
            cards.add(zero);
            for (int i = 1; i < 10; i++) {
                Card temp1 = new Card(color, i);
                Card temp2 = new Card(color, i);
                cards.add(temp1);
                cards.add(temp2);
            }
        }
        Collections.shuffle(cards);
        return cards;
    }

    // Remove a card from the deck and return it
    public Card drawCard() {
        return this.cards.remove(0);
    }

    // Print the contents of the deck
    private void printDeck() {
        for (Card c : this.cards) {
            System.out.println(c.color + " " + c.number);
        }
    }

    public static void main(String[] args) {

    }
}
