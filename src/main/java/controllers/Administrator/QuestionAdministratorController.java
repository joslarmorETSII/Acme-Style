package controllers.Administrator;


import controllers.AbstractController;
import domain.Answer;
import domain.Question;
import domain.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.AnswerService;
import services.QuestionService;

@Controller
@RequestMapping("/question/administrator")
public class QuestionAdministratorController extends AbstractController {
    // Services --------------------------------------------

    @Autowired
    private QuestionService questionService;

    // Constructor --------------------------------------------

    public QuestionAdministratorController() {
        super();
    }

    // Edition --------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int questionId){
        ModelAndView result;

        Question question= questionService.findOne(questionId);
        Servise servise=question.getServise();
        try{
            questionService.delete(question);
            result = new ModelAndView("redirect:../../question/actor/list.do?serviseId="+servise.getId());
        }catch (Throwable oops){
            result = createEditModelAndView(question,"general.commit.error");
        }

        return result;
    }

    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(final Question question) {
        ModelAndView result;

        result = this.createEditModelAndView(question, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final Question question, final String messageCode) {
        ModelAndView result;

        result = new ModelAndView("question/add");
        result.addObject("question", question);
        result.addObject("actionURI","question/administrator/edit.do");
        result.addObject("message", messageCode);
        result.addObject("servise",question.getServise());

        return result;
    }
}
