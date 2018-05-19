package converters;

import domain.Photo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.PhotoRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToPhotoConverter implements Converter<String, Photo> {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Photo convert(final String text) {
        Photo result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = photoRepository.findOne(id);
            }
        } catch (final Throwable oops) {
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}