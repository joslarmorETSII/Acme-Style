package controllers.Artist;

import controllers.AbstractController;
import domain.Answer;
import domain.Question;
import domain.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.AnswerService;
import services.QuestionService;

import javax.validation.Valid;

@Controller
@RequestMapping("/answer/artist")
public class AnswerArtistController extends AbstractController{
    // Services ----------------------------------------------

    @Autowired
    private ActorService actorService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    // Constructor --------------------------------------------

    public AnswerArtistController() {
        super();
    }

    // Add answer ---------------------------------------------

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView answer(@RequestParam int questionId) {
        ModelAndView result;
        Answer answer;
        Question question;

        question = questionService.findOneToEdit(questionId);
        answer = answerService.create();
        answer.setQuestion(question);
        result = new ModelAndView("answer/add");
        result.addObject("answer", answer);

        return result;
    }

    // Edit ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.POST,params = "save")
    public ModelAndView edit(@Valid Answer answer, BindingResult binding){
        ModelAndView result;
        if (binding.hasErrors()) {
            result = createEditModelAndView(answer);
        } else
            try {
                answerService.save(answer);
                result = new ModelAndView("redirect:/answer/actor/list.do?questionId="+answer.getQuestion().getId());
            } catch (Throwable oops) {
                result = createEditModelAndView(answer, "general.commit.error");
            }

        return result;
    }
    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(Answer answer) {
        return createEditModelAndView(answer, null);
    }

    protected ModelAndView createEditModelAndView(Answer answer ,  String text) {
        ModelAndView result;

        result = new ModelAndView("answer/add");
        result.addObject("answer", answer);
        result.addObject("message", text);

        return result;
    }


}
