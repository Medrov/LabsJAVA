package ThirdLab;

import java.util.Map;

public class Recipient extends Resident {
    @Override
    public double getEnergyPerDay(Map<Integer, Double> energyPerDay, Integer months, Integer forAll) {
        return super.getEnergyPerDay(energyPerDay, months, forAll);
    }

    public double getAmountOfSalary(Map<Integer, Double> energyPerDay, Integer months, Integer forAll) {
        double recipientCoefficient = 0.25;
        return getEnergyPerDay(energyPerDay, months, forAll) * 2.5;
    }
}
