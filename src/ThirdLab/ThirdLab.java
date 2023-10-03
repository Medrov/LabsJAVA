package ThirdLab;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class ThirdLab {
    public static void main(String[] args) {
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));

        boolean isLeapYear = false;

        Resident[] residents = {
          new Resident("Vladimir", new HashMap<>()),
          new Resident("Islam", new HashMap<>()),
          new Resident("Leonid", new HashMap<>()),
          new Resident("Vasiliy", new HashMap<>()),
          new Resident("Nikita", new HashMap<>()),
          new Recipient("Oleg", new HashMap<>()),
          new Recipient("Klein", new HashMap<>()),
          new Recipient("Watanabe", new HashMap<>()),
        };

        for (Resident resident : residents) {
            for (int j = 1; j <= 12; j++) {
                Map<Integer, Double> innerMap = new HashMap<Integer, Double>();
                for (int k = 1; k <= 31; k++) {
                    if((j == 2 && (isLeapYear ? k <= 29 : k <= 28))) {
                        double randomValue = 130 + (250 - 130) * random.nextDouble();
                        innerMap.put(k, randomValue);
                    }  else if (k <= 30 && j != 2){
                        double randomValue = 130 + (250 - 130) * random.nextDouble();
                        innerMap.put(k, randomValue);
                    } else if((j == 1 || j == 3 || j == 5 || j == 7 || j == 8 || j == 10 || j == 12)) {
                        double randomValue = 130 + (250 - 130) * random.nextDouble();
                        innerMap.put(k, randomValue);
                    }
                }
                resident.energyPerDay.put(j, innerMap);
            }
        }

        //print Residents
        for (Resident resident : residents) {
            System.out.println("Резидент: " + resident.name);

            for (int j = 1; j <= 12; j++) {
                System.out.println(j+". "+"Месяц");
                for (int k = 1; k <= 31; k++) {
                    if (resident.energyPerDay.get(j).get(k) != null)
                        System.out.println(k+". "+"Затраты по дням: " + df.format(resident.energyPerDay.get(j).get(k)));
                }
                System.out.println();
            }
//            System.out.println("Energy: " + resident.getEnergy(resident.energyPerMonth, 3, false));
//            System.out.println("Money's energy: " + resident.getAmountOfMoney(resident.energyPerMonth, 3, false));

            System.out.println("Min: " + resident.getMonthMinMax(resident.energyPerDay, true));
            System.out.println("Min: " + resident.getNameMonthMinMax(residents, false));

            System.out.println();
        }



    }
}
