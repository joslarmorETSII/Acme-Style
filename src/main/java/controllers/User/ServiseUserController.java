
package controllers.User;

import controllers.AbstractController;
import domain.*;
import forms.SubscribeServiseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.HttpAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ServiseService;
import services.SubscribeService;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/servise/user")
public class ServiseUserController extends AbstractController {

    // Services --------------------------------------------

    @Autowired
    private ServiseService serviseService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubscribeService subscribeService;





    // Constructor --------------------------------------------

    public ServiseUserController() {
        super();
    }



    // Listing -------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView result;
        User user;

        Collection<Servise> servises=new ArrayList<>();

        user = userService.findByPrincipal();
        servises=subscribeService.servisesSubscribedPerUser(user.getId());


        result = new ModelAndView("servise/listServiseUser");
        result.addObject("servises", servises);
        result.addObject("user",user);
        result.addObject("requestURI","servise/user/list.do");

        return result;

    }

    @RequestMapping(value = "/listServisesToSubscribe", method = RequestMethod.GET)
    public ModelAndView listServisesToSubscribe() {
        ModelAndView result;
        User user = userService.findByPrincipal();

        Collection<Servise> servisesPublished = serviseService.findAll();
        Collection<Servise> servisesSubscribed= subscribeService.servisesSubscribedPerUser(user.getId());

        servisesPublished.removeAll(servisesSubscribed);

        result = new ModelAndView("servise/list");
        result.addObject("servises", servisesPublished);
        result.addObject("requestURI","servise/user/listServisesToSubscribe.do");

        return result;

    }


    // Subscribe --------------------------------------------------------------

    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    public ModelAndView subscribe(@RequestParam int serviseId) {

        ModelAndView result;
        Servise servise;
        User user = userService.findByPrincipal();
        servise=serviseService.findOne(serviseId);
        Assert.notNull(servise);
        Subscription subscription = subscribeService.subscriptionByUserAndService(user.getId(),serviseId);
        Assert.isTrue(subscription==null,"Already subscribed to this service");

        SubscribeServiseForm subscribeServiseForm = new SubscribeServiseForm();
        subscribeServiseForm.setServise(servise);

        result = new ModelAndView("user/subscribeServiseForm");
        result.addObject("subscribeServiseForm", subscribeServiseForm);

        return result;
    }

    @RequestMapping(value = "/unsubscribe", method = RequestMethod.GET)
    public ModelAndView unsubscribe(@RequestParam int serviseId) {
        ModelAndView result;
        Servise servise;
        User principal;

        servise = serviseService.findOne(serviseId);
        Assert.notNull(servise);
        principal = userService.findByPrincipal();
        Subscription subscription = subscribeService.subscriptionByUserAndService(principal.getId(),serviseId);
        subscribeService.delete(subscription);

        result = new ModelAndView("redirect:listServisesToSubscribe.do");

        return result;
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid SubscribeServiseForm subscribeServiseForm, BindingResult binding) {
        ModelAndView result;
        User user;
        Servise servise;
        Boolean IsSuscribed;
        Subscription subscription;

        try {
            CreditCard creditCard = userService.reconstructSubscribeServise(subscribeServiseForm, binding);

            if (binding.hasErrors())
                result = createEditModelAndView(subscribeServiseForm);
            else {
                result = new ModelAndView("redirect:list.do");

                subscription = subscribeService.create();
                user = userService.findByPrincipal();
                servise = subscribeServiseForm.getServise();
                subscription.setUser(user);
                subscription.setServise(servise);
                subscription.setCreditCard(creditCard);
                subscribeService.save(subscription);
                IsSuscribed= true;
            }
        } catch (final Throwable oops) {
            result = this.createEditModelAndView(subscribeServiseForm, "general.commit.error");
        }

        return result;
    }

    protected ModelAndView createEditModelAndView(SubscribeServiseForm subscribeServiseForm) {
        return createEditModelAndView(subscribeServiseForm,null);
    }

    protected ModelAndView createEditModelAndView(SubscribeServiseForm subscribeServiseForm, final String messageCode) {
        ModelAndView result;

        result = new ModelAndView("user/subscribeServiseForm");
        result.addObject("subscribeServiseForm", subscribeServiseForm);
        result.addObject("message", messageCode);


        return result;
    }

    // Display ----------------------------------------------------------------

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display(@RequestParam final int serviseId) {
        ModelAndView result;
        Servise servise= null;
        String finalPrice;

        servise = this.serviseService.findOne(serviseId);
        finalPrice=serviseService.finalPrice(servise);


        result = new ModelAndView("servise/display");
        result.addObject("servise", servise);
        result.addObject("cancelURI", "welcome/index.do");
        result.addObject("finalPrice",finalPrice);


        return result;
    }

}
