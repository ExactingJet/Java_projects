package CoffeeMachine;

import javax.swing.*;
import java.util.*;

public class CoffeeMachine {
    private static int water = 400;
    private static int milk = 540;
    private static int beans = 120;
    private static int cups = 9;
    private static int money = 550;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
        String action = input.nextLine();
        while (!action.equals("exit")) {
            switch (action) {
                case "buy" -> buyCoffee();
                case "fill" -> fillIngredients();
                case "take" -> takeMoney();
                case "remaining" -> info();
            }
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
            action = input.next();
        }

    }

    public static void info() {
        System.out.printf("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money""", water, milk, beans, cups, money);
    }

    public static void fillIngredients() {
        System.out.println("Write how many ml of water you want to add:");
        water += input.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milk += input.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        beans += input.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        cups += input.nextInt();
    }

    public static void buyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String coffeeType = input.next();
        boolean canMake;
        switch (coffeeType) {
            case "1" -> {
                int[] needResources = new int[]{200, 0, 16, 1};
                canMake = checkResources(needResources);
                if (canMake) {
                    water -= 250;
                    beans -= 16;
                    money += 4;
                    cups -= 1;
                }
            }
            case "2" -> {
                int[] needResources = new int[]{350, 75, 20, 1};
                canMake = checkResources(needResources);
                if (canMake) {
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    money += 7;
                    cups -= 1;
                }
            }
            case "3" -> {
                int[] needResources = new int[]{200, 100, 12, 1};
                canMake = checkResources(needResources);
                if (canMake) {
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    money += 6;
                    cups -= 1;
                }
            }
            case "back" -> {}
        }
    }

    public static boolean checkResources(int[] resources) {
        System.out.println("Checking resources...");
        if (resources[0] > water) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (resources[1] > milk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (resources[2] > beans) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        } else if (resources[3] > cups) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        System.out.println("I have enough resources, making you a coffee!");
        return true;
    }

    public static void takeMoney() {
        System.out.printf("I gave you $%d", money);
        money = 0;
    }

}
