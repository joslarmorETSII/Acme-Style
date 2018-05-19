package converters;

import domain.GpsCoordinates;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;

@Component
@Transactional
public class GpsCoordinatesToStringConverter implements Converter<GpsCoordinates, String> {

    @Override
    public String convert(GpsCoordinates gpsCoordinates) {
        String result;
        StringBuilder builder;

        if (gpsCoordinates == null)
            result = null;
        else
            try {
                builder = new StringBuilder();

                builder.append(URLEncoder.encode(gpsCoordinates.getName(), "UTF-8"));
                builder.append("|");
                builder.append(URLEncoder.encode(Double.toString(gpsCoordinates.getLatitude()), "UTF-8"));
                builder.append("|");
                builder.append(URLEncoder.encode(Double.toString(gpsCoordinates.getLongitude()), "UTF-8"));

                result = builder.toString();
            } catch (final Throwable oops) {
                throw new RuntimeException(oops);
            }
        return result;
    }
}
