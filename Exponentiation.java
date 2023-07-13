
public class Exponentiation {
    public static double calculateExponent(double base, double exponent) {
        if (exponent == 0) {
            return 1.0;
        }
        double result = base;
        int exp = (int) Math.abs(exponent);
        for (int i = 1; i < exp; i++) {
            result *= base;
        }
        if (exponent < 0) {
            return 1.0 / result;
        } else {
            return result;
        }
    }
}
