
package controllers.Administrator;

import java.util.Collection;

import javax.validation.Valid;

import domain.Post;
import domain.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import controllers.AbstractController;
import domain.Category;
import services.PostService;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController extends AbstractController {

	// Services ------------------------------------------------------------

	@Autowired
	private CategoryService	categoryService;

	@Autowired
	private PostService postService;

	// Constructors --------------------------------------------------------

	public CategoryAdministratorController() {
		super();
	}

	// Listing  --------------------------------------------------------------

	// Creation  --------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result = null;
		Category category = null;

		category = this.categoryService.create();
		result = this.createEditModelAndView(category);

		return result;

	}

	// Listing -------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Category> categories = categoryService.findAll();
		Collection<Post> posts = postService.findAll();

		result = new ModelAndView("category/list");
		result.addObject("categories", categories);
		result.addObject("requestURI","category/administrator/list.do");
		result.addObject("cancelURI", "welcome/index.do");
		result.addObject("posts", posts);

		return result;

	}

	// Edition    --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryId) {

		ModelAndView result = null;
		Category category = null;

		category = this.categoryService.findOne(categoryId);
		Assert.notNull(category);

		result = this.createEditModelAndView(category);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Category category, final BindingResult bindingResult) {

		ModelAndView result = null;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(category);
		else
			try {
				this.categoryService.save(category);
				result = new ModelAndView("redirect:../list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(category, "general.commit.error");
			}

		return result;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int categoryId){
		ModelAndView result;

		Category category = this.categoryService.findOneToEdit(categoryId);
		categoryService.delete(category);
		result = new ModelAndView("redirect:list.do");

		return result;
	}

	// Ancillary methods ----------------------------------------------------

	protected ModelAndView createEditModelAndView(final Category category) {
		ModelAndView result = null;
		result = this.createEditModelAndView(category, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Category category, final String messageCode) {
		ModelAndView result = null;


		result = new ModelAndView("category/edit");
		result.addObject("category", category);
		result.addObject("message", messageCode);
		result.addObject("cancelURI", "category/administrator/list.do");
		return result;
	}
}
