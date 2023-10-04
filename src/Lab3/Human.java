package Lab3;

public class Human {
    private String name;

    private double height;

    private double weight;

    private double age;

    private int mode;

    private boolean isMan;

    double[] list = {1.2, 1.38, 1.46, 1.55, 1.64, 1.73, 1.9};

    public Human(String name, double height, double weight, double age, int mode, boolean isMan) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.mode = mode;
        this.isMan = isMan;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        if (mode >= 0 && mode <= 6)
            this.mode = mode;
    }

    double setIdValue (int id) {
        return switch (id) {
            case 0 -> list[0];
            case 1 -> list[1];
            case 2 -> list[2];
            case 3 -> list[3];
            case 4 -> list[4];
            case 5 -> list[5];
            case 6 -> list[6];
            default -> 0;
        };
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public void countDCI(Meal[] meals, Human human, int mode) {
        double dci;
        double mealsCals = 0;
        if (human.isMan())
            dci = (human.getWeight() * 10 + human.getHeight() * 6.25 - human.getAge() * 5 - 161) * setIdValue(mode);
        else
            dci = (human.getWeight() * 10 + human.getHeight() * 6.25 - human.getAge() * 5 + 5) * setIdValue(mode);

        System.out.println("Ваш дневная норма: " + dci + " cal");

        for (Meal meal : meals) {
            mealsCals += meal.countCalories(meal.getProteins(), meal.getCarbs(), meal.getFats());
        }

        System.out.println("Сколько калорий вы съели: " + mealsCals + " cal");

        if(mealsCals > dci) {
            System.out.println("Вы съели очень много сегодня");
        } else if (mealsCals < dci) {
            System.out.println("Вы недоели сегодня");
        } else {
            System.out.println("Идеально");
        }

    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
}
