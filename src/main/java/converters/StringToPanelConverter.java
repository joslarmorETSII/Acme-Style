package converters;

import domain.Panel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToPanelConverter implements Converter<Panel, String>{

    @Override
    public String convert(Panel panel) {

        String result;
        if(panel == null){
            result = null;
        }else{
            result = String.valueOf(panel.getId());
        }
        return result;
    }
}
