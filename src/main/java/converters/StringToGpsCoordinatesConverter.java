package converters;

import domain.GpsCoordinates;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;

@Transactional
@Component
public class StringToGpsCoordinatesConverter implements Converter<String, GpsCoordinates> {

    @Override
    public GpsCoordinates convert(final String text) {
        GpsCoordinates result;
        String parts[];

        if (text == null)
            result = null;
        else
            try {
                parts = text.split("\\|");
                result = new GpsCoordinates();

                result.setName(URLDecoder.decode(parts[0], "UTF-8"));
                result.setLatitude(Double.valueOf(URLDecoder.decode(parts[1], "UTF-8")));
                result.setLongitude(Double.valueOf(URLDecoder.decode(parts[2], "UTF-8")));
            } catch (final Throwable oops) {
                throw new RuntimeException(oops);
            }
        return result;
    }
}
