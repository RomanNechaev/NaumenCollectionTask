package task5;
/**
 * @author Nechaev Roman
 */
public class CalculationAmountOfDeposit implements Task {

    private static final Integer DEPOSIT_TAX = 35;

    private static final Double MAX_RATE = 12.5;

    private final Integer numberOfMounts;

    private final Double percentageRate;

    private final Long startDeposit;

    CalculationAmountOfDeposit(Integer numberOfMounts, Double percentageRate, Long startDeposit) {
        this.numberOfMounts = numberOfMounts;
        this.percentageRate = percentageRate/100;
        this.startDeposit = startDeposit;
    }

    @Override
    public Double call() {
        double totalSum = startDeposit + percentageRate * startDeposit * ((double) numberOfMounts / 12);
        if (percentageRate > MAX_RATE) {
            totalSum += totalSum * ((double) DEPOSIT_TAX / 100);
        }
        return totalSum;
    }

}