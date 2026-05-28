import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        String[] values = {"02", "03", "04", "05", "06", "07", "08", "09", "10", "A", "J", "K", "Q"};

        for (String s : suits) {
            for (String v : values) {
                deck.add(new Card(s, v));
            }
        }
    }

    public Card getRandomCard() {
        int random = (int)(Math.random() * deck.size());
        return deck.remove(random);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public boolean hasLost (Card[][] hand){
        ArrayList<Integer> intOfHand = new ArrayList<>();
        for (int i = 0; i < hand.length; i ++){
            for (int j = 0; j < hand[0].length; j ++){
                switch (hand[i][j].getValue()) {
                    case "A" -> intOfHand.add(1);
                    case "2" -> intOfHand.add(2);
                    case "3" -> intOfHand.add(3);
                    case "4" -> intOfHand.add(4);
                    case "5" -> intOfHand.add(5);
                    case "6" -> intOfHand.add(6);
                    case "7" -> intOfHand.add(7);
                    case "8" -> intOfHand.add(8);
                    case "9" -> intOfHand.add(9);
                    case "10" -> intOfHand.add(10);
                    case "J" -> intOfHand.add(0);
                    case "Q" -> intOfHand.add(0);
                    case "K" -> intOfHand.add(0);
                }
            }
        }

        int counter = 0;
        for (int i = 0; i < intOfHand.size() - 1; i ++) {
            for (int j = 1; j < intOfHand.size(); j++) {
                if (intOfHand.get(i) + intOfHand.get(j) == 11) {
                    return false;
                }
                else {
                    if (intOfHand.get(i) == 0){
                        counter++;
                    }
                }
            }
        }

        if (counter >= 3){
            return false;
        }

        return true;
    }
}