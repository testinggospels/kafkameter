package co.signal.handlebars;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIntHelper implements Helper<Void> {

    @Override
    public Object apply(Void context, Options options) throws IOException {
        int lowerBound = coerceToInt(options.hash("lower", Integer.MIN_VALUE));
        int upperBound = coerceToInt(options.hash("upper", Integer.MAX_VALUE));
        return ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
    }

    public static Integer coerceToInt(Object value) {
        if (value == null) {
            return null;
        }

        if (Number.class.isAssignableFrom(value.getClass())) {
            return ((Number) value).intValue();
        }

        if (CharSequence.class.isAssignableFrom(value.getClass())) {
            return Integer.parseInt(value.toString());
        }

        return null;
    }
}