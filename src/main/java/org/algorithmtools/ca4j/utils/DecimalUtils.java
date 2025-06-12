package org.algorithmtools.ca4j.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalUtils {

    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = BigDecimal.ZERO;
        return a.add(b);
    }

    public static BigDecimal add(double a, double b) {
        return BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)).setScale(4, RoundingMode.HALF_UP);
    }

    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = BigDecimal.ZERO;
        return a.subtract(b);
    }

    public static BigDecimal subtract(double a, double b) {
        return BigDecimal.valueOf(a).subtract(BigDecimal.valueOf(b)).setScale(4, RoundingMode.HALF_UP);
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            return BigDecimal.ZERO;
        }
        return a.multiply(b).setScale(4, RoundingMode.HALF_UP);
    }

    public static BigDecimal multiply(double a, double b) {
        if (a == 0 || b == 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(a).multiply(BigDecimal.valueOf(b)).setScale(4, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (a == null || b == null || b.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return a.divide(b, 4, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(double a, double b) {
        if (b == 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), 4, RoundingMode.HALF_UP);
    }

    /**
     * BigDecimal adjust precision
     * @param value double value
     * @return 4 precision double value
     */
    public static double adjustPrecision(double value, int precision) {
        if(precision < 0) return value;
        return BigDecimal.valueOf(value)
                .setScale(precision, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public static double exclude0Multiply(double a, double b){
        if (a == 0) {
            return b;
        } else if (b == 0){
            return a;
        } else {
            return a * b;
        }
    }

    public static double rateDivide(double value, double divide){
        if (divide == 0) {
            return 0;
        } else {
            return value / divide;
        }
    }
}

