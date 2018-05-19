package converters;

import domain.Subscription;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class SubscriptionToStringConverter implements Converter<Subscription, String>{

    @Override
    public String convert(Subscription subscription) {

        String result;
        if(subscription == null){
            result = null;
        }else{
            result = String.valueOf(subscription.getId());
        }
        return result;
    }

}
