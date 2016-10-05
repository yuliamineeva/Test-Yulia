import java.util.Scanner;

public class Player {

    int getShoot() {
        System.out.println("Куда стрелять?");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int shoot = Integer.parseInt(s) - 1;
        return shoot;
    }

}
