package converters;

import domain.Artist;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import repositories.ArtistRepository;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToArtistConverter implements Converter<String, Artist> {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Artist convert( String text) {
        Artist result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = artistRepository.findOne(id);
            }
        } catch (final Throwable oops) {
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
