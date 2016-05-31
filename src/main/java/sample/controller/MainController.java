package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sample.ContactService;
import sample.model.Contact;

/**
 * Created by oleh on 30.05.16.
 */

@Controller
public class MainController {
    @Autowired private ContactService contactService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("all");

        modelAndView.addObject("contacts",contactService.getAll());

        return modelAndView;
    }

    @RequestMapping(value = "/add", method= RequestMethod.GET)
    public ModelAndView showAddForm(){
        return new ModelAndView("add_form", "contact",new Contact());
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contact") Contact contact){
        if(contact.getId() == null) contactService.update(contact);

        return "redirect:/";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteContact (@RequestParam(required = true) Long id){
        contactService.remove(id);

        return "redirect:/";
    }

}
