public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        Player player = new Player();

        field.init();
        field.setShips();
//        System.out.println();
        System.out.println("Начали игру");

        do {
            field.showField();
//            for (int i = 0; i < field.cellsWithShips.length; i++) {
//                System.out.println(i + " " + field.cellsWithShips[i]);
//            }
            field.doShoot(player.getShoot());
        } while (!field.isGameEnd());
        field.showField();
        System.out.println("Все корабли унижтожены. Игра закончена!");

    }
}
