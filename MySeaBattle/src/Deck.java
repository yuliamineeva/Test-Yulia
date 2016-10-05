public class Deck {
    char type;
    boolean isWounded;
    int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isWounded() {
        return isWounded;
    }

    public void setWounded(boolean wounded) {
        isWounded = wounded;
    }

    public char getType() {
        if (isWounded) {
            type = 'D';
        } else {
            type = 'X';
        }
        return type;
    }



}
