package Lab4;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Resident {
    String name;
    Map<Integer, Map<Integer, Double>> energyPerDay;

    public Resident(String name, Map<Integer, Map<Integer, Double>> energyPerDay) {
        this.name = name;
        this.energyPerDay = energyPerDay;
    }

    public double getEnergy(Map<Integer, Map<Integer, Double>> energyPerDay, Integer month, Boolean forAll) {
        double result = 0;
        if(forAll) {
            for (int i = 1; i <= energyPerDay.size(); i++) {
                for (int j = 1; j <= energyPerDay.get(i).size(); j++) {
                    result += energyPerDay.get(i).get(j);
                }
            }
        } else {
            for (int j = 1; j <= energyPerDay.get(month).size(); j++) {
                result += energyPerDay.get(month).get(j);
            }
        }
        return result;
    }

    public double getAmountOfMoney(Map<Integer, Map<Integer, Double>> energyPerDay, Integer months, Boolean forAll) {
        double recipientCoefficient = 0.25;
        return getEnergy(energyPerDay, months, forAll) * 2.5 * recipientCoefficient ;
    }

    public int getMonthMinMax(Map<Integer, Map<Integer, Double>> energyPerDay, boolean isMax) {
        int result;
        Map<Integer, Double> results = new HashMap<>();
        if (isMax) {
            for(int i = 1; i <= 12; i++) {
                results.put(i, getEnergy( energyPerDay, i, false));
            }
            result = Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
        } else {
            for(int i = 1; i <= 12; i++) {
                results.put(i, getEnergy( energyPerDay, i, false));
            }
            result = Collections.min(results.entrySet(), Map.Entry.comparingByValue()).getKey();
        }
        return result;
    }

    public int getDayMinMax(Map<Integer, Map<Integer, Double>> energyPerDay, boolean isMax) {
        Double result;
        List<Double> list = new ArrayList<>();
        for (int i = 1; i <= energyPerDay.size(); i++) {
            for (int j = 1; j <= energyPerDay.get(i).size(); j++) {
                list.add(energyPerDay.get(i).get(j));
            }
        }
        if (isMax) {
            result = Collections.max(list);
        } else {
            result = Collections.min(list);
        }
        return list.indexOf(result) + 1;
    }

    public double getDayMinMaxValue(Map<Integer, Map<Integer, Double>> energyPerDay, boolean isMax) {
        Double result;
        List<Double> list = new ArrayList<>();
        for (int i = 1; i <= energyPerDay.size(); i++) {
            for (int j = 1; j <= energyPerDay.get(i).size(); j++) {
                list.add(energyPerDay.get(i).get(j));
            }
        }
        if (isMax) {
            result = Collections.max(list);
        } else {
            result = Collections.min(list);
        }
        return result;
    }

    public double getAverageEnergy(Map<Integer, Map<Integer, Double>> energyPerDay, int month) {
        double result = 0;
        for (int j = 1; j <= energyPerDay.get(month).size(); j++) {
            result += energyPerDay.get(month).get(j);
        }
        result = result/energyPerDay.get(month).size();
        return result;
    }

    public String getNameMonthMinMax(Resident[] residents, boolean isMax) {
        String result;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));

        List<Integer> list = new ArrayList<>();
        List<Double> finalList = new ArrayList<>();
        if (isMax) {
            for (Resident resident : residents) {
                list.add(getMonthMinMax(resident.energyPerDay, true));
            }

            for (int i = 0; i < residents.length; i++) {
                finalList.add(getEnergy(residents[i].energyPerDay, list.get(i), false));
            }
            System.out.println("Max month: ");
            result = residents[finalList.indexOf(Collections.max(finalList))].name+": " + df.format(Collections.max(finalList));
        } else {
            for (Resident resident : residents) {
                list.add(getMonthMinMax(resident.energyPerDay, false));
            }

            for (int i = 0; i < residents.length; i++) {
                finalList.add(getEnergy(residents[i].energyPerDay, list.get(i), false));
            }
            System.out.println("Min month: ");
            result = residents[finalList.indexOf(Collections.min(finalList))].name+": " + df.format(Collections.min(finalList));

        }

        return result;
    }

    public String getNameDayMinMax(Resident[] residents, boolean isMax) {
        String result;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));

        List<Integer> list = new ArrayList<>();
        List<Double> finalList = new ArrayList<>();
        if (isMax) {
            for (Resident resident : residents) {
                list.add(getDayMinMax(resident.energyPerDay, true));
            }

            for (Resident resident : residents) {
                finalList.add(getDayMinMaxValue(resident.energyPerDay, true));
            }
            System.out.println("Max Day: ");
            result = residents[finalList.indexOf(Collections.max(finalList))].name+": " + df.format(Collections.max(finalList));
        } else {
            for (Resident resident : residents) {
                list.add(getDayMinMax(resident.energyPerDay, false));
            }

            for (Resident resident : residents) {
                finalList.add(getDayMinMaxValue(resident.energyPerDay, false));
            }
            System.out.println("Min Day: ");
            result = residents[finalList.indexOf(Collections.min(finalList))].name+": " + df.format(Collections.min(finalList));
        }

        return result;
    }

}
