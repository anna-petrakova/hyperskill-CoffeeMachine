package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static void main(String[] args) {

        Machine coffeeMachine = new Machine();
        Scanner s = new Scanner(System.in);

        //System.out.println("Write action (buy, fill, take, remaining, exit)");

        String action;
        while (true) {
            coffeeMachine.printStateLine();
            action = s.nextLine();
            if ("exit".equals(action)) {
                break;
            }
            coffeeMachine.process(action);
        }
    }





}
