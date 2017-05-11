package Controller;

import Model.Field;
import Model.Player;
import Model.Point;
import View.GameWindow;

/**
 * Created by pc on 26.01.2017.
 */
public class Controller {
    public static Field field = new Field();
    public static GameWindow gameWindow;

    public static void setGameWindow(GameWindow gameWindow) {
        Controller.gameWindow = gameWindow;
    }

    public static void setName(String name) {
        Player.name = name;
    }
//    public static void updateLabel(String message){
//        gameWindow.updateLabel(message);
//    }
    public static void updateMove(Boolean isPlayer){
        gameWindow.updateHeader(isPlayer);
    }

    public static void chooseAlignment(int number) {
        Player.isChoiceSelected = true;
        Player.choiceAlignment = number;
    }

    public static void doShoot(Point point, String buttonText) {
            Player.point = point;
            Player.isMakeShoot = true;
    }


}
