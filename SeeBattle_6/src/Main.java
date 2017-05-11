import Controller.Controller;
import Model.Game;
import View.GameWindow;


public class Main {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.init();
        Controller.setGameWindow(gameWindow);
        Game game = new Game();
        game.run(gameWindow);
    }
}
