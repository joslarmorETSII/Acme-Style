package converters;

import domain.Artist;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class ArtistToStringConverter implements Converter<Artist,String> {


    @Override
    public String convert(Artist artist) {
        String result;

        if (artist == null)
            result = null;
        else
            result = String.valueOf(artist.getId());

        return result;
    }
}
