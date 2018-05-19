package converters;


import domain.Photo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class PhotoToStringConverter implements Converter<Photo, String>{

    @Override
    public String convert(Photo picture) {

        String result;
        if(picture == null){
            result = null;
        }else{
            result = String.valueOf(picture.getId());
        }
        return result;
    }

}