public class Card {

    // Attributes
    String color;
    int number;

    // Constructor
    Card(String color, int number) {
        this.color = color;
        this.number = number;
    }

    // print the info of a card
    public void printCard() {
        System.out.println(this.color + " " + this.number);
    }

    // return the info of a card
    public String cardInfo() {
        return ("" + this.color + " " + this.number);
    }

    public static void main(String[] args) {

    }
}
