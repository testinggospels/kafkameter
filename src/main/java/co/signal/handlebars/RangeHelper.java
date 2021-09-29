package co.signal.handlebars;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RangeHelper implements Helper<Object> {

    @Override
    public Object apply(Object context, Options options) throws IOException {
        Integer lowerBound = coerceToInt(context);
        Integer upperBound = options.params.length > 0 ? coerceToInt(options.param(0)) : null;

        int limit = (upperBound - lowerBound) + 1;
        return Stream.iterate(lowerBound, n -> n + 1).limit(limit).collect(Collectors.toList());
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