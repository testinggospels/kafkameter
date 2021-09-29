package co.signal.handlebars;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathsHelper implements Helper<Object> {

    @Override
    public Object apply(Object context, Options options) throws IOException {

        BigDecimal left = coerceToBigDecimal(context);
        String operator = options.params[0].toString();
        BigDecimal right = coerceToBigDecimal(options.params[1]);

        BigDecimal result = null;
        switch (operator) {
            case "+":
                result = left.add(right);
                break;
            case "-":
                result = left.subtract(right);
                break;
            case "*":
            case "x":
                result = left.multiply(right);
                break;
            case "/":
                result = left.divide(right, RoundingMode.HALF_UP);
                break;
            case "%":
                result = left.remainder(right);
                break;
            default:
                return null;
        }

        return reduceToPrimitiveNumber(result);
    }

    private static BigDecimal coerceToBigDecimal(Object value) {
        if (value instanceof Integer) {
            return new BigDecimal((int) value);
        }

        if (value instanceof Long) {
            return new BigDecimal((long) value);
        }

        if (value instanceof Double) {
            return BigDecimal.valueOf((double) value);
        }

        if (value instanceof Float) {
            return BigDecimal.valueOf((float) value);
        }

        if (value instanceof CharSequence) {
            return new BigDecimal(value.toString());
        }

        return new BigDecimal(0);
    }

    private static Object reduceToPrimitiveNumber(BigDecimal value) {
        if (value == null) {
            return null;
        }

        if (value.scale() == 0) {
            if (value.longValue() <= Integer.MAX_VALUE) {
                return value.intValue();
            }

            return value.longValue();
        }

        return value.doubleValue();
    }
}