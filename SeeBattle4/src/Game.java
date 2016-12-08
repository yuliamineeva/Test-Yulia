
public class Game {
    private boolean isPlayer; //Это игрок или компьютер
    Field field = new Field();
  //  Player player = new Player();

    public void run() {
        System.out.println("Начали игру");
 //       field.player.getName();
        field.init();
        field.show();
        isPlayer = true;
//        int choice = field.player.chooseAlignment();
//        switch (choice) {
//            case 1:
//                field.setShipAuto(isPlayer);
//                break;
//            case 2:
//                field.setShipManual();
//                break;
//            default:
//                field.setShipAuto(isPlayer);
//                break;
//        }
        field.setShipAuto(isPlayer); // комеентировать, когда открываю основной код
        field.setShipAuto(!isPlayer);
        do {
            field.show();
            field.doShoot();
        } while (!field.isGameEnd());

        field.show();
        System.out.println("Все корабли унижтожены. Игра закончена!");
    }
}
