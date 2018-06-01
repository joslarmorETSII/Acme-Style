package controllers;

import domain.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController extends AbstractController{

    // Supporting Services --------------------------------

    @Autowired
    private StoreService storeService;


    // Constructor -----------------------------------------

    public StoreController() {
        super();
    }

    // Display ----------------------------------------------

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display(@RequestParam  int storeId) {
        ModelAndView result;
        Store store;

        store = storeService.findOne(storeId);

        result = new ModelAndView("store/display");
        result.addObject("store", store);
        result.addObject("cancelURI", "welcome/index.do");


        return result;
    }
}
