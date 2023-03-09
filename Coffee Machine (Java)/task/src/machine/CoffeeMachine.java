package machine;

public class CoffeeMachine {

    private static final CoffeeMachineService machineService = new CoffeeMachineService(400,540,120,550, 9);

    public static void main(String[] args) {
        machineService.run();
    }
}
