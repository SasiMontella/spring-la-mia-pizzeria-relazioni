package org.learning.springlamiapizzeriacrudrelazioni.controller;

import org.learning.springlamiapizzeriacrudrelazioni.model.Offerta;
import org.learning.springlamiapizzeriacrudrelazioni.model.Pizza;
import org.learning.springlamiapizzeriacrudrelazioni.repository.OffertaRepository;
import org.learning.springlamiapizzeriacrudrelazioni.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/offerte")
public class OffertaController {
        @Autowired
        private PizzaRepository pizzarepository;
        @Autowired
        private OffertaRepository offertarepository;

@GetMapping("/create")
public String create(@RequestParam Integer pizzaId, Model model){
    Optional<Pizza> result = pizzarepository.findById(pizzaId);
        if (result.isPresent()){
            Pizza offertaPizza = result.get();
            model.addAttribute("pizza", offertaPizza);

            Offerta newOfferta = new Offerta();
            newOfferta.setPizza(offertaPizza);
            newOfferta.setDataInizio(LocalDate.now());
            model.addAttribute("offerta", newOfferta);
            return "offerte/create";
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + pizzaId + " not found");
        }
    }
@PostMapping("/create")
public String store(Offerta formOfferta){
        Offerta storedOfferta = offertarepository.save(formOfferta);
        return "redirect:/pizza/show/" +storedOfferta.getPizza().getId();
    }

}
