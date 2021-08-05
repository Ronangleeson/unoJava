import org.w3c.dom.Node;

import java.util.*;


public class Game {
    // Game settings
    static int numPlayers = 2;
    static int startingCards = 3;
    static boolean infiniteDraw = true;
    static Scanner scan = new Scanner(System.in);


    // Game attributes
    ArrayList<Player> players = new ArrayList<>();
    Deck deck;
    int playersLeft;
    int mostRecentPlayer;
    int currentPlayerIndex;

    // Game constructor: Game is home to the other classes that make up Uno
    Game() {
        // create a new Deck
        this.deck = new Deck();
        // draw cards from that deck to give to players
        for (int i = 0; i < numPlayers; i++) {
            ArrayList<Card> tempHand = new ArrayList<>();
            for (int j = 0; j < startingCards; j++) {
                tempHand.add(this.deck.drawCard());
            }
            Player tempPlayer = new Player(i + 1, tempHand, startingCards);
            this.players.add(tempPlayer);
        }

        this.playersLeft = numPlayers;
        this.mostRecentPlayer = 1;
        this.currentPlayerIndex = 0;
    }

    public static<E> void p(E data) {
        System.out.println(data);
    }

    private void startGame() {
        p("Welcome to Uno! This game was made by Ronan Gleeson");
        p("");
        makeMove();
    }


    private void makeMove() {


        while (true) {

            // get current player from current player index and prompt them to reveal their cards
            Player currentPlayer = this.players.get(this.currentPlayerIndex);
            p("Player " + currentPlayer.playerNumber + ", is is your turn");
            System.out.print("Press enter when ready to see cards: ");
            String ready = scan.nextLine();
            currentPlayer.printHand();

            // check if the player has a valid move
            // if they do, let them select a card to play
                // must also check that what they select is valid
            // if they do not, make them draw card(s)

            // first check that the Player has a valid move
            // if not, make them draw a card
            if (combinedCheck(currentPlayer) == true) {
                p("");
                p("Top card: " + this.deck.topCard.cardInfo());
                p("Enter the number of the card you wish to play: ");
                int cardNumber;
                while (true) {
                    cardNumber = scan.nextInt();
                    if (validCard(currentPlayer.hand.get(cardNumber - 1))) {
                        break;
                    }
                    else {
                        p("Invalid card, pick another card");
                    }
                }

                // update the top card, place the former played card in the discarded cards
                Card playedCard = currentPlayer.hand.remove(cardNumber - 1);
                currentPlayer.numCards--;
                Card formerTopCard = this.deck.topCard;
                this.deck.discardedCards.add(formerTopCard);
                this.deck.topCard = playedCard;
            }

            System.out.println("Press enter to end your turn: ");
            scan.nextLine();
            if (victory() == true) {
                break;
            }
            incrementPlayerIndex();
        }
        System.out.println("Player " + this.currentPlayerIndex + " has won!");
    }

    private void checkIfPowerCard() {


    }



    private void incrementPlayerIndex() {
        if (this.currentPlayerIndex + 1 == this.numPlayers) {
            this.currentPlayerIndex = 0;
        } else {
            this.currentPlayerIndex++;
        }
    }

    // check if a player has a valid move
    // If not, make them draw a card
    // If they still do not have a valid card, return false (otherwise return true)
    private boolean checkCards(Player player) {
        if (hasValidMove(player) == false) {
            p("No valid cards, press enter to draw a card: ");
            scan.nextLine();
            Card newCard = this.deck.drawCard();
            System.out.println("New card: " + newCard.color + " " + newCard.number);
            player.hand.add(newCard);
            player.numCards++;
        }
        if (hasValidMove(player)) {
            return true;
        }
        return false;
    }

    // see if player has valid move
    // if they do not, make them draw card(s)
    // if they do, just return true
    private boolean combinedCheck(Player player) {
        // Player has valid move already, no need to make them draw cards
        if (hasValidMove(player)) {
            return true;
        }
        // Otherwise, make them draw a card, then check if they have a valid move
        else {
            p("No valid cards, press enter to draw a card: ");
            scan.nextLine();
            Card newCard = this.deck.drawCard();
            System.out.println("New card: " + newCard.color + " " + newCard.number);
            player.hand.add(newCard);
            player.numCards++;
            if (hasValidMove(player) == true) {
                return true;
            } else {
                if (infiniteDraw == true) {
                    combinedCheck(player);
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }

    // Checks if a player has a valid move
    private boolean hasValidMove(Player p) {
        for (int i = 0; i < p.numCards; i++) {
            if (p.hand.get(i).number == this.deck.topCard.number || p.hand.get(i).color == this.deck.topCard.color) {
                return true;
            }
        }
        return false;
    }

    // Checks if the card a player wants to play is valid to be played
    private boolean validCard(Card c) {
        if (c.number == this.deck.topCard.number || c.color == this.deck.topCard.color) {
            return true;
        }
        return false;
    }

    // checks if a player has won the game
    private boolean victory() {
        for (Player p : this.players) {
            if (p.numCards == 0) {
                return true;
            }
        }
        return false;
    }

    private void endGame(int currentPlayer) {
        System.out.println();
    }



//    public static int[] findPlayerOrder(int currentPlayer, CircularLinkedList list) {
//        int[] playerOrder = new int[4];
//
//
//
//        return playerOrder;
//    }


//    private int[] determinePlayers(int currentPlayer) {
//        int[] playerOrder = new int[4];
//        int prevPlayer;
//        int nextPlayer;
//        int futurePlayer;
//        if (numPlayers == 2) {
//            if (currentPlayer == 1) {
//                prevPlayer = 2;
//                nextPlayer = 1;
//                futurePlayer = 2;
//            }
//            else if (currentPlayer == 2) {
//                prevPlayer = 1;
//                nextPlayer = 1;
//                futurePlayer = 2;
//            }
//        }
//        else if (numPlayers == 3) {
//            if (currentPlayer == 1) {
//                prevPlayer = 3;
//                nextPlayer = 2;
//                futurePlayer = 3;
//            }
//            else if (currentPlayer == 2) {
//                prevPlayer = 1;
//                nextPlayer = 3;
//                futurePlayer = 1;
//            }
//            else if (currentPlayer == 3) {
//                prevPlayer = 2;
//                nextPlayer = 1;
//                futurePlayer = 2;
//            }
//        }
//        else if (numPlayers >= 4) {
//            if (currentPlayer == 1) {
//                prevPlayer = numPlayers;
//                nextPlayer = 2;
//                futurePlayer = 3;
//            }
//            else if (currentPlayer + 1 == numPlayers) {
//                prevPlayer = currentPlayer - 1;
//                nextPlayer = currentPlayer + 1;
//                futurePlayer = 1;
//            }
//            else if (currentPlayer == numPlayers) {
//                prevPlayer = currentPlayer - 1;
//                nextPlayer = 1;
//                futurePlayer = 2;
//            }
//            else {
//                prevPlayer = currentPlayer - 1;
//                nextPlayer = currentPlayer + 1;
//                futurePlayer = currentPlayer + 2;
//            }
//        }
//
//
//        return playerOrder;
//    }



    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();


    }
}
