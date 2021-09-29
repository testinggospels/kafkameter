package co.signal.handlebars;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDecimalHelper implements Helper<Void> {

    @Override
    public Object apply(Void context, Options options) throws IOException {
        double lowerBound = coerceToDouble(options.hash("lower", Double.MIN_VALUE));
        double upperBound = coerceToDouble(options.hash("upper", Double.MAX_VALUE));

        return ThreadLocalRandom.current().nextDouble(lowerBound, upperBound);
    }

    public static Double coerceToDouble(Object value) {
        if (value == null) {
            return null;
        }

        if (Number.class.isAssignableFrom(value.getClass())) {
            return ((Number) value).doubleValue();
        }

        if (CharSequence.class.isAssignableFrom(value.getClass())) {
            return Double.parseDouble(value.toString());
        }

        return null;
    }
}