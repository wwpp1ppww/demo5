package com.example.demo.controller;

import com.example.demo.dao.IngredientDAO;
import com.example.demo.dao.PersonDAO;
import com.example.demo.models.Ingredient;
import com.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientsController {

    private final IngredientDAO ingredientDAO;

    @Autowired
    public IngredientsController(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }


    @GetMapping("/ingredients")
    public String Ingredients(Model model) {
        model.addAttribute("ingredients", ingredientDAO.index());
        return "ingredients";
    }

    @PostMapping("/ingredients")
    public String save(@ModelAttribute("ingredient") Ingredient ingredient) {
        ingredientDAO.save(ingredient);
        return "redirect:/ingredients";
    }

    @GetMapping ("/ingredients/new")
    public String create(@ModelAttribute("ingredient") Ingredient ingredient) {
        return "ingredientNew";
    }

    @GetMapping("/ingredients/{id}")
    public String Ingredient(@PathVariable("id") int id, Model model) {
        model.addAttribute("ingredient", ingredientDAO.show(id));
        return "ingredient";
    }


}
