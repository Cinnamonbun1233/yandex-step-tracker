import java.util.Scanner;

public class StepTracker {
    int[][] arrayOfSteps = new int[12][30];
    String[] arrayOfMonths = new String[]{"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август",
            "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    Converter converter = new Converter();
    int defaultGoalSteps = 10_000;

    void getMainMenu() {
        System.out.println("Что ты хочешь сделать?");
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("4 - Выйти из приложения");
    }

    void getArrayOfMonth() {
        for (int i = 0; i < arrayOfMonths.length; i++) {
            System.out.println(i + " - " + arrayOfMonths[i]);
        }
    }

    void setStepsPerDay(Scanner scanner) {
        getArrayOfMonth();
        System.out.println("Введи порядковый номер месяца (от 0 до 11):");
        int month = scanner.nextInt();
        System.out.println("Выбран: " + arrayOfMonths[month]);
        System.out.println("Введи порядковый номер дня (от 1 до 30):");
        int day = scanner.nextInt() - 1;
        while (true) {
            System.out.println("Текущее количество шагов за этот день: " + arrayOfSteps[month][day]);
            System.out.println("Введи новое количество шагов за день:");
            int steps = scanner.nextInt();
            if (steps < 0) {
                System.out.println("Количество шагов не может быть отрицательным числом :(");
            } else {
                arrayOfSteps[month][day] = steps;
                System.out.println("Новое количество шагов за этот день: " + steps);
                return;
            }
        }
    }

    void getStatisticsForMonth(Scanner scanner) {
        getArrayOfMonth();
        System.out.println("Введи порядковый номер месяца (от 0 до 11):");
        int month = scanner.nextInt();
        System.out.println("Выбран: " + arrayOfMonths[month]);
        getStatisticsStepsByDays(month);
        System.out.println("Общее количество шагов за месяц: " + getSumStepsPerMonth(month));
        getNumberStepsPerMonth(month);
        getAverageStepsPerMonth(month);
        getDistancePerMonth(month);
        getCaloriesBurnedPerMonth(month);
        getBestSeriesStepsPerMonth(month);
    }

    void getStatisticsStepsByDays(int month) {
        for (int i = 0; i < 29; i++) {
            System.out.print((i + 1) + " день: " + arrayOfSteps[month][i] + ", ");
        }
        System.out.println("30 день: " + arrayOfSteps[month][29]);
    }

    int getSumStepsPerMonth(int month) {
        int sumSteps = 0;
        for (int i = 0; i < 30; i++) {
            sumSteps = sumSteps + arrayOfSteps[month][i];
        }
        return sumSteps;
    }

    void getNumberStepsPerMonth(int month) {
        int maxSteps = 0;
        for (int i = 0; i < 30; i++) {
            if (arrayOfSteps[month][i] > maxSteps) {
                maxSteps = arrayOfSteps[month][i];
            }
        }
        System.out.println("Максимальное пройденное количество шагов за месяц: " + maxSteps);
    }

    void getAverageStepsPerMonth(int month) {
        System.out.println("Среднее количество шагов за месяц: " + (getSumStepsPerMonth(month) / 30));
    }

    void getDistancePerMonth(int month) {
        int distance = (int) converter.convertStepsToKilometers(getSumStepsPerMonth(month));
        System.out.println("Пройденная дистанция (в км) за месяц: " + distance);
    }

    void getCaloriesBurnedPerMonth(int month) {
        int calories = (int) converter.convertStepsToCalories(getSumStepsPerMonth(month));
        System.out.println("Количество сожжённых килокалорий за месяц: " + calories);
    }

    void getBestSeriesStepsPerMonth(int month) {
        int series = 0;
        int bestSeries = 0;
        for (int i = 0; i < 30; i++) {
            if (arrayOfSteps[month][i] >= defaultGoalSteps) {
                series++;
                if (series > bestSeries) {
                    bestSeries = series;
                }
            } else {
                series = 0;
            }
        }
        System.out.println("Лучшая серия шагов за месяц: " + bestSeries);
    }

    void setDefaultGoalSteps(Scanner scanner) {
        while (true) {
            System.out.println("Текущая цель по шагам в день: " + defaultGoalSteps);
            System.out.println("Введите новую цель по шагам в день:");
            int scannerSteps = scanner.nextInt();
            if (scannerSteps < 0) {
                System.out.println("К сожалению, цель по шагам не может быть отрицательным числом :(");
            } else {
                defaultGoalSteps = scannerSteps;
                System.out.println("Новая цель по шагам в день: " + defaultGoalSteps);
                return;
            }
        }
    }
}