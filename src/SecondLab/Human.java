package SecondLab;

public class Human {
    private String name;

    private double height;

    private double weight;

    private double age;

    private int id;

    private boolean isMan;

    double[] list = {1.2, 1.38, 1.46, 1.55, 1.64, 1.73, 1.9};

    public Human(String name, double height, double weight, double age, int id, boolean isMan) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >= 0 && id <= 6)
            this.id = id;

        switch (id) {
            case 0:

                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;

        }
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    void countDCI(double proteins, double carbs, double fats, Human human) {
        double dci;
        if (human.isMan())
            dci = (human.getWeight() * 10 + human.getHeight() * 6.25 - human.getWeight() * 5 - 161);
        else
            dci = (human.getWeight() * 10 + human.getHeight() * 6.25 - human.getWeight() * 5 + 5);

    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
}
