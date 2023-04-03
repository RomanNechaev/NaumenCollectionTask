package task5;

import java.util.Scanner;

/**
 * @author Nechaev Roman
 */
public class App {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var managementSystem = new ManagementSystem();
        System.out.println("Система управления расчетами, версия: 0.9");
        printInitialCommand();
        var input = scanner.nextInt();
        while (input != 3) {
            switch (input) {
                case 1 -> {
                    createTask(managementSystem);
                    printInitialCommand();
                }
                case 2 -> {
                    printResult(managementSystem);
                    printInitialCommand();
                }
                default -> {
                    System.out.println("Упс, такой команды нет");
                    printInitialCommand();
                }
            }
            input = scanner.nextInt();
        }
    }

    public static void createTask(ManagementSystem managementSystem) {
        System.out.println("Выберете расчет, который нужно сделать");
        System.out.println("1. Посчитать полную стоимость кредита");
        System.out.println("2. Посчитать полную стоимость ипотеки");
        System.out.println("3. Посчитать кол-во зарплат до покупки мечты");
        System.out.println("4. Посчитать доходность вклада");
        var input = scanner.nextInt();
        switch (input) {
            case 1 -> {
                System.out.println("Введите сумму кредита, процентную ставку, кол-во месяцев");
                System.out.println("сумма кредита");
                Long loanAmount = scanner.nextLong();
                System.out.println("Процентная ставка");
                Double percentageRate = scanner.nextDouble();
                System.out.println("кол-во месяцев");
                Integer numberOfMounts = scanner.nextInt();
                managementSystem.add(new CalculationTotalAmountOfLoan(numberOfMounts, percentageRate, loanAmount));
            }
            case 2 -> {
                System.out.println("Введите сумму ипотеки, процентную ставку, кол-во месяцев");
                System.out.println("сумма ипотеки");
                Long loanAmount = scanner.nextLong();
                System.out.println("Процентная ставка");
                Double percentageRate = scanner.nextDouble();
                System.out.println("кол-во месяцев");
                Integer numberOfMounts = scanner.nextInt();
                managementSystem.add(new CalculationMortgageLoan(numberOfMounts, percentageRate, loanAmount));
            }
            case 3 -> {
                System.out.println("Введите зарплату, сумму мечты");
                System.out.println("зарплата");
                Long salary = scanner.nextLong();
                System.out.println("Сумма мечты");
                Long costOfDream = scanner.nextLong();
                managementSystem.add(new CalculationCountOfSalaryForDream(salary, costOfDream));
            }
            case 4 -> {
                System.out.println("Введите сумму вклада, кол-во месяц, процентную ставку");
                System.out.println("сумма вклада");
                Long startDeposit = scanner.nextLong();
                System.out.println("кол-во месяцев");
                Integer mounts = scanner.nextInt();
                System.out.println("процентную ставку");
                Double percentageRate = scanner.nextDouble();
                managementSystem.add(new CalculationAmountOfDeposit(mounts, percentageRate, startDeposit));
            }
        }
    }

    public static void printResult(ManagementSystem managementSystem) {
        try {
            var task = managementSystem.get();
            System.out.println("Результат задачи №" + task.getKey() + " :" + task.getValue().get());
        } catch (Exception e) {
            System.out.println("Невозомжно получить результат, т.к очередь задач пуста!");
        }
    }

    public static void printInitialCommand() {
        System.out.println("Что хотите сделать?");
        System.out.println("1. Создать задачу");
        System.out.println("2. Получить результат расчета");
        System.out.println("3. Выйти");
    }

}
