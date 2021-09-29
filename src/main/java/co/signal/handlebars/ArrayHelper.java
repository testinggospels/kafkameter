package co.signal.handlebars;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.google.common.collect.ImmutableList;

import java.io.IOException;

import static java.util.Arrays.asList;

public class ArrayHelper implements Helper<Object> {

    @Override
    public Object apply(Object context, Options options) throws IOException {
        if (context == null || context == options.context.model()) {
            return ImmutableList.of();
        }

        return ImmutableList.builder().add(context).addAll(asList(options.params)).build();
    }
}