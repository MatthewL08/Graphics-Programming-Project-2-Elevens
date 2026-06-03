import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {

    private Deck d;
    private Card[][] cards;
    private ArrayList<Point> points;
    private ArrayList<Integer> button;
    private Rectangle replace = new Rectangle(300, 10,150 , 65);
    private Rectangle reset = new Rectangle(300, 210,150 , 65);
    private ArrayList<Integer> xValue = new ArrayList<>();
    private ArrayList<Integer> yValue = new ArrayList<>();


    public DrawPanel() {
        cards = new Card[3][3];
        d = new Deck();
        for (int r = 0; r < cards.length; r++) {
            for (int c = 0; c < cards.length; c++) {
                cards[r][c] = d.getRandomCard();
            }
        }
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        for (int r = 0; r < cards.length; r++) {
            for (int c = 0; c < cards.length; c++) {
                g.drawImage(cards[r][c].getImage(), x, y, null);
                Rectangle cardHitBox = new Rectangle(x, y, cards[r][c].getImage().getWidth(), cards[r][c].getImage().getHeight());
                cards[r][c].setHitbox(cardHitBox);
                if (cards[r][c].getHighlight()) {
                    g.drawRect(x, y, (int)cardHitBox.getWidth(), (int)cardHitBox.getHeight());
                }
                x += 80;
            }
            y += 100;
            x = 50;
        }

        g.drawString("Number of cards left: " + d.getDeck().size(), x, y + 100);
        g.drawRect(300, 10, 150,65);
        g.drawRect(300, 210, 150,65);
        if (d.hasLost(cards) == true){
            g.drawString("Game Over", 150,  250);
        }

    }

    public void mousePressed(MouseEvent e) {

        Point pointA = e.getPoint();
        int button = e.getButton();

        if (replace.contains(pointA)) {
            ArrayList<String> selected = new ArrayList<>();
            for (int l = 0; l < xValue.size(); l++) {
                Point p = new Point(xValue.get(l), yValue.get(l));
                for (int r = 0; r < cards.length; r++) {
                    for (int c = 0; c < cards.length; c++) {
                        if (cards[r][c].getHitbox().contains(p)) {
                            selected.add(cards[r][c].getValue());
                        }
                    }
                }
            }
           if (d.isPlayable(selected)) {
               for (int l = 0; l < xValue.size(); l++) {
                   Point p = new Point(xValue.get(l), yValue.get(l));
                   for (int r = 0; r < cards.length; r++) {
                       for (int c = 0; c < cards.length; c++) {
                           if (d.getDeck().size() != 0 && (button == 1 || button == 3)) {
                               if (cards[r][c].getHitbox().contains(p)) {
                                   cards[r][c] = d.getRandomCard();
                               }
                           }

                           if (button == 3 && cards[r][c].getHitbox().contains(p)) {
                               cards[r][c].flipHighlight();

                           }
                       }
                   }
               }
           }
            xValue.clear();
            yValue.clear();

        }
        else if (reset.contains(pointA)){
            cards = new Card[3][3];
            d = new Deck();
            for (int r = 0; r < cards.length; r++) {
                for (int c = 0; c < cards.length; c++) {
                    cards[r][c] = d.getRandomCard();
                }
            }
        }
        else{
            xValue.add((int) pointA.getX());
            yValue.add((int) pointA.getY());
        }
    }


    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}