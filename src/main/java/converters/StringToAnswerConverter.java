package converters;

import domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.AnswerRepository;

@Component
@Transactional
public class StringToAnswerConverter implements Converter<String, Answer> {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer convert(String text) {
        Answer result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = answerRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
