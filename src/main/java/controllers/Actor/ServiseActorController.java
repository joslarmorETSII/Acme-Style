package controllers.Actor;

import controllers.AbstractController;
import domain.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ServiseService;

@Controller
@RequestMapping("/servise/actor")
public class ServiseActorController extends AbstractController {

    // Services --------------------------------------------

    @Autowired
    private ServiseService serviseService;

    // Constructor --------------------------------------------

    public ServiseActorController() {
        super();
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
