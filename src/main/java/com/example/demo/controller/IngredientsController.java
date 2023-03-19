package com.example.demo.controller;

import com.example.demo.dao.IngredientDAO;
import com.example.demo.dao.PersonDAO;
import com.example.demo.models.Ingredient;
import com.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String save(@ModelAttribute("ingredient") Ingredient ingredient, Model model) {
        Ingredient ing = ingredientDAO.save(ingredient);
        model.addAttribute("ingredient", ing);
        model.addAttribute("save","success");
        String s = "redirect:/ingredients/" + ing.getId() + "?source=create";
        return s;
    }

    @GetMapping ("/ingredients/new")
    public String create(@ModelAttribute("ingredient") Ingredient ingredient) {
        return "ingredientNew";
    }

    @GetMapping("/ingredients/{id}")
    public String ShowIngredient(@PathVariable("id") int id, Model model, @RequestParam(value = "source", required = false) String source) {
        model.addAttribute("ingredient", ingredientDAO.show(id));
        model.addAttribute("source", source == null ? "": source);
        return "ingredient";
    }

    @PostMapping("/ingredients/{id}")
    public String UpdateIngredient(@ModelAttribute("ingredient") Ingredient ingredient, Model model) {
        ingredientDAO.update(ingredient);
        String s = "redirect:/ingredients/" + ingredient.getId() + "?source=change";
        return s;
    }
}
