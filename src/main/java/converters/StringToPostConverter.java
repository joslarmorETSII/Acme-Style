package converters;

import domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.PostRepository;

@Component
@Transactional
public class StringToPostConverter implements Converter<String, Post> {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post convert(String text) {
        Post result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = postRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
