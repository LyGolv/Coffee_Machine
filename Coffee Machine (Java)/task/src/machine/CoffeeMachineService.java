package machine;

import java.util.Scanner;

public class CoffeeMachineService {

    private int water;
    private int milk;
    private int beans;
    private int money;
    private int cups;
    private boolean running = true;

    private final Scanner scanner = new Scanner(System.in);

    public CoffeeMachineService(int water, int milk, int beans, int money, int cups) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.money = money;
        this.cups = cups;
    }

    public void run() {
        while (running) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            switch (scanner.nextLine()) {
                case "buy" -> buy();
                case "fill" -> fill();
                case "take" -> take();
                case "remaining" -> System.out.println(this);
                case "exit" -> running = false;
            }
        }
    }

    public boolean canMakeCoffee(int type) {
        int maxCupOfCoffee = Math.min(
                Math.min(
                        safeDivision(water, Coffee.values()[type - 1].water),
                        safeDivision(milk, Coffee.values()[type - 1].milk)
                ),
                        safeDivision(beans, Coffee.values()[type - 1].beans)
        );
        return maxCupOfCoffee > 0;
    }

    private int safeDivision(int a, int b) {
        return b == 0 ? a : a / b;
    }

    public void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String input = scanner.nextLine();
        if (input.equals("back")) return;
        int type = Integer.parseInt(input);
        if (canMakeCoffee(type)) {
            System.out.println("I have enough resources, making you a coffee!");
            Coffee coffee = Coffee.values()[type - 1];
            money += coffee.price;
            water -= coffee.water;
            milk -= coffee.milk;
            beans -= coffee.beans;
            cups -= 1;
        } else System.out.println("Not enough resources!");
    }

    public void fill() {
        System.out.println("Write how many ml of water the coffee machine has:");
        this.water += scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        this.milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has");
        this.beans += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        this.cups += scanner.nextInt();
    }

    public void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    @Override
    public String toString() {
        return "The coffee machine has:\n"
                + water + " ml of water\n"
                + milk + " ml of milk\n"
                + beans + " g of coffee beans\n"
                + cups + " disposable cups\n"
                + "$" + money + " of money\n";
    }
}
