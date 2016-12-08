import java.util.ArrayList;
import java.util.List;

public class Field {
    public static final int SIZE = 10;
    public final char BLANK_SYMB = '.';   //символ пустого поля
    public final char MISS_SYMB = '*';    //символ промаха
    public final char SHIP_SYMB = 'S';    //символ целой палубы корабля
    public final char HIT_SYMB = 'D';    //символ попадания в корабль
    private final int SHIP4_QUANTITY = 1; // Количество 4-хпалубных кораблей
    private final int SHIP3_QUANTITY = 2; // Количество 3-хпалубных кораблей
    private final int SHIP2_QUANTITY = 3; // Количество 2-хпалубных кораблей
    private final int SHIP1_QUANTITY = 4; // Количество 1-палубных кораблей
    private boolean isPlayerMove = true;
    Cell[][] cellsPlayer = new Cell[SIZE][SIZE];
    Cell[][] cellsComp = new Cell[SIZE][SIZE];
    Ship ship;
    Player player = new Player();
    Cell cell;
    List<Ship> shipsPlayer = new ArrayList<Ship>(); // Список установленных на поле кораблей игрока
    List<Ship> shipsComp = new ArrayList<Ship>();   // Список установленных на поле кораблей компьютера

    public void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Cell cell = new Cell(j, i, BLANK_SYMB, null);
                cellsPlayer[j][i] = cell;
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Cell cell = new Cell(j, i, BLANK_SYMB, null);
                cellsComp[j][i] = cell;
            }
        }
        for (int i = 0; i < SHIP4_QUANTITY; i++) {
            Ship shipPlayer = new Ship(4);
            shipsPlayer.add(shipPlayer);
            Ship shipComp = new Ship(4);
            shipsComp.add(shipComp);
        }
        for (int i = 0; i < SHIP3_QUANTITY; i++) {
            Ship shipPlayer = new Ship(3);
            shipsPlayer.add(shipPlayer);
            Ship shipComp = new Ship(3);
            shipsComp.add(shipComp);
        }
        for (int i = 0; i < SHIP2_QUANTITY; i++) {
            Ship shipPlayer = new Ship(2);
            shipsPlayer.add(shipPlayer);
            Ship shipComp = new Ship(2);
            shipsComp.add(shipComp);
        }
        for (int i = 0; i < SHIP1_QUANTITY; i++) {
            Ship shipPlayer = new Ship(1);
            shipsPlayer.add(shipPlayer);
            Ship shipComp = new Ship(1);
            shipsComp.add(shipComp);
        }
    }

    public void show() {
        String header = "\n" + "    |           Ваше поле           |         Поле компьютера      |\n" +
                "    |_______________________________|______________________________|\n" +
                "    |       A B C D E F G H I J     |       A B C D E F G H I J    |";
        System.out.println(header);  //  Верхняя подпись поля
        String LeftColumn; //коллонка с цифрами
        for (int i = 0; i < SIZE; i++) {
            if (i < 9) {
                LeftColumn = "    |     " + (i + 1) + " ";
            } else {
                LeftColumn = "    |    " + (i + 1) + " ";
            }
            System.out.print(LeftColumn);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(cellsPlayer[j][i].getSymbol() + " ");
            }
            System.out.print(LeftColumn);
            for (int j = 0; j < SIZE; j++) {
                if (j < SIZE - 1) {
                    System.out.print(cellsComp[j][i].getSymbol() + " ");
                } else {
                    System.out.print(cellsComp[j][i].getSymbol() + "    |");
                }
            }
            System.out.println();
        }
    }

    //   boolean isPlayer - это игрок или компьютер
    public void setShipAuto(boolean isPlayer) {
        String ownerShips = isPlayer ? " игрока" : " компьютера";
        System.out.println("Идет автоматическая расстановка кораблей" + ownerShips);
        for (int i = 0; i < shipsPlayer.size(); i++) {
            boolean isIntersect;
            do {
                isIntersect = false;
                ship = isPlayer ? shipsPlayer.get(i) : shipsComp.get(i);
                ship.init(ship.getLength());
                for (int j = 0; j < i; j++) {
                    Ship anotherShip = isPlayer ? shipsPlayer.get(j) : shipsComp.get(j);
                    if (ship.isIntersectTwoShip(anotherShip)) {
                        isIntersect = true;
                    }
                }
            } while (isIntersect);
            if (isPlayer) {
                for (int x = ship.leftUp.getX(); x <= ship.rightDown.getX(); x++) {
                    for (int y = ship.leftUp.getY(); y <= ship.rightDown.getY(); y++) {
                        Cell cell = new Cell(x, y, SHIP_SYMB, ship);
                        cellsPlayer[x][y] = cell;
                    }
                }
            } else {
                for (int x = ship.leftUp.getX(); x <= ship.rightDown.getX(); x++) {
                    for (int y = ship.leftUp.getY(); y <= ship.rightDown.getY(); y++) {
                        Cell cell = new Cell(x, y, SHIP_SYMB, ship);
                        cellsComp[x][y] = cell;
                    }
                }
            }
        }
    }

    public void setShipManual() {
        System.out.println("Ручная расстановка кораблей игрока"); // todo Ручная расстановка кораблей
    }


    public void doShoot() {
        Point point;
        if (isPlayerMove) {           // если это ход игрока
            point = player.getShoot();
            cell = cellsComp[point.getX()][point.getY()];
        } else {                    // если это ход компьютера
            do {
                point = Point.getRandomPoint();
                //           point = player.getShoot();        // для тестирования
                cell = cellsPlayer[point.getX()][point.getY()];
            } while (cell.getSymbol() == '*' || cell.getSymbol() == 'D');
        }
        if (cell.getSymbol() == '*' || cell.getSymbol() == 'D') {
            System.out.println("Уже стреляли");
            isPlayerMove = !isPlayerMove;
        } else if (cell.getSymbol() == '.') {
            System.out.println("Промах");
            cell.setSymbol('*');
            isPlayerMove = !isPlayerMove;
        } else if (cell.ship != null) {
            cell.setSymbol('D');
            int healthy = cell.ship.getHealthyDeck() - 1;
            cell.ship.setHealthyDeck(healthy);
            if (healthy == 0) {
                System.out.println("Попали! Корабль убит!" + healthy);
            } else {
                System.out.println("Попали! Корабль ранен" + healthy);
            }
        } else {
            System.out.println("Ошибка!");
        }
        System.out.println((point.getX() + 1) + " " + (point.getY() + 1));      // для тестирования
    }

    public boolean isGameEnd() {
        //todo сделать проверку на конец игры
        return false;
    }

}
