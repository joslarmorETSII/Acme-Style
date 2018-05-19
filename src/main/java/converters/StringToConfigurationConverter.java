package converters;

import domain.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ConfigurationRepository;

@Transactional
@Component
public class StringToConfigurationConverter implements Converter<String, Configuration> {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Override
    public Configuration convert(String text) {
        Configuration result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = configurationRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
