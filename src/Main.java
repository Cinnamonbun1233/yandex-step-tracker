import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();

        System.out.println("Привет, друг!");

        while (true) {

            stepTracker.getMainMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                stepTracker.setStepsPerDay(scanner);
            } else if (command == 2) {
                stepTracker.getStatisticsForMonth(scanner);
            } else if (command == 3) {
                stepTracker.setDefaultGoalSteps(scanner);
            } else if (command == 4) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Прости, такой команды не существует");
            }
        }
    }
}