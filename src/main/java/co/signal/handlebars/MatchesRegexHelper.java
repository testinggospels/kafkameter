package co.signal.handlebars;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.TagType;

import java.io.IOException;

public class MatchesRegexHelper implements Helper<Object> {

    @Override
    public Object apply(Object context, Options options) throws IOException {

        String value = context.toString();
        String regex = options.param(0);

        boolean isMatch = value.matches(regex);

        if (options.tagType == TagType.SECTION) {
            return isMatch ? options.apply(options.fn) : "";
        }

        return isMatch;
    }
}