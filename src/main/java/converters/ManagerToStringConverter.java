package converters;
import domain.Manager;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class ManagerToStringConverter  implements Converter<Manager,String> {


    @Override
    public String convert(Manager manager) {
        String result;

        if (manager == null)
            result = null;
        else
            result = String.valueOf(manager.getId());

        return result;
    }
}
