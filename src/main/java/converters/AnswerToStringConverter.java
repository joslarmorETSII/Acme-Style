package converters;

import domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.AnswerRepository;

@Component
@Transactional
public class AnswerToStringConverter implements Converter<Answer, String>{

    @Override
    public String convert(Answer entity) {

        String result;
        if (entity == null)
            result = null;
        else
            result = String.valueOf(entity.getId());

        return result;
    }
}
