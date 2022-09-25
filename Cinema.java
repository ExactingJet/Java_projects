package cinema;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        int curentIncome = 0;
        int tickets = 0;
        float percentage = 0;
        final Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        int rows = input.nextInt() + 1;
        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int cols = input.nextInt() + 1;
        String[][] theater = cinema(rows, cols);


        menu();
        System.out.print("> ");
        int choise = input.nextInt();

        while (choise != 0) {
            switch (choise) {
                case 1 -> {
                    printCinema(theater, rows, cols);
                }
                case 2 -> {
                    curentIncome += byuSeat(input, theater, totalSeats(rows, cols), rows, cols);
                    tickets++;
                    percentage = (float) tickets / totalSeats(rows, cols) * 100;
                }
                case 3 -> {
                    statistics(curentIncome, rows, cols, tickets, percentage);
                }
            }
            menu();
            choise = input.nextInt();
        }

    }

    public static void menu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }


    public static void statistics(int income, int rows, int cols, int tickets, float percentage) {
        System.out.printf("Number of purchased tickets: %d\n", tickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.printf("Curent income:$%d\n", income);
        totalIncome(totalSeats(rows, cols), rows - 1, cols - 1);
    }

    public static String[][] cinema(int rows, int cols) {

        String[][] seats = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = "S";
                seats[0][0] = " ";
            }
        }

        for (int i = 1; i < rows; i++) {
            seats[i][0] = String.valueOf(i);
        }

        for (int i = 1; i < cols; i++) {
            seats[0][i] = String.valueOf(i);
        }
        return seats;
    }

    public static void printCinema(String[][] cinema, int rows, int cols) {
        System.out.println("Cinema:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void totalIncome(int totalSeats, int rowsNumber, int seatsNumber) {
        int totalIncome;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
            System.out.printf("Total income: $%d\n", totalIncome);
        } else {
            int halfRows = rowsNumber / 2;
            totalIncome = (halfRows * seatsNumber) * 10 + ((rowsNumber - halfRows) * seatsNumber * 8);
            System.out.printf("Total income: $%d\n", totalIncome);
        }
    }

    public static int byuSeat(Scanner input, String[][] cinema, int totalSeats, int rows, int cols) {
        int count = 1;
        int income = 0;
        System.out.println("\nEnter a row number:");
        System.out.print("> ");
        int rowNumber = input.nextInt();
        System.out.println("Enter a seat number in that row:");
        System.out.print("> ");
        int seatNumber = input.nextInt();
        while (count != 0) {
            if (rowNumber >= rows || seatNumber >= cols) {
                System.out.println("Wrong input!");
            } else if (cinema[rowNumber][seatNumber].equals("B")) {
                System.out.println("That ticket has already been purchased");
            } else {
                cinema[rowNumber][seatNumber] = "B";
                if (totalSeats <= 60) {
                    System.out.println("\nTicket price: $10");
                    income = 10;
                } else if (rowNumber <= (rows - 1) / 2) {
                    System.out.println("\nTicket price: $10");
                    income = 10;
                } else {
                    System.out.println("\nTicket price: $8");
                    income = 8;
                }
                count--;
                break;
            }
            System.out.println("\nEnter a row number:");
            System.out.print("> ");
            rowNumber = input.nextInt();
            System.out.println("Enter a seat number in that row:");
            System.out.print("> ");
            seatNumber = input.nextInt();
        }
        return income;
    }


    public static int totalSeats(int rows, int seats) {
        return (rows - 1) * (seats - 1);
    }


}
