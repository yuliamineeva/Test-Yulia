public class Cell {
    private int x;
    private int y;
    private char symbol;
    Ship ship;

    public Cell(int x, int y, char symbol, Ship ship) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.ship = ship;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
