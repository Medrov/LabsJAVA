package Lab3;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Meal {
    private final String name;
    private final double proteins;

    private final double carbs;

    private final double fats;

    public Meal(String name, double proteins, double carbs, double fats) {
        this.name = name;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }

    public String getName() {
        return name;
    }

    public double getProteins() {
        return proteins;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFats() {
        return fats;
    }

    double countCalories(double proteins, double carbs, double fats) {
        return 9.29 * fats + 4.1 * proteins + 4.1 * carbs;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }

    public static Meal createFromScanner(final Scanner scan) {
        System.out.print("Name: ");
        String name = scan.next();
        String proteins = "", carbs = "", fats = "";

        try {
          System.out.print("Proteins: ");
          proteins = scan.next();
        } catch (NumberFormatException e) {
          System.out.println("Неправильный формат значения");
        }

        try {
          System.out.print("Carbs: ");
          carbs = scan.next();
        } catch (NumberFormatException e) {
          System.out.println("Неправильный формат значения");
        }

        try {
          System.out.print("Fats: ");
          fats = scan.next();
        } catch (NumberFormatException e) {
            System.out.println("Неправильный формат значения");
        }
        Meal meal = null;
        try {
            meal = new Meal(name, Double.parseDouble(proteins), Double.parseDouble(carbs), Double.parseDouble(fats));
        } catch (NumberFormatException e) {
            System.out.println("Неправильный формат значения");
        }
        return meal;
    }
}
