package controllers;

import domain.Artist;
import domain.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ServiseService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/servise")
public class ServiseController extends AbstractController {

    // Services --------------------------------------------

    @Autowired
    private ServiseService serviseService;

    // Constructor --------------------------------------------

    public ServiseController() {
        super();
    }

    // Listing -------------------------------------------------------

    @RequestMapping(value = "/listServisesPublished", method = RequestMethod.GET)
    public ModelAndView listServisesPublished() {
        ModelAndView result;
        Artist artist;
        Collection<Servise> servises=new ArrayList<>();

        servises=serviseService.findAll();

        result = new ModelAndView("servise/list");
        result.addObject("servises", servises);
        result.addObject("requestURI","servise/listServisesPublished.do");

        return result;

    }
}
