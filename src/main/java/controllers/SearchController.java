/*
 *
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.Authority;
import services.*;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/search")
public class SearchController extends AbstractController {

	// Managed repository -----------------------------------------------------

	// Supporting services ----------------------------------------------------

	@Autowired
	private ServiseService serviseService;

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@Autowired
	private AdministratorService administratorService;

	// Constructors -----------------------------------------------------------

	public SearchController() {
		super();
	}

	// Search ---------------------------------------------------------------

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam String keyword ){
		ModelAndView result;
		Collection<Actor> actors = userService.searchActorsPerKeyword(keyword);
		Collection<Actor> res = new ArrayList<Actor>(actors);
		result = new ModelAndView("search/search");

		res.remove(administratorService.findAll().iterator().next());

		result.addObject("keyword", keyword);
		result.addObject("servises", serviseService.searchServisesPerKeyword(keyword));
		result.addObject("events",eventService.searchEventsPerKeyword(keyword));
		result.addObject("actors",res);
		result.addObject("requestURI", "search/search.do");
		result.addObject("cancelURI", "welcome/index.do");

		return result;
	}

}
