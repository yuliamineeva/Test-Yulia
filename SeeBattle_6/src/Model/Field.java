package Model;

import Controller.Controller;
import sun.text.resources.cldr.ia.FormatData_ia;

import java.util.ArrayList;
import java.util.List;

public class Field {
    public static final int SIZE = 10;
    public volatile String message = "Начали игру";
    public final char BLANK_SYMB = '.';   //символ пустого поля
    public final char MISS_SYMB = '*';    //символ промаха
    public final char SHIP_SYMB = 'S';    //символ целой палубы корабля
    public final char HIT_SYMB = 'D';    //символ попадания в корабль

    public enum Symbol {BLANK, MISS, SHIP, HIT}

    private final int SHIP4_QUANTITY = 1; // Количество 4-хпалубных кораблей
    private final int SHIP3_QUANTITY = 2; // Количество 3-хпалубных кораблей
    private final int SHIP2_QUANTITY = 3; // Количество 2-хпалубных кораблей
    private final int SHIP1_QUANTITY = 4; // Количество 1-палубных кораблей
    private boolean isPlayerMove = true;
    Cell[][] cellsPlayer = new Cell[SIZE][SIZE];
    Cell[][] cellsComp = new Cell[SIZE][SIZE];
    Symbol[][] symbolsPlayer = new Symbol[SIZE][SIZE];
    Symbol[][] symbolsComp = new Symbol[SIZE][SIZE];
    String[][] strSymbolsPlayer = new String[SIZE][SIZE];
    String[][] strSymbolsComp = new String[SIZE][SIZE];
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
                symbolsPlayer[i][j] = Symbol.BLANK;
                strSymbolsPlayer[i][j] = " ";
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Cell cell = new Cell(j, i, BLANK_SYMB, null);
                cellsComp[j][i] = cell;
                symbolsComp[i][j] = Symbol.BLANK;
                strSymbolsComp[i][j] = " ";
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
                switch (cellsPlayer[j][i].getSymbol()) {
                    case BLANK_SYMB:
                        strSymbolsPlayer[i][j] = " ";
                        symbolsPlayer[i][j] = Symbol.BLANK;
                        break;
                    case MISS_SYMB:
                        strSymbolsPlayer[i][j] = String.valueOf(MISS_SYMB);
                        symbolsPlayer[i][j] = Symbol.MISS;
                        break;
                    case SHIP_SYMB:
                        strSymbolsPlayer[i][j] = String.valueOf(SHIP_SYMB);
                        symbolsPlayer[i][j] = Symbol.SHIP;
                        break;
                    case HIT_SYMB:
                        strSymbolsPlayer[i][j] = String.valueOf(HIT_SYMB);
                        symbolsPlayer[i][j] = Symbol.HIT;
                        break;
                }
            }
            System.out.print(LeftColumn);
            for (int j = 0; j < SIZE; j++) {
                switch (cellsComp[j][i].getSymbol()) {
                    case BLANK_SYMB:
                        strSymbolsComp[i][j] = " ";
                        symbolsComp[i][j] = Symbol.BLANK;
                        break;
                    case MISS_SYMB:
                        strSymbolsComp[i][j] = String.valueOf(MISS_SYMB);
                        symbolsComp[i][j] = Symbol.MISS;
                        break;
                    case SHIP_SYMB:
                        strSymbolsComp[i][j] = String.valueOf(SHIP_SYMB);
                        symbolsComp[i][j] = Symbol.SHIP;
                        break;
                    case HIT_SYMB:
                        strSymbolsComp[i][j] = String.valueOf(HIT_SYMB);
                        symbolsComp[i][j] = Symbol.HIT;
                        break;
                }

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
        message = "Идет автоматическая расстановка кораблей" + ownerShips;
        System.out.println(message);
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
                        symbolsPlayer[x][y] = Symbol.SHIP;
                    }
                }
            } else {
                for (int x = ship.leftUp.getX(); x <= ship.rightDown.getX(); x++) {
                    for (int y = ship.leftUp.getY(); y <= ship.rightDown.getY(); y++) {
                        Cell cell = new Cell(x, y, SHIP_SYMB, ship);
                        cellsComp[x][y] = cell;
                        symbolsComp[x][y] = Symbol.SHIP;
                    }
                }
            }
        }
    }

    public void setShipManual() {
        message = "Ручная расстановка кораблей игрока";
        System.out.println(message);
        // todo Ручная расстановка кораблей, вместо этого пока автоматическая
        setShipAuto(true);
    }


    public void doShoot() {
        Point point;
        String messageOwner;
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        if (isPlayerMove) {           // если это ход игрока
            Controller.updateMove(isPlayerMove);
            System.out.println("Ваш ход, " + player.getName());
            point = player.getShoot();
            cell = cellsComp[point.getX()][point.getY()];
            messageOwner = "Ход игрока. " + chars[point.getX()] + (point.getY() + 1) + " ";
        } else {                    // если это ход компьютера
            Controller.updateMove(isPlayerMove);
            do {        // todo сделать искуственный интеллект в отдельном классе Comp
                point = Point.getRandomPoint();
                cell = cellsPlayer[point.getX()][point.getY()];
            } while (cell.getSymbol() == MISS_SYMB || cell.getSymbol() == HIT_SYMB);
            messageOwner = "Ход компьютера: " + chars[point.getX()] + (point.getY() + 1) + " ";
        }
        if (cell.getSymbol() == MISS_SYMB || cell.getSymbol() == HIT_SYMB) {
            message = messageOwner + "Уже стреляли";
            System.out.println(message);
            isPlayerMove = !isPlayerMove;
        } else if (cell.getSymbol() == BLANK_SYMB) {
            message = messageOwner + "Промах";
            System.out.println(message);
            cell.setSymbol(MISS_SYMB);
            isPlayerMove = !isPlayerMove;
        } else if (cell.ship != null) {
            cell.setSymbol(HIT_SYMB);
            int healthy = cell.ship.getHealthyDeck() - 1;
            cell.ship.setHealthyDeck(healthy);
            if (healthy == 0) {
                message = messageOwner + "Попали! Корабль убит!";
                showEmptySpace(cell.ship, isPlayerMove ? cellsComp : cellsPlayer);
                if (isPlayerMove) {
                    shipsComp.remove(cell.getShip());
                    message = message + " Осталось уничтожить " + shipsComp.size() + " кораблей компьютера";
                    System.out.println(message);
                } else {
                    shipsPlayer.remove(cell.ship);
                    message = message + " У вас осталось " + shipsPlayer.size() + " кораблей";
                    System.out.println(message);
                }
            } else {
                message = messageOwner + "Попали! Корабль ранен";
                System.out.println(message);
            }
        } else {
            message = "Ошибка!";
            System.out.println(message);
        }
    }

    public void showEmptySpace(Ship ship, Cell[][] cells) {
        System.out.println("leftUp X: " + ship.leftUp.getX() + "Y: " + ship.leftUp.getY());
        System.out.println("rightDown X: " + ship.rightDown.getX() + "Y: " + ship.rightDown.getY());
        int beginX, beginY, endX, endY;
        beginX = (ship.leftUp.getX() == 0) ? 0 : (ship.leftUp.getX() - 1);
        beginY = (ship.leftUp.getY() == 0) ? 0 : (ship.leftUp.getY() - 1);
        endX = (ship.rightDown.getX() == SIZE - 1) ? (SIZE - 1) : (ship.rightDown.getX() + 1);
        endY = (ship.rightDown.getY() == SIZE - 1) ? (SIZE - 1) : (ship.rightDown.getY() + 1);
        for (int i = beginX; i <= endX; i++) {
            if (cells[i][beginY].getShip() != ship) {
                cells[i][beginY].setSymbol(MISS_SYMB);
            }
            if (cells[i][endY].getShip() != ship) {
                cells[i][endY].setSymbol(MISS_SYMB);
            }
        }
        for (int i = ship.leftUp.getY(); i <= ship.rightDown.getY(); i++) {
            if (cells[beginX][i].getShip() != ship) {
                cells[beginX][i].setSymbol(MISS_SYMB);
            }
            if (cells[endX][i].getShip() != ship){
                cells[endX][i].setSymbol(MISS_SYMB);
            }
        }
    }

    public boolean isGameEnd() {
        if (shipsComp.size() == 0) {
            message = "Все корабли унижтожены. " + player.getName() + ", поздравляем! Вы победили!";
            return true;
        }
        if (shipsPlayer.size() == 0) {
            message = "Все корабли унижтожены. Игра закончена! Победил компьютер.";
            return true;
        }
        return false;
    }
}
