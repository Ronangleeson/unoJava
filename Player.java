import java.util.ArrayList;

public class Player {

    // Attributes
    int playerNumber;
    ArrayList<Card> hand;
    int numCards;

    // Constructor
    Player(int number, ArrayList<Card> cards, int startingCards) {
        this.playerNumber = number;
        this.hand = cards;
        this.numCards = startingCards;
    }

    // Method that prints a player's hand
    public void printHand() {
        for (int i = 0; i < this.numCards; i++) {
            System.out.println((i + 1) + ": " + this.hand.get(i).color + " " + this.hand.get(i).number);
        }

    }

    public static void main(String[] args) {

    }
}
