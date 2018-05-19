package converters;

import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.CommentRepository;

@Component
@Transactional
public class StringToCommentConverter implements Converter<String, Comment> {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment convert(String text) {
        Comment result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = commentRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
