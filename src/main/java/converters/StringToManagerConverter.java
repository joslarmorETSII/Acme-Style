package converters;
import domain.Manager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.ManagerRepository;
import javax.transaction.Transactional;

@Component
@Transactional
public class StringToManagerConverter implements Converter<String, Manager> {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Manager convert(String text) {
        Manager result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = managerRepository.findOne(id);
            }
        } catch (final Throwable oops) {
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
