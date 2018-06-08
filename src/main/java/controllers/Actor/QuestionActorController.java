package controllers.Actor;

import controllers.AbstractController;
import domain.Actor;
import domain.Question;
import domain.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.Authority;
import services.ActorService;
import services.QuestionService;
import services.ServiseService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/question/actor")
public class QuestionActorController extends AbstractController {

    // Services --------------------------------------------

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private ServiseService serviseService;

    // Constructor -----------------------------------------

    public QuestionActorController() {
        super();
    }

    // Creation ---------------------------------------------------------------

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam int serviseId) {
        ModelAndView result;
        Question question;
        Servise servise;

        servise = serviseService.findOne(serviseId);
        question = questionService.create();
        question.setServise(servise);
        result = new ModelAndView("question/add");
        result.addObject("question", question);
        result.addObject("requestURI","question/actor/edit.do");
        result.addObject("cancelURI","question/actor/list.do?serviseId="+serviseId);

        return result;
    }

    // Edit ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.POST,params = "save")
    public ModelAndView edit(@Valid Question question, BindingResult binding){
        ModelAndView result;
        Actor actor = actorService.findByPrincipal();

        if (binding.hasErrors()) {
            result = createEditModelAndView(question);
        } else
            try {
                questionService.save(question);
                result = new ModelAndView("redirect:/question/actor/list.do?serviseId="+question.getServise().getId());
            } catch (Throwable oops) {
                result = createEditModelAndView(question, "general.commit.error");
            }

        return result;
    }

    // Listing ----------------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam int serviseId) {
        ModelAndView result;
        Servise servise;
        Collection<Question> questions;
        Actor actor = actorService.findByPrincipal();

        servise = serviseService.findOne(serviseId);
        Assert.notNull(servise);
        questions = servise.getQuestions();
        result = new ModelAndView("question/list");
        result.addObject("requestURI", "gallery/actor/list.do?serviceId="+serviseId);
        result.addObject("cancelURI", "servise/listServisesPublished.do");
        result.addObject("questions", questions);
        result.addObject("servise", servise);

        return result;
    }

    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(Question question) {
        return createEditModelAndView(question, null);
    }

    protected ModelAndView createEditModelAndView(Question question ,  String text) {
        ModelAndView result;

        result = new ModelAndView("question/add");
        result.addObject("question", question);
        result.addObject("message", text);
        result.addObject("requestURI", "question/actor/edit.do");
        result.addObject("cancelURI","question/actor/list.do?serviseId="+question.getServise().getId());

        return result;
    }

}
