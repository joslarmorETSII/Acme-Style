package converters;

import domain.Question;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class QuestionToStringConverter implements Converter<Question, String>{

    @Override
    public String convert(Question entity) {

        String result;
        if (entity == null)
            result = null;
        else
            result = String.valueOf(entity.getId());

        return result;
    }
}
