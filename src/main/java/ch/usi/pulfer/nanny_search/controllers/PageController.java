package ch.usi.pulfer.nanny_search.controllers;

import ch.usi.pulfer.nanny_search.models.InformationRetrievalSystem;
import ch.usi.pulfer.nanny_search.models.QueryResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.usi.pulfer.nanny_search.models.Query;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PageController {

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String getIndex(Model model){
        model.addAttribute(new Query());
        return "../static/index";
    }

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.POST)
    public String postIndex(@ModelAttribute Query query, Model model){
        ArrayList<QueryResult> results = InformationRetrievalSystem.getResults(query);
        model.addAttribute("Results", results);
        return "../static/index";
    }
}
