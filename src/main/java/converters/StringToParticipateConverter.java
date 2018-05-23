package converters;

import domain.Participate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ParticipateRepository;

@Transactional
@Component
public class StringToParticipateConverter implements Converter<String, Participate> {

    @Autowired
    private ParticipateRepository participateRepository;

    @Override
    public Participate convert(String text) {
        Participate result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = participateRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
