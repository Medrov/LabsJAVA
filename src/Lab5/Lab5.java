package Lab5;

import Lab3.Human;
import Lab3.Meal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Lab5 {
    static Scanner scan = new Scanner(System.in);
    String[] mealParameters = {"Name", "Proteins", "Carbs", "Fats"};
    //Lunch
    static Meal olivie = new Meal("Olivie", 5.4, 7, 16.7);
    static Meal solyanka = new Meal("Solyanka", 5.2, 1.7, 4.6);
    static Meal puree = new Meal("Puree", 2.1, 8.5, 4.6);
    static Meal escalop = new Meal("Escalop", 19, 6.2, 42);
    //Dinner
    static Meal uha = new Meal("Uha", 3.4, 5.5, 1);
    static Meal vinegret = new Meal("Vinegret", 1.7, 8.2, 10.3);
    static Meal borzh = new Meal("Borzh", 4.4, 5.5, 3.6);

    static Human[] humans = {
            new Human("Маша", 165, 48, 18, 2, false),
            new Human("Петя", 185, 88, 19, 4, true),
            new Human("Катя", 165, 98, 2, 1, true),
            new Human("Саша", 155, 38, 18, 3, false),
    };
    static List<Meal> meals = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        menu();
    }
    static void menu(){
        //printLogo();
        System.out.println("1. Выбор пользователя");
        System.out.println("2. Расписать еду");
        System.out.println("3. Убрать индекс");
        System.out.println("4. Запись пользователей");
        System.out.print("Ващ выбор: ");
        int choice = 0;
        try {
          choice = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Даю еще шанс");
        }


        switch (choice) {
            case 1:
                chooseUser();
                menu();
                break;
            case 2:
                setMeal();
                menu();
                break;
            case 3:
                removeIndex();
                menu();
                break;
            case 4:
                finalCount();
                menu();
                break;
            default:
                System.out.println("Даю еще шанс");
                menu();
        }
    }

    public static void chooseUser() {
        System.out.println("Список ребят:");
        for (int i = 0; i < humans.length; i++)
            System.out.println((i + 1) + ". " + humans[i].getName());

        System.out.print("Выбирай персонажа: ");
        String input = scan.next();

        while (!input.equals("no")) {
            try {
                System.out.println("Ты выбрал: " + humans[Integer.parseInt(input) - 1].getName());
                humans[Integer.parseInt(input) - 1].countDCI(new Meal[]{olivie, solyanka, puree, escalop, uha, vinegret, borzh}, humans[Integer.parseInt(input) - 1], 3);
                System.out.print("Выбирай следующего: ");
                input = scan.next();
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат индекса");
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Исключение выхода индекса массива за пределы допустимых значений");
                break;
            }
        }
        System.out.println();
    }

    static void setMeal(){
        System.out.println("Распишите еду:");
        do {
            meals.add(Meal.createFromScanner(scan));
            System.out.print("Родной, работаем дальше: ");
        }
        while (!(scan.next().equals("no")));

        meals.addAll(List.of(new Meal[]{olivie, solyanka, puree, escalop, uha, vinegret, borzh}));
        System.out.println(meals);
    }

    static void removeIndex() {
        try {
            System.out.print("Убрать индекс: ");
            int removeIndex = scan.nextInt();
            meals.remove(removeIndex);
        } catch (NumberFormatException e) {
            System.out.println("Неправильный формат индекса");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Исключение выхода индекса массива за пределы допустимых значений");
        }
        System.out.println(meals);
    }

    static void finalCount() {
        System.out.println("Список ребят:");
        for (int i = 0; i < humans.length; i++)
            System.out.println((i + 1) + ". " + humans[i].getName());

        System.out.print("Выбирай персонажа: ");
        int index = scan.nextInt();
        try {
            humans[index - 1].countDCI(meals.toArray(new Meal[0]), humans[index - 1], 3);
        } catch (NullPointerException e) {
            System.out.println("Ошибка нулевого элемента");
        }

    }


    public static void printLogo() {
        System.out.println(
                "\n" +
                        "##   ## ##### ###   ##### ####   ###  #       # \n" +
                        "# # # # #     #  #  #     #   # #   #  #     #  \n" +
                        "#  #  # ##### #   # ##### ####  #   #   #   #   \n" +
                        "#     # #     #  #  #     #  #  #   #    # #    \n" +
                        "#     # ##### ###   ##### #   #  ###      #     \n" +
                        "                                    __          \n" +
                        "                                    /_  _  /_   \n" +
                        "                                   //_'/_ / / . \n" +
                        "\n");
    }


}
