package converters;

import domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.QuestionRepository;


@Component
@Transactional
public class StringToQuestionConverter implements Converter<String, Question>{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question convert(String text) {
        Question result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = questionRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
