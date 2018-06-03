package controllers.Administrator;


import controllers.AbstractController;
import domain.Answer;
import domain.Feedback;
import domain.Question;
import domain.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.AnswerService;
import services.FeedbackService;

@Controller
@RequestMapping("/answer/administrator")
public class AnswerAdministratorController extends AbstractController {
    // Services --------------------------------------------

    @Autowired
    private AnswerService answerService;

    // Constructor --------------------------------------------

    public AnswerAdministratorController() {
        super();
    }

    // Edition --------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int answerId){
        ModelAndView result;

        Answer answer= answerService.findOne(answerId);
        Question question=answer.getQuestion();
        try{
            answerService.delete(answer);
            result = new ModelAndView("redirect:../../answer/actor/list.do?questionId="+question.getId());
        }catch (Throwable oops){
            result = createEditModelAndView(answer,"general.commit.error");
        }

        return result;
    }

    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(final Answer answer) {
        ModelAndView result;

        result = this.createEditModelAndView(answer, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final Answer answer, final String messageCode) {
        ModelAndView result;

        result = new ModelAndView("answer/edit");
        result.addObject("answer", answer);
        result.addObject("actionURI","answer/administrator/edit.do");
        result.addObject("message", messageCode);
        result.addObject("question",answer.getQuestion());

        return result;
    }
}
