package converters;

import domain.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.StoreRepository;

@Transactional
@Component
public class StoreToStringConverter implements Converter<String, Store> {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store convert(String text) {
        Store result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = storeRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
