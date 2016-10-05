public class Field {
    public static final int SHIPS_AMOUNT = 3;
    final int SIZE = 10;
    char[] cells = new char[SIZE];
    Ship ship;
    Ship[] ships = new Ship[SHIPS_AMOUNT];
    Ship[] cellsWithShips = new Ship[SIZE];

    void init() {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = '.';
        }
    }

    void setShips() {
        for (int i = 0; i < SHIPS_AMOUNT; i++) {
            ships[i] = new Ship();
            boolean isIntersect;
            do {
                isIntersect = false;
                ships[i].init();
                for (int j = 0; j < i; j++) {
                    if (ships[i].isIntersectTwoShip(ships[j])) {
                        isIntersect = true;
                    }
                }
            } while (isIntersect);
            ships[i].deckInit(ships[i]);
            drawShip(ships[i]);
        }
    }

    void drawShip(Ship ship) {
        for (int i = 0; i < ship.decksAmount; i++) {
            cells[i + ship.getPosition()] = 'X';
            cellsWithShips[i + ship.getPosition()] = ship;
        }
    }

    void doShoot(int shoot) {
        if (cells[shoot] == '*' || cells[shoot] == 'D') {
            System.out.println("Уже стреляли");
        } else if (cellsWithShips[shoot] == null) {
            System.out.println("Промах");
            cells[shoot] = '*';
        } else if (cellsWithShips[shoot].isShoot(cellsWithShips[shoot], shoot)) {
            cells[shoot] = 'D';
            if (cellsWithShips[shoot].isDad(cellsWithShips[shoot], shoot)) {
                System.out.println("Попали! Корабль убит!");
            } else {
                System.out.println("Попали! Корабль ранен");
            }
        } else {
            System.out.println("ERROR");
        }
    }

    void showField() {
        System.out.println(cells);
    }

    boolean isGameEnd() {
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == 'X') {
                return false;
            }
        }
        return true;
    }
}
