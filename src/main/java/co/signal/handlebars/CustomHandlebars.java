package co.signal.handlebars;

import java.io.IOException;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public enum CustomHandlebars implements Helper<Object> {
    randomValue {
        private final RandomValueHelper helper = new RandomValueHelper();

        @Override
        public Object apply(final Object context, final Options options) throws IOException {
            return this.helper.apply(null, options);
        }
    },
}