package machine;

public enum CoffeeMachineState {
    CHOOSING_ACTION("\nWrite action (buy, fill, take, remaining, exit)"),
    CHOOSING_COFFEE("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - capuccino, back - to main menu"),
    FILL_WATER("\nWrite how many ml of water do you want to add:"),
    FILL_MILK("Write how many ml of milk do you want to add:"),
    FILL_BEANS("Write how many grams of coffee beans do you want to add:"),
    FILL_CUPS("Write how many disposable cups of coffee do you want to add:");

    String line;

    CoffeeMachineState(String line) {
        this.line = line;
    }

    public void printLine() {
        System.out.println(line);
    }
}
