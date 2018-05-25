package controllers.User;

import controllers.AbstractController;
import domain.*;
import forms.FeedbackForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.FeedbackService;
import services.ServiseService;
import services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/feedback/user")
public class FeedbackUserController extends AbstractController{

    // Services --------------------------------------------

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServiseService serviseService;

    // Constructor --------------------------------------------

    public FeedbackUserController() {
        super();
    }



    // Listing -------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam int serviseId) {
        ModelAndView result;
        User user;
        Servise servise;

        Collection<Feedback> feedbacks=new ArrayList<Feedback>();
        servise=serviseService.findOne(serviseId);

        user = userService.findByPrincipal();
        feedbacks=servise.getFeedbacks();


        result = new ModelAndView("feedback/list");
        result.addObject("feedbacks", feedbacks);
        result.addObject("user",user);
        result.addObject("requestURI","feedback/user/list.do");

        return result;

    }

    // Feedback --------------------------------------------------------------

    @RequestMapping(value = "/evaluate", method = RequestMethod.GET)
    public ModelAndView subscribe(@RequestParam int serviseId) {

        ModelAndView result;
        Servise servise;
        Collection<Integer> listPoints;

        User user = userService.findByPrincipal();
        servise=serviseService.findOne(serviseId);
        Assert.notNull(servise);
        Feedback feedback = feedbackService.feedbackByUserAndService(user.getId(),serviseId);
        Assert.isTrue(feedback==null,"Already left a feedback to this service");

        FeedbackForm feedbackForm = new FeedbackForm();
        feedbackForm.setServise(servise);

        result = new ModelAndView("feedback/feedbackForm");
        result.addObject("feedbackForm", feedbackForm);

        return result;
    }

    @RequestMapping(value = "/evaluate", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid FeedbackForm feedbackForm, BindingResult binding) {
        ModelAndView result;
        User user;
        Servise servise;
        Feedback feedback;

        try {
            feedback = userService.reconstructFeedback(feedbackForm, binding);

            if (binding.hasErrors())
                result = createEditModelAndView(feedbackForm);
            else {
                servise = feedbackForm.getServise();

                result = new ModelAndView("redirect: list.do?serviseId="+servise.getId());

                user = userService.findByPrincipal();
                feedback.setUser(user);
                feedback.setServise(servise);
                feedbackService.save(feedback);

            }
        } catch (final Throwable oops) {
            result = this.createEditModelAndView(feedbackForm, "general.commit.error");
        }

        return result;
    }

    protected ModelAndView createEditModelAndView(FeedbackForm feedbackForm) {
        return createEditModelAndView(feedbackForm,null);
    }

    protected ModelAndView createEditModelAndView(FeedbackForm feedbackForm, final String messageCode) {
        ModelAndView result;

        result = new ModelAndView("feedback/feedbackForm");
        result.addObject("feedbackForm", feedbackForm);
        result.addObject("message", messageCode);


        return result;
    }
}
