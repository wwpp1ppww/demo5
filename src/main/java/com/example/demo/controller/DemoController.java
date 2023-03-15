package com.example.demo.controller;

import com.example.demo.dao.PersonDAO;
import com.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {

    private final PersonDAO personDAO;
    @Autowired
    public DemoController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/")
    public String Hello() {
        return "hello";
    }

    @GetMapping("/recipes")
    public String Recipes() {
        return "recipes";
    }

    @GetMapping("/ingredients")
    public String Ingredients() {
        return "ingredients";
    }

    @GetMapping("/people")
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        model.addAttribute("name", "");
        return "people";
    }

    @GetMapping("/person")
    public String newPerson(@ModelAttribute("person")Person person) {
        //@ModelAttribute("person")Person person
        return "person";


    }

}
