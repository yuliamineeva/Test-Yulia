import java.util.ArrayList;

public class Ship {
    int position;
    int decksAmount;
    boolean isDadShip;
    ArrayList<Deck> decks = new ArrayList<>();

    void init() {
        decksAmount = (int) (Math.random() * 3) + 1;
        position = (int) (Math.random() * (10 - decksAmount)) + 1;
    }

    void deckInit(Ship ship) {
        for (int i = 0; i < ship.getDecksAmount(); i++) {
            Deck deck = new Deck();
            deck.position = i + position;
            deck.isWounded = false;
            deck.type = 'X';
            decks.add(deck);
        }
    }

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public int getDecksAmount() {
        return decksAmount;
    }

    int getPosition() {
        return position;
    }

    public boolean isIntersectTwoShip(Ship ship) {
        if (ship.getPosition() + ship.decksAmount < position) {
            return false;
        }
        if (position + decksAmount < ship.position) {
            return false;
        }
        return true;
    }

    public boolean isShoot(Ship ship, int shoot) {
        ship.getDecks();
        for (int i = 0; i < decksAmount; i++) {
            if (decks.get(i).position == shoot) {
                decks.get(i).setWounded(true);
                return true;
            }
        }
        return false;
    }

    public boolean isDad(Ship ship, int shoot) {
        isDadShip = true;
        ship.getDecks();
        for (int i = 0; i < decksAmount; i++) {
            Deck deck = decks.get(i);
            if (deck.isWounded == false) {
                isDadShip = false;
            }
        }
        return isDadShip;
    }
}
