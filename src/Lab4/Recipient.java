package Lab4;

import java.util.Map;

public class Recipient extends Resident {
    public Recipient(String name, Map<Integer, Map<Integer, Double>> energyPerMonth) {
        super(name, energyPerMonth);
    }

    @Override
    public double getEnergy(Map<Integer, Map<Integer, Double>> energyPerDay, Integer month, Boolean forAll) {
        return super.getEnergy(energyPerDay, month, forAll);
    }

    @Override
    public double getAmountOfMoney(Map<Integer, Map<Integer, Double>> energyPerDay, Integer months, Boolean forAll) {
        return super.getAmountOfMoney(energyPerDay, months, forAll);
    }

    @Override
    public int getMonthMinMax(Map<Integer, Map<Integer, Double>> energyPerDay, boolean isMax) {
        return super.getMonthMinMax(energyPerDay, isMax);
    }

    @Override
    public int getDayMinMax(Map<Integer, Map<Integer, Double>> energyPerDay, boolean isMax) {
        return super.getDayMinMax(energyPerDay, isMax);
    }

    @Override
    public double getAverageEnergy(Map<Integer, Map<Integer, Double>> energyPerDay, int month) {
        return super.getAverageEnergy(energyPerDay, month);
    }

    @Override
    public double getDayMinMaxValue(Map<Integer, Map<Integer, Double>> energyPerDay, boolean isMax) {
        return super.getDayMinMaxValue(energyPerDay, isMax);
    }

    public String getNameDayMinMax(Resident[] residents, boolean isMax) {
        return super.getNameDayMinMax(residents, isMax);
    }

    @Override
    public String getNameMonthMinMax(Resident[] residents, boolean isMax) {
        return super.getNameMonthMinMax(residents, isMax);
    }
}
