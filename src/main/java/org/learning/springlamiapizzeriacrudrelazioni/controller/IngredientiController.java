package org.learning.springlamiapizzeriacrudrelazioni.controller;

import jakarta.validation.Valid;
import org.learning.springlamiapizzeriacrudrelazioni.model.Ingredienti;
import org.learning.springlamiapizzeriacrudrelazioni.repository.IngredientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {
    @Autowired
    public IngredientiRepository ingredientirepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredientiList", ingredientirepository.findAll());
        return "ingredienti/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("formIngredienti", new Ingredienti());
        return "ingredienti/form";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("formIngredienti") Ingredienti formIngredienti, BindingResult bindingresult) {
        if (bindingresult.hasErrors()) {
            return "ingredienti/form";
        }
        ingredientirepository.save(formIngredienti);
        return "redirect:/ingredienti";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Ingredienti> result = ingredientirepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("formIngredienti", result.get());
            return "ingredienti/form";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient with id " + id + " not found");

        }
    }
}
