import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSCalculation {
    final static Scanner input = new Scanner(System.in);
    final static double averageEnergyConsume = 6.54;
    final static int[] stationPrice = new int[]{1995000, 3730000, 7300000};
    final static int[] stationUseCoast = new int[]{8, 17, 25};

    public static void main(String[] args) {
        int[] countOfEvCars = evCarsCount();

        System.out.println("Enter anumber EV cars in city: ");
        int evCarsInCity = input.nextInt();

        double carGrow = carGrowth(countOfEvCars);
        System.out.println(carGrow);

        double energyLevelForDay = 51.23 * (evCarsInCity * Math.pow((1 + carGrow / 100), 9)) / 6.54;
        System.out.println(energyLevelForDay);

        double summaryEnergyConsume = summaryEnergyConsume(carGrow, evCarsInCity);
        System.out.println(summaryEnergyConsume);

        calculatingNumberOfChargingStation(summaryEnergyConsume);

    }

    public static int[] evCarsCount() {
        System.out.println("Enter a count of EV from 2016 to 2021 years: ");
        int[] countOfEVCars = new int[6];
        for (int i = 0; i < 6; i++) {
            countOfEVCars[i] = input.nextInt();
        }
        return countOfEVCars;
    }

    public static double carGrowth(int[] evCarsCount) {
        double carGrow = 0;
        for (int i = 1; i < 6; i++) {
            carGrow = carGrow + ((double) evCarsCount[i - 1] / evCarsCount[i]);
        }
        return ((carGrow / 5) - 1) * 100;
    }

    public static double summaryEnergyConsume(double carGrow, int evCarsInCity) {
        int summEVCars = Math.round(evCarsInCity * (int) Math.pow((1 + carGrow / 100), 9));
        ArrayList<double[]> carsList = new ArrayList<>();
        carsList.add(0, new double[]{74.55, 24.0});
        carsList.add(1, new double[]{6.20, 85.0});
        carsList.add(2, new double[]{5.07, 79.2});
        carsList.add(3, new double[]{4.30, 95.0});
        carsList.add(4, new double[]{3.08, 75.0});
        carsList.add(5, new double[]{2.82, 16.0});
        carsList.add(6, new double[]{3.98, 59.0});

        double summEnergy = 0;
        for (int i = 0; i < carsList.size(); i++) {
            summEnergy = summEnergy + summEVCars * carsList.get(i)[0] / 100 * carsList.get(i)[1];
        }
        return summEnergy;
    }


    public static void calculatingNumberOfChargingStation(double summaryEnergyConsume) {
        ArrayList<List<Integer>> numberCS = new ArrayList<>();
        int kWt_150 = (int) Math.ceil(summaryEnergyConsume / 24 / 150);
        int kWt_50 = (int) Math.ceil(summaryEnergyConsume / 24 / 50);
        int kWt_22 = (int) Math.ceil(summaryEnergyConsume / 24 / 22);

        for (int i = 0; i < kWt_22; i++) {
            for (int j = 0; j < kWt_50; j++) {
                for (int k = 0; k < kWt_150; k++) {
                    double v = summaryEnergyConsume / (i * 22 + j * 50 + k * 150);
                    if ((v < 24) && (v > 23.99)) {
                        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(i, j, k));
                        int totalPrice = list.get(0) * stationPrice[0] + list.get(1) * stationPrice[1] + list.get(2) * stationPrice[2];
                        int totalUseCount = list.get(0) * stationUseCoast[0] * 24 * 22 + list.get(1) * stationUseCoast[1] * 24 * 50 + list.get(2) * stationUseCoast[2] * 24 * 150;
                        list.add(totalPrice);
                        list.add(totalUseCount);
                        int cs = list.get(0) + list.get(1) + list.get(2);
                        if (cs < 100 & cs > 80) {
                            numberCS.add(list);
                        }
                        break;
                    }
                }
            }
        }
        table(numberCS);
    }

    public static void table(ArrayList<List<Integer>> temp) {
        Object[] collumHeaders = {"22 kWt", "50 kWt", "150 kWt", "BuildingPrice", "UsersCoast"};

        JFrame frame = new JFrame("Charging Station option");

        frame.getContentPane().setLayout(new FlowLayout());

        frame.setSize(1020, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Object[][] tempArray = temp.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);

        JTable jTableOption = new JTable(tempArray, collumHeaders);

        JScrollPane scroll = new JScrollPane(jTableOption);

        jTableOption.setPreferredScrollableViewportSize(new Dimension(800, 800));

        frame.getContentPane().add(scroll);

        frame.setVisible(true);
    }

}
