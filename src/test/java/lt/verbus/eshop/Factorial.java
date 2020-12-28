package lt.verbus.eshop;

public class Factorial {
    public static int factorial(int param) {
        if (param < 0) {
            throw new IllegalArgumentException();
        }

        if (param == 0) {
            return 1;
        }

        int res = 1;
        for (int i = param; i > 0; i--) {
            res *= i;
        }
        return res;
    }
}
