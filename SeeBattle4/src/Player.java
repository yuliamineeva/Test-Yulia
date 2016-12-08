import java.util.Scanner;

public class Player {
    private String name;
    boolean isValid = false;
    Scanner scanner = new Scanner(System.in);

    public String getName() {
        System.out.println("Введите имя игрока");
        name = scanner.nextLine();
        return name;
    }

    public int chooseAlignment() {
        System.out.println("Выберете способ установки корабля: \n1. Автоматическая расстановка\n2. Ручная расстановка");
        int number = 0;
        try {
            number = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Необходимо ввести одну цифру");
        }
        return number;
    }

    public Point getShoot() {
        Point point;
        String str;
        int tempY = 0;
        do {
            isValid = false;
            System.out.println("Ваш ход, " + name + ". Введите координаты выстрела: сначала буква, затем цифра(без пробела). Например, а1, B10)");
            point = new Point();
            str = scanner.nextLine();
            str = str.toLowerCase();
            char charX = str.charAt(0);
            switch (charX){
                case 'a':
                    point.setX(0);
                    break;
                case 'b':
                    point.setX(1);
                    break;
                case 'c':
                    point.setX(2);
                    break;
                case 'd':
                    point.setX(3);
                    break;
                case 'e':
                    point.setX(4);
                    break;
                case 'f':
                    point.setX(5);
                    break;
                case 'g':
                    point.setX(6);
                    break;
                case 'h':
                    point.setX(7);
                    break;
                case 'i':
                    point.setX(8);
                    break;
                case 'j':
                    point.setX(9);
                    break;
                default:
                    System.out.println("Координаты не верные, попробуйте еще раз!");
                    System.out.println("Варианты букв: A, B, C, D, E, F, G, H, I, J (заглавные или строчные)");
                    isValid = true;
            }
        String stringY = str.substring(1);
            try {
                tempY = Integer.parseInt(stringY)- 1;
                if (tempY > 9) {
                    System.out.println("Координаты не верные, попробуйте еще раз!");
                    System.out.println("Координата по вертикали должна быть от 1 до 10.");
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Координаты не верные, попробуйте еще раз!");
                System.out.println("Координата по вертикали должна быть числом от 1 до 10 (без пробелов).");
                isValid = true;
            }
        } while (isValid);
        point.setY(tempY);
        return point;
    }

}
