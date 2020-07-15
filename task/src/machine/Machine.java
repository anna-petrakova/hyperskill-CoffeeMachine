package machine;

import java.util.ArrayList;
import java.util.List;

public class Machine {
    private int money = 550;
    private int cups = 9;
    private int water = 400;
    private int milk = 540;
    private int beans = 120;

    private CoffeeMachineState state = CoffeeMachineState.CHOOSING_ACTION;

    public void printStateLine() {
        state.printLine();
    }

    public void process(String input) {
        switch (state) {
            case CHOOSING_ACTION:
                chooseAction(input);
                break;
            case CHOOSING_COFFEE:
                buyCoffee(input);
                break;
            case FILL_WATER:
            case FILL_MILK:
            case FILL_BEANS:
            case FILL_CUPS:
                fillMachine(input);
                break;
        }
    }

    private void chooseAction(String action) {
        switch (action) {
            case "buy":
                state = CoffeeMachineState.CHOOSING_COFFEE;
                break;
            case "fill":
                state = CoffeeMachineState.FILL_WATER;
                break;
            case "take":
                take();
                break;
            case "remaining":
                printState();
                break;
            default:
                System.out.println("We do not support this action.");
        }
    }

    private void buyCoffee(String coffee) {
        switch (coffee) {
            case "1":
                buyOneCoffee(250, 0, 16, 4);
                break;
            case "2":
                buyOneCoffee(350, 75, 20, 7);
                break;
            case "3":
                buyOneCoffee(200, 100, 12, 6);
                break;
            case "back":
                break;
            default:
                System.out.println("We do not sell this coffee");
        }
        state = CoffeeMachineState.CHOOSING_ACTION;
    }

    private void buyOneCoffee(int water, int milk, int beans, int money) {
        boolean hasEnoughIngredients = verifyIngredients(water, milk, beans);
        if (hasEnoughIngredients) {
            System.out.println("I have enough resources, making you a coffee!");
            this.water -= water;
            this.milk -= milk;
            this.beans -= beans;
            this.money += money;
            this.cups -= 1;
        }
    }

    private boolean verifyIngredients(int water, int milk, int beans) {
        List<String> missingIngredients = new ArrayList<>();

        int difference;
        if (this.water < water) {
            difference = water - this.water;
            missingIngredients.add("water (missing " + difference + " ml)");
        }
        if (this.milk < milk ) {
            difference = milk - this.milk;
            missingIngredients.add("milk (missing " + difference + " ml)");
        }
        if (this.beans < beans ) {
            difference = beans - this.beans;
            missingIngredients.add("coffee beans (missing " + difference + " g)");
        }
        if (this.cups < 1) {
            missingIngredients.add("cups");
        }

        if (missingIngredients.size() != 0) {
            StringBuilder missingIngredientsLine = new StringBuilder("Sorry, not enough of " + missingIngredients.get(0));
            for (int i = 1; i < missingIngredients.size(); i++) {
                missingIngredientsLine.append(" and " + missingIngredients.get(i));
            }
            System.out.println(missingIngredientsLine.toString());
            return false;
        }
        return true;
    }

    private void fillMachine(String input) {
        int numberToAdd;
        try {
            numberToAdd = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            numberToAdd = 0;
        }
        switch (state) {
            case FILL_WATER:
                this.water += numberToAdd;
                state = CoffeeMachineState.FILL_MILK;
                break;
            case FILL_MILK:
                this.milk += numberToAdd;
                state = CoffeeMachineState.FILL_BEANS;
                break;
            case FILL_BEANS:
                this.beans += numberToAdd;
                state = CoffeeMachineState.FILL_CUPS;
                break;
            case FILL_CUPS:
                this.cups += numberToAdd;
                state = CoffeeMachineState.CHOOSING_ACTION;
                break;
            default:
                state = CoffeeMachineState.CHOOSING_ACTION;
                break;
        }
    }

    private void take() {
        System.out.println("I gave you " + money);
        money = 0;
    }

    private void printState() {
        System.out.println("The coffee machine has: ");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of cups");
        System.out.println(money + " of money");
    }
}
