package utils;

public class ChaosTest {

    private static ChaosStrategy instance = ChaosStrategy.getInstance();

    public static void main(String[] args) {
        instance.CalChaos();
        for (int i = 0; i < 500; i++) {
            double result = instance.LM(1, instance.getChaosValue());
            System.out.println(result);
        }
    }
}
