package controllers.Actor;

import controllers.AbstractController;
import domain.Actor;
import domain.Answer;
import domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.QuestionService;

import java.util.Collection;

@Controller
@RequestMapping("/answer/actor")
public class AnswerActorController extends AbstractController {
    // Services ----------------------------------------------

    @Autowired
    private ActorService actorService;

    @Autowired
    private QuestionService questionService;

    // Constructor --------------------------------------------

    public AnswerActorController() {
        super();
    }

    // Listing ------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam int questionId) {
        ModelAndView result;
        Question question;
        Collection<Answer> answers;
        Actor actor;

        actor = actorService.findByPrincipal();
        question = questionService.findOne(questionId);
        Assert.notNull(question);
        answers = question.getAnswers();
        result = new ModelAndView("answer/list");
        result.addObject("answers", answers);
        result.addObject("question", question);
        result.addObject("owner",actor.equals(question.getServise().getCreator()));
        result.addObject("requestURI","answer/actor/list.do?questionId="+questionId);

        return result;
    }
}
