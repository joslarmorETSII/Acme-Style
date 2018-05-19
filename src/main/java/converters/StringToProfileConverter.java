package converters;

import domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ProfileRepository;

@Component
@Transactional
public class StringToProfileConverter implements Converter<String, Profile> {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile convert(String text) {
        Profile result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = profileRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
