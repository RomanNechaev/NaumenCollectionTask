package task5;

/**
 * @author Nechaev Roman
 */
public class CalculationMortgageLoan implements Task {
    private static final Integer TAX_DEDUCTION = 13;

    private final Integer numberOfMounts;

    private final Double percentageRate;

    private final Long loanAmount;

    CalculationMortgageLoan(Integer numberOfMounts, Double percentageRate, Long loanAmount) {
        this.numberOfMounts = numberOfMounts;
        this.percentageRate = percentageRate/100;
        this.loanAmount = loanAmount;
    }

    @Override
    public Double call() {
        var totalSum = (loanAmount / numberOfMounts + loanAmount * percentageRate / 12) * numberOfMounts;
        return totalSum - (totalSum-loanAmount)*(TAX_DEDUCTION/100) - totalSum*(TAX_DEDUCTION/100);
    }
}
