package converters;

import domain.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import repositories.ActionRepository;
import security.UserAccount;
import security.UserAccountRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToActionConverter implements Converter<String, Action>{

	@Autowired
	ActionRepository actionRepository;

	@Override
	public Action convert(String text) {
		Action result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = actionRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
