package ThirdLab;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Resident {

    String name;
    Map<Integer, Double> energyPerDay;

    public double getEnergyPerDay(Map<Integer, Double> energyPerDay, Integer months, Integer forAll) {
      double result;
      if (months == null) {
        result = 0;
      } else {
        result = energyPerDay.get(months);
      }
      if (forAll == null) {
        result = 0;
      } else {
        result = energyPerDay.get(forAll);
      }
      return result;
    }

    public int getMonthMinMax(Map<Integer, Double> energyPerDay, boolean isMax) {
        int result;
        if (isMax) {
           result = Collections.max(energyPerDay.entrySet(), Map.Entry.comparingByValue()).getKey();
        } else {
           result = Collections.min(energyPerDay.entrySet(), Map.Entry.comparingByValue()).getKey();
        }
        return result;
    }

    public int getDayMinMax(Map<Integer, Double> energyPerDay, boolean isMax) {
        int result;
        if (isMax) {
            result = Collections.max(energyPerDay.entrySet(), Map.Entry.comparingByValue()).getKey();
        } else {
            result = Collections.min(energyPerDay.entrySet(), Map.Entry.comparingByValue()).getKey();
        }
        return result;
    }

    public int getAverageEnergy(Map<Integer, Double> energyPerDay, boolean isMax) {
        int result;
        if (isMax) {
            result = Collections.max(energyPerDay.entrySet(), Map.Entry.comparingByValue()).getKey();
        } else {
            result = Collections.min(energyPerDay.entrySet(), Map.Entry.comparingByValue()).getKey();
        }
        return result;
    }


}
