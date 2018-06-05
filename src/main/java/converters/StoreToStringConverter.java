package converters;

import domain.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.StoreRepository;

@Transactional
@Component
public class StoreToStringConverter implements Converter<Store, String> {

    @Override
    public String convert(Store entity) {

        String result;
        if (entity == null)
            result = null;
        else
            result = String.valueOf(entity.getId());

        return result;
    }
}
