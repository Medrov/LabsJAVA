package SecondLab;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
}
