package converters;

import domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.FeedbackRepository;


@Component
@Transactional
public class StringToFeedbackConverter implements Converter<String, Feedback>{

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback convert(String text) {
        Feedback result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = feedbackRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
