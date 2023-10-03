package SecondLab;

public class SecondLab {
    public static void main(String[] args) {
        //Lunch
        Meal olivie = new Meal("Olivie", 5.4, 7, 16.7);
        Meal solyanka = new Meal("Solyanka", 5.2, 1.7, 4.6);
        Meal puree = new Meal("Puree", 2.1, 8.5, 4.6);
        Meal escalop = new Meal("Escalop", 19, 6.2, 42);
        //Dinner
        Meal uha = new Meal("Uha", 3.4, 5.5, 1);
        Meal vinegret = new Meal("Vinegret", 1.7, 8.2, 10.3);
        Meal borzh = new Meal("Borzh", 4.4, 5.5, 3.6);

        Human[] humans = new Human[2];

        for (int i = 0; i < humans.length; i++) {
            if(i == 0)
                humans[i] = new Human("Masha", 165, 48, 18, 2, false);
            else
                humans[i] = new Human("Misha", 185, 88, 18, 4, true);
        }
        for(Human human : humans) {
            System.out.println("Пользователь: " + human.getName());
            human.countDCI(new Meal[]{olivie, solyanka, puree, escalop, uha, vinegret, borzh}, human, 3);
            System.out.println();
        }
    }
}
