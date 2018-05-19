package converters;

import domain.Raffle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.RaffleRepository;

@Component
@Transactional
public class StringToRaffleConverter implements Converter<String, Raffle> {

    @Autowired
    private RaffleRepository raffleRepository;

    @Override
    public Raffle convert(String text) {
        Raffle result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = raffleRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
