import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

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
        if (deck.size() > 0) {
            int random = (int) (Math.random() * deck.size());
            return deck.remove(random);
        }
        return new Card();
        }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public boolean isPlayable (ArrayList<String> selected){
        ArrayList<Integer> intOfHand = new ArrayList<>();
        for (String value : selected) {
                switch (value) {
                    case "A" -> intOfHand.add(1);
                    case "02" -> intOfHand.add(2);
                    case "03" -> intOfHand.add(3);
                    case "04" -> intOfHand.add(4);
                    case "05" -> intOfHand.add(5);
                    case "06" -> intOfHand.add(6);
                    case "07" -> intOfHand.add(7);
                    case "08" -> intOfHand.add(8);
                    case "09" -> intOfHand.add(9);
                    case "10" -> intOfHand.add(10);
                    case "J", "Q", "K" -> intOfHand.add(0);
                }
            }
        int sum = 0;
        int counter = 0;
        for (int i = 0; i < intOfHand.size(); i++) {
        sum = sum + intOfHand.get(i);
            if (intOfHand.get(i) == 0){
                counter++;
            }
        }
        if (sum == 11 || counter >= 3){
            return true;
        }
        return false;
    }
    public boolean hasLost(Card[][] hand) {
        ArrayList<Integer> intOfHand = new ArrayList<>();
        for (Card[] cards : hand) {
            for (int j = 0; j < hand[0].length; j++) {
                switch (cards[j].getValue()) {
                    case "A" -> intOfHand.add(1);
                    case "02" -> intOfHand.add(2);
                    case "03" -> intOfHand.add(3);
                    case "04" -> intOfHand.add(4);
                    case "05" -> intOfHand.add(5);
                    case "06" -> intOfHand.add(6);
                    case "07" -> intOfHand.add(7);
                    case "08" -> intOfHand.add(8);
                    case "09" -> intOfHand.add(9);
                    case "10" -> intOfHand.add(10);
                    case "J", "Q", "K" -> intOfHand.add(0);
                }
            }
        }

        int counter = 0;


        for (int a = 0; a < intOfHand.size(); a++) {
            for (int i = 0; i < intOfHand.size(); i++) {
                int sum = 0;
                sum = intOfHand.get(i) + intOfHand.get(a);
                if (sum == 11) {
                    return false;
                }
            }
            if (intOfHand.get(a) == 0){
                counter++;
            }
        }



        if (counter >= 3){
            return false;
        }


        return true;
    }
}