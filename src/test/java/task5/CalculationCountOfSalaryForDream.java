package task5;

/**
 * @author Nechaev Roman
 */
public class CalculationCountOfSalaryForDream implements Task {

    private final Long salary;

    private final Long costOfDream;


    CalculationCountOfSalaryForDream(Long salary, Long costOfDream) {
        this.salary = salary;
        this.costOfDream = costOfDream;
    }

    @Override
    public Double call() {
        return (Math.ceil((double) costOfDream / salary));
    }
}