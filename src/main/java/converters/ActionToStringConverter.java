package converters;


import domain.Action;
import domain.Actor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class ActionToStringConverter implements Converter<Action, String>{

	@Override
	public String convert(Action action) {
		
		String result;
		if(action == null){
			result = null;
		}else{
			result = String.valueOf(action.getId());
		}
		return result;
	}

}