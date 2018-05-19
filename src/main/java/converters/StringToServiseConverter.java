package converters;

import domain.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ServiseRepository;


@Component
@Transactional
public class StringToServiseConverter implements Converter<String, Servise>{

    @Autowired
    private ServiseRepository serviseRepository;

    @Override
    public Servise convert(String text) {
        Servise result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = serviseRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
