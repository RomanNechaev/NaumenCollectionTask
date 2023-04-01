package task5;

/**
 * @author Nechaev Roman
 */
public class CalculationTotalAmountOfLoan implements Task {

    private final Integer numberOfMounts;

    private final Double percentageRate;

    private final Long loanAmount;

    CalculationTotalAmountOfLoan(Integer numberOfMounts, Double percentageRate, Long loanAmount) {
        this.numberOfMounts = numberOfMounts;
        this.percentageRate = percentageRate/100;
        this.loanAmount = loanAmount;
    }

    @Override
    public Double call() {
        return loanAmount+percentageRate*loanAmount*(numberOfMounts/12);
    }
}