package converters;

import domain.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.SubscriptionRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToSubscripitonConverter implements Converter<String, Subscription>{

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription convert(String text) {
        Subscription result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = subscriptionRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }


}
