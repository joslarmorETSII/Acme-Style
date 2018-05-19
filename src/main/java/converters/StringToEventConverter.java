package converters;

import domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.EventRepository;

@Transactional
@Component
public class StringToEventConverter implements Converter<String, Event> {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event convert(String text) {
        Event result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = eventRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
