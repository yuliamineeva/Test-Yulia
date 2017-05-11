package Model;

import Controller.Controller;
import View.GameWindow;

public class Game {
    private boolean isPlayer; //Это игрок или компьютер
    Field field = new Field();

    public void run(GameWindow gameWindow) {
        System.out.println(field.message + ", " + field.player.getName());
        field.init();
        field.show();
        isPlayer = true;
        int choice = field.player.chooseAlignment();
        switch (choice) {
            case 1:
                field.setShipAuto(isPlayer);
                gameWindow.updateLabel(field.message);
                break;
            case 2:
                field.setShipManual();
                gameWindow.updateLabel(field.message);
                break;
            default:
                field.setShipAuto(isPlayer);
                gameWindow.updateLabel(field.message);
                break;
        }
        field.setShipAuto(!isPlayer);
        gameWindow.updateLabel(field.message);
        do {
            field.show();
            gameWindow.update(field.symbolsComp, field.symbolsPlayer);
            field.doShoot();
            gameWindow.updateLabel(field.message);
        } while (!field.isGameEnd());

        field.show();
        gameWindow.update(field.symbolsComp, field.symbolsPlayer);
        gameWindow.updateLabel(field.message);
        System.out.println(field.message);

    }
}
