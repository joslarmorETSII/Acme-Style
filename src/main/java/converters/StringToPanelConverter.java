package converters;

import domain.Panel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.FolderRepository;
import repositories.PanelRepository;
import services.PanelService;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToPanelConverter implements Converter<String, Panel>{

    @Autowired
    private PanelRepository panelRepository;

    @Override
    public Panel convert(String text) {
        Panel result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = panelRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
