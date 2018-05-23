package converters;

import domain.Participate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ParticipateToStringConverter implements Converter<Participate, String> {

    @Override
    public String convert(Participate entity) {

        String result;
        if(entity == null){
            result = null;
        }else{
            result = String.valueOf(entity.getId());
        }
        return result;
    }
}
