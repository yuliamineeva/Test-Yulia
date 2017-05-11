package Model;

public class Player {
    public static String name;
    public static Point point;
    public volatile static boolean isChoiceSelected;
    public volatile static boolean isMakeShoot;
    public static int choiceAlignment;

    public String getName() {
        return name;
    }

    public int chooseAlignment() {
        while(!isChoiceSelected);
        return choiceAlignment;
    }

    public Point getShoot() {
        isMakeShoot = false;
        while (!isMakeShoot);
        return point;
    }

}
