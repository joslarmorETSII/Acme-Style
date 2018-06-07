package services;

import domain.Actor;
import domain.Category;
import domain.Comment;
import domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.CategoryRepository;
import repositories.PostRepository;
import security.Authority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class CategoryService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private CategoryRepository categoryRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private PostService postService;

    @Autowired
    private ActorService actorService;

    // Constructors -----------------------------------------------------------

    public CategoryService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Category create(){
        Category result;

        result = new Category();

        return result;
    }

    public Category findOne(int id){
        return categoryRepository.findOne(id);
    }

    public Category findOneToEdit(int id){
        Category res = categoryRepository.findOne(id);

        Assert.isTrue(actorService.isAdministrator());

        return res;
    }

    public Collection<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void delete(Category category){
        Assert.notNull(category);
        Assert.isTrue(actorService.isAdministrator());

        for(Post p : postService.findAll()){
            Assert.isTrue(!p.getCategories().contains(category), "This category contains posts");
        }

        categoryRepository.delete(category);
    }

    public Category save( Category category){
        Assert.notNull(category);
        Assert.isTrue(actorService.isAdministrator());

        return categoryRepository.save(category);
    }

    // Other business methods -------------------------------------------------

    public Collection<Category> categoriesAssociated(){
        return this.categoryRepository.categoriesAssociated();
    }

    public void flush() {
        categoryRepository.flush();
    }
}
