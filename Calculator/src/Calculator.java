import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String code;

        do{
            System.out.print("Введите код операции: ");
            code = scanner.nextLine();
            if (code.equals("+") || code.equals("*") || code.equals("^") || code.equalsIgnoreCase("root")
                    || code.equals("-") || code.equals("/") || code.equals("%") || code.equalsIgnoreCase("rand")) {
                System.out.print("Введите число A: ");
                double a = new Double(scanner.nextLine().replace(',', '.'));
                System.out.print("Введите число B: ");
                double b = new Double(scanner.nextLine().replace(',', '.'));
                switch(code.toLowerCase()){
                    case "+":
                        System.out.println("Ответ: " + a + " + " + b + " = " + (a + b));
                        break;
                    case "-":
                        System.out.println("Ответ: " + a + " - " + b + " = " + (a - b));
                        break;
                    case "*":
                        System.out.println("Ответ: " + a + " * " + b + " = " + (a * b));
                        break;
                    case "/":
                    case "%":
                        if (b == 0)
                            System.out.println("Ошибка: на ноль делить нельзя.");
                        else
                            System.out.println("Ответ: " + a + " " + code + " " + b + " = "
                                    + (code.equals("/") ? (a / b) : (a % b)));
                        break;
                    case "^":
                        System.out.println("Ответ: " + a + " ^ " + b + " = " + Math.pow(a, b));
                        break;
                    case "root":
                        if (a < 0 && b % 2 == 0)
                            System.out.println("Ошибка: нельзя вычислять корень четной степени для отрицательных чисел.");
                        else
                            System.out.println("Ответ: Корень из " + a + "степени " + b + " = " + Math.pow(a, 1.0 / b));
                        break;
                    case "rand":
                        System.out.println("Ответ: случайное число от " + a + " до " + b + " : "
                                + (a + (b - a) * Math.random()));

                }
            } else {
                switch(code.toLowerCase()){
                    case "help":
                        System.out.println("Список доступных операций:");
                        System.out.println("+    Сложение A и B.");
                        System.out.println("-    Вычитание B из A.");
                        System.out.println("*    Умножение A на B.");
                        System.out.println("/    Деление A на B.");
                        System.out.println("%    Деление по модулю A на B.");
                        System.out.println("^    Возведение A в степень B.");
                        System.out.println("root Вычисление из А корня степени B.");
                        System.out.println("rand Вывод случайного числа от A до B.");
                        System.out.println("help Вывод списка операций.");
                        System.out.println("exit Выход из программы.");
                        break;
                    case "exit":
                        System.out.println("Программа закрыта.");
                        break;
                    default:
                        System.out.println("Неверный код операции. Введите \"help\" для помощи.");
                }
            }
        }while(!code.equalsIgnoreCase("exit"));
        scanner.close();
    }
}