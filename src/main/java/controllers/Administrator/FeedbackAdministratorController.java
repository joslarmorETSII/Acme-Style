package controllers.Administrator;


import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import java.util.Collection;

@Controller
@RequestMapping("/feedback/administrator")
public class FeedbackAdministratorController extends AbstractController {
    // Services --------------------------------------------

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private ServiseService serviseService;





    // Constructor --------------------------------------------

    public FeedbackAdministratorController() {
        super();
    }

    // Edition --------------------------------------------




    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int feedbackId){
        ModelAndView result;


        Feedback feedback= feedbackService.findOne(feedbackId);
        Servise servise=feedback.getServise();
        try{

            feedbackService.delete(feedback);
            result = new ModelAndView("redirect:../../servise/administrator/display.do?serviseId="+servise.getId());
        }catch (Throwable oops){
            result = createEditModelAndView(feedback,"general.commit.error");
        }

        return result;
    }



    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(final Feedback feedback) {
        ModelAndView result;

        result = this.createEditModelAndView(feedback, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final Feedback feedback, final String messageCode) {
        ModelAndView result;


        result = new ModelAndView("feedback/edit");
        result.addObject("feedback", feedback);
        result.addObject("actionURI","feedback/administrator/edit.do");
        result.addObject("message", messageCode);
        result.addObject("servise",feedback.getServise());

        return result;
    }
}
